package com.securedweb.controller;

import java.util.List;

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

import com.securedweb.dto.tenant.ProjectDTO;
import com.securedweb.dto.tenant.StatusDTO;
import com.securedweb.service.tenant.ProjectService;
import com.securedweb.util.TenantHolder;

@RestController
@RequestMapping("/project")
public class ProjectController {
 
 @Autowired()
 private ProjectService projectService;
 
 @GetMapping(value="/{projectId}",produces={MediaType.APPLICATION_JSON_VALUE})
 public ProjectDTO getUser(@PathVariable("projectId") String projectId){
	 return projectService.getProjectDTO(Integer.parseInt(projectId));
 }
 
 @PreAuthorize("hasRole('ADMIN') or hasRole('DBA')")
 @PostMapping(value="/add",consumes={MediaType.APPLICATION_JSON_VALUE},produces={MediaType.APPLICATION_JSON_VALUE})
 public StatusDTO addProject(@RequestBody ProjectDTO project){
	 StatusDTO status = new StatusDTO();
	 if(projectService.isProjectNameUnique(project.getName(),TenantHolder.getTenantId()))
	{
		 projectService.addProject(project);
	     status.setMessage("Project added successfully");
	     status.setStatus(200);
	     return status;
	}else {
		status.setMessage("Please enter a unique project name.");
		return status;
	}		 
 }
 
 @PreAuthorize("hasRole('ADMIN') or hasRole('DBA')")
 @PostMapping(value="/update",consumes={MediaType.APPLICATION_JSON_VALUE},produces={MediaType.APPLICATION_JSON_VALUE})
 public StatusDTO updateUser(@RequestBody ProjectDTO project){
	 projectService.updateProject(project);
	 StatusDTO status = new StatusDTO();
     status.setMessage("Project details updated successfully");
     status.setStatus(200);
     return status;
 }

 @GetMapping(value="/all",produces={MediaType.APPLICATION_JSON_VALUE})
 public List<ProjectDTO> getAllProjects(){
 return projectService.getAllProjects();
 }
 
 @PreAuthorize("hasRole('ADMIN') or hasRole('DBA')")
 @DeleteMapping(value="/delete/{projectId}",produces={MediaType.APPLICATION_JSON_VALUE})
 public StatusDTO deleteUser(@PathVariable("projectId") Integer projectId){
	 projectService.deleteProject(projectId);
     StatusDTO status = new StatusDTO();
     status.setMessage("Project Deleted Successfully");
     status.setStatus(200);
     return status;
 }
}