package com.formation.jpa.dal;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import com.formation.jpa.bean.Product;
import com.formation.jpa.bean.ShoppingCartItem;
import com.formation.jpa.util.DAOUtil;
import com.formation.jpa.util.dto.ProductData;

public class ShoppingCartItemDaoImpl implements ShoppingCartItemDao {

	public void create(ShoppingCartItem s) throws Exception {
		EntityManager em = DAOUtil.getEntityManager();
		EntityTransaction et = em.getTransaction();
		System.out.println(s.toString());
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

	public void delete(ShoppingCartItem s) throws Exception {
		EntityManager em = DAOUtil.getEntityManager();
		EntityTransaction et = em.getTransaction();
		System.out.println(s.getId());
		ShoppingCartItem cart = em.find(ShoppingCartItem.class, s.getId());
		et.begin();
		try {
			em.remove(cart);
			et.commit();
		} catch (Exception e) {
			e.printStackTrace();
			et.rollback();
			throw e;
		}
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

	public ShoppingCartItem findById(int id) {
		EntityManager em = DAOUtil.getEntityManager();
		ShoppingCartItem s = em.find(ShoppingCartItem.class, id);
		em.close();
		return s;
	}

	@Override
	public List<ShoppingCartItem> findAll() {
		String req = "select Object(s) from ShoppingCartItem s";
		EntityManager em = DAOUtil.getEntityManager();
		List<ShoppingCartItem> liste = em.createQuery(req, ShoppingCartItem.class).getResultList();
		em.close();

		return liste;
	}

	public List<ShoppingCartItem> findAllGroupBy() {
		EntityManager em = DAOUtil.getEntityManager();
		String req = "select Object(s) from ShoppingCartItem s GROUP BY s.product";
		List<ShoppingCartItem> liste = em.createQuery(req, ShoppingCartItem.class).getResultList();
		em.close();
		return liste;
	}

	public void createByProductData(ProductData productData) throws Exception {
		EntityManager em = DAOUtil.getEntityManager();
		Product p = em.find(Product.class, productData.getId());
		String req = "select Object(s) from ShoppingCartItem s where s.product.id =:id";
		TypedQuery<ShoppingCartItem> querry = em.createQuery(req, ShoppingCartItem.class).setParameter("id",
				productData.getId());
		try {
			ShoppingCartItem item = querry.getSingleResult();
			if(item != null) {
				item.setQuantity(productData.getQuantity());
				update(item);
				
			}
		} catch (Exception e) {
			ShoppingCartItem item = new ShoppingCartItem();
			item.setProduct(p);
			item.setQuantity(productData.getQuantity());
			create(item);
		}

		em.close();

	}

}
