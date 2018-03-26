package com.securedweb.dto.tenant;

import java.io.Serializable;
import java.util.Set;

import com.securedweb.model.tenant.Role;
import com.securedweb.model.tenant.UserProject;
import com.securedweb.model.tenant.UserTask;

public class UserDTO implements Serializable{

	 private static final long serialVersionUID = 1L;
	 
	 private Integer id;
	 private String firstName;
	 private String lastName;
	 private String email;
	 private String tenantId;
	 private String forgotToken;
	 private String ssoId;
	 private String password;
	 private String newPassword;
	 private Set<UserProject> userProjects;
	 private Set<UserTask> userTasks;
	 private Set<Role> userRoles;
	 
	 public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTenantId() {
		return tenantId;
	}
	public void setTenantId(String tenantId) {
		this.tenantId = tenantId;
	}
	public String getForgotToken() {
		return forgotToken;
	}
	public void setForgotToken(String forgotToken) {
		this.forgotToken = forgotToken;
	}
	public Set<UserProject> getUserProjects() {
		return userProjects;
	}
	public void setUserProjects(Set<UserProject> userProjects) {
		this.userProjects = userProjects;
	}
	public Set<UserTask> getUserTasks() {
		return userTasks;
	}
	public void setUserTasks(Set<UserTask> userTasks) {
		this.userTasks = userTasks;
	}
	public Set<Role> getUserRoles() {
		return userRoles;
	}
	public void setUserRoles(Set<Role> userRoles) {
		this.userRoles = userRoles;
	}
	public String getSsoId() {
		return ssoId;
	}
	public void setSsoId(String ssoId) {
		this.ssoId = ssoId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getNewPassword() {
		return newPassword;
	}
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
}
