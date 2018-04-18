package com.securedweb.controller;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.securedweb.dto.tenant.DocumentDTO;
import com.securedweb.dto.tenant.StatusDTO;
import com.securedweb.model.tenant.Project;
import com.securedweb.model.tenant.User;
import com.securedweb.service.tenant.DocumentService;
import com.securedweb.service.tenant.ProjectService;
import com.securedweb.service.tenant.UserService;
import com.securedweb.util.TenantHolder;


@RestController
@RequestMapping(value="document")
public class DocumentController {
	
	private static final Logger LOG = LoggerFactory.getLogger(DocumentController.class);
	
	private static final String rootPath = "/home/rajendra/SecuredWeb";
	
	@Autowired
	ProjectService projectSercvice;
	
	
	@Autowired
	DocumentService documentService;
	
	@Autowired
	ProjectService projectService;
	
	@Autowired
	UserService userService;
 
	@GetMapping(value ="/all/{type}/{id}",produces= MediaType.APPLICATION_JSON_VALUE)
    public List<DocumentDTO> getAllDocuments(@PathVariable("type") String type,@PathVariable("id") Integer id) {
    	List<DocumentDTO> documents = documentService.getAllDocuments(type,id);
        return documents;
    }
    
    @PostMapping(value ="/upload/{type}/{id}",produces= MediaType.APPLICATION_JSON_VALUE,consumes=MediaType.ALL_VALUE)
    public StatusDTO uploadDocuments(@PathVariable("type") String type,@PathVariable("id") Integer id,@RequestParam("description") String description,@RequestParam("files") MultipartFile[] files) throws IOException{
    	
    	Project project = null;
    	User user = null;
    	if(type.equals("user"))
    		user = userService.getUser(id);
		else if(type.equals("project"))
			project= projectService.getParentProject(id);
    	
    	StatusDTO status = new StatusDTO();
    	for(MultipartFile file:Arrays.asList(files))
    	{
    		System.err.println(file.getOriginalFilename());
			try {
				File dir = new File(rootPath + File.separator + "Uploads"+ File.separator + TenantHolder.getTenantId()+ File.separator +type+ File.separator +project.getName());
				if (!dir.exists())
					dir.mkdirs();
				File serverFile = new File(dir.getAbsolutePath()+ File.separator + file.getOriginalFilename());

				file.transferTo(serverFile);
				String fileLocationDB = TenantHolder.getTenantId()+ File.separator+type+ File.separator +project.getName()+File.separator+file.getOriginalFilename();
				documentService.uploadDocument(type,id,description,file,fileLocationDB);
				status.setStatus(200);
		    	status.setMessage("Files uploaded successfully");
		    	
			} catch (Exception e) {
				status.setMessage("Failed to upload " );
			}
		}
    	return status;
    }
    
    @DeleteMapping(value ="/delete/{documentId}",produces= MediaType.APPLICATION_JSON_VALUE)
    public StatusDTO deleteDocument(@PathVariable Integer documentId) {
    	StatusDTO status = new StatusDTO();
    	documentService.deleteDocumentById(documentId);
    	status.setStatus(200);
    	status.setMessage("Deleted successfully");
    	return status;
    }
    
}
