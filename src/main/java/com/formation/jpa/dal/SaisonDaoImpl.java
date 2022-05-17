package com.formation.jpa.dal;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import com.formation.jpa.bean.Saison;
import com.formation.jpa.util.DAOUtil;



public class SaisonDaoImpl implements SaisonDao {

	
	public void add( Saison s) throws Exception{
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

	public void delete(Saison s) throws Exception{
		EntityManager em = DAOUtil.getEntityManager();
		EntityTransaction et = em.getTransaction();
		Saison Saison = em.find(Saison.class, s.getId());
		et.begin();
		try {
			em.remove(Saison);
			et.commit();
		} catch (Exception e) {
			e.printStackTrace();
			et.rollback();
			throw e;
		}
	}

	public  void update(Saison s) throws Exception{
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
	
	public void update(List<Saison> listeSaison) throws Exception{
		EntityManager em = DAOUtil.getEntityManager();
		EntityTransaction et = em.getTransaction();
		et.begin();
		try {
			for (Saison t : listeSaison) {
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
	
	public Saison findById(int id){
		EntityManager em = DAOUtil.getEntityManager();
		Saison s = em.find(Saison.class, id);
		em.close();
		return s;
	}
	
	@Override
	public  List<Saison> findAll(){
		String req = "select Object(s) from Saison s";
		EntityManager em = DAOUtil.getEntityManager();
		List<Saison> liste = em
				.createQuery(req, Saison.class)
				.getResultList();
		em.close();
		
		return liste;
	}

}
