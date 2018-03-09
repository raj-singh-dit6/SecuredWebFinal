package com.securedweb.repository.tenant;

import org.springframework.data.repository.CrudRepository;

import com.securedweb.model.tenant.Role;

public interface RoleRepository extends CrudRepository<Role,Integer>{
	
	Role findByType(String type);

}
