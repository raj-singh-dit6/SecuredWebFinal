package com.securedweb.dto.tenant;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.securedweb.model.tenant.Project;
import com.securedweb.model.tenant.Task;
import com.securedweb.model.tenant.UserProject;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor
public class ProjectDTO {

	private Integer id;
	private String name;
	private String description;
	private String startDate;
	private Project parentProject;
	private byte[] projectFile;
	private Set<Project> childProjects = new HashSet<Project>(); 
    private Set<Task> tasks;
	private String tenantId;
	private Set<UserProject> userProjects = new HashSet<UserProject>();
	private LocalDateTime createDateTime;
	private LocalDateTime updateDateTime;
}