// 作业互评
$( document ).ready(function() {
	
	// 搜索某班级作业
	var classId;
	var lists=[];
	$("#btn-search").on("click",function(e){
		classId = $("#classId").val();
		
		fetchData(classId);
	});
	function fetchData(classId){
		$.ajax({
			url:"/ldhomework/list"+ (classId!==""?("/"+classId):""),
			method:"GET",
			success:function(res, status,jqxhr){
    			console.log(res);
    			if(res.responseCode && res.responseCode==200){
    				lists = res.body || [];
    				renderTable(lists);
    			}else{
    				$('#tipModal').find('.alert').removeClass('alert-success').addClass('alert-danger');
    			
	    			$('#tipModal').find('.alert-heading').text(res.responseMsg || 'do operation');
	    			$('#tipModal').modal('show');
	    			setTimeout(function(){
	    				$('#tipModal').modal('hide');
	    			},2000);
    			}
    		},
    		error:function(err,status){
    			console.log(err);
    		}
		})
	}
	function renderTable(tblist){
		var tpl = '<tr><td>{{ userid }}</td>'
		      + '<td><a href="{{ homeworkfilepath }}">{{ homeworkfilename }}</a></td>'
		      + '<td>{{ createdate }}</td>'
		      + '<td>{{ starcount }}</td>'
		      + '<td>{{ negativecount }}</td>'
		      + '<td>{{ bestflag }}</td>'
		      + '<td>{{ correctflag }}</td>'
		      + '<td key="{{ id }}"><a href="javascript:void(0);" class="btn-viewfeedback text-info" data-id="{{ id }}" data-user="{{ userid }}"><i class="fa fa-toggle-right"></i> 查看评论</a> | <a href="javascript:void(0);" class="btn-feedback text-info" data-id="{{ id }}" data-user="{{ userid }}"><i class="fa fa-star"></i> 评分</a></td></tr>';
		var htmls = [];
		for(var i=0;i<tblist.length;i++){
			htmls.push(Utils.parseTemplate(tpl,tblist[i]));
		}
		$('#hwTable').find('tbody').html(htmls.join(''));
		
	}
	function showMessage(message,flag){
		if(!!flag){
			$('#tipModal').find('.alert').removeClass('alert-danger').addClass('alert-success');
		}else{
			$('#tipModal').find('.alert').removeClass('alert-success').addClass('alert-danger');
		}
		$('#tipModal').find('.alert-heading').text(message || 'do operation');
		$('#tipModal').modal('show');
		setTimeout(function(){
			$('#tipModal').modal('hide');
		},2000);
	}
	
	//
	var myModal = $('#myModal');
	var myModalList = $('#myModalList');
	
	// open feedback modal
    $("#hwTable").on("click",".btn-feedback",function(e){
    	var btn = $(e.currentTarget);
    	var id = btn.data('id');
    	var userid=btn.data('user');
    	myModal.find('.form-control').val('');
    	myModal.find('#homeworkid').val(id);
    	myModal.find('#userid').val(userid);
    	myModal.modal('show');
    });
    $("#hwTable").on("click",".btn-viewfeedback",function(e){
    	var btn = $(e.currentTarget);
    	var id = btn.data('id');
    	myModalList.find('.form-control').val('');
    	myModalList.find('.id').val(id);
    	
    	var ftpl = '<li class="media">'
					    +'<div class="d-flex mr-3 rounded-circle"><i class="fa fa-user"></i> </div>'
					    +'<div class="media-body">'
					    +'  <p class="feedback-contents">{{ mark }}<p>'
					    +'  <p class="text-right">'
					    +'  	<small class="feedback-user ml-2">{{ userid }}</small>'
					    +'  </p>'
					    +'</div>'
					  +'</li>';
    	$.ajax({
    		url:'/ldhomeworkfb/list'+(id?'/'+id:''),
    		method:'GET',
    		success:function(res, status,jqxhr){
    			console.log(res);
    			if(res.responseCode && res.responseCode==200){
    				lists = res.body || [];
    				renderFeedback(ftpl,lists);
    			}else{
    				showMessage(res.responseMsg);
    			}
    		},
    		error:function(err,status){
    			console.log(err);
    		}
    	});
    	
    	myModalList.modal('show');
    });
    function renderFeedback(tpl,objs){
    	var htmls = [];
		for(var i=0;i<objs.length;i++){
			htmls.push(Utils.parseTemplate(tpl,objs[i]));
		}
		if(objs.length>0){
			myModalList.find('ul').html(htmls.join(''));
		}else{
			myModalList.find('ul').html('<li><p class="text-center">暂无数据</p></li>')
		}
    }
    
    myModal.on('click','.btn-submit',function(e){
    	var d={},valid={};
    	d.homeworkid = myModal.find('#homeworkid').val();
    	d.id=d.homeworkid;
    	d.userid = myModal.find('#userid').val();
    	d.myid = myModal.find('#myid').val();
    	d.starcount = myModal.find('#radio1:checked').val() || 0;
    	d.negativecount = myModal.find('#radio2:checked').val() || 0;
    	d.acount = myModal.find('#radio3:checked').val() || 0;
    	d.levelflag = myModal.find('.levelflag:checked').val();
    	d.mark = myModal.find('#mark').val();
    	
    	if(!d.myid){
    		showMessage('你是谁？');
    		valid.myid=true;
    	}else{
    		delete valid.myid;
    	}
    	if(!myModal.find('.starcount:checked').val()){
    		showMessage('点赞还没选捏');
    		valid.starcount=true;
    	}else{
    		delete valid.starcount;
    	}
    	if(!d.levelflag){
    		showMessage('给个评价亲');
    		valid.levelflag=true;
    	}else{
    		delete valid.levelflag;
    	}
    	if(Utils.EmptyObject(valid)){
    		return;
    	}
    	
    	$.ajax({
    		url:'/ldhomeworkfb/feedback',
    		method:'POST',
    		data:d,
    		//dataType:'json',
    		//contentType:'application/json; charset=UTF-8',
    		success:function(res, status,jqxhr){
    			showMessage(res.responseMsg,true);
    			myModal.modal('hide');
    			fetchData(classId);
    		},
    		error:function(err,status){
    			showMessage(err.responseMsg,false)
    		}
    	})
    })
    
    
    
    
    function EmptyObject(obj){
    	for(o in obj){
    		if(o){
    			return true;
    		}
    	}
    	return false;
    }
    
    
});