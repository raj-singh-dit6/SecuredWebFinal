package com.securedweb.converter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.securedweb.model.tenant.Role;
import com.securedweb.repository.tenant.RoleRepository;

/**
 * A converter class used in views to map id's to actual userRole objects.
 */
@Component
public class RoleToUserRoleConverter implements Converter<Object, Role>{
 
    static final Logger logger = LoggerFactory.getLogger(RoleToUserRoleConverter.class);
     
    @Autowired
    RoleRepository roleRepository;
 
    /**
     * Gets UserRole by Id
     * @see org.springframework.core.convert.converter.Converter#convert(java.lang.Object)
     */
    public Role convert(Object element) {
        Integer id = Integer.parseInt((String)element);
        Role role= roleRepository.findById(id).get();
        logger.info("Role : {}",role);
        return role;
    }
}