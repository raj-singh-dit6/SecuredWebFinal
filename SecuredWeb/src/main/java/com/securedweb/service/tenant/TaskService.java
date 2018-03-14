package com.securedweb.service.tenant;

import java.util.List;

import com.securedweb.model.tenant.Task;

public interface TaskService {
	
	List<Task> findAll();

	void saveTask(Task task);
	
}
