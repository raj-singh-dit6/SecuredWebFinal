package com.securedweb.service.tenant;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.securedweb.dto.tenant.DocumentDTO;
import com.securedweb.model.tenant.Document;

public interface DocumentService {

	
	void deleteDocumentById(Integer documentId);

	List<DocumentDTO> getAllDocuments(String type, Integer id);

	void uploadDocument(String type, Integer id, String description, MultipartFile[] files) throws IOException;

	Document getDocument(Integer documentId, Integer projectId);

}
