package servlet.notice;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.CommonUtil;
import dao.Notice_DAO;
import dto.Notice_DTO;


@WebServlet("/NoticeList")
public class NoticeList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public NoticeList() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		Notice_DAO dao = new Notice_DAO();
		
		String selValue = request.getParameter("t_sel");
		String txtValue = request.getParameter("t_search");
		if(selValue == null){
			selValue ="title";
			txtValue ="";
		}
		request.setAttribute("t_sel", selValue);
		request.setAttribute("t_search", txtValue);
		
		ArrayList<Notice_DTO> arrN = dao.getNoticeList(selValue, txtValue);
		request.setAttribute("t_arrN", arrN);
		
		String notice_no = request.getParameter("t_noticeNo");
		int wHit = dao.noticeHit(notice_no);
		request.setAttribute("t_wHit", wHit);
		int nCount = dao.getCount();
		request.setAttribute("t_nCount", nCount);
		
		//*********page start***********/
		
		String r_page = CommonUtil.checkNull(request.getParameter("r_page"));		
		int			current_page;					// 현재페이지 번호
		int			n_page=0;
		int			p_page=0;
		int			total_page;						// 총페이지 수
		int			total_count;					// 총레코드 수
		int			for_count;						
		int			list_setup_count = 5;			// 한번에 출력될 List 수
		int			p_no;
		int			v_count;
		int			a_count;
		String		url				= null;	

		// 조회된 건수 구하기  total_count : 설정
		if(arrN == null) total_count =0;
		else total_count = arrN.size();

		// 페이지번호가 없으면 1페이지로 간주
		if(r_page.equals("")) current_page = 1;
		else current_page = Integer.parseInt(r_page);
			
		for_count		= list_setup_count;
		p_no			= list_setup_count;				// 페이지수가 10
		total_page = total_count / list_setup_count;		// 전체페이지수 계산 (if 23개 게시물이면 2)
	   
		if(current_page == 1) {
			v_count		= 0;
			a_count		= list_setup_count;
			for_count	= 0;
		} else{
			v_count		= 0;
			a_count		= p_no * current_page;
			for_count	= a_count - list_setup_count;
		}
		if(total_page * list_setup_count != total_count)   total_page = total_page +1;
		
		// 이전, 다음 페이지 
		n_page = current_page +1;
		p_page = current_page -1;
		//*********page end***********/
		
		request.setAttribute("total_page",  total_page);
		request.setAttribute("current_page", current_page);
		request.setAttribute("for_count", for_count);
		request.setAttribute("v_count", v_count);
		request.setAttribute("a_count", a_count);
		
		RequestDispatcher rdp = request.getRequestDispatcher("/notice/notice_r.jsp");
		rdp.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
