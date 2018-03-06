package com.securedweb.dao;

import java.util.List;

import com.securedweb.model.Role;

public interface RoleDao {
	
	 List<Role> findAll();
     
	 Role findByType(String type);
	     
	 Role findById(int id);

}
