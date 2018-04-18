/*!  To check unique parameter    */
function isUnique(pageType,obj,param){
	var value  =  $(obj).val();
	var reqUrl = pageType+"/unique/"+param+"/"+value ;
	if(value!=""){
	$.ajax({
	    	async: false,
	    	type: "GET",
	        url: reqUrl,
	        beforeSend: function(xhr) {
		           xhr.setRequestHeader(header, token);
		       },
	        success: function(status) {
	        	if(status.status==200)
	        	{
	        		if ($(obj).next(".validation").length == 0) // only add if not added
	                {
	        			$(obj).after("<div class='validation col-lg-12' style='color:red;margin-bottom: 5px;'>"+status.message+"</div>");
	        			$(obj).focus();
	                }
	        	}else{
	        		$(obj).siblings(".validation").remove(); // remove it
	        	}
	        }
	    });
	}else{
		$(obj).siblings(".validation").remove();
	}
}
