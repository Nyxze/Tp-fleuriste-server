package com.formation.jpa.dal;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

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

}
