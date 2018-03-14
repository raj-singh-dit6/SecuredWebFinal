package com.securedweb.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.securedweb.model.tenant.Project;
import com.securedweb.model.tenant.User;
import com.securedweb.service.tenant.ProjectService;
import com.securedweb.util.TenantHolder;

@Controller
@RequestMapping(value="/project")
public class ProjectController {

	@Autowired
	ProjectService projectService;
	
    @GetMapping(value = "/manageProjects",produces = { MediaType.APPLICATION_JSON_VALUE })
    @ResponseBody
    public List<Project> manageProjects() {
		List<Project> projects = projectService.findAll();
		System.err.println(projects);
    	return projects;
    }
 
    @PostMapping(value = { "/addProject" })
    @ResponseBody
    public String addProject(@RequestBody Project project) {
    	project.setTenantId(TenantHolder.getTenantId());
    	projectService.save(project);
    	return "success";
    }
	
}