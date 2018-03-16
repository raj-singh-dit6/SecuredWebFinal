package com.securedweb.repository.tenant;

import org.springframework.data.repository.CrudRepository;

import com.securedweb.model.tenant.UserProject;
import com.securedweb.model.tenant.UserProjectCompositeKey;

public interface UserProjectRepository extends CrudRepository<UserProject,Integer>{

}
