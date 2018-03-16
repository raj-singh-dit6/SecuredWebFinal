package com.securedweb.service.tenant;

import java.util.List;

import com.securedweb.dto.tenant.TaskDTO;

public interface TaskService {
	
	TaskDTO addTask(TaskDTO task);

	List<TaskDTO> getAllTasks();
	
}
