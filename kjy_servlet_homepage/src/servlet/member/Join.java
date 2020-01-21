package servlet.member;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.CommonUtil;
import dao.Member_DAO;

@WebServlet("/Join")
public class Join extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Join() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		Member_DAO dao = new Member_DAO();
		String id 		= request.getParameter("t_id");
		String pw_pre 	= request.getParameter("t_pw");
		String pw = "";
		try {
			pw = CommonUtil.encryptSHA256(pw_pre);
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String name 	= request.getParameter("name");
		String birth 	= request.getParameter("birth");
		String area 	= request.getParameter("area");
		String address 	= request.getParameter("address");
		String telecom 	= request.getParameter("telecom");
		String phone_1 	= request.getParameter("phone_1");
		String phone_2  = request.getParameter("phone_2");
		String phone_3  = request.getParameter("phone_3");
		String email_1  = request.getParameter("email_1");
		String email_2  = request.getParameter("email_2");
		String reg_date = CommonUtil.getToday();
		String update_date = "";
		String status = "y";
				
		int result = 0;
		result = dao.insertMember(id, pw, name, birth, area, address, telecom, phone_1, phone_2, phone_3,
				email_1, email_2, reg_date, update_date, status);
		String msg = "";
		String url = "";
		RequestDispatcher rdp = null;
		if(result != 0) {
			msg = "등록 성공";
			url = "/Index";
		} else {
			msg = "등록 실패";
			url = "/Index";			
		}		

		request.setAttribute("msg", msg);
		request.setAttribute("url", url);
		rdp = request.getRequestDispatcher("/common/view_messageAlert.jsp");
		
		rdp.forward(request, response);	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
