package com.securedweb.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.securedweb.dao.UserRoleDao;
import com.securedweb.model.UserRole;

@Service("userRoleService")
@Transactional
public class UserRoleServiceImp implements UserRoleService{

	@Autowired
	UserRoleDao userRoleDao;
	
	@Override
	public List<UserRole> findAll() {
		return userRoleDao.findAll();
	}

	@Override
	public UserRole findByType(String type) {
		return userRoleDao.findByType(type);
	}

	@Override
	public UserRole findById(int id) {
		return userRoleDao.findById(id);
	}
	

}
