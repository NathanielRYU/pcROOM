
function makeForm(fname, faction, fmethod){
	const form = document.createElement("form");
	if(fname != ""){form.setAttribute("name", fname);}
	form.setAttribute("action", faction);
	form.setAttribute("method", fmethod);
	return form;
}

function makeInputElement(type, name, value, placeholder){
	const input = document.createElement("input");
	input.setAttribute("type", type);
	input.setAttribute("name", name);
	if(value != ""){input.setAttribute("value", value);}
	if(placeholder != ""){input.setAttribute("placeholder", placeholder);}
	
	return input;
}


/*MainFriend.jsp -> mainOne or mainTwo 페이지 이동*/
function moveServices(action, pNickName, pPcRoomName) {
	const form = makeForm("", action, "post");
	const nickName = makeInputElement("hidden", "nickName", pNickName, "");
	const pcRoomName = makeInputElement("hidden", "pcRoomName", pPcRoomName, "");
	form.appendChild(nickName);
	form.appendChild(pcRoomName);
	document.body.appendChild(form);
	form.submit();

}

function backButton(){
		history.go(-1);
	}
	
function accessOut(Action, pNickName, pPcRoomCode) {
     
		location.href = "Out?nickName=" + pNickName + "&pcRoomCode=" + pPcRoomCode;

	}
		