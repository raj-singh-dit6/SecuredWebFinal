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
import com.securedweb.dto.tenant.UserDTO;
import com.securedweb.service.tenant.RoleService;
import com.securedweb.service.tenant.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
 
 @Autowired()
 private UserService userService;
 
 @Autowired
 RoleService roleService;
 
 @GetMapping(value="/{ssoId}",produces={MediaType.APPLICATION_JSON_VALUE})
 public UserDTO getUser(@PathVariable("ssoId") String ssoId){
	 return userService.getUser(ssoId);
 }
 
 @PostMapping(value="/add",consumes={MediaType.APPLICATION_JSON_VALUE},produces={MediaType.APPLICATION_JSON_VALUE})
 public StatusDTO addUser(@RequestBody UserDTO user){
	 StatusDTO status = new StatusDTO();
	 if(userService.isUserSSOUnique(user.getSsoId()))
	{
		 userService.addUser(user);
	     status.setMessage("User added successfully");
	     status.setStatus(200);
	     return status;
	}else {
		status.setMessage("Please enter a unique SSO ID");
		return status;
	}		 
 }
 
 @PreAuthorize("hasRole('ADMIN') or hasRole('DBA')")
 @PostMapping(value="/update",consumes={MediaType.APPLICATION_JSON_VALUE},produces={MediaType.APPLICATION_JSON_VALUE})
 public StatusDTO updateUser(@RequestBody UserDTO user){
	 userService.updateUser(user);
	 StatusDTO status = new StatusDTO();
     status.setMessage("User details updated successfully");
     status.setStatus(200);
     return status;
 }

 @GetMapping(value="/all",produces={MediaType.APPLICATION_JSON_VALUE})
 public List<UserDTO> getAllUsers(){
 return userService.getAllUsers();
 
 }
 
 @PreAuthorize("hasRole('ADMIN') or hasRole('DBA')")
 @DeleteMapping(value="/delete/{ssoId}",produces={MediaType.APPLICATION_JSON_VALUE})
 public StatusDTO deleteUSer(@PathVariable("ssoId") String ssoId){
	 userService.deleteUser(ssoId);
     StatusDTO status = new StatusDTO();
     status.setMessage("User Deleted Successfully");
     status.setStatus(200);
     return status;
 }
}