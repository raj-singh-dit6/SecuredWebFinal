package com.securedweb.service.tenant;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.securedweb.dto.tenant.RoleDTO;
import com.securedweb.model.tenant.Role;
import com.securedweb.repository.tenant.RoleRepository;

@Service("roleService")
@Transactional("tenantTransactionManager")
public class RoleServiceImp implements RoleService{
	
	private static final Logger LOG = LoggerFactory.getLogger(RoleServiceImp.class);

	@Autowired
	RoleRepository roleRepository;
	
	@Override
	public List<RoleDTO> getAllRoles() {
		List<Role> roleList=(List<Role>)roleRepository.findAll();
		List<RoleDTO> roleDTOList = new ArrayList<RoleDTO>();
		for(Role role:roleList)
		{
			RoleDTO roleDTO= new RoleDTO();
			roleDTO.setId(role.getId());
			roleDTO.setType(role.getType());
			roleDTOList.add(roleDTO);
		}
		return roleDTOList;
	}
	
}
