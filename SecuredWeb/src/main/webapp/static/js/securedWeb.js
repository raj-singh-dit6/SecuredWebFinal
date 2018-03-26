function changePassword()
{
	var userId= $('#userId').val();
	var oldpassoword = $('#userCurrentPassword').val();
	var newPassword = $('#userPassword').val();
	var confirmPassword = $('#userConfirmPassword').val();
	
	if(oldpassoword==""){
		 if ($("#userCurrentPassword").parent().next(".validation").length == 0) // only add if not added
	        {
	            $("#userCurrentPassword").parent().after("<div class='validation' style='color:red;margin-bottom: 20px;'>Please enter your current password.</div>");
	      }
		$('#userCurrentPassword').focus();
		return false;
	}else {
        $("#userCurrentPassword").parent().next(".validation").remove(); // remove it
    }
	
	if(newPassword==""){
		 if ($("#userPassword").parent().next(".validation").length == 0) // only add if not added
	        {
	            $("#userPassword").parent().after("<div class='validation' style='color:red;margin-bottom: 20px;'>Please enter your new password.</div>");
	      }
		$('#userPassword').focus();
		return false;
	}else {
        $("#userPassword").parent().next(".validation").remove(); // remove it
    }
	
	
	if(confirmPassword==""){
		if ($("#userConfirmPassword").parent().next(".validation").length == 0) // only add if not added
        {
            $("#userConfirmPassword").parent().after("<div class='validation' style='color:red;margin-bottom: 20px;'>Please enter password to confirm.</div>");
        }
		$('#userConfirmPassword').focus();
		return false;
	}else {
        $("#userConfirmPassword").parent().next(".validation").remove(); // remove it
    }
	
	if(newPassword!=confirmPassword){
		if ($("#userConfirmPassword").parent().next(".validation").length == 0) // only add if not added
        {
            $("#userConfirmPassword").parent().after("<div class='validation' style='color:red;margin-bottom: 20px;'>Passwords did not match, please enter correct passwords.</div>");
        }
		$('#userPassword').focus();
		$('#userConfirmPassword').focus();
		return false;
	}else{
		
		$("#userConfirmPassword").parent().next(".validation").remove(); // remove it
	}
	
	var user = {};
	user.ssoId = userId;
	user.password = oldpassoword;
	user.newPassword = newPassword;
	
	$.ajax({
		type:'POST',
		async: false,
       	url : 'user/changePassword?tenantId='+tenantId,
       	contentType: 'application/json',
       	data : JSON.stringify(user),
       	dataType : 'json',
        beforeSend: function(xhr) {
	           xhr.setRequestHeader(header, token);
	       },
        success: function(status) {
        	bootbox.alert(status.message);
        	if(status.status==200){
        		$('#ChangePasswordModalAjax').modal('hide');
        	}	
        }
    });
}


function loadAllTasksByProjectId(obj)
{
	var projId= obj.value;
	$.ajax({
    	async: false,
    	type: "GET",
        url: "project/load/task/"+projId+"?tenantId="+tenantId,
        beforeSend: function(xhr) {
	           xhr.setRequestHeader(header, token);
	       },
        success: function(tasks) {
        	//alert(tasks);
        	tasks.forEach(function(task){
	            	$("select#assignTask").append('<option id="'+task.id+'" value="'+task.id+'">'+task.name+'</option>"');
	            });
        }
    });
}

function loadAllUsersByProjectId(obj)
{
	var projId= obj.value;
	$.ajax({
    	async: false,
    	type: "GET",
        url: "project/load/user/"+projId+"?tenantId="+tenantId,
        beforeSend: function(xhr) {
	           xhr.setRequestHeader(header, token);
	       },
        success: function(users) {
        	//alert(users);
        	users.forEach(function(user){
	            	$("select#assignUser").append('<option id="'+user.id+'" value="'+user.id+'">'+user.firstName+'</option>"');
	            });
        }
    });
}

