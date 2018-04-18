<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<nav class="navbar navbar-expand-sm bg-dark navbar-dark">
  <!-- Brand -->
  <div class="container-fluid">
	<div class="navbar-header">
	  <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>                        
      </button>
	  <a class="navbar-brand" href="#">Tenant ID</a>
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
	        Administration
	      </a>
	      <div class="dropdown-menu">
	        <a id="manageUsers" class="dropdown-item" href="#" >Manage Users</a>
	        <a class="dropdown-item" href="#" data-toggle="modal" data-target="#newUserModal">Add User</a>
	      </div>
	    </li>
	    </sec:authorize>
	    <li class="nav-item">
	      <a class="nav-link" href="#">Projects</a>
	    </li>
	    <li class="nav-item">
	      <a class="nav-link" href="#">Tasks</a>
	    </li>
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