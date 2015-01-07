$(function(){
	$('#form').validate({
		rules: {
			"name": {
				required:true/*,
				remote: {
	                type: "post",
	                url: "/insurance/exist",
	                data: {
	                    name: function() {
	                        return $("#username").val();
	                    }
	                },
	                dataType: "json"
	            }*/
			},
			"contact": {
				required:true
			},
			"phone": {
				required:true
			}
		},
		messages: {
			"name": {
				required:"请输入报修单"/*,
				remote:"名称已存在"*/
			},
			"contact": {
				required:"请输入紧急联系人"
			},
			"phone": {
				required:"请输入联系人电话"
			}
		}
	});
});