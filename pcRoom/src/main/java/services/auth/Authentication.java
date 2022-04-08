package services.auth;

import java.sql.Connection;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import bean.MembersInfo;
import bean.PcRoom;
import webpos.DataAccessObject;
import bean.Action;


public class Authentication {
	private HttpSession session;
	private HttpServletRequest req;
	private DataAccessObject dao;
	private MembersInfo mi;
	private PcRoom pc;
	private Action action;

	public Authentication(HttpServletRequest req) {
		this.req = req;
		dao = null;
	}

	public Action backController(int jobCode) { // Action -->>로그인 > 동적페이지, 로그아웃 > 정적페이지
		Action action = null;
		switch (jobCode) {
		case 1:
			action = this.accessCtl();
			break;
		case -1:
			action = this.accessOutCtl();
			break;
		case 2:
			action = this.idCheckCtl();
			break;

		case 0:
			action = this.afterAccess();
			break;
		case 3:
			action = this.setMembers();
			break;
		default:
		}
		return action;
	}


	//===============================회원가입
	private Action setMembers() {
		Action action = new Action();
		boolean tran = false;

		this.mi = new MembersInfo();
		this.mi.setNickName(this.req.getParameter("nickname"));
		this.mi.setMemName(this.req.getParameter("pName"));
		this.mi.setPhoneNum(this.req.getParameter("phonenum"));
		this.mi.setPassWord(this.req.getParameter("pPassword"));

		dao = new DataAccessObject();
		Connection conn = dao.getConnection();
		dao.modifyTranStatus(conn, false);

		if(dao.setMember(conn, mi)) {
			tran =true;
		}

		action.setPage(tran ? "main.html" : "signin.html");
		action.setRedirect(tran ? false : true);

		dao.setTransaction(conn, tran);
		dao.modifyTranStatus(conn, true);
		dao.closeConnection(conn);

		return action;
	}

	/*id중복체크*/
	private Action idCheckCtl() {
		Action action = new Action();

		boolean tran = false;

		this.mi = new MembersInfo();
		this.mi.setNickName(this.req.getParameter("idinput"));

		dao = new DataAccessObject();
		Connection conn = dao.getConnection();
		dao.modifyTranStatus(conn, false);

		if (dao.idCheckInfo(conn, mi)) {
			req.setAttribute("accessInfo", mi.getNickName());
			tran = true;
		}

		action.setPage(tran ? "idCheckResult.jsp" : "idCheckFalse.jsp");
		action.setRedirect(tran ? false : true);

		dao.setTransaction(conn, tran);
		dao.modifyTranStatus(conn, true);
		dao.closeConnection(conn);

		return action;
	}
	/*로그인*/
	private Action accessCtl() {

		Action action = new Action();
		boolean tran = false;

		this.mi = new MembersInfo();

		this.mi.setNickName(this.req.getParameter("nickName"));
		this.mi.setPassWord(this.req.getParameter("pPassword"));


		dao = new DataAccessObject();
		Connection conn = dao.getConnection();
		dao.modifyTranStatus(conn, false);

		if (dao.isMember(conn, mi)) {
			session = this.req.getSession();
			session.setAttribute("nickName", mi.getNickName());

			tran = true;
		}

		action.setPage(tran ? "main.jsp" : "main.html");
		action.setRedirect(tran ? false : true);

		dao.setTransaction(conn, tran);
		dao.modifyTranStatus(conn, true);
		dao.closeConnection(conn);

		return action;
	}

	private Action accessOutCtl() {
		Action action = new Action();
		boolean tranState = false;
		HttpSession session = this.req.getSession();

		this.mi = new MembersInfo();
		//1. 클라이언트 데이터 --> MembersInfo : nickName, pcRoomCode, State

		this.mi.setNickName(this.req.getParameter("nickName"));
		this.mi.setMemPcCode(this.req.getParameter("pcRoomCode"));
		this.mi.setState(-9);


		/* dao호출 */
		dao = new DataAccessObject();
		Connection conn = dao.getConnection();
		dao.modifyTranStatus(conn, tranState);

		if(dao.insAccessHistory(conn, mi)) {
			tranState = true;
			session.invalidate();
		}

		action.setRedirect(tranState? true : false);
		action.setPage("main.html");

		return action;

	}

	private Action afterAccess() {
		
		action = new Action();
		session = this.req.getSession();
		session.setAttribute("nickName", session.getAttribute("nickName"));
		session.setAttribute("pcRoomName", session.getAttribute("pcRoomName"));
		action.setRedirect(false);
		action.setPage("mainFriend.jsp");

		return action;
	}

}
