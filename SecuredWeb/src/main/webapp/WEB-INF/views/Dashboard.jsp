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
    
    <link href="/SecuredWeb/static/css/bootstrap.css" rel="stylesheet">
    <link href="/SecuredWeb/static/css/app.css" rel="stylesheet">
	<link rel="stylesheet" href="https://code.jquery.com/ui/1.10.3/themes/smoothness/jquery-ui.css">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
	<!--JS FOR For Data Tables -->
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
	<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.13.0/umd/popper.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.0.0/js/bootstrap.min.js"></script>
	<script src="https://cdn.datatables.net/1.10.16/js/jquery.dataTables.min.js"></script>
	<script src="https://cdn.datatables.net/1.10.16/js/dataTables.bootstrap4.min.js"></script>
	<script src="https://cdn.datatables.net/select/1.2.5/js/dataTables.select.min.js"></script>
	<script src="https://cdn.datatables.net/responsive/2.2.1/js/dataTables.responsive.min.js"></script>
	<script src="https://cdn.datatables.net/responsive/2.2.1/js/responsive.bootstrap4.min.js"></script>
	<script src="https://cdn.datatables.net/rowreorder/1.2.3/js/dataTables.rowReorder.min.js"></script>
	<!--CSS For Data Tables -->
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.0.0/css/bootstrap.css">
	<link rel="stylesheet" href="https://cdn.datatables.net/1.10.16/css/dataTables.bootstrap4.min.css">
	<link rel="stylesheet" href="https://cdn.datatables.net/select/1.2.5/css/select.bootstrap4.min.css">
	<link rel="stylesheet" href="https://cdn.datatables.net/rowreorder/1.2.3/css/rowReorder.bootstrap4.min.css">
	<link rel="stylesheet" href="https://cdn.datatables.net/responsive/2.2.1/css/responsive.bootstrap4.min.css">
	
</head>
 
<body>
	<%@include file="NavBar.jsp" %>
	<div class="container-fluid">
		<div class="panel-group">	
			<div class="page-header">
				<%@include file="authHeader.jsp" %>	
			</div>
		</div>
		<div class="panel-group">	
	        <div class="panel panel-default">
	              <!-- Default panel contents -->
	            <div class="panel-heading">
	            	<div id="successAlert" class="alert alert-success alert-dismissible fade in">
				    	<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
				    	<strong>User added successfully !</strong>
				  	</div>
	            </div>
	            <div id="warningAlert" class="alert alert-danger alert-dismissible fade in">
    				<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
    				<strong>Sorry!</strong> There was some issue while adding user.Please try again.
  				</div>
	            <div class="panel-body">
	            	 <c:choose>
	            	 <c:when test="${list}">
                                      <!-- Default panel contents -->
            <div class="panel-heading"><span class="lead">List of Users </span></div>
            <table id="userList" class="table table-striped table-bordered nowrap" cellspacing="0" width="100%">
                <thead>
                    <tr>
                        <th>Firstname</th>
                        <th>Lastname</th>
                        <th>Email</th>
                        <th>SSO ID</th>
                        <th>Roles</th>
                        <sec:authorize access="hasRole('ADMIN') or hasRole('DBA')">
                            <th width="100"></th>
                        </sec:authorize>
                        <sec:authorize access="hasRole('ADMIN')">
                            <th width="100"></th>
                        </sec:authorize>
                    </tr>
                </thead>
                <tbody>
                <c:forEach items="${users}" var="user">
                    <tr>
                        <td>${user.firstName}</td>
                        <td>${user.lastName}</td>
                        <td>${user.email}</td>
                        <td>${user.ssoId}</td>
                        <td><c:forEach items="${user.userRoles}" var="role">${role.type}</c:forEach></td>
                        <sec:authorize access="hasRole('ADMIN') or hasRole('DBA')">
                            <td><a id="editUrl" href="#" class="btn btn-success custom-width">edit</a><input id="hiddenSsoId"type="hidden" value="${user.ssoId}"></td>
                        </sec:authorize>
                        <sec:authorize access="hasRole('ADMIN')">
                            <td><a id="deleteUrl" href="#" class="btn btn-danger custom-width">delete</a><input id="hiddenSsoId"type="hidden" value="${user.ssoId}"></td>
                        </sec:authorize>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
                    </c:when>
                    <c:otherwise>
                 	</c:otherwise>
                  </c:choose>	
	            </div>
	        
	        </div>
		</div>
	</div>
	<!-- <div class="container-fluid text-center">    
	  <div class="row content">
	    <div class="col-sm-1 sidenav">
	      <p><a href="#">Link</a></p>
	      <p><a href="#">Link</a></p>
	      <p><a href="#">Link</a></p>
	    </div>
	    <div class="col-sm-8 text-left"></div> 
	  </div>
	  </div> -->
	<footer class="container-fluid text-center">
  		<p>Tenant ID</p>
	</footer>
 
