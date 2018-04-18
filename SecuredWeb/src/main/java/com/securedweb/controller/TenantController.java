package com.securedweb.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.securedweb.dto.master.TenantDTO;
import com.securedweb.service.master.TenantService;

@RestController
@RequestMapping("/tenant")
public class TenantController {
	
	private static final Logger LOG = LoggerFactory.getLogger(TenantController.class);

	@Autowired
	TenantService tenantService;
	
	 @GetMapping(value="/all",produces={MediaType.APPLICATION_JSON_VALUE})
	 public List<TenantDTO> getAllTenants(){
	 return tenantService.getAllTenants();
	 
	 }
	 
}
