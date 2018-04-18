package com.securedweb.model.tenant;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Data;

@Entity
@Data
public class Project implements Serializable{

	@Id	
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@Column(nullable=false)
	private String name;

	@Column(nullable=false)
	private String description;
	
	@Column(nullable=true)
	private Date startDate;
	
	@ManyToOne(cascade={CascadeType.ALL})
	@JoinColumn(name="parentProjectId",insertable = false, updatable = false)
	@JsonBackReference
	private Project parentProject;

	@OneToMany(mappedBy="parentProject",fetch=FetchType.EAGER)
	@JsonManagedReference
	private Set<Project> childProjects = new HashSet<Project>(); 
	 
	@CreationTimestamp
	private LocalDateTime createDateTime;

	@UpdateTimestamp
	private LocalDateTime updateDateTime;
	
	@JsonIgnore
	@OneToMany(mappedBy="project",fetch=FetchType.LAZY)
    private Set<Task> tasks;
	
	@Column(nullable=false)
	private String tenantId;
	
	@JsonIgnore
	@OneToMany(mappedBy = "project", cascade = CascadeType.ALL)
    private Set<Document> projectDocuments = new HashSet<Document>();
	
	@JsonIgnore
	@OneToMany(mappedBy = "project")
	private Set<UserProject> userProjects = new HashSet<UserProject>();
}
