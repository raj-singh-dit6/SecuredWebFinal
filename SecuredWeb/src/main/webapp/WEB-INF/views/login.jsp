<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <meta name="_csrf" content="${_csrf.token}"/>
		<meta name="_csrf_header" content="${_csrf.headerName}"/>
        <title>Login page</title>
	    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet">
	   	<link href="/SecuredWeb/static/css/app.css" rel="stylesheet">
	   	<link href="/SecuredWeb/static/css/securedWeb.css" rel="stylesheet">
		<link rel="stylesheet" href="https://code.jquery.com/ui/1.10.3/themes/smoothness/jquery-ui.css">
    </head>
    <body>
        <div id="mainWrapper">
            <div class="login-container">
                <div class="login-card">
                    <div class="login-form">
                        <c:url var="loginUrl" value="/login" />
                        <form action="${loginUrl}" method="post" id="loginForm" class="form-horizontal">
                            <c:if test="${error!= null}">
                                <div class="alert alert-danger">
                                    <p>${error}</p>
                                </div>
                            </c:if>
                            <c:if test="${param.error != null}">
                                <div class="alert alert-danger">
                                    <p>Invalid username and password.</p>
                                </div>
                            </c:if>
                            <c:if test="${param.logout != null}">
                                <div class="alert alert-success">
                                    <p>You have been logged out successfully.</p>
                                </div>
                            </c:if>
                            <div class="input-group input-sm">
                                <label class="input-group-addon" for="username"><i class="glyphicon glyphicon-user"></i></label>
                                <input type="text" class="form-control" id="username" name="ssoId" placeholder="Enter Username">
                            </div>
                            <div class="input-group input-sm">
                                <label class="input-group-addon" for="password"><i class="glyphicon glyphicon-lock"></i></label> 
                                <input type="password" class="form-control" id="password" name="password" placeholder="Enter Password">
                            </div>
                            <div class="input-group input-sm">
                              <div class="checkbox">
                                <label><input type="checkbox" id="rememberme" name="remember-me"> Remember Me</label>  
                              </div>
                            </div>
                            <input type="hidden" name="${_csrf.parameterName}"  value="${_csrf.token}" />
                                 
                            <div class="form-actions">
                                <input type="submit"
                                    class="btn btn-block btn-primary btn-default" value="Log in">
                            </div>
                        </form>
                        <div class="row">
	                        <div class="col-md-6">
	                        	<button type="button" data-toggle="modal" data-target="#newUserModalAjax" class="btn" onClick="loadAjaxPage('registerUser')">Register</button>
	            		   </div>
	            		   <div class="col-md-6">
	                        	<button type="button" data-toggle="modal" data-target="#ForgotPasswordModalAjax" class="btn" onClick="loadAjaxPage('forgotPassword')">Forgot Password?</button>
	            		   </div>
            		   </div>
                    </div>
                </div>
            </div>
            
        </div>
	       <!--New  User  Modal -->
	       <div class="modal fade" id="newUserModalAjax">
		     <div class="modal-dialog modal-dialog-centered">
		      	<div class="modal-content" id="newUserModalBody">
		    
		       	</div>
		     </div>
		   </div>
		   	<!-- ---------------------Modal ends here----------------------- -->
 	   	
 	   	<!-- Forgot Password Modal -->
		 <div class="modal fade" id="ForgotPasswordModalAjax">
		    <div class="modal-dialog modal-dialog-centered">
		      <div class="modal-content" id="ForgotPasswordModalBody">
		      
		      </div>
		   </div>
		 </div>
		  <!-- ---------------------Modal ends here----------------------- -->
	    </body>
</html>
<script src="https://code.jquery.com/jquery-3.2.1.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.13.0/umd/popper.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.0.0/js/bootstrap.min.js"></script>

<script src="/SecuredWeb/static/js/bootbox.min.js"></script>
<script src="/SecuredWeb/static/js/jquery.validate.js"></script>

<script src="/SecuredWeb/static/js/securedWeb.validation.js"></script>
<script>
var token = $("meta[name='_csrf']").attr("content");
var header = $("meta[name='_csrf_header']").attr("content");

