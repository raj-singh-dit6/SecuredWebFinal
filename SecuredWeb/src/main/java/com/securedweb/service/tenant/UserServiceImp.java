package com.securedweb.service.tenant;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.securedweb.dto.tenant.UserDTO;
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
	public UserDTO getUser(String ssoId) {
		UserDTO userDTO = new UserDTO();
		User user;
		if(TenantHolder.getTenantId().equals("1")){
			user=userRepository.findBySsoId(ssoId);
		}else {
			user=userRepository.findBySsoIdAndTenantId(ssoId,TenantHolder.getTenantId());
			
		}
		
		if(user!=null){
			Hibernate.initialize(user.getUserRoles());
            Hibernate.initialize(user.getUserProjects());
            Hibernate.initialize(user.getUserTasks());
            
            userDTO.setFirstName(user.getFirstName());
			userDTO.setLastName(user.getLastName());
			userDTO.setEmail(user.getEmail());
			userDTO.setSsoId(user.getSsoId());
			userDTO.setTenantId(user.getTenantId());
        }
		return userDTO;
	}

	@Override
	public UserDTO addUser(UserDTO user) {
		 User newUser = new User();
		 newUser.setFirstName(user.getFirstName());
		 newUser.setLastName(user.getLastName());
		 newUser.setEmail(user.getEmail());
		 newUser.setSsoId(user.getSsoId());
		 newUser.setPassword(passwordEncoder.encode(user.getPassword()));
		 newUser.setTenantId(user.getTenantId());
		 newUser.setUserRoles(user.getUserRoles());;
		 userRepository.save(newUser);
		 return user;
	}

	@Override
	public UserDTO updateUser(UserDTO user) {
		User updateUser = userRepository.findBySsoIdAndTenantId(user.getSsoId(), TenantHolder.getTenantId());
		updateUser.setFirstName(user.getFirstName());
		updateUser.setLastName(user.getLastName());
		updateUser.setEmail(user.getEmail());
		updateUser.setUserRoles(user.getUserRoles());;
		updateUser=userRepository.save(updateUser);
		return user;
	}

	@Override
	public void deleteUser(String ssoId) {

		userRepository.deleteBySsoIdAndTenantId(ssoId,TenantHolder.getTenantId());
	}

	@Override
	public List<UserDTO> getAllUsers() {
		
		List<User> userList=new ArrayList<User>();
		List<UserDTO> userDTOList=new ArrayList<UserDTO>();
		
		if(TenantHolder.getTenantId().equals("1"))
			userList=(List<User>) userRepository.findAll();
		else
			userList=(List<User>) userRepository.findByTenantId(TenantHolder.getTenantId());

		for(User user:userList)
		{
				
			Hibernate.initialize(user.getUserRoles());
            Hibernate.initialize(user.getUserProjects());
            Hibernate.initialize(user.getUserTasks());
            
				UserDTO userDTO = new UserDTO();
				userDTO.setFirstName(user.getFirstName());
				userDTO.setLastName(user.getLastName());
				userDTO.setEmail(user.getEmail());
				userDTO.setSsoId(user.getSsoId());
		        userDTO.setUserRoles(user.getUserRoles());
		        userDTOList.add(userDTO);
		}
		
		return 	userDTOList;
	}

	@Override
	public boolean isUserSSOUnique(String ssoId) {
		User user = userRepository.findBySsoIdAndTenantId(ssoId,TenantHolder.getTenantId());
		return user == null;
	}
	
	

}
