package com.securedweb.controller;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

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
	
	private static Map<String,String> viewMap = new HashMap<String,String>(); 

	@Autowired
	UserService userService;
	
	@Autowired
	ProjectService projectService;
	
	@Autowired 
	private PasswordResetTokenRepository tokenRepository;
	
	@PostConstruct
    public void init() {
		Properties prop = new Properties();
		ClassLoader loader = getClass().getClassLoader();           
		InputStream stream = loader.getResourceAsStream("views.properties");
	    try {
    	  prop.load(stream);
	      viewMap.putAll(prop.entrySet().stream()
                    .collect(Collectors.toMap(e -> e.getKey().toString(), e -> e.getValue().toString())));
	    }
	    catch (Exception e) {
	    	e.printStackTrace();
	    }
	}
	
	@GetMapping(value="/ajaxPage/{pagetype}")
	public String ajaxPage(@PathVariable("pagetype") String pagetype) {
		return viewMap.get(pagetype);
	}

}
