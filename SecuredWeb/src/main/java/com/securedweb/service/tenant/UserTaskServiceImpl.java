package com.securedweb.service.tenant;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.securedweb.dto.tenant.UserTaskDTO;
import com.securedweb.model.tenant.Project;
import com.securedweb.model.tenant.Task;
import com.securedweb.model.tenant.TaskStatus;
import com.securedweb.model.tenant.User;
import com.securedweb.model.tenant.UserTask;
import com.securedweb.repository.tenant.TaskRepository;
import com.securedweb.repository.tenant.TaskStatusRepository;
import com.securedweb.repository.tenant.UserRepository;
import com.securedweb.repository.tenant.UserTaskRepository;
import com.securedweb.util.TenantHolder;

@Service("userTaskService")
@Transactional("tenantTransactionManager")
public class UserTaskServiceImpl implements UserTaskService {

	@Autowired
	UserTaskRepository userTaskRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	UserService userService;
	
	@Autowired
	ProjectService projectService;
	
	@Autowired
	TaskService taskService;

	@Autowired
	TaskRepository taskRepository;
	
	@Autowired
	TaskStatusService taskStatusService;
	
	@Override
	public List<UserTaskDTO> getAllUserTask() {
		
		List<UserTask> userTaskList = userTaskRepository.findByTenantId(TenantHolder.getTenantId());
		List<UserTaskDTO> userTaskDTOList = new ArrayList<UserTaskDTO>();
		
		for(UserTask userTask: userTaskList)
		{
			if(userTask!=null)
			{
				Hibernate.initialize(userTask.getUser());
				Hibernate.initialize(userTask.getTask());
				
				User user = userTask.getUser();
				Task task =  userTask.getTask();
				Project project = task.getProject();
				
				UserTaskDTO userTaskDTO = new UserTaskDTO();
				userTaskDTO.setId(userTask.getId());
				userTaskDTO.setUser(user);
				userTaskDTO.setProject(project);
				userTaskDTO.setTask(task);
				userTaskDTOList.add(userTaskDTO);
			}
		}
		return userTaskDTOList;
	}
	
	@Override
	public List<UserTaskDTO> getAllUserTaskBySsoId() {
		List<UserTaskDTO> userTaskDTOList = new ArrayList<UserTaskDTO>(); 
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		 if (principal instanceof UserDetails) {
			 String  ssoId = ((UserDetails)principal).getUsername();
			 System.err.println("User ssoId :"+ssoId);
				 	
				
			User user = userRepository.findBySsoIdAndTenantId(ssoId, TenantHolder.getTenantId());
			Set<UserTask> userTaskSet = userTaskRepository.findByUserAndTenantId(user,TenantHolder.getTenantId());
			for(UserTask userTask: userTaskSet)
			{
					Hibernate.initialize(userTask.getTask());
					
					Task task =  userTask.getTask();
					Project project = task.getProject();

					UserTaskDTO userTaskDTO = new UserTaskDTO();
					userTaskDTO.setId(userTask.getId());
					userTaskDTO.setUser(user);
					userTaskDTO.setProject(project);
					userTaskDTO.setTask(task);
					userTaskDTOList.add(userTaskDTO);
			}
		}
		return userTaskDTOList;
	}

	@Override
	public UserTaskDTO addUserTask(UserTaskDTO userTask) {
		
		User user = userService.getUser(userTask.getUser().getId());
		Task task = taskService.getTask(userTask.getTask().getId());
		
		UserTask newUserTask = new UserTask();
		newUserTask.setUser(user);
		newUserTask.setTask(task);
		newUserTask.setTenantId(TenantHolder.getTenantId());
		user.getUserTasks().add(newUserTask);
		System.err.println(user.getFirstName() + " : TOTAL TASKS : "+ user.getUserTasks().size());
		return userTask;
	}

	@Override
	public void deleteUserTask(Integer userTaskId) {
		
		UserTask userTask=userTaskRepository.findByIdAndTenantId(userTaskId,TenantHolder.getTenantId());
		System.err.println(userTask);
		User user = userService.getUser(userTask.getUser().getId());
		user.getUserTasks().remove(userTask);
		
		System.err.println(user.getFirstName() + " : TOTAL TASKS : "+ user.getUserTasks().size());
	}

	@Override
	public UserTaskDTO getUserTask(Integer userTaskId) {
		
		UserTask userTask = userTaskRepository.findByIdAndTenantId(userTaskId, TenantHolder.getTenantId());
		UserTaskDTO userTaskDTO = new UserTaskDTO();
		if(userTask!=null)
		{
			userTaskDTO.setId(userTask.getId());
			userTaskDTO.setTask(userTask.getTask());
			userTaskDTO.setProject(userTask.getTask().getProject());
		}
		return userTaskDTO;
	}

	@Override
	public UserTaskDTO updateUserTask(UserTaskDTO userTask) {
		UserTask updateUserTask = userTaskRepository.findByIdAndTenantId(userTask.getId(), TenantHolder.getTenantId());
		Task task = taskService.getTask(updateUserTask.getTask().getId());
		TaskStatus taskStatus = taskStatusService.getTaskStatus(userTask.getTask().getTaskStatus().getId());
				
		task.setDescription(userTask.getTask().getDescription());
		task.setTaskStatus(taskStatus);
		updateUserTask.setTask(task);

		return userTask;
		
	}

	
}
