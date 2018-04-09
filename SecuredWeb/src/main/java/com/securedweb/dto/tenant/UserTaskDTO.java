package com.securedweb.dto.tenant;

import com.securedweb.model.tenant.Project;
import com.securedweb.model.tenant.Task;
import com.securedweb.model.tenant.User;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor
public class UserTaskDTO {

	private Integer id;
	private User user;
    private Task task;
	private Project project;
	
}
