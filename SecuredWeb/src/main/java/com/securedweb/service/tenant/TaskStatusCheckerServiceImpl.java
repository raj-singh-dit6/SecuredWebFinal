package com.securedweb.service.tenant;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.securedweb.model.tenant.Task;
import com.securedweb.repository.tenant.TaskRepository;

@Service("taskStatusCheckerService")
public class TaskStatusCheckerServiceImpl{
	
	@Autowired
	TaskRepository  taskRepository;
	
	
	@Scheduled(cron = "0 0/1 0 ? * *")
	//@Scheduled(cron = "0 0/2 * * * ?")
	public void taskStatusChecker() throws ParseException {
		//System.err.println("-- 00 : 08 A.M. CRON EXECUTED-- ");
		SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
		List<Task> taskList = (List<Task>) taskRepository.findAll();
		for(Task task : taskList)
		{
			
			String taskStatus = task.getTaskStatus().getStatus();
			if(taskStatus.equals("In Progress"))
			{
				String now =  format.format(new Date());
				String statusSetDate = format.format(task.getStatusSetDate());
				
				Date  d1 = format.parse(statusSetDate);
				Date  d2 = format.parse(now); 
				
				long diff = d2.getTime() - d1.getTime();
				long diffDAYS = diff /  (24 * 60 * 60 * 1000);
				
				//System.err.println(statusSetDate);
				//System.err.println(now);
				
				//System.err.println(diffDAYS);
				
				if(diffDAYS>=10) {
					task.getTaskStatus().setStatusColour("#ff0000");
					//System.err.println(task.getName() + " status flag changed to red ");
				}	
			}
		}
	}
	
}
