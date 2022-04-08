package pcRoom;

import java.sql.Connection;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import bean.Action;
import bean.MembersInfo;
import bean.PcRoom;
public class PcRoomManagement {

	private HttpSession session;
	private HttpServletRequest req;
	private MembersInfo mi;
	private PcRoom pc;
	private Action action;

	public PcRoomManagement(HttpServletRequest req) {
		this.req = req;
		
	}

	public Action backController(int jobCode) { // Action -->>로그인 > 동적페이지, 로그아웃 > 정적페이지
		Action action = null;
		switch (jobCode) {
		case 1:
			action = this.accessCtlTwo();
			break;
		default:
		}
		return action;
	}
	//===============================두번째 화면 멤버이름&피씨방이름 표출
	
	private Action accessCtlTwo() {
		
		action = new Action();
		boolean tran = false;

		this.mi = new MembersInfo();
		
		this.mi.setNickName(this.req.getParameter("nickName"));
		this.mi.setMemPcCode(this.req.getParameter("pcRoomCode"));
		this.mi.setMemPcRoomName(this.req.getParameter("pcRoomName"));
		this.mi.setState(9);
	    

	DataAccessObject dao = new DataAccessObject();
	Connection conn = dao.getConnection();

	if(dao.insAccessHistory(conn, mi)) {
		session = this.req.getSession();
		session.setAttribute("nickName", mi.getNickName());
		session.setAttribute("pcRoomCode", mi.getMemPcCode());
		session.setAttribute("pcRoomName", mi.getMemPcRoomName());
		tran = true;

	}
	
	action.setPage(tran ? "mainFriend.jsp" : "main.jsp");
	action.setRedirect(tran ? false : true);


	return action;
}




}

