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
	if(data && data!=null && data.length != 0){
		/*]]>*/
		console.log(data);
		$.each(data, function(index,value){
			$('#admintable tbody').html(
			'<tr id="' + value.id + '">' +
			'<td>' + value.no + '</td>' + 
			'<td>' + value.name + '</td>'+
			'<td>' + value.state + '</td>' +
			'<td>' + value.address + '</td>' +
			'<td>' + value.chargetype + '</td>' +
			'<td>' + value.lastyearcharge + '</td>' +
			'<td>' + value.thisyearcharge + '</td>' +
			'<td>' + value.usertype + '</td>' +
			'<td>' + value.totaluser + '</td>' +
			'<td>' + value.revnum + '</td>' +
			'<td>' + value.identity + '</td>' +
			'<td>' + value.organization + '</td>' +
			'<td>' + value.contact + '</td>' +
			'<td>' + value.bank + '</td>' +
			'<td>' + value.lastmonthkj + '</td>' +
			'<td>' + value.thismonthkj + '</td>' +
			'<td>' + value.phone + '</td>'  +
			'<td>' + '<a href="/admin/repair/' + value.id +'" data-entity=' + value.id + '>报修</a>' + '</td></tr>');
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