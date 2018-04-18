package com.securedweb.service.tenant;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;

import com.securedweb.model.tenant.User;

public interface UserService {

	User findById(int id);
	
	User findBySSO(String sso);
	
	@PreAuthorize("hasRole('ADMIN') or hasRole('DBA')")
	void saveUser(User user);
	
	@PreAuthorize("hasRole('ADMIN') or hasRole('DBA')")
	void updateUser(User user); 
	
	@PreAuthorize("hasRole('ADMIN') or hasRole('DBA')")
	void deleteUserBySSO(String sso);
	
	List<User> findAllUser();
	
	boolean isUserSSOUnique(Integer id, String sso);
	
}
