package com.formation.jpa.dal;

import java.util.List;

import com.formation.jpa.bean.Bouquet;
import com.formation.jpa.bean.User;
import com.formation.jpa.util.dto.ProductData;
import com.formation.jpa.util.dto.UserDto;

public interface UserDao {
	public void add( User u) throws Exception;
	public void delete( User u) throws Exception;
	public  void update(User u) throws Exception;
	public User findById(int id);
	public  List<User> findAll();
	public boolean connexion(UserDto userDto) throws Exception;
	public User findByName(String username);




}
