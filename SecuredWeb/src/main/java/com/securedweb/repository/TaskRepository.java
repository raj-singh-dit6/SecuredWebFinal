package com.securedweb.repository;

import org.springframework.data.repository.CrudRepository;

import com.securedweb.model.Task;

public interface TaskRepository extends CrudRepository<Task,Integer>{

}
