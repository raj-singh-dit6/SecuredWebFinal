package com.securedweb.service.tenant;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.securedweb.model.tenant.TaskStatus;
import com.securedweb.repository.tenant.TaskStatusRepository;

@Service("taskStatusService")
@Transactional("tenantTransactionManager")
public class TaskStatusServiceImpl implements TaskStatusService{

	@Autowired
	TaskStatusRepository taskStatusRepository;
	
	@Override
	public List<TaskStatus> findAll() {
		List<TaskStatus> taskStatus = new ArrayList<TaskStatus>();
		taskStatus = (List<TaskStatus>) taskStatusRepository.findAll(); 
		for(TaskStatus status:taskStatus)
		{
			if(status!=null)
			{
				Hibernate.initialize(status.getTasks());
			}	
		}	
		return taskStatus; 
	}

	@Override
	public void saveTaskStatus(TaskStatus taskStatus) {
		taskStatusRepository.save(taskStatus);
	}
}
