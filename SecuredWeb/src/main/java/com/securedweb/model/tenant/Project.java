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
@Table(name="PROJECT")
public class Project implements Serializable{

	@Id	
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@NotEmpty
	@Column(name="NAME", nullable=false)
	private String name;

	@NotEmpty
	@Column(name="DESCRIPTION" , nullable=false)
	private String description;
	
	@ManyToOne(cascade={CascadeType.ALL})
	@JoinColumn(name="PARENT_PROJECT_ID")
	private Project parentProject;

	@OneToMany(mappedBy="parentProject")
	private Set<Project> childProjects = new HashSet<Project>(); 
	
	
	@Column(name="CREATED_ON")
	@CreationTimestamp
	private LocalDateTime createDateTime;

	@Column(name="UPDATED_ON")
	@UpdateTimestamp
	private LocalDateTime updateDateTime;
	
	@OneToMany(mappedBy="project")
    private Set<Task> tasks;
	
	@NotEmpty
	@Column(name="TENANT_ID", nullable=false)
	private String tenantId;
	
	@OneToMany(mappedBy = "primaryKey.project",cascade = CascadeType.ALL)
	private Set<UserProject> userProjects = new HashSet<UserProject>();
	
	public Integer getId() {
		return id;
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
	
}
