package servlet.notice;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.Notice_DAO;
import dto.Notice_DTO;

@WebServlet("/NoticeUpdateForm")
public class NoticeUpdateForm extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public NoticeUpdateForm() {
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
		
		Notice_DAO dao = new Notice_DAO();
		String notice_no = request.getParameter("t_notice_no");
		System.out.println(notice_no);
		Notice_DTO dto = dao.getNoticeView(notice_no);
		
		request.setAttribute("dtoN", dto);
		rdp = request.getRequestDispatcher("/notice/notice_u.jsp");
		}
		rdp.forward(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}