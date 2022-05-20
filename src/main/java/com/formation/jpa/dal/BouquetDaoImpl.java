package com.formation.jpa.dal;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import com.formation.jpa.bean.Bouquet;
import com.formation.jpa.util.DAOUtil;

public class BouquetDaoImpl implements BouquetDao {

	public void add(Bouquet s) throws Exception {
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

	public void delete(Bouquet s) throws Exception {
		EntityManager em = DAOUtil.getEntityManager();
		EntityTransaction et = em.getTransaction();
		Bouquet Bouquet = em.find(Bouquet.class, s.getId());
		et.begin();
		try {
			em.remove(Bouquet);
			et.commit();
		} catch (Exception e) {
			e.printStackTrace();
			et.rollback();
			throw e;
		} finally {
			em.close();
		}
	}

	public void update(Bouquet s) throws Exception {
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

	public void update(List<Bouquet> listeBouquet) throws Exception {
		EntityManager em = DAOUtil.getEntityManager();
		EntityTransaction et = em.getTransaction();
		et.begin();
		try {
			for (Bouquet t : listeBouquet) {
				em.merge(t);
			}

			et.commit();
		} catch (Exception e) {
			et.rollback();
			throw e;
		} finally {
			em.close();
		}
	}

	public Bouquet findById(int id) {
		EntityManager em = DAOUtil.getEntityManager();
		Bouquet s = em.find(Bouquet.class, id);
		em.close();
		return s;
	}

	@Override
	public List<Bouquet> findAll() {
		String req = "select Object(s) from Bouquet s";
		EntityManager em = DAOUtil.getEntityManager();
		List<Bouquet> liste = em.createQuery(req, Bouquet.class).getResultList();
		em.close();

		return liste;
	}

	@Override
	public double getPriceMax() {
		double max = 0d;
		EntityManager em = DAOUtil.getEntityManager();
		Query querry = em.createQuery("SELECT MAX(b.price) from Bouquet b");
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
		Query querry = em.createQuery("SELECT MIN(b.price) from Bouquet b");
		try {
			max = (double) querry.getSingleResult();

		} catch (Exception e) {
			System.out.println("error lors du getPriceMax");
		}
		em.close();

		return max;
	}

	public List<Bouquet> findByQuerryParams(String search, double min, double max) {
		EntityManager em = DAOUtil.getEntityManager();
		Query querry = em.createQuery(
				"SELECT Object(b) from Bouquet b WHERE b.name like :search and b.price >= :min and b.price<=:max",Bouquet.class);
		querry.setParameter("search", "%" + search + "%");
		querry.setParameter("min", min);
		querry.setParameter("max", max);

		List<Bouquet> liste = null;

		try {
			liste = querry.getResultList();
			System.out.println("Querry réussi");

		} catch (Exception e) {
			e.printStackTrace();
				System.out.println("error lors du findByQuerryParams default");
			}

		return liste;

	}

	@Override
	public List<Bouquet> findByQuerryParams(String search, double min, double max, int season, int style) {
		EntityManager em = DAOUtil.getEntityManager();
		Query querry = em.createQuery(
				"SELECT Object(b) from Bouquet b "
				+ "WHERE b.name like :search "
				+ "and b.price >= :min "
				+ "and b.price<=:max "
				+ "and b.style.id=:style "
				+ "and b.season.id=:season",Bouquet.class);
		querry.setParameter("search", "%" + search + "%");
		querry.setParameter("min", min);
		querry.setParameter("max", max);
		querry.setParameter("season", season);
		querry.setParameter("style", style);

		List<Bouquet> liste = null;

		try {
			liste = querry.getResultList();
			System.out.println("Querry réussi");
		
		
		} catch (Exception e) {
			e.printStackTrace();
				System.out.println("error lors du findByQuerryParams avec season et style");
			}

		return liste;

	}

	@Override
	public List<Bouquet> findByQuerryParams(String search, double min, double max, int paramsId,String params) {
		
		EntityManager em = DAOUtil.getEntityManager();
		Query querry = em.createQuery(
				"SELECT Object(b) from Bouquet b "
				+ "WHERE b.name like :search "
				+ "and b.price >= :min "
				+ "and b.price<=:max "
				+ "and b."+params+".id=:params "
				,Bouquet.class);
		querry.setParameter("search", "%" + search + "%");
		querry.setParameter("min", min);
		querry.setParameter("max", max);
		querry.setParameter("params", paramsId);

		List<Bouquet> liste = null;

		try {
			liste = querry.getResultList();
			System.out.println("Querry réussi");
		
		} catch (Exception e) {
			e.printStackTrace();
				System.out.println("error lors du findByQuerryParams withs params and paramId");
			}

		return liste;

	};

}
