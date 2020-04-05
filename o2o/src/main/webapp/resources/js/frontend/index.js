$(function(){
	var mainPageUrl = '/frontend/getmainpageinfo';
	$.getJSON(mainPageUrl, function(data){
		if (data.success) {
			var dailyNewList = data.dailyNewList;
			var shopCategoryList = data.shopCategoryList;
			var swiperHtml = '';
			dailyNewList.map(function(item, index){
				swiperHtml += '<div class="swiper-slide img-wrap">' 
					+ '<a href = "' + item.link
					+ '"external>'
					+ '<img class="banner-img" src="' + item.img +'" alt="'+ item.name+ '"></a>'
					+ '</div>';
			});
			$('.swiper-wrapper').html(swiperHtml);
			// set time of rotation of each image
			$('.swiper-container').swiper({
				autoplay: 3000,
				autoplayDisableOnInteraction: true
			});
			
			// combine shop category for shown
			var categoryHtml ='';
			shopCategoryList.map(function(item, index){
				categoryHtml += '<div class="col-50 shop-classify" data-category=' + item.id + '>'
							+ '<div class="word">' 
							+ '<p class="shop-title">' + item.name + '</p>'
							+ '<p class="shop-desc">' + item.categoryDesc + '</p></div>'
							+ '<div class="shop-classify-img-wrap">'
							+ '<img class="shop-img" src="' + item.img + '"></div></div>';
			});
			$('.row').html(categoryHtml);
		}
	});
	
	$('#me').click(function(){
		$.openPanel('#panel-right-demo');
	});
	
	$('.row').on('click', '.shop-classify', function(e){
		var categoryId = e.currentTarget.dataset.category;
		var newUrl = '/frontend/shoplist?parentId=' + categoryId;
		window.location.href = newUrl;
	});
})