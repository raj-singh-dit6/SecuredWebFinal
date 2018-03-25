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
import com.securedweb.dto.tenant.UserTaskDTO;
import com.securedweb.service.tenant.UserTaskService;

@RestController
@RequestMapping("/userTask")
public class UserTaskController {
	
	
	 @Autowired()
	 private UserTaskService userTaskService;
	 
	
	 @GetMapping(value="/{userTaskId}",produces={MediaType.APPLICATION_JSON_VALUE})
	 public UserTaskDTO getAllUserTask(@PathVariable("userTaskId")Integer userTaskId){
		 return userTaskService.getUserTask(userTaskId);
	 }
	 
	 @GetMapping(value="/all",produces={MediaType.APPLICATION_JSON_VALUE})
	 public List<UserTaskDTO> getAllUserTask(){
		 return userTaskService.getAllUserTask();
	 }
	 
	 @PreAuthorize("hasRole('ADMIN') or hasRole('DBA')")
	 @PostMapping(value="/add",consumes={MediaType.APPLICATION_JSON_VALUE},produces={MediaType.APPLICATION_JSON_VALUE})
	 public StatusDTO addUserTask(@RequestBody UserTaskDTO userTask){
		 StatusDTO status = new StatusDTO();
		 userTaskService.addUserTask(userTask);
		     status.setMessage("Task assigned successfully");
		     status.setStatus(200);
		     return status;
	 }
	 
	 @PreAuthorize("hasRole('ADMIN') or hasRole('DBA')")
	 @DeleteMapping(value="/delete/{userTaskId}",produces={MediaType.APPLICATION_JSON_VALUE})
	 public StatusDTO deleteUserTask(@PathVariable("userTaskId")Integer userTaskId){
		 userTaskService.deleteUserTask(userTaskId);
	     StatusDTO status = new StatusDTO();
	     status.setMessage("Task deassigned Successfully");
	     status.setStatus(200);
	     return status;
	 }

	 @PreAuthorize("hasRole('USER')")
	 @GetMapping(value="/load/tasks",produces={MediaType.APPLICATION_JSON_VALUE})
	 public List<UserTaskDTO> loadUserTasks(){
		 return userTaskService.getAllUserTaskBySsoId();
	 }
	 
	 @PreAuthorize("hasRole('USER')")
	 @PostMapping(value="/update",consumes={MediaType.APPLICATION_JSON_VALUE},produces={MediaType.APPLICATION_JSON_VALUE})
	 public StatusDTO updateUserTask(@RequestBody UserTaskDTO userTask){
		 StatusDTO status = new StatusDTO();
		 userTaskService.updateUserTask(userTask);
		 status.setMessage("User task update successfully");
	     status.setStatus(200);
	     return status;
	 }
}
