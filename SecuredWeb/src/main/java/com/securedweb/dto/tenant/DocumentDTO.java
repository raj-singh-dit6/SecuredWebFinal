package com.securedweb.dto.tenant;

import com.securedweb.model.tenant.Project;
import com.securedweb.model.tenant.User;

public class DocumentDTO {
	
		private Integer id;
		
	    private String name;
	     
	    private String description;
	     
	    private User user;
	    
	    private Project  project;

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
	    
	    
	     
}
