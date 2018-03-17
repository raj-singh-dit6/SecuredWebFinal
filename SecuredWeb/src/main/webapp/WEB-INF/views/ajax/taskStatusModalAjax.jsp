
        <!-- Modal Header -->
        <div class="modal-header">
          <h4 class="modal-title" id="TaskStatusModalHeading">Add New Task Status</h4>
          <!-- <button type="button" class="close" data-dismiss="modal">&times;</button> -->
        </div>
        
        <!-- Modal body -->
        <div class="modal-body">
        	<form action="#" method="POST" name="addTaskStatusForm">
            <div class="row">
                <div class="form-group col-md-12">
                    <label class="col-md-3 control-lable" for="taskStatus">Status</label>
                    <div class="col-md-7">
                        <input type="text" name="taskStatusName" id="taskStatusName" class="form-control input-sm"/>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="form-group col-md-12">
                    <label class="col-md-3 control-lable" for="taskStatusColour">Flag Color</label>
                    <div class="col-md-7">
                        <select id="taskStatusColour" type="text" class="form-control" >
                        	<option></option>
                        	<option id="orange" value="orange">Orange</option>
                        	<option id="blue" value="blue">Blue</option>
                        	<option id="purple" value="purple">Purple</option>
                        	<option id="green" value="green">Green</option>
                        </select>
                        <!-- <input type="text" name="taskStatusColour" id="taskStatusColour" class="form-control input-sm" /> -->
                    </div>
                </div>
            </div>
            <input type="hidden" id="taskStatusId">
            </form>
        </div>
        <!-- Modal footer -->
        <div class="modal-footer">
                <button id="AddTaskStatusSubmit" type="button" class="btn btn-primary" onClick="addTaskStatus()">Add</button> 
                <button id="UpdateTaskStatusSubmit" type="button" class="btn btn-primary" onClick="updateTaskStatus()">Update</button>  
                <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
        </div>
