<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>    
      
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
                           <input type="text" name="ssoId" id="ssoId" class="form-control input-sm" />
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
                    </div>
                </div>
            </div>
            </form>
        </div>
        <!-- Modal footer -->
        <div class="modal-footer">
                <button id="addUser" type="button" class="btn btn-primary">Add</button>  
                <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
            </div>	
