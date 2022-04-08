<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here :: dynamic :: accessinfo</title>

<link href="${pageContext.request.contextPath}/resources/common.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath}/resources/forMain.css" rel="stylesheet" type="text/css">
<script src="resources/resource.js"></script>

<script>
   function getList(Action, pNickName) {
      const form = makeForm('', Action, 'post');
      const nickName = makeInputElement('hidden', 'nickName', pNickName, '');

      form.appendChild(nickName);
      document.body.appendChild(form);
      alert(nickName);
      form.submit();
   }

   function getPcList(Action, pNickName, pPcRoomCode, pPcRoomName
		              'pcRoom','${nickName}', 'P1','인디고 PC방') {
      const form = makeForm('', Action, 'post');
      const nickName = makeInputElement('hidden', 'nickName', pNickName, '');
      const pcRoomCode = makeInputElement('hidden', 'pcRoomCode',
            pPcRoomCode, '');
      const pcRoomName = makeInputElement('hidden', 'pcRoomName',
            pPcRoomName, '');
      form.appendChild(nickName);
      form.appendChild(pcRoomCode);
      form.appendChild(pcRoomName);
      document.body.appendChild(form);

      form.submit();
   }
</script>
</head>
<body>
   <div class="top">
      <span id="top_left">${nickName}님!안녕하세요!</span>
   </div>
   <div class="titleName">[ &nbsp;&nbsp;피 / 시 / 방&nbsp;&nbsp;]</div>
   <div class="mainTitle Active" id="oneText"></div>
   <section>
      <div class="mainTitle" id="twoText">
         <p class="subTitle"
            onmouseover="this.style.backgroundColor='skyblue';"
            onmouseout="this.style.backgroundColor='#FFF';">연수구</p>
         <div class="storeName">
            <p>
               <span onClick="getPcList('pcRoom','${nickName}', 'P1','인디고 PC방')">인디고
                  PC방</span>
            </p>
            <p>
               <span onClick="getPcList('pcRoom','${nickName}', 'P2','AWM PC방')">AWM
                  PC방</span>
            </p>
            <p>
               <span onClick="getPcList('pcRoom','${nickName}', 'P3','아지트 PC방')">아지트
                  PC방</span>
            </p>
            <p>
               <span onClick="getPcList('pcRoom','${nickName}', 'P4','4TEN PC방')">4TEN
                  PC방</span>
            </p>
            <p>
               <span onClick="getPcList('pcRoom','${nickName}', 'P5','사이버리아 PC방')">사이버리아
                  PC방</span>
            </p>
            <p>
               <span onClick="getPcList('pcRoom','${nickName}', 'P6','오셀롯 PC방')">오셀롯
                  PC방</span>
            </p>
            <p>
               <span onClick="getPcList('pcRoom','${nickName}', 'P7','헤라 PC방')">헤라
                  PC방</span>
            </p>
         </div>
      </div>
      <div class="mainTitle" id="threeText">
         <p class="subTitle"
            onmouseover="this.style.backgroundColor='skyblue';"
            onmouseout="this.style.backgroundColor='#FFF';">부평구</p>
         <div class="storeName">
            <p>
               <span
                  onClick="getPcList('pcRoom','${nickName}', 'P8','5-STAR PC방')">5-STAR
                  PC방</span>
            </p>
            <p>
               <span onClick="getPcList('pcRoom','${nickName}', 'P9','오레오 PC방')">오레오
                  PC방</span>
            </p>
            <p>
               <span onClick="getPcList('pcRoom','${nickName}', 'P10','보야르 PC방')">보야르
                  PC방</span>
            </p>
            <p>
               <span onClick="getPcList('pcRoom','${nickName}', 'P11','3POP PC방')">3POP
                  PC방</span>
            </p>
            <p>
               <span onClick="getPcList('pcRoom','${nickName}', 'P12','본스 PC방')">본스
                  PC방</span>
            </p>
            <p>
               <span onClick="getPcList('pcRoom','${nickName}', 'P13','독스 PC방')">독스
                  PC방</span>
            </p>
            <p>
               <span onClick="getPcList('pcRoom','${nickName}', 'P14','시즌아이 PC방')">시즌아이
                  PC방</span>
            </p>
         </div>
      </div>
      <div class="mainTitle" id="fourText">
         <p class="subTitle"
            onmouseover="this.style.backgroundColor='skyblue';"
            onmouseout="this.style.backgroundColor='#FFF';">미추홀구</p>
         <div class="storeName">
            <p>
               <span onClick="getPcList('pcRoom','${nickName}', 'P15','하늘다리 PC방')">하늘다리
                  PC방</span>
            </p>
            <p>
               <span onClick="getPcList('pcRoom','${nickName}', 'P16','진 PC방')">진
                  PC방</span>
            </p>
            <p>
               <span onClick="getPcList('pcRoom','${nickName}', 'P17','여기 PC방')">여기
                  PC방</span>
            </p>
            <p>
               <span onClick="getPcList('pcRoom','${nickName}', 'P18','더엑스 PC방')">더엑스
                  PC방</span>
            </p>
            <p>
               <span onClick="getPcList('pcRoom','${nickName}', 'P19','킹덤 PC방')">킹덤
                  PC방</span>
            </p>
            <p>
               <span onClick="getPcList('pcRoom','${nickName}', 'P20','긱스타 PC방')">긱스타
                  PC방</span>
            </p>
            <p>
               <span onClick="getPcList('pcRoom','${nickName}', 'P21','비원 PC방')">비원
                  PC방</span>
            </p>
         </div>
      </div>
      <div class="mainTitle" id="fiveText">
         <p class="subTitle"
            onmouseover="this.style.backgroundColor='skyblue';"
            onmouseout="this.style.backgroundColor='#FFF';">남동구</p>
         <div class="storeName">
            <p>
               <span onClick="getPcList('pcRoom','${nickName}', 'P22','놀이터 PC방')">놀이터
                  PC방</span>
            </p>
            <p>
               <span onClick="getPcList('pcRoom','${nickName}', 'P23','메종드 PC방')">메종드
                  PC방</span>
            </p>
            <p>
               <span onClick="getPcList('pcRoom','${nickName}', 'P24','탑스타 PC방')">탑스타
                  PC방</span>
            </p>
            <p>
               <span onClick="getPcList('pcRoom','${nickName}', 'P25','세븐 PC방')">세븐
                  PC방</span>
            </p>
            <p>
               <span onClick="getPcList('pcRoom','${nickName}', 'P26','아레나 PC방')">아레나
                  PC방</span>
            </p>
            <p>
               <span onClick="getPcList('pcRoom','${nickName}', 'P27','철구 PC방')">철구
                  PC방</span>
            </p>
            <p>
               <span onClick="getPcList('pcRoom','${nickName}', 'P28','VRIZ PC방')">VRIZ
                  PC방</span>
            </p>
         </div>
      </div>
      <div class="mainTitle" id="sixText">
         <p class="subTitle"
            onmouseover="this.style.backgroundColor='skyblue';"
            onmouseout="this.style.backgroundColor='#FFF';">서구</p>
         <div class="storeName">
            <p>
               <span onClick="getPcList('pcRoom','${nickName}', 'P29','노블 PC방')">노블
                  PC방</span>
            </p>
            <p>
               <span onClick="getPcList('pcRoom','${nickName}', 'P30','팬텀 PC방')">팬텀
                  PC방</span>
            </p>
            <p>
               <span onClick="getPcList('pcRoom','${nickName}', 'P31','벤큐 PC방')">벤큐
                  PC방</span>
            </p>
            <p>
               <span onClick="getPcList('pcRoom','${nickName}', 'P32','그레이 PC방')">그레이
                  PC방</span>
            </p>
            <p>
               <span onClick="getPcList('pcRoom','${nickName}', 'P33','아이센스 PC방')">아이센스
                  PC방</span>
            </p>
            <p>
               <span
                  onClick="getPcList('pcRoom','${nickName}', 'P34','URBAN PC방')">URBAN
                  PC방</span>
            </p>
            <p>
               <span onClick="getPcList('pcRoom','${nickName}', 'P35','케이스타 PC방')">케이스타
                  PC방</span>
            </p>
         </div>
      </div>
   </section>
   <div class="note">*INFOMATION* 현재 접속 지역의 "피씨방"을 클릭하여 로그인 하세요!!</div>
      <input type="button" id="bb" value="뒤로가기" onClick="backButton()" />
</body>
<script>
   let menuZone = document.getElementsByClassName("mainTitle");
   let menuTitle = document.getElementsByClassName("subTitle");
   let menuItems = document.getElementsByClassName("storeName");

   for (let titleIdx = 0; titleIdx < menuTitle.length; titleIdx++) {
      menuTitle[titleIdx].addEventListener("click", function(e) {//이벤트 기능이 없는 곳에 이벤트 기능을 부여할때:addEventListener
         for (let zoneIdx = 0; zoneIdx < menuZone.length; zoneIdx++) {
            menuZone[zoneIdx].classList.remove("Active");
         }
         e.target.parentNode.classList.add("Active");
         activeItem();
      });
   }

   function activeItem() {
      for (let itemsIdx = 0; itemsIdx < menuItems.length; itemsIdx++) {
         menuItems[itemsIdx].style.display = "none";
      }
      const activeItems = document
            .querySelector('.mainTitle.Active .storeName');

      activeItems.style.display = "block";
   }
   activeItem();
</script>
</html>