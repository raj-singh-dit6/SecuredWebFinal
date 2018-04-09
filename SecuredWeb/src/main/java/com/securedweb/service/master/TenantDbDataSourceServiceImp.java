package com.securedweb.service.master;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.securedweb.model.master.TenantDbDataSource;
import com.securedweb.repository.master.TenantDbDataSourceRepository;

@Service("tenantDbDataSource")
@Transactional("masterTransactionManager")
public class TenantDbDataSourceServiceImp implements TenantDbDataSourceService {
	
	@Autowired
	private TenantDbDataSourceRepository  tenantDbDataSourceRepository;
	
	@Override
	public TenantDbDataSource findById(int id) {
		return tenantDbDataSourceRepository.findById(id).get();
	}

}
