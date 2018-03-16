package com.securedweb.dto.tenant;

import java.util.Set;

import com.securedweb.model.tenant.Task;

public class TaskStatusDTO {

	private Integer id;
	
	private String status;
	
	private String statusColour;
	
	private String tenantId;

	private Set<Task> tasks;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getStatusColour() {
		return statusColour;
	}

	public void setStatusColour(String statusColour) {
		this.statusColour = statusColour;
	}

	public String getTenantId() {
		return tenantId;
	}

	public void setTenantId(String tenantId) {
		this.tenantId = tenantId;
	}

	public Set<Task> getTasks() {
		return tasks;
	}

	public void setTasks(Set<Task> tasks) {
		this.tasks = tasks;
	}
}
