package com.securedweb.repository.tenant;

import java.util.List;
import java.util.Set;

import org.springframework.data.repository.CrudRepository;

import com.securedweb.model.tenant.User;
import com.securedweb.model.tenant.UserProject;

public interface UserProjectRepository extends CrudRepository<UserProject,Integer>{

	UserProject findByIdAndTenantId(Integer id, String tenantId);

	List<UserProject> findByTenantId(String tenantId);


	void deleteByIdAndTenantId(Integer userProjectId, String tenantId);

	Set<UserProject> findByUserAndTenantId(User user,String tenantId);

}
