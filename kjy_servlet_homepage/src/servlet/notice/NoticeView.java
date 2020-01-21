package servlet.notice;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.Notice_DAO;
import dto.Notice_DTO;


@WebServlet("/NoticeView")
public class NoticeView extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public NoticeView() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String notice_no = request.getParameter("t_noticeNo");
		Notice_DAO dao = new Notice_DAO();
		int nHit = dao.noticeHit(notice_no);
		request.setAttribute("t_hit", nHit);
		Notice_DTO dtoN = dao.getNoticeView(notice_no);
		request.setAttribute("dtoN", dtoN);
		
		RequestDispatcher rdp = request.getRequestDispatcher("/notice/notice_v.jsp");
		rdp.forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
