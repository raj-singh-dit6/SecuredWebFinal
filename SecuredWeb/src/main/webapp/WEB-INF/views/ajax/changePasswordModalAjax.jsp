       <!-- Modal Header -->
        <div class="modal-header">
          <h4 class="modal-title" id="">Change Password</h4>
          <button type="button" class="close" data-dismiss="modal">&times;</button>
        </div>
        
        <!-- Modal body -->
        <div class="modal-body">
        	<form action="#" method="POST" name="changePasswordForm">
                <div class="form-group col-lg-12">
                    <label class="col-lg-5 control-lable" for="userCurrentPassword">Current Password</label>
                    <div class="col-lg-7">
						<input type="password" name="userPassword" id="userCurrentPassword" class="form-control input-sm" required/>
                    </div>
                </div>
                <div class="form-group col-lg-12">
                    <label class="col-lg-5 control-lable" for="userPassword">Password</label>
                    <div class="col-lg-7">
						<input type="password" name="userPassword" id="userPassword" class="form-control input-sm" required/>
                    </div>
                </div>
                <div class="form-group col-lg-12">
                    <label class="col-lg-5 control-lable" for="userConfirmPassword">Confirm Password</label>
                    <div class="col-lg-7">
                        <input type="password" name="userConfirmPassword" id="userConfirmPassword" class="form-control input-sm" required/>
                    </div>
                </div>
            </form>
        </div>
        <!-- Modal footer -->
        <div class="modal-footer" id="ChangePasswordFooter">
        		<button id="ChangePasswordSubmit" type="button" class="btn btn-primary" onClick="changePassword()">Change</button> 
                <button type="button" class="btn btn-secondary" data-dismiss="modal" >Close</button>
        </div>	