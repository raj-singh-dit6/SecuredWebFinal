    <!-- Modal Header -->
        <div class="modal-header">
          <h4 class="modal-title" id="ForgotPasswordModalHeading">Reset Your Password</h4>
          <button type="button" class="close" data-dismiss="modal">&times;</button>
        </div>
        
        <!-- Modal body -->
        <div class="modal-body">
        	<form action="#" method="POST" name="ForgotPasswordForm">
            <div class="row">
                <div class="form-group col-md-12">
                    <label class="col-md-3 control-lable" for="Email">Email</label>
                    <div class="col-md-7">
						<input class="form-control" id="Email"/>
                    </div>
                </div>
            </div>
            </form>
        </div>
        <!-- Modal footer -->
        <div class="modal-footer">
         <button type="button" data-toggle="modal" data-target="#ForgotPasswordModalAjax" class="btn" type="submit" onClick="resetPass()">Reset</button>
		</div>	
