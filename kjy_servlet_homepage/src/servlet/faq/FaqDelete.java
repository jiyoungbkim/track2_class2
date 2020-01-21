package servlet.faq;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.Faq_DAO;

@WebServlet("/FaqDelete")
public class FaqDelete extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public FaqDelete() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession(true);
		String session_level = (String)session.getAttribute("session_level");
		RequestDispatcher rdp = null;
		if(session_level == null) {
			String msg = "세션시간이 만료되었습니다.";
			String url = "/index/index.jsp";
			request.setAttribute("msg", msg);
			request.setAttribute("url", url);
			rdp = request.getRequestDispatcher("/common/view_messageAlert.jsp");
			
		} else {
		
		Faq_DAO dao = new Faq_DAO();
		
		String faq_no = request.getParameter("t_faq_no");

		int result = dao.deleteFaq(faq_no);
		String msg = "";
		String url = "";
		if(result != 0) {
			msg = "삭제 성공";
			url = "/FaqList";
		} else {
			msg = "삭제 실패";
			url = "/FaqList";			
		}		
		request.setAttribute("msg", msg);
		request.setAttribute("url", url);
		rdp = request.getRequestDispatcher("/common/view_messageAlert.jsp");
		}
		rdp.forward(request, response);	
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}