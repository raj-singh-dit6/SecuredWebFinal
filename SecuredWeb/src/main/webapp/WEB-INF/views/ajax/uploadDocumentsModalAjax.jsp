        <!-- Modal Header -->
        <div class="modal-header">
          <h4 class="modal-title" id="UploadDocumentsModalHeading"></h4>
          <!-- <button type="button" class="close" data-dismiss="modal">&times;</button> -->
        </div>
        
        <!-- Modal body -->
        <div class="modal-body">
        	<form action="#" method="POST" name="UserForm" id="UploadDocumentForm" enctype="multipart/form-data">
                <div class="document0 form-group col-md-12">
                    <div class="input-group col-md-12">
                        <input id="multipleFiles" type="file" multiple name="files" class="form-control input-sm" accept="application/pdf,image/*"/>
                    </div>
                    <!-- <div class="input-group col-md-3">
                        <input type="text" name="description" class="description form-control input-sm" placeholder="description"/>
                    </div> -->
                    <div class="input-group col-md-12">
                        <textarea id="description" name="description" rows="4" cols="45"></textarea>
                    </div>
                    
                </div>
           </form>
        	
        	<div class="progress">
		      <div id="progressBar" class="progress-bar progress-bar-success" role="progressbar"
		        aria-valuenow="0" aria-valuemin="0" aria-valuemax="100" style="width: 0%">0%</div>
		    </div>

		    <!-- Alert -->
		    <div id="alertMsg" style="color: red;font-size: 18px;"></div>
        
        </div>
        
        <!-- Modal footer -->
        <div class="modal-footer" id="addUploadFooter">
        </div>	