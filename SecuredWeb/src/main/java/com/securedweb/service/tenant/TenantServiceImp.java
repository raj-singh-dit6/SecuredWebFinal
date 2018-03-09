package com.securedweb.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.securedweb.model.Tenant;
import com.securedweb.repository.TenantRepository;

@Service("tenantService")
@Transactional
public class TenantServiceImp implements TenantService{
	

    @Autowired
    TenantRepository tenantRepository;
 
    /*@Autowired
    public TenantServiceImp(@Lazy TenantRepository tenantRepostory) {
        this.tenantRepository = tenantRepostory;
    }*/

	@Override
	public List<Tenant> findAll() {
		return (List<Tenant>) tenantRepository.findAll();
	}
	
    
    

}
