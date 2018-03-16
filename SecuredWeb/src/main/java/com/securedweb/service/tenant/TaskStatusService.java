package com.securedweb.service.tenant;

import java.util.List;

import com.securedweb.dto.tenant.TaskStatusDTO;
import com.securedweb.model.tenant.TaskStatus;

public interface TaskStatusService {

	List<TaskStatusDTO> getAllTaskStatus();

	TaskStatus getTaskStatus(Integer id);

	TaskStatusDTO addTaskStatus(TaskStatusDTO taskStatus);

	TaskStatusDTO getTaskStatusDTO(Integer id);

	void deleteTaskStatus(Integer taskStatusId);
}
