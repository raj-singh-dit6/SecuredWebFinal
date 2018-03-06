package com.securedweb.model;

import java.io.Serializable;

public enum RoleType implements Serializable{
	USER("USER"),
	DBA("DBA"),
	ADMIN("ADMIN"),
	SUPER("SUPER");
	
	String roleType;
	private RoleType(String roleType)
	{
		this.roleType=roleType;
		
	}

	public String getRoleType() {
		return roleType;
	}
}