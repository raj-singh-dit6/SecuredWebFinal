package com.securedweb.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.securedweb.model.User;


@Repository("userDao")
public class UserDaoImp extends AbstractDao<Integer,User>  implements UserDao{
	
	static final Logger logger = LoggerFactory.getLogger(UserDaoImp.class);
    
	@Override
	public User findById(int id) {
        User user = getByKey(id);
        /*if(user!=null){
            Hibernate.initialize(user.getUserProfiles());
        }*/
        return user;
    }
	
	@Override
    public User findBySSO(String sso) {
        logger.info("SSO : {}", sso);
        Criteria crit = createEntityCriteria();
        crit.add(Restrictions.eq("ssoId", sso));
        User user = (User)crit.uniqueResult();
        if(user!=null){
            Hibernate.initialize(user.getUserRoles());
        }
        return user;
    }
 
	@Override
	@SuppressWarnings("unchecked")
    public List<User> findAllUser() {
        Criteria criteria = createEntityCriteria().addOrder(Order.asc("firstName"));
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);//To avoid duplicates.
        List<User> users = (List<User>) criteria.list();
         
        // No need to fetch userProfiles since we are not showing them on list page. Let them lazy load. 
        // Uncomment below lines for eagerly fetching of userProfiles if you want.
        /*
        for(User user : users){
            Hibernate.initialize(user.getUserProfiles());
        }*/
        return users;
    }
 
	@Override
    public void saveUser(User user) {
        persist(user);
    }
 
	@Override
    public void deleteUserBySSO(String sso) {
        Criteria crit = createEntityCriteria();
        crit.add(Restrictions.eq("ssoId", sso));
        User user = (User)crit.uniqueResult();
        delete(user);
    }
	
}
