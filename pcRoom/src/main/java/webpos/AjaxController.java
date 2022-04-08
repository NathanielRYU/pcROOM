package webpos;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import bean.Action;
import friends.FriendsManagement;
import services.auth.Authentication;



@WebServlet({"/FriendInfo","/FrId","/ReqFriend","/ResFriend","/BlkFriend","/AccFr","/RefFr" , "/AddReq"})
public class AjaxController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AjaxController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doAjax(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		this.doAjax(request, response);
	}

	private void doAjax(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String ajaxData = null;
		String jobCode = (req.getRequestURI().substring(req.getContextPath().length()+1));
		HttpSession session = req.getSession();
		FriendsManagement fr = null;
		System.out.println(jobCode);
 
		if (session.getAttribute("nickName") != null) {
			if(jobCode.equals("FriendInfo")){
				fr = new FriendsManagement(req);
				ajaxData = fr.backController("1");
			}else if(jobCode.equals("ReqFriend")){
				fr = new FriendsManagement(req);
				ajaxData = fr.backController("2");
			}else if(jobCode.equals("ResFriend")){
				fr = new FriendsManagement(req);
				ajaxData = fr.backController("3");
			}else if(jobCode.equals("BlkFriend")){
				fr = new FriendsManagement(req);
				ajaxData = fr.backController("4");
			}else if(jobCode.equals("FrId")){
				fr = new FriendsManagement(req);
				ajaxData = fr.backController("5");
			}else if(jobCode.equals("AccFr")) {
				fr = new FriendsManagement(req);
				ajaxData = fr.backController("6");
			}else if(jobCode.equals("RefFr")) {
				fr = new FriendsManagement(req);
				ajaxData = fr.backController("7");
			}else if(jobCode.equals("AddReq")) {
				fr = new FriendsManagement(req);
				ajaxData = fr.backController("8");
			}	
			else {

			}

			res.setContentType("text/html; charset=utf-8");
			PrintWriter p = res.getWriter();
			
			p.write(ajaxData);
		}

	}
	}