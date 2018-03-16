package com.securedweb.service.tenant;

import java.util.List;

import com.securedweb.dto.tenant.UserProjectDTO;

public interface UserProjectService {
	
	UserProjectDTO addUserProject(UserProjectDTO userProjectDTO);
	void deleteUserProject(UserProjectDTO userProjectDTO);
	List<UserProjectDTO> getAllUserProject();

}
