/*친구 로그인 정보에 해당하는 class 이름 배열*/
let itemName = ["nickName", "pcRoom", "states"];

/*뒤로가기 기능*/
function backButton(){
	history.go(-1);
}

/*로그아웃 기능*/	
function accessOut(Action, pNickName, pPcRoomCode) {
     
	location.href = "Out?nickName=" + pNickName + "&pcRoomCode=" + pPcRoomCode;

}
/*<div></div>생성*/
function createDiv(name, className) {
	const div = document.createElement("div");
	div.setAttribute("name", name);
	div.setAttribute("class", className);

	return div;
}	


/*JsonData 가져오기*/
function getAjaxJson(action,data, fn){
		let ajax = new XMLHttpRequest();
		
		ajax.onreadystatechange = function() {
			if (ajax.readyState == 4 && ajax.status == 200) {
				window[fn](JSON.parse(ajax.responseText));
				
				
			}

		};
	
	ajax.open("post", action, true);
	ajax.setRequestHeader("Content-type","application/x-www-form-urlencoded")
    /*send 하기 전엔 state값 2, status 값은 없음*/
    ajax.send(data);/*send 누르면 state가 3으로 변함*/
	}
	
/*자신의 Id 전송*/
function getList(pNickName){

	 const data = "nickName=" + encodeURIComponent(pNickName); 
	 getAjaxJson("FriendInfo",data,"friendList");
	
}
/*친구 로그인 정보 리스트 생성*/
function friendList(jsonData){
	
	 const list = document.getElementById("dataList");

	      for(idx=0; idx<jsonData.length; idx++){ 
                let record = createDiv("record","record");
                      
					  for(colIdx=0; colIdx<3; colIdx++){
	            	  let item = createDiv(itemName[colIdx], "friends " + itemName[colIdx]);
                	  item.innerHTML += (colIdx ==0)? jsonData[idx].userId : (colIdx ==1)? jsonData[idx].pcRoomName : jsonData[idx].stName;
                      record.appendChild(item);
          	          }
                list.appendChild(record);
           } 
	}


/*새로고침 기능*/
function refresh(){
	history.go(0);
	}
