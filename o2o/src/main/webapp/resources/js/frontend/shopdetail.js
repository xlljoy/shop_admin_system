$(function(){
	var loading = false;
	var maxItems = 999;
	var shopId = getQueryString('shopId');
	var pageSize = 4;
	var pageNum = 1;
	var shopInfoUrl = '/frontend/getshopdetailinfo' + '?shopId=' + shopId;
	var listProductsUrl = '/frontend/listproducts';
	var productCategoryId = '';
	var name = '';

	$.getJSON(shopInfoUrl, function(data){
		if (data.success) {
			var productCategoryList = data.productCategoryList;
			
			var searchHtml = '';
			searchHtml += '<a href="#" class="button" data-category-id=""> all categories </a>';
			productCategoryList.map(function(item, index){
				searchHtml += '<a href="#" class="button" data-id=' + item.id+ '>'
							+ item.name + '</a>';
			}); 
			$('#productlist-search-div').html(searchHtml);
			
			// combine shop info to show on shop card
			var shopDesc = data.shopDesc;
			var shopAddr = data.shopAddr;
			var shopImg = data.shopImg;
			var phone = data.phone;
			var shopName = data.name;
			$('#shop-img').attr('src',shopImg);
			$('#shop-desc').html(shopDesc);
			$('#shop-addr').html(shopAddr);
			$('#shop-phone').html(phone);
			$('#shop-name').html(shopName);
		}
	});
	
	listProducts(pageSize, pageNum);
	// @param pageSize
	// @param pageIndex
	// @return
	function listProducts(pageSize, pageIndex) {
		var url = '';
		url += listProductsUrl;
		url += '?';
		url += 'pageSize=' + pageSize + '&';
		url += 'pageIndex=' + pageIndex + '&';
		url += 'name=' + name + '&';
		url += 'productCategoryId=' + productCategoryId + '&';
		url += 'shopId=' + shopId;
		
		loading = true;
		$.getJSON(url, function(data){
			if (data.success) {
				var productList = data.productList;
				maxItems = data.count;
				var productListHtml = '';
				productList.map(function(item, index){
					var date = new Date(item.updateTime);
					var productUpdateTime = date.Format("yyyy-MM-dd");
					
					productListHtml += '<div class="card" data-product-id="'
					+ item.id + '">'
	                + '<div class="card-header">'+ item.name+'</div>'
	                + '<div class="card-content">'
	                  + '<div class="list-block media-list">'
	                       + '<ul>'
	                          + '<li class="item-content">'
	                               + '<div class="item-media">'
	                                  + '<img src="' + item.img + '"width="44">'
	                               + '</div>'
	                              + '<div class="item-inner">'
	                                  + '<div class="item-subtitle">' + item.productDesc + '</div>'
	                              + '</div>'
	                          + '</li>'
	                      + '</ul>'
	                  + '</div>'
	              + '</div>'
	              + '<div class="card-footer">'
	               + '<p class="color-gray">' + productUpdateTime + ' updated</p>'
	                  + '<span>check</span>'
	              + '</div>'
	              + '</div>';
				}); 
				$('.list-div').append(productListHtml);
				var total = $('.list-div .card').length;
				if (total >= maxItems) {
					//$('.infinite-scroll-preloader').hide();
					$.detachInfiniteScroll($('.infinite-scroll'));
					$('.infinite-scroll-preloader').remove();
				} 
//				else {
//					$('.infinite-scroll-preloader').show();
//				}
				pageNum += 1;
				loading = false;
				$.refreshScroller();
			}
		});
	}
	
	$('.product-list').on('click', '.card', function(e) {
		var productId = e.currentTarget.dataset.productId;
		window.location.href = '/frontend/productdetail?productId=' + productId;
	});
	
	$('#productlist-search-div').on('click', '.button', function(e){
		productCategoryId = e.target.dataset.id;
		if (productCategoryId) {
			if ($(e.target).hasClass('button-fill')) {
				$(e.target).removeClass('button-fill');
				productCategoryId = '';
			} else {
				$(e.target).addClass('button-fill').siblings().removeClass('button-fill');
			}
			$('.list-div').empty();
			pageNum = 1;
			listProducts(pageSize, pageNum);
		}
	});
	$(document).on('infinite', '.infinite-scroll-bottom', function() {
		if (loading)
			return;
		listProducts(pageSize, pageNum);
	});
	$('#me').click(function(){
		$.openPanel('#panel-right-demo');
	});
	$('#search').on('change',function(e){
		name = e.target.value;
		$('.list-div').empty();
		pageNum = 1;
		listProducts(pageSize, pageNum);
	});

	$.init();
})