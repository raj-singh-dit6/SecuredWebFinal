package com.securedweb.dto.tenant;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.securedweb.model.tenant.Project;
import com.securedweb.model.tenant.Task;
import com.securedweb.model.tenant.UserProject;

public class ProjectDTO {
	
	
	private Integer id;
	private String name;
	private String description;
	private Project parentProject;
	private Set<Project> childProjects = new HashSet<Project>(); 
    private Set<Task> tasks;
	private String tenantId;
	private Set<UserProject> userProjects = new HashSet<UserProject>();
	private LocalDateTime createDateTime;
	private LocalDateTime updateDateTime;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
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
	public Project getParentProject() {
		return parentProject;
	}
	public void setParentProject(Project parentProject) {
		this.parentProject = parentProject;
	}
	public Set<Project> getChildProjects() {
		return childProjects;
	}
	public void setChildProjects(Set<Project> childProjects) {
		this.childProjects = childProjects;
	}
	public Set<Task> getTasks() {
		return tasks;
	}
	public void setTasks(Set<Task> tasks) {
		this.tasks = tasks;
	}
	public String getTenantId() {
		return tenantId;
	}
	public void setTenantId(String tenantId) {
		this.tenantId = tenantId;
	}
	public Set<UserProject> getUserProjects() {
		return userProjects;
	}
	public void setUserProjects(Set<UserProject> userProjects) {
		this.userProjects = userProjects;
	}
	public LocalDateTime getCreateDateTime() {
		return createDateTime;
	}
	public void setCreateDateTime(LocalDateTime createDateTime) {
		this.createDateTime = createDateTime;
	}
	public LocalDateTime getUpdateDateTime() {
		return updateDateTime;
	}
	public void setUpdateDateTime(LocalDateTime updateDateTime) {
		this.updateDateTime = updateDateTime;
	}

	@Override
	public String toString() {
		return "ProjectDTO [id=" + id + ", name=" + name + ", description=" + description + ", parentProject="
				+ parentProject + ", childProjects=" + childProjects + ", tasks=" + tasks + ", tenantId=" + tenantId
				+ ", userProjects=" + userProjects + ", createDateTime=" + createDateTime + ", updateDateTime="
				+ updateDateTime + "]";
	}
	
}
