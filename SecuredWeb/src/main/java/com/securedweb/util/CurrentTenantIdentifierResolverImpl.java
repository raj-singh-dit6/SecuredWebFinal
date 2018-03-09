package com.securedweb.util;

import org.hibernate.context.spi.CurrentTenantIdentifierResolver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

@Component("currentTenantIdentifierResolverImpl")
public class CurrentTenantIdentifierResolverImpl implements CurrentTenantIdentifierResolver {

	private static final Logger LOG = LoggerFactory.getLogger(CurrentTenantIdentifierResolverImpl.class);
	
	private static final String DEFAULT_TENANT_ID = "1";
	
	@Override
	public String resolveCurrentTenantIdentifier() {
		
		/*ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		String tenantId = attr!=null?attr.getRequest().getParameter("tenantId"):"";
        
        if (tenantId != "") {
            TenantHolder.setTenantId(tenantId);
        	return tenantId;
        }
        return DEFAULT_TENANT_ID;*/
        
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();				
		if(authentication != null) {
			//System.out.println(((User)authentication.getPrincipal()).getTenantId());
			RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
			if (requestAttributes != null) {
					String identifier = (String) requestAttributes.getAttribute("CURRENT_TENANT_IDENTIFIER",RequestAttributes.SCOPE_REQUEST);
					System.err.println("CurentTenantIdentifier : "+TenantHolder.getTenantId());
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
