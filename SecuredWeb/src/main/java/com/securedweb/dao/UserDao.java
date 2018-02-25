package com.securedweb.dao;

import java.util.List;

import com.securedweb.model.User;

public interface UserDao {
	User findById(int id);
	User findBySSO(String sso);
	void saveUser(User user);
	void deleteUserBySSO(String sso);
	List<User> findAllUser();

}
