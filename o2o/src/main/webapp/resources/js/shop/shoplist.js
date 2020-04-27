/**
 * 
 */

$(function(){
	getshoplist();
	function getshoplist(e) {
	$.ajax({
		url: "/shopadmin/getshoplist",
		type: "GET",
		dataType: "json",
		success:function(data){
			if (data.success) {
				handleUser(data.user);
				handleList(data.shopList);
			}
		}
	});
	}
	function handleUser(data) {
		$('#user-name').text(data.name);
	}
	
	function handleList(data) {
		var html = '';
		data.map(function(item,index){
			html += '<div class="row row-shop"><div class="col-40">'
				+ item.name + '</div><div class = "col-40">'
				+ shopStatus(item.enableStatus)
				+ '</div><div class = "col-20">'
				+ goShop(item.enableStatus, item.id) + '</div></div>';
		});
		$('.shop-wrap').html(html);
	}
	function shopStatus(status){
		if (status == 0) {
			return "checking";
		} else if (status == -1){
			return "illegal";
		} else if (status == 1){
			return "pass";
		}
	}
	function goShop(enableStatus, id) {
		if (enableStatus == 1) {
			return '<a href= "/shopadmin/shopmanagement?shopId=' + id + '"> enter </a>';
		} else {
			return "";
		}
	}
	
	$('#log-out').click(function(){	
		var logoutUrl = '/local/logout';
		$.ajax({
			url: logoutUrl,
			type: 'POST',
			contentType: false,
			processData: false,
			cache: false,
			dataType: 'json',
			success:function(data){
				if (data.success) {
					$.toast('bye bye');
					var userType = $('#log-out').attr('userType');
					window.location.href = '/local/login?userType=' + userType;
					return false;
				}
			},
			error:function(data, error){
				alert(error);
			}
		});
	});
})