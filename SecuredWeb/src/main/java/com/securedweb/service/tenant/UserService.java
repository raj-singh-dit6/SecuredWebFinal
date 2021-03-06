package com.securedweb.service.tenant;

import java.util.List;

import com.securedweb.dto.tenant.UserDTO;
import com.securedweb.model.tenant.User;

public interface UserService {


	UserDTO getUser(String ssoId);

	UserDTO addUser(UserDTO user);
	
	void deleteUser(String sso);

	UserDTO updateUser(UserDTO user);

	List<UserDTO> getAllUsers();

	User getUser(Integer id);

	User findUserByEmail(String userEmail);

	void updatePassword(String updatedPassword, Integer id);

	boolean changePassword(UserDTO user);


	boolean isUserSSOUnique(String ssoId, String tenantId);

	boolean isUserEmailUnique(String ssoId, String email, String tenantId);

}
