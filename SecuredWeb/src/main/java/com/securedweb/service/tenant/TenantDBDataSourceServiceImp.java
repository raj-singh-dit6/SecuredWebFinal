package com.securedweb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.securedweb.model.TenantDBDataSource;
import com.securedweb.repository.TenantDBDataSourceRepository;

@Service("tenantDBDataSource")
@Transactional
public class TenantDBDataSourceServiceImp implements TenantDBDataSourceService {

	@Autowired
	private TenantDBDataSourceRepository  tenantDBDataSourceRepository;
	
	/*@Autowired
	TenantDBDataSourceServiceImp(@Lazy TenantDBDataSourceRepository  tenantDBDataSourceRepository){
		this.tenantDBDataSourceRepository=tenantDBDataSourceRepository;
	}*/
	
	
	@Override
	public TenantDBDataSource findById(int id) {
		return tenantDBDataSourceRepository.findById(id).get();
	}

}
