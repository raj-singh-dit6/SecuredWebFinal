package com.securedweb.configuration;

import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletRegistration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class AppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
 
	private static final Logger LOG = LoggerFactory.getLogger(AppInitializer.class);

	/*Set these variables for your project needs*/
    
    private static final String LOCATION = "/home/rajendra/Downloads/temp";
 
    private static final long MAX_FILE_SIZE = 1024 * 1024 * 20; //20MB
     
    private static final long MAX_REQUEST_SIZE = 1024 * 1024 * 30;//30MB
 
    private static final int FILE_SIZE_THRESHOLD = 0;
    
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
 
    @Override
    protected void customizeRegistration(ServletRegistration.Dynamic registration) {
        registration.setMultipartConfig(getMultipartConfigElement());
    }
    
    private MultipartConfigElement getMultipartConfigElement(){
        MultipartConfigElement multipartConfigElement = new MultipartConfigElement(LOCATION, MAX_FILE_SIZE, MAX_REQUEST_SIZE, FILE_SIZE_THRESHOLD);
        return multipartConfigElement;
    }
     
}
