package com.securedweb.repository.tenant;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.securedweb.model.tenant.Document;
import com.securedweb.model.tenant.Project;
import com.securedweb.model.tenant.User;

public interface DocumentRepository extends CrudRepository<Document, Integer>{

	List<Document> findAllByProject(Project project);

	List<Document> findAllByUser(User user);

	Document findByIdAndProject(Integer documentId, Project project);

}
