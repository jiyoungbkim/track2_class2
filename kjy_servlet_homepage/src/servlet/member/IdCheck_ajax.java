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

@WebServlet("/IdCheck_ajax")
public class IdCheck_ajax extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public IdCheck_ajax() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		Member_DAO dao = new Member_DAO();
		String id = request.getParameter("t_id");
		String result = dao.getCheckcount(id);

		
		String msg = "";
		if(result.equals("0")) { 
			msg = "사용가능한 아이디입니다.";
		} else { 
			msg = "중복된 아이디입니다.";
		}
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		out.println(msg);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
