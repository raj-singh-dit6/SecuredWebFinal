package com.securedweb.dto.tenant;

import java.io.Serializable;
import java.util.Set;

import com.securedweb.model.tenant.Role;
import com.securedweb.model.tenant.UserProject;
import com.securedweb.model.tenant.UserTask;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor
public class UserDTO implements Serializable{

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
	 
}
