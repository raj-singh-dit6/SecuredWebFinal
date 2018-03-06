package com.securedweb.util;

import org.hibernate.context.spi.CurrentTenantIdentifierResolver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import com.securedweb.model.User;

@Component("currentTenantIdentifierResolverImpl")
public class CurrentTenantIdentifierResolverImpl implements CurrentTenantIdentifierResolver {

	private static final Logger LOG = LoggerFactory.getLogger(CurrentTenantIdentifierResolverImpl.class);
	
	private static final String DEFAULT_TENANT_ID = "1";
	
	@Override
	public String resolveCurrentTenantIdentifier() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();				
		if(authentication != null && authentication.getPrincipal() instanceof User) {
			System.out.println(((User)authentication.getPrincipal()).getTenandId());
			RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
			if (requestAttributes != null) {
					String identifier = (String) requestAttributes.getAttribute("CURRENT_TENANT_IDENTIFIER",RequestAttributes.SCOPE_REQUEST);
					if (identifier != null) {
						return identifier;
					}
		  }
		} 
		return DEFAULT_TENANT_ID;
	}

	@Override
	public boolean validateExistingCurrentSessions() {
		return true;
	}
}
