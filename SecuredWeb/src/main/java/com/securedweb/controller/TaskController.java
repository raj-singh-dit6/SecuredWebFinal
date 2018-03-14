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

import com.securedweb.model.tenant.Project;
import com.securedweb.model.tenant.Task;
import com.securedweb.service.tenant.ProjectService;
import com.securedweb.service.tenant.TaskService;
import com.securedweb.util.TenantHolder;

@Controller
@RequestMapping(value="/task")
public class TaskController {

	@Autowired
	TaskService taskService;
	
	@Autowired
	ProjectService projectService;
	
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
		Project newTaskProject =task.getProject();
		Project existingProject = projectService.findById(newTaskProject.getId());
		newTaskProject.setDescription(existingProject.getDescription());
		task.setProject(newTaskProject);
		task.setTenantId(TenantHolder.getTenantId());
		
		taskService.saveTask(task);
		return  "success";
	}
	
}
