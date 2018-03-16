package com.securedweb.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.securedweb.dto.tenant.StatusDTO;
import com.securedweb.dto.tenant.TaskStatusDTO;
import com.securedweb.service.tenant.TaskStatusService;

@RestController
@RequestMapping(value="/taskStatus")
public class TaskStatusController {

	
	@Autowired
	TaskStatusService taskStatusService;
	
	@GetMapping(value="/all",produces= { MediaType.APPLICATION_JSON_VALUE })
	public List<TaskStatusDTO> getAllTaskStatus()
	{
		return taskStatusService.getAllTaskStatus();
		
	}
	
	@PostMapping(value="/addTaskStatus",consumes= { MediaType.APPLICATION_JSON_VALUE },produces= { MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public StatusDTO addTaskStatus(@RequestBody TaskStatusDTO taskStatus)
	{
		taskStatusService.addTaskStatus(taskStatus);
		StatusDTO status = new StatusDTO();
		return  status;
	}

}
