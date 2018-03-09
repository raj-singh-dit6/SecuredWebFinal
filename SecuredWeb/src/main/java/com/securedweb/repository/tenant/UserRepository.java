package com.securedweb.repository.tenant;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.securedweb.model.tenant.User;

public interface UserRepository extends CrudRepository<User, Integer>{
	
	List<User> findByTenantId(String tenandId);
	
	User findBySsoId(String SSO_ID);
	
    void deleteBySsoId(String SSO_ID);
}