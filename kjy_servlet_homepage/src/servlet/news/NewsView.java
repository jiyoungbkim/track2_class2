package servlet.news;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.News_DAO;
import dto.News_DTO;

@WebServlet("/NewsView")
public class NewsView extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public NewsView() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String news_no = request.getParameter("t_newsNo");
		News_DAO dao = new News_DAO();
		int wHit = dao.newsHit(news_no);
		request.setAttribute("t_hit", wHit);
		News_DTO dtoW = dao.getNewsView(news_no);
		request.setAttribute("dtoW", dtoW);
		
		RequestDispatcher rdp = request.getRequestDispatcher("news/news_v.jsp");
		rdp.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		doGet(request, response);
	}

}
