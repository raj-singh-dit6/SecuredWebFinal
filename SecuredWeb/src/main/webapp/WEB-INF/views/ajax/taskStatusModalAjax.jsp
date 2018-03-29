        <!-- Modal Header -->
        <div class="modal-header">
          <h4 class="modal-title" id="TaskStatusModalHeading">Add New Task Status</h4>
          <!-- <button type="button" class="close" data-dismiss="modal">&times;</button> -->
        </div>
        
        <!-- Modal body -->
        <div class="modal-body">
        	<form id="TaskStatusForm" name="TaskStatusForm">
                <div class="form-group col-lg-12">
                    <label class="col-lg-5 control-lable" for="taskStatus">Status</label>
                    <div class="col-lg-7">
                        <input type="text" name="taskStatusName" id="taskStatusName" class="form-control input-sm" autofocus required/>
                    </div>
                </div>
                <div class="form-group col-lg-12">
                    <label class="col-lg-5 control-lable" for="taskStatusColour">Flag Color</label>
                    <div class="col-lg-7">
                       <input id="taskStatusColour" type="text" class="form-control" value="#5367ce"  required/>
                    </div>
                </div>
		                 <!-- Modal footer -->
		        <div class="modal-footer">
		                <button id="AddTaskStatusSubmit" type="submit" class="add btn btn-primary" onCLick="addTaskStatus();">Add</button> 
		                <button id="UpdateTaskStatusSubmit" type="submit" class="update btn btn-primary" onCLick="updateTaskStatus();" >Update</button>  
		                <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
		        </div>
            <input type="hidden" id="taskStatusId">
            </form>
        </div>
       