function loadAjaxPage(pageType,operation,id)
{	
	var reqURL= "view/ajaxPage/"+pageType+"?tenantId="+tenantId;
	if(pageType=="user")
	{   
		$.ajax({
	    	async: false,
	    	type: "GET",
	        url: reqURL,
	        beforeSend: function(xhr) {
		           xhr.setRequestHeader(header, token);
		       },
	        success: function(response) {
	            $("#UserModalBody").html( response );
	        }
	    });
		$('#UserModalAjax').modal('show');
		if(operation=="add"){
			$("#UserModalHeading").text("Add New User");
			$("#UpdateUserSubmit").hide();
			$.ajax({
		    	async: false,
		    	type: "GET",
		        url: "role/all?tenantId="+tenantId,
		        beforeSend: function(xhr) {
			           xhr.setRequestHeader(header, token);
			       },
		        success: function(roles) {
		            roles.forEach(function(role){
		            	if(role.type.indexOf("USER")!=-1){
		            		$("#userRoles").append('<option id="'+role.id+'" value="'+role.id+'" selected>'+role.type+'</option>"');
		            	}else{
		            		$("#userRoles").append('<option id="'+role.id+'" value="'+role.id+'">'+role.type+'</option>"');	
		            	}
		            });
		        }
		    });
		}
		else if(operation=="edit")
		{  	var ssoId = id;
			var userRoles = [];
			$("#UserModalHeading").text("Update User Details");
			$("#userPasswordDiv").hide();
			$("#userConfirmPasswordDiv").hide();
			$("#userSsoId").prop( "disabled", true);
			$("#AddUserSubmit").hide();
			$.ajax({
		    	async: false,
		    	type: "GET",
		        url: "user/"+ssoId+"?tenantId="+tenantId,
		        beforeSend: function(xhr) {
			           xhr.setRequestHeader(header, token);
			       },
		        success: function(user) {
		        	$("#userFirstName").val(user.firstName);
		        	$("#userLastName").val(user.lastName);
		        	$("#userEmail").val(user.email);
		        	$("#userSsoId").val(user.ssoId);
					user.userRoles.forEach(function(role){
						userRoles.push(role.id);
		            });
		        	 
		        }
		    });
			$.ajax({
		    	async: false,
		    	type: "GET",
		        url: "role/all?tenantId="+tenantId,
		        beforeSend: function(xhr) {
			           xhr.setRequestHeader(header, token);
			       },
		        success: function(roles) {
		            roles.forEach(function(role){
		            	if(userRoles.indexOf(role.id)!=-1){
		            		$("#userRoles").append('<option id="'+role.id+'" value="'+role.id+'" selected>'+role.type+'</option>"');
		            	}else{
		            		$("#userRoles").append('<option id="'+role.id+'" value="'+role.id+'">'+role.type+'</option>"');
		            	}
		            });
		        }
		    });
		}	
	}else if(pageType=="project")
	{
		$.ajax({
	    	async: false,
	    	type: "GET",
	        url: reqURL,
	        beforeSend: function(xhr) {
		           xhr.setRequestHeader(header, token);
		       },
	        success: function(response) {
	            $("#ProjectModalBody").html( response );
	        }
	    });
		$('#ProjectModalAjax').modal('show');
		if(operation=="add"){
			$("#ProjectModalHeading").text("Add New Project");
			$("#UpdateProjectSubmit").hide();
			$.ajax({
		    	async: false,
		    	type: "GET",
		        url: "project/all?tenantId="+tenantId,
		        beforeSend: function(xhr) {
			           xhr.setRequestHeader(header, token);
			       },
		        success: function(projects) {
		        	projects.forEach(function(project){
		            	$("select#projParent").append('<option id="'+project.id+'" value="'+project.id+'">'+project.name+'</option>"');
		            });
		        }
			});
		}	
		else if(operation=="edit")
		{  	var projectId = id;
			var userProjectId;
			$("#ProjectModalHeading").text("Update Project Details");
			$("#AddProjectSubmit").hide();
			$.ajax({
		    	async: false,
		    	type: "GET",
		        url: "project/"+projectId+"?tenantId="+tenantId,
		        beforeSend: function(xhr) {
			           xhr.setRequestHeader(header, token);
			       },
		        success: function(project) {
		        	$("#projName").val(project.name);
		        	$("#projDesc").val(project.description);
		        	$("#projId").val(id);
		        	if(project.parentProject!=null)
		        	{	
		        		userProjectId = project.parentProject.id;
		        	}
		        }
		    });
			$.ajax({
		    	async: false,
		    	type: "GET",
		        url: "project/all?tenantId="+tenantId,
		        beforeSend: function(xhr) {
			           xhr.setRequestHeader(header, token);
			       },
		        success: function(projects) {
		        	projects.forEach(function(project){
		        		if(userProjectId==project.id)
		        		{
		            		$("select#projParent").append('<option id="'+project.id+'" value="'+project.id+'" selected>'+project.name+'</option>"');
		        		}else{
		        			$("select#projParent").append('<option id="'+project.id+'" value="'+project.id+'">'+project.name+'</option>"');
		        		}
	        		});
		        }
		    });
		}
	}else if(pageType=="userProject")
	{
			$.ajax({
		    	async: false,
		    	type: "GET",
		        url: reqURL,
		        beforeSend: function(xhr) {
			           xhr.setRequestHeader(header, token);
			       },
		        success: function(response) {
		        	$("#AssignProjectModalBody").html( response );
		        }
		    });
			$('#AssignProjectModalAjax').modal('show');
			if(operation=="add"){
				$("#AssignProjectModalHeading").text("Assign Project to User");
				$("#UpdateUserProjectSubmit").hide();
				$.ajax({
			    	async: false,
			    	type: "GET",
			        url: "project/all?tenantId="+tenantId,
			        beforeSend: function(xhr) {
				           xhr.setRequestHeader(header, token);
				       },
			        success: function(projects) {
			        	projects.forEach(function(project){
			            	$("select#assignProject").append('<option id="'+project.id+'" value="'+project.id+'">'+project.name+'</option>"');
			            });
			        }
				});
				$.ajax({
			    	async: false,
			    	type: "GET",
			        url: "user/all?tenantId="+tenantId,
			        beforeSend: function(xhr) {
				           xhr.setRequestHeader(header, token);
				       },
			        success: function(users) {
			        	users.forEach(function(user){
			            	$("select#assignUser").append('<option id="'+user.id+'" value="'+user.id+'">'+user.firstName+'</option>"');
			            });
			        }
				});
			}	
			
	}else if(pageType=="task")
	{
			$.ajax({
		    	async: false,
		    	type: "GET",
		        url: reqURL,
		        beforeSend: function(xhr) {
			           xhr.setRequestHeader(header, token);
			       },
		        success: function(response) {
		            $("#TaskModalBody").html( response );
		        }
		    });
			$('#TaskModalAjax').modal('show');
			if(operation=="add"){
				$("#TaskModalHeading").text("Add New Task");
				$("#UpdateTaskSubmit").hide();
				$.ajax({
			    	async: false,
			    	type: "GET",
			        url: "project/all?tenantId="+tenantId,
			        beforeSend: function(xhr) {
				           xhr.setRequestHeader(header, token);
				       },
			        success: function(projects) {
			        	projects.forEach(function(project){
			            	$("select#taskProject").append('<option id="'+project.id+'" value="'+project.id+'">'+project.name+'</option>"');
			            });
			        }
				});
				
				$.ajax({
			    	async: false,
			    	type: "GET",
			        url: "taskStatus/all?tenantId="+tenantId,
			        beforeSend: function(xhr) {
				           xhr.setRequestHeader(header, token);
				       },
			        success: function(taskStatuses) {
			        	taskStatuses.forEach(function(taskStatus){
			            	$("select#taskStatus").append('<option id="'+taskStatus.id+'" value="'+taskStatus.id+'">'+taskStatus.status+'</option>"');
			            });
			        }
				});    
			}	
			else if(operation=="edit")
			{  	var taskId = id;
				var taskProjectId ="";
				var taskStatusId = "";
				$("#TaskModalHeading").text("Update Task Details");
				$("#AddTaskSubmit").hide();
				$.ajax({
			    	async: false,
			    	type: "GET",
			        url: "task/"+taskId+"?tenantId="+tenantId,
			        beforeSend: function(xhr) {
				           xhr.setRequestHeader(header, token);
				       },
			        success: function(task) {
			        	$("#taskName").val(task.name);
			        	$("#taskDesc").val(task.description);
			        	$("#taskId").val(task.id);
			        	taskProjectId = task.project.id;
			        	taskStatusId = task.taskStatus.id;
			        	
			        }
			    });
				
				$.ajax({
			    	async: false,
			    	type: "GET",
			        url: "project/all?tenantId="+tenantId,
			        beforeSend: function(xhr) {
				           xhr.setRequestHeader(header, token);
				       },
			        success: function(projects) {
			        	projects.forEach(function(project){
			        		if(taskProjectId==project.id){
			            		$("select#taskProject").append('<option id="'+project.id+'" value="'+project.id+'" selected>'+project.name+'</option>"');
			        		}else{
			        			$("select#taskProject").append('<option id="'+project.id+'" value="'+project.id+'">'+project.name+'</option>"');
			        		}
			        	});
			        }
				});
				
				$.ajax({
			    	async: false,
			    	type: "GET",
			        url: "taskStatus/all?tenantId="+tenantId,
			        beforeSend: function(xhr) {
				           xhr.setRequestHeader(header, token);
				       },
			        success: function(taskStatuses) {
			        	taskStatuses.forEach(function(taskStatus){
			        		if(taskStatusId==taskStatus.id){
			        			$("select#taskStatus").append('<option id="'+taskStatus.id+'" value="'+taskStatus.id+'" selected>'+taskStatus.status+'</option>"');
			        		}else
			        		{
			        			$("select#taskStatus").append('<option id="'+taskStatus.id+'" value="'+taskStatus.id+'">'+taskStatus.status+'</option>"');
			        		}
			        
			        	});
			        }
				});
			}
			
	}else if(pageType=="userTask")
	{
			$.ajax({
		    	async: false,
		    	type: "GET",
		        url: reqURL,
		        beforeSend: function(xhr) {
			           xhr.setRequestHeader(header, token);
			       },
		        success: function(response) {
		        	$("#AssignTaskModalBody").html( response );
		        }
		    });
			$('#AssignTaskModalAjax').modal('show');
			
			if(operation=="add"){
				$("#AssignTaskModalHeading").text("Assign Task to User");
				$("#UpdateUserTaskSubmit").hide();
				$.ajax({
			    	async: false,
			    	type: "GET",
			        url: "project/all?tenantId="+tenantId,
			        beforeSend: function(xhr) {
				           xhr.setRequestHeader(header, token);
				       },
			        success: function(projects) {
			        	projects.forEach(function(project){
			            	$("select#assignProject").append('<option id="'+project.id+'" value="'+project.id+'" >'+project.name+'</option>"');
			            });
			        }
				});
			}
			
	}else if(pageType=="taskStatus")
	{
		
		$.ajax({
	    	async: false,
	    	type: "GET",
	        url: reqURL,
	        beforeSend: function(xhr) {
		           xhr.setRequestHeader(header, token);
		       },
	        success: function(response) {
	            $("#TaskStatusModalBody").html( response );
	        }
	    });
		$('#TaskStatusModalAjax').modal('show');
		if(operation=="add"){
			$("#TaskStatusModalHeading").text("Add New Task Status");
			$("#UpdateTaskStatusSubmit").hide();
		}	
		else if(operation=="edit")
		{  	
			var taskStatusId = id;
			$("#TaskStatusModalHeading").text("Update Task Status Details");
			$("#AddTaskStatusSubmit").hide();
			$.ajax({
		    	async: false,
		    	type: "GET",
		        url: "taskStatus/"+taskStatusId+"?tenantId="+tenantId,
		        beforeSend: function(xhr) {
			           xhr.setRequestHeader(header, token);
			       },
		        success: function(taskStatus) {
		        	$("#taskStatusName").val(taskStatus.status);
		        	$("select#taskStatusColour").val(taskStatus.statusColour);
		        	$("#taskStatusId").val(taskStatus.id);
		        }
		    });
			
		}
		
	}else if(pageType=="userTaskByUser")
	{
		debugger
		$.ajax({
	    	async: false,
	    	type: "GET",
	        url: reqURL,
	        beforeSend: function(xhr) {
		           xhr.setRequestHeader(header, token);
		       },
	        success: function(response) {
	        	$("#AssignTaskModalBody").html( response );
	        }
	    });
		$('#AssignTaskModalAjax').modal('show');
		
		if(operation=="add"){
			$("#AssignTaskModalHeading").text("Assign Task");
			$("#UpdateUserTaskSubmit").hide();
			$("#assignUserDiv").hide();
			$.ajax({
		    	async: false,
		    	type: "GET",
		        url: "userProject/load/projects?tenantId="+tenantId,
		        beforeSend: function(xhr) {
			           xhr.setRequestHeader(header, token);
			       },
		        success: function(userProjects) {
		        	userProjects.forEach(function(userProject){
		            	$("select#assignProject").append('<option id="'+userProject.project.id+'" value="'+userProject.project.id+'"  >'+userProject.project.name+'</option>"');
		            });
		        }
			});
		}
	}else if(pageType=="updateTaskByUser"){
		$.ajax({
	    	async: false,
	    	type: "GET",
	        url: reqURL,
	        beforeSend: function(xhr) {
		           xhr.setRequestHeader(header, token);
		       },
	        success: function(response) {
	            $("#UpdateTaskByUserModalBody").html( response );
	        }
	    });
		$('#UpdateTaskByUserModalAjax').modal('show');
		if(operation=="edit"){
			var userTaskId = id;
			var selectedTaskStatus = "";
			$("#UpdateTaskByUserModalHeading").text("Update Task Details");
			var userTaskId = id;
			$.ajax({
		    	async: false,
		    	type: "GET",
		        url: "userTask/"+userTaskId+"?tenantId="+tenantId,
		        beforeSend: function(xhr) {
			           xhr.setRequestHeader(header, token);
			       },
		        success: function(userTask) {
		        	$("#userTaskId").val(userTask.id);
		        	$("#userProjectName").val(userTask.project.name);
		        	$("#userTaskName").val(userTask.task.name);
		        	$("#userTaskDesc").val(userTask.task.description);
		        	selectedTaskStatus = userTask.task.taskStatus.id;
		        	debugger
		        }
		    });
			
			$.ajax({
		    	async: false,
		    	type: "GET",
		        url: "taskStatus/all?tenantId="+tenantId,
		        beforeSend: function(xhr) {
			           xhr.setRequestHeader(header, token);
			       },
		        success: function(taskStatuses) {
		        	taskStatuses.forEach(function(taskStatus){
		        		debugger
		        		if(taskStatus.id==selectedTaskStatus){
		        			$("select#userTaskStatus").append('<option id="'+taskStatus.id+'" value="'+taskStatus.id+'" selected>'+taskStatus.status+'</option>"');
		            	}else{
		            		$("select#userTaskStatus").append('<option id="'+taskStatus.id+'" value="'+taskStatus.id+'">'+taskStatus.status+'</option>"');
		            	}				            
		            });
		        }
			});
		}	

	}else if(pageType=="projectDocuments"){
		$('.modal').modal('hide');
		var projectId = id;
		debugger
		$.ajax({
	    	async: false,
	    	type: "GET",
	        url: reqURL,
	        beforeSend: function(xhr) {
		           xhr.setRequestHeader(header, token);
		       },
	        success: function(response) {
	            $("#DocumentsModalBody").html( response );
	        }
	    });
		$("#DocumentsModalHeading").text("Manage Project Documents");
		$("#DocumentsModalAjax").modal('show');
		$('#addDocumentsFooter').prepend('<button id="UploadDocuments" type="button" class="btn btn-primary" data-toggle="modal" onCLick="loadAjaxPage(\'uploadDocuments\',\'\',\''+projectId+'\')" data-target="#UploadDocumentsModalAjax">Upload Documents</button>');
		
		fillProjectDocumentsInDataTable(projectId);
		
	}else if(pageType=="uploadDocuments"){
		var projectId = id;
		$('.modal').modal('hide');
		$.ajax({
	    	async: false,
	    	type: "GET",
	        url: reqURL,
	        beforeSend: function(xhr) {
		           xhr.setRequestHeader(header, token);
		       },
	        success: function(response) {
	            $("#UploadDocumentsModalBody").html( response );
	        }
	    });
		$("#UploadDocumentsModalAjax").modal('show');
		$("#UploadDocumentsModalHeading").text("Upload Project Documents");
		$('#addUploadFooter').append('<button id="UploadMoreDocuments" type="button" class="btn btn-primary" onClick="uploadProjectDocuments(\''+projectId+'\')">Upload</button>');
		$('#addUploadFooter').append('<button type="button" class="btn btn-secondary" data-toggle="modal" onClick="loadAjaxPage(\'projectDocuments\',\'\',\''+projectId+'\')" data-target="#DocumentsModalAjax">Go back to manage documents</button>');
	}
	else if(pageType=="changePassword"){
		
		$.ajax({
	    	async: false,
	    	type: "GET",
	        url: reqURL,
	        beforeSend: function(xhr) {
		           xhr.setRequestHeader(header, token);
		       },
	        success: function(response) {
	            $("#ChangePasswordModalBody").html( response );
	        }
	    });
		$("#ChangePasswordModalAjax").modal('show');
	}
	
}


