        <!-- Modal Header -->
        <div class="modal-header">
          <h4 class="modal-title" id="UserModalHeading"></h4>
          <button type="button" class="close" data-dismiss="modal">&times;</button>
        </div>
        
        <!-- Modal body -->
        <div class="modal-body">
        	<form action="#" method="POST" name="UserForm" id="UserForm">
                <div class="form-group col-lg-12">
                    <label class="col-lg-5 control-lable" for="userFirstName">First Name<span style="color:red;">*</span></label>
                    <div class="input-group col-lg-7">
                        <input type="text" name="userFirstName" id="userFirstName" class="form-control input-sm" placeholder="First Name" autofocus/>
                    </div>
                </div>
                <div class="form-group col-lg-12">
                    <label class="col-lg-5 control-lable" for="userLastName">Last Name<span style="color:red;">*</span></label>
                    <div class="input-group col-lg-7">
                        <input type="text" name="userLastName" id="userLastName" class="form-control input-sm" placeholder="Last Name"/>
                    </div>
                </div>
                <div class="form-group col-lg-12" id="userSsoIdDiv">
                    <label class="col-lg-5 control-lable" for="userSsoId">User ID<span style="color:red;">*</span></label>
                    <div class="input-group col-lg-7">
                           <input type="text" name="userSsoId" id="userSsoId" class="form-control input-sm" placeholder="User ID" />
                    </div>
                </div>
                <div class="form-group col-lg-12" id="userPasswordDiv">
                    <label class="col-lg-5 control-lable" for="userPassword">Password<span style="color:red;">*</span></label>
                    <div class="input-group col-lg-7">
                        <input type="password" name="userPassword" id="userPassword" class="form-control input-sm" placeholder="Password" />
                    </div>
                </div>
                <div class="form-group col-lg-12" id="userConfirmPasswordDiv">
                    <label class="col-lg-5 control-lable" for="userConfirmPassword">Confirm Password<span style="color:red;">*</span></label>
                    <div class="input-group col-lg-7">
                        <input type="password" name="userConfirmPassword" id="userConfirmPassword" class="form-control input-sm" placeholder="Confirm Password" />
                    </div>
                </div>
                <div class="form-group col-lg-12">
                    <label class="col-lg-5 control-lable" for="userEmail">Email<span style="color:red;">*</span></label>
                    <div class="input-group col-lg-7">
                        <input type="email" name="userEmail" id="userEmail" class="form-control input-sm" placeholder="Email"/>
                    </div>
                </div>
                <div class="form-group col-lg-12">
                    <label class="col-lg-5 control-lable" for="userRoles">Roles<span style="color:red;">*</span></label>
                    <div class="input-group col-lg-7">
						<select multiple class="form-control" id="userRoles">
					    </select>
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
