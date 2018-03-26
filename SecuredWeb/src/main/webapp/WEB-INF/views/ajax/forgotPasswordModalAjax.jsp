    <!-- Modal Header -->
        <div class="modal-header">
          <h4 class="modal-title" id="ForgotPasswordModalHeading">Reset Your Password</h4>
          <button type="button" class="close" data-dismiss="modal">&times;</button>
        </div>
        
        <!-- Modal body -->
        <div class="modal-body">
        	<form action="#" method="POST" name="ForgotPasswordForm">
                <div class="form-group col-lg-12">
                    <label class="col-lg-5 control-lable" for="Email">Email<span style="color:red;">*</span></label>
                    <div class="col-lg-7">
						<input class="form-control" id="Email" autofocus/>
                    </div>
                </div>
            </form>
        </div>
        <!-- Modal footer -->
        <div class="modal-footer">
         <button type="button" data-toggle="modal" data-target="#ForgotPasswordModalAjax" class="btn" type="submit" onClick="resetPass()">Reset</button>
		</div>	
