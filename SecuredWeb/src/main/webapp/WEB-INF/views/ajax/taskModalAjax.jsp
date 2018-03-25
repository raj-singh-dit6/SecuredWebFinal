       <!-- Modal Header -->
        <div class="modal-header">
          <h4 class="modal-title" id="TaskModalHeading">Add New Task</h4>
          <button type="button" class="close" data-dismiss="modal">&times;</button>
        </div>
        
        <!-- Modal body -->
        <div class="modal-body">
        	<form action="#" method="POST" name="addTaskForm">
            <div class="row">
                <div class="form-group col-md-12">
                    <label class="col-md-3 control-lable" for="taskProject">Project</label>
                    <div class="col-md-7">
                        <select  class="form-control" id="taskProject" autofocus>
					    <option></option>
					    </select>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="form-group col-md-12">
                    <label class="col-md-3 control-lable" for="taskName">Task Name</label>
                    <div class="col-md-7">
                        <input type="text" name="taskName" id="taskName" class="form-control input-sm"/>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="form-group col-md-12">
                    <label class="col-md-3 control-lable" for="taskDesc">Task Description</label>
                    <div class="col-md-7">
                        <input type="text" name="taskDesc" id="taskDesc" class="form-control input-sm" />
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="form-group col-md-12">
                    <label class="col-md-3 control-lable" for="taskStatus">Task Status</label>
                    <div class="col-md-7">
						<select  class="form-control" id="taskStatus">
							<option></option>   
					      </select>
                    </div>
                </div>
            </div>
            <input type="hidden" id="taskId"/>
            </form>
        </div>
        <!-- Modal footer -->
        <div class="modal-footer">
                <button id="AddTaskSubmit" type="button" class="btn btn-primary" onClick="addTask()">Add</button> 
                <button id="UpdateTaskSubmit" type="button" class="btn btn-primary" onClick="updateTask()">Update</button>  
                <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
        </div>	