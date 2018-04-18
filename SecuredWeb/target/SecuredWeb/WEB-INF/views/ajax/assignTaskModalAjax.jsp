        <!-- Modal Header -->
        <div class="modal-header">
          <h4 class="modal-title" id="AssignTaskModalHeading"></h4>
          <button type="button" class="close" data-dismiss="modal">&times;</button>
        </div>
        
        <!-- Modal body -->
        <div class="modal-body">
        	<form action="#" method="POST" id="AssignTaskForm" name="AssignTaskForm">
                <div class="form-group col-lg-12">
                    <label class="col-lg-5 control-lable" for="assignProject">Project</label>
                    <div class="col-lg-7">
						<select class="loadProjectTask form-control" id="assignProject"  onchange="loadAllTasksByProjectId(this);loadAllUsersByProjectId(this)" autofocus required>
						<option></option>
					    </select>
                    </div>
                </div>
                <div class="form-group col-lg-12">
                    <label class="col-lg-5 control-lable" for="assignTask">Task</label>
                    <div class="col-lg-7">
						<select class="form-control" id="assignTask" required>
						<option></option>
					    </select>
                    </div>
                </div>
                <div class="form-group col-lg-12" id="assignUserDiv">
                    <label class="col-lg-5 control-lable" for="assignUser">User</label>
                    <div class="col-lg-7">
	                    <select class="form-control" id="assignUser" required>
							<option></option>
						</select>
					</div>
                </div>
	            <!-- Modal footer -->
	        <div class="modal-footer">
	                <button id="AddUserTaskSubmit" type="submit" class="btn btn-primary" onCLick="addUserTask()">Assign</button>  
	                <button id="UpdateUserTaskSubmit" type="submit" class="btn btn-primary" onCLick="updateUserTask()">Update</button>  
	                <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
	            </div>	
            <input type="hidden" id="assignTaskId"/>
            </form>
        </div>
