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
import com.securedweb.dto.tenant.TaskStatusDTO;
import com.securedweb.service.tenant.TaskStatusService;

@RestController
@RequestMapping(value="/taskStatus")
public class TaskStatusController {
	
	private static final Logger LOG = LoggerFactory.getLogger(TaskStatusController.class);
	
	@Autowired
	TaskStatusService taskStatusService;
	
	@GetMapping(value="/all",produces= { MediaType.APPLICATION_JSON_VALUE })
	public List<TaskStatusDTO> getAllTaskStatus()
	{
		return taskStatusService.getAllTaskStatus();
		
	}
	
	 @PreAuthorize("hasRole('ADMIN') or hasRole('DBA')")
	 @PostMapping(value="/update",consumes={MediaType.APPLICATION_JSON_VALUE},produces={MediaType.APPLICATION_JSON_VALUE})
	 public StatusDTO updateTaskStatus(@RequestBody TaskStatusDTO taskStatus){
		 taskStatusService.updateTaskStatus(taskStatus);
		 StatusDTO status = new StatusDTO();
	     status.setMessage("Task Status details updated successfully");
	     status.setStatus(200);
	     return status;
	 }
	
	@PreAuthorize("hasRole('ADMIN') or hasRole('DBA')")
	@PostMapping(value="/add",consumes= { MediaType.APPLICATION_JSON_VALUE },produces= { MediaType.APPLICATION_JSON_VALUE })
	public StatusDTO addTaskStatus(@RequestBody TaskStatusDTO taskStatus)
	{
		taskStatusService.addTaskStatus(taskStatus);
		StatusDTO status = new StatusDTO();
		status.setMessage("Task Status added successfully");
	    status.setStatus(200);
		return  status;
	}
	
	@GetMapping(value="/{taskStatusId}",produces= { MediaType.APPLICATION_JSON_VALUE })
	public TaskStatusDTO getTaskStatus(@PathVariable("taskStatusId") Integer taskStatusId)
	{
		return taskStatusService.getTaskStatusDTO(taskStatusId);
	}
	
	@PreAuthorize("hasRole('ADMIN') or hasRole('DBA')")
	@DeleteMapping(value="/delete/{taskStatusId}",produces= { MediaType.APPLICATION_JSON_VALUE })
	public StatusDTO deleteTaskStatus(@PathVariable(value="taskStatusId") Integer taskStatusId)
	{
		taskStatusService.deleteTaskStatus(taskStatusId);
		StatusDTO status = new StatusDTO();
		status.setMessage("Task Status deleted successfully");
		status.setStatus(200);
		return status;
		
	}

}
