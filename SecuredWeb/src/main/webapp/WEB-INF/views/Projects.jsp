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
				    	<strong>Project added successfully !</strong>
				  	</div>
	            </div>
	            <div id="warningAlert" class="alert alert-danger alert-dismissible fade in">
    				<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
    				<strong>Sorry!</strong> There was some issue while adding project.Please try again.
  				</div>
	            <div class="panel-body">
                  <table id="projectList" class="table table-striped table-bordered display" width="100%"></table>	
	            </div>
	        
	        </div>
		</div>
	</div>
	<footer class="container-fluid text-center">
  		<p>Tenant ID</p>
	</footer>
 
</body>
</html>
<script>

$(document).ready(function() {
	$('#successAlert').hide();
	$('#warningAlert').hide();
});

$('#addProject').click(function(e) {
	
	var token = $("meta[name='_csrf']").attr("content");
	var header = $("meta[name='_csrf_header']").attr("content");
	
    var project = {};
    project.name 	= $('#firstName').val();
    project.description 	= $('#lastName').val();
    project.ssoId 		= $('#ssoId').val();
    project
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
            $('#newProjectModal').modal('hide');
            $('#successAlert').show();
            $('#manageProjects').click();
          }else{
            //Set error messages
        	$('#newProjectModal').modal('hide	');  
            $('#warningAlert').show();
          }
       }
 });
}); 

$('#manageProjects').click(function(e) {
	
	var token = $("meta[name='_csrf']").attr("content");
	var header = $("meta[name='_csrf_header']").attr("content");
    
    $.ajax({
       type:'GET',
       async: false,
       url : 'listProject',
       contentType: 'application/json',
       dataType : 'json',
       beforeSend: function(xhr) {
           // here it is
           xhr.setRequestHeader(header, token);
       },
       success : function(projects) {
       
    	   fillDataInDataTable(projects);
       }
 });
}); 

function fillDataInDataTable(projects){
	var dataSet=[];
	projects.forEach(function(project){
		var dataEach = [];
		dataEach.push(project.name);
		dataEach.push(project.description);
		var childProjects = "";
		project.childProjects.forEach(function(project){
			childProjects = childProjects + project.name + " ,";
		});
		childProjects = childProjects.substring(0,childProjects.length-1);
		dataEach.push(childProjects);
		dataSet.push(dataEach);
	});
	
	//alert(JSON.stringify(dataSet));
	//alert(dataSet);
	
	if ($.fn.DataTable.isDataTable("#projectList")) {
		  $('#projectList').DataTable().clear().destroy();
		}

	
	$('#projectList').DataTable( {
        data: dataSet,
        columns: [
            { title: "Name" },
            { title: "Description" },
            { title: "Sub Projects" },
        ], 
        /*  responsive: {
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
        },  */
        "pagingType" : "full_numbers",
	    select	   : true,
	    rowReorder: true,
	    "columnDefs": [ {
	    	data : null,  
	    	responsivePriority: 1 , 
	    	orderable: false, 
	    	width : '200px' , 
	    	title : '' , 
	    	class : 'form-control' , 
	    	'defaultContent': '<button id="emailDeal"  style="  color : red ; color: rgb(201, 191, 197) ;  border-radius: 5px ; width: 100px; height: 100px; background-color: transparent; border-width: 0.5px; border-color: white;" >Click to Email Deal </button> <button id+"showDeal"  style="  margin-top: 10px color : red ; color: rgb(201, 191, 197) ; border-radius: 5px ; width: 100px; height: 100px; background-color: transparent; border-width: 0.5px; border-color: white;" >Click to Show Deal </button>'

        } ],
    });
}

</script>