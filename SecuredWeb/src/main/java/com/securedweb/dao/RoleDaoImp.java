package com.securedweb.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.securedweb.model.Role;

@Repository("roleDao")
public class RoleDaoImp extends AbstractDao<Integer,Role> implements RoleDao{
	
	
	@Override
	@SuppressWarnings("unchecked")
	public List<Role> findAll() {
		Criteria criteria= createEntityCriteria();
		criteria.addOrder(Order.asc("type"));
		return (List<Role>)criteria.list();
	}

	@Override
	public Role findByType(String type) {
		Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.eq("type", type));
		return (Role)criteria.uniqueResult();
	}

	@Override
	public Role findById(int id) {
		return getByKey(id);
	}

}
