package com.securedweb.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TenantHolder {
	
	  private static final Logger LOG = LoggerFactory.getLogger(TenantHolder.class);

	  private static final ThreadLocal<String> CONTEXT = new ThreadLocal<>();

	  public static void setTenantId(String tenantId) {
	    CONTEXT.set(tenantId);
	  }

	  public static String getTenantId() {
	    return CONTEXT.get();
	  }

	  public static void clear() {
	    CONTEXT.remove();
	  }
	}
