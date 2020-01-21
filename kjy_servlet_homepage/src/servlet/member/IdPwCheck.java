package servlet.member;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.Member_DAO;

@WebServlet("/IdPwCheck")
public class IdPwCheck extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public IdPwCheck() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		Member_DAO dao = new Member_DAO();
		String id = request.getParameter("t_id");
		String pw = request.getParameter("t_pw");
		//String result = dao.getCheckcount(id);
		
		int result2 = dao.getIdPwCheck(id, pw);
		String msg = "";
		if(result2 == 0) {
			msg = "id 또는 비밀번호가 다릅니다.";
		}
		//request.setAttribute("s_id", id);
		//request.setAttribute("result", result);
		
		
		/*
		 * String msg = ""; if(result.equals("0")) { msg = "사용가능한 아이디입니다."; } else { msg
		 * = "중복된 아이디입니다."; }
		 */
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		out.println(msg);
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
