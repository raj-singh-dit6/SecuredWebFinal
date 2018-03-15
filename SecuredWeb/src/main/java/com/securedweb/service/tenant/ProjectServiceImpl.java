package com.securedweb.service.tenant;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.securedweb.dto.tenant.ProjectDTO;
import com.securedweb.model.tenant.Project;
import com.securedweb.repository.tenant.ProjectRepository;
import com.securedweb.util.TenantHolder;

@Service("projectService")
@Transactional("tenantTransactionManager")
public class ProjectServiceImpl implements ProjectService {

	@Autowired
	ProjectRepository projectRepository;

	@Override
	public boolean isProjectNameUnique(String name, String tenantId) {
		Project project = projectRepository.findByNameAndTenantId(name,tenantId);
		return project == null;
	}

	@Override
	public ProjectDTO addProject(ProjectDTO project) {
		System.out.print(project.getParentProject());
		if(project.getParentProject().getName()==null)
		 {	 
			project.setParentProject(null);
		 }else
		 {
			 Project parentProject = getParentProject(project.getParentProject().getId());
			 project.setParentProject(parentProject);
		 }
		
		Project newProject = new Project();
		newProject.setName(project.getName());
		newProject.setDescription(project.getDescription());
		newProject.setParentProject(project.getParentProject());
		newProject.setTenantId(TenantHolder.getTenantId());
		projectRepository.save(newProject);
		return project;
	}

	@Override
	public ProjectDTO updateProject(ProjectDTO project) {
		Project updateProject = projectRepository.findByIdAndTenantId(project.getId(), TenantHolder.getTenantId());
		System.out.print(project.getParentProject());
		if(project.getParentProject().getName()==null)
		 {	 
			project.setParentProject(null);
		 }else
		 {
			 Project parentProject = getParentProject(project.getParentProject().getId());
			 project.setParentProject(parentProject);
		 } 
			
			 updateProject.setName(project.getName());
			 updateProject.setDescription(project.getDescription());
			 updateProject.setParentProject(project.getParentProject());
		return project;
	}

	@Override
	public List<ProjectDTO> getAllProjects() {
		List<Project> projectList =  new ArrayList<Project>();
		List<ProjectDTO> projectDTOList = new ArrayList<ProjectDTO>();
		projectList=(List<Project>) projectRepository.findByTenantId(TenantHolder.getTenantId());
		for(Project project:projectList)
		{
			if(project!=null)
			{
					Hibernate.initialize(project.getChildProjects());
					Hibernate.initialize(project.getParentProject());
					Hibernate.initialize(project.getTasks());
					Hibernate.initialize(project.getUserProjects());
					
						ProjectDTO projectDTO = new ProjectDTO();
						projectDTO.setId(project.getId());
						projectDTO.setName(project.getName());
						projectDTO.setParentProject(project.getParentProject());
						projectDTO.setChildProjects(project.getChildProjects());
						projectDTO.setDescription(project.getDescription());
						projectDTOList.add(projectDTO);
			}	
		}
		return projectDTOList;
	}

	@Override
	public void deleteProject(Integer id) {
		projectRepository.deleteByIdAndTenantId(id,TenantHolder.getTenantId());		
	}

	@Override
	public ProjectDTO getProject(Integer id) {
		Project project= projectRepository.findByIdAndTenantId(id, TenantHolder.getTenantId());
		ProjectDTO projectDTO = new ProjectDTO();
		if(project!=null)
		{
			Hibernate.initialize(project.getChildProjects());
			Hibernate.initialize(project.getParentProject());
			Hibernate.initialize(project.getTasks());
			Hibernate.initialize(project.getUserProjects());

				projectDTO.setName(project.getName());
				projectDTO.setId(project.getId());
				projectDTO.setDescription(project.getDescription());
				//projectDTO.setParentProject(project.getParentProject());
		}
		return projectDTO;
	}
	
	@Override
	public Project getParentProject(Integer id) {
		Project project= projectRepository.findByIdAndTenantId(id, TenantHolder.getTenantId());
		if(project!=null)
		{
			Hibernate.initialize(project.getChildProjects());
			Hibernate.initialize(project.getParentProject());
			Hibernate.initialize(project.getTasks());
			Hibernate.initialize(project.getUserProjects());
		}
		return project;
	}
	
}
