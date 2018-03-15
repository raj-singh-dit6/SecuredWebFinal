package com.securedweb.service.tenant;

import java.util.List;

import com.securedweb.dto.tenant.ProjectDTO;
import com.securedweb.dto.tenant.UserDTO;
import com.securedweb.model.tenant.Project;

public interface ProjectService {

	boolean isProjectNameUnique(String name, String tenantId);
	ProjectDTO addProject(ProjectDTO project);
	ProjectDTO updateProject(ProjectDTO project);
	List<ProjectDTO> getAllProjects();
	ProjectDTO getProject(Integer id);
	Project getParentProject(Integer id);
	void deleteProject(Integer id);
	
}
