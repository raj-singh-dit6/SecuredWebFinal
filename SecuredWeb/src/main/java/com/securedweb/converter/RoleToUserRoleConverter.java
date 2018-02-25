package com.securedweb.converter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.securedweb.model.UserRole;
import com.securedweb.service.UserRoleService;

/**
 * A converter class used in views to map id's to actual userRole objects.
 */
@Component
public class RoleToUserRoleConverter implements Converter<Object, UserRole>{
 
    static final Logger logger = LoggerFactory.getLogger(RoleToUserRoleConverter.class);
     
    @Autowired
    UserRoleService userRoleService;
 
    /**
     * Gets UserRole by Id
     * @see org.springframework.core.convert.converter.Converter#convert(java.lang.Object)
     */
    public UserRole convert(Object element) {
        Integer id = Integer.parseInt((String)element);
        UserRole role= userRoleService.findById(id);
        logger.info("Role : {}",role);
        return role;
    }
     
}