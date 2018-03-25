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

@Entity
@Table(name="USER",uniqueConstraints = @UniqueConstraint(columnNames = "EMAIL"))
	public class User implements Serializable{
	
	@Id 
	@GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;
 
	@NotEmpty
    @Column(name="SSO_ID", unique=true, nullable=false)
    private String ssoId;
     
	@NotEmpty
	@Column(name="PASSWORD", nullable=false)
    private String password;
    
	@NotEmpty
    @Column(name="FIRST_NAME", nullable=false)
    private String firstName;

	@NotEmpty
    @Column(name="LAST_NAME", nullable=false)
    private String lastName;
 
	@NotEmpty
	@Column(name="EMAIL", nullable=false)
    private String email;
	
	@NotEmpty
    @Column(name="TENANT_ID")
    private String tenantId;
	
    @Column(name="FORGOT_TOKEN",nullable=false)
    private String forgotToken;
	
	
	@Column(name="CREATED_ON")
	@CreationTimestamp
	private LocalDateTime createDateTime;

	@Column(name="UPDATED_ON")
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
    @JoinTable(name = "USER_ROLE", 
             joinColumns = { @JoinColumn(name = "USER_ID") }, 
             inverseJoinColumns = { @JoinColumn(name = "ROLE_ID") })
    private Set<Role> userRoles = new HashSet<Role>();
	

	public String getTenantId() {
		return tenantId;
	}

	public void setTenantId(String tenantId) {
		this.tenantId = tenantId;
	}

	public String getForgotToken() {
		return forgotToken;
	}

	public void setForgotToken(String forgotToken) {
		this.forgotToken = forgotToken;
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

	public Set<Role> getUserRoles() {
		return userRoles;
	}

	public void setUserRoles(Set<Role> userRoles) {
		this.userRoles = userRoles;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSsoId() {
		return ssoId;
	}

	public Set<Document> getUserDocuments() {
		return userDocuments;
	}

	public void setUserDocuments(Set<Document> userDocuments) {
		this.userDocuments = userDocuments;
	}

	public void setSsoId(String ssoId) {
		this.ssoId = ssoId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public Set<UserProject> getUserProjects() {
		return userProjects;
	}

	public void setUserProjects(Set<UserProject> userProjects) {
		this.userProjects = userProjects;
	}

	public Set<UserTask> getUserTasks() {
		return userTasks;
	}

	public void setUserTasks(Set<UserTask> userTasks) {
		this.userTasks = userTasks;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((createDateTime == null) ? 0 : createDateTime.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + ((forgotToken == null) ? 0 : forgotToken.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((ssoId == null) ? 0 : ssoId.hashCode());
		result = prime * result + ((tenantId == null) ? 0 : tenantId.hashCode());
		result = prime * result + ((updateDateTime == null) ? 0 : updateDateTime.hashCode());
		result = prime * result + ((userDocuments == null) ? 0 : userDocuments.hashCode());
		result = prime * result + ((userProjects == null) ? 0 : userProjects.hashCode());
		result = prime * result + ((userRoles == null) ? 0 : userRoles.hashCode());
		result = prime * result + ((userTasks == null) ? 0 : userTasks.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (createDateTime == null) {
			if (other.createDateTime != null)
				return false;
		} else if (!createDateTime.equals(other.createDateTime))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (forgotToken == null) {
			if (other.forgotToken != null)
				return false;
		} else if (!forgotToken.equals(other.forgotToken))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (ssoId == null) {
			if (other.ssoId != null)
				return false;
		} else if (!ssoId.equals(other.ssoId))
			return false;
		if (tenantId == null) {
			if (other.tenantId != null)
				return false;
		} else if (!tenantId.equals(other.tenantId))
			return false;
		if (updateDateTime == null) {
			if (other.updateDateTime != null)
				return false;
		} else if (!updateDateTime.equals(other.updateDateTime))
			return false;
		if (userDocuments == null) {
			if (other.userDocuments != null)
				return false;
		} else if (!userDocuments.equals(other.userDocuments))
			return false;
		if (userProjects == null) {
			if (other.userProjects != null)
				return false;
		} else if (!userProjects.equals(other.userProjects))
			return false;
		if (userRoles == null) {
			if (other.userRoles != null)
				return false;
		} else if (!userRoles.equals(other.userRoles))
			return false;
		if (userTasks == null) {
			if (other.userTasks != null)
				return false;
		} else if (!userTasks.equals(other.userTasks))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", ssoId=" + ssoId + ", password=" + password + ", firstName=" + firstName
				+ ", lastName=" + lastName + ", email=" + email + ", tenantId=" + tenantId + ", forgotToken="
				+ forgotToken + ", createDateTime=" + createDateTime + ", updateDateTime=" + updateDateTime
				+ ", userProjects=" + userProjects + ", userTasks=" + userTasks + ", userDocuments=" + userDocuments
				+ ", userRoles=" + userRoles + "]";
	}
	
	
}
