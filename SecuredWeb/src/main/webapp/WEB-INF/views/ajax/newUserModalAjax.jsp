	        <!-- Modal Header -->
	        <div class="modal-header">
	          <h4 class="modal-title">Register New User</h4>
	          <!-- <button type="button" class="close" data-dismiss="modal">&times;</button> -->
	        </div>
	        
	        <!-- Modal body -->
	        <div id = "newUserModal"  class="modal-body">
	        	<form action="#" method="POST" name="addUserForm">
	                <div class="form-group col-lg-12">
	                    <label class="col-lg-3 control-lable" for="newFirstName">First Name<span style="color:red;">*</span></label>
	                    <div class="col-lg-7">
	                        <input type="text" name="newFirstName" id="newFirstName" class="form-control input-sm" autofocus/>
	                    </div>
	                </div>
	                <div class="form-group col-lg-12">
	                    <label class="col-lg-3 control-lable" for="newLastName">Last Name<span style="color:red;">*</span></label>
	                    <div class="col-lg-7">
	                        <input type="text" name="newLastName" id="newLastName" class="form-control input-sm" />
	                    </div>
	                </div>
	                <div class="form-group col-lg-12">
	                    <label class="col-lg-3 control-lable" for="newSsoId">User ID<span style="color:red;">*</span></label>
	                    <div class="col-lg-7">
							<input type="text" name="newSsoId" id="newSsoId" class="form-control input-sm" />
	                    </div>
	                </div>
	                <div class="form-group col-lg-12">
	                    <label class="col-lg-3 control-lable" for="newPassword">Password<span style="color:red;">*</span></label>
	                    <div class="col-lg-7">
	                        <input type="password" name="newPassword" id="newPassword" class="form-control input-sm" />
	                    </div>
	                </div>
	                <div class="form-group col-lg-12">
	                    <label class="col-lg-3 control-lable" for="confirmPassword">Confirm Password<span style="color:red;">*</span></label>
	                    <div class="col-lg-7">
	                        <input type="password" name="newConfirmPassword" id="newConfirmPassword" class="form-control input-sm" />
	                    </div>
	                </div>
	                <div class="form-group col-lg-12">
	                    <label class="col-lg-3 control-lable" for="newEmail">Email<span style="color:red;">*</span></label>
	                    <div class="col-lg-7">
	                        <input type="text" name="newEmail" id="newEmail" class="form-control input-sm" />
	                    </div>
	                </div>
	                <div class="form-group col-lg-12">
	                    <label class="col-lg-3 control-lable" for="tenants">Tenant<span style="color:red;">*</span></label>
	                    <div class="col-lg-7">
							<select  class="form-control" id="tenants">
						     <option></option>
						     </select>
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