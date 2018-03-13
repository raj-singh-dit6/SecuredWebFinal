package com.securedweb.service.master;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.securedweb.model.master.Tenant;
import com.securedweb.repository.master.TenantRepository;

@Service("tenantService")
@Transactional("masterTransactionManager")
public class TenantServiceImp implements TenantService{
	

    @Autowired
    private TenantRepository tenantRepository;

	@Override
	public List<Tenant> findAll() {
		return (List<Tenant>) tenantRepository.findAll();
	}

	@Override
	public Tenant findById(String id) {
		tenantRepository.findById(Integer.parseInt(id));
		return tenantRepository.findById(Integer.parseInt(id)).get();
	}
	
    
    

}
