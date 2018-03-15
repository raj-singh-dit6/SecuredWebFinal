package com.securedweb.repository.tenant;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.securedweb.model.tenant.Project;

public interface ProjectRepository extends CrudRepository<Project,Integer>{


	List<Project> findByTenantId(String tenantId);

	Project findByIdAndTenantId(Integer id, String tenantId);

	Project findByNameAndTenantId(String name, String tenantId);

	void deleteByIdAndTenantId(Integer id, String tenantId);

}
