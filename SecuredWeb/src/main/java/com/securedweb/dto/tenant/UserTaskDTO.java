package com.securedweb.dto.tenant;

import com.securedweb.model.tenant.Project;
import com.securedweb.model.tenant.Task;
import com.securedweb.model.tenant.User;

public class UserTaskDTO {

	private Integer id;
	private User user;
    private Task task;
	private Project project;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Task getTask() {
		return task;
	}
	public void setTask(Task task) {
		this.task = task;
	}
	public Project getProject() {
		return project;
	}
	public void setProject(Project project) {
		this.project = project;
	}
	
}
