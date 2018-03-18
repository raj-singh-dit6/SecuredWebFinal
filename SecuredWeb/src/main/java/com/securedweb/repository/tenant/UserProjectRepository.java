package com.securedweb.repository.tenant;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.securedweb.model.tenant.UserProject;

public interface UserProjectRepository extends CrudRepository<UserProject,Integer>{

	UserProject findByIdAndTenantId(Integer id, String tenantId);

	List<UserProject> findByTenantId(String tenantId);

	List<UserProject> findByProjectIdAndTenantId(String projectId, String tenantId);

	void deleteByIdAndTenantId(Integer userProjectId, String tenantId);

}
