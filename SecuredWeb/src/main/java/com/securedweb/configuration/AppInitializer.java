package com.securedweb.configuration;

import javax.servlet.MultipartConfigElement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class AppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
 
	private static final Logger LOG = LoggerFactory.getLogger(AppInitializer.class);
	
	@Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[] { AppConfig.class };
    }
  
    @Override
    protected Class<?>[] getServletConfigClasses() {
        return null;
    }
  
    @Override
    protected String[] getServletMappings() {
        return new String[] { "/" };
    }
 
    private MultipartConfigElement getMultipartConfigElement(){
        MultipartConfigElement multipartConfigElement = new MultipartConfigElement(LOCATION, MAX_FILE_SIZE, MAX_REQUEST_SIZE, FILE_SIZE_THRESHOLD);
        return multipartConfigElement;
    }
     
    /*Set these variables for your project needs*/
     
    private static final String LOCATION = "C:/mytemp/";
 
    private static final long MAX_FILE_SIZE = 1024 * 1024 * 20;//25MB
     
    private static final long MAX_REQUEST_SIZE = 1024 * 1024 * 30;//30MB
 
    private static final int FILE_SIZE_THRESHOLD = 0;
}
