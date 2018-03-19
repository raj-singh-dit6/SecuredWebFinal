package com.securedweb.service.tenant;

import java.util.List;

import com.securedweb.dto.tenant.UserTaskDTO;

public interface UserTaskService {


	UserTaskDTO addUserTask(UserTaskDTO userTask);

	void deleteUserTask(Integer userTaskId);

	List<UserTaskDTO> getAllUserTask();

	List<UserTaskDTO> getAllUserTaskBySsoId();

	UserTaskDTO getUserTask(Integer userTaskId);

}
