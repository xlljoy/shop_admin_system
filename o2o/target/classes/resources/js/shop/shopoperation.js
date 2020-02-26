/**
 * 
 */
$(function(){
	var initUrl = '/shopadmin/getshopinitinfo';
	var registerShopUrl = '/shopadmin/registershop';
	//alert(initUrl);
	getShopInitInfo();
	function getShopInitInfo(){
		$.getJSON(initUrl,function(data){
			if(data.success){
				var tempZoneHtml = '';
				var tempShopCateHtml = '';
				data.zoneList.map(function(item,index){
					tempZoneHtml += '<option data-id="' + item.zoneId + '">'
					+ item.name + '</option>';
				});
				
				data.shopCategoryList.map(function(item,index){
					tempShopCateHtml += '<option data-id="' + item.id + '">'
					+ item.name + '</option>';
				});
				$('#zone').html(tempZoneHtml);
				$('#shop-category').html(tempShopCateHtml);	
			}
		});
		
		$('submit').click(function(){
			var shop = {};
			shop.name = $('#name').val();
			shop.shopCategory = {
				shopCategoryId: $('#shop-category').find('option').not(function(){
				return !this.selected;
			}).data('id')
			};
			shop.zone = {
				zoneId: $('#zone').find('option').not(function(){
				return !this.selected;
			}).data('id')
			};
			
			var shopImg = $('#shop-img')[0].file[0];
			shop.addr = $('#address').val();
			shop.phone = $('#phone').val();
			shop.shopDesc = $('#shop-desc').val();
			
			var formData = new FormData();
			formData.append('shopImg', shopImg);
			formData.append('shopStr', JSON.stringify(shop));
			
			$.ajax({
				url: registerShopUrl,
				type: 'POST',
				data: formData,
				contentType: false,
				processData: false,
				cache: false,
				success:function(data){
					if (data.success) {
						$.toast('well done');
					}
					else {
						$.toast('failed ' + data.errMsg);
					}
				}
			});
		});
	}
})