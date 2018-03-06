package com.securedweb.repository;

import org.springframework.data.repository.CrudRepository;

import com.securedweb.model.Role;

public interface RoleRepository extends CrudRepository<Role,Integer>{
	
	Role findByType(String type);

}
