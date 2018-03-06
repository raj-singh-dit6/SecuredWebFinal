package com.securedweb.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.securedweb.model.User;
import com.securedweb.repository.UserRepository;

@Service("userService")
@Transactional
public class UserServiceImp implements UserService{

	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Override
	public User findById(int id) {
		return userRepository.findById(id).get();
	}

	@Override
	public User findBySSO(String sso) {
		return userRepository.findBySsoId(sso);
	}

	@Override
	public void saveUser(User user) {
		userRepository.save(user);
	}

	@Override
	public void updateUser(User user) {
		User entity = userRepository.findById(user.getId()).get();
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
		userRepository.deleteBySsoId(sso);
	}

	@Override
	public List<User> findAlluser() {
		return (List<User>) userRepository.findAll();
	}

	@Override
	public boolean isUserSSOUnique(Integer id, String sso) {
		User user = userRepository.findBySsoId(sso);
		return ( user == null || ((id!=null) && user.getId() == id));
	}

}
