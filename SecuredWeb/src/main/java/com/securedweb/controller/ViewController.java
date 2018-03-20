package com.securedweb.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.securedweb.model.tenant.PasswordResetToken;
import com.securedweb.repository.tenant.PasswordResetTokenRepository;
import com.securedweb.service.tenant.ProjectService;
import com.securedweb.service.tenant.UserService;

@Controller
@RequestMapping(value="view")
public class ViewController {
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
		return view;
	}

}
