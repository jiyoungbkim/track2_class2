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

@WebServlet("/NewsUpdate")
public class NewsUpdate extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public NewsUpdate() {
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
			
			int sizeLimit = 1024 * 1024 * 1;
			String file_dir = CommonUtil.file_dir_news;
			
			MultipartRequest mpr = new MultipartRequest(request,file_dir,sizeLimit,"UTF-8");
			
			String news_no 		= mpr.getParameter("t_news_no");
			String title 		= mpr.getParameter("title");
			String content 		= mpr.getParameter("contents");
			String reg_id 		= mpr.getParameter("writer");
			String reg_date 	= CommonUtil.getToday();
			
			String saveFileName  = "";
			String fileName = mpr.getFilesystemName("fileName_a");
			String delFile = CommonUtil.checkNull(mpr.getParameter("checkbox_del_fileName"));
		if(!delFile.equals("")) {
			File dFa = new File(file_dir,delFile);
			dFa.delete();
		} else {
			saveFileName = mpr.getParameter("ori_fileName_a");
		}
		if(fileName != null) {
			String delFile_1 = mpr.getParameter("ori_fileName_a");
			if(delFile_1 != null) {
				File dFa = new File(file_dir,delFile_1);
				dFa.delete();
			}
			File oldFile = new File(file_dir,fileName);
			File newFile = new File(file_dir,news_no+"-"+fileName);
		
			oldFile.renameTo(newFile);
			saveFileName = newFile.getName();
		}
			int result = dao.updateNews(news_no,title,content,reg_id,reg_date,saveFileName);
			
			String msg = "";
			String url = "";
			if(result != 0) {
				msg = "수정 성공";
				url = "/NewsList";
			} else {
				msg = "수정 실패";
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
