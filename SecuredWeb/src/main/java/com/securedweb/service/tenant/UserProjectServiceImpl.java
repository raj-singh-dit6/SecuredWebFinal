package com.securedweb.service.tenant;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.securedweb.dto.tenant.UserProjectDTO;
import com.securedweb.model.tenant.Project;
import com.securedweb.model.tenant.User;
import com.securedweb.model.tenant.UserProject;
import com.securedweb.repository.tenant.ProjectRepository;
import com.securedweb.repository.tenant.UserProjectRepository;
import com.securedweb.repository.tenant.UserRepository;
import com.securedweb.util.TenantHolder;

@Service("userProjectService")
@Transactional("tenantTransactionManager")
public class UserProjectServiceImpl implements UserProjectService {

	@Autowired
	UserService userService;
	
	@Autowired
	ProjectService projectService;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	UserProjectRepository userProjectRepository;
	
	@Autowired
	ProjectRepository projectRepository;
	
	@Override
	public UserProjectDTO addUserProject(UserProjectDTO userProjectDTO)
	{
		User user = userService.getUser(userProjectDTO.getUser().getId());
		Project project = projectService.getProject(userProjectDTO.getProject().getId());
		System.err.println(user);
		System.err.println(project);
		
		UserProject userProject = new UserProject();
		userProject.setProject(project);
		userProject.setUser(user);
		userProject.setTenantId(TenantHolder.getTenantId());
		user.getUserProjects().add(userProject);
		userRepository.save(user);
		projectRepository.save(project);
		
		  System.out.println(user.getUserProjects().size());
		  
		return userProjectDTO;
		
	}
	
	@Override
	public void deleteUserProject(Integer userProjectId)
	{
		UserProject userProject=userProjectRepository.findByIdAndTenantId(userProjectId,TenantHolder.getTenantId());
		System.err.println(userProject);
		User user = userService.getUser(userProject.getUser().getId());
		user.getUserProjects().remove(userProject);
		userRepository.save(user);
	}

	@Override
	public List<UserProjectDTO> getAllUserProject() {
		List<UserProject> userProjectList = userProjectRepository.findByTenantId(TenantHolder.getTenantId());
		List<UserProjectDTO> userProjectDTOList = new ArrayList<UserProjectDTO>();
		
		for(UserProject userProject:userProjectList)
		{
			if(userProject!=null)
			{
				UserProjectDTO userProjectDTO = new UserProjectDTO();
				
				userProjectDTO.setId((userProject.getId()));
				userProjectDTO.setProject(userProject.getProject());
				userProjectDTO.setUser(userProject.getUser());
				userProjectDTOList.add(userProjectDTO);
			}
			
		}
		System.err.println(userProjectDTOList);
		return userProjectDTOList;
	
	}
}