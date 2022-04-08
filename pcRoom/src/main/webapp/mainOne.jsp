<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="${pageContext.request.contextPath}/resources/common.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath}/resources/forMainOne.css" rel="stylesheet" type="text/css">
<script src="resources/resource.js"></script>
<script src="resources/friendRequest.js"></script>

</head>
<body onload = "getReq('${nickName}'),getRes('${nickName}'),getBlk('${nickName}')">
	<div class="top">
		<span id="top_left">[${nickName}]님!
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
					onmouseout="this.style.backgroundColor='#FFF';"  onClick="moveServices('FriendList','${nickName}','${pcRoomName}')">목&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;록</p>

				<div>
					<p class="textTwo" id="three" >*친구 로그인 상태 확인*</p>

				</div>
			</div>
			<div class="textOne">

				<p class="textOneA" id="four"
					onmouseover="this.style.backgroundColor='skyblue';"
					onmouseout="this.style.backgroundColor='#FFF';">등&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;록&nbsp;&nbsp;&nbsp;&&nbsp;&nbsp;&nbsp;차&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;단</p>

				<div>
					<p class="textTwo" id="five">*친구 신청, 등록, 차단*</p>
				</div>
			</div>
			
		</section>
		<div class="searchBox">
		<input type="text" id="value" name = "frId" placeholder="아이디 찾기" /><button onClick = "getFrIdCheck();" class="search">찾기</button>
		</div>
	</div>
	<div class="titleNameforList" id="reqTitle" onClick="getReq('${nickName}')">신&nbsp;청&nbsp;한 친&nbsp;구&nbsp;목&nbsp;록</div>
	<div class="floatS" id="reqList"></div>
	<input type="button" id="accept" value="추&nbsp;&nbsp;가" onClick="addReq()" /></div>
	<input type="button" id="refuse" value="삭&nbsp;&nbsp;제" onClick="delReq()" /></div>
	<div class="titleNameforList" id="resTitle" onClick="getRes('${nickName}')">받&nbsp;은&nbsp;친&nbsp;구&nbsp;목&nbsp;록</div>
	<div class="floatS" id="resList"></div>
	<input type="button" id="accept2" value="수&nbsp;&nbsp;락" onClick="addRes()" /></div>
	<input type="button" id="refuse2" value="거&nbsp;&nbsp;절" onClick="delRes()" /></div>
	<div class="titleNameforList" id="blkTitle" onClick="getBlk('${nickName}')">차&nbsp;단&nbsp;목&nbsp;록</div>
	<div class="floatS" id="blkList"></div>
	<input type="button" id="accept3" value="추&nbsp;&nbsp;가" onClick="addBlk()" /></div>
	<input type="button" id="refuse3" value="삭&nbsp;&nbsp;제" onClick="delBlk()" /></div>
	<div class="bottom" id="sevenText">	
	</div>
	<div><input type="button" id="bb" value="뒤로가기" onClick="backButton()" /></div>
	<div class="floatS" id="sideRight"></div>
	
	<div id = "frIdCheckBox">
	      <div id = "frIdInput"><span id = "reqFrId" name = "resUsId"></span></div>
	</div>
</body>
<script>

	
</script>
</html>