package servlet.notice;

import java.io.File;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import common.CommonUtil;
import dao.Notice_DAO;

/**
 * Servlet implementation class NoticeDelete
 */
@WebServlet("/NoticeDelete")
public class NoticeDelete extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public NoticeDelete() {
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
			String fileName = request.getParameter("t_fileName");
			String file_dir = CommonUtil.file_dir_notice;
			if(fileName != null) {
				File dFa = new File(file_dir, fileName);
				dFa.delete();
			}
			int result = dao.deleteNotice(notice_no);
			String msg = "";
			String url = "";
			if(result != 0) {
				msg = "삭제 성공";
				url = "/NoticeList";
			} else {
				msg = "삭제 실패";
				url = "/NoticeList";			
			}		
			//response.sendRedirect("/NoticeList");
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
