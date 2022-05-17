package com.formation.jpa.dal;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import com.formation.jpa.bean.Style;
import com.formation.jpa.util.DAOUtil;



public class StyleDaoImpl implements StyleDao{

	
	public void add(Style s) throws Exception{
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

	public void delete(Style s) throws Exception{
		EntityManager em = DAOUtil.getEntityManager();
		EntityTransaction et = em.getTransaction();
		Style style = em.find(Style.class, s.getId());
		et.begin();
		try {
			em.remove(style);
			et.commit();
		} catch (Exception e) {
			e.printStackTrace();
			et.rollback();
			throw e;
		}
		finally {
			em.close();
		}
	}

	public  void update(Style s) throws Exception{
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
	
	public void update(List<Style> listeStyle) throws Exception{
		EntityManager em = DAOUtil.getEntityManager();
		EntityTransaction et = em.getTransaction();
		et.begin();
		try {
			for (Style t : listeStyle) {
				em.merge(t);
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
	
	public Style findById(int id){
		EntityManager em = DAOUtil.getEntityManager();
		Style s = em.find(Style.class, id);
		em.close();
		return s;
	}
	
	@Override
	public  List<Style> findAll(){
		String req = "select Object(s) from Style s";
		EntityManager em = DAOUtil.getEntityManager();
		List<Style> liste = em
				.createQuery(req, Style.class)
				.getResultList();
		em.close();
		
		return liste;
	}

}
