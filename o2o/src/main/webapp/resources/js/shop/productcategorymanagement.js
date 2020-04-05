$(function(){
	var getListUrl = "/shopadmin/getproductcategorylist";
	var addProductCategoriesUrl = "/shopadmin/addproductcategories";
	var deleteProductCategoryUrl = "/shopadmin/deleteproductcategory";
	getproductCategoryList();
	function getproductCategoryList(e) {
	$.ajax({
		url : getListUrl,
		type: "GET",
		dataType: "json",
		success:function(data){
			if (data.success) {
				handleList(data.data);
			}
		}
	});
	}
	function handleList(dataList) {
		var html = '';
		dataList.map(function(item,index){
			html += '<div class="row row-product-category exist ">'
				+ '<div class = "col-33 product-category-name">' + item.name + '</div>'
				+ '<div class = "col-33">' + item.priority + '</div>'
				+ '<div class = "col-33"><a href= "#" class="button delete" data-id="' + item.id + '">delete</a></div>'
				+ '</div>';
		});
		$('.product-category-wrap').html(html);
	}

	$('#addnew').click(function(){
		var newhtml = '';
		newhtml += '<div class="row row-product-category add ">'
			+ '<div class = "col-33"><input class="category-input category" type="text"  placeholder="name"></div>'
			+ '<div class = "col-33"><input class="category-input priority" type="number"  placeholder="priority"></div>'
			+ '<div class = "col-33"><a href= "#" class="button delete">delete</a></div>'
			+ '</div>';
		$('.product-category-wrap').append(newhtml);
	});
	$('#submit').click(function(){
		var tempArr =  $('.add');
		var productCategoryList = [];
		tempArr.map(function(index, item) {
			var tempObj = {};
			tempObj.name = $(item).find('.category').val();
			tempObj.priority = $(item).find('.priority').val();
			if (tempObj.name && tempObj.priority) {
				productCategoryList.push(tempObj);
			}
		});
		$.ajax({
			url: addProductCategoriesUrl,
			type: "POST",
			data: JSON.stringify(productCategoryList),
			contentType:'application/json',
			success:function(data){
				if (data.success) {
					$.toast('well done');
					getproductCategoryList();
				} else {
					$.toast('failed!!!!!!');
				}
			}
		});
	});
	$('.product-category-wrap').on('click', '.row-product-category.add .delete',
			function(e){
		console.log($(this).parent().parent());
		$(this).parent().parent().remove();
	});
	$('.product-category-wrap').on('click', '.row-product-category.exist .delete',
			function(e){
		var target = e.currentTarget;
		$.confirm('are you sure?', function(){
			$.ajax({
				url: deleteProductCategoryUrl,
				type: "POST",
				data: {
					productCategoryId : target.dataset.id
				},
				dataType:'json',
				success:function(data){
					if (data.success) {
						$.toast('well done');
						getproductCategoryList();
					} else {
						$.toast('failed!!!!!!');
					}
				}
			});
		})

	});
})