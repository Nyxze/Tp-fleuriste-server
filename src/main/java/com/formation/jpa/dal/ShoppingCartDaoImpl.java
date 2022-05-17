package com.formation.jpa.dal;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import com.formation.jpa.bean.Saison;
import com.formation.jpa.bean.ShoppingCart;
import com.formation.jpa.util.DAOUtil;



public class ShoppingCartDaoImpl implements ShoppingCartDao {

	
	public void create(ShoppingCart s) throws Exception{
		EntityManager em = DAOUtil.getEntityManager();
		EntityTransaction et = em.getTransaction();
		et.begin();
		try {
			em.persist(s);
			et.commit();
		} catch (Exception e) {
			et.rollback();
			throw e;
		}
		finally {
			em.close();
		}
	}

	public void delete(ShoppingCart s) throws Exception{
		EntityManager em = DAOUtil.getEntityManager();
		EntityTransaction et = em.getTransaction();
		ShoppingCart cart = em.find(ShoppingCart.class, s.getId());
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

	public  void update(ShoppingCart s) throws Exception{
		EntityManager em = DAOUtil.getEntityManager();
		EntityTransaction et = em.getTransaction();
		et.begin();
		try {
			em.merge(s);
			et.commit();
		} catch (Exception e) {
			et.rollback();
			throw e;
		}
		finally {
			em.close();
		}
	}
	
	public void update(List<ShoppingCart> listeCart) throws Exception{
		EntityManager em = DAOUtil.getEntityManager();
		EntityTransaction et = em.getTransaction();
		et.begin();
		try {
			for (ShoppingCart s : listeCart) {
				em.merge(s);
			}
			
			et.commit();
		} catch (Exception e) {
			et.rollback();
			throw e;
		}
		finally {
			em.close();
		}
	}
	
	public ShoppingCart findById(int id){
		EntityManager em = DAOUtil.getEntityManager();
		ShoppingCart s = em.find(ShoppingCart.class, id);
		em.close();
		return s;
	}
	
	@Override
	public  List<ShoppingCart> findAll(){
		String req = "select Object(s) from Saison s";
		EntityManager em = DAOUtil.getEntityManager();
		List<ShoppingCart> liste = em
				.createQuery(req, ShoppingCart.class)
				.getResultList();
		em.close();
		
		return liste;
	}

	

}
