/**
 * 
 */
$(function(){
	var productId = getQueryString('productId');
	var isEdit = productId ? true : false;
	var initUrl = '/shopadmin/getproductinitinfo';
	var getProductByIdUrl = '/shopadmin/getproductbyid?productId=' + productId; 
	var modifyProductUrl = '/shopadmin/modifyproduct';
	var addProductUrl = '/shopadmin/addproduct';
	var productCategoryUrl = "/shopadmin/getproductcategorylist";
	//alert(initUrl);
	if (isEdit) {
		getProductById(productId);
	} else {
		getProductInitInfo();
	}
	
	function getProductById(productId){
		$.getJSON(getProductByIdUrl,function(data){
			if(data.success){
				var product = data.product;
				$('#name').val(product.name);
				$('#priority').val(product.priority);
				$('#normal-price').val(product.normalPrice);
				$('#promotion-price').val(product.promotionPrice);
				$('#point').val(product.point);
				$('#product-desc').val(product.productDesc);
			
				var tempProductCategoryHtml = '';
				var selectedProductCategory = product.productCategory.id;
				data.productCategoryList.map(function(item,index){
					//var isSelect = selectedProductCategory === item.id ? 'selected' : '';
					tempProductCategoryHtml += '<option data-value="' + item.id + '">'
					+ item.name + '</option>';
				});
				$('#product-category').html(tempProductCategoryHtml);
				$("#product-category option[data-value='"+product.productCategory.id+"']").attr("selected", "selected");	
			}
		});
	}
	
	
	function getProductInitInfo(){
		$.getJSON(initUrl,function(data){
			if(data.success){
				var tempProductCateHtml = '';
				data.productCategoryList.map(function(item,index){
					tempProductCateHtml += '<option data-value="' + item.id + '">'
					+ item.name + '</option>';
				});
				$('#product-category').html(tempProductCateHtml);	
			}
		});
	}
	// for adding detail images,
	// maximunm images could be added are 6
	// once you added one, number is less than 6, new import file button will be generated
	$('.side-img-div').on('change', '.side-img:last-child', function(){
		if ($('.side-img').length < 6) {
			$('#side-img').append('<input type ="file" class="side-img">');
		}
	})

		$('#submit').click(function(){
			var product = {};
			product.id = productId;
			product.name = $('#name').val();
			product.productCategory = {
					id: $('#product-category').find('option').not(function(){
				return !this.selected;
			}).data('value')
			};
			var mainImg = $('#main-img')[0].files[0];
			product.name = $('#name').val();
			product.priority = $('#priority').val();
			product.normalPrice = $('#normal-price').val();
			product.promotionPrice = $('#normal-price').val();
			product.point = $('#point').val();
			product.productDesc = $('#product-desc').val();
			var formData = new FormData();
			
			$('.side-img').map(
					function(index, item){
						//check whether there is a image selected
						if ($('.side-img')[index].files.length > 0) {
							// key is designed as sideImg + i
							formData.append('sideImg' + index, $('.side-img')[index].files[0]);
						}
					})
			formData.append('mainImg', mainImg);
			formData.append('productStr', JSON.stringify(product));
			
			var verifyCodeActual = $('#verify_code').val();
			if (!verifyCodeActual) {
				$toast("please input verify code !");
				return;
			}
			formData.append('verifyCodeActual', verifyCodeActual);
			
			$.ajax({
				url: isEdit ? modifyProductUrl : addProductUrl,
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