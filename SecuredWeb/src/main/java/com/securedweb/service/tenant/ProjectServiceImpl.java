package com.securedweb.service.tenant;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.securedweb.model.tenant.Project;
import com.securedweb.repository.tenant.ProjectRepository;

@Service("projectService")
@Transactional("tenantTransactionManager")
public class ProjectServiceImpl implements ProjectService {

	@Autowired
	ProjectRepository projectRepo;
	
	@Override
	public List<Project> findAll() {
		List<Project> projects=new ArrayList<Project>();
		projects=(List<Project>) projectRepo.findAll();
		for(Project project:projects)
		{
			if(project!=null)
			{
				Hibernate.initialize(project.getChildProjects());
				Hibernate.initialize(project.getParentProject());
				Hibernate.initialize(project.getUserProjects());
				Hibernate.initialize(project.getTasks());
			}	
		}
		return projects;
	}

	@Override
	public Project findById(int id) {
		Project project=projectRepo.findById(id).get();
		if(project!=null)
		{
			Hibernate.initialize(project.getChildProjects());
			Hibernate.initialize(project.getParentProject());
			Hibernate.initialize(project.getUserProjects());
			Hibernate.initialize(project.getTasks());
		}	
		return project;
	}

	@Override
	public void save(Project project) {
		projectRepo.save(project);		
	}
	
}
