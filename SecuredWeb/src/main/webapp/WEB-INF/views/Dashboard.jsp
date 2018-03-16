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


<!-- Project Modal -->
 <div class="modal fade" id="AssignProjectModalAjax">
    <div class="modal-dialog modal-dialog-centered">
      <div class="modal-content" id="AssignProjectModalBody">
      
      </div>
   </div>
 </div>
  <!-- ---------------------Modal ends here----------------------- -->
  
 <!-- The Modal -->
  <div class="modal fade" id="TaskModalAjax">
    <div class="modal-dialog modal-dialog-centered">
      <div class="modal-content" id="TaskModalBody">
       
      </div>
    </div>
  </div>
  <!-- ---------------------Modal ends here----------------------- -->

 <!-- The Modal -->
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
	
	$('#AssignProjectModalAjax').on('shown.bs.modal', function() {
		loadAjaxPage('userProject','add');
	});
	
	$('#UserModalAjax').on('shown.bs.modal', function() {
		loadAjaxPage('user','add');
	});
	
	$('#ProjectModalAjax').on('shown.bs.modal', function() {
		loadAjaxPage('project','add');
	});
	
	$('#TaskModalAjax').on('shown.bs.modal', function() {
		loadAjaxPage('task','add');
	});
	
	$('#TaskStatusModalAjax').on('shown.bs.modal', function() {
		loadAjaxPage('taskStatus','add');
	});
	
});

function loadAjaxPage(pageType,operation,id)
{	
	debugger
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
			$("div #userPassword").hide();
			$("div #userSsoId").hide();
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
			$("#ProjectModalHeading").text("Update User Details");
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
			        	 alert(projects);
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
		        url: "taskStatus/"+taskId+"?tenantId="+tenantId,
		        beforeSend: function(xhr) {
			           xhr.setRequestHeader(header, token);
			       },
		        success: function(task) {
		        	$("#taskStatusName").val(task.name);
		        	$("#taskStatusId").val(task.id);
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
	    	        url: "user/delete/"+ssoId+"",
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
	    	        url: "project/delete/"+id+"",
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
	    	        url: "task/delete/"+taskId+"",
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
	    	        url: "taskStatus/delete/"+taskStatusId+"",
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
       url : 'project/add',
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
       url : 'project/update',
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
	debugger
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
       url : 'user/add?tenanId='+tenantId,
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
	
	debugger
	
	userProject.user=user
	userProject.project= project;

	alert(JSON.stringify(userProject));
	
    $.ajax({
       type:'POST',
       async: false,
       url : 'userProject/add?tenanId='+tenantId,
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
	       	}else{
	       		$('#warningMessage').html(status.message);
	       		$('#warningAlert').show();
	       	}
       }
 });
}

$('#manageUserProjects').click(function(e) {
	
	debugger
    $.ajax({
       type:'GET',
       async: false,
       url : 'userProject/all',
       contentType: 'application/json',
       dataType : 'json',
       beforeSend: function(xhr) {
           xhr.setRequestHeader(header, token);
       },
       success : function(userProjects) {
    		debugger
    	   alert(userProjects);
    	   fillUserProjectsInDataTable(userProjects);
       }
 });
}); 

function fillUserProjectsInDataTable(userProjects){
	debugger
	var dataSet=[];
	userProjects.forEach(function(userProject){
		var dataEach = [];
		dataEach.push(userProject.project.id);
		dataEach.push(userProject.project.name);
		dataEach.push(userProject.user.id);
		dataEach.push(userProject.user.firstName);
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
        	{ title: "User Id" },
            { title: "User First Name" },
            { "render": function (data, type, full, meta) {
            	return '<a class="projectEdit btn btn-success custom-width"  href=#  data-toggle="modal" id=\'' + full[0] + '\' onClick="loadAjaxPage(\'project\',\'edit\',\'' + full[0] + '\',\''+full[2]+'\');" data-target="#ProjectModalAjax" >Edit</a>'} },
           	{ "render": function (data, type, full, meta) {
               	return '<a class="projectDelete btn btn-danger custom-width" href=# id=\'' + full[0] + '\' onClick="deleteUserProjectById(\'' + full[0] + '\',\''+full[2]+'\');" >Delete</a>'} },
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
                "targets": [ 2 ],
                "visible": false,
                "searchable": false
            }
		]
    });
}

$('#addTask').click(function(e) {
	
	var taskStatus = {};
	$("#taskStatus option:selected"), function(){            
		taskStatus.id=$(this).val();
		taskStatus.status=$(this).text();
	};
	
    var task = {};
    task.project.id 	= $('#taskProject option:selected').val();
    task.project.name 	= $('#taskProject option:selected').text();
    task.name 			= $('#taskName').val();
    task.description    = $('#taskDesc').val();
    task.taskStatus 	= taskStatus;
    task.tenantId 	= "";
    
    $.ajax({
       type:'POST',
       async: false,
       url : 'task/addTask',
       contentType: 'application/json',
       data : JSON.stringify(task),
       dataType : 'json',
       beforeSend: function(xhr) {
           // here it is
           xhr.setRequestHeader(header, token);
       },
       success : function(res) {
       
          if(res=="success"){
            $('#newTaskModal').modal('hide');
            $('#successAlert').show();
            $('#manageTasks').click();
          }else{
        	$('#newTaskModal').modal('hide');  
            $('#warningAlert').show();
          }
       }
 });
}); 

$('#addTaskStatus').click(function(e) {
    var taskStatus = {};
    taskStatus.status 	= $('#taskStatusName').val();
    taskStatus.statusColour = $('#statusColourPicker option:selected').val();
    
    $.ajax({
       type:'POST',
       async: false,
       url : 'taskStatus/add',
       contentType: 'application/json',
       data : JSON.stringify(taskStatus),
       dataType : 'json',
       beforeSend: function(xhr) {
           xhr.setRequestHeader(header, token);
       },
       success : function(status) {
       
          if(status.status==200){
            $('#newTaskStatusModal').modal('hide');
            $('#successAlert').show();
            $('#manageTaskStatus').click();
          }else{
        	$('#newTaskStatusModal').modal('hide	');  
            $('#warningAlert').show();
          }
       }
 });
}); 


$('#manageUsers').click(function(e) {
    
    $.ajax({
       type:'GET',
       async: false,
       url : 'user/all',
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
	
	debugger
    $.ajax({
       type:'GET',
       async: false,
       url : 'project/all',
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
	debugger
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
       url : 'task/all',
       contentType: 'application/json',
       dataType : 'json',
       beforeSend: function(xhr) {
           xhr.setRequestHeader(header, token);
       },
       success : function(tasks) {
    	   fillTasksInDataTable(tasks);
       }
 });
}); 

function fillTasksInDataTable(tasks){
	var dataSet=[];
	tasks.forEach(function(task){
		var dataEach = [];
		dataEach.push(task.id);
		dataEach.push(task.name);
		dataEach.push(task.description);
		dataEach.push(task.taskStatus.status);
		dataEach.push(task.taskStatus.colour);
		dataSet.push(dataEach);
	});

	if ($.fn.DataTable.isDataTable("#dataTable")) {
		  $('#dataTable').DataTable().clear().destroy();
		}

	$('#dataTable').DataTable( {
        data: dataSet,
        columns: [
        	{ title: "Task Id" },
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
       url : 'taskStatus/all',
       contentType: 'application/json',
       dataType : 'json',
       beforeSend: function(xhr) {
           xhr.setRequestHeader(header, token);
       },
       success : function(taskStatus) {
    	   alert(taskStatus);
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