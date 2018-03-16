<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<nav class="navbar navbar-expand-lg bg-dark navbar-dark">
  <!-- Brand -->
  <div class="container-fluid">
	<div class="navbar-header">
	  <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>                        
      </button>
	  <a class="navbar-brand" href="home">${tenantName}</a>
	</div>
	  <!-- Links -->
	<div class="collapse navbar-collapse" id="myNavbar">  
	  <ul class="nav navbar-nav">
	    <sec:authorize access="hasRole('ADMIN') or hasRole('DBA')">  
	    <!-- Dropdown -->
	    <li class="nav-item">
	      <a class="nav-link" href="home">Home</a>
	    </li>
	    <li class="nav-item dropdown">
	      <a class="nav-link dropdown-toggle" href="#" id="navbardrop" data-toggle="dropdown">
	        Admin
	      </a>
	      <div class="dropdown-menu">
	        <a id="manageUsers" class="dropdown-item" href="#" >Manage Users</a>
	        <a class="dropdown-item" href="#" data-toggle="modal"  data-target="#UserModalAjax">Add User</a>
	      </div>
	    </li>
	    </sec:authorize>
	    <li class="nav-item dropdown">
	      <a class="nav-link dropdown-toggle" href="#" id="navbardrop" data-toggle="dropdown">
	        Projects
	      </a>
	      <div class="dropdown-menu">
	        <a id="manageProjects" class="dropdown-item" href="#" >Manage Projects</a>
	        <a class="dropdown-item" href="#" data-toggle="modal"  data-target="#ProjectModalAjax">Add Project</a>
	      	<a class="dropdown-item" href="#" data-toggle="modal"   data-target="#AssignProjectModalAjax">Assign Project</a>
	      	<a id="manageUserProjects" class="dropdown-item" href="#" >Manage Assigned Projects</a>
	      </div>
	    </li>
	    <li class="nav-item dropdown">
	      <a class="nav-link dropdown-toggle" href="#" id="navbardrop" data-toggle="dropdown">
	        Tasks
	      </a>
	      <div class="dropdown-menu">
	        <a id="manageTasks" class="dropdown-item" href="#" >Manage Tasks</a>
	        <a class="dropdown-item" href="#" data-toggle="modal"  data-target="#TaskModalAjax">Add Task</a>
	      </div>
	    </li>
	    <sec:authorize access="hasRole('ADMIN') or hasRole('DBA')"> 
	    <li class="nav-item dropdown">
	      <a class="nav-link dropdown-toggle" href="#" id="navbardrop" data-toggle="dropdown">
	        Task Status
	      </a>
	      <div class="dropdown-menu">
	        <a id="manageTaskStatus" class="dropdown-item" href="#" >Manage Task Status</a>
	        <a class="dropdown-item" href="#" data-toggle="modal" data-target="#TaskStatusModalAjax">Add Task Status</a>
	      </div>
	    </li>
	    </sec:authorize>
	    <sec:authorize access="hasRole('USER')">  
	    <li class="nav-item">
	      <a class="nav-link" href="#">Team Members</a>
	    </li>
	    </sec:authorize>
	    <!-- Dropdown -->
	    <li class="nav-item dropdown">
	      <a class="nav-link dropdown-toggle" href="#" id="navbardrop" data-toggle="dropdown">
	        Account
	      </a>
	      <div class="dropdown-menu">
	        <a class="dropdown-item" href="#">View Profile</a>
	        <a class="dropdown-item" href="#">Change Password</a>
	      </div>
	    </li>
	  </ul>
	  <ul class="nav navbar-nav navbar-right">
        <li><a href="logout" class="btn btn-info btn-sm"><span class="glyphicon glyphicon-log-out"></span> Logout</a></li>
      </ul>
      </div>
	</div>
</nav>