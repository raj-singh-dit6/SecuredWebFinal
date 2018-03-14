package com.securedweb.security;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.securedweb.model.tenant.Role;
import com.securedweb.model.tenant.User;
import com.securedweb.repository.tenant.UserRepository;


@Service("customUserDetailsService")
public class CustomUserDetailsService implements UserDetailsService{
	
	static final Logger logger = LoggerFactory.getLogger(CustomUserDetailsService.class);
	
	@Autowired
	private UserRepository userRepository;

	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String ssoId)
	{
		User user = userRepository.findBySsoId(ssoId);
		logger.info("User { } ",user);
		if(user==null) {
			logger.info("user not found");
			throw new UsernameNotFoundException("Username not found");
		}
		
		return new org.springframework.security.core.userdetails.User(user.getSsoId(), user.getPassword(), 
                true, true, true, true, getGrantedAuthorities(user));
	}
	
	private List<GrantedAuthority> getGrantedAuthorities(User user){
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        for(Role role : user.getUserRoles()){
            logger.info("Role : {}", role);
            authorities.add(new SimpleGrantedAuthority("ROLE_"+role.getType()));
        }
        logger.info("authorities : {}", authorities);
        return authorities;
    }
}
