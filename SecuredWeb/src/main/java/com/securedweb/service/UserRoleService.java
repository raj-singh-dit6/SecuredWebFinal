package com.securedweb.service;

import java.util.List;

import com.securedweb.model.UserRole;

public interface UserRoleService {

	 List<UserRole> findAll();
    
	 UserRole findByType(String type);
	     
	 UserRole findById(int id);
	
}
