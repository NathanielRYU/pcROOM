<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="${pageContext.request.contextPath}/resources/common.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath}/resources/forMainTwo.css" rel="stylesheet" type="text/css">
<script src="resources/friendList.js"></script>
<script src="resources/resource.js"></script>
</head>

<body onload = "getList('${nickName}')">
	<div class="top">
		<span id="top_left" >[${nickName}]님!
			여기는&nbsp;[${pcRoomName}]&nbsp;입니다!</span> <span id="top_right"><input
			type="button" id="out" value="로그아웃"
			onClick="accessOut('Out','${nickName}','${pcRoomCode}')" /></span>
	</div>
	<div class="titleName">[ &nbsp;&nbsp;피 / 시 / 방&nbsp;&nbsp;]</div>
	<div class="floatS" id="dumy"></div>
	<div class="floatS" id="sideLeft">
		<section>
			<div class="textOne Active" id="one">친&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;구</div>
			<div class="textOne">

				<p class="textOneA" id="two"
					onmouseover="this.style.backgroundColor='skyblue';"
					onmouseout="this.style.backgroundColor='#FFF';" >목&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;록</p>

				<div>
					<p class="textTwo" id="three" >*친구 로그인 상태 확인*</p>

				</div>
			</div>
			<div class="textOne">

				<p class="textOneA" id="four"
					onmouseover="this.style.backgroundColor='skyblue';"
					onmouseout="this.style.backgroundColor='#FFF';" onClick="moveServices('RegFriend','${nickName}','${pcRoomName}')">등&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;록&nbsp;&nbsp;&nbsp;&&nbsp;&nbsp;&nbsp;차&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;단</p>

				<div>
					<p class="textTwo" id="five">*친구 신청, 등록, 차단*</p>
				</div>
			</div>
			
		</section>
	</div>
	<div class="floatS" id="sideRight"></div>
	<div >
		<input type="button" id="bb" value="뒤로가기" onClick="backButton()" />
	</div>
	<div id = Title>
	<div id = dataTitle> 
	   <table>
	       <tr>
	           <th width=7%><span class = subTitle>친구ID</span></th>
	           <th width=7%><span class = subTitle>로그인 된 PC방</span></th>
	           <th width=7%><span class = subTitle>로그인 상태</span></th>
	       </tr>
	   </table>
	   </div>
	   <div id = Friend onClick="refresh()">새로고침</div>
	   <div id = "dataList"></div>
	</div>
	
	
</body>

</html>