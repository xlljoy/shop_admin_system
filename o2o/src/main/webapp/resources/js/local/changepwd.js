
$(function(){
	var userType = getQueryString('userType');
	var changePwdUrl = '/local/changelocalaccountpwd'; 

	$('#submit').click(function(){
		var userName = $('#name').val();
		var oldPassword = $('#old-password').val();
		var newPassword = $('#new-password').val();
		var confirmPassword = $('#confirm-password').val();		
		if (oldPassword === newPassword) {
			$.toast('please input a new password!');
			return;
		}
		
		if (confirmPassword != newPassword) {
			$.toast('new password does not match');
			return;
		}
		var verifyCodeActual = $('#verify_code').val();
		if (!verifyCodeActual) {
			$.toast('please input verify code !');
			return;
		}			
		$.ajax({
			url: changePwdUrl,
			type: 'POST',
			data: {
				userName: userName,
				password: oldPassword,
				newPassword: newPassword,
				verifyCodeActual: verifyCodeActual
			},
			dataType: 'json',
			success:function(data){
				if (data.success) {
					$.toast('password have been changed');
					if (userType == 1) {
						window.location.href = '/frontend/index';
					} else {
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