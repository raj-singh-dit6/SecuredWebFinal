<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
 
<html>
<head>
	<meta name="_csrf" content="${_csrf.token}"/>
	<meta name="_csrf_header" content="${_csrf.headerName}"/>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Users List</title>
    
    <script src="https://code.jquery.com/jquery-3.2.1.js"></script>
    <script src="/SecuredWeb/static/js/bootbox.min.js"></script>
    <link href="/SecuredWeb/static/css/bootstrap.css" rel="stylesheet">
   	<link href="/SecuredWeb/static/css/app.css" rel="stylesheet">
	<link rel="stylesheet" href="https://code.jquery.com/ui/1.10.3/themes/smoothness/jquery-ui.css">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script> 
	
	<!--JS FOR For Data Tables -->
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
	<!-- <script src="https://code.jquery.com/jquery-1.12.4.js"></script> -->
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.13.0/umd/popper.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.0.0/js/bootstrap.min.js"></script>
	<script src="https://cdn.datatables.net/1.10.16/js/jquery.dataTables.min.js"></script>
	<script src="https://cdn.datatables.net/1.10.16/js/dataTables.bootstrap4.min.js"></script>
	<script src="https://cdn.datatables.net/select/1.2.5/js/dataTables.select.min.js"></script>
	<script src="https://cdn.datatables.net/responsive/2.2.1/js/dataTables.responsive.min.js"></script>
	<script src="https://cdn.datatables.net/responsive/2.2.1/js/responsive.bootstrap4.min.js"></script>
	<script src="https://cdn.datatables.net/rowreorder/1.2.3/js/dataTables.rowReorder.min.js"></script>
	<script src="https://cdn.datatables.net/colreorder/1.4.1/js/dataTables.colReorder.min.js"></script>
	<!--CSS For Data Tables -->
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.css">
	<link rel="stylesheet" href="https://cdn.datatables.net/1.10.16/css/dataTables.bootstrap4.min.css">
	<link rel="stylesheet" href="https://cdn.datatables.net/select/1.2.5/css/select.bootstrap4.min.css">
	<link rel="stylesheet" href="https://cdn.datatables.net/responsive/2.2.1/css/responsive.bootstrap4.min.css">
	<link rel="stylesheet" href="https://cdn.datatables.net/rowreorder/1.2.3/css/rowReorder.bootstrap4.min.css">
	<link rel="stylesheet" href="https://cdn.datatables.net/colreorder/1.4.1/css/colReorder.dataTables.min.css">
	<!-- FOR COLOUR PICKER -->
	<link href="/SecuredWeb/static/css/bootstrap-colorpicker.css" rel="stylesheet">
	
<style>
div.dataTables_length>label>select.form-control.form-control-sm{
	height : 30px;
}
div.dataTables_length>label{
	float : left;
}
nav-link.dropdown-toggle:hover{
	color: black;
}
</style>
</head>
 
<body>
	<%@include file="NavBar.jsp" %>
	<c:choose>
        <c:when test="${dashboard}">
			<%@include file="authHeader.jsp" %>
        </c:when>
        <c:otherwise>
        </c:otherwise>
    </c:choose>
	<div class="container-fluid text-center" style="height:90%">    
	  <div class="row content">
	    <div class="col-sm-12 text-left">
		    <div id="successAlert" class="alert alert-success alert-dismissible fade in">
					    	<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
					    	<strong id="successMessage"></strong>
			</div>
			<div id="warningAlert" class="alert alert-danger alert-dismissible fade in">
	    				<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
	    				<strong id="warningMessage" ></strong>
  			</div> 
	    </div>
	  </div>
	  <div class="row content">
		  <div class="col-sm-12">
		  	<table id="dataTable" class="table table-striped table-bordered display" width="100%"></table>
		  </div>
	  </div>
	</div>

	<footer class="container-fluid text-center">
	  <p>${tenantName}</p>
	  <input type="hidden" id="tenantId" value="${tenantId}">
	</footer>

<!--  User Modal -->
  <div class="modal fade" id="UserModalAjax">
    <div class="modal-dialog modal-dialog-centered">
      <div class="modal-content" id="UserModalBody">
    
      </div>
    </div>
  </div>
  <!-- ---------------------Modal ends here----------------------- -->
  
  
	<!-- Project Modal -->
 <div class="modal fade" id="ProjectModalAjax">
    <div class="modal-dialog modal-dialog-centered">
      <div class="modal-content" id="ProjectModalBody">
      
      </div>
   </div>
 </div>
  <!-- ---------------------Modal ends here----------------------- -->


