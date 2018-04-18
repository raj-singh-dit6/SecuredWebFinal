package com.securedweb.service.master;

import java.util.List;

import com.securedweb.dto.master.TenantDTO;
import com.securedweb.model.master.Tenant;

public interface TenantService {
	Tenant findById(String id);
	List<TenantDTO> getAllTenants();

}
