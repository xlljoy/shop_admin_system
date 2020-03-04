$(function(){
	getproductCategoryList();
	function getproductCategoryList(e) {
	$.ajax({
		url: "/shopadmin/getproductcategorylist",
		type: "GET",
		dataType: "json",
		success:function(data){
			if (data.success) {
				handleList(data.data);
			}
		}
	});
	}
	function handleList(data) {
		var html = '';
		data.map(function(item,index){
			html += '<div class="row row-product-category"><div class="col-33">'
				+ item.name + '</div><div class = "col-33">'
				+ item.priority
				+ '</div><div class = "col-33">'
				+ goDelete(item.id) + '</div></div>';
		});
		$('.product-category-wrap').html(html);
	}
	function goDelete(id) {
		return '<a href= "#" class="button delete" data-id"' + id + '"> delete </a>';
	}
})