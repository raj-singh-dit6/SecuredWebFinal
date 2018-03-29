package com.securedweb.controller;

import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.securedweb.dto.tenant.StatusDTO;
import com.securedweb.dto.tenant.UserDTO;
import com.securedweb.model.tenant.Mail;
import com.securedweb.model.tenant.PasswordResetToken;
import com.securedweb.model.tenant.User;
import com.securedweb.repository.tenant.PasswordResetTokenRepository;
import com.securedweb.service.tenant.MailService;
import com.securedweb.service.tenant.RoleService;
import com.securedweb.service.tenant.UserService;
import com.securedweb.util.TenantHolder;

@RestController
@RequestMapping("/user")
public class UserController {
	
 private static final Logger LOG = LoggerFactory.getLogger(UserController.class);	
 
 @Autowired
 private UserService userService;
 
 @Autowired 
 private PasswordResetTokenRepository tokenRepository;

 @Autowired 
 MailService mailService;
 
 @Autowired
 RoleService roleService;
 
 @GetMapping(value="/{ssoId}",produces={MediaType.APPLICATION_JSON_VALUE})
 public UserDTO getUser(@PathVariable("ssoId") String ssoId){
	 return userService.getUser(ssoId);
 }
 
 @PostMapping(value="/add",consumes={MediaType.APPLICATION_JSON_VALUE},produces={MediaType.APPLICATION_JSON_VALUE})
 public StatusDTO addUser(@RequestBody UserDTO user){
	 StatusDTO status = new StatusDTO();
	 if(userService.isUserSSOUnique(user.getSsoId(),user.getTenantId()!=""?user.getTenantId():TenantHolder.getTenantId()))
	{
		if(userService.isUserEmailUnique(user.getEmail(),user.getTenantId()!=""?user.getTenantId():TenantHolder.getTenantId()))
		{  
			 userService.addUser(user);
		     status.setMessage("User added successfully");
		     status.setStatus(200);
		}else {
			status.setMessage("An account already exists with this email id.");	
		}
		 return status;
	}else {
		status.setMessage("Please enter a unique SSO ID");
		return status;
	}		 
 }
 
 @PreAuthorize("hasRole('ADMIN') or hasRole('DBA')")
 @PostMapping(value="/update",consumes={MediaType.APPLICATION_JSON_VALUE},produces={MediaType.APPLICATION_JSON_VALUE})
 public StatusDTO updateUser(@RequestBody UserDTO user){
	 StatusDTO status = new StatusDTO();
	 if(userService.isUserEmailUnique(user.getEmail(),TenantHolder.getTenantId()))
	{  
			 userService.updateUser(user);
		     status.setMessage("User details updated successfully");
		     status.setStatus(200);
	}else {
			status.setMessage("An account already exists with this email id.");	
	}
    return status;
 }
 
 @PreAuthorize("hasRole('ADMIN') or hasRole('DBA')")
 @PostMapping(value="/changePassword",produces={MediaType.APPLICATION_JSON_VALUE},consumes={MediaType.APPLICATION_JSON_VALUE})
 public StatusDTO changePassowrd(@RequestBody UserDTO user){
	 StatusDTO status = new StatusDTO();
	 boolean result=userService.changePassword(user);
	 if(result)
	 {
	     status.setMessage("Password changed successfully.");
	     status.setStatus(200);
	     return status;
	 }else
	 {
		 status.setMessage("Current passoword entered was not correct.");
	     return status;
	 }
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
 
 @PostMapping(value="/resetPassword",produces={MediaType.APPLICATION_JSON_VALUE})
 public StatusDTO sendResetPasswordMail(HttpServletRequest request, @RequestParam("email") String userEmail){
	 
	 
	 StatusDTO status = new StatusDTO();
	
	 User user = userService.findUserByEmail(userEmail);
	    if (user == null) {
	    	status.setMessage("Email ID does not exist in out database, please enter correct email id.");
	    }else
	    {		
	    	 	PasswordResetToken token = new PasswordResetToken();
	            token.setToken(UUID.randomUUID().toString());
	            token.setUser(user);
	            token.setExpiryDate(72);
	            tokenRepository.save(token);
	            
	            Mail mail = new Mail();
	            mail.setContextPath(request.getContextPath());
	            mail.setFrom("raj.singh.dit8@gmail.com");
	            mail.setTo(user.getEmail());
	            mail.setSubject("Password reset request");
	            
	            String url = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()+"/SecuredWeb/service/reset-password?token=" + token.getToken();
	            System.err.println(url);
	            mail.setBody(url);
	            
	            mailService.sendEmail(mail);
	            
	            status.setMessage("Email sent to your registered email id : "+user.getEmail());
	            status.setStatus(200);
	    }
		return status;
 }

 @GetMapping(value="/unique/ssoId/{ssoId}",produces={MediaType.APPLICATION_JSON_VALUE})
 public StatusDTO isUserSSOUnique(@PathVariable("ssoId") String ssoId){
	 StatusDTO status = new StatusDTO();
	 if(!userService.isUserSSOUnique(ssoId,TenantHolder.getTenantId()))
	 {
		 status.setMessage("Please enter a unique user id.");
		 status.setStatus(200);	 
	 }
     return status;
 }
 
 @GetMapping(value="/unique/email/{emailId:.+}",produces={MediaType.APPLICATION_JSON_VALUE})
 public StatusDTO isUserEmailUnique(@PathVariable("emailId") String emailId){
	 System.err.println(emailId);
	 StatusDTO status = new StatusDTO();
	 if(!userService.isUserEmailUnique(emailId,TenantHolder.getTenantId()))
	 {
		 status.setMessage("An account already exist with this email id.");
		 status.setStatus(200);	 
	 }
     return status;
 }
 
}	