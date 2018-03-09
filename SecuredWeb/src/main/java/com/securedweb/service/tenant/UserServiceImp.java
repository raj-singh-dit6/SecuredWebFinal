package com.securedweb.service.tenant;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.securedweb.model.tenant.User;
import com.securedweb.repository.tenant.UserRepository;
import com.securedweb.util.TenantHolder;

@Service("userService")
@Transactional("tenantTransactionManager")
public class UserServiceImp implements UserService{

	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Override
	public User findById(int id) {
		User user=userRepository.findById(id).get();;
		if(user!=null){
            Hibernate.initialize(user.getUserRoles());
        }
		return user;
	}

	@Override
	public User findBySSO(String ssoId) {
		User user=userRepository.findBySsoId(ssoId);
		if(user!=null){
            Hibernate.initialize(user.getUserRoles());
        }
		return user;
	}

	@Override
	public void saveUser(User user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		userRepository.save(user);
	}

	@Override
	public void updateUser(User user) {
		User entity = userRepository.findById(user.getId()).get();
		
		if(entity!=null)
		{	entity.setSsoId(user.getSsoId());
			if(!(user.getPassword().equals(entity.getPassword())))
				entity.setPassword(passwordEncoder.encode(user.getPassword()));
			entity.setEmail(user.getEmail());
			entity.setFirstName(user.getFirstName());
			entity.setLastName(user.getLastName());
		}
	}

	@Override
	public void deleteUserBySSO(String ssoId) {
		userRepository.deleteBySsoId(ssoId);
	}

	@Override
	public List<User> findAllUser() {
		List<User> userList=new ArrayList<User>();
		if(TenantHolder.getTenantId().equals("1"))
			userList=(List<User>) userRepository.findAll();
		else
			userList=(List<User>) userRepository.findByTenantId(TenantHolder.getTenantId());
		
		for(User user:userList)
		{
	            Hibernate.initialize(user.getUserRoles());
	            Hibernate.initialize(user.getUserProjects());
	            Hibernate.initialize(user.getUserTasks());
		}
		return userList;
	
	}

	@Override
	public boolean isUserSSOUnique(Integer id, String ssoId) {
		User user = userRepository.findBySsoId(ssoId);
		return ( user == null || ((id!=null) && user.getId() == id));
	}

}
