// 作业上传
$( document ).ready(function() {
	
    // upload file
    var MAX_SIZE = '5MB';
    var file = $("#homeworkFilePath");
    var hwFile;
    file.on('change',function(e){
    	var files = this.files;
    	console.log(files)
    	if(files){
    		if(files.length>0 && files[0].size <= Utils.translateSize(MAX_SIZE)){
    			file.trigger('fileselect',[this.files]);
    		}else{
    			previewFile(null);
    			hwFile=null;
    			// info('上传的文件超出'+MAX_SIZE+'大小限制')
    		}
    	}
    });
    file.on('fileselect',function(e,files){
    	hwFile = files[0];
    	previewFile(hwFile);
    });
    var tipmsg='';
    $("#btn-submit").on("click",function(e){
    	// validate
    	var param = {},valids={};
    	param.userId=$("#userId").val();
    	param.classId=$("#classId").val();
    	param.lessionId=$("#lessionId").val();
    	param.homeworkFileName = $("#homeworkFileName").val();
    	if(param.userId && param.userId.indexOf('选择')!==-1){
    		$('.text-danger[role=userId]').addClass('show');
    		valids.userId=true;
    	}else{
    		$('.text-danger[role=userId]').removeClass('show');
    		delete valids.userId;
    	}
    	if(param.classId && param.classId.indexOf('选择')!==-1){
    		$('.text-danger[role=classId]').addClass('show');
    		valids.classId=true;
    	}else{
    		$('.text-danger[role=classId]').removeClass('show')
    		delete valids.classId;
    	}
    	if(param.lessionId && param.lessionId.indexOf('选择')!==-1){
    		$('.text-danger[role=lessionId]').addClass('show');
    		valids.lessionId=true;
    	}else{
    		$('.text-danger[role=lessionId]').removeClass('show');
    		delete valids.lessionId;
    	}
    	if(param.homeworkFileName && param.homeworkFileName.trim()!==''){
    		$('.text-danger[role=homeworkFileName]').removeClass('show');
    		delete valids.homeworkFileName;
    	}else{
    		$('.text-danger[role=homeworkFileName]').addClass('show');
    		valids.homeworkFileName = true;
    	}
    	if(typeof hwFile == 'undefined' || hwFile==null){
    		$('.text-danger[role=homeworkFilePath]').addClass('show');
    		valids.homeworkFilePath = true;
    	}else{
    		$('.text-danger[role=homeworkFilePath]').removeClass('show');
    		delete valids.homeworkFilePath;
    	}
    	
    	if(Utils.EmptyObject(valids)){
    		return;
    	}
    	var formData = new FormData();
    	formData.append("userid",param.userId);
    	formData.append("classid",param.classId);
    	formData.append("lessionid",param.lessionId);
    	formData.append("homeworkfilename",param.homeworkFileName);
    	formData.append("file",hwFile,hwFile.name ||'file');
    	//console.log('formData',formData)
    	$.ajax({
    		url:'/ldhomework/upload',
    		method:'POST',
    		data:formData,
    		cache : false,
    	    contentType : false,
    	    processData : false,
    		success:function(res, status,jqxhr){
    			console.log(res);
    			if(res.responseCode && res.responseCode==200){
    				$('#tipModal').find('.alert').removeClass('alert-danger').addClass('alert-success');
    			}else{
    				$('#tipModal').find('.alert').removeClass('alert-success').addClass('alert-danger');
    			}
    			$('#tipModal').find('.alert-heading').text(res.responseMsg || 'do operation');
    			$('#tipModal').modal('show');
    			setTimeout(function(){
    				$('#tipModal').modal('hide');
    			},2000);
    		},
    		error:function(err,status){
    			console.log(err);
    		}
    	});
    	
    });
    function previewFile(file){
    	if(file){
    		$("#previewFile .fa").removeClass('hidden-xs-up').text(file.name||'');
    	} else {
    		$("#previewFile .fa").addClass('hidden-xs-up').text('')
    	}
    }
    
    
    
});