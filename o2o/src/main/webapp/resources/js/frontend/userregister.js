/**
 * 
 */
$(function(){
	var userregisterUrl = "/frontend/adduser";

		$('#submit').click(function(){
			var user = {};
			user.name = $('#name').val();
			user.gender = 
					$('#gender').find('option').not(function(){
				return !this.selected;
			}).data('value')
			;
			var profileImg = $('#head-img')[0].files[0];
			user.email = $('#email').val();
			var formData = new FormData();
			formData.append('headImg', profileImg);
			formData.append('userStr', JSON.stringify(user));
			
			var verifyCodeActual = $('#verify_code').val();
			if (!verifyCodeActual) {
				$toast("please input verify code !");
				return;
			}
			formData.append('verifyCodeActual', verifyCodeActual);
			
			$.ajax({
				url: userregisterUrl,
				type: 'POST',
				data: formData,
				contentType: false,
				processData: false,
				cache: false,
				success:function(data){
					if (data.success) {
						$.toast('well done');
						window.location.href = '/frontend/index';
					}
					else {
						$.toast('failed ' + data.errMsg);
					}
					$('#verify_code_img').click();
				}
			});
		});
})