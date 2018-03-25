package com.securedweb.controller;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.util.FileCopyUtils;
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
import com.securedweb.model.tenant.Document;
import com.securedweb.service.tenant.DocumentService;
import com.securedweb.service.tenant.ProjectService;


@RestController
@RequestMapping(value="document")
public class DocumentController {
	
	@Autowired
	ProjectService projectSercvice;
	
	
	@Autowired
	DocumentService documentService;
	
	
    @GetMapping(value ="/all/{type}/{id}",produces= MediaType.APPLICATION_JSON_VALUE)
    public List<DocumentDTO> getAllDocuments(@PathVariable("type") String type,@PathVariable("id") Integer id) {
    	List<DocumentDTO> documents = documentService.getAllDocuments(type,id);
        return documents;
    }
    
    @PostMapping(value ="/upload/{type}/{id}",produces= MediaType.APPLICATION_JSON_VALUE,consumes=MediaType.ALL_VALUE)
    public StatusDTO uploadDocuments(@PathVariable("type") String type,@PathVariable("id") Integer id,@RequestParam("description") String description,@RequestParam("files") MultipartFile[] files) throws IOException{
    	
    	System.err.println(type);
    	System.err.println(files.length);
    	
    	StatusDTO status = new StatusDTO();
    	for(MultipartFile file:Arrays.asList(files))
    	{
    		System.err.println(file.getOriginalFilename());
    	}
    	documentService.uploadDocument(type,id,description,files);
    	status.setStatus(200);
    	status.setMessage("Uploaded successfully");
    	return status;
    }
 
    @GetMapping(value ="/download/{projectId}/{documentId}",produces= MediaType.APPLICATION_JSON_VALUE)
    public StatusDTO downloadDocument( @PathVariable("projectId") Integer projectId, @PathVariable("documentId") Integer documentId, HttpServletResponse response) throws IOException {
    	StatusDTO status = new StatusDTO();
    	Document document = documentService.getDocument(documentId,projectId);
        response.setContentType(document.getType());
        response.setContentLength(document.getContent().length);
        response.setHeader("Content-Disposition","attachment; filename=\"" + document.getName() +"\"");
  
        status.setStatus(200);
    	status.setMessage("Downloded successfully");
        
    	FileCopyUtils.copy(document.getContent(), response.getOutputStream());
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
