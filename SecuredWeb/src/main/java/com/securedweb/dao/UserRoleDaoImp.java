package com.securedweb.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.securedweb.model.UserRole;

@Repository("userRoleDao")
public class UserRoleDaoImp extends AbstractDao<Integer,UserRole> implements UserRoleDao{
	
	
	@Override
	@SuppressWarnings("unchecked")
	public List<UserRole> findAll() {
		Criteria criteria= createEntityCriteria();
		criteria.addOrder(Order.asc("type"));
		return (List<UserRole>)criteria.list();
	}

	@Override
	public UserRole findByType(String type) {
		Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.eq("type", type));
		return (UserRole)criteria.uniqueResult();
	}

	@Override
	public UserRole findById(int id) {
		return getByKey(id);
	}

}
