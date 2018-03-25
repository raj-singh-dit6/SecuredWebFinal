	        <!-- Modal Header -->
	        <div class="modal-header">
	          <h4 class="modal-title">Register New User</h4>
	          <!-- <button type="button" class="close" data-dismiss="modal">&times;</button> -->
	        </div>
	        
	        <!-- Modal body -->
	        <div id = "newUserModal"  class="modal-body">
	        	<form action="#" method="POST" name="addUserForm">
	            <div class="row">
	                <div class="form-group col-md-12">
	                    <label class="col-md-3 control-lable" for="newFirstName">First Name</label>
	                    <div class="col-md-7">
	                        <input type="text" name="newFirstName" id="newFirstName" class="form-control input-sm"/>
	                    </div>
	                </div>
	            </div>
	            <div class="row">
	                <div class="form-group col-md-12">
	                    <label class="col-md-3 control-lable" for="newLastName">Last Name</label>
	                    <div class="col-md-7">
	                        <input type="text" name="newLastName" id="newLastName" class="form-control input-sm" />
	                    </div>
	                </div>
	            </div>
	            <div class="row">
	                <div class="form-group col-md-12">
	                    <label class="col-md-3 control-lable" for="newSsoId">SSO ID</label>
	                    <div class="col-md-7">
							<input type="text" name="newSsoId" id="newSsoId" class="form-control input-sm" />
	                    </div>
	                </div>
	            </div>
	            <div class="row">
	                <div class="form-group col-md-12">
	                    <label class="col-md-3 control-lable" for="newPassword">Password</label>
	                    <div class="col-md-7">
	                        <input type="password" name="newPassword" id="newPassword" class="form-control input-sm" />
	                    </div>
	                </div>
	            </div>
	            <div class="row">
	                <div class="form-group col-md-12">
	                    <label class="col-md-3 control-lable" for="confirmPassword">Confirm Password</label>
	                    <div class="col-md-7">
	                        <input type="password" name="confirmPassword" id="confirmPassword" class="form-control input-sm" />
	                    </div>
	                </div>
	            </div>
	            <div class="row">
	                <div class="form-group col-md-12">
	                    <label class="col-md-3 control-lable" for="newEmail">Email</label>
	                    <div class="col-md-7">
	                        <input type="text" name="newEmail" id="newEmail" class="form-control input-sm" />
	                    </div>
	                </div>
	            </div>
	            <div class="row">
	                <div class="form-group col-md-12">
	                    <label class="col-md-3 control-lable" for="tenants">Tenant</label>
	                    <div class="col-md-7">
							<select  class="form-control" id="tenants">
						     <option></option>
						     </select>
	                    </div>
	                </div>
	            </div>
	            </form>
	        </div>
	        <!-- Modal footer -->
	        <div class="modal-footer">
	                <button id="registerUserSubmit" type="button" onClick="registerUserSubmit()" class="btn btn-primary">Register</button>  
	                <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
	         </div>	
    <!-- ------------------Modal ends here--------------- -->   