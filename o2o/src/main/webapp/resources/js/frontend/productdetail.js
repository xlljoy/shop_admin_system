$(function(){
	var loading = false;
	var maxItems = 999;
	var productId = getQueryString('productId');
	var pageSize = 4;
	var pageNum = 1;
	var productInfoUrl = '/frontend/getproduct' + '?productId=' + productId;
	var productCategoryId = '';
	var name = '';

	$.getJSON(productInfoUrl, function(data){
		if (data.success) {
			var productImgList = data.productImgList;
			var product = data.product;
			var imgHtml = '';

			productImgList.map(function(item, index){
				imgHtml += '<div>'
					 + '<img src="'+ item.imgAddr +'" width="100%"/>'
					 + '</div>';
			}); 
			$('#imgList').html(imgHtml);
			
			// combine shop info to show on shop card
			$('#product-img').attr('src',product.imgAddr);
			$('#product-update-time').text(new Date(product.updateTime).Format("yyyy-MM-dd"));
			$('#product-name').text(product.name);
			$('#product-desc').text(product.productDesc);
			$('#normal-price').text(product.normalPrice);
			$('#promotion-price').text(product.promotionPrice);
			
			if (product.normalPrice != undefined
					&& product.promotionPrice != undefined) {
				$('#price').show();
				$('#normal-price').html(
						'<del>' + '$' + product.normalPrice + '</del>');
				$('#promotion-price').text('$' + product.promotionPrice);
			} else if (product.normalPrice != undefined
					&& product.promotionPrice == undefined) {
				$('#price').show();
				$('#promotion-price').text('$' + product.normalPrice);
			} else if (product.normalPrice == undefined
					&& product.promotionPrice != undefined) {
				$('#promotion-price').text('$' + product.promotionPrice);
			}
		}
	});
})