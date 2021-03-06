package com.securedweb.service.tenant;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import org.hibernate.Hibernate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.securedweb.dto.tenant.UserDTO;
import com.securedweb.model.tenant.User;
import com.securedweb.repository.tenant.UserRepository;
import com.securedweb.util.TenantHolder;

@Service("userService")
@Transactional("tenantTransactionManager")
public class UserServiceImp implements UserService{
	
	private static final Logger LOG = LoggerFactory.getLogger(UserServiceImp.class);
	
	@Autowired
	UserRepository userRepository;
	
	@Override
	public UserDTO getUser(String ssoId) {
		UserDTO userDTO = new UserDTO();
		User user=userRepository.findBySsoIdAndTenantId(ssoId,TenantHolder.getTenantId());
		
		if(user!=null){
			Hibernate.initialize(user.getUserRoles());
            //Hibernate.initialize(user.getUserProjects());
            //Hibernate.initialize(user.getUserTasks());
            
            userDTO.setFirstName(user.getFirstName());
			userDTO.setLastName(user.getLastName());
			userDTO.setEmail(user.getEmail());
			userDTO.setSsoId(user.getSsoId());
			userDTO.setUserRoles(user.getUserRoles());
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
		 String encodedPassword="";
		try {
			encodedPassword = Base64.getEncoder().encodeToString(user.getPassword().getBytes("utf-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		 newUser.setPassword(encodedPassword);
		 newUser.setTenantId(TenantHolder.getTenantId());
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
		return user;
	}

	@Override
	public void deleteUser(String ssoId) {

		userRepository.deleteBySsoIdAndTenantId(ssoId,TenantHolder.getTenantId());
	}

	@Override
	public List<UserDTO> getAllUsers() {
		
		List<User> userList=(List<User>) userRepository.findByTenantId(TenantHolder.getTenantId());
		List<UserDTO> userDTOList=new ArrayList<UserDTO>();
		
		for(User user:userList)
		{
				
			Hibernate.initialize(user.getUserRoles());
            Hibernate.initialize(user.getUserProjects());
            Hibernate.initialize(user.getUserTasks());
            
				UserDTO userDTO = new UserDTO();
				userDTO.setId(user.getId());
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
	public boolean isUserSSOUnique(String ssoId,String tenantId) {
		User user = userRepository.findBySsoIdAndTenantId(ssoId,tenantId);
		return user == null;
	}
	
	
	
	@Override
	public User getUser(Integer id) {
		User user=userRepository.findByIdAndTenantId(id,TenantHolder.getTenantId());
		
		if(user!=null){
			Hibernate.initialize(user.getUserRoles());
            Hibernate.initialize(user.getUserProjects());
            Hibernate.initialize(user.getUserTasks());
        }
		return user;
	}

	@Override
	public User findUserByEmail(String userEmail) {
		
		return userRepository.findByEmail(userEmail);
	}

	@Override
	public void updatePassword(String updatedPassword, Integer id) {
		userRepository.updatePassword(updatedPassword,id);
		
	}

	@Override
	public boolean changePassword(UserDTO user) {
		User updateUser = userRepository.findBySsoIdAndTenantId(user.getSsoId(),TenantHolder.getTenantId());
		
		byte[] base64decodedBytes = Base64.getDecoder().decode(updateUser.getPassword());
		String oldPassword="";
		try {
			oldPassword = new String(base64decodedBytes, "utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
        
		System.err.println("NEW :"+user.getPassword() +" &  OLD :" + oldPassword);
		if(oldPassword.equals(user.getPassword()))
		{	
			
			 String encodedPassword="";
			try {
				encodedPassword = Base64.getEncoder().encodeToString(user.getNewPassword().getBytes("utf-8"));
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			
			updateUser.setPassword(encodedPassword);
			return true;
		}
		else
		{
			return false;
		}	
		
	}

	@Override
	public boolean isUserEmailUnique(String ssoId,String email,String tenantId) {
		User user = userRepository.findByEmailAndTenantId(email,tenantId);
		if(user != null && user.getSsoId().equals(ssoId))
			return true;
		else
			return user == null;
	}
}
