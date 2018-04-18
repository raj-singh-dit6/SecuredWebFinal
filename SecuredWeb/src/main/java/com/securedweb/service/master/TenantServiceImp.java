package com.securedweb.service.master;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.securedweb.dto.master.TenantDTO;
import com.securedweb.model.master.Tenant;
import com.securedweb.repository.master.TenantRepository;

@Service("tenantService")
@Transactional("masterTransactionManager")
public class TenantServiceImp implements TenantService{
	

    @Autowired
    private TenantRepository tenantRepository;

	@Override
	public List<TenantDTO> getAllTenants() {
		
		List<Tenant> tenantList=(List<Tenant>) tenantRepository.findAll();
		List<TenantDTO> tenantDTOList= new ArrayList<TenantDTO>();
		for(Tenant tenant:tenantList)
		{
			TenantDTO tenantDTO = new TenantDTO();
			tenantDTO.setTenantId(tenant.getTenantId());
			tenantDTO.setTenantName(tenant.getTenantName());
			tenantDTOList.add(tenantDTO);
		}
	return tenantDTOList;
	}

	@Override
	public Tenant findById(String id) {
		tenantRepository.findById(Integer.parseInt(id));
		return tenantRepository.findById(Integer.parseInt(id)).get();
	}
	
    
    

}