function fillProjectDocumentsInDataTable(projectId)
{
	debugger
	var documents = null;
	$.ajax({
    	async: false,
    	type: "GET",
        url: "document/all/project/"+projectId+"?tenantId="+tenantId,
        beforeSend: function(xhr) {
	           xhr.setRequestHeader(header, token);
	       },
        success: function(documentList) {
        	documents =  documentList 
        }
    });
	
	var dataSet=[];
	documents.forEach(function(document){
		var dataEach = [];
		dataEach.push(document.id);
		dataEach.push(document.name);
		dataEach.push(document.description);
		dataEach.push(document.location);
		dataSet.push(dataEach);
	});

	if ($.fn.DataTable.isDataTable("#documentsTable")) {
		  $('#documentsTable').DataTable().clear().destroy();
		}
       
	$('#documentsTable').DataTable( {
		data: dataSet,
		columns: [
            { title: "Document Id" },
            { title: "Document Name	" },
            { title: "Document Description" },
            { title: "Document Location" },
            { "render": function (data, type, full, meta) {
               	return '<a data-fancybox="'+full[1]+'"  data-type="iframe" data-src="files/'+full[3]+'" class="fancybox btn btn-success custom-width" href="javascript:;" )">Download</a>'} },
            { "render": function (data, type, full, meta) {
               	return '<a class="btn btn-danger custom-width" href=# id=\'' + full[0] + '\' onClick="deleteById(\'projectDocument\',\'' + full[0] + '\')" >Delete</a>'} },
        ],
        "scrollY": 200,
        "scrollX": true,
	    select	   : true,
	    rowReorder: true,
	    colReorder: true,
	    "columnDefs": [
            {
                "targets": [ 0 ],
                "visible": false,
                "searchable": false
            },
            {
                "targets": [ 3 ],
                "visible": false,
                "searchable": false
            }]
    });	
}

function downloadById(projectId,documentId)
{	
	$.ajax({
    	async: false,
    	type: "GET",
        url: "document/download/"+projectId+"/"+documentId+"?tenantId="+tenantId,
        beforeSend: function(xhr) {
	           xhr.setRequestHeader(header, token);
	       },
        success: function(status) {
        	if(status.status==200){
				alert(status);
        	}	
        }
    });

}
function deleteById(pageType,id)
{	
	if(pageType=="user")
	{
		var ssoId = id;
		var action=bootbox.confirm({
	    message: "Are you sure , you want to delete record for this user ?",
	    buttons: {
	        confirm: {
	            label: 'Yes',
	            className: 'btn-success'
	        },
	        cancel: {
	            label: 'No',
	            className: 'btn-danger'
	        }
	    },
	    callback: function (result) {
	    	if(result)
	    	{
	    		$.ajax({
	    	    	async: false,
	    	    	type: "DELETE",
	    	        url: "user/delete/"+ssoId+"?tenantId="+tenantId,
	    	        beforeSend: function(xhr) {
	    		           xhr.setRequestHeader(header, token);
	    		       },
	    	        success: function(status) {
	    	        	if(status.status==200){
	    	        		$('#successMessage').html(status.message);
	    		            $('#successAlert').show();
	    		            manageUsers();
	    	        	}else{
	    	        		$('#warningMessage').html(status.message);
	    	        		$('#warningAlert').show();
	    		            manageUsers();
	    	        	}	
	    	        }
	    	    });
	    	}	
	    	}
		});
	}else if(pageType=="project")
	{ 
		var projectID= id;	
		var action=bootbox.confirm({
	    message: "Are you sure , you want to delete record for this project ?",
	    buttons: {
	        confirm: {
	            label: 'Yes',
	            className: 'btn-success'
	        },
	        cancel: {
	            label: 'No',
	            className: 'btn-danger'
	        }
	    },
	    callback: function (result) {
	    	if(result)
	    	{
	    		$.ajax({
	    	    	async: false,
	    	    	type: "DELETE",
	    	        url: "project/delete/"+id+"?tenantId="+tenantId,
	    	        beforeSend: function(xhr) {
	    		           xhr.setRequestHeader(header, token);
	    		       },
	    	        success: function(status) {
	    	        	if(status.status==200){
	    	        		$('#successMessage').html(status.message);
	    		            $('#successAlert').show();
	    		            manageProjects();
	    	        	}else{
	    	        		$('#warningMessage').html(status.message);
	    	        		$('#warningAlert').show();
	    	        	}	
	    	        }
	    	    });
	    	}	
	    	}
		});
	}else if(pageType=="task")
	{
		
		var taskId = id;
		var action=bootbox.confirm({
	    message: "Are you sure , you want to delete record for this task ?",
	    buttons: {
	        confirm: {
	            label: 'Yes',
	            className: 'btn-success'
	        },
	        cancel: {
	            label: 'No',
	            className: 'btn-danger'
	        }
	    },
	    callback: function (result) {
	    	if(result)
	    	{
	    		$.ajax({
	    	    	async: false,
	    	    	type: "DELETE",
	    	        url: "task/delete/"+taskId+"?tenantId="+tenantId,
	    	        beforeSend: function(xhr) {
	    		           xhr.setRequestHeader(header, token);
	    		       },
	    	        success: function(status) {
	    	        	if(status.status==200){
	    	        		$('#successMessage').html(status.message);
	    		            $('#successAlert').show();
	    		            manageTasks();
	    	        	}else{
	    	        		$('#warningMessage').html(status.message);
	    	        		$('#warningAlert').show();
	    	        		manageTasks();
	    	        	}	
	    	        }
	    	    });
	    	}	
	    	}
		});
	}else if(pageType=="taskStatus")
	{
		var taskStatusId = id;
		var action=bootbox.confirm({
	    message: "Are you sure , you want to delete record for this task status ?",
	    buttons: {
	        confirm: {
	            label: 'Yes',
	            className: 'btn-success'
	        },
	        cancel: {
	            label: 'No',
	            className: 'btn-danger'
	        }
	    },
	    callback: function (result) {
	    	if(result)
	    	{
	    		$.ajax({
	    	    	async: false,
	    	    	type: "DELETE",
	    	        url: "taskStatus/delete/"+taskStatusId+"?tenantId="+tenantId,
	    	        beforeSend: function(xhr) {
	    		           xhr.setRequestHeader(header, token);
	    		       },
	    	        success: function(status) {
	    	        	if(status.status==200){
	    	        		$('#successMessage').html(status.message);
	    		            $('#successAlert').show();
	    		            manageTaskStatus();
	    	        	}else{
	    	        		$('#warningMessage').html(status.message);
	    	        		$('#warningAlert').show();
	    	        		 manageTaskStatus();
	    	        	}	
	    	        }
	    	    });
	    	}	
	    	}
		});
	}else if(pageType=="userProject")
	{
		var userProjectId = id;
		var action=bootbox.confirm({
	    message: "Are you sure , you want to deassign project for this user?",
	    buttons: {
	        confirm: {
	            label: 'Yes',
	            className: 'btn-success'
	        },
	        cancel: {
	            label: 'No',
	            className: 'btn-danger'
	        }
	    },
	    callback: function (result) {
	    	if(result)
	    	{
	    		$.ajax({
	    	    	async: false,
	    	    	type: "DELETE",
	    	        url: "userProject/delete/"+userProjectId+"?tenantId="+tenantId,
	    	        beforeSend: function(xhr) {
	    		           xhr.setRequestHeader(header, token);
	    		       },
	    	        success: function(status) {
	    	        	if(status.status==200){
	    	        		$('#successMessage').html(status.message);
	    		            $('#successAlert').show();
	    		            manageUserProjects();	
	    	        	}else{
	    	        		$('#warningMessage').html(status.message);
	    	        		$('#warningAlert').show();
	    	        	}	
	    	        }
	    	    });
	    	}	
	    	}
		});
		
	}else if(pageType=="projectDocument")
	{
		
		var documentId = id;
		var action=bootbox.confirm({
	    message: "Are you sure , you want to delete this document?",
	    buttons: {
	        confirm: {
	            label: 'Yes',
	            className: 'btn-success'
	        },
	        cancel: {
	            label: 'No',
	            className: 'btn-danger'
	        }
	    },
	    callback: function (result) {
	    	if(result)
	    	{
	    		$.ajax({
	    	    	async: false,
	    	    	type: "DELETE",
	    	        url: "document/delete/"+documentId+"?tenantId="+tenantId,
	    	        beforeSend: function(xhr) {
	    		           xhr.setRequestHeader(header, token);
	    		       },
	    	        success: function(status) {
	    	        	if(status.status==200){
	    	        	}	
	    	        }
	    	    });
	    	}	
	    	}
		});
	}
}


