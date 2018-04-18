package com.securedweb.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;


@Component("multiTenancyInterceptor")
public class MultiTenancyInterceptor extends HandlerInterceptorAdapter {
 
 private static final Logger LOG = LoggerFactory.getLogger(MultiTenancyInterceptor.class);
	
 @Override
 public boolean preHandle(HttpServletRequest req, HttpServletResponse res, Object handler)
   throws Exception {
	  String attrValue=(String)req.getSession().getAttribute("tenantId");
	  if (attrValue!=null && !attrValue.equals("")) {
		  System.err.println("Tenant from Session : "+(String)req.getSession().getAttribute("tenantId"));
		  System.err.println("Tenant from URL : "+attrValue);
		  if(!attrValue.equals((String)req.getSession().getAttribute("tenantId"))) {  
			req.setAttribute("CURRENT_TENANT_IDENTIFIER", (String)req.getSession().getAttribute("tenantId"));
			TenantHolder.setTenantId((String)req.getSession().getAttribute("tenantId"));
	  	}else {
		  req.setAttribute("CURRENT_TENANT_IDENTIFIER", attrValue);
		   TenantHolder.setTenantId(attrValue);
	  	}	
	  }else
	  {
		  TenantHolder.setTenantId("1");
	  }
	  return true;
 }
}
