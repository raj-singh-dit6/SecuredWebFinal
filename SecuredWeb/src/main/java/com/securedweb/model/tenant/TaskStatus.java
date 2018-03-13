package com.securedweb.model.tenant;

import java.time.LocalDateTime;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name="TASK_STATUS")
public class TaskStatus {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name="STATUS")
	private String status;
	
	@Column(name="STATUS_COLOUR")
	private String statusColour;
	
	@Column(name="TENANT_ID")
	private String tenantId;

	@OneToMany(mappedBy="taskStatus")
	private Set<Task> tasks;
	
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
	

}
