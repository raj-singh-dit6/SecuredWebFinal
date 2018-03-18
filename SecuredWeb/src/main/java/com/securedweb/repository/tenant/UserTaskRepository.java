package com.securedweb.repository.tenant;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.securedweb.model.tenant.UserTask;

public interface UserTaskRepository extends CrudRepository<UserTask, Integer> {

	List<UserTask> findByTenantId(String tenantId);


	UserTask findByIdAndTenantId(Integer userTaskId, String tenantId);


	void deleteByIdAndTenantId(Integer userTaskId, String tenantId);

}
