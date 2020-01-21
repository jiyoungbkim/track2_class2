package servlet.member;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import common.CommonUtil;
import dao.Member_DAO;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String id = request.getParameter("t_id");
		//String pw = request.getParameter("t_pw");
		String pw_pre 	= request.getParameter("t_pw");
		String pw = "";
		try {
			pw = CommonUtil.encryptSHA256(pw_pre);
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Member_DAO dao = new Member_DAO();
		String name = dao.checkLogin(id, pw);
		System.out.println("name===="+name);
		String msg = "";
		String url = "";
		if(name == null) {
			msg = "ID 또는 비밀번호 오류입니다.";
			url = "/LoginForm";
		} else {
			HttpSession session = request.getSession(true);
			session.setAttribute("session_name",name);
			session.setAttribute("session_id",id);
			if(id.equals("manager")){
				session.setAttribute("session_level","manager");
			}
			session.setMaxInactiveInterval(60*30*1);
			//session.setMaxInactiveInterval(5);
			
			msg = name+"님 환영합니다";
			url = "/IndexR";
		}
		request.setAttribute("msg", msg);
		request.setAttribute("url", url);
		RequestDispatcher rdp = request.getRequestDispatcher("/common/view_messageAlert.jsp");
		rdp.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
