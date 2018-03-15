package com.securedweb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.securedweb.dto.tenant.ProjectDTO;
import com.securedweb.dto.tenant.UserDTO;
import com.securedweb.service.tenant.ProjectService;
import com.securedweb.service.tenant.UserService;

@Controller
@RequestMapping(value="view")
public class ViewController {
	@Autowired
	UserService userService;
	
	@Autowired
	ProjectService projectService;

	@GetMapping(value="/ajaxPage/{pagetype}")
	public String ajaxPage(@PathVariable("pagetype") String pagetype) {
		String view="";
		if(pagetype.equals("user"))
				view= "ajax/userModalAjax";
		else if(pagetype.equals("project"))
			view ="ajax/projectModalAjax";
		
		return view;
	}	
}
