package com.securedweb.repository.tenant;

import org.springframework.data.repository.CrudRepository;

import com.securedweb.model.tenant.PersistentLogin;

public interface PersistentLoginRespository extends CrudRepository<PersistentLogin,String> {

	PersistentLogin findByUsername(String username);
}
