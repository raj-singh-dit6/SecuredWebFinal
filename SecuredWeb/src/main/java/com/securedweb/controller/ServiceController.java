package com.securedweb.controller;

import java.io.UnsupportedEncodingException;
import java.util.Base64;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.securedweb.dto.tenant.PasswordResetDTO;
import com.securedweb.dto.tenant.StatusDTO;
import com.securedweb.model.tenant.PasswordResetToken;
import com.securedweb.model.tenant.User;
import com.securedweb.repository.tenant.PasswordResetTokenRepository;
import com.securedweb.service.tenant.UserService;

@Controller
@RequestMapping("/service")
public class ServiceController {

	private static final Logger LOG = LoggerFactory.getLogger(ServiceController.class);
	
	 @Autowired 
	 private PasswordResetTokenRepository tokenRepository;
	 
	 @Autowired
	 UserService userService;
	 
	 @PostMapping(value="/resetPassword",consumes={MediaType.APPLICATION_JSON_VALUE},produces={MediaType.APPLICATION_JSON_VALUE})
	 @ResponseBody
	 public StatusDTO handlePasswordReset(@RequestBody PasswordResetDTO passwordResetDTO){
		
		StatusDTO status = new StatusDTO();
		PasswordResetToken token = tokenRepository.findByToken(passwordResetDTO.getToken());
        if(token!=null) {
			User user = token.getUser();
			String updatedPassword = "";
			try {
				updatedPassword = Base64.getEncoder().encodeToString(passwordResetDTO.getPassword().getBytes("utf-8"));
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
	        userService.updatePassword(updatedPassword, user.getId());
	        tokenRepository.delete(token);
	        status.setMessage("Password updated, please login to visit your account.");
	        status.setStatus(200);
	        return status;
        }else
        {
        	status.setMessage("Token already consumed , Password already updated, please login to visit your account.");
            return status;	
        }	
    }
	 
	 @GetMapping(value="/resetPassword")
	 public String getMapping() {
        return "login";
    }
	 
		@GetMapping(value="/reset-password")
		public String displayResetPasswordPage(@RequestParam("token") String token,Model model,HttpServletRequest request, HttpSession session) {
			PasswordResetToken resetToken = tokenRepository.findByToken(token);
			System.err.print(token);
			if (resetToken == null){
		        model.addAttribute("error", "Could not find password reset token.");
		    } else if (resetToken.isExpired()){
		        model.addAttribute("error", "Token has expired, please request a new password reset from login page.");
		    } else {
		        model.addAttribute("token", resetToken.getToken());
		    }

	    return "reset-password";

	    }
	 
}
