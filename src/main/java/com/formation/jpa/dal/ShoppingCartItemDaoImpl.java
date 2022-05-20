package com.formation.jpa.dal;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import com.formation.jpa.bean.Product;
import com.formation.jpa.bean.ShoppingCart;
import com.formation.jpa.bean.ShoppingCartItem;
import com.formation.jpa.bean.User;
import com.formation.jpa.util.DAOUtil;
import com.formation.jpa.util.dto.ProductData;

public class ShoppingCartItemDaoImpl implements ShoppingCartItemDao {

	public void create(ShoppingCartItem s) throws Exception {
		EntityManager em = DAOUtil.getEntityManager();
		EntityTransaction et = em.getTransaction();
		et.begin();
		try {
			em.persist(s);
			et.commit();
		} catch (Exception e) {
			et.rollback();
			throw e;
		} finally {
			em.close();
		}
	}

	public void delete(ShoppingCartItem s, int shoppingCartId) throws Exception {
		EntityManager em = DAOUtil.getEntityManager();
		EntityTransaction et = em.getTransaction();
		Product p = em.find(Product.class, s.getProduct().getId());

		et.begin();
		try {
			String req = "delete from ShoppingCartItem s where s.id =:id and s.shoppingCart.id =:scid";
			em.createQuery(req).setParameter("id", s.getId()).setParameter("scid", shoppingCartId).executeUpdate();
			p.setStock(p.getStock() + s.getQuantity());
			updateStock(p);
			et.commit();
		} catch (Exception e) {
			e.printStackTrace();
			et.rollback();
			throw e;
		}
		em.close();
	}

	public void update(ShoppingCartItem s) throws Exception {
		EntityManager em = DAOUtil.getEntityManager();
		EntityTransaction et = em.getTransaction();
		et.begin();
		try {
			em.merge(s);
			et.commit();
		} catch (Exception e) {
			et.rollback();
			throw e;
		} finally {
			em.close();
		}
	}

	public void update(List<ShoppingCartItem> listeCartItem) throws Exception {
		EntityManager em = DAOUtil.getEntityManager();
		EntityTransaction et = em.getTransaction();
		et.begin();
		try {
			for (ShoppingCartItem s : listeCartItem) {
				em.merge(s);
			}

			et.commit();
		} catch (Exception e) {
			et.rollback();
			throw e;
		} finally {
			em.close();
		}
	}

	public ShoppingCartItem findById(int id, int shoppingCartId) {
		EntityManager em = DAOUtil.getEntityManager();

		String req = "select Object(s) from ShoppingCartItem s where s.id =:id and s.shoppingCart.id =:scid";
		TypedQuery<ShoppingCartItem> querry = em.createQuery(req, ShoppingCartItem.class).setParameter("id", id)
				.setParameter("scid", shoppingCartId);
		ShoppingCartItem s = querry.getSingleResult();
		em.close();
		return s;
	}

	@Override
	public List<ShoppingCartItem> findAll(int id) {
		//
		String req = "select sci from ShoppingCart sc join sc.listCartItem sci";
		EntityManager em = DAOUtil.getEntityManager();
		List<ShoppingCartItem> liste = em.createQuery(req, ShoppingCartItem.class).getResultList();
		for (ShoppingCartItem shoppingCartItem : liste) {
			System.out.println(shoppingCartItem.toString());
		}
		em.close();

		return liste;
	}

	public List<ShoppingCartItem> findAllGroupBy(int id) {
		EntityManager em = DAOUtil.getEntityManager();
		String req = "select Object(sci) from ShoppingCart sc join sc.listCartItem sci where sc.id=" + id;
		List<ShoppingCartItem> liste = em.createQuery(req, ShoppingCartItem.class).getResultList();
		em.close();
		return liste;
	}

	public void createByProductData(ProductData productData, int id) throws Exception {

		EntityManager em = DAOUtil.getEntityManager();
		Product p = em.find(Product.class, productData.getId());
		ShoppingCart sc = em.find(ShoppingCart.class, id);

		String req = "select Object(s) from ShoppingCartItem s where s.product.id =:id and s.shoppingCart.id =:scid";
		TypedQuery<ShoppingCartItem> querry = em.createQuery(req, ShoppingCartItem.class)
				.setParameter("id", productData.getId()).setParameter("scid", id);

		try {
			ShoppingCartItem item = querry.getSingleResult();

			if (item != null) {

				System.out.println("PRODUCT STOCK AVT" +p.getStock());
				System.out.println("ITEM QT" + item.getQuantity());
				p.setStock(p.getStock() + item.getQuantity() - productData.getQuantity());
				System.out.println("PRODUCT STOCK APRES" +p.getStock());
				item.setQuantity(productData.getQuantity());
				if (p.getStock()>=0) {
					updateStock(p);
					update(item);
				} else {
					System.out.println("Product Stock" + p.getStock() + "Commande qt" + item.getQuantity());
				}

			}
		} catch (Exception e) {
			ShoppingCartItem item = new ShoppingCartItem();
			item.setProduct(p);
			item.setShoppingCart(sc);
			item.setQuantity(productData.getQuantity());
			p.setStock(p.getStock() - item.getQuantity());

			if (p.getStock()>=0) {
				updateStock(p);
				create(item);
			} else {
				System.out.println("Product Stock" + p.getStock() + "Commande qt" + item.getQuantity());
			}

			e.printStackTrace();
		}

		em.close();

	}

	private void updateStock(Product product) {
		EntityManager em = DAOUtil.getEntityManager();
		EntityTransaction et = em.getTransaction();
		et.begin();
		try {
			em.merge(product);
			et.commit();
		} catch (Exception e) {
			et.rollback();
			throw e;
		} finally {
			em.close();
		}
	}

}