<!-- User Project Modal -->
 <div class="modal fade" id="AssignProjectModalAjax">
    <div class="modal-dialog modal-dialog-centered">
      <div class="modal-content" id="AssignProjectModalBody">
      
      </div>
   </div>
 </div>
  <!-- ---------------------Modal ends here----------------------- -->
  
 <!-- Task Modal -->
  <div class="modal fade" id="TaskModalAjax">
    <div class="modal-dialog modal-dialog-centered">
      <div class="modal-content" id="TaskModalBody">
       
      </div>
    </div>
  </div>
  <!-- ---------------------Modal ends here----------------------- -->

 <!-- User Task Modal -->
 <div class="modal fade" id="AssignTaskModalAjax">
    <div class="modal-dialog modal-dialog-centered">
      <div class="modal-content" id="AssignTaskModalBody">
      
      </div>
   </div>
 </div>
  <!-- ---------------------Modal ends here----------------------- -->
  
  
  
 <!-- Task Status Modal -->
  <div class="modal fade" id="TaskStatusModalAjax">
    <div class="modal-dialog modal-dialog-centered">
      <div class="modal-content" id="TaskStatusModalBody">
      
      </div>
    </div>
  </div>
  <!-- ---------------------Modal ends here----------------------- -->
 	
</body>
</html>
<script>

var token = $("meta[name='_csrf']").attr("content");
var header = $("meta[name='_csrf_header']").attr("content");
var tenantId=$("#tenantId").val();

$(document).ready(function() {
	$('#successAlert').hide();
	$('#warningAlert').hide();
});

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
		            	$("#userRoles").append('<option id="'+role.id+'" value="'+role.id+'">'+role.type+'</option>"');
		            });
		        }
		    });
		}
		else if(operation=="edit")
		{  	var ssoId = id;
			$("#UserModalHeading").text("Update User Details");
			$("#userPasswordDiv").hide();
			$("#userSsoIdDiv").hide();
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
		            	$("#userRoles").append('<option id="'+role.id+'" value="'+role.id+'">'+role.type+'</option>"');
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
		            	$("select#projParent").append('<option id="'+project.id+'" value="'+project.id+'">'+project.name+'</option>"');
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
		        	$("#taskStatusId").val(taskStatus.id);
		        }
		    });
			
		}
		
	}else if(pageType=="userTaskByUser")
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
		        	//alert(userProjects);
		        	userProjects.forEach(function(userProject){
		            	$("select#assignProject").append('<option id="'+userProject.project.id+'" value="'+userProject.project.id+'"  >'+userProject.project.name+'</option>"');
		            });
		        }
			});
		}	
		
	}
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
	    		            $('#manageUsers').click();
	    	        	}else{
	    	        		$('#warningMessage').html(status.message);
	    	        		$('#warningAlert').show();
	    		            $('#manageUsers').click();
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
	    		            $('#manageProjects').click();
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
	    		            $('#manageTasks').click();
	    	        	}else{
	    	        		$('#warningMessage').html(status.message);
	    	        		$('#warningAlert').show();
	    		            $('#manageTasks').click();
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
	    		            $('#manageTaskStatus').click();
	    	        	}else{
	    	        		$('#warningMessage').html(status.message);
	    	        		$('#warningAlert').show();
	    		            $('#manageTaskStatus').click();
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
	    		            $('#manageUserProjects').click();
	    	        	}else{
	    	        		$('#warningMessage').html(status.message);
	    	        		$('#warningAlert').show();
	    	        	}	
	    	        }
	    	    });
	    	}	
	    	}
		});
		
	}	
}

function addProject() {
	var parentProject = {};
	$('select#projParent option').each(function() {
		if (this.selected && $(this).val()!="" )
		{	
		    	parentProject.id=$(this).val();
		    	parentProject.name=$(this).text();
		}
	});
    
	var project = {};
    project.name 	= $('#projName').val();
    project.description 	= $('#projDesc').val();
    project.parentProject	= parentProject;
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
	            $('#manageProjects').click();
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
	
	alert(JSON.stringify(task));
	
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
	            $('#manageTasks').click();
	          }else{
	        	$('#warningMessage').html(status.message);
	        	$('#TaskModalAjax').modal('hide');  
	            $('#warningAlert').show();
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
	            $('#manageTaskStatus').click();
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
    project.name 	= $('#projName').val();
    project.description 	= $('#projDesc').val();
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
	            $('#manageProjects').click();
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
	
    //alert(JSON.stringify(user));
    
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
	            $('#manageUsers').click();
	          }else{
	        	$('#warningMessage').html(status.message);
	        	$('#UserModalAjax').modal('hide');  
	            $('#warningAlert').show();
	          }
	       }
	 });
}

