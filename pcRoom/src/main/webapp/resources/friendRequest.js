/*신청목록 차단목록에 출력할 회원아이디 배열*/
let itemName = ["nickName"];

/*<div></div>생성*/
function createDiv(name, className) {
   const div = document.createElement("div");
   div.setAttribute("name", name);
   div.setAttribute("class", className);



   return div;
}   
/*JsonData 가져오기*/
function getAjaxJson(action, data, fn) {
   let ajax = new XMLHttpRequest();
   ajax.onreadystatechange = function() {
      if (ajax.readyState == 4 && ajax.status == 200) {
         window[fn](JSON.parse(ajax.responseText));
         
      }

   };

   ajax.open("post", action, true);
   ajax.setRequestHeader("Content-type", "application/x-www-form-urlencoded")
   /*send 하기 전엔 state값 2, status 값은 없음*/
   ajax.send(data);/*send 누르면 state가 3으로 변함*/
   
}
/*자신 신청한 친구목록을 가져오기위한 REQUEST ID 값 전송*/
function getReq(pNickName) {
   
   const data = "nickName=" + encodeURIComponent(pNickName);
   getAjaxJson("ReqFriend", data, "friendReqList");
}
/*신청받은 친구목록을 가져오기위한 RESPONSE ID 값 전송*/
function getRes(pNickName) {
   
   const data = "nickName=" + encodeURIComponent(pNickName);
   getAjaxJson("ResFriend", data, "friendResList");
}
/*신청받은 친구목록을 가져오기위한 RESPONSE ID 값 전송*/
function getBlk(pNickName) {
   
   const data = "nickName=" + encodeURIComponent(pNickName);
   getAjaxJson("BlkFriend", data, "friendBlkList");
}
/*신청목록 가져오기*/
function friendReqList(jsonData) {
   const list = document.getElementById("reqList");
   
      for (idx = 0; idx < jsonData.length; idx++) {
         let record = createDiv("record", "record");
         record.setAttribute("onClick","selectRecord(this)");
            record.setAttribute("style","background-color:black");
         for (colIdx = 0; colIdx < 1; colIdx++) {
         
            let item = createDiv(itemName[colIdx], "friends " + itemName[colIdx]);
            if(colIdx==0){
               item.innerHTML += jsonData[idx].userId;
            }
            
            record.appendChild(item);   
         }
         list.appendChild(record);
      }
}
/*신청받은목록 가져오기*/
function friendResList(jsonData) {
   const list = document.getElementById("resList");
   
         for (idx = 0; idx < jsonData.length; idx++) {
            let record = createDiv("record", "record");
            record.setAttribute("onClick","selectRecord(this)");
               record.setAttribute("style","background-color:black");
            for (colIdx = 0; colIdx < 1; colIdx++) {
               
            let item = createDiv(itemName[colIdx], "friends " + itemName[colIdx]);
            if(colIdx==0){
               item.innerHTML += jsonData[idx].userId;
            }
            record.appendChild(item);
         }
         list.appendChild(record);
      }
}
/*차단목록 가져오기*/
function friendBlkList(jsonData) {
   const list = document.getElementById("blkList");
   
      for (idx = 0; idx < jsonData.length; idx++) {
            let record = createDiv("record", "record");
            record.setAttribute("onClick","selectRecord(this)");
            record.setAttribute("style","background-color:black");
            for (colIdx = 0; colIdx < 1; colIdx++) {
               
            let item = createDiv(itemName[colIdx], "friends " + itemName[colIdx]);
            if(colIdx==0){
               item.innerHTML += jsonData[idx].userId;
            }
            record.appendChild(item);
         }
         list.appendChild(record);
      }
}

/*------------------------------------------승현-------------------------------------------------------기능 구현 -------*/

/*userId 유효성 체크 후 return data*/
function getUserData(action, data) {
      let ajax = new XMLHttpRequest();

      ajax.onreadystatechange = function() {
         if (ajax.readyState == 4 && ajax.status == 200) {
            const serverData = ajax.responseText;
            
            
         if (serverData.substr(0, 1) == "<") {
            document.getElementById("frIdInput").innerHTML = serverData;
         } else {
               document.getElementById("reqFrId").innerHTML = serverData; 
               document.getElementById("frIdInput").innerHTML += "님께 친구 신청 or 차단"; 
               
         }

         }
      };
      /*true는 비동기화*/
      ajax.open("post", action, true);
      ajax.setRequestHeader("Content-type",
            "application/x-www-form-urlencoded")

      /*send 하기 전엔 state값 2, status 값은 없음*/
      ajax.send(data);/*send 누르면 state가 3으로 변함*/

   }   
/*추가, 삭제, 거절, 차단 Data 전달*/
function getAjaxData(action, data) {
		let ajax = new XMLHttpRequest();

		ajax.onreadystatechange = function() {
			if (ajax.readyState == 4 && ajax.status == 200) {
				const serverData = ajax.responseText;
				alert(serverData);
				
					document.getElementById("frIdInput").innerHTML = serverData;
			        refresh();
			}
		};
		/*true는 비동기화*/
		ajax.open("post", action, true);
		ajax.setRequestHeader("Content-type",
				"application/x-www-form-urlencoded")

		/*send 하기 전엔 state값 2, status 값은 없음*/
		ajax.send(data);/*send 누르면 state가 3으로 변함*/

	}
	
/*userId 유효성 체크*/ 
function getFrIdCheck(){
   const userId = document.getElementsByName("frId")[0];
   const data = "userId=" + encodeURIComponent(userId.value);
   getUserData("FrId",data);
   
}
/*친구 신청하기*/
function addReq(pNickName){
   const stCode = "&states=" + -1;
   const resUsId = document.getElementById("reqFrId").innerText;
   const data = "&resUsId=" + encodeURIComponent(resUsId) + stCode;
  
   getAjaxData("AddReq",data);
}
/*유저 차단하기*/
function addBlk(pNickName){
   const stCode = "&states=" + -2;
   const resUsId = document.getElementById("reqFrId").innerText;
   const data = "resUsId=" + encodeURIComponent(resUsId) + stCode;
  
   getAjaxData("AddReq",data);
}

function addBlk(pNickName){
   const stCode = "&states=" + -2;
   const resUsId = document.getElementById("reqFrId").innerText;
   const data = "resUsId=" + encodeURIComponent(resUsId) + stCode;
  
   getAjaxData("AddReq",data);
}

function delReq(pNickName){
   const stCode = "&states=" + 3;
   const resUsId = document.getElementById("reqFrId").innerText;
   let reqUsId = document.getElementsByName("friends nickName").innerText;
   
   alert(reqUsId);
	
   const data = "&resUsId=" + encodeURIComponent(resUsId) + stCode + "reqUsId=" + encodeURIComponent(reqUsId);
				
   alert(data);
   getAjaxData("delReq",data);
}

let currentRecord = null;

/* 리스트에서 아이디 선택*/
function selectRecord(obj){
   if(currentRecord != null){
      currentRecord.style.color = "#212121";
      
      currentRecord = null;
   }
   currentRecord = obj;
   obj.style.color = "#FF007F";
   obj.style.backgroundColor = "#050099";
}

/*새로고침 기능*/
function refresh(){
	history.go(0);
	}
	
