        <!-- Modal Header -->
        <div class="modal-header">
          <h4 class="modal-title" id="AssignTaskModalHeading"></h4>
          <button type="button" class="close" data-dismiss="modal">&times;</button>
        </div>
        
        <!-- Modal body -->
        <div class="modal-body">
        	<form action="#" method="POST" name="AssignTaskForm">
            <div class="row">
                <div class="form-group col-md-12">
                    <label class="col-md-3 control-lable" for="assignProject">Projects</label>
                    <div class="col-md-7">
						<select class="loadProjectTask form-control" id="assignProject" onchange="loadAllTasksByProjectId(this);loadAllUsersByProjectId(this)">
						<option></option>
					    </select>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="form-group col-md-12">
                    <label class="col-md-3 control-lable" for="assignTask">Task</label>
                    <div class="col-md-7">
						<select class="form-control" id="assignTask">
						<option></option>
					    </select>
                    </div>
                </div>
            </div>
            <div class="row" id="assignUserDiv">
                <div class="form-group col-md-12" >
                    <label class="col-md-3 control-lable" for="assignUser">Users</label>
                    <div class="col-md-7">
                    <select class="form-control" id="assignUser">
						<option></option>
					</select>
					</div>
                </div>
            </div>
            <input type="hidden" id="assignTaskId"/>
            </form>
        </div>
        <!-- Modal footer -->
        <div class="modal-footer">
                <button id="AddUserTaskSubmit" type="button" class="btn btn-primary" onCLick="addUserTask()">Add</button>  
                <button id="UpdateUserTaskSubmit" type="button" class="btn btn-primary" onCLick="updateUserTask()">Update</button>  
                <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
            </div>	
