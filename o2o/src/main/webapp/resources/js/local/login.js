
$(function(){
	var loginUrl = '/local/logincheck'; 
	var needVerify = false;
	var loginCount = 0;
	var userType = getQueryString('userType');
	$('#submit').click(function(){
		var userName = $('#name').val();
		var password = $('#password').val();

		var verifyCodeActual = $('#verify_code').val();
		if (loginCount >= 3) {
			if (!verifyCodeActual) {
				$toast("please input verify code !");
				return;
			}
		}
			
		$.ajax({
			url: loginUrl,
			type: 'POST',
			data: {
				userName: userName,
				password: password,
				needVerify : needVerify,
				verifyCodeActual : verifyCodeActual
			},
			dataType: 'json',
			success:function(data){
				if (data.success) {
					$.toast('login successfully');
					if (userType == 1) {
						window.location.href = '/frontend/index';
					} else {
						window.location.href = '/shopadmin/shoplist';
					}		
				}
				else {
					$.toast('failed! ' + data.errMsg);
					loginCount++;
					if (loginCount >= 3) {
						$('#verifyPart').show();
						needVerify = true;
						$('#verify_code_img').click();
					}
				}
				
			}
		});
	});
})