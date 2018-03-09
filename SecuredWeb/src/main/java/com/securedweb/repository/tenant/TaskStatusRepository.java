package com.securedweb.repository.tenant;

import org.springframework.data.repository.CrudRepository;

import com.securedweb.model.tenant.TaskStatus;

public interface TaskStatusRepository extends CrudRepository<TaskStatus,Integer>{

}
