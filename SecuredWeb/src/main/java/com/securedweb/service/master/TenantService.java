package com.securedweb.service.master;

import java.util.List;

import com.securedweb.model.master.Tenant;

public interface TenantService {
	List<Tenant> findAll();
	Tenant findById(String id);

}
