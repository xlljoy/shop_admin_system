 /**
 * 
 */

$(function(){
	getshoplist();
	function getshoplist(e) {
	$.ajax({
		url: "/shopadmin/getproductlist",
		type: "GET",
		dataType: "json",
		success:function(data){
			if (data.success) {
				handleUser(data.user);
				handleList(data.productList);
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
				+ productStatus(item.enableStatus)
				+ '</div><div class = "col-20">'
				+ goProduct(item.enableStatus, item.id) + '</div></div>';
		});
		$('.product-wrap').html(html);
	}
	function productStatus(status){
		if (status == 0) {
			return "checking";
		} else if (status == -1){
			return "illegal";
		} else if (status == 1){
			return "pass";
		}
	}
	function goProduct(enableStatus, id) {
		if (enableStatus == 1) {
			return '<a href= "/shopadmin/productoperation?productId=' + id + '"> enter </a>';
		} else {
			return "";
		}
	}
})