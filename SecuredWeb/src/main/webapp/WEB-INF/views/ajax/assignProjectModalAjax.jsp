<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>    
      
        <!-- Modal Header -->
        <div class="modal-header">
          <h4 class="modal-title" id="AssignProjectModalHeading"></h4>
          <button type="button" class="close" data-dismiss="modal">&times;</button>
        </div>
        
        <!-- Modal body -->
        <div class="modal-body">
        	<form action="#" method="POST" name="AssignProjectForm">
            <div class="row">
                <div class="form-group col-md-12">
                    <label class="col-md-3 control-lable" for="assignProject">Projects</label>
                    <div class="col-md-7">
						<select class="form-control" id="assignProject">
						<option></option>
					    </select>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="form-group col-md-12">
                    <label class="col-md-3 control-lable" for="assignUser">Users</label>
                    <div class="col-md-7">
                    <select class="form-control" id="assignUser">
						<option></option>
					</select>
					</div>
                </div>
            </div>
            <input type="hidden" id="assignProjId"/>
            </form>
        </div>
        <!-- Modal footer -->
        <div class="modal-footer">
                <button id="AddUserProjectSubmit" type="button" class="btn btn-primary" onCLick="addUserProject()">Add</button>  
                <button id="UpdateUserProjectSubmit" type="button" class="btn btn-primary" onCLick="updateUserProject()">Update</button>  
                <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
            </div>	
