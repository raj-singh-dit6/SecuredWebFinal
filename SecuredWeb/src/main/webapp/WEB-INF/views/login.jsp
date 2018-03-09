<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>Login page</title>
        <link href="<c:url value='/static/css/app.css' />" rel="stylesheet"></link>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>	    
    </head>
    <body>
        <div id="mainWrapper">
            <div class="login-container">
                <div class="login-card">
                    <div class="login-form">
                        <c:url var="loginUrl" value="/login" />
                        <form action="${loginUrl}" method="post" class="form-horizontal">
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
                                <input type="text" class="form-control" id="username" name="ssoId" placeholder="Enter Username" required>
                            </div>
                            <div class="input-group input-sm">
                                <label class="input-group-addon" for="password"><i class="glyphicon glyphicon-lock"></i></label> 
                                <input type="password" class="form-control" id="password" name="password" placeholder="Enter Password" required>
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
                    </div>
                </div>
            </div>
        </div>
        <div class="modal fade" id="newUser">
	    <div class="modal-dialog modal-dialog-centered">
	      <div class="modal-content">
	      
	        <!-- Modal Header -->
	        <div class="modal-header">
	          <h4 class="modal-title">Add New User</h4>
	          <!-- <button type="button" class="close" data-dismiss="modal">&times;</button> -->
	        </div>
	        
	        <!-- Modal body -->
	        <div id = "newUserModal"  class="modal-body">
	        	<form action="#" method="POST" name="addUserForm">
	            <div class="row">
	                <div class="form-group col-md-12">
	                    <label class="col-md-3 control-lable" for="firstName">First Name</label>
	                    <div class="col-md-7">
	                        <input type="text" name="firstName" id="newfirstName" class="form-control input-sm"/>
	                    </div>
	                </div>
	            </div>
	            <div class="row">
	                <div class="form-group col-md-12">
	                    <label class="col-md-3 control-lable" for="lastName">Last Name</label>
	                    <div class="col-md-7">
	                        <input type="text" name="lastName" id="newlastName" class="form-control input-sm" />
	                    </div>
	                </div>
	            </div>
	            <div class="row">
	                <div class="form-group col-md-12">
	                    <label class="col-md-3 control-lable" for="ssoId">SSO ID</label>
	                    <div class="col-md-7">
							<input type="text" name="ssoId" id="newSsoId" class="form-control input-sm" />
	                    </div>
	                </div>
	            </div>
	            <div class="row">
	                <div class="form-group col-md-12">
	                    <label class="col-md-3 control-lable" for="password">Password</label>
	                    <div class="col-md-7">
	                        <input type="password" name="password" id="newPassword" class="form-control input-sm" />
	                    </div>
	                </div>
	            </div>
	            <div class="row">
	                <div class="form-group col-md-12">
	                    <label class="col-md-3 control-lable" for="email">Email</label>
	                    <div class="col-md-7">
	                        <input type="text" name="email" id="newEmail" class="form-control input-sm" />
	                    </div>
	                </div>
	            </div>
	            <div class="row">
	                <div class="form-group col-md-12">
	                    <label class="col-md-3 control-lable" for="userRoles">Roles</label>
	                    <div class="col-md-7">
							<select multiple class="form-control" id="neweserRoles">
						        <option id="${role.id}" value="${role.id}">${role.type}</option>
						      </select>
	                        <%-- <select name="userRoles" items="${roles}" multiple="true" itemValue="id" itemLabel="type" class="form-control input-sm" /> --%>
	                    </div>
	                </div>
	            </div>
	            </form>
	        </div>
	        <!-- Modal footer -->
	        <div class="modal-footer">
	             <c:choose>
	                 <c:when test="${edit}">
	                     <button id="editUser" type="button" class="btn btn-primary">Submit</button>  
	                 </c:when>
	                 <c:otherwise>
	                     <button id="registerUser" type="button" class="btn btn-primary">Add</button>  
	                 </c:otherwise>
	             </c:choose>
	                <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
	         </div>	
	      </div>
	    </div>
 	 </div>
    <!-- ------------------Modal ends here--------------- -->    
    </body>
</html>