package com.securedweb.repository;

import org.springframework.data.repository.CrudRepository;

import com.securedweb.model.TaskStatus;

public interface TaskStatusRepository extends CrudRepository<TaskStatus,Integer>{

}