function uploadProjectDocuments(projectId){
	
	
	if($('#multipleFiles').val()=="")
	{

		if ($("#multipleFiles").parent().next(".validation").length == 0) // only add if not added
        {
            $("#multipleFiles").parent().after("<div class='validation' style='color:red;margin-bottom: 20px;'>Please select at least one file.</div>");
        }
		$('#multipleFiles').focus();
		return false;
	}else{
		$("#multipleFiles").parent().next(".validation").remove(); // remove it
	}
	
	if($('textarea#description').val()=="")
	{
		if ($("textarea#description").parent().next(".validation").length == 0) // only add if not added
        {
            $("textarea#description").parent().after("<div class='validation' style='color:red;margin-bottom: 20px;'>Please add description files to be uploaded.</div>");
        }
		
		$('textarea#description').focus();
		return false;
		
	}else{
		$("textarea#description").parent().next(".validation").remove(); // remove it
	}

    var form = $('#UploadDocumentForm')[0];
    var data = new FormData(form);

    $('#UploadMoreDocuments').prop('disabled', true);
	$.ajax({
	      type : 'POST',
	      enctype: 'multipart/form-data',
	      url : 'document/upload/project/'+projectId+'?tenantId='+tenantId,
	      data: data,
	      contentType: false, 
          processData: false, 
	      xhr: function(){
	        //Get XmlHttpRequest object
	         var xhr = $.ajaxSettings.xhr() ;
	        
	        //Set onprogress event handler 
	         xhr.upload.onprogress = function(event){
	          	var perc = Math.round((event.loaded / event.total) * 100);
	          	$('#progressBar').text(perc + '%');
	          	$('#progressBar').css('width',perc + '%');
	         };
	         return xhr ;
	    	},
	    	beforeSend: function( xhr ) {
	    		 xhr.setRequestHeader(header, token);
	    		//Reset alert message and progress bar
	    		$('#alertMsg').text('');
	    		$('#progressBar').text('');
	    		$('#progressBar').css('width','0%');
	    	},	
	    	success : function(status){
	    		
	    		if(status.status==200){
	    			$('#multipleFiles').val("");
	    			$('#UploadMoreDocuments').prop('disabled', false);
	    			$('textarea#description').val("");
	    			$('#progressBar').text('');
		    		$('#progressBar').css('width','0%');
	    			bootbox.alert(status.message);
	    			
	    		}else{
	    			$('#multipleFiles').val("");
	    			$('#UploadMoreDocuments').prop('disabled', false);
	    			$('textarea#description').val("");
	    			$('#progressBar').text('');
		    		$('#progressBar').css('width','0%');
	    		}
				
	    	}	
	    });
	}

function addProject(){
	
	var projName=$('#projName').val();
	var projDesc =$('#projDesc').val();;

	if(projName=="")
	{
		if ($("projName").parent().next(".validation").length == 0) // only add if not added
        {
            $("projName").parent().after("<div class='validation' style='color:red;margin-bottom: 20px;'>Please enter project name.</div>");
        }
		
		$('#projName').focus();
		return false;
	}else{
		$("#projName").parent().next(".validation").remove(); // remove it
	}
	
	if(projDesc==""){
		if ($("projDesc").parent().next(".validation").length == 0) // only add if not added
        {
            $("projDesc").parent().after("<div class='validation' style='color:red;margin-bottom: 20px;'>Please add description for project.</div>");
        }
		$('#projDesc').focus();
		return false;
	}else{
		$("#projDesc").parent().next(".validation").remove(); // remove it
	}

	var parentProject = {};
	$('select#projParent option').each(function() {
		if (this.selected && $(this).val()!="" )
		{	
		    	parentProject.id=$(this).val();
		    	parentProject.name=$(this).text();
		}
	});
	
	var project = {};
    project.name 	= projName;
    project.description 	= projDesc;
    project.parentProject	= parentProject;
    
    alert(JSON.stringify(project));
    
	$.ajax({
		   type:'POST',
	       async: false,
	       url : 'project/add?tenantId='+tenantId,
	       contentType: 'application/json',
	       data : JSON.stringify(project),
	       dataType : 'json',
	       beforeSend: function(xhr) {
	    		
	    	   xhr.setRequestHeader(header, token);
	       },
	       success : function(status) {
	    	   if(status.status==200){
		        	$('#successMessage').html(status.message);
		        	$('#ProjectModalAjax').modal('hide');
		            $('#successAlert').show();
		            manageProjects();
		          }else{
		        	$('#warningMessage').html(status.message);
		        	$('#ProjectModalAjax').modal('hide');  
		        	$('#warningAlert').show();
		          }
	       }
	}); 
}

function updateTask()
{
	
	var taskStatus = {};
	taskStatus.id = $('select#taskStatus option:selected').val(); 
	taskStatus.status = $('select#taskStatus option:selected').text();
		
	var project = {};	
	project.id 	= $('select#taskProject option:selected').val();
	project.name = $('select#taskProject option:selected').text();
    
	
	var task = {};
	task.id = $('#taskId').val();
	task.name = $('#taskName').val();
	task.description = $('#taskDesc').val();
	task.project = project;
	task.taskStatus =taskStatus;

	
	if(project.name=="")
	{

		if ($("select#taskProject").parent().next(".validation").length == 0) // only add if not added
        {
            $("select#taskProject").parent().after("<div class='validation' style='color:red;margin-bottom: 20px;'>Please select a project for the task.</div>");
        }
		$("select#taskProject").focus();
		return false;
	}else{
		$("select#taskProject").parent().next(".validation").remove(); // remove it
	}

	
	
	if(taskStatus.status=="")
	{
		if ($("select#taskStatus").parent().next(".validation").length == 0) // only add if not added
        {
            $("select#taskStatus").parent().after("<div class='validation' style='color:red;margin-bottom: 20px;'>Please select a task status for task.</div>");
        }
		$('#select#taskStatus').focus();
		return false;
		
	}else{
		$("select#taskStatus").parent().next(".validation").remove(); // remove it
	}
	
	if(task.name =="")
	{
		if ($("#taskName").parent().next(".validation").length == 0) // only add if not added
        {
            $("#taskName").parent().after("<div class='validation' style='color:red;margin-bottom: 20px;'>Please enter a task name.</div>");
        }
		$('#taskName').focus();
		return false;
		
	}else{
		$("#taskName").parent().next(".validation").remove(); // remove it
	}
	

	if(task.description =="")
	{
		if ($("#taskDesc").parent().next(".validation").length == 0) // only add if not added
        {
            $("#taskDesc").parent().after("<div class='validation' style='color:red;margin-bottom: 20px;'>Please enter a task description.</div>");
        }
		$('#taskDesc').focus();
		return false;
		
	}else{
		$("#taskDesc").parent().next(".validation").remove(); // remove it
	}
	
	
	//alert(JSON.stringify(task));
	
	$.ajax({
       type:'POST',
       async: false,
       url : 'task/update?tenantId='+tenantId,
       contentType: 'application/json',
       data : JSON.stringify(task),
       dataType : 'json',
       beforeSend: function(xhr) {
           xhr.setRequestHeader(header, token);
       },
       success : function(status) {
    	   if(status.status==200){
	        	$('#successMessage').html(status.message);
	        	$('#TaskModalAjax').modal('hide');
	            $('#successAlert').show();
	            manageTasks();
	          }else{
	        	$('#warningMessage').html(status.message);
	        	$('#TaskModalAjax').modal('hide');  
	        	manageTasks();
	          }
       }
 });
} 

function updateTaskStatus()
{	
	var taskStatus = {};
	taskStatus.id 			= $('#taskStatusId').val();
	taskStatus.status 		= $('#taskStatusName').val();
	taskStatus.statusColour = $('select#taskStatusColour option:selected').val();
    
	if(taskStatus.status =="")
	{
		if ($("#taskStatusName").parent().next(".validation").length == 0) // only add if not added
        {
            $("#taskStatusName").parent().after("<div class='validation' style='color:red;margin-bottom: 20px;'>Please enter a task status.</div>");
        }
		$('#taskStatusName').focus();
		return false;
		
	}else{
		$("#taskStatusName").parent().next(".validation").remove(); // remove it
	}
	
	if(taskStatus.statusColour=="")
	{
		if ($("#taskStatusColour").parent().next(".validation").length == 0) // only add if not added
        {
            $("#taskStatusColour").parent().after("<div class='validation' style='color:red;margin-bottom: 20px;'>Please select a task status indicator.</div>");
        }
		$('#taskStatusColour').focus();
		return false;
		
	}else{
		$("#taskStatusColour").parent().next(".validation").remove(); // remove it
	}
	
    $.ajax({
       type:'POST',
       async: false,
       url : 'taskStatus/update?tenantId='+tenantId,
       contentType: 'application/json',
       data : JSON.stringify(taskStatus),
       dataType : 'json',
       beforeSend: function(xhr) {
           xhr.setRequestHeader(header, token);
       },
       success : function(status) {
    	   if(status.status==200){
	        	$('#successMessage').html(status.message);
	        	$('#TaskStatusModalAjax').modal('hide');
	            $('#successAlert').show();
	            manageTaskStatus()
	          }else{
	        	$('#warningMessage').html(status.message);
	        	$('#TaskStatusModalAjax').modal('hide');  
	            $('#warningAlert').show();
	          }
       }
 });
	
}

