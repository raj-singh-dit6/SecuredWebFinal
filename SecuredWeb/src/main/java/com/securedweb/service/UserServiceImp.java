package com.securedweb.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.securedweb.dao.UserDao;
import com.securedweb.model.User;

@Service("userService")
@Transactional
public class UserServiceImp implements UserService{

	
	@Autowired
	UserDao userDao;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Override
	public User findById(int id) {
		return userDao.findById(id);
	}

	@Override
	public User findBySSO(String sso) {
		return userDao.findBySSO(sso);
	}

	@Override
	public void saveUser(User user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		userDao.saveUser(user);		
	}

	@Override
	public void updateUser(User user) {
		User entity = userDao.findById(user.getId());
		if(entity!=null)
		{	entity.setSsoId(user.getSsoId());
			if(!(user.getPassword().equals(entity.getPassword())))
				entity.setPassword(user.getPassword());
			entity.setEmail(user.getEmail());
			entity.setFirstName(user.getFirstName());
			entity.setLastName(user.getLastName());
		}
	}

	@Override
	public void deleteUserBySSO(String sso) {
		userDao.deleteUserBySSO(sso);
	}

	@Override
	public List<User> findAlluser() {
		return userDao.findAllUser();
	}

	@Override
	public boolean isUserSSOUnique(Integer id, String sso) {
		User user = userDao.findBySSO(sso);
		return ( user == null || ((id!=null) && user.getId() == id));
	}

}
