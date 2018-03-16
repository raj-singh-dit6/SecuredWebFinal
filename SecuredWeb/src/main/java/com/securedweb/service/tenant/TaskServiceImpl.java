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
import com.securedweb.repository.tenant.TaskRepository;

@Service("taskService")
@Transactional("tenantTransactionManager")
public class TaskServiceImpl implements TaskService{

	@Autowired
	TaskRepository taskRepository;
	
	@Autowired
	ProjectService projectService;

	@Autowired
	TaskStatusService taskStatusService;
	
	
	@Override
	public TaskDTO addTask(TaskDTO task) {
		if(task.getProject().getId()!=null)
		 {	 
			Project taskProject =projectService.getProject(task.getProject().getId());
			task.setProject(taskProject);
		 }else if(task.getTaskStatus().getId()!=null)
		 {
			 TaskStatus taskStatus = taskStatusService.getTaskStatus(task.getTaskStatus().getId());
			 task.setTaskStatus(taskStatus);
		 }
		
		Task newTask = new Task();
		newTask.setName(task.getName());
		newTask.setDescription(task.getDescription());
		newTask.setProject(task.getProject());
		newTask.setTaskStatus(task.getTaskStatus());
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
				 Hibernate.initialize(task.getUserTasks());
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
	
	
}
