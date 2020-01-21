package servlet.member;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.Notice_DAO;
import dto.Notice_DTO;

@WebServlet("/IndexR")
public class IndexR extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public IndexR() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Notice_DAO dao = new Notice_DAO();
		
		ArrayList<Notice_DTO> arrN = dao.getNoticeIndex();
		request.setAttribute("t_arrN", arrN);
		RequestDispatcher rdp = request.getRequestDispatcher("/index/index.jsp");
		rdp.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
