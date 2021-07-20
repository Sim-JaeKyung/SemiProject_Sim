$(document).ready(function(){
	$('#loginBtn').click(function(){
		const id=$('#id').val();
		const pw=$('#pw').val();
		
		$.post("main",
		{sign:"login",id,pw},
		function(data){
			data=JSON.parse(data);
			console.log(data);
			if(data.msg){
				alert(data.msg);
			}else{
				$.cookie("id",data.id,{path:'/'});
				document.getElementById("welcomeMsg").innerHTML=data.id+"님 환영합니다.  <button class='myBtn' id='logoutBtn'>로그아웃</button>";
				location.reload();
				
			}
		});
	
	});
	
	
});