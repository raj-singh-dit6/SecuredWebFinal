package com.securedweb.util;

public class TenantHolder {

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
