package com.securedweb.repository.tenant;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.securedweb.model.tenant.TaskStatus;

public interface TaskStatusRepository extends CrudRepository<TaskStatus,Integer>{

	List<TaskStatus> findByTenantId(String tenantId);

	TaskStatus findByIdAndTenantId(Integer id, String tenantId);

}
