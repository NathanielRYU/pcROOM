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
			text-align :center;
			}
		
</style>
<script>
	function pValue(){
		document.getElementById("userId").value = opener.document.getElementById("txTwo").value;
	}
	
	function sendValue(){
		opener.document.getElementById("txTwo").value = document.getElementById("userId").value;
	}
	function idCheck(){
		const userData = document.getElementsByName("idinput")[0];
		const form = document.getElementsByName("idCheckForm")[0];
		
		form.submit();
	}
</script>
</head>
<body onload="pValue()">
<div id="wrap">
	<br>
	<b><font size="4" color="gray">아이디 중복체크</font></b>
	<hr size="1" width="460">
	<br>
	<div id="chk">
		<form name="idCheckForm" action="idChe" method="post">
			<input type="text" name="idinput" id="userId" >
			<input type="button" value="중복확인" onclick="idCheck()">
			<input type="button" value="사용하기" onclick="sendValue()">
		</form>
		<div id="msg"></div>
		<br>
		<input id="cancelBtn" type="button" value="닫기" onclick="window.close()"><br>
	</div>
</div>	
</body>
</html>