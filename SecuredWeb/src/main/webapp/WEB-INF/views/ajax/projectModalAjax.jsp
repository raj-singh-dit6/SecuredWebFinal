        <!-- Modal Header -->
        <div class="modal-header">
          <h4 class="modal-title" id="ProjectModalHeading"></h4>
          <button type="button" class="close" data-dismiss="modal">&times;</button>
        </div>
        
        <!-- Modal body -->
        <div class="modal-body">
        	<form action="#" method="POST" name="ProjectForm">
            <div class="row">
                <div class="form-group col-lg-12">
                    <label class="col-lg-5 control-lable" for="projName">Project Name</label>
                    <div class="col-lg-7">
                        <input type="text" name="projName" id="projName" class="form-control input-sm" autofocus required/>
                    </div>
                </div>
            </div>
            <div class="row" id="userSsoId">
                <div class="form-group col-lg-12">
                    <label class="col-lg-5 control-lable" for="projDesc">Project Description</label>
                    <div class="col-lg-7">
                           <input type="text" name="projDesc" id="projDesc" class="form-control input-sm" required/>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="form-group col-lg-12">
                    <label class="col-lg-5 control-lable" for="projParent">Parent Project</label>
                    <div class="col-lg-7">
						<select class="form-control" id="projParent">
						<option></option>
					    </select>
                    </div>
                </div>
            </div>
            <input type="hidden" id="projId"/>
            </form>
	   </div>
		
		 <!-- Modal footer -->
        <div class="modal-footer">
                <button id="AddProjectSubmit" type="button" class="btn btn-primary" onCLick="addProject()">Add</button>  
                <button id="UpdateProjectSubmit" type="button" class="btn btn-primary" onCLick="updateProject()">Update</button>  
                <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
            </div>	
