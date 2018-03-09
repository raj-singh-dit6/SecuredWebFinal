package com.securedweb.repository.master;

import org.springframework.data.repository.CrudRepository;

import com.securedweb.model.master.Tenant;

public interface TenantRepository extends CrudRepository<Tenant,Integer>{

}
