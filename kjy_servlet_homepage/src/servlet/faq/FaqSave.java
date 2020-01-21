package servlet.faq;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import common.CommonUtil;
import dao.Faq_DAO;

@WebServlet("/FaqSave")
public class FaqSave extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public FaqSave() {
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
		String faq_no = dao.getFaqNo();
				
		String title = request.getParameter("title");
		String content 	= request.getParameter("contents");
		String reg_id 	= request.getParameter("writer");
		String reg_date = CommonUtil.getToday();		

		int result = dao.insertFaq(faq_no,title,content,reg_id,reg_date);

		String msg = "";
		String url = "";
		if(result != 0) {
			msg = "등록 성공";
			url = "/FaqList";
		} else {
			msg = "등록 실패";
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