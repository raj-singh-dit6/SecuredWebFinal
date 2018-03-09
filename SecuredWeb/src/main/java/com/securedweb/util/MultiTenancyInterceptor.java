package com.securedweb.util;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;


@Component("multiTenancyInterceptor")
public class MultiTenancyInterceptor extends HandlerInterceptorAdapter {
 
 /*@Autowired
 TenantRepository tenantRepository;*/
 
 private static final Logger LOG = LoggerFactory.getLogger(MultiTenancyInterceptor.class);
	
 @Override
 public boolean preHandle(HttpServletRequest req, HttpServletResponse res, Object handler)
   throws Exception {
	
	 /*  Map<String, String> map = new HashMap<String, String>();
		
	  Enumeration headerNames = req.getHeaderNames();
	  while (headerNames.hasMoreElements()) {
	
		String key = (String) headerNames.nextElement();
		if(key.equals("tenantId")){
			String value = req.getHeader(key);
			map.put(key, value);
		}
	  }
	
     if (map.containsKey("tenantId")) {
        String tenantId = map.get("tenantId").toString();
        //Optional<Tenant> thisTenant =  tenantRepository.findById(Integer.parseInt(tenantId));
        //if (thisTenant.isPresent()) {
           req.setAttribute("Current_Tenant", tenantId);
           return true;
        } else {
           //res.sendRedirect(req.getContextPath() + "/signUp");
           return false;
        }
     } else {
        return true;
     }
  */
 
  ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
  Map<String, Object> pathVars = (Map<String, Object>) req.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
  String attrValue=req.getParameter("tenantId");
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
