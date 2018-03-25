package com.securedweb.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.securedweb.dto.tenant.StatusDTO;
import com.securedweb.dto.tenant.TaskDTO;
import com.securedweb.service.tenant.TaskService;

@RestController
@RequestMapping(value="/task")
public class TaskController {
	
	private static final Logger LOG = LoggerFactory.getLogger(TaskController.class);

	@Autowired
	TaskService taskService;
	/**
	 * Returns a list of task records.
	 * @return
	 */
	@GetMapping(value="/all",produces= { MediaType.APPLICATION_JSON_VALUE })
	public List<TaskDTO> getAllTasks()
	{ 	
		return taskService.getAllTasks();
	}
	
	/**
	 * Adds a new task record.
	 * @param task
	 * @return
	 */
	@PreAuthorize("hasRole('ADMIN') or hasRole('DBA')")
	@PostMapping(value="/add",consumes= { MediaType.APPLICATION_JSON_VALUE },produces= { MediaType.APPLICATION_JSON_VALUE })
	public StatusDTO addtask(@RequestBody TaskDTO task)
	{
		taskService.addTask(task);
		StatusDTO status = new StatusDTO();
		status.setMessage("Task added successfully");
		status.setStatus(200);
		return status;
		
	}
	
	/**
	 * Returns a particular task record  for a given task id.
	 * @param taskId
	 * @return
	 */
	@GetMapping(value="/{taskId}",produces= { MediaType.APPLICATION_JSON_VALUE })
	public TaskDTO getTask(@PathVariable("taskId") Integer taskId)
	{
		return taskService.getTaskDTO(taskId);
	}
	
	/**
	 * Deletes a particular task record for a given task id.
	 * @param taskId
	 * @return
	 */
	@PreAuthorize("hasRole('ADMIN') or hasRole('DBA')")
	@DeleteMapping(value="/delete/{taskId}",produces= { MediaType.APPLICATION_JSON_VALUE })
	public StatusDTO deleteTask(@PathVariable("taskId") Integer taskId)
	{
		taskService.deleteTask(taskId);
		StatusDTO status = new StatusDTO();
		status.setMessage("Task Status deleted successfully");
		status.setStatus(200);
		return status;
	}
	/**
	 * Updates a particular task record for a given task id inside task dto.
	 * @param task
	 * @return
	 */
	 @PostMapping(value="/update",consumes={MediaType.APPLICATION_JSON_VALUE},produces={MediaType.APPLICATION_JSON_VALUE})
	 public StatusDTO updateTask(@RequestBody TaskDTO task){
		 taskService.updateTask(task);
		 StatusDTO status = new StatusDTO();
	     status.setMessage("Task details updated successfully");
	     status.setStatus(200);
	     return status;
	 }
	
}
