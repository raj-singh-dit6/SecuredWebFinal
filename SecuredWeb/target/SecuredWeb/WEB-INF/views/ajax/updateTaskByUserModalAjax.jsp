        <!-- Modal Header -->
        <div class="modal-header">
          <h4 class="modal-title" id="UpdateTaskByUserModalHeading"></h4>
          <button type="button" class="close" data-dismiss="modal">&times;</button>
        </div>
        
        <!-- Modal body -->
        <div class="modal-body">
        	<form action="#" method="POST" id="UpdateUserTaskForm" name="UpdateUserTaskForm">
                <div class="form-group col-lg-12" >
                    <label class="col-lg-5 control-lable" for="userProjectName">Project</label>
                    <div class="col-lg-7">
                    	<input class="form-control" id="userProjectName" value="" disabled/>
                    </div>
                </div>
                <div class="form-group col-lg-12">
                    <label class="col-lg-5 control-lable" for="userTaskName">Task</label>
                    <div class="col-lg-7">
						<input class="form-control" id="userTaskName" value="" disabled/>
                    </div>
                </div>
                <div class="form-group col-lg-12" id="assignUserDiv">
                    <label class="col-lg-5 control-lable" for="userTaskDesc">Task Description</label>
                    <div class="col-lg-7">
						<input  class="form-control" id="userTaskDesc" value="" autofocus required/>
					</div>
                </div>
                <div class="form-group col-lg-12" id="assignUserDiv">
                    <label class="col-lg-5 control-lable" for="userTaskStatus">Task Status</label>
                    <div class="col-lg-7">
						<select class="form-control" id="userTaskStatus" required>
							<option></option>
						</select>
					</div>
                </div>
                <!-- Modal footer -->
		        <div class="modal-footer">
		                <button id="UpdateTaskByUserSubmit" type="submit" class="btn btn-primary" onCLick="updateTaskByUser()">Update</button>  
		                <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
		        </div>	
            <input type="hidden" id="userTaskId"/>
            </form>
        </div>
