package com.securedweb.service.tenant;

import java.util.List;

import com.securedweb.dto.tenant.TaskDTO;
import com.securedweb.model.tenant.Task;

public interface TaskService {
	
	TaskDTO addTask(TaskDTO task);

	List<TaskDTO> getAllTasks();

	void deleteTask(Integer taskId);

	TaskDTO getTaskDTO(Integer taskId);

	TaskDTO updateTask(TaskDTO task);

	Task getTask(Integer id);
	
}
