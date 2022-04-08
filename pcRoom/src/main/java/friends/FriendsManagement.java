package friends;

import java.sql.Connection;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import bean.Action;
import bean.Friends;
import bean.MembersInfo;





public class FriendsManagement {
	private HttpServletRequest req;
	private HttpSession session;

	public FriendsManagement(HttpServletRequest req) {
		this.req = req;
	}

	public Action backController(int jobCode) { // Action -->>로그인 > 동적페이지, 로그아웃 > 정적페이지
		Action action = null;
		switch (jobCode) {
		case 1:
			action = this.getFriendList1();
			break;

		case 2:
			action = this.getRegFriendList();
			break;
		default:
		}
		return action;
	}

	public String backController(String jobCode) {
		String form = null;

		switch(jobCode) {
		case "1" :
			form = this.getFriendList();
			break;
		case "2" :
			form = this.getReqFriendList();
			break;
		case "3" :
			form = this.getResFriendList();
			break;
		case "4" :
			form = this.getBlkFriendList();
			break;
		case "5" :
			form = this.getFrIdCheck();
			break;
		case "8" :
			form = this.insInfo();
			break;

		default:
		}
		return form;
	}
	
	private String insInfo() {
  
        String message = null;
        boolean tran = false;
        this.session = this.req.getSession();       
        
        Friends fr = new Friends();
        
        fr.setReqUserId((String)this.session.getAttribute("nickName"));
        fr.setResUserId(this.req.getParameter("resUsId"));
        
        DataAccessObject dao = new DataAccessObject();
        Connection conn = dao.getConnection();
        dao.modifyTranStatus(conn, tran);
        
        if(dao.checkFriendTable(conn, fr)) {
        	if(Integer.parseInt(this.req.getParameter("states")) == -1) {
        		tran = true;
        		fr.setStCode(-1);
        		dao.updFrd(conn, fr);
        		message = "친구 신청이 완료되었습니다";
        	}else if(Integer.parseInt(this.req.getParameter("states")) == -2){
        		tran = true;
        		fr.setStCode(-2);
        		dao.updFrd(conn, fr);
        		message = "차단 신청이 완료되었습니다";
        	}else if(Integer.parseInt(this.req.getParameter("states")) == 1) {
        		tran = true;
        		fr.setStCode(1);
        		dao.acceptFrd(conn, fr);
        		message = "친구 수락이 완료되었습니다";
        	}else if(Integer.parseInt(this.req.getParameter("states")) == 3) {
        		tran = true;
        		fr.setStCode(3);
        		dao.updFrd(conn, fr);
        		message = "삭제가 완료되었습니다";
        	}else {
        		tran = false;
        		message = "새로고침 후 다시 시도해주세요";
        	}
           
        	
        }else {
        	if(Integer.parseInt(this.req.getParameter("states")) == -1) {
        		tran = true;
        		fr.setStCode(-1);
        		dao.insFrTable(conn, fr);
        		message = "친구 신청이 완료되었습니다";
        	}else if(Integer.parseInt(this.req.getParameter("states")) == -2){
        		tran = true;
        		fr.setStCode(-2);
        		dao.insFrTable(conn, fr);
        		message = "차단 신청이 완료되었습니다";
        	}else {
        		tran = false;
        		message = "새로고침 후 다시 시도해주세요";
        	}
        	
        }
        dao.setTransaction(conn, tran);
        dao.modifyTranStatus(conn, true);
        dao.closeConnection(conn);
		return message;
	}

	

	

	private String getReqFriendList() {
		String jsonData = null;
		Friends frd = new Friends();

		frd.setUserId(this.req.getParameter("nickName"));

		DataAccessObject dao = new DataAccessObject();
		Connection conn = dao.getConnection();
		jsonData = new Gson().toJson(dao.getReqFrdInfo(conn, frd));
		dao.closeConnection(conn);

		return jsonData;
	}

	private String getResFriendList() {
		String jsonData = null;
		Friends frd = new Friends();

		frd.setUserId(this.req.getParameter("nickName"));

		DataAccessObject dao = new DataAccessObject();
		Connection conn = dao.getConnection();
		jsonData = new Gson().toJson(dao.getResFrdInfo(conn, frd));
		dao.closeConnection(conn);

		return jsonData;
	}

	private String getBlkFriendList() {
		String jsonData = null;
		Friends frd = new Friends();

		frd.setUserId(this.req.getParameter("nickName"));

		DataAccessObject dao = new DataAccessObject();
		Connection conn = dao.getConnection();
		jsonData = new Gson().toJson(dao.getBlkFrdInfo(conn, frd));
		dao.closeConnection(conn);

		return jsonData;
	}
	private Action getRegFriendList() {
		Action action = new Action();
		boolean tran = false;
		this.session = req.getSession();


		MembersInfo mi = new MembersInfo();
		if(this.req.getParameter("nickName").equals(this.session.getAttribute("nickName"))) {
			if(this.req.getParameter("pcRoomName").equals(this.session.getAttribute("pcRoomName"))) {
				mi.setNickName(this.req.getParameter("nickName"));
				mi.setMemPcRoomName(this.req.getParameter("pcRoomName"));
				tran = true;
			}
		}


		action.setPage(tran ? "mainOne.jsp" : "mainFriend.jsp");
		action.setRedirect(tran ? false : true);



		return action;
	}
	private Action getFriendList1() {
		Action action = new Action();
		boolean tran = false;
		this.session = req.getSession();


		MembersInfo mi = new MembersInfo();
		if(this.req.getParameter("nickName").equals(this.session.getAttribute("nickName"))) {
			if(this.req.getParameter("pcRoomName").equals(this.session.getAttribute("pcRoomName"))) {
				mi.setNickName(this.req.getParameter("nickName"));
				mi.setMemPcRoomName(this.req.getParameter("pcRoomName"));
				tran = true;
			}
		}


		action.setPage(tran ? "mainTwo.jsp" : "mainFriend.jsp");
		action.setRedirect(tran ? false : true);



		return action;
	}

	private String getFriendList() {
		String jsonData = null;
		Friends fr = new Friends();

		fr.setUserId(this.req.getParameter("nickName"));

		DataAccessObject dao =new DataAccessObject();
		Connection conn = dao.getConnection();

		jsonData = new Gson().toJson(dao.getFriendList(conn, fr));	

		
		dao.closeConnection(conn);

		return jsonData;
	}

	/*User Table에서 ID찾기 */
	   private String getFrIdCheck() {
	      String data = null;

	      Friends fr = new Friends();
	      this.session = this.req.getSession();
	      System.out.println(this.session.getAttribute("nickName"));
	      System.out.println(this.req.getParameter("userId"));
	      fr.setReqUserId((String)this.session.getAttribute("nickName"));
	      fr.setUserId(this.req.getParameter("userId"));

	      DataAccessObject dao = new DataAccessObject();
	      Connection conn = dao.getConnection();
	      
	      if ((!dao.idCheckInfo1(conn, fr))) {
	         if(dao.idCheckInfo2(conn, fr)) {
	            data = fr.getUserId();
	         }else {
	            data = "<잘못된 유저이름입니다>";
	         }
	      }else {
	         data = "<잘못된 유저이름입니다>";
	      }
	      dao.closeConnection(conn);
	      return data;
	   }

}
