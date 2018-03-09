package com.securedweb.model.tenant;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.AssociationOverride;
import javax.persistence.AssociationOverrides;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name="USER_TASK")
@AssociationOverrides({
	@AssociationOverride(name = "primaryKey.user",
        joinColumns = @JoinColumn(name = "USER_ID")),
    @AssociationOverride(name = "primaryKey.task",
        joinColumns = @JoinColumn(name = "TASK_ID")) })
public class UserTask implements Serializable{
	
	@EmbeddedId
	private UserTaskCompositeKey primaryKey = new UserTaskCompositeKey();
	
	@Transient
	private User user;

	@Transient
    private Task task;
	
    @Column(name = "TENANT_ID")
	private String tenantId;
	
	@Column(name="CREATED_ON")
	@CreationTimestamp
	private LocalDateTime createDateTime;
	
	@Column(name="UPDATED_ON")
	@UpdateTimestamp
	private LocalDateTime updateDateTime;

	public UserTaskCompositeKey getPrimaryKey() {
		return primaryKey;
	}

	public void setPrimaryKey(UserTaskCompositeKey primaryKey) {
		this.primaryKey = primaryKey;
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

	public String getTenantId() {
		return tenantId;
	}

	public void setTenantId(String tenantId) {
		this.tenantId = tenantId;
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

}
