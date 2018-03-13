package com.securedweb.model.tenant;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name="TASK")
public class Task implements Serializable {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	
	@ManyToOne
    @JoinColumn(name="PROJECT_ID", nullable=false)
	private Project project;
	
	@Column(name="NAME",nullable=false)
	private String name;

	@Column(name="DESCRIPTION")
	private String description;
	
	@ManyToOne
	@JoinColumn(name="TASK_STATUS_ID", nullable=false)
	private TaskStatus taskStatus;

	@OneToMany(mappedBy = "primaryKey.task",cascade = CascadeType.ALL)
	private Set<UserTask> userTasks = new HashSet<UserTask>();
	
	@NotEmpty
	@Column(name="TENANT_ID", nullable=false)
	private String tenantId;
	
	public String getTenantId() {
		return tenantId;
	}

	public void setTenantId(String tenantId) {
		this.tenantId = tenantId;
	}

	public Set<UserTask> getUserTasks() {
		return userTasks;
	}

	public void setUserTasks(Set<UserTask> userTasks) {
		this.userTasks = userTasks;
	}

	@Column(name="CREATED_ON")
	@CreationTimestamp
	private LocalDateTime createDateTime;
	
	
	@Column(name="UPDATED_ON")
	@UpdateTimestamp
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

	public TaskStatus getTaskStatus() {
		return taskStatus;
	}

	public void setTaskStatus(TaskStatus taskStatus) {
		this.taskStatus = taskStatus;
	}
	
	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

}