$('#newUserModalAjax').on('shown.bs.modal', function (e) {
	$("#addUserForm").validate({
	  rules: {	
			newSsoId: {
		      minlength: 6
		    },
		  	newEmail: {
		      email: true
		    },
	      	newConfirmPassword: {
	        	equalTo: "#newPassword"
	      	}
		    
		  },
	  messages: {
		    tenants : { required:"Please select atleast one tenant."},
		  	newFirstName:{ required: "Please enter your first name."},
			newLastName: { required:"Please enter your last name."},
	    	newSsoId : {
	        	required: "Please enter a user id.",
	        	minlength: "Your user id must be at least 8 characters."
	      	},
	      	newPassword : { required:"Please enter a password."},
	      	newConfirmPassword : { required:"Please enter a password to confirm."},
	    	newEmail: {
				required : "Please enter your email id.",	    	
	    		email:"Your email address must be in the format of 'xyz@domain.com'.",
	    	},
	  	},
	  submitHandler: function(form) {
		  return false;
	  	},
	});
});

function loadAjaxPage(pageType){	
	var reqURL= "view/ajaxPage/"+pageType;
	
	if(pageType=="registerUser")
	{   
		$.ajax({
	    	async: false,
	    	type: "GET",
	        url: reqURL,
	        beforeSend: function(xhr) {
		           xhr.setRequestHeader(header, token);
		       },
	        success: function(response) {
	        	$('#newUserModalBody').html( response );
	        	$('#newUserModalAjax').modal('show');
	        	$.ajax({
	    	    	async: false,
	    	    	type: "GET",
	    	        url: "tenant/all",
	    	        beforeSend: function(xhr) {
				           xhr.setRequestHeader(header, token);
				       },
	    	        success: function(tenants) {
	    	        	tenants.forEach(function(tenant){
			            	$("select#tenants").append('<option id="'+tenant.tenantId+'" value="'+tenant.tenantId+'">'+tenant.tenantName+'</option>"');
			            });
	    	        	 
	    	        }
	    	    });
	        }
	    });
	}else if(pageType=="forgotPassword")
	{
		$.ajax({
	    	async: false,
	    	type: "GET",
	        url: reqURL,
	        beforeSend: function(xhr) {
		           xhr.setRequestHeader(header, token);
		       },
	        success: function(response) {
	        	$('#ForgotPasswordModalBody').html( response );
	        	$('#ForgotPasswordModalAjax').modal('show');
	        }
	    });
	}
	$('input,select,textarea').filter('[required]').each(function(){ 
		$(this).parent().prev().append('<span style="color:red;">*</span>');
	});
	
}

function registerUserSubmit()
{
	debugger
    var firstName 		= $('#newFirstName').val();
    var lastName 		= $('#newLastName').val();
    var ssoId 			= $('#newSsoId').val();
    var password 		= $('#newPassword').val();
    var confirmPassword = $('#newConfirmPassword').val();
    var email 			= $('#newEmail').val();	
    var tenantId 		= $('select#tenants option:selected').val();
    var emailRegex 		= /^([\w-\.]+)@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.)|(([\w-]+\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\]?)$/;

	var user={};
		user.firstName=firstName;
		user.lastName=lastName;
		user.ssoId=ssoId;
		user.password=password;
		user.email=email;
		
		var userRoles = [];
		var role ={};
		role.id=1;
		role.type="USER";
		userRoles.push(role);
		user.userRoles= userRoles;
		
		$.ajax({
	    	async: false,
	    	type: "POST",
	        url: "user/add?tenantId="+tenantId,
	        contentType: 'application/json',
	        data : JSON.stringify(user),
	        dataType : 'json',
	        beforeSend: function(xhr) {
		           xhr.setRequestHeader(header, token);
		       },
	        success: function(status) {
	        	if(status.status==200)
	        	{
	        		bootbox.alert("Account has been created for user name : '"+ssoId+"'", function(){ 
	        			$('#newUserModalAjax').modal('toggle');
	        			
	        		});
	        	}else
	        	{
	        		bootbox.alert(status.message, function(){ 
	        		});
	        	}	
	        }
	    });
}

function resetPass(){
    var email = $("#Email").val();
    $.ajax({
    	async: false,
    	type: "POST",
        url: "user/resetPassword?email="+email,
        beforeSend: function(xhr) {
	           xhr.setRequestHeader(header, token);
	       },
        success: function(status) {
        	if(status.status==200)
        	{
        		bootbox.alert(status.message, function(){ 
        			$('ForgotPasswordModalAjax').modal('hide'); 
        			$('div.alert-danger').hide();
        		});
        	}else
        	{
        		bootbox.alert(status.message, function(){
        			$('div.alert-danger').hide();
        		});
        		
        	}	
        	 
        }
    });
}
</script>