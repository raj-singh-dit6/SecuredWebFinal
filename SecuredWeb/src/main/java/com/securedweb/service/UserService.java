package com.securedweb.service;

import java.util.List;

import com.securedweb.model.User;

public interface UserService {

	User findById(int id);
	User findBySSO(String sso);
	void saveUser(User user);
	void updateUser(User user); 
	void deleteUserBySSO(String sso);
	List<User> findAlluser();
	boolean isUserSSOUnique(Integer id, String sso);
	
}