function updateProject()
{	
	var projName=$('#projName').val();
	var projDesc =$('#projDesc').val();;

	if(projName=="")
	{
		if ($("#projName").parent().next(".validation").length == 0) // only add if not added
        {
            $("#projName").parent().after("<div class='validation' style='color:red;margin-bottom: 20px;'>Please enter project name.</div>");
        }
		$('#projName').focus();
		return false;
		
	}else{
		$("#projName").parent().next(".validation").remove(); // remove it
	}

	if(projDesc==""){
		if ($("#projDesc").parent().next(".validation").length == 0) // only add if not added
        {
            $("#projDesc").parent().after("<div class='validation' style='color:red;margin-bottom: 20px;'>Please add project description.</div>");
        }
		$('#projDesc').focus();
		return false;
		
	}else{
		$("#projDesc").parent().next(".validation").remove(); // remove it
	}
	
	var parentProject = {};
	$('select#projParent option').each(function() {
	    if (this.selected)
	    {
	    	if($(this).val()!="")
	    	{	
		    	parentProject.id=$(this).val();
		    	parentProject.name=$(this).text();
	    	}
	    }	
	});
	
	var project = {};
	project.id = $('#projId').val();
    project.name 	= projName;
    project.description 	= projDesc;
    project.parentProject	= parentProject;
    
    $.ajax({
       type:'POST',
       async: false,
       url : 'project/update?tenantId='+tenantId,
       contentType: 'application/json',
       data : JSON.stringify(project),
       dataType : 'json',
       beforeSend: function(xhr) {
           xhr.setRequestHeader(header, token);
       },
       success : function(status) {
    	   if(status.status==200){
	        	$('#successMessage').html(status.message);
	        	$('#ProjectModalAjax').modal('hide');
	            $('#successAlert').show();
	            manageProjects();
	          }else{
	        	$('#warningMessage').html(status.message);
	        	$('#ProjectModalAjax').modal('hide');  
	            $('#warningAlert').show();
	          }
       }
 });
	
}

function updateUser()
{	
    var firstName 		= $('#userFirstName').val();
    var lastName 		= $('#userLastName').val();
    var email 			= $('#userEmail').val();
    var emailRegex 		= /^([\w-\.]+)@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.)|(([\w-]+\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\]?)$/;
	if(firstName=="")
	{
		if ($("#userFirstName").parent().next(".validation").length == 0) // only add if not added
        {
            $("#userFirstName").parent().after("<div class='validation' style='color:red;margin-bottom: 20px;'>Please enter first name.</div>");
        }
		$('#userFirstName').focus();
		return false;
		
	}else{
		$("#userFirstName").parent().next(".validation").remove(); // remove it
	}

	if(lastName==""){
		if ($("#userLastName").parent().next(".validation").length == 0) // only add if not added
        {
            $("#userLastName").parent().after("<div class='validation' style='color:red;margin-bottom: 20px;'>Please enter last name.</div>");
        }
		$('#userLastName').focus();
		return false;
		
	}else{
		$("#userLastName").parent().next(".validation").remove(); // remove it
	}

	if(email==""){
		if ($("#userEmail").parent().next(".validation").length == 0) // only add if not added
        {
            $("#userEmail").parent().after("<div class='validation' style='color:red;margin-bottom: 20px;'>Please enter email id.</div>");
        }
		$('#userEmail').focus();
		return false;
		
	}else{
		$("#userEmail").parent().next(".validation").remove(); // remove it
	}

	if(!emailRegex.test(email)){
		if ($("#userEmail").parent().next(".validation").length == 0) // only add if not added
        {
            $("#userEmail").parent().after("<div class='validation' style='color:red;margin-bottom: 20px;'>Please enter a valid email id.</div>");
        }
		$('#userEmail').focus();
		return false;
		
	}else{
		$("#userEmail").parent().next(".validation").remove(); // remove it
	}
	
	var userRoles = [];
	$.each($("#userRoles option:selected"), function(){            
		var role={};
		role.id=$(this).val();
		role.type=$(this).text();
		userRoles.push(role);
	});
	
    var user = {};
    user.firstName 	= $('#userFirstName').val();
    user.lastName 	= $('#userLastName').val();
    user.ssoId 		= $('#userSsoId').val();
    user.email 		= $('#userEmail').val();
    user.userRoles	= userRoles;
	
    $.ajax({
	       type:'POST',
	       async: false,
	       url : 'user/update?tenantId='+tenantId,
	       contentType: 'application/json',
	       data : JSON.stringify(user),
	       dataType : 'json',
	       beforeSend: function(xhr) {
	           xhr.setRequestHeader(header, token);
	       },
	       success : function(status) {
	       
	          if(status.status==200){
	        	$('#successMessage').html(status.message);
	        	$('#UserModalAjax').modal('hide');
	            $('#successAlert').show();
	            manageUsers();
	          }else{
	        	$('#warningMessage').html(status.message);
	        	$('#UserModalAjax').modal('hide');  
	            $('#warningAlert').show();
	          }
	       }
	 });
}

function addUser(){

    var firstName 		= $('#userFirstName').val();
    var lastName 		= $('#userLastName').val();
    var ssoId 			= $('#userSsoId').val();
    var password 		= $('#userPassword').val();
    var confirmPassword = $('#userConfirmPassword').val();
    var email 			= $('#userEmail').val();	
    var emailRegex 		= /^([\w-\.]+)@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.)|(([\w-]+\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\]?)$/;
	if(firstName=="")
	{
		if ($("#userFirstName").parent().next(".validation").length == 0) // only add if not added
        {
            $("#userFirstName").parent().after("<div class='validation' style='color:red;margin-bottom: 20px;'>Please enter first name.</div>");
        }
		$('#userFirstName').focus();
		return false;
		
	}else{
		$("#userFirstName").parent().next(".validation").remove(); // remove it
	}

	if(lastName==""){
		if ($("#userLastName").parent().next(".validation").length == 0) // only add if not added
        {
            $("#userLastName").parent().after("<div class='validation' style='color:red;margin-bottom: 20px;'>Please enter last name.</div>");
        }
		$('#userLastName').focus();
		return false;
		
	}else{
		$("#userLastName").parent().next(".validation").remove(); // remove it
	}

	if(ssoId==""){
		if ($("#userSsoId").parent().next(".validation").length == 0) // only add if not added
        {
            $("#userSsoId").parent().after("<div class='validation' style='color:red;margin-bottom: 20px;'>Please enter user id.</div>");
        }
		$('#userSsoId').focus();
		return false;
		
	}else{
		$("#userSsoId").parent().next(".validation").remove(); // remove it
	}


	if(password==""){
		if ($("#userPassword").parent().next(".validation").length == 0) // only add if not added
        {
            $("#userPassword").parent().after("<div class='validation' style='color:red;margin-bottom: 20px;'>Please enter password.</div>");
        }
		$('#userPassword').focus();
		return false;
		
	}else{
		$("#userPassword").parent().next(".validation").remove(); // remove it
	}

	if(confirmPassword==""){
		if ($("#userConfirmPassword").parent().next(".validation").length == 0) // only add if not added
        {
            $("#userConfirmPassword").parent().after("<div class='validation' style='color:red;margin-bottom: 20px;'>Please enter confirm password.</div>");
        }
		$('#userConfirmPassword').focus();
		return false;
		
	}else{
		$("#userConfirmPassword").parent().next(".validation").remove(); // remove it
	}


	if(email==""){
		if ($("#userEmail").parent().next(".validation").length == 0) // only add if not added
        {
            $("#userEmail").parent().after("<div class='validation' style='color:red;margin-bottom: 20px;'>Please enter email id.</div>");
        }
		$('#userEmail').focus();
		return false;
		
	}else{
		$("#userEmail").parent().next(".validation").remove(); // remove it
	}

	if(password!=confirmPassword){
		if ($("#userPassword").parent().next(".validation").length == 0) // only add if not added
        {
            $("#userPassword").parent().after("<div class='validation' style='color:red;margin-bottom: 20px;'>Passwords did not match, please enter correct passwords.</div>");
        }
		$('#userPassword').focus();
		$('#userConfirmPassword').focus();
		return false;
		
	}else{
		$("#userPassword").parent().next(".validation").remove(); // remove it
	}

	if(!emailRegex.test(email)){
		if ($("#userEmail").parent().next(".validation").length == 0) // only add if not added
        {
            $("#userEmail").parent().after("<div class='validation' style='color:red;margin-bottom: 20px;'>Please enter a valid email address.</div>");
        }
		$('#userEmail').focus();
		return false;
		
	}else{
		$("#userEmail").parent().next(".validation").remove(); // remove it
	}
	
	
	var userRoles = [];
	$.each($("#userRoles option:selected"), function(){            
		var role={};
		role.id=$(this).val();
		role.type=$(this).text();
		userRoles.push(role);
	});
	
    var user = {};
    user.firstName 	= firstName;
    user.lastName 	= lastName;
    user.ssoId 		= ssoId;
    user.password 	= password;
    user.email 		= email;
    user.userRoles	= userRoles;

    $.ajax({
       type:'POST',
       async: false,
       url : 'user/add?tenantId='+tenantId,
       contentType: 'application/json',
       data : JSON.stringify(user),
       dataType : 'json',
       beforeSend: function(xhr) {
           xhr.setRequestHeader(header, token);
       },
       success : function(status) {
    	   if(status.status==200){
    		   	$('#successMessage').html(status.message);
    		    $('#UserModalAjax').modal('hide');
	            $('#successAlert').show();
	            manageUsers();
	       	}else{
	       		$('#warningMessage').html(status.message);
	       		$('#warningAlert').show();
	       		manageUsers();
	       	}
       }
 });
}


