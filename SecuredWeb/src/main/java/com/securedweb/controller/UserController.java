package com.securedweb.controller;

import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationTrustResolver;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.rememberme.PersistentTokenBasedRememberMeServices;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.securedweb.model.tenant.Role;
import com.securedweb.model.tenant.User;
import com.securedweb.model.tenant.UserJSONResponse;
import com.securedweb.service.tenant.RoleService;
import com.securedweb.service.tenant.UserService;
import com.securedweb.util.TenantHolder;

@Controller
@RequestMapping("/")
@SessionAttributes("roles")
public class UserController {

	@Autowired
    UserService userService;
     
    @Autowired
    RoleService roleService;
     
    @Autowired
    MessageSource messageSource;
 
    @Autowired
    PersistentTokenBasedRememberMeServices persistentTokenBasedRememberMeServices;
     
    @Autowired
    AuthenticationTrustResolver authenticationTrustResolver;
     
    @GetMapping(value = "/list",produces = { MediaType.APPLICATION_JSON_VALUE })
    @ResponseBody
    public List<User> listUsers() {
		List<User> users = userService.findAllUser();
		System.err.println(users);
    	return users;
    }
 
    @GetMapping(value = { "/newuser" })
    public String newUser(ModelMap model) {
    	User user = new User();
        model.addAttribute("user", user);
        model.addAttribute("edit", false);
        model.addAttribute("loggedinuser", getPrincipal());
        return "Dashboard";
    }
    
    @PostMapping(value = "/addUser", produces = { MediaType.APPLICATION_JSON_VALUE })
    @ResponseBody
    public String addUser(@RequestBody User user) {
    	System.out.println(user.getUserRoles().size());
    	
    	user.setTenantId(TenantHolder.getTenantId());
    	System.err.println(user.toString());
    	userService.saveUser(user);
       return "success";
    }
 
    /**
     * This method will be called on form submission, handling POST request for
     * saving user in database. It also validates the user input
     */
    @PostMapping(value = { "/newuser" })
    public String saveUser(@Valid User user, BindingResult result,
            ModelMap model) {
 
        if (result.hasErrors()) {
            return "ajax/registration";
        }
 
        /*
         * Preferred way to achieve uniqueness of field [sso] should be implementing custom @Unique annotation 
         * and applying it on field [sso] of Model class [User].
         * 
         * Below mentioned peace of code [if block] is to demonstrate that you can fill custom errors outside the validation
         * framework as well while still using internationalized messages.
         * 
         */
        if(!userService.isUserSSOUnique(user.getId(), user.getSsoId())){
            FieldError ssoError =new FieldError("user","ssoId",messageSource.getMessage("non.unique.ssoId", new String[]{user.getSsoId()}, Locale.getDefault()));
            result.addError(ssoError);
            return "ajax/registration";
        }
         
        userService.saveUser(user);
 
        model.addAttribute("success", "User " + user.getFirstName() + " "+ user.getLastName() + " registered successfully");
        model.addAttribute("loggedinuser", getPrincipal());
        //return "success";
        return "registrationSuccess";
    }
 
 
    /**
     * This method will provide the medium to update an existing user.
     */
    @GetMapping(value = { "/edit-user" })
    public String editUser(@PathVariable String ssoId, ModelMap model) {
        System.out.println(ssoId);
    	User user = userService.findBySSO(ssoId);
        model.addAttribute("user", user);
        model.addAttribute("edit", true);
        model.addAttribute("loggedinuser", getPrincipal());
        return "ajax/registration";
    }
     
    /**
     * This method will be called on form submission, handling POST request for
     * updating user in database. It also validates the user input
     */
    @PostMapping(value = { "/edit-user-{ssoId}" })
    public String updateUser(@Valid User user, BindingResult result,
            ModelMap model, @PathVariable String ssoId) {
 
        if (result.hasErrors()) {
            return "ajax/registration";
        }
        /*//Uncomment below 'if block' if you WANT TO ALLOW UPDATING SSO_ID in UI which is a unique key to a User.
        if(!userService.isUserSSOUnique(user.getId(), user.getSsoId())){
            FieldError ssoError =new FieldError("user","ssoId",messageSource.getMessage("non.unique.ssoId", new String[]{user.getSsoId()}, Locale.getDefault()));
            result.addError(ssoError);
            return "registration";
        }*/
        userService.updateUser(user);
        model.addAttribute("success", "User " + user.getFirstName() + " "+ user.getLastName() + " updated successfully");
        model.addAttribute("loggedinuser", getPrincipal());
        return "registrationSuccess";
    }
 
     
    /**
     * This method will delete an user by it's SSOID value.
     */
    @GetMapping(value = { "/delete-user-{ssoId}" })
    public String deleteUser(@PathVariable String ssoId) {
    	userService.deleteUserBySSO(ssoId);
        return "forward:/list?tenantId="+TenantHolder.getTenantId();
    }
     
 
    /**
     * This method will provide UserRole list to views
     */
    @ModelAttribute("roles")
    public List<Role> initializeRoles() {
        return roleService.findAll();
    }
     
    /**
     * This method handles Access-Denied redirect.
     */
    @GetMapping(value = "/Access_Denied")
    public String accessDeniedPage(ModelMap model) {
        model.addAttribute("loggedinuser", getPrincipal());
        return "accessDenied";
    }
 
    /**
     * This method handles login GET requests.
     * If users is already logged-in and tries to goto login page again, will be redirected to list page.
     */
    @GetMapping(value = {"/","/login","/home"})
    public String loginPage() {
        if (isCurrentAuthenticationAnonymous()) {
            return "login";
        } else {
        	String tenantId=userService.findBySSO(getPrincipal()).getTenantId();
        	System.err.println("Controller : "+tenantId);
        	if(tenantId.equals("1"))
        		return "forward:/Dashboard";
        	else
        		return "forward:/Dashboard?"+"tenantId="+tenantId;
        }
    }
    
    @GetMapping(value = "/Dashboard")
    public String getDashboard (Model model) {
    	User user = new User();
        model.addAttribute("user", user);
        model.addAttribute("edit", false);
        model.addAttribute("list", false);
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
