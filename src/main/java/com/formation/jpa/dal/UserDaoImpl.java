package com.formation.jpa.dal;

import java.sql.SQLException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import com.formation.jpa.bean.User;
import com.formation.jpa.util.DAOUtil;
import com.formation.jpa.util.dto.UserDto;

public class UserDaoImpl implements UserDao {

	public void add(User u) throws Exception {
		EntityManager em = DAOUtil.getEntityManager();
		EntityTransaction et = em.getTransaction();
		et.begin();
		try {
			em.persist(u);
			et.commit();
		} catch (Exception e) {
			et.rollback();
			throw e;
		} finally {
			em.close();
		}
	}

	public void delete(User s) throws Exception {
		EntityManager em = DAOUtil.getEntityManager();
		EntityTransaction et = em.getTransaction();
		User User = em.find(User.class, s.getId());
		et.begin();
		try {
			em.remove(User);
			et.commit();
		} catch (Exception e) {
			e.printStackTrace();
			et.rollback();
			throw e;
		} finally {
			em.close();
		}
	}

	public void update(User u) throws Exception {
		EntityManager em = DAOUtil.getEntityManager();
		EntityTransaction et = em.getTransaction();
		et.begin();
		try {
			em.merge(u);
			et.commit();
		} catch (Exception e) {
			et.rollback();
			throw e;
		} finally {
			em.close();
		}
	}

	public void update(List<User> listeUser) throws Exception {
		EntityManager em = DAOUtil.getEntityManager();
		EntityTransaction et = em.getTransaction();
		et.begin();
		try {
			for (User u : listeUser) {
				em.merge(u);
			}

			et.commit();
		} catch (Exception e) {
			et.rollback();
			throw e;
		} finally {
			em.close();
		}
	}

	public User findById(int id) {
		EntityManager em = DAOUtil.getEntityManager();
		User s = em.find(User.class, id);
		em.close();
		return s;
	}

	@Override
	public List<User> findAll() {
		String req = "select Object(u) from User u";
		EntityManager em = DAOUtil.getEntityManager();
		List<User> liste = em.createQuery(req, User.class).getResultList();
		em.close();

		return liste;
	}

	public boolean connexion(UserDto user)  throws Exception{
		EntityManager em = DAOUtil.getEntityManager();
		String req = "SELECT Object(u) from User u WHERE u.username=:username and u.pswd= :pswd";
		TypedQuery<User> querry = em.createQuery(req, User.class).setParameter("username", user.getUsername())
				.setParameter("pswd", user.getPswd());
		boolean isValid = false;
		try {
			if (querry.getSingleResult() != null)
				isValid = true;

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("utilisateur introuvable");
			isValid = false;
			throw new SQLException();
		}
		em.close();
		return isValid;

	}

	@Override
	public User findByName(String username) {
		EntityManager em = DAOUtil.getEntityManager();
		String req = "SELECT Object(u) from User u WHERE u.username=:username";
		TypedQuery<User> querry = em.createQuery(req, User.class).setParameter("username", username);
		User u = new User();
		try {
			u = querry.getSingleResult();

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("utilisateur introuvable");

		}
		em.close();
		return u;

	}

}
