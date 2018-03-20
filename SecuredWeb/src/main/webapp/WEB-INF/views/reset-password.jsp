<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <meta name="_csrf" content="${_csrf.token}"/>
		<meta name="_csrf_header" content="${_csrf.headerName}"/>
        <title>Reset Password</title>
    	<script src="https://code.jquery.com/jquery-3.2.1.js"></script>
	    <script src="/SecuredWeb/static/js/bootbox.min.js"></script>
	    <link href="/SecuredWeb/static/css/bootstrap.css" rel="stylesheet">
	   	<link href="/SecuredWeb/static/css/app.css" rel="stylesheet">
		<link rel="stylesheet" href="https://code.jquery.com/ui/1.10.3/themes/smoothness/jquery-ui.css">
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script> 
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.13.0/umd/popper.min.js"></script>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.0.0/js/bootstrap.min.js"></script>
    </head>
    <body>
        <div id="mainWrapper">
	  	 <div class="container">
		    <div class="row">
		        <div class="col-md-4 col-md-offset-4">
		            <div class="panel panel-default">
		                <div class="panel-body">
		                    <div class="text-center">
		                        <h3><i class="glyphicon glyphicon-lock" style="font-size:2em;"></i></h3>
		                        <h2 class="text-center">Reset password</h2>
		                        <div class="panel-body">
									<c:choose>
									 <c:when test = "${error!=null}">
		                            <div>
		                                <div class="alert alert-danger">
		                                    <span id="errorMessage">${error}</span>
		                                </div>
		                                <!-- <div class="alert alert-success">
		                                	<button type="button" data-toggle="modal" data-target="#ForgotPasswordModalAjax" class="btn" onClick="loadAjaxPage('forgotPassword')">Forgot Password?</button>
		                                </div> -->
		                            </div>
		                            </c:when>
		                            <c:otherwise>
		
		                            <form  action="#" method="POST">
		                                <input type="hidden" id="hiddenToken" name="token" value="${token}"/>
		                                <div class="form-group">
		                                    <div class="input-group">
		                                        <span class="input-group-addon">
		                                            <i class="glyphicon glyphicon-lock"></i>
		                                        </span>
		                                        <input id="password" class="form-control" placeholder="Password" type="password"/>
		                                    </div>
		                                </div>
		                                <div class="form-group">
		                                    <div class="input-group">
		                                        <span class="input-group-addon">
		                                            <i class="glyphicon glyphicon-lock"></i>
		                                        </span>
		                                        <input id="confirmPassword" class="form-control" placeholder="Confirm Password" type="password"/>
		                                    </div>
		                                </div>
		                                <div class="form-group">
		                                    <button type="button"  class="btn btn-block btn-success" onClick="resetPasswordSubmit()" >Reset password</button>
		                                </div>
		                            </form>
		                            </c:otherwise>
								</c:choose>	
		                        </div>
		                    </div>
		                </div>
		            </div>
		        </div>
    		  </div>
        	</div>
        </div>
        
        
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
<script>
function resetPasswordSubmit(){

    var password = $("#password").val();
    var confirmPassword = $("#confirmPassword").val();
    var token = $("#hiddenToken").val();
   
    var passwordResetDTO ={};
    passwordResetDTO.password=password;
    passwordResetDTO.confirmPassword=confirmPassword;
    passwordResetDTO.token=token;
   
    if(password == "" || confirmPassword=="")
    {
    	bootbox.alert("Please enter both passwords.");
		return false;
    }
    else if(password!=confirmPassword)
	{
		bootbox.alert("Password did not match, please type correct password");
		return false;
	}else{ 
		
		$.ajax({
	    	async: false,
	    	type: "POST",
	        url: "resetPassword",
	        contentType: 'application/json',
	        data : JSON.stringify(passwordResetDTO),
	        dataType : 'json',
	        success: function(status) {
	        	if(status.status==200)
	        	{
	        		bootbox.alert(status.message, function(){ 
	        		    });
	        			
	        	}else{
	        		
					bootbox.alert(status.message, function(){ 
	        		});
	        		
	        	}	
	        	 
	        }
	    });
	}
}
</script>