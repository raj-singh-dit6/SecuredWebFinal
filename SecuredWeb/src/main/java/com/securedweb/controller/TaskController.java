package com.securedweb.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
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

	@Autowired
	TaskService taskService;
	
	@GetMapping(value="/all",produces= { MediaType.APPLICATION_JSON_VALUE })
	public List<TaskDTO> getAllTasks()
	{ 	
		return taskService.getAllTasks();
	}
	
	
	@PostMapping(value="/add",consumes= { MediaType.APPLICATION_JSON_VALUE },produces= { MediaType.APPLICATION_JSON_VALUE })
	public StatusDTO addtask(@RequestBody TaskDTO task)
	{
		taskService.addTask(task);
		StatusDTO status = new StatusDTO();
		status.setMessage("Task added successfully");
		status.setStatus(200);
		return status;
		
	}
	
}
