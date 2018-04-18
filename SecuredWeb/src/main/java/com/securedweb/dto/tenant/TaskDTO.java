package com.securedweb.dto.tenant;

import java.util.HashSet;

import java.util.Set;

import com.securedweb.model.tenant.Project;
import com.securedweb.model.tenant.TaskStatus;
import com.securedweb.model.tenant.UserTask;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter @Setter @NoArgsConstructor
public class TaskDTO {
	
	private Integer id;
	private Project project;
	private String name;
	private String description;
	private TaskStatus taskStatus;
	private Set<UserTask> userTasks = new HashSet<UserTask>();
	private String tenantId;


}
