<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>    
      
        <!-- Modal Header -->
        <div class="modal-header">
          <h4 class="modal-title" id="UserModalHeading"></h4>
          <button type="button" class="close" data-dismiss="modal">&times;</button>
        </div>
        
        <!-- Modal body -->
        <div class="modal-body">
        	<form action="#" method="POST" name="UserForm">
            <div class="row">
                <div class="form-group col-md-12">
                    <label class="col-md-3 control-lable" for="userFirstName">First Name</label>
                    <div class="col-md-7">
                        <input type="text" name="userFirstName" id="userFirstName" class="form-control input-sm"/>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="form-group col-md-12">
                    <label class="col-md-3 control-lable" for="userLastName">Last Name</label>
                    <div class="col-md-7">
                        <input type="text" name="userLastName" id="userLastName" class="form-control input-sm" />
                    </div>
                </div>
            </div>
            <div class="row" id="userSsoId">
                <div class="form-group col-md-12">
                    <label class="col-md-3 control-lable" for="userSsoId">SSO ID</label>
                    <div class="col-md-7">
                           <input type="text" name="userSsoId" id="userSsoId" class="form-control input-sm" />
                    </div>
                </div>
            </div>
            <div class="row" id="userPassword">
                <div class="form-group col-md-12">
                    <label class="col-md-3 control-lable" for="userPassword">Password</label>
                    <div class="col-md-7">
                        <input type="password" name="userPassword" id="userPassword" class="form-control input-sm" />
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="form-group col-md-12">
                    <label class="col-md-3 control-lable" for="userEmail">Email</label>
                    <div class="col-md-7">
                        <input type="text" name="userEmail" id="userEmail" class="form-control input-sm" />
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="form-group col-md-12">
                    <label class="col-md-3 control-lable" for="userRoles">Roles</label>
                    <div class="col-md-7">
						<select multiple class="form-control" id="userRoles">
					    </select>
                    </div>
                </div>
            </div>
            </form>
        </div>
        <!-- Modal footer -->
        <div class="modal-footer">
                <button id="AddUserSubmit" type="button" class="btn btn-primary" onCLick="addUser()">Add</button>  
                <button id="UpdateUserSubmit" type="button" class="btn btn-primary" onCLick="updateUser()">Update</button>  
                <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
            </div>	
