       <!-- Modal Header -->
        <div class="modal-header">
          <h4 class="modal-title" id="TaskModalHeading">Add New Task</h4>
          <button type="button" class="close" data-dismiss="modal">&times;</button>
        </div>
        
        <!-- Modal body -->
        <div class="modal-body">
        	<form action="#" method="POST" id="TaskForm" name="TaskForm">
                <div class="form-group col-lg-12">
                    <label class="col-lg-5 control-lable" for="taskProject">Project</label>
                    <div class="col-lg-7">
                        <select  class="form-control" id="taskProject" autofocus required>
					    	<option></option>
					    </select>
                    </div>
                </div>
                <div class="form-group col-lg-12">
                    <label class="col-lg-5 control-lable" for="taskName">Task Name</label>
                    <div class="col-lg-7">
                        <input type="text" name="taskName" id="taskName" class="form-control input-sm" required/>
                    </div>
                </div>
                <div class="form-group col-lg-12">
                    <label class="col-lg-5 control-lable" for="taskDesc">Task Description</label>
                    <div class="col-lg-7">
                        <input type="text" name="taskDesc" id="taskDesc" class="form-control input-sm" />
                    </div>
                </div>
                <div class="form-group col-lg-12">
                    <label class="col-lg-5 control-lable" for="taskStatus">Task Status</label>
                    <div class="col-lg-7">
						<select  class="form-control" id="taskStatus" required>
					      </select>
                    </div>
                </div>
	            <!-- Modal footer -->
	        <div class="modal-footer">
	                <button id="AddTaskSubmit" type="submit" class="add btn btn-primary" onclick="addTask();">Add</button> 
	                <button id="UpdateTaskSubmit" type="submit" class="update btn btn-primary" onclick="updateTask();">Update</button>  
	                <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
	        </div>	
            <input type="hidden" id="taskId"/>
            </form>
        </div>
