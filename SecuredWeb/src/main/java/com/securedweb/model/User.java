package com.securedweb.model;

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
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name="USER")
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
    @JoinColumn(name="TENANT_ID")
    private Integer tenandId;
	
	@NotEmpty
    @Column(name="FORGOT_TOKEN")
    private Integer forgotToken;
	
	@NotEmpty
    @Column(name="TOKEN_ID")
    private String tokenId;
	
	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "TENANT_ID")
	private Tenant tenant;
	
	@Column(name="CREATED_ON")
	@CreationTimestamp
	private LocalDateTime createDateTime;

	@Column(name="UPDATED_ON")
	@UpdateTimestamp
	private LocalDateTime updateDateTime;
	
	
	@OneToMany(mappedBy = "primaryKey.user",cascade = CascadeType.ALL)
	private Set<UserProject> userProjects = new HashSet<UserProject>(); 
	
	@OneToMany(mappedBy = "primaryKey.user",cascade = CascadeType.ALL)
	private Set<UserTask> userTasks = new HashSet<UserTask>();
	
	@NotEmpty
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "USER_ROLE", 
             joinColumns = { @JoinColumn(name = "USER_ID") }, 
             inverseJoinColumns = { @JoinColumn(name = "ROLE_ID") })
    private Set<Role> userRoles = new HashSet<Role>();
	
	public String getTokenId() {
		return tokenId;
	}

	public void setTokenId(String tokenId) {
		this.tokenId = tokenId;
	}

	public Tenant getTenant() {
		return tenant;
	}

	public void setTenant(Tenant tenant) {
		this.tenant = tenant;
	}

	public Integer getTenandId() {
		return tenandId;
	}

	public void setTenandId(Integer tenandId) {
		this.tenandId = tenandId;
	}

	public Integer getForgotToken() {
		return forgotToken;
	}

	public void setForgotToken(Integer forgotToken) {
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
	public boolean equals(Object o)
	{
		if(o==null)
			return false;
		if(this==o)
			return true;
		if(!(o instanceof User))
			return false;
		User other=(User)o;
		if(id==null)
		{	if(other.id!=null)
			return false;
		}else if (!id.equals(other.id))
			return false;
		if(ssoId==null)
		{	if(other.ssoId!=null)
				return false;
		}else if(!ssoId.equals(other.ssoId))
			return false;
		return true;
		
	}
	
	@Override
	public int hashCode()
	{
		final int  prime =31;
		int result = 1;
		result = prime*result+((id==null)?0:id.hashCode());	
		result = prime*result+((ssoId==null)?0:ssoId.hashCode());

		return result;
	}

	@Override 
	public String toString() {
	return "User [id=" + id + ", ssoId=" + ssoId + ", password=" + password
            + ", firstName=" + firstName + ", lastName=" + lastName
            + ", email=" + email + "]";
	}
	
}
