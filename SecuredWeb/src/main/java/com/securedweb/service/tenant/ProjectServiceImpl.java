package com.securedweb.service.tenant;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Hibernate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.securedweb.dto.tenant.ProjectDTO;
import com.securedweb.dto.tenant.TaskDTO;
import com.securedweb.dto.tenant.UserDTO;
import com.securedweb.dto.tenant.UserProjectDTO;
import com.securedweb.model.tenant.Project;
import com.securedweb.model.tenant.Task;
import com.securedweb.model.tenant.User;
import com.securedweb.model.tenant.UserProject;
import com.securedweb.repository.tenant.ProjectRepository;
import com.securedweb.repository.tenant.UserProjectRepository;
import com.securedweb.repository.tenant.UserRepository;
import com.securedweb.util.TenantHolder;

@Service("projectService")
@Transactional("tenantTransactionManager")
public class ProjectServiceImpl implements ProjectService {
	
	private static final Logger LOG = LoggerFactory.getLogger(ProjectServiceImpl.class);

	@Autowired
	ProjectRepository projectRepository;
	
	@Autowired
	UserProjectRepository userProjectRepository;

	@Autowired
	UserRepository userRepository;
	
	@Override
	public boolean isProjectNameUnique(String name, String tenantId) {
		Project project = projectRepository.findByNameAndTenantId(name,tenantId);
		return project == null;
	}

	@Override
	public ProjectDTO addProject(ProjectDTO project) {
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
					//Hibernate.initialize(project.getTasks());
					//Hibernate.initialize(project.getUserProjects());
					
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
	public ProjectDTO getProjectDTO(Integer id) {
		Project project= projectRepository.findByIdAndTenantId(id, TenantHolder.getTenantId());
		ProjectDTO projectDTO = new ProjectDTO();
		if(project!=null)
		{
			Hibernate.initialize(project.getChildProjects());
			Hibernate.initialize(project.getParentProject());
			//Hibernate.initialize(project.getTasks());
			//Hibernate.initialize(project.getUserProjects());

				projectDTO.setName(project.getName());
				projectDTO.setId(project.getId());
				projectDTO.setDescription(project.getDescription());
				projectDTO.setParentProject(project.getParentProject());
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
			//Hibernate.initialize(project.getTasks());
			//Hibernate.initialize(project.getUserProjects());
		}
		return project;
	}
	
	
	@Override
	public Project getProject(Integer id) {
		Project project= projectRepository.findByIdAndTenantId(id, TenantHolder.getTenantId());
		if(project!=null)
		{
			//Hibernate.initialize(project.getChildProjects());
			//Hibernate.initialize(project.getParentProject());
			//Hibernate.initialize(project.getTasks());
			//Hibernate.initialize(project.getUserProjects());
		}
		return project;
	}

	@Override
	public Set<TaskDTO> loadAllTasksByProjectID(Integer id) {
		Project project=projectRepository.findByIdAndTenantId(id, TenantHolder.getTenantId());
		Set<Task> projectTasks=project.getTasks();
		Set<TaskDTO> projectTaskDTOs=new HashSet<TaskDTO>();		
		for(Task task:projectTasks)
		{
			TaskDTO taskDTO = new TaskDTO();
			taskDTO.setId(task.getId());
			taskDTO.setName(task.getDescription());
			taskDTO.setDescription(task.getDescription());
			projectTaskDTOs.add(taskDTO);
		}
		return projectTaskDTOs;
	}

	@Override
	public Set<UserDTO> loadAllUsersByProjectID(Integer id) {
		Project project = projectRepository.findByIdAndTenantId(id, TenantHolder.getTenantId());
		Set<UserProject> userProjectSet=project.getUserProjects();
		Set<UserDTO> userDTOs = new HashSet<UserDTO>();
		for(UserProject userProject:userProjectSet)
		{
			User user = userProject.getUser();
			UserDTO userDTO = new UserDTO();
			userDTO.setId(user.getId());
			userDTO.setFirstName(user.getFirstName());
			userDTOs.add(userDTO);
		}
		return userDTOs;		
	}
	
}
