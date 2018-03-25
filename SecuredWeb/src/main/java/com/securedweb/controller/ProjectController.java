package com.securedweb.controller;

import java.io.IOException;
import java.util.List;
import java.util.Set;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.securedweb.dto.tenant.ProjectDTO;
import com.securedweb.dto.tenant.StatusDTO;
import com.securedweb.dto.tenant.TaskDTO;
import com.securedweb.dto.tenant.UserDTO;
import com.securedweb.service.tenant.ProjectService;
import com.securedweb.util.TenantHolder;

@RestController
@RequestMapping("/project")
public class ProjectController {
 
private static final Logger LOG = LoggerFactory.getLogger(ProjectController.class);
	
 @Autowired
 private ProjectService projectService;
 
 /**
  * This method returns a particular project dto for a given project id.
  * @param projectId
  * @return
  */
 @GetMapping(value="/{projectId}",produces={MediaType.APPLICATION_JSON_VALUE})
 public ProjectDTO getUser(@PathVariable("projectId") Integer projectId){
	 return projectService.getProjectDTO(projectId);
 }
 
 /**
  * Adds new project record.
  * @param project
  * @return
 * @throws IOException 
 * @throws JsonMappingException 
 * @throws JsonParseException 
  */
 @PreAuthorize("hasRole('ADMIN') or hasRole('DBA')")
 @PostMapping(value="/add",produces={MediaType.APPLICATION_JSON_VALUE},consumes={MediaType.ALL_VALUE})
 public StatusDTO addProject(@RequestParam("project")String project, @RequestParam("file") MultipartFile file) throws JsonParseException, JsonMappingException, IOException{
	 System.out.println(project);
	 ObjectMapper objectMapper = new ObjectMapper();
	 ProjectDTO projectDTO 	= objectMapper.readValue(project,ProjectDTO.class);
	 StatusDTO status = new StatusDTO();
	 if(projectService.isProjectNameUnique(projectDTO.getName(),TenantHolder.getTenantId()))
	{
		if (file!=null && !file.getOriginalFilename().isEmpty()) {
			projectDTO.setProjectFile(file.getBytes());
			System.err.println(file.getOriginalFilename());
	      }
		 projectService.addProject(projectDTO);
	     status.setMessage("Project added successfully");
	     status.setStatus(200);
	     return status;
	}else {
		status.setMessage("Please enter a unique project name.");
		return status;
	} 
	 
 }
 
 /**
  * Updates  a particular project record for a given project id inside project dto.
  * @param project
  * @return
  */
 @PreAuthorize("hasRole('ADMIN') or hasRole('DBA')")
 @PostMapping(value="/update",consumes={MediaType.APPLICATION_JSON_VALUE},produces={MediaType.APPLICATION_JSON_VALUE})
 public StatusDTO updateProject(@RequestBody ProjectDTO project){
	 projectService.updateProject(project);
	 StatusDTO status = new StatusDTO();
     status.setMessage("Project details updated successfully");
     status.setStatus(200);
     return status;
 }

 /**
  * Returns a list of project.
  * @return
  */
 @GetMapping(value="/all",produces={MediaType.APPLICATION_JSON_VALUE})
 public List<ProjectDTO> getAllProjects(){
 return projectService.getAllProjects();
 }
 
 /**
  * Deletes a particular project record for a given project id.
  * @param projectId
  * @return
  */
 @PreAuthorize("hasRole('ADMIN') or hasRole('DBA')")
 @DeleteMapping(value="/delete/{projectId}",produces={MediaType.APPLICATION_JSON_VALUE})
 public StatusDTO deleteUser(@PathVariable("projectId") Integer projectId){
	 projectService.deleteProject(projectId);
     StatusDTO status = new StatusDTO();
     status.setMessage("Project Deleted Successfully");
     status.setStatus(200);
     return status;
 }
 /**
  * Returns a set of task records as task dto for a given project id.
  * @param projectId
  * @return
  */
 @GetMapping(value="/load/task/{projectId}",produces={MediaType.APPLICATION_JSON_VALUE})
 public Set<TaskDTO> loadAllTasksByProjectID(@PathVariable("projectId") Integer projectId){
 return projectService.loadAllTasksByProjectID(projectId);
 }
 /**
  *  Returns a set of user records as user dto for a given project id.
  * @param projectId
  * @return
  */
 @GetMapping(value="/load/user/{projectId}",produces={MediaType.APPLICATION_JSON_VALUE})
 public Set<UserDTO> loadAllUsersByProjectID(@PathVariable("projectId") Integer projectId){
 return projectService.loadAllUsersByProjectID(projectId);
 }
 
}