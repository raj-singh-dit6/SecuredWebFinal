package com.securedweb.service.tenant;

import java.util.List;

import com.securedweb.model.tenant.Project;

public interface ProjectService {

	List<Project> findAll();
	Project findById(int id) ;
	void save(Project project);
	
}
