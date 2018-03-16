package com.securedweb.service.tenant;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.securedweb.dto.tenant.TaskStatusDTO;
import com.securedweb.model.tenant.TaskStatus;
import com.securedweb.repository.tenant.TaskStatusRepository;
import com.securedweb.util.TenantHolder;

@Service("taskStatusService")
@Transactional("tenantTransactionManager")
public class TaskStatusServiceImpl implements TaskStatusService{

	@Autowired
	TaskStatusRepository taskStatusRepository;

	@Override
	public List<TaskStatusDTO> getAllTaskStatus() {
		List<TaskStatus> taskStatusList = (List<TaskStatus>) taskStatusRepository.findByTenantId(TenantHolder.getTenantId()); 
		List<TaskStatusDTO> taskStatusDTOList	= new ArrayList<TaskStatusDTO>();
		for(TaskStatus taskStatus:taskStatusList)
		{
			if(taskStatus!=null)
			{
				Hibernate.initialize(taskStatus.getTasks());
				TaskStatusDTO taskStatusDTO = new TaskStatusDTO();
				taskStatusDTO.setStatus(taskStatus.getStatus());
				taskStatusDTO.setStatusColour(taskStatus.getStatusColour());
				taskStatusDTOList.add(taskStatusDTO);
			}	
		}	
		return taskStatusDTOList; 
	}

	@Override
	public TaskStatusDTO addTaskStatus(TaskStatusDTO taskStatus) {
		TaskStatus newTaskStatus = new TaskStatus();
		newTaskStatus.setStatus(taskStatus.getStatus());
		newTaskStatus.setStatusColour(taskStatus.getStatusColour());
		newTaskStatus.setTenantId(TenantHolder.getTenantId());
		taskStatusRepository.save(newTaskStatus);
		return taskStatus;
	}

	@Override
	public TaskStatus getTaskStatus(Integer id) {
		TaskStatus taskStatus = taskStatusRepository.findByIdAndTenantId(id,TenantHolder.getTenantId());
		return taskStatus;
	}
}
