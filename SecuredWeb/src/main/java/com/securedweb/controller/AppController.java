package com.securedweb.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.authentication.AuthenticationTrustResolver;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.rememberme.PersistentTokenBasedRememberMeServices;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.securedweb.model.tenant.Project;
import com.securedweb.model.tenant.Role;
import com.securedweb.model.tenant.TaskStatus;
import com.securedweb.repository.tenant.UserRepository;
import com.securedweb.service.master.TenantService;
import com.securedweb.service.tenant.ProjectService;
import com.securedweb.service.tenant.RoleService;
import com.securedweb.service.tenant.TaskStatusService;
import com.securedweb.service.tenant.UserService;

@Controller
@RequestMapping("/")
@SessionAttributes("roles")
public class AppController {

	@Autowired
    UserService userService;
    
	
	@Autowired
	UserRepository userRepository;
	
    @Autowired
    RoleService roleService;
    
    @Autowired
    TenantService tenantService;
    
    @Autowired
    ProjectService projectService;
     
    
    @Autowired
    TaskStatusService taskStatusService;
     
    @Autowired
    MessageSource messageSource;
 
    @Autowired
    PersistentTokenBasedRememberMeServices persistentTokenBasedRememberMeServices;
     
    @Autowired
    AuthenticationTrustResolver authenticationTrustResolver;

    
    
    /**
     * This method handles login GET requests.
     * If users is already logged-in and tries to goto login page again, will be redirected to list page.
     */
    @GetMapping(value = {"/","/login","/home"})
    public String loginPage() {
        if (isCurrentAuthenticationAnonymous()) {
            return "login";
        } else {
        	String tenantId=userRepository.findBySsoId(getPrincipal()).getTenantId();
        	System.err.println("Controller : "+tenantId);
        	if(tenantId.equals("1"))
        		return "forward:/Dashboard";
        	else
        		return "forward:/Dashboard?"+"tenantId="+tenantId;
        }
    }
    
    @GetMapping(value = "/Dashboard")
    public String getDashboard (Model model) {
    	String tenantId=userRepository.findBySsoId(getPrincipal()).getTenantId();
    	String tenantName= tenantService.findById(tenantId).getTenantName();
        System.err.println(tenantName);
    	model.addAttribute("edit", false);
        model.addAttribute("list", false);
        model.addAttribute("tenantName", tenantName);
        model.addAttribute("tenantId", tenantId);
        model.addAttribute("home", true);
        model.addAttribute("loggedinuser", getPrincipal());
    	return "Dashboard";
    }
 
    /**
     * This method handles logout requests.
     * Toggle the handlers if you are RememberMe functionality is useless in your app.
     */
    @GetMapping(value="/logout")
    public String logoutPage (HttpServletRequest request, HttpServletResponse response){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){    
            //new SecurityContextLogoutHandler().logout(request, response, auth);
            persistentTokenBasedRememberMeServices.logout(request, response, auth);
            SecurityContextHolder.getContext().setAuthentication(null);
        }
        return "redirect:/login?logout";
    }

    @GetMapping(value = "/Access_Denied")
    public String accessDeniedPage(ModelMap model) {
        model.addAttribute("loggedinuser", getPrincipal());
        return "accessDenied";
    }

    @ModelAttribute("roles")
    public List<Role> initializeRoles() {
        return roleService.findAll();
    }
    
    @ModelAttribute("status")
    public List<TaskStatus> initializeProjects() {
        return taskStatusService.findAll();
    }
    
    
    @ModelAttribute("projects")
    public List<Project> initializeTasks() {
        return projectService.findAll();
    }
    

    /**
     * This method returns the principal[user-name] of logged-in user.
     */
    private String getPrincipal(){
        String userName = null;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
 
        if (principal instanceof UserDetails) {
            userName = ((UserDetails)principal).getUsername();
        } else {
            userName = principal.toString();
        }
        return userName;
    }
     
    /**
     * This method returns true if users is already authenticated [logged-in], else false.
     */
    private boolean isCurrentAuthenticationAnonymous() {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        System.out.println(authentication.getPrincipal());
        return authenticationTrustResolver.isAnonymous(authentication);
    }
    
 
 
}
