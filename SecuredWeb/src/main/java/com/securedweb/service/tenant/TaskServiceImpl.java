package com.securedweb.service.tenant;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.securedweb.dto.tenant.TaskDTO;
import com.securedweb.model.tenant.Project;
import com.securedweb.model.tenant.Task;
import com.securedweb.model.tenant.TaskStatus;
import com.securedweb.repository.tenant.ProjectRepository;
import com.securedweb.repository.tenant.TaskRepository;
import com.securedweb.repository.tenant.TaskStatusRepository;
import com.securedweb.util.TenantHolder;

@Service("taskService")
@Transactional("tenantTransactionManager")
public class TaskServiceImpl implements TaskService{

	@Autowired
	TaskRepository taskRepository;
	
	@Autowired
	ProjectRepository projectRepository;
	
	@Autowired
	TaskStatusRepository taskStatusRepository;
	
	@Autowired
	ProjectService projectService;

	@Autowired
	TaskStatusService taskStatusService;
	
	
	@Override
	public TaskDTO addTask(TaskDTO task) {
		Project taskProject =projectService.getProject(task.getProject().getId());
		TaskStatus taskStatus = taskStatusService.getTaskStatus(task.getTaskStatus().getId());
		
		Task newTask = new Task();
		newTask.setName(task.getName());
		newTask.setDescription(task.getDescription());
		newTask.setTenantId(TenantHolder.getTenantId());
		newTask.setProject(taskProject);
		newTask.setTaskStatus(taskStatus);
		
		taskProject.getTasks().add(newTask);
		taskStatus.getTasks().add(newTask);
		
		projectRepository.save(taskProject);
		taskStatusRepository.save(taskStatus);
		
		taskRepository.save(newTask);
		
		return task;
	}

	@Override
	public List<TaskDTO> getAllTasks() {
		
		List<Task> taskList = (List<Task>)taskRepository.findAll();
		List<TaskDTO> taskDTOList = new ArrayList<TaskDTO>();
		for(Task task:taskList)
		{
			 if(task!=null)
			 {
				 //Hibernate.initialize(task.getUserTasks());
				 Hibernate.initialize(task.getTaskStatus());
				 Hibernate.initialize(task.getProject());
				 	
				 TaskDTO taskDTO = new TaskDTO();
				 taskDTO.setId(task.getId());
				 taskDTO.setDescription(task.getDescription());
				 taskDTO.setName(task.getName());
				 taskDTO.setProject(task.getProject());
				 taskDTO.setTaskStatus(task.getTaskStatus());
				 taskDTOList.add(taskDTO);
			 }	 
		}
		return taskDTOList;
	}

	@Override
	public void deleteTask(Integer taskId) {
		taskRepository.deleteByIdAndTenantId(taskId, TenantHolder.getTenantId());
	}

	@Override
	public TaskDTO getTaskDTO(Integer taskId) {
		Task task=taskRepository.findByIdAndTenantId(taskId,TenantHolder.getTenantId());
		TaskDTO taskDTO= new TaskDTO();
		taskDTO.setId(task.getId());
		taskDTO.setDescription(task.getDescription());
		taskDTO.setName(task.getName());
		taskDTO.setProject(task.getProject());
		taskDTO.setTaskStatus(task.getTaskStatus());
		
		return taskDTO;
	}

	@Override
	public TaskDTO updateTask(TaskDTO task) {
		Task updateTask = taskRepository.findByIdAndTenantId(task.getId(), TenantHolder.getTenantId());
		
		Project oldProject =updateTask.getProject();
		TaskStatus oldTaskStatus = updateTask.getTaskStatus();
		
		oldProject.getTasks().remove(updateTask);
		oldTaskStatus.getTasks().remove(updateTask);
		
		Project newProject =	projectRepository.findByIdAndTenantId(task.getProject().getId(), TenantHolder.getTenantId());
		TaskStatus newTaskStatus =  taskStatusRepository.findByIdAndTenantId(task.getTaskStatus().getId(), TenantHolder.getTenantId());
		
		updateTask.setName(task.getName());
		updateTask.setDescription(task.getDescription());
		updateTask.setTenantId(TenantHolder.getTenantId());
		updateTask.setProject(newProject);
		updateTask.setTaskStatus(newTaskStatus);

		newProject.getTasks().add(updateTask);
		newTaskStatus.getTasks().add(updateTask);
		
		projectRepository.save(newProject);
		taskStatusRepository.save(newTaskStatus);
		
		return task;
	
	}
}
