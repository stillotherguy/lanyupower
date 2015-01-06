function remote(url, callback){
	$.ajax({
		url:url,
		dataType:'json',
		type:'get',
		contentType:'application/json; charset=UTF-8',
		success:function(data){
			if(callback){
				callback(data);
			}
		}
	});
}

function commonCallback(data){
	/*<![CDATA[*/
	if(data&&data.length != 0){
		/*]]>*/
		$.each(data, function(index,value){
			$('#admintable tbody').append('<tr id="' + value.id + '"><td>' + value.no + '</td>');
			$('#admintable tbody tr').append('<td>' + value.name + '</td>')
			.append('<td>' + value.state + '</td>')
			.append('<td>' + value.address + '</td>')
			.append('<td>' + value.chargetype + '</td>')
			.append('<td>' + value.lastyearcharge + '</td>')
			.append('<td>' + value.thisyearcharge + '</td>')
			.append('<td>' + value.usertype + '</td>')
			.append('<td>' + value.totaluser + '</td>')
			.append('<td>' + value.revnum + '</td>')
			.append('<td>' + value.identity + '</td>')
			.append('<td>' + value.organization + '</td>')
			.append('<td>' + value.contact + '</td>')
			.append('<td>' + value.bank + '</td>')
			.append('<td>' + value.lastmonthkj + '</td>')
			.append('<td>' + value.thismonthkj + '</td>')
			.append('<td>' + value.phone + '</td>')
			.append('<td>' + '<a href="/admin/repair/' + value.id +'" data-entity=' + value.id + '>报修</a>' + '</td></tr>');
		});
		//$('#result td:eq(0)').text(data.no);
		/*<![CDATA[*/
	}else{
		$('#admintable tbody').append('<tr><td>无结果</td></tr>');
	}
	/*]]>*/
}

$(function(){
	var username = $('#username').text().trim();
	
	$('#card').blur(function(){
		$('#admintable tbody').empty();
		var no = $(this).val();
		if(!no){
			return;
		}
		remote('/admin/card/' + no, commonCallback);
	})
	$('#address').blur(function(){
		$('#admintable tbody').empty();
		var no = $(this).val();
		if(!no){
			return;
		}
		remote('/admin/address/' + no, commonCallback);
	})
	$('#phone').blur(function(){
		$('#admintable tbody').empty();
		var no = $(this).val();
		if(!no){
			return;
		}
		remote('/admin/phone/' + no, commonCallback);
	})
	$('#name').blur(function(){
		$('#admintable tbody').empty();
		var no = $(this).val();
		if(!no){
			return;
		}
		remote('/admin/name/' + no, commonCallback);
	})
	$('#mobile').blur(function(){
		$('#admintable tbody').empty();
		var no = $(this).val();
		if(!no){
			return;
		}
		remote('/admin/mobile/' + no, commonCallback);
	})
	
	//操作点击事件
	$('#admintable tbody tr td:last a').on('click', function(event){
		var id = $(event.target).attr('data-entity');
		if(id){
			alert($('#clientid').val(id));
		}
	});
	//客户页面
	
});