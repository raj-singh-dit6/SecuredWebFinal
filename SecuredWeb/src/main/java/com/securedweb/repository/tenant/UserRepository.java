package com.securedweb.repository.tenant;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.securedweb.model.tenant.User;

public interface UserRepository extends CrudRepository<User, Integer>{
	
	List<User> findByTenantId(String tenandId);
	
	User findBySsoId(String SSO_ID);

	User findBySsoIdAndTenantId(String ssoId, String tenantId);

	void deleteBySsoIdAndTenantId(String ssoId, String tenantId);

	User findByIdAndTenantId(Integer id, String tenantId);

	User findByEmail(String userEmail);

	@Modifying
    @Query("update User u set u.password = :password where u.id = :id")
    void updatePassword(@Param("password") String password, @Param("id") Integer id);

	User findByEmailAndTenantId(String userEmail, String tenantId);
}