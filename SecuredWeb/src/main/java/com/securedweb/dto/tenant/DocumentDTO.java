package com.securedweb.dto.tenant;

import com.securedweb.model.tenant.Project;
import com.securedweb.model.tenant.User;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor
public class DocumentDTO {
	
		private Integer id;
	    private String name;
	    private String description;
	    private String location;
	    private User user;
	    private Project  project;
	     
}
