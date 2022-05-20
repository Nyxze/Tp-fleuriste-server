package com.formation.jpa.dal;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import com.formation.jpa.bean.Bouquet;
import com.formation.jpa.bean.Fleur;
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
		finally {
			em.close();
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
		finally {
			em.close();
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
		finally {
			em.close();
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
		finally {
			em.close();
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

	public List<Plante> findByQuerryParams(String search, double min, double max) {
		EntityManager em = DAOUtil.getEntityManager();
		Query querry = em.createQuery(
				"SELECT Object(p) from Plante p WHERE p.name like :search and p.price >= :min and p.price<=:max",
				Plante.class);
		querry.setParameter("search", "%" + search + "%");
		querry.setParameter("min", min);
		querry.setParameter("max", max);

		List<Plante> liste = null;

		try {
			liste = querry.getResultList();
			System.out.println("Querry réussi");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("error lors du findByQuerryParams");
		}

		return liste;

	}

	

	@Override
	public double getPriceMax() {

		double max = 0d;
		EntityManager em = DAOUtil.getEntityManager();
		Query querry = em.createQuery("SELECT MAX(p.price) from Plante p");
		try {
			max = (double) querry.getSingleResult();

		} catch (Exception e) {

			System.out.println("error lors du getPriceMax");
		}
		em.close();

		return max;
	}

	@Override
	public double getPriceMin() {
		double max = 0d;
		EntityManager em = DAOUtil.getEntityManager();
		Query querry = em.createQuery("SELECT MIN(p.price) from Plante p");
		try {
			max = (double) querry.getSingleResult();

		} catch (Exception e) {

			System.out.println("error lors du getPriceMax");
		}
		em.close();

		return max;
	}
}
