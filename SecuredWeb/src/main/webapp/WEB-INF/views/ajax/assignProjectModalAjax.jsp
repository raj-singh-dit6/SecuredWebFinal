        <!-- Modal Header -->
        <div class="modal-header">
          <h4 class="modal-title" id="AssignProjectModalHeading"></h4>
          <button type="button" class="close" data-dismiss="modal">&times;</button>
        </div>
        
        <!-- Modal body -->
        <div class="modal-body">
        	<form action="#" method="POST" name="AssignProjectForm">
                <div class="form-group col-lg-12">
                    <label class="col-lg-5 control-lable" for="assignProject">Project</label>
                    <div class="col-lg-7">
						<select class="form-control" id="assignProject" autofocus required>
						<option></option>
					    </select>
                    </div>
                </div>
                <div class="form-group col-lg-12">
                    <label class="col-lg-5 control-lable" for="assignUser">User</label>
                    <div class="col-lg-7">
	                    <select class="form-control" id="assignUser" required>
							<option></option>
						</select>
					</div>
                </div>
            <input type="hidden" id="assignProjId"/>
            </form>
        </div>
        <!-- Modal footer -->
        <div class="modal-footer">
                <button id="AddUserProjectSubmit" type="button" class="btn btn-primary" onCLick="addUserProject()">Assign</button>  
                <button id="UpdateUserProjectSubmit" type="button" class="btn btn-primary" onCLick="updateUserProject()">Update</button>  
                <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
            </div>	
