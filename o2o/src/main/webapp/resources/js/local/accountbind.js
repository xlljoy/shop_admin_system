
$(function(){
	var accountbindUrl = '/local/binglocalaccount'; 
	var userType = getQueryString('userType'); // 2: shop owner
	$('#submit').click(function(){
		var userName = $('#name').val();
		var password = $('#password').val();
		
		var verifyCodeActual = $('#verify_code').val();
		if (!verifyCodeActual) {
			$toast("please input verify code !");
			return;
		}			
		$.ajax({
			url: accountbindUrl,
			async : false,
			cache : false,
			type: 'POST',
			data: {
				userName: userName,
				password: password,
				verifyCodeActual : verifyCodeActual
			},
			dataType: 'json',
			success:function(data){
				if (data.success) {
					$.toast('binding succeed');
					if (userType == 1) {
						// 1: normal user
						window.location.href = '/frontend/index';
					} else {
						// otherwise, shop owner
						window.location.href = '/shopadmin/shoplist';
					}
				}
				else {
					$.toast('failed ' + data.errMsg);
				}
				$('#verify_code_img').click();
			}
		});
	});
})