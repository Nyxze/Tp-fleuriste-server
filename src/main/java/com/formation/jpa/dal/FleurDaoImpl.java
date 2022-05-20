package com.formation.jpa.dal;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import com.formation.jpa.bean.Bouquet;
import com.formation.jpa.bean.Fleur;
import com.formation.jpa.util.DAOUtil;

public class FleurDaoImpl implements FleurDao {

	public void add(Fleur f) throws Exception {

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
		} finally {
			em.close();
		}
	}

	public void delete(Fleur f) throws Exception {
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
		} finally {
			em.close();
		}
	}

	public void update(Fleur f) throws Exception {
		EntityManager em = DAOUtil.getEntityManager();
		EntityTransaction et = em.getTransaction();
		et.begin();
		try {
			em.merge(f);
			et.commit();
		} catch (Exception e) {
			et.rollback();
			throw e;
		} finally {
			em.close();
		}
	}

	public void update(List<Fleur> listeFleur) throws Exception {
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
		} finally {
			em.close();
		}
	}

	public Fleur findById(int id) {
		EntityManager em = DAOUtil.getEntityManager();
		Fleur s = em.find(Fleur.class, id);
		em.close();
		return s;
	}

	@Override
	public List<Fleur> findAll() {
		String req = "select Object(s) from Fleur s";
		EntityManager em = DAOUtil.getEntityManager();
		List<Fleur> liste = em.createQuery(req, Fleur.class).getResultList();
		em.close();

		return liste;
	}

	public List<Fleur> findByQuerryParams(String search, double min, double max) {
		EntityManager em = DAOUtil.getEntityManager();
		Query querry = em.createQuery(
				"SELECT Object(f) from Fleur f WHERE f.name like :search and f.price >= :min and f.price<=:max",
				Fleur.class);
		querry.setParameter("search", "%" + search + "%");
		querry.setParameter("min", min);
		querry.setParameter("max", max);

		List<Fleur> liste = null;

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
	public List<Fleur> findByQuerryParams(String search, double min, double max, int season) {

		EntityManager em = DAOUtil.getEntityManager();
		Query querry = em.createQuery("SELECT Object(f) from Fleur f " + "WHERE f.name like :search "
				+ "and f.price >= :min " + "and f.price<=:max " + "and f.season.id=:season ", Bouquet.class);
		querry.setParameter("search", "%" + search + "%");
		querry.setParameter("min", min);
		querry.setParameter("max", max);
		querry.setParameter("season", season);

		List<Fleur> liste = null;

		try {
			liste = querry.getResultList();
			System.out.println("Querry réussi");

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("error lors du findByQuerryParams avec sesason");
		}

		return liste;

	}

	@Override
	public double getPriceMax() {

		double max = 0d;
		EntityManager em = DAOUtil.getEntityManager();
		Query querry = em.createQuery("SELECT MAX(f.price) from Fleur f");
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
		Query querry = em.createQuery("SELECT MIN(f.price) from Fleur f");
		try {
			max = (double) querry.getSingleResult();

		} catch (Exception e) {

			System.out.println("error lors du PriceMin");
		}
		em.close();

		return max;
	}
}
