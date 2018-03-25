        <!-- Modal Header -->
        <div class="modal-header">
          <h4 class="modal-title" id="UpdateTaskByUserModalHeading"></h4>
          <button type="button" class="close" data-dismiss="modal">&times;</button>
        </div>
        
        <!-- Modal body -->
        <div class="modal-body">
        	<form action="#" method="POST" name="UpdateUserTaskForm">
            <div class="row">
                <div class="form-group col-md-12">
                    <label class="col-md-3 control-lable" for="userProjectName">Project</label>
                    <div class="col-md-7">
                    	<input class="form-control" id="userProjectName" value="" disabled />
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="form-group col-md-12">
                    <label class="col-md-3 control-lable" for="userTaskName">Task</label>
                    <div class="col-md-7">
						<input class="form-control" id="userTaskName" value="" disabled/>
                    </div>
                </div>
            </div>
            <div class="row" id="assignUserDiv">
                <div class="form-group col-md-12" >
                    <label class="col-md-3 control-lable" for="userTaskDesc">Task Description</label>
                    <div class="col-md-7">
						<input  class="form-control" id="userTaskDesc" value="" autofocus/>
					</div>
                </div>
            </div>
            <div class="row" id="assignUserDiv">
                <div class="form-group col-md-12" >
                    <label class="col-md-3 control-lable" for="userTaskStatus">Task Status</label>
                    <div class="col-md-7">
						<select class="form-control" id="userTaskStatus">
						<option></option>
						</select>
					</div>
                </div>
            </div>
            <input type="hidden" id="userTaskId"/>
            </form>
        </div>
        <!-- Modal footer -->
        <div class="modal-footer">
                <button id="UpdateTaskByUserSubmit" type="button" class="btn btn-primary" onCLick="updateTaskByUser()">Update</button>  
                <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
            </div>	
