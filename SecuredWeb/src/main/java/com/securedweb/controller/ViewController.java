package com.securedweb.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.securedweb.repository.tenant.PasswordResetTokenRepository;
import com.securedweb.service.tenant.ProjectService;
import com.securedweb.service.tenant.UserService;

@Controller
@RequestMapping(value="view")
public class ViewController {
	
	private static final Logger LOG = LoggerFactory.getLogger(ViewController.class);	
	
	@Autowired
	UserService userService;
	
	@Autowired
	ProjectService projectService;
	
	@Autowired 
	private PasswordResetTokenRepository tokenRepository;

	@GetMapping(value="/ajaxPage/{pagetype}")
	public String ajaxPage(@PathVariable("pagetype") String pagetype) {
		System.out.println("view controller");
		String view="";
		if(pagetype.equals("user"))
				view= "ajax/userModalAjax";
		else if(pagetype.equals("project"))
			view ="ajax/projectModalAjax";
		else if(pagetype.equals("userProject"))
			view ="ajax/assignProjectModalAjax";
		else if(pagetype.equals("task"))
			view ="ajax/taskModalAjax";
		else if(pagetype.equals("taskStatus"))
			view ="ajax/taskStatusModalAjax";
		else if(pagetype.equals("userTask"))
			view ="ajax/assignTaskModalAjax";
		else if(pagetype.equals("userTaskByUser"))
			view ="ajax/assignTaskModalAjax";
		else if(pagetype.equals("updateTaskByUser"))
			view ="ajax/updateTaskByUserModalAjax";
		else if(pagetype.equals("registerUser"))
			view ="ajax/newUserModalAjax";
		else if(pagetype.equals("forgotPassword"))
			view ="ajax/forgotPasswordModalAjax";
		else if(pagetype.equals("projectDocuments"))
			view ="ajax/documentsModalAjax";
		else if(pagetype.equals("uploadDocuments"))
			view ="ajax/uploadDocumentsModalAjax";
		else if(pagetype.equals("changePassword"))
			view ="ajax/changePasswordModalAjax";
		
		return view;
	}

}
