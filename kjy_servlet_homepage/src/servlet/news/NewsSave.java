package servlet.news;

import java.io.File;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;

import common.CommonUtil;
import dao.News_DAO;

@WebServlet("/NewsSave")
public class NewsSave extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public NewsSave() {
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
		
		News_DAO dao = new News_DAO();
		String news_no = dao.getNewsNo();
		
		int sizeLimit = 1024 * 1024 * 1;
		String file_dir = CommonUtil.file_dir_news;
		
		MultipartRequest mpr = new MultipartRequest(request,file_dir,sizeLimit,"UTF-8");
		
		String fileName = mpr.getFilesystemName("fileName_a");
		
		//out.print(fileName);
		
		String saveFileName = "";
		if(fileName != null) {
			File oldFile = new File(file_dir,fileName);
			File newFile = new File(file_dir,news_no+"-"+fileName);
		
			oldFile.renameTo(newFile);
			saveFileName = newFile.getName();
		}
		
		
		String title 	= mpr.getParameter("title");
		String content 	= mpr.getParameter("contents");
		String reg_id = mpr.getParameter("writer");
		String reg_date = CommonUtil.getToday();		

		int result = dao.insertNews(news_no,title,content,reg_id,reg_date,saveFileName,0);

		String msg = "";
		String url = "";
		if(result != 0) {
			msg = "등록 성공";
			url = "/NewsList";
		} else {
			msg = "등록 실패";
			url = "/NewsList";			
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