function addUser(){
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
    user.password 	= $('#userPassword').val();
    user.email 		= $('#userEmail').val();
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
	            $('#manageUsers').click();
	       	}else{
	       		$('#warningMessage').html(status.message);
	       		$('#warningAlert').show();
		        $('#manageUsers').click();
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
	            $('#manageUserProjects').click();
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
	
	
	userTask.user=user
	userTask.project= project;
	userTask.task= task;

	//alert(JSON.stringify(userTask));
	
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
	            $('#manageUserTasks').click();
	       	}else{
	       		$('#warningMessage').html(status.message);
	       		$('#warningAlert').show();
	       	}
       }
 });
}

$('#manageUserProjects').click(function(e) {
	
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
});

$('#manageUserTasks').click(function(e) {
	
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
});


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
	taskStatus.id=$('#taskStatus option:selected').val();
	taskStatus.status=$('#taskStatus option:selected').text();
	
	var project={};
	project.id 	= $('#taskProject option:selected').val();
	project.name 	= $('#taskProject option:selected').text();
    
	var task = {};
    task.name 		  = $('#taskName').val();
    task.description  = $('#taskDesc').val();
    task.taskStatus   = taskStatus;
    task.project 	  = project;
    
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
            $('#manageTasks').click();
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
            $('#manageTaskStatus').click();
          }else{
        	$('#warningAlert').text(status.message);
        	$('#TaskStatusModalAjax').modal('hide');  
            $('#warningAlert').show();
          }
       }
 });
} 


$('#loadProjectsForUser').click(function(e) {
    
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
        	//alert(userProjects);
    	   fillAssignedProjectsInDataTable(userProjects);
       }
 });
}); 

function fillAssignedProjectsInDataTable(userProjects){
	var dataSet=[];
	userProjects.forEach(function(userProject){
		var dataEach = [];
		dataEach.push(userProject.project.id);
		dataEach.push(userProject.project.name);
		dataEach.push(userProject.project.description);
		dataEach.push(userProject.project.createDateTime);
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
            { title: "Created TimeStamp" },
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

$('#loadTasksForUser').click(function(e) {
    
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
       
    	   //alert(userTasks)
    	   fillAssignedTasksInDataTable(userTasks);
       }
 });
}); 

function fillAssignedTasksInDataTable(userTasks){
	var dataSet=[];
	userTasks.forEach(function(userTask){
		var dataEach = [];
		dataEach.push(userTask.task.id);
		dataEach.push(userTask.project.name);
		dataEach.push(userTask.task.name);
		dataEach.push(userTask.task.description);
		dataEach.push(userTask.task.taskStatus.status);
		dataEach.push(userTask.task.taskStatus.statusColour);
		dataEach.push(userTask.task.createDateTime);
		dataSet.push(dataEach);
	});

	if ($.fn.DataTable.isDataTable("#dataTable")) {
		  $('#dataTable').DataTable().clear().destroy();
		}
       
	$('#dataTable').DataTable( {
		data: dataSet,
		columns: [
            { title: "Task Id" },
            { title: "Project Name" },
            { title: "Task Name	" },
            { title: "Task Description" },
            { title: "Task Status" },
            { title: "Task Status Flag" },
            { title: "Created TimeStamp" },
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


$('#manageUsers').click(function(e) {
    
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
}); 

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

$('#manageProjects').click(function(e) {
	
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
}); 

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
            	return '<a class="projectEdit btn btn-success custom-width"  href=#  data-toggle="modal" id=\'' + full[0] + '\' onClick="loadAjaxPage(\'project\',\'edit\',\'' + full[0] + '\')" data-target="#ProjectModalAjax" >Edit</a>'} },
           	{ "render": function (data, type, full, meta) {
               	return '<a class="projectDelete btn btn-danger custom-width" href=# id=\'' + full[0] + '\' onClick="deleteById(\'project\',\'' + full[0] + '\')" >Delete</a>'} },
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



$('#manageTasks').click(function(e) {
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
}); 

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

$('#manageTaskStatus').click(function(e) {
	
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
});

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

</script>