/**
 * 
 */
$(function(){
	var shopId = getQueryString('shopId');
	var isEdit =  shopId ? true : false;
	var initUrl = '/shopadmin/getshopinitinfo';
	var getShopByIdUrl = '/shopadmin/getshopbyid?shopId=' + shopId; 
	var modifyShopUrl = '/shopadmin/modifyshop';
	var registerShopUrl = '/shopadmin/registershop';
	//alert(initUrl);
	if (isEdit) {
		getShopById(shopId);
	} else {
		getShopInitInfo();
	}
	
	function getShopById(shopId){
		$.getJSON(getShopByIdUrl,function(data){
			if(data.success){
				var shop = data.shop;
				$('#address').val(shop.addr);
				$('#name').val(shop.name);
				$('#phone').val(shop.phone);
				$('#shop-desc').val(shop.shopDesc);
			
			var shopCategory = '<option data-id="' 
				+ shop.shopCategory.id + '"selected>'
				+ shop.shopCategory.name + '</option>';
			
				var tempZoneHtml = '';
				data.zoneList.map(function(item,index){
					tempZoneHtml += '<option data-id="' + item.zoneId + '">'
					+ item.name + '</option>';
				});
				$('#shop-category').html(shopCategory);	
				$('#shop-category').attr('disabled','disabled');
				$('#zone').html(tempZoneHtml);
				$("#zone option[data-id='"+shop.zone.zoneId+"']").attr("selected", "selected");	
			}
		});
	}
	
	
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
	}
	

		
		

		$('#submit').click(function(){
			var shop = {};
			if (isEdit) {
				shop.id = shopId;
			}
			shop.name = $('#name').val();
			shop.shopCategory = {
				id: $('#shop-category').find('option').not(function(){
				return !this.selected;
			}).data('id')
			};
			shop.zone = {
				zoneId: $('#zone').find('option').not(function(){
				return !this.selected;
			}).data('id')
			};
			
			var shopImg = $('#shop-img')[0].files[0];
			shop.addr = $('#address').val();
			shop.phone = $('#phone').val();
			shop.shopDesc = $('#shop-desc').val();
			
			var formData = new FormData();
			formData.append('shopImg', shopImg);
			formData.append('shopStr', JSON.stringify(shop));
			
			var verifyCodeActual = $('#verify_code').val();
			if (!verifyCodeActual) {
				$toast("please input verify code !");
				return;
			}
			formData.append('verifyCodeActual', verifyCodeActual);
			
			$.ajax({
				url: isEdit ? modifyShopUrl : registerShopUrl,
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
					$('#verify_code_img').click();
				}
			});
		});
})