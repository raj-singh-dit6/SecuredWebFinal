package com.securedweb.model;

import java.io.Serializable;

public enum UserRoleType implements Serializable{
	USER("USER"),
	DBA("DBA"),
	ADMIN("ADMIN");
	
	String userRoleType;
	private UserRoleType(String userRoleType)
	{
		this.userRoleType=userRoleType;
		
	}

	public String getUserRoleType() {
		return userRoleType;
	}
}