package com.securedweb.service.tenant;

import java.util.List;

import com.securedweb.model.tenant.TaskStatus;

public interface TaskStatusService {

	List<TaskStatus> findAll();

	void saveTaskStatus(TaskStatus taskStatus);
}
