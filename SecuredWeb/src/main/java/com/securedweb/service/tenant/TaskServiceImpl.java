package com.securedweb.service.tenant;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.securedweb.model.tenant.Task;
import com.securedweb.repository.tenant.TaskRepository;

@Service("taskService")
@Transactional("tenantTransactionManager")
public class TaskServiceImpl implements TaskService{

	@Autowired
	TaskRepository taskRepository;
	 
	@Override
	public List<Task> findAll() {
		List<Task> tasks = new ArrayList<Task>();
		for(Task task:tasks)
		{
			 if(tasks!=null)
			 {
				 Hibernate.initialize(task.getUserTasks());
				 Hibernate.initialize(task.getTaskStatus());
				 Hibernate.initialize(task.getProject());
			 }	 
			
		}
		return(List<Task>)taskRepository.findAll();
	}

	@Override
	public void saveTask(Task task) {
		taskRepository.save(task);
	}
	
	
}
