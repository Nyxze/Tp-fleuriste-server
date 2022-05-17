package com.formation.jpa.dal;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import com.formation.jpa.bean.Fleur;
import com.formation.jpa.util.DAOUtil;



public class FleurDaoImpl implements FleurDao{

	
	public void add(Fleur f) throws Exception{
		
		EntityManager em = DAOUtil.getEntityManager();
		EntityTransaction et = em.getTransaction();
		et.begin();
		try {
			em.persist(f);
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

	public void delete(Fleur f) throws Exception{
		EntityManager em = DAOUtil.getEntityManager();
		EntityTransaction et = em.getTransaction();
		Fleur Fleur = em.find(Fleur.class, f.getId());
		et.begin();
		try {
			em.remove(Fleur);
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

	public  void update(Fleur f) throws Exception{
		EntityManager em = DAOUtil.getEntityManager();
		EntityTransaction et = em.getTransaction();
		et.begin();
		try {
			em.merge(f);
			et.commit();
		} catch (Exception e) {
			et.rollback();
			throw e;
		}
		finally {
			em.close();
		}
	}
	
	public void update(List<Fleur> listeFleur) throws Exception{
		EntityManager em = DAOUtil.getEntityManager();
		EntityTransaction et = em.getTransaction();
		et.begin();
		try {
			for (Fleur f : listeFleur) {
				em.merge(f);
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
	
	public Fleur findById(int id){
		EntityManager em = DAOUtil.getEntityManager();
		Fleur s = em.find(Fleur.class, id);
		em.close();
		return s;
	}
	
	@Override
	public  List<Fleur> findAll(){
		String req = "select Object(s) from Fleur s";
		EntityManager em = DAOUtil.getEntityManager();
		List<Fleur> liste = em
				.createQuery(req, Fleur.class)
				.getResultList();
		em.close();
		
		return liste;
	}

}