function addUserProject(){

	var userProject={};
	var user ={};
	var project ={};
    
	$('select#assignProject option').each(function(){
		if(this.selected && $(this).val()!="" ){	
			project.id=$(this).val();
			project.name=$(this).text();
		}
	});
	
	$('select#assignUser option').each(function(){
		if(this.selected && $(this).val()!="" ){	
			user.id=$(this).val();
			user.fistName=$(this).text();
		}
	});
	
	if(project.name==null)
	{
		if ($("select#assignProject").parent().next(".validation").length == 0) // only add if not added
        {
            $("select#assignProject").parent().after("<div class='validation' style='color:red;margin-bottom: 20px;'>Please select project.</div>");
        }
		$('select#assignProject').focus();
		return false;
		
	}else{
		$("select#assignProject").parent().next(".validation").remove(); // remove it
	}

	if(user.fistName==null){
		if ($("select#assignUser").parent().next(".validation").length == 0) // only add if not added
        {
            $("select#assignUser").parent().after("<div class='validation' style='color:red;margin-bottom: 20px;'>Please select user.</div>");
        }
		$('select#assignUser').focus();
		return false;
		
	}else{
		$("select#assignUser").parent().next(".validation").remove(); // remove it
	}
	
	userProject.user=user
	userProject.project= project;
	
    $.ajax({
       type:'POST',
       async: false,
       url : 'userProject/add?tenantId='+tenantId,
       contentType: 'application/json',
       data : JSON.stringify(userProject),
       dataType : 'json',
       beforeSend: function(xhr) {
           xhr.setRequestHeader(header, token);
       },
       success : function(status) {
    	   if(status.status==200){
    		   	$('#successMessage').html(status.message);
    		    $('#AssignProjectModalAjax').modal('hide');
	            $('#successAlert').show();
	            manageUserProjects();
	       	}else{
	       		$('#warningMessage').html(status.message);
	       		$('#warningAlert').show();
	       	}
       }
 });
}

function addUserTask(){

	var userTask={};
	var user ={};
	var project ={};
	var task ={};
    
	$('select#assignProject option').each(function(){
		if(this.selected && $(this).val()!="" ){	
			project.id=$(this).val();
			project.name=$(this).text();
		}
	});
	
	$('select#assignUser option').each(function(){
		if(this.selected && $(this).val()!="" ){	
			user.id=$(this).val();
			user.firstName=$(this).text();
		}
	});
	
	$('select#assignTask option').each(function(){
		if(this.selected && $(this).val()!="" ){	
			task.id=$(this).val();
			task.name=$(this).text();
		}
	});
	
	if(project.name==null)
	{
		if ($("select#assignProject ").parent().next(".validation").length == 0) // only add if not added
        {
            $("select#assignProject ").parent().after("<div class='validation' style='color:red;margin-bottom: 20px;'>Please select project.</div>");
        }
		$('select#assignProject ').focus();
		return false;
		
	}else{
		$("select#assignProject ").parent().next(".validation").remove(); // remove it
	}

	if(user.fistName==null){
		if ($("select#assignUser").parent().next(".validation").length == 0) // only add if not added
        {
            $("select#assignUser").parent().after("<div class='validation' style='color:red;margin-bottom: 20px;'>Please select user.</div>");
        }
		$('select#assignUser').focus();
		return false;
		
	}else{
		$("select#assignUser").parent().next(".validation").remove(); // remove it
	}

	if(task.name==null){
		if ($("select#assignTask").parent().next(".validation").length == 0) // only add if not added
        {
            $("select#assignTask").parent().after("<div class='validation' style='color:red;margin-bottom: 20px;'>Please select task.</div>");
        }
		$('select#assignTask').focus();
		return false;
		
	}else{
		$("select#assignTask").parent().next(".validation").remove(); // remove it
	}
	
	userTask.user=user
	userTask.project= project;
	userTask.task= task;

    $.ajax({
       type:'POST',
       async: false,
       url : 'userTask/add?tenantId='+tenantId,
       contentType: 'application/json',
       data : JSON.stringify(userTask),
       dataType : 'json',
       beforeSend: function(xhr) {
           xhr.setRequestHeader(header, token);
       },
       success : function(status) {
    	   if(status.status==200){
    		   	$('#successMessage').html(status.message);
    		    $('#AssignTaskModalAjax').modal('hide');
	            $('#successAlert').show();
	            manageUserTasks();
	       	}else{
	       		$('#warningMessage').html(status.message);
	       		$('#warningAlert').show();
	       	}
       }
 });
}

function manageUserProjects(){
	
    $.ajax({
       type:'GET',
       async: false,
       url : 'userProject/all?tenantId='+tenantId,
       contentType: 'application/json',
       dataType : 'json',
       beforeSend: function(xhr) {
           xhr.setRequestHeader(header, token);
       },
       success : function(userProjects) {
    		fillUserProjectsInDataTable(userProjects);
       }
 });
}

function manageUserTasks() {
	
    $.ajax({
       type:'GET',
       async: false,
       url : 'userTask/all?tenantId='+tenantId,
       contentType: 'application/json',
       dataType : 'json',
       beforeSend: function(xhr) {
           xhr.setRequestHeader(header, token);
       },
       success : function(userTasks) {
    		fillUserTasksInDataTable(userTasks);
       }
 });
}


function fillUserTasksInDataTable(userTasks){
	var dataSet=[];
	userTasks.forEach(function(userTask){
		var dataEach = [];
		dataEach.push(userTask.id);
		dataEach.push(userTask.user.id);
		dataEach.push(userTask.user.firstName);
		dataEach.push(userTask.project.id);
		dataEach.push(userTask.project.name);
		dataEach.push(userTask.task.id);
		dataEach.push(userTask.task.name);
		dataSet.push(dataEach);
	});

	if ($.fn.DataTable.isDataTable("#dataTable")) {
		  $('#dataTable').DataTable().clear().destroy();
		}

	$('#dataTable').DataTable( {
        data: dataSet,
        columns: [
        	{ title: "User Task Id" },
        	{ title: "User Id" },
            { title: "User Name" },
        	{ title: "Project Id" },
            { title: "Project Name" },
            { title: "Task Id" },
            { title: "Task Name" },
           	{ "render": function (data, type, full, meta) {
               	return '<a class="projectDelete btn btn-danger custom-width" href=# id=\'' + full[0] + '\' onClick="deleteById(\'userTask\',\'' + full[0] + '\')" >Deassign</a>'} },
        ],
        responsive: true,
        scrollY:        450,
        deferRender:    true,
        scroller:       true,
         responsive: {
            details: {
                display: $.fn.dataTable.Responsive.display.modal( {
                    header: function ( row ) {
                        var data = row.data();
                        return 'Details for '+data[1]+' '+data[3];
                    }
                } ),
                renderer: $.fn.dataTable.Responsive.renderer.tableAll( {
                    tableClass: 'table'
                } )
            }
        }, 
        "pagingType" : "full_numbers",
	    select	   : true,
	    rowReorder: true,
	    colReorder: true,
	    "columnDefs": [
            {
                "targets": [ 0 ],
                "visible": false,
                "searchable": false
            },
            {
                "targets": [ 1 ],
                "visible": false,
                "searchable": false
            },
            {
                "targets": [ 3 ],
                "visible": false,
                "searchable": false
            },
            {
                "targets": [ 5 ],
                "visible": false,
                "searchable": false
            }
		]
    });
}


function fillUserProjectsInDataTable(userProjects){
	var dataSet=[];
	userProjects.forEach(function(userProject){
		var dataEach = [];
		dataEach.push(userProject.id);
		dataEach.push(userProject.user.id);
		dataEach.push(userProject.user.firstName);
		dataEach.push(userProject.project.id);
		dataEach.push(userProject.project.name);
		dataEach.push(userProject.project.description);
		dataSet.push(dataEach);
	});

	if ($.fn.DataTable.isDataTable("#dataTable")) {
		  $('#dataTable').DataTable().clear().destroy();
		}

	$('#dataTable').DataTable( {
        data: dataSet,
        columns: [
        	{ title: "User Project Id" },
        	{ title: "User Id" },
            { title: "User Name" },
        	{ title: "Project Id" },
            { title: "Project Name" },
            { title: "Description" },
           	{ "render": function (data, type, full, meta) {
               	return '<a class="projectDelete btn btn-danger custom-width" href=# id=\'' + full[0] + '\' onClick="deleteById(\'userProject\',\'' + full[0] + '\')" >Deassign</a>'} },
        ],
        responsive: true,
        scrollY:        450,
        deferRender:    true,
        scroller:       true,
         responsive: {
            details: {
                display: $.fn.dataTable.Responsive.display.modal( {
                    header: function ( row ) {
                        var data = row.data();
                        return 'Details for '+data[1]+' '+data[3];
                    }
                } ),
                renderer: $.fn.dataTable.Responsive.renderer.tableAll( {
                    tableClass: 'table'
                } )
            }
        }, 
        "pagingType" : "full_numbers",
	    select	   : true,
	    rowReorder: true,
	    colReorder: true,
	    "columnDefs": [
            {
                "targets": [ 0 ],
                "visible": false,
                "searchable": false
            },
            {
                "targets": [ 1 ],
                "visible": false,
                "searchable": false
            },
            {
                "targets": [ 3 ],
                "visible": false,
                "searchable": false
            }
		]
    });
}

