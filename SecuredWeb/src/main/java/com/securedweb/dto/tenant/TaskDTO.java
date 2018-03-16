package com.securedweb.dto.tenant;

import java.util.HashSet;
import java.util.Set;

import com.securedweb.model.tenant.Project;
import com.securedweb.model.tenant.TaskStatus;
import com.securedweb.model.tenant.UserTask;

public class TaskDTO {
	
	private Integer id;
	
	private Project project;
	
	private String name;

	private String description;
	
	private TaskStatus taskStatus;

	private Set<UserTask> userTasks = new HashSet<UserTask>();
	
	private String tenantId;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public TaskStatus getTaskStatus() {
		return taskStatus;
	}

	public void setTaskStatus(TaskStatus taskStatus) {
		this.taskStatus = taskStatus;
	}

	public Set<UserTask> getUserTasks() {
		return userTasks;
	}

	public void setUserTasks(Set<UserTask> userTasks) {
		this.userTasks = userTasks;
	}

	public String getTenantId() {
		return tenantId;
	}

	public void setTenantId(String tenantId) {
		this.tenantId = tenantId;
	}


}
