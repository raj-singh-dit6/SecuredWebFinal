package com.securedweb.service.tenant;

import java.util.List;

import com.securedweb.dto.tenant.UserDTO;

public interface UserService {


	UserDTO getUser(String ssoId);

	UserDTO addUser(UserDTO user);
	
	void deleteUser(String sso);

	UserDTO updateUser(UserDTO user);

	List<UserDTO> getAllUsers();

	boolean isUserSSOUnique(String ssoId, String tenantId);
}
