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

import com.securedweb.model.tenant.Task;
import com.securedweb.service.tenant.ProjectService;
import com.securedweb.service.tenant.TaskService;

@Controller
@RequestMapping(value="/task")
public class TaskController {

	@Autowired
	TaskService taskService;
	
	@GetMapping(value="/manageTasks",produces= { MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public List<Task> manageTasks()
	{ 	
		List<Task> tasks = taskService.findAll();
		return tasks;
	}
	
	
	@PostMapping(value="/addtask")
	@ResponseBody
	public String addtask(@RequestBody Task task)
	{
		return  "success";
	}
	
}
