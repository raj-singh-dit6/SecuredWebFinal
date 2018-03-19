package com.securedweb.service.tenant;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
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
		
		UserProject userProject = new UserProject();
		userProject.setProject(project);
		userProject.setUser(user);
		userProject.setTenantId(TenantHolder.getTenantId());
		
		user.getUserProjects().add(userProject);
		  
		System.err.println(user.getFirstName() + " : TOTAL PROJECTS : "+ user.getUserTasks().size());
		
		return userProjectDTO;
		
	}
	
	@Override
	public void deleteUserProject(Integer userProjectId)
	{
		UserProject userProject=userProjectRepository.findByIdAndTenantId(userProjectId,TenantHolder.getTenantId());
		
		User user = userService.getUser(userProject.getUser().getId());
		user.getUserProjects().remove(userProject);
		
		System.err.println(user.getFirstName() + " : TOTAL PROJECTS : "+ user.getUserTasks().size());;
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
	

	@Override
	public List<UserProjectDTO> getAllUserProjectBySsoId() {
		
		 Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		 List<UserProjectDTO> userProjectDTOList= new ArrayList<UserProjectDTO>();
		 if (principal instanceof UserDetails) {
			 String  ssoId = ((UserDetails)principal).getUsername();
			 System.err.println("User ssoId :"+ssoId);
			 User user = userRepository.findBySsoIdAndTenantId(ssoId, TenantHolder.getTenantId());
			 Set<UserProject> userProjectSet=userProjectRepository.findByUserAndTenantId(user,TenantHolder.getTenantId());
			for(UserProject userProject:userProjectSet)
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
		 }
		 System.err.println(userProjectDTOList);
		 return userProjectDTOList;
	}
}	