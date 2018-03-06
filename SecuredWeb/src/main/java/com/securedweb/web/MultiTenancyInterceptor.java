package com.securedweb.web;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.securedweb.util.CurrentTenantIdentifierResolverImpl;

public class MultiTenancyInterceptor extends HandlerInterceptorAdapter {

 private static final Logger LOG = LoggerFactory.getLogger(MultiTenancyInterceptor.class);
	
 @Override
 public boolean preHandle(HttpServletRequest req, HttpServletResponse res, Object handler)
   throws Exception {
  Map<String, Object> pathVars = (Map<String, Object>) req.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
  
  if (pathVars.containsKey("tenantid")) {
   req.setAttribute("CURRENT_TENANT_IDENTIFIER", pathVars.get("tenantid"));
  }
  return true;
 }
}
