package com.securedweb.service.tenant;

/*@Service("taskStatusCheckerService")
@PropertySource("classpath:cron.properties")*/
public class TaskStatusCheckerServiceImpl{
/*	
	@Autowired
	TaskRepository  taskRepository;
	
	@Scheduled(cron = "0 0/1 0 ? * *")
	//@Scheduled(cron = "0 0/2 * * * ?")
	@Scheduled(fixedDelayString = "${fixedDelay.in.milliseconds}")
	public void taskStatusChecker() throws ParseException {
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
				
				
				if(diffDAYS>=10) {
					task.getTaskStatus().setStatusColour("#ff0000");
				}	
			}
		}
	}
	
*/}