<!-- The Modal -->
  <div class="modal fade" id="newUserModal">
    <div class="modal-dialog modal-dialog-centered">
      <div class="modal-content">
      
        <!-- Modal Header -->
        <div class="modal-header">
          <h4 class="modal-title">Add New User</h4>
          <!-- <button type="button" class="close" data-dismiss="modal">&times;</button> -->
        </div>
        
        <!-- Modal body -->
        <div class="modal-body">
        	<form action="#" method="POST" name="addUserForm">
            <div class="row">
                <div class="form-group col-md-12">
                    <label class="col-md-3 control-lable" for="firstName">First Name</label>
                    <div class="col-md-7">
                        <input type="text" name="firstName" id="firstName" class="form-control input-sm"/>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="form-group col-md-12">
                    <label class="col-md-3 control-lable" for="lastName">Last Name</label>
                    <div class="col-md-7">
                        <input type="text" name="lastName" id="lastName" class="form-control input-sm" />
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="form-group col-md-12">
                    <label class="col-md-3 control-lable" for="ssoId">SSO ID</label>
                    <div class="col-md-7">
                        <c:choose>
                            <c:when test="${edit}">
                                <input type="text" name="ssoId" id="ssoId" class="form-control input-sm" disabled="true"/>
                            </c:when>
                            <c:otherwise>
                                <input type="text" name="ssoId" id="ssoId" class="form-control input-sm" />
                            </c:otherwise>
                        </c:choose>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="form-group col-md-12">
                    <label class="col-md-3 control-lable" for="password">Password</label>
                    <div class="col-md-7">
                        <input type="password" name="password" id="password" class="form-control input-sm" />
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="form-group col-md-12">
                    <label class="col-md-3 control-lable" for="email">Email</label>
                    <div class="col-md-7">
                        <input type="text" name="email" id="email" class="form-control input-sm" />
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="form-group col-md-12">
                    <label class="col-md-3 control-lable" for="userRoles">Roles</label>
                    <div class="col-md-7">
						<select multiple class="form-control" id="userRoles">
					    <c:forEach items="${roles}" var="role">    
					        <option id="${role.id}" value="${role.id}">${role.type}</option>
					     </c:forEach>   
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
  <!-- ---------------------Modal ends here----------------------- -->
</body>
</html>
<script>

$(document).ready(function() {
	$('#successAlert').hide();
	$('#warningAlert').hide();
	
	$('#userList').DataTable({
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
	    rowReorder: true
	    
	});
});

$('#registerUser').click(function(e) {
	
	var token = $("meta[name='_csrf']").attr("content");
	var header = $("meta[name='_csrf_header']").attr("content");
	
	var userRoles = [];
	$.each($("#userRoles option:selected"), function(){            
		var role={};
		role.id=$(this).val();
		role.type=$(this).text();
		userRoles.push(role);
	});
	
    var user = {};
    user.firstName 	= $('#firstName').val();
    user.lastName 	= $('#lastName').val();
    user.ssoId 		= $('#ssoId').val();
    user.password 		= $('#password').val();
    user.email 		= $('#email').val();
    user.tenantId 	= "";
    user.forgotToken = "";
    user.userRoles	= userRoles;

    alert(JSON.stringify(user));
	
    //Prevent default submission of form
    e.preventDefault();
    
    //Remove all errors
    $('input').next().remove();
    
    $.ajax({
       type:'POST',
       async: false,
       url : 'addUser',
       contentType: 'application/json',
       data : JSON.stringify(user),
       dataType : 'json',
       beforeSend: function(xhr) {
           // here it is
           xhr.setRequestHeader(header, token);
       },
       success : function(res) {
       
          if(res=="success"){
             //Set response
            alert(res);
            $('#newUserModal').modal('hide');
            $('#successAlert').show();
          }else{
            //Set error messages
        	$('#newUserModal').modal('hide	');  
            $('#warningAlert').show();
          }
       }
 });
}); 

$('#manageUsers').click(function(e) {
	
	var token = $("meta[name='_csrf']").attr("content");
	var header = $("meta[name='_csrf_header']").attr("content");
    
    $.ajax({
       type:'GET',
       async: false,
       url : 'list',
       contentType: 'application/json',
       dataType : 'json',
       beforeSend: function(xhr) {
           // here it is
           xhr.setRequestHeader(header, token);
       },
       success : function(users) {
       
    	   alert(users);
         
       }
 });
}); 

/* $('#manageUsers').click(function(){
	$.get("list",
		    function(data, status){
		        alert("Data: " + data + "\nStatus: " + status);
		    });
}); */

</script>