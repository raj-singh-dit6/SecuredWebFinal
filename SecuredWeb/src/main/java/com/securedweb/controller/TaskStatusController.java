package com.securedweb.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.securedweb.model.tenant.TaskStatus;
import com.securedweb.service.tenant.TaskStatusService;
import com.securedweb.util.TenantHolder;

@Controller
@RequestMapping(value="/taskStatus")
public class TaskStatusController {

	
	@Autowired
	TaskStatusService taskStatusService;
	
	@GetMapping(value="/manageTaskStatus",produces= { MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public List<TaskStatus> manageTaskStatus()
	{
		List<TaskStatus> taskStatus = taskStatusService.findAll(); 
		return  taskStatus;
	}
	
	
	@PostMapping(value="/addTaskStatus")
	@ResponseBody
	public String addTaskStatus(@RequestBody TaskStatus taskStatus)
	{
		taskStatus.setTenantId(TenantHolder.getTenantId());
		System.err.println(taskStatus.toString());
		taskStatusService.saveTaskStatus(taskStatus);
		return  "success";
	}

}
