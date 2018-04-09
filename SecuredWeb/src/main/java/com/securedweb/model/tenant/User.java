package com.securedweb.model.tenant;

import java.io.Serializable;
import java.time.LocalDateTime;
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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Entity
@Data
public class User implements Serializable{
	
	@Id 
	@GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;
 
    @Column(unique=true, nullable=false)
    private String ssoId;
     
	@Column(nullable=false)
    private String password;
    
    @Column(nullable=false)
    private String firstName;

    @Column( nullable=false)
    private String lastName;
 
	@Column( nullable=false)
    private String email;
	
    @Column(nullable=false)
    private String tenantId;
	
	@CreationTimestamp
	private LocalDateTime createDateTime;

	@UpdateTimestamp
	private LocalDateTime updateDateTime;
	
	
	@JsonIgnore
	@OneToMany(mappedBy = "project", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<UserProject> userProjects = new HashSet<UserProject>(); 
	
	@JsonIgnore
	@OneToMany(mappedBy = "task",cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<UserTask> userTasks = new HashSet<UserTask>();
	
	@JsonIgnore
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private Set<Document> userDocuments = new HashSet<Document>();
	
	@NotEmpty
	@JsonIgnore
	@ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "userRole", 
             joinColumns = { @JoinColumn(name = "userId") }, 
             inverseJoinColumns = { @JoinColumn(name = "roleId") })
    private Set<Role> userRoles = new HashSet<Role>();
	
}
