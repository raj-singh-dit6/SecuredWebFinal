package com.securedweb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.securedweb.dto.tenant.UserDTO;
import com.securedweb.service.tenant.UserService;

@RestController
@RequestMapping(value="view")
public class ViewController {
	@Autowired
	UserService userService;

	@PostMapping(value="/editAjaxPage/{pagetype}/{ssoId}")
	public ModelAndView editAjaxPage(@PathVariable("pagetype") String pagetype,@PathVariable("ssoId") String ssoId, Model model) {
		ModelAndView modelAndView=null;
		if(pagetype.equals("user")) {
				UserDTO user = userService.getUser(ssoId);
				model.addAttribute( "user", user );
				modelAndView =new ModelAndView("ajax/userEditAjax");
		}
		else if(pagetype.equals("project"))
		{
			
		}
		return modelAndView;
	}
	
	@PostMapping(value="/addAjaxPage/{pagetype}")
	public ModelAndView editAjax(@PathVariable("pagetype") String pagetype,Model model) {
		ModelAndView modelAndView=null;
		if(pagetype.equals("user")) {
				UserDTO user = new UserDTO();
				model.addAttribute( "user", user );
				modelAndView =new ModelAndView("ajax/userAddAjax");
		}
		else if(pagetype.equals("project"))
		{
			
		}
		return modelAndView;
	}
}