function addTask() {
	
	var taskStatus = {};
	taskStatus.id=$('select#taskStatus option:selected').val();
	taskStatus.status=$('select#taskStatus option:selected').text();
	
	var project={};
	project.id 	= $('select#taskProject option:selected').val();
	project.name 	= $('select#taskProject option:selected').text();
    
	var task = {};
    task.name 		  = $('#taskName').val();
    task.description  = $('#taskDesc').val();
    task.taskStatus   = taskStatus;
    task.project 	  = project;
    
    
    if(project.name =="")
	{
		if ($("select#taskProject").parent().next(".validation").length == 0) // only add if not added
        {
            $("select#taskProject").parent().after("<div class='validation' style='color:red;margin-bottom: 20px;'>Please select a project for the task.</div>");
        }
		$('select#taskProject').focus();
		return false;
		
	}else{
		$("select#taskProject").parent().next(".validation").remove(); // remove it
	}
    
    if(task.name==""){
		if ($("#taskName").parent().next(".validation").length == 0) // only add if not added
        {
            $("#taskName").parent().after("<div class='validation' style='color:red;margin-bottom: 20px;'>Please enter the task name.</div>");
        }
		$('#taskName').focus();
		return false;
		
	}else{
		$("#taskName").parent().next(".validation").remove(); // remove it
	}

    if(task.description==""){
    	if ($("#taskDesc").parent().next(".validation").length == 0) // only add if not added
        {
            $("#taskDesc").parent().after("<div class='validation' style='color:red;margin-bottom: 20px;'>Please add description for the task.</div>");
        }
		$('#taskDesc').focus();
		return false;
		
	}else{
		$("#taskDesc").parent().next(".validation").remove(); // remove it
	}

    if(taskStatus.status==""){
		if ($("select#taskStatus").parent().next(".validation").length == 0) // only add if not added
        {
            $("select#taskStatus").parent().after("<div class='validation' style='color:red;margin-bottom: 20px;'>Please select task status for the task.</div>");
        }
		$('select#taskStatus').focus();
		return false;
		
	}else{
		$("select#taskStatus").parent().next(".validation").remove(); // remove it
	}
    
    
    $.ajax({
       type:'POST',
       async: false,
       url : 'task/add?tenantId='+tenantId,
       contentType: 'application/json',
       data : JSON.stringify(task),
       dataType : 'json',
       beforeSend: function(xhr) {
           xhr.setRequestHeader(header, token);
       },
       success : function(status) {
       
          if(status.status==200){
            $('#TaskModalAjax').modal('hide');
            $('#successAlert').show();
            manageTasks();
          }else{
        	$('#TaskModalAjax').modal('hide');  
            $('#warningAlert').show();
          }
       }
 });
} 

function addTaskStatus() {
	
    var taskStatus = {};
    taskStatus.status 	= $('#taskStatusName').val();
    taskStatus.statusColour = $('select#taskStatusColour option:selected').val();
    
    if(taskStatus.status==""){
		 $('#taskStatusName').focus();
		 if ($("#taskStatusName").parent().next(".validation").length == 0) // only add if not added
	        {
	            $("#taskStatusName").parent().after("<div class='validation' style='color:red;margin-bottom: 20px;'>Please enter the task status name.</div>");
	        }
			$('#taskStatusName').focus();
			return false;
			
	}else{
		$("#taskStatusName").parent().next(".validation").remove(); // remove it
	}
    

    if(taskStatus.statusColour==""){
		if ($("select#taskStatusColour").parent().next(".validation").length == 0) // only add if not added
        {
            $("select#taskStatusColour").parent().after("<div class='validation' style='color:red;margin-bottom: 20px;'>Please select task status for the task.</div>");
        }
		$('select#taskStatusColour').focus();
		return false;
		
	}else{
		$("select#taskStatusColour").parent().next(".validation").remove(); // remove it
	}
    
    $.ajax({
       type:'POST',
       async: false,
       url : 'taskStatus/add?tenantId='+tenantId,
       contentType: 'application/json',
       data : JSON.stringify(taskStatus),
       dataType : 'json',
       beforeSend: function(xhr) {
           xhr.setRequestHeader(header, token);
       },
       success : function(status) {
       
          if(status.status==200){
        	$('#successAlert').text(status.message);
            $('#TaskStatusModalAjax').modal('hide');
            $('#successAlert').show();
            manageTaskStatus();
          }else{
        	$('#warningAlert').text(status.message);
        	$('#TaskStatusModalAjax').modal('hide');  
            $('#warningAlert').show();
          }
       }
 });
} 


function loadProjectsForUser() {
    
    $.ajax({
       type:'GET',
       async: false,
       url : 'userProject/load/projects?tenantId='+tenantId,
       contentType: 'application/json',
       dataType : 'json',
       beforeSend: function(xhr) {
           xhr.setRequestHeader(header, token);
       },
       success : function(userProjects) {
    	   fillAssignedProjectsInDataTable(userProjects);
       }
 });
}

function fillAssignedProjectsInDataTable(userProjects){
	var dataSet=[];
	userProjects.forEach(function(userProject){
		var dataEach = [];
		dataEach.push(userProject.project.id);
		dataEach.push(userProject.project.name);
		dataEach.push(userProject.project.description);
		dataSet.push(dataEach);
	});

	if ($.fn.DataTable.isDataTable("#dataTable")) {
		  $('#dataTable').DataTable().clear().destroy();
		}
       
	$('#dataTable').DataTable( {
		data: dataSet,
		columns: [
            { title: "Project Id" },
            { title: "Project Name	" },
            { title: "Project Description" },
        ],
        responsive: true,
        scrollY:        450,
        deferRender:    true,
        scroller:       true,
         responsive: {
            details: {
                display: $.fn.dataTable.Responsive.display.modal( {
                    header: function ( row ) {
                        var data = row.data();
                        return 'Details for '+data[0]+' '+data[1];
                    }
                } ),
                renderer: $.fn.dataTable.Responsive.renderer.tableAll( {
                    tableClass: 'table'
                } )
            }
        }, 
        "pagingType" : "full_numbers",
	    select	   : true,
	    rowReorder: true,
	    colReorder: true,
	    "columnDefs": [
            {
                "targets": [ 0 ],
                "visible": false,
                "searchable": false
            }]
    });
}

function loadTasksForUser() {
    
    $.ajax({
       type:'GET',
       async: false,
       url : 'userTask/load/tasks?tenantId='+tenantId,
       contentType: 'application/json',
       dataType : 'json',
       beforeSend: function(xhr) {
           xhr.setRequestHeader(header, token);
       },
       success : function(userTasks) {
    	   fillAssignedTasksInDataTable(userTasks);
       }
 });
}

function fillAssignedTasksInDataTable(userTasks){
	var dataSet=[];
	userTasks.forEach(function(userTask){
		var dataEach = [];
		dataEach.push(userTask.id);
		dataEach.push(userTask.project.name);
		dataEach.push(userTask.project.description);
		dataEach.push(userTask.task.name);
		dataEach.push(userTask.task.description);
		dataEach.push(userTask.task.taskStatus.status);
		dataEach.push(userTask.task.taskStatus.statusColour);
		dataSet.push(dataEach);
	});

	if ($.fn.DataTable.isDataTable("#dataTable")) {
		  $('#dataTable').DataTable().clear().destroy();
		}
       
	$('#dataTable').DataTable( {
		data: dataSet,
		columns: [
            { title: "userTask Id" },
            { title: "Project Name" },
            { title: "Task Name	" },
            { title: "Task Description" },
            { title: "Task Status" },
            { title: "Task Status Flag" },
            { "render": function (data, type, full, meta) {
            	return '<a class="btn btn-success custom-width"  href=#  data-toggle="modal" id=\'' + full[0] + '\' onClick="loadAjaxPage(\'updateTaskByUser\',\'edit\',\'' + full[0] + '\')" data-target="#UpdateTaskByUserModalAjax" >Edit</a>'} },
        ],
        responsive: true,
        scrollY:        450,
        deferRender:    true,
        scroller:       true,
         responsive: {
            details: {
                display: $.fn.dataTable.Responsive.display.modal( {
                    header: function ( row ) {
                        var data = row.data();
                        return 'Details for '+data[0]+' '+data[1];
                    }
                } ),
                renderer: $.fn.dataTable.Responsive.renderer.tableAll( {
                    tableClass: 'table'
                } )
            }
        }, 
        "pagingType" : "full_numbers",
	    select	   : true,
	    rowReorder: true,
	    colReorder: true,
	    "columnDefs": [
            {
                "targets": [ 0 ],
                "visible": false,
                "searchable": false
            }]
    });
}


function manageUsers () {
    
    $.ajax({
       type:'GET',
       async: false,
       url : 'user/all?tenantId='+tenantId,
       contentType: 'application/json',
       dataType : 'json',
       beforeSend: function(xhr) {
           xhr.setRequestHeader(header, token);
       },
       success : function(users) {
       
    	   fillUsersInDataTable(users);
       }
 });
}

function fillUsersInDataTable(users){
	var dataSet=[];
	users.forEach(function(user){
		var dataEach = [];
		dataEach.push(user.firstName);
		dataEach.push(user.lastName);
		dataEach.push(user.ssoId);
		dataEach.push(user.email);
		var roles = "";
		user.userRoles.forEach(function(role){
			roles = roles + role.type + " , ";
		});
		roles = roles.substring(0,roles.length-2);
		dataEach.push(roles);
		dataSet.push(dataEach);
	});

	if ($.fn.DataTable.isDataTable("#dataTable")) {
		  $('#dataTable').DataTable().clear().destroy();
		}
       
	$('#dataTable').DataTable( {
		data: dataSet,
		columns: [
            { title: "First Name" },
            { title: "Last Name	" },
            { title: "SSO ID" },
            { title: "Email" },
            { title: "Roles" },
            { "render": function (data, type, full, meta) {
            	return '<a class="userEdit btn btn-success custom-width"  href=#  data-toggle="modal" id=\'' + full[2] + '\' onClick="loadAjaxPage(\'user\',\'edit\',\'' + full[2] + '\');" data-target="#UserModalAjax" >Edit</a>'} },
           	{ "render": function (data, type, full, meta) {
               	return '<a class="userDelete btn btn-danger custom-width" href=# id=\'' + full[2] + '\' onClick="deleteById(\'user\',\'' + full[2] + '\');" >Delete</a>'} },	
        ],
        responsive: true,
        scrollY:        450,
        deferRender:    true,
        scroller:       true,
         responsive: {
            details: {
                display: $.fn.dataTable.Responsive.display.modal( {
                    header: function ( row ) {
                        var data = row.data();
                        return 'Details for '+data[0]+' '+data[1];
                    }
                } ),
                renderer: $.fn.dataTable.Responsive.renderer.tableAll( {
                    tableClass: 'table'
                } )
            }
        }, 
        "pagingType" : "full_numbers",
	    select	   : true,
	    rowReorder: true,
	    colReorder: true,
    });
}

