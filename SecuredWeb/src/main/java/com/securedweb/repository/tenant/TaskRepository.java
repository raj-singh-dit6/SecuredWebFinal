package com.securedweb.repository.tenant;

import org.springframework.data.repository.CrudRepository;

import com.securedweb.model.tenant.Task;

public interface TaskRepository extends CrudRepository<Task,Integer>{

	Task findByIdAndTenantId(Integer taskId,String tenantId);

	void deleteByIdAndTenantId(Integer taskId,String tenantId);

}
