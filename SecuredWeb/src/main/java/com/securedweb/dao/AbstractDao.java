package com.securedweb.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;

import javax.persistence.EntityManagerFactory;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

public abstract class AbstractDao<PK extends Serializable,T > {

	private final Class<T> persistentClass;
	
	
	
	@SuppressWarnings("unchecked")
    public AbstractDao(){
        this.persistentClass =(Class<T>) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[1];
    
	}
	
	/*Using setter also we can set the Class of the Entity*/
	/*public void setClazz( final Class< T > clazzToSet ){
	      persistentClass = clazzToSet;
	   }*/
	@Autowired
	@Qualifier("tenantEntityManager")
	EntityManagerFactory masterEntityManager;
	 
    protected Session getSession()
    {
    	System.out.println("Getting session..........");
    	System.out.println(masterEntityManager.toString());
    	SessionFactory sessionFactory = masterEntityManager.unwrap(SessionFactory.class);
    	System.out.println("............... session..........");
        return sessionFactory.getCurrentSession();
    }
 
    @SuppressWarnings("unchecked")
    public T getByKey(PK key) {
        return (T) getSession().get(persistentClass, key);
    }
 
    public void persist(T entity) {
        getSession().persist(entity);
    }
 
    public void update(T entity) {
        getSession().update(entity);
    }
 
    public void delete(T entity) {
        getSession().delete(entity);
    }
     
    protected Criteria createEntityCriteria(){
        return getSession().createCriteria(persistentClass);
    }
	
}
