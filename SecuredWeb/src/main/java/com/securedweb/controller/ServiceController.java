package com.securedweb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.securedweb.dto.tenant.PasswordResetDTO;
import com.securedweb.model.tenant.PasswordResetToken;
import com.securedweb.model.tenant.User;
import com.securedweb.repository.tenant.PasswordResetTokenRepository;
import com.securedweb.service.tenant.UserService;

@Controller
@RequestMapping("/service")
public class ServiceController {

	 @Autowired 
	 private PasswordResetTokenRepository tokenRepository;
	 
	 @Autowired
	private PasswordEncoder passwordEncoder;
	 
	 @Autowired
	 UserService userService;
	 
	 @PreAuthorize("hasRole('ADMIN') or hasRole('DBA')")
	 @PostMapping(value="/resetPassword",consumes={MediaType.APPLICATION_JSON_VALUE},produces={MediaType.APPLICATION_JSON_VALUE})
	 public String handlePasswordReset(@RequestBody PasswordResetDTO passwordResetDTO){
		
		PasswordResetToken token = tokenRepository.findByToken(passwordResetDTO.getToken());
        User user = token.getUser();
        String updatedPassword = passwordEncoder.encode(passwordResetDTO.getPassword());
        userService.updatePassword(updatedPassword, user.getId());
        tokenRepository.delete(token);
        return "redirect:/login?resetSuccess";
    }
}
