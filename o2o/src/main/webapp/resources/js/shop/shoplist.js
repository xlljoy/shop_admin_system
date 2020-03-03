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
			return '<a href=" + "/shopadmin/shopmanage?shopId=' + id + '"> enter </a>';
		} else {
			return "";
		}
	}
})