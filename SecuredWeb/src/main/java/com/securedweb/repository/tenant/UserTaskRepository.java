package com.securedweb.repository.tenant;

import org.springframework.data.repository.CrudRepository;

import com.securedweb.model.tenant.UserTask;

public interface UserTaskRepository extends CrudRepository<UserTask, Integer> {

}
