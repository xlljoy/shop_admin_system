$(function(){
	var loading = false;
	var maxItems = 999;
	var parentId = getQueryString('parentId');
	var pageSize = 4;
	var pageNum = 1;
	var shopPageInfoUrl = '/frontend/listshopspageinfo';
	var listShopsUrl = '/frontend/listshops';
	var zoneId = '';
	var shopCategoryId = '';
	var name = '';
	var selectParent = false;
	if (parentId) {
		selectParent = true;
	}
	$.getJSON(shopPageInfoUrl, function(data){
		if (data.success) {
			var shopCategoryList = data.shopCategoryList;
			var zoneList = data.zoneList;
			var zoneHtml = '<option value="">all zones</option>';
			zoneList.map(function(item, index){
				zoneHtml += '<option data-value="' + item.zoneId + '">'
				+ item.name + '</option>';
			}); 
			$('#area-search').html(zoneHtml);
			
			var searchHtml = '';
			searchHtml += '<a href="#" class="button" data-category-id=""> all categories </a>';
			shopCategoryList.map(function(item, index){
				searchHtml += '<a href="#" class="button" data-id=' + item.id+ '>'
							+ item.name + '</a>';
			}); 
			$('#shoplist-search-div').html(searchHtml);
		}
	});
	
	listShops(pageSize, pageNum);
	// @param pageSize
	// @param pageIndex
	// @return
	function listShops(pageSize, pageIndex) {
		var url = '';
		url += listShopsUrl;
		url += '?';
		if (parentId) {
			url += 'parentId=' + parentId + '&';
		}
		url += 'pageSize=' + pageSize + '&';
		url += 'pageIndex=' + pageIndex + '&';
		url += 'zoneId=' + zoneId + '&';
		url += 'name=' + name + '&';
		url += 'shopCategoryId=' + shopCategoryId ;
		
		loading = true;
		$.getJSON(url, function(data){
			if (data.success) {
				var shopList = data.shopList;
				maxItems = data.count;
				var shopListHtml = '';
				shopList.map(function(item, index){
					var date = new Date(item.updateTime);
					var shopUpdateTime = date.Format("yyyy-MM-dd");
					
					shopListHtml += '<div class="card" data-shop-id="'
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
	                                  + '<div class="item-subtitle">' + item.shopDesc + '</div>'
	                              + '</div>'
	                          + '</li>'
	                      + '</ul>'
	                  + '</div>'
	              + '</div>'
	              + '<div class="card-footer">'
	               + '<p class="color-gray">' + shopUpdateTime + ' updated</p>'
	                  + '<span>check</span>'
	              + '</div>'
	              + '</div>';
				}); 
				$('.list-div').append(shopListHtml);
				var total = $('.list-div .card').length;
				if (total >= maxItems) {
					$('.infinite-scroll-preloader').hide();
				} else {
					$('.infinite-scroll-preloader').show();
				}
				pageNum += 1;
				loading = false;
				$.refreshScroller();
			}
		});
	}
	
	$('.shop-list').on('click', '.card', function(e) {
		var shopId = e.currentTarget.dataset.shopId;
		window.location.href = '/frontend/shopdetail?shopId=' + shopId;
	});
	
	$('#shoplist-search-div').on('click', '.button', function(e){
		if (parentId && selectParent) {
			shopCategoryId = e.target.dataset.id;
			if ($(e.target).hasClass('button-fill')) {
				$(e.target).removeClass('button-fill');
				shopCategoryId = '';
			} else {
				$(e.target).addClass('button-fill').siblings().removeClass('button-fill');
			}
			$('.list-div').empty();
			pageNum = 1;
			listShops(pageSize, pageNum);
		} else {
			parentId = e.target.dataset.id;
			if ($(e.target).hasClass('button-fill')) {
				$(e.target).removeClass('button-fill');
				parentId = '';
			} else {
				$(e.target).addClass('button-fill').siblings().removeClass('button-fill');
			}
			$('.list-div').empty();
			pageNum = 1;
			listShops(pageSize, pageNum);
		}
	});
	$(document).on('infinite', '.infinite-scroll-bottom', function() {
		if (loading)
			return;
		listShops(pageSize, pageNum);
	});
	$('#me').click(function(){
		$.openPanel('#panel-right-demo');
	});
	$('#search').on('change',function(e){
		name = e.target.value;
		$('.list-div').empty();
		pageNum = 1;
		listShops(pageSize, pageNum);
	});
	$('#area-search').on('change', function(){
		zoneId = $('#area-search').val();
		$('.list-div').empty();
		pageNum = 1;
		listShops(pageSize, pageNum);
	});
	$.init();
})