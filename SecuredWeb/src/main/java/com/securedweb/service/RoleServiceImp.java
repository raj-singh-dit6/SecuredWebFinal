package com.securedweb.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.securedweb.model.Role;
import com.securedweb.repository.RoleRepository;

@Service("roleService")
@Transactional
public class RoleServiceImp implements RoleService{

	@Autowired
	RoleRepository roleRepository;
	
	@Override
	public List<Role> findAll() {
		return (List<Role>)roleRepository.findAll();
	}

	@Override
	public Role findByType(String type) {
		return roleRepository.findByType(type);
	}

	@Override
	public Role findById(int id) {
		return roleRepository.findById(id).get();
	}
}
