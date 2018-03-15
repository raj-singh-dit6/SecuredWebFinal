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

import com.securedweb.dto.tenant.RoleDTO;
import com.securedweb.dto.tenant.StatusDTO;
import com.securedweb.service.tenant.RoleService;

@RestController
@RequestMapping("/role")
public class RoleController {
 
 @Autowired()
 private RoleService roleService;
 
 @PreAuthorize("hasRole('ADMIN') or hasRole('DBA')")
 @PostMapping(value="/add",consumes={MediaType.APPLICATION_JSON_VALUE},produces={MediaType.APPLICATION_JSON_VALUE})
 public StatusDTO addRole(@RequestBody RoleDTO role){
	 
	 return null;
 }
 
 @PreAuthorize("hasRole('ADMIN') or hasRole('DBA')")
 @PostMapping(value="/update",consumes={MediaType.APPLICATION_JSON_VALUE},produces={MediaType.APPLICATION_JSON_VALUE})
 public StatusDTO updateRole(@RequestBody RoleDTO role){
	 StatusDTO status = new StatusDTO();
     status.setMessage("Role details updated successfully");
     status.setStatus(200);
     return status;
 }

 @GetMapping(value="/all",produces={MediaType.APPLICATION_JSON_VALUE})
 public List<RoleDTO> getAllRoles(){
 return roleService.getAllRoles();
 
 }
 
 @PreAuthorize("hasRole('ADMIN') or hasRole('DBA')")
 @DeleteMapping(value="/delete/{roleId}",produces={MediaType.APPLICATION_JSON_VALUE})
 public StatusDTO deleteRole(@PathVariable("roleId") String roleId){
     StatusDTO status = new StatusDTO();
     status.setMessage("User Deleted Successfully");
     status.setStatus(200);
     return status;
 }
}