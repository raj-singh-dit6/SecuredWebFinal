package com.securedweb.service.tenant;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.securedweb.dto.tenant.UserProjectDTO;
import com.securedweb.model.tenant.Project;
import com.securedweb.model.tenant.User;
import com.securedweb.model.tenant.UserProject;
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
	UserProjectRepository userProjectRepository;
	
	@Autowired
	UserRepository userRepository;
	
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
		userProjectRepository.save(userProject);
		
		return userProjectDTO;
		
	}
	
	@Override
	public void deleteUserProject(UserProjectDTO userProjectDTO)
	{
		User user = userService.getUser(userProjectDTO.getUser().getId());
		Project project = projectService.getProject(userProjectDTO.getProject().getId());
		UserProject userProject = new UserProject();
		userProject.setProject(project);
		userProject.setUser(user);
		userProjectRepository.delete(userProject);
		
	}

	@Override
	public List<UserProjectDTO> getAllUserProject() {
		List<User> userList = userRepository.findByTenantId(TenantHolder.getTenantId());
		List<UserProjectDTO> userProjectDTOList = new ArrayList<UserProjectDTO>();
		for(User user:userList)
		{
			if(user!=null)
			{
				Hibernate.initialize(user.getUserProjects());
				Set<UserProject> userProjectSet = user.getUserProjects();
				System.err.println("userProjectSet :"+userProjectSet);
				for(UserProject UserProject:userProjectSet)
				{	
					UserProjectDTO UserProjectDTO= new UserProjectDTO();
					UserProjectDTO.setUser(UserProject.getUser());
					UserProjectDTO.setProject(UserProject.getProject());
					userProjectDTOList.add(UserProjectDTO);
				}
			}
			
		}
		
		System.err.println(userProjectDTOList);
		return userProjectDTOList;
	}
	
	
}
