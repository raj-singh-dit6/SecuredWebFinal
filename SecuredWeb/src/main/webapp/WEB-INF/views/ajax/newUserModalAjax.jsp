   			        <!-- Modal Header -->
	        <div class="modal-header">
	          <h4 class="modal-title">Register New User</h4>
	          <!-- <button type="button" class="close" data-dismiss="modal">&times;</button> -->
	        </div>
	        
	        <!-- Modal body -->
	        <div id = "newUserModal"  class="modal-body">
	        	<form action="#" method="POST" id="addUserForm" name="addUserForm">
	                <div class="form-group col-lg-12">
	                    <label class="col-lg-5 control-lable" for="tenants">Tenant</label>
	                    <div class="col-lg-7">
							<select  class="form-control" id="tenants" required>
						     <option></option>
						     </select>
	                    </div>
	                </div>
	                <div class="form-group col-lg-12">
	                    <label class="col-lg-5 control-lable" for="newFirstName">First Name</label>
	                    <div class="col-lg-7">
	                        <input type="text" name="newFirstName" id="newFirstName" class="form-control input-sm" autofocus required/>
	                    </div>
	                </div>
	                <div class="form-group col-lg-12">
	                    <label class="col-lg-5 control-lable" for="newLastName">Last Name</label>
	                    <div class="col-lg-7">
	                        <input type="text" name="newLastName" id="newLastName" class="form-control input-sm" required/>
	                    </div>
	                </div>
	                <div class="form-group col-lg-12">
	                    <label class="col-lg-5 control-lable" for="newSsoId">User ID</label>
	                    <div class="col-lg-7">
							<input type="text" name="newSsoId" id="newSsoId" class="unique form-control input-sm" onblur="isUnique('user',this,'ssoId')" required/>
	                    </div>
	                </div>
	                <div class="form-group col-lg-12">
	                    <label class="col-lg-5 control-lable" for="newPassword">Password</label>
	                    <div class="col-lg-7">
	                        <input type="password" name="newPassword" id="newPassword" class="form-control input-sm" required />
	                    </div>
	                </div>
	                <div class="form-group col-lg-12">
	                    <label class="col-lg-5 control-lable" for="confirmPassword">Confirm Password</label>
	                    <div class="col-lg-7">
	                        <input type="password" name="newConfirmPassword" id="newConfirmPassword" class="form-control input-sm" required/>
	                    </div>
	                </div>
	                <div class="form-group col-lg-12">
	                    <label class="col-lg-5 control-lable" for="newEmail">Email</label>
	                    <div class="col-lg-7">
	                        <input type="text" name="newEmail" id="newEmail" class="form-control input-sm" onblur="isUnique('user',this,'email')" required/>
	                    </div>
	                </div>
							<!-- Modal footer -->
			        <div class="modal-footer">
			                <button id="registerUserSubmit" type="submit" class="btn btn-primary" onclick="registerUserSubmit();">Register</button>  
			                <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
			         </div>
	            </form>
	        </div>
    <!-- ------------------Modal ends here--------------- -->