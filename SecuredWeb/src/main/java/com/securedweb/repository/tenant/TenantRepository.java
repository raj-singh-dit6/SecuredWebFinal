package com.securedweb.repository;

import org.springframework.data.repository.CrudRepository;

import com.securedweb.model.Tenant;

public interface TenantRepository extends CrudRepository<Tenant,Integer>{

}
