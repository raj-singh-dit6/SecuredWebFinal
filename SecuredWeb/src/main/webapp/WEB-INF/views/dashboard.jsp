<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<meta name="_csrf" content="${_csrf.token}"/>
	<meta name="_csrf_header" content="${_csrf.headerName}"/>
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet">
    <link href="/SecuredWeb/static/css/securedWeb.css" rel="stylesheet">
	<link rel="stylesheet" href="https://code.jquery.com/ui/1.10.3/themes/smoothness/jquery-ui.css">
	
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/fancybox/3.2.5/jquery.fancybox.min.css" />
	<!--CSS For Data Tables -->
	<link rel="stylesheet" href="https://cdn.datatables.net/1.10.16/css/dataTables.bootstrap4.min.css">
	<link rel="stylesheet" href="https://cdn.datatables.net/select/1.2.5/css/select.bootstrap4.min.css">
	<link rel="stylesheet" href="https://cdn.datatables.net/responsive/2.2.1/css/responsive.bootstrap4.min.css">
	<link rel="stylesheet" href="https://cdn.datatables.net/rowreorder/1.2.3/css/rowReorder.bootstrap4.min.css">
	<link rel="stylesheet" href="https://cdn.datatables.net/colreorder/1.4.1/css/colReorder.dataTables.min.css">
	<!-- FOR COLOUR PICKER -->
    <link href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-colorpicker/2.3.6/css/bootstrap-colorpicker.css" rel="stylesheet">

</head>
 
<body>
	<%@include file="navbar.jsp" %>	
	<div class="container-fluid text-center" style="height:100%">    
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

	<footer class="container-fluid text-center bg-dark">
	  <p>${tenantName}</p>
	  <input type="hidden" id="tenantId" value="${tenantId}">
	  <input type="hidden" id="userId" value="${userId}">
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
  
 
 <!-- User Task Modal -->
 <div class="modal fade" id="UpdateTaskByUserModalAjax">
    <div class="modal-dialog modal-dialog-centered">
      <div class="modal-content" id="UpdateTaskByUserModalBody">
      
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

<!--  User Modal -->
  <div class="modal fade" id="DocumentsModalAjax">
    <div class="modal-dialog modal-dialog-centered modal-lg">
      <div class="modal-content" id="DocumentsModalBody">
    
      </div>
    </div>
  </div>
  <!-- ---------------------Modal ends here----------------------- -->
 	
 <!--  UPLOAD DOCUMENTS Modal -->
  <div class="modal fade" id="UploadDocumentsModalAjax">
    <div class="modal-dialog modal-dialog-centered">
      <div class="modal-content" id="UploadDocumentsModalBody">
    
      </div>
    </div>
  </div>
  <!-- ---------------------Modal ends here----------------------- -->
  
  <!--  UPLOAD DOCUMENTS Modal -->
  <div class="modal fade" id="ChangePasswordModalAjax">
    <div class="modal-dialog modal-dialog-centered">
      <div class="modal-content" id="ChangePasswordModalBody">
    
      </div>
    </div>
  </div>
  <!-- ---------------------Modal ends here----------------------- -->
 		
</body>
</html>
    <!-- Datatable extensions js -->
	<script src="https://code.jquery.com/jquery-3.2.1.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.13.0/umd/popper.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
	<!-- <script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.0.0/js/bootstrap.min.js"></script> -->
	<!-- Datatable extensions js -->
	<script src="https://cdn.datatables.net/1.10.16/js/jquery.dataTables.min.js"></script>
	<script src="https://cdn.datatables.net/1.10.16/js/dataTables.bootstrap4.min.js"></script>
	
	<!-- Datatable extensions js -->
	<script src="https://cdn.datatables.net/select/1.2.5/js/dataTables.select.min.js"></script>
	<script src="https://cdn.datatables.net/responsive/2.2.1/js/dataTables.responsive.min.js"></script>
	<script src="https://cdn.datatables.net/responsive/2.2.1/js/responsive.bootstrap4.min.js"></script>
	<script src="https://cdn.datatables.net/rowreorder/1.2.3/js/dataTables.rowReorder.min.js"></script>
	<script src="https://cdn.datatables.net/colreorder/1.4.1/js/dataTables.colReorder.min.js"></script>
	
	<!--Application   -->
	<script src="/SecuredWeb/static/js/securedWeb.js"></script>
	
	<!-- Bootbox -->
	<script src="/SecuredWeb/static/js/bootbox.min.js"></script>
	
	<!--Fancy Box  -->
	<script src="https://cdnjs.cloudflare.com/ajax/libs/fancybox/3.2.5/jquery.fancybox.min.js"></script>
	<!--Bootstrap Colour Picker  -->
	<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-colorpicker/2.3.6/js/bootstrap-colorpicker.js"></script>  
	
	<!-- jquery form validator -->
	<script src="/SecuredWeb/static/js/jquery.validate.js"></script>
	
	<script src="/SecuredWeb/static/js/securedWeb.validation.js"></script>
