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
import dto.Faq_DTO;
import dto.News_DTO;

@WebServlet("/FaqUpdateForm")
public class FaqUpdateForm extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public FaqUpdateForm() {
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
		Faq_DTO dto = dao.getFaqView(faq_no);
		
		request.setAttribute("dtoF", dto);
		rdp = request.getRequestDispatcher("/faq/faq_u.jsp");
		}
		rdp.forward(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}