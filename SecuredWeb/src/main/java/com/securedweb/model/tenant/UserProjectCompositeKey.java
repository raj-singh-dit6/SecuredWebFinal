package com.securedweb.model.tenant;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;


/*@Embeddable*/
public class UserProjectCompositeKey implements Serializable{
	
	/*@ManyToOne(cascade = CascadeType.ALL,fetch=FetchType.LAZY)
	private User user;
	
	@ManyToOne(cascade = CascadeType.ALL,fetch=FetchType.LAZY)
	private Project project;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}


*/
}
