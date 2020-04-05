$(function(){
	var productListUrl = '/shopadmin/getproductlist';
	var modifyStatusUrl = '/shopadmin/modifyproduct';
	getlist();
	
	function getlist(){
		$.getJSON(productListUrl, function(data){
			if (data.success) {
				var tempHtml = '';
				// traverse every product， combine to a new line
				// product name, priority, on/off, edit, view
				data.productList.map(function(item,index){
					var textStatus = "OFF";
					var productStatus = 0;
					if (item.enableStatus == 0) {
						textStatus = "ON";
						productStatus = 1;
					} else {
						productStatus = 0;
					}
					tempHtml += '<div class="row row-product"><div class="col-33">'
						+ item.name + '</div><div class = "col-20">'
						+ item.priority
						+ '</div><div class = "col-40">'
						// for edit
						+ '<a href="#" class="edit" data-id="' + item.id + '"data-status="' + item.enableStatus + '">edit</a>'
						//for remove
						+ '<a href="#" class="status" data-id="' + item.id + '"data-status="' + productStatus +'">' + textStatus + '</a>'
						// for view
						+ '<a href="#" class="view" data-id="' + item.id + '"data-status="' + item.enableStatus +'">view</a>'
						+'</div>'
						+'</div>';
				});
				$('.product-wrap').html(tempHtml);

			
			}
	});
	}
	
	$('.product-wrap').on('click', 'a', 
			function(e){
				var target = $(e.currentTarget);
				if (target.hasClass('edit')) {
					window.location.href = '/shopadmin/productoperation?productId=' + e.currentTarget.dataset.id;
				} else if (target.hasClass('status')) {
					changeProductStatus(e.currentTarget.dataset.status, e.currentTarget.dataset.id);
				} else if (target.hasClass('view')){
					window.location.href = '/shopadmin/productoperation?productId=' + e.currentTarget.dataset.id;
				}
	});
	
	function changeProductStatus(status, id) {
		var product = {};
		product.id = id;
		product.enableStatus = status
		
		$.confirm('Are you sure?', function(){
			$.ajax({
				url: modifyStatusUrl,
				type: 'POST',
				data: {
					productStr: JSON.stringify(product),
					statusChange: true
				},
				dataType: 'json',
				success: function(data){
					if (data.success) {
						$.toast('success!');
						getlist();
					} else {
						$.toast('failed.');
					}
				}				
			});
		});
	}

})