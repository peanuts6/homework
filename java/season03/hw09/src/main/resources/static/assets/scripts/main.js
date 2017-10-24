$( document ).ready(function() {
	
	// 搜索某班级作业
	var classId;
	$("#btn-search").on("click",function(e){
		classId = $("#classId").val();
		$.ajax({
			url:"/ldhomework/list"+"?classId="+classId
		})
	});
	
	// open feedback modal
    $("#hwTable").on("click",".btn-feedback",function(e){
    	$('#myModal').modal('show');
    });
    $("#hwTable").on("click",".btn-viewfeedback",function(e){
    	$('#myModalList').modal('show');
    });
    $('#myModal').on('show.bs.modal', function (event) {
    	  var button = $(event.relatedTarget) 
    	  var recipient = button.data('whatever') 
    	  // ajax
    	  var modal = $(this)
    	  modal.find('.modal-title').text('作业互评' + recipient)
    });
    
    // upload file
    $("#btn-submit").on("click",function(e){
    	// validate
    	
//    	FormData fd = new FormData();
//    	fd.append("userId",'');
    });
    
    
});