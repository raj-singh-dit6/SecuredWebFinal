package com.securedweb.service.tenant;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.securedweb.dto.tenant.DocumentDTO;
import com.securedweb.model.tenant.Document;
import com.securedweb.model.tenant.Project;
import com.securedweb.model.tenant.User;
import com.securedweb.repository.tenant.DocumentRepository;

@Service("documentService")
@Transactional("tenantTransactionManager")
public class DocumentServiceImpl implements DocumentService{

	@Autowired
	DocumentRepository documentRepository;
	
	@Autowired
	ProjectService projectService;
	
	@Autowired
	UserService userService;
	
	
	@Override
	public List<DocumentDTO> getAllDocuments(String type,Integer id) {
		List<Document> documentList = new ArrayList<Document>();
		if(type.equals("user"))
			documentList= documentRepository.findAllByUser(userService.getUser(id));
		else if(type.equals("project"))
			documentList = documentRepository.findAllByProject(projectService.getProject(id));
		
		List<DocumentDTO> documentDTOList = new ArrayList<DocumentDTO>(); 

		for(Document document: documentList)
		{
			DocumentDTO documentDTO = new DocumentDTO();
			documentDTO.setId(document.getId());
			documentDTO.setName(document.getName());
			documentDTO.setDescription(document.getDescription());
			documentDTO.setUser(document.getUser());
			documentDTO.setProject(document.getProject());
			documentDTOList.add(documentDTO);
		}
		
		return documentDTOList;
	}

	@Override
	public void uploadDocument(String type,Integer id, String description, MultipartFile[] files) throws IOException {
		Object obj=null;
		if(type.equals("user"))
			obj=userService.getUser(id);
		else if(type.equals("project"))	
			obj = projectService.getProject(id);
		
		for(int i=0;i<files.length;i++)
		{
			saveDocument(files[i],description,obj);
		}	
		
	}

	@Override
	public Document getDocument(Integer documentId,Integer projectId) {
		Project project = projectService.getProject(projectId);	
		return documentRepository.findByIdAndProject(documentId,project);
	}

	@Override
	public void deleteDocumentById(Integer documentId) {
		
		documentRepository.deleteById(documentId);
	}

	private void saveDocument(MultipartFile multipartFile, String description,Object obj) throws IOException{
        
		Document newDocument = new Document();
		newDocument.setName(multipartFile.getOriginalFilename());
		newDocument.setDescription(description);
		newDocument.setType(multipartFile.getContentType());
		newDocument.setContent(multipartFile.getBytes());
		if(obj instanceof Project)
			newDocument.setProject((Project)obj);
		else if(obj instanceof User)
			newDocument.setUser((User)obj);
		documentRepository.save(newDocument);
    }
}
