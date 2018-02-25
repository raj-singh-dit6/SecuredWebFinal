package com.securedweb.dao;

import java.util.List;

import com.securedweb.model.UserRole;

public interface UserRoleDao {
	
	 List<UserRole> findAll();
     
	 UserRole findByType(String type);
	     
	 UserRole findById(int id);

}
