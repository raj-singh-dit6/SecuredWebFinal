package com.securedweb.service;

import java.util.List;

import com.securedweb.model.Role;

public interface RoleService {
	
	List<Role> findAll();
	Role findByType(String type) ;
	Role findById(int id) ;

}
