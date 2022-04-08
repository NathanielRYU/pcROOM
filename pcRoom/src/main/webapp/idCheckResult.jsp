<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	#wrap	{
			width: 490px;
			text-align :center;
			margin: 0 auto 0 auto;
			}
	#chk	{
			color:#FF24A3;
			}
</style>
<script>
	function back() {
		history.go(-1);
	}
	
</script>
</head>
<body>
<div id="wrap">
	<br>
	<b><font size="4" color="gray">중복 결과</font></b>
	<hr size="1" width="460">
	<br>
	<div id="chk">
		<strong>다른 아이디를 사용해 주세요!!</strong>
	</div>
	<div><input type="button" id="ba" value="뒤로가기" onClick="back();" /></div>
</div>	
</body>
</html>