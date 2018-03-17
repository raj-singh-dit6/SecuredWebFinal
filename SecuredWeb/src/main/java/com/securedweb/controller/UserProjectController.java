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

import com.securedweb.dto.tenant.StatusDTO;
import com.securedweb.dto.tenant.UserProjectDTO;
import com.securedweb.service.tenant.UserProjectService;

@RestController
@RequestMapping("/userProject")
public class UserProjectController {
	
	
	 @Autowired()
	 private UserProjectService userProjectService;
	 
	 @GetMapping(value="/all",produces={MediaType.APPLICATION_JSON_VALUE})
	 public List<UserProjectDTO> getAllUserProject(){
		 return userProjectService.getAllUserProject();
	 }
	 
	 @PreAuthorize("hasRole('ADMIN') or hasRole('DBA')")
	 @PostMapping(value="/add",consumes={MediaType.APPLICATION_JSON_VALUE},produces={MediaType.APPLICATION_JSON_VALUE})
	 public StatusDTO addUserProject(@RequestBody UserProjectDTO userProject){
		 StatusDTO status = new StatusDTO();
			 userProjectService.addUserProject(userProject);
		     status.setMessage("Project assigned successfully");
		     status.setStatus(200);
		     return status;
	 }
	 
	 @PreAuthorize("hasRole('ADMIN') or hasRole('DBA')")
	 @DeleteMapping(value="/delete/{userProjectId}",produces={MediaType.APPLICATION_JSON_VALUE})
	 public StatusDTO deleteUSer(@PathVariable("userProjectId")Integer userProjectId){
		 userProjectService.deleteUserProject(userProjectId);
	     StatusDTO status = new StatusDTO();
	     status.setMessage("Project deassigned Successfully");
	     status.setStatus(200);
	     return status;
	 }

}
