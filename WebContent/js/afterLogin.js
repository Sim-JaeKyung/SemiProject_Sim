$(document).ready(function() {
	const id = $.cookie('id');
	//로그인 유지+댓글창 id생성
	if (id) {
		document.getElementById("welcomeMsg").innerHTML = id + "님 환영합니다.  <button class='myBtn' id='logoutBtn'>로그아웃</button>";
		$("#replyID").val(id);
	}
	$('#loginCheck > a').click(function(event) {
		if (id == null) {
			alert("로그인 후 이용 바랍니다");

			event.preventDefault();
		}
	});
	//로그아웃
	$(document).on('click', '#logoutBtn', function() {
		$.post('main', { sign: "logout" }, function() {
			alert("로그아웃했습니다");
			$.removeCookie('id', { path: '/' });
			location.reload();
		});
	});

	//댓글달기
	$("#replyBtn").click(function() {
		if (id == null) {
			alert("로그인 후 이용 부탁드립니다");
		} else {
			const id = $("#replyID").val();
			const content = $("#replyContent").val();

			$.post('main', { sign: 'replyInsert', id, content },
				function(data) {
					alert(data);
				});
		}
		location.reload();
	});

    //댓글노출
    $.get('main',{sign:"replyList.do"}, function(data){
        console.log(data);
        data=JSON.parse(data);
        let table="<table><tr><th>글번호</th><th>작성자</th><th>내용</th><th>작성일</th></tr>";
        
        for(let i=0;i<data.length;i++){
            console.log(data[i]);
            table += "<tr><td id='articleNo'>"+data[i].articleNo+"</td><td id='id'>"+data[i].id+"</td>";
            table += "<td id='content'>"+data[i].content+"</td><td id='writeDate'>"+ data[i].writeDate+"</td></tr>";
        }
        table +="</table>";
        $("#replyListDiv").html(table);
    });

});