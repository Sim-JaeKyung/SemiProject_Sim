$(document).ready(function() {

	const input = $('#inputBtn').click(function() {
		let inputText = $('#donkey-text').val();
		const output = $('#donkey-output');
		console.log(inputText);
		output += $('#donkey-output').append(inputText);
		//출력 결과 왜 영어는 가로로 쭉 늘어나는데, 한글은 페이지 안에서 세로로 내려가는지 모르겠음
	});
	$('#brBtn').click(function() {
		$('#donkey-output').append("<br/>");
		//변수에 대한걸 더 배우면 윗줄에 깔끔하게 변수선언해주고, 효율적으로 정리할 수 있을 것 같음.
		//output.append("<br/>");를 사용 가능하게 하는 것
	});
	$('#clearBoxBtn').click(function(){
	    $('#donkey-container').empty();
	});
});