function manageProjects() {
	
    $.ajax({
       type:'GET',
       async: false,
       url : 'project/all?tenantId='+tenantId,
       contentType: 'application/json',
       dataType : 'json',
       beforeSend: function(xhr) {
           xhr.setRequestHeader(header, token);
       },
       success : function(projects) {
    	   fillProjectsInDataTable(projects);
       }
 });
}

function fillProjectsInDataTable(projects){
	var dataSet=[];
	projects.forEach(function(project){
		var dataEach = [];
		dataEach.push(project.id);
		dataEach.push(project.name);
		dataEach.push(project.description);
		if(project.parentProject!=null){
			dataEach.push(project.parentProject.id);
			dataEach.push(project.parentProject.name);
		}else{
			dataEach.push("");
			dataEach.push("");
		}
		var projects = "";
		project.childProjects.forEach(function(project){
			projects = projects + project.name + ", ";
		});
		projects = projects.substring(0,projects.length-2);
		dataEach.push(projects);
		dataSet.push(dataEach);
	});

	if ($.fn.DataTable.isDataTable("#dataTable")) {
		  $('#dataTable').DataTable().clear().destroy();
		}

	$('#dataTable').DataTable( {
        data: dataSet,
        columns: [
        	{ title: "Project Id" },
            { title: "Project Name" },
            { title: "Description" },
            { title: "Parent Project Id" },
            { title: "Parent Project" },
            { title: "Child Projects" },            
            { "render": function (data, type, full, meta) {
            	return '<a class="btn btn-success custom-width"  href=#  data-toggle="modal" id=\'' + full[0] + '\' onClick="loadAjaxPage(\'projectDocuments\',\'\',\'' + full[0] + '\')" data-target="#DocumentsModalAjax" >Manage Documents</a>'} },
           	 { "render": function (data, type, full, meta) {
            	return '<a class="projectEdit btn btn-success custom-width"  href=#  data-toggle="modal" id=\'' + full[0] + '\' onClick="loadAjaxPage(\'project\',\'edit\',\'' + full[0] + '\')" data-target="#ProjectModalAjax" >Edit</a>'} },
           	{ "render": function (data, type, full, meta) {
               	return '<a class="btn btn-danger custom-width" href=# id=\'' + full[0] + '\' onClick="deleteById(\'project\',\'' + full[0] + '\')" >Delete</a>'} },
           		
        ],
        responsive: true,
        scrollY:        450,
        deferRender:    true,
        scroller:       true,
         responsive: {
            details: {
                display: $.fn.dataTable.Responsive.display.modal( {
                    header: function ( row ) {
                        var data = row.data();
                        return 'Details for '+data[0]+' '+data[1];
                    }
                } ),
                renderer: $.fn.dataTable.Responsive.renderer.tableAll( {
                    tableClass: 'table'
                } )
            }
        }, 
        "pagingType" : "full_numbers",
	    select	   : true,
	    rowReorder: true,
	    colReorder: true,
	    "columnDefs": [
            {
                "targets": [ 0 ],
                "visible": false,
                "searchable": false
            },
            {
                "targets": [ 3 ],
                "visible": false,
                "searchable": false
            }
		]
    });
}



function manageTasks() {
    $.ajax({
       type:'GET',
       async: false,
       url : 'task/all?tenantId='+tenantId,
       contentType: 'application/json',
       dataType : 'json',
       beforeSend: function(xhr) {
           xhr.setRequestHeader(header, token);
       },
       success : function(tasks) {
    	   //alert(tasks);
    	   fillTasksInDataTable(tasks);
       }
 });
}

function fillTasksInDataTable(tasks){
	var dataSet=[];
	tasks.forEach(function(task){
		var dataEach = [];
		dataEach.push(task.id);
		dataEach.push(task.project.name);
		dataEach.push(task.name);
		dataEach.push(task.description);
		dataEach.push(task.taskStatus.status);
		dataEach.push(task.taskStatus.statusColour);
		dataSet.push(dataEach);
	});

	if ($.fn.DataTable.isDataTable("#dataTable")) {
		  $('#dataTable').DataTable().clear().destroy();
		}

	$('#dataTable').DataTable( {
        data: dataSet,
        columns: [
        	{ title: "Task Id" },
        	{ title: "Project Name"},
        	{ title: "Task Name" },
            { title: "Description" },
            { title: "Task Status"},
            { title: "Status Flag" },
            { "render": function (data, type, full, meta) {
            	return '<a class="taskEdit btn btn-success custom-width"  href=#  data-toggle="modal" id=\'' + full[0] + '\' onClick="loadAjaxPage(\'task\',\'edit\',\'' + full[0] + '\')" data-target="#TaskModalAjax" >Edit</a>'} },
           	{ "render": function (data, type, full, meta) {
               	return '<a class="taskDelete btn btn-danger custom-width" href=# id=\'' + full[0] + '\' onClick="deleteById(\'task\',\'' + full[0] + '\')" >Delete</a>'} },
        ],
        responsive: true,
        colReorder: true,
        "pagingType" : "full_numbers",
	    select	   : true,
	    rowReorder: true,
	    "columnDefs": [
            {
                "targets": [ 0 ],
                "visible": false,
                "searchable": false
            }
		]
    });
}

function manageTaskStatus() {
	
    $.ajax({
       type:'GET',
       async: false,
       url : 'taskStatus/all?tenantId='+tenantId,
       contentType: 'application/json',
       dataType : 'json',
       beforeSend: function(xhr) {
           xhr.setRequestHeader(header, token);
       },
       success : function(taskStatus) {
    	   fillTaskStatusInDataTable(taskStatus);
       }
 });
}

function fillTaskStatusInDataTable(taskStatus){
	var dataSet=[];
	taskStatus.forEach(function(Status){
		var dataEach = [];
		dataEach.push(Status.id);
		dataEach.push(Status.status);
		dataEach.push(Status.statusColour);
		dataSet.push(dataEach);
	});

	if ($.fn.DataTable.isDataTable("#dataTable")) {
		  $('#dataTable').DataTable().clear().destroy();
		}

	$('#dataTable').DataTable( {
        data: dataSet,
        columns: [
        	{ title: "Status Id" },
            { title: "Status" },
            { title: "Status Colour"},
            { "render": function (data, type, full, meta) {
            	return '<a class="taskStatusEdit btn btn-success custom-width"  href=#  data-toggle="modal" id=\'' + full[0] + '\' onClick="loadAjaxPage(\'taskStatus\',\'edit\',\'' + full[0] + '\')" data-target="#TaskStatusModalAjax" >Edit</a>'} },
           	{ "render": function (data, type, full, meta) {
               	return '<a class="taskStatusDelete btn btn-danger custom-width" href=# id=\'' + full[0] + '\' onClick="deleteById(\'taskStatus\',\'' + full[0] + '\')" >Delete</a>'} },
        ], 
        "pagingType" : "full_numbers",
	    select	   : true,
	    rowReorder: true,
	    responsive: true,
        colReorder: true,
        "columnDefs": [
            {
                "targets": [ 0 ],
                "visible": false,
                "searchable": false
            }
		]
    });
}


function updateTaskByUser(){ 
	
	var userTaskId = $('#userTaskId').val();
	var description = $('#userTaskDesc').val();
	
	var taskStatus = {};
	taskStatus.id=$('select#userTaskStatus option:selected').val();
	taskStatus.status=$('select#userTaskStatus option:selected').text();
	
	var task = {};
	task.description = description;
	task.taskStatus = taskStatus;
	
	if(description=="")
	{
		if ($("#userTaskDesc").parent().next(".validation").length == 0) // only add if not added
        {
            $("#userTaskDesc").parent().after("<div class='validation' style='color:red;margin-bottom: 20px;'>Please add a description for task.</div>");
        }
		$('#userTaskDesc').focus();
		return false;
		
	}else{
		$("#userTaskDesc").parent().next(".validation").remove(); // remove it
	}

	if(taskStatus.status==""){
		if ($("select#userTaskStatus").parent().next(".validation").length == 0) // only add if not added
        {
            $("select#userTaskStatus").parent().after("<div class='validation' style='color:red;margin-bottom: 20px;'>Please select a task status for the task.</div>");
        }
		$('select#userTaskStatus').focus();
		return false;
		
	}else{
		$("select#userTaskStatus").parent().next(".validation").remove(); // remove it
	}
	
	var userTask = {};

	userTask.id = userTaskId;
	userTask.task = task;
	
	$.ajax({
	       type:'POST',
	       async: false,
	       url : 'userTask/update?tenantId='+tenantId,
	       contentType: 'application/json',
	       data : JSON.stringify(userTask),
	       dataType : 'json',
	       beforeSend: function(xhr) {
	           xhr.setRequestHeader(header, token);
	       },
	       success : function(status) {
	    	   if(status.status==200){
		        	$('#successMessage').html(status.message);
		        	$('#UpdateTaskByUserModalAjax').modal('hide');
		            $('#successAlert').show();
		            manageTaskStatus()
		          }else{
		        	$('#warningMessage').html(status.message);
		        	$('#UpdateTaskByUserModalAjax').modal('hide');  
		            $('#warningAlert').show();
		          }
	       }
	       
	 });
	
}