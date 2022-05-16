package com.formation.jpa.dal;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import com.formation.jpa.bean.Plante;
import com.formation.jpa.util.DAOUtil;



public class PlanteDaoImpl implements PlanteDao{

	
	public void add(Plante p) throws Exception{
		EntityManager em = DAOUtil.getEntityManager();
		EntityTransaction et = em.getTransaction();
		et.begin();
		try {
			em.persist(p);
			et.commit();
		} catch (Exception e) {
			et.rollback();
			throw e;
		}
	}

	public void delete(Plante s) throws Exception{
		EntityManager em = DAOUtil.getEntityManager();
		EntityTransaction et = em.getTransaction();
		Plante Plante = em.find(Plante.class, s.getId());
		et.begin();
		try {
			em.remove(Plante);
			et.commit();
		} catch (Exception e) {
			e.printStackTrace();
			et.rollback();
			throw e;
		}
	}

	public  void update(Plante s) throws Exception{
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
	}
	
	public void update(List<Plante> listePlante) throws Exception{
		EntityManager em = DAOUtil.getEntityManager();
		EntityTransaction et = em.getTransaction();
		et.begin();
		try {
			for (Plante t : listePlante) {
				em.merge(t);
			}
			
			et.commit();
		} catch (Exception e) {
			et.rollback();
			throw e;
		}
	}
	
	public Plante findById(int id){
		EntityManager em = DAOUtil.getEntityManager();
		Plante s = em.find(Plante.class, id);
		em.close();
		return s;
	}
	
	@Override
	public  List<Plante> findAll(){
		String req = "select Object(s) from Plante s";
		EntityManager em = DAOUtil.getEntityManager();
		List<Plante> liste = em
				.createQuery(req, Plante.class)
				.getResultList();
		em.close();
		
		return liste;
	}

}
