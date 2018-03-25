        <!-- Modal Header -->
        <div class="modal-header">
          <h4 class="modal-title" id="ProjectModalHeading"></h4>
          <button type="button" class="close" data-dismiss="modal">&times;</button>
        </div>
        
        <!-- Modal body -->
        <div class="modal-body">
        	<form action="#" method="POST" name="ProjectForm" enctype="multipart/form-data">
            <div class="row">
                <div class="form-group col-md-12">
                    <label class="col-md-3 control-lable" for="projName">Project Name</label>
                    <div class="col-md-7">
                        <input type="text" name="projName" id="projName" class="form-control input-sm" autofocus/>
                    </div>
                </div>
            </div>
            <div class="row" id="userSsoId">
                <div class="form-group col-md-12">
                    <label class="col-md-3 control-lable" for="projDesc">Project Description</label>
                    <div class="col-md-7">
                           <input type="text" name="projDesc" id="projDesc" class="form-control input-sm" />
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="form-group col-md-12">
                    <label class="col-md-3 control-lable" for="projParent">Parent Project</label>
                    <div class="col-md-7">
						<select class="form-control" id="projParent">
						<option></option>
					    </select>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="form-group col-md-12">
                    <label class="col-md-3 control-lable" for="projFile">Select File</label>
                    <div class="col-md-8">
						<input class="form-control" type="file" name="file" id="projFile">
                    </div>
                </div>
            </div>
            <input type="hidden" id="projId"/>
            </form>
            
            <br/>
            
            <div class="progress">
		      <div id="progressBar" class="progress-bar progress-bar-success" role="progressbar"
		        aria-valuenow="0" aria-valuemin="0" aria-valuemax="100" style="width: 0%">0%</div>
		    </div>

		    <!-- Alert -->
		    <div id="alertMsg" style="color: red;font-size: 18px;"></div>
	   </div>
		
		 <!-- Modal footer -->
        <div class="modal-footer">
                <button id="AddProjectSubmit" type="button" class="btn btn-primary" onCLick="addProject()">Add</button>  
                <button id="UpdateProjectSubmit" type="button" class="btn btn-primary" onCLick="updateProject()">Update</button>  
                <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
            </div>	
