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
	  String attrValue=(String)req.getSession().getAttribute("tenandtId");
	  if (attrValue!=null && attrValue!="") {
	   req.setAttribute("CURRENT_TENANT_IDENTIFIER", attrValue);
	   TenantHolder.setTenantId(attrValue);
	   System.err.println("CurentTenantIdentifier : "+attrValue);
	  }
	  else
	  {
		  TenantHolder.setTenantId("1");
	  }
	  
	  return true;
 }
}