<script>

var token = $("meta[name='_csrf']").attr("content");
var header = $("meta[name='_csrf_header']").attr("content");
var tenantId=$("#tenantId").val();

$(function () {
	$('#taskStatusColour').colorpicker();
  });

$(document).ready(function() {
	$('#successAlert').hide();
	$('#warningAlert').hide();
	
	$(".fancybox").fancybox({
	    width  : 600,
	    height : 300,
	});
});



$('#UserModalAjax').on('shown.bs.modal', function (e) {
$("#UserForm").validate({
	  rules: {	
		  userSsoId: { minlength: 8},
		  userEmail: {email: true},
	  },
	  messages: {
		  	userFirstName:{ required: "Please enter your first name."},
		  	userLastName: { required:"Please enter your last name."},
		  	userSsoId : {
	        	required: "Please enter a user id.",
	        	minlength: "Your user id must be at least 8 characters."
	      	},
	      	userPassword : { required:"Please enter a password."},
	      	userConfirmPassword : { required:"Please enter a password to confirm."},
	    	userEmail: {
				required : "Please enter your email id.",	    	
	    		email:"Your email address must be in the format of 'xyz@domain.com'.",
	    	},
	    	userRoles : { required:"Please select  your role in your organisation."},
	  	},
	  submitHandler: function(form) {
		  return false;
	  }
	});
});

$('#ProjectModalAjax').on('shown.bs.modal', function (e) {
	debugger;
	$("#ProjectForm").validate({
		  rules: {	
			  projName: { minlength: 8},
			  projDesc: {minlength: 20},
		  },
		  messages: {
			  	projName:{ required:"Please mention project name.",
			  		minlength: "Your project name id must be at least 8 characters."},
			  	projDesc: { 
			  		required:"Please add description to the project.",
			  		minlength: "Project description must be at least 20 characters."},
		  	},
		  submitHandler: function(form) {
			  return false;
		  }
		});
	});
	
	
$('#TaskModalAjax').on('shown.bs.modal', function (e) {
	debugger;
	$("#TaskForm").validate({
		  rules: {	
			  taskName: { minlength: 10},
		  },
		  messages: {
			  taskProject: { required:"Please select project for which you want to add task."},	
			  taskName:{ required:"Please mention project name.",
			  		minlength: "Your task name must be at least 10 characters."},
			  taskStatus: { required:"Please select task status for the task."},	
		  	},
		  submitHandler: function(form) {
		    return false;
		  }
		});
	});



$('#TaskStatusModalAjax').on('shown.bs.modal', function (e) {
	debugger;
	$("#TaskStatusForm").validate({
		  rules: {	
			  taskStatusName: { minlength: 5},
		  },
		  messages: {
			  taskStatusName:{ required:"Please mention task status name.",
			  		minlength: "Your task status name must be at least 5 characters."},
			  taskStatusColour: { required:"Please select task status for the task."},	
		  	},
		  	submitHandler: function (form) {
				return false;	
			}
		});
	
    });
    
    
$('#AssignProjectModalAjax').on('shown.bs.modal', function (e) {
	debugger;
	$("#AssignProjectForm").validate({
		  messages: {
			  assignProject: { required:"Please select project."},	
			  assignUser: { required:"Please select user to whom u wish to assign project."},	
		  	},
		  	submitHandler: function (form) {
				return false;	
			}
		});
	
    });
    
$('#AssignProjectModalAjax').on('shown.bs.modal', function (e) {
	debugger;
	$("#AssignProjectForm").validate({
		  messages: {
			  assignProject: { required:"Please select project."},	
			  assignUser: { required:"Please select user."},
			  assignTask: { required:"Please select task."},
		  	},
		  	submitHandler: function (form) {
				return false;	
			}
		});
});

$('#UpdateTaskByUserModalAjax').on('shown.bs.modal', function (e) {
		debugger;
		$("#UpdateUserTaskForm").validate({
			  messages: {
				  userTaskDesc: { required:"Please add description to your task."},	
				  userTaskStatus: { required:"Please select task status for your task."},
			  	},
			  	submitHandler: function (form) {
					return false;	
				}
			});	
	
 });
 
$('#UploadDocumentsModalAjax').on('shown.bs.modal', function (e) {
	$("#UploadDocumentForm").validate({
		  messages: {
			  multipleFiles: { required:"Please select atleast one file."},	
			  description: { required:"Please add description for your files."},
		  	},
		  	submitHandler: function (form) {
				return false;	
			}
		});	

});


</script>
