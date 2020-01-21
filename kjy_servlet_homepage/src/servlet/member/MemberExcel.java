package servlet.member;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.CommonUtil;
import dao.Member_DAO;
import dto.Member_DTO;

@WebServlet("/MemberExcel")
public class MemberExcel extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public MemberExcel() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");	
		Member_DAO dao = new Member_DAO();	
		
		String selValue   = request.getParameter("t_sel");
		String txtValue   = CommonUtil.checkNull(request.getParameter("t_search"));
		String areaValue  = request.getParameter("t_area");
		String checkValue = request.getParameter("t_check");
		if(selValue == null){
			selValue  = "all";
			txtValue  = "";
			areaValue = "";
		}
		if(checkValue == null) {
			checkValue ="reg_date";
		}
		
		request.setAttribute("t_selValue", selValue);
		request.setAttribute("t_txtValue", txtValue);
		request.setAttribute("t_checkValue", checkValue);
		request.setAttribute("t_areaValue", areaValue);
		
		ArrayList<Member_DTO> arrM = dao.getMemberList(selValue,txtValue,areaValue,checkValue);			
		request.setAttribute("t_arrM", arrM);
		
		RequestDispatcher rdp = request.getRequestDispatcher("/common/excel_down_member.jsp");
		rdp.forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
