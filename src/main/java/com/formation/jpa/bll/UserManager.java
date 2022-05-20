package com.formation.jpa.bll;

import java.util.List;

import com.formation.jpa.bean.User;
import com.formation.jpa.dal.UserDao;
import com.formation.jpa.exception.BeanException;
import com.formation.jpa.util.DaoFactory;
import com.formation.jpa.util.dto.UserDto;

public class UserManager {

	private UserDao dao;

	public UserManager() {
		dao = DaoFactory.getUserDao();
	}

	public List<User> listeUsers() {
		return dao.findAll();
	}

	public User trouverUser(int id) {
		return dao.findById(id);
	}

	public void ajoutUser(User s) throws Exception {

		if (s.getUsername() != null && !s.getUsername().trim().equals(""))
			dao.add(s);
		else
			throw new BeanException("Le User doit posséder un libellé");
	}

	public void modifierUser(User u) throws Exception {
		if (u.getUsername() != null && !u.getUsername().trim().equals(""))
			dao.update(u);
		else
			throw new BeanException("Le User doit posséder un libellé");
	}

	public void supprimerUser(User u) throws Exception {
		dao.delete(u);
	}

	public void supprimerUser(int id) throws Exception {
		System.out.println(id);
		User s = dao.findById(id);
		System.out.println(s.toString());
		dao.delete(s);
	}

	public List<User> trier(String type) {
		List<User> liste = null;
		switch (type) {
		default:
			liste = dao.findAll();
		}
		return liste;
	}

	public boolean connecterUser(UserDto user) throws Exception {

		try {
			
			return dao.connexion(user);
		} catch(Exception e) {
			
			throw new BeanException("Identifiants incorrect");
		}

	}

	public User trouverUserParNom(String username) {
		return dao.findByName(username);
		
		
	}

}
