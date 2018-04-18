package com.securedweb.service.tenant;

import java.util.List;

import com.securedweb.model.tenant.Role;

public interface RoleService {
	
	List<Role> findAll();
	Role findByType(String type) ;
	Role findById(int id) ;

}
