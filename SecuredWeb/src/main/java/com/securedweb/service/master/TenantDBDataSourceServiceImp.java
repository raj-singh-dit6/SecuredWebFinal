package com.securedweb.service.master;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.securedweb.model.master.TenantDBDataSource;
import com.securedweb.repository.master.TenantDBDataSourceRepository;

@Service("tenantDBDataSource")
@Transactional("masterTransactionManager")
public class TenantDBDataSourceServiceImp implements TenantDBDataSourceService {
	
	@Autowired
	private TenantDBDataSourceRepository  tenantDBDataSourceRepository;
	
	@Override
	public TenantDBDataSource findById(int id) {
		return tenantDBDataSourceRepository.findById(id).get();
	}

}
