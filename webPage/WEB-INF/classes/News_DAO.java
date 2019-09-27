package dao;

import java.rmi.RemoteException;
import java.sql.*;
import java.util.*;
import common.DBConnectionOracle;
import dto.Notice_DTO;
import common.*;

public class News_DAO {
	DBConnectionOracle common = new DBConnectionOracle();
	Connection con = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
	
	public String getMaxNo(){
		String query = "select max(news_no) from a20_track2_web_news";
		String result = "";
		try{
			con = common.getConnection();
			ps  = con.prepareStatement(query);
			rs  = ps.executeQuery();
			while(rs.next()){
				result = rs.getString(1);
			}
		} catch(RemoteException me) {
			System.out.println(" RemoteException getMaxNo(): "+me.getMessage());
		} catch(SQLException se) {
			System.out.println(" SQLException getMaxNo(): "+se.getMessage());
		} catch(Exception e) {
			System.out.println(" 오류 : D_2_DAO.getMaxNo() ");
		} finally {
			try{
				common.close(con, ps, rs);
			} catch(Exception e) {
				System.out.println(" getMaxNo close Exception "+e.getMessage());
			}
		}
		return result;
	}
	public String getNewsNo(){
		String newsNo = getMaxNo();
		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);
		
		String nowYear = Integer.toString(year).substring(2,4);
		
		if(newsNo == null) {
			newsNo = nowYear+"_0001";
		} else {
			String checkYear = newsNo.substring(0,2);
			
			int y =0;
			if(nowYear.equals(checkYear)) {
				y = Integer.parseInt(newsNo.substring(3));
				y++;
				String r = CommonUtil.getLPad(Integer.toString(y), 4, "0");
				newsNo = checkYear+"_"+r;
			} else {
				newsNo = nowYear + "_0001";
			}
		}
		return newsNo;
	}
	public int insertNews(String news_no, String title, String content, String reg_id, String reg_date) {
		String query = " insert into a20_track2_web_news(news_no, title, content, reg_id, reg_date) "+
						" values ('"+news_no+"','"+title+"','"+content+"','"+reg_id+"','"+reg_date+"')";
		int result = 0;
		try {
			con = common.getConnection();
			ps = con.prepareStatement(query);
			result = ps.executeUpdate();
		} catch (RemoteException me){
			System.out.println(" RemoteException insertNews(): " + me.getMessage());
		} catch (SQLException se){
			System.out.println(" SQLException insertNews(): " + se.getMessage());
		} catch (Exception e) {
			System.out.println(" 오류 : D_2_DAO.insertNews() ");
		} finally {
			try {
				common.close(con, ps);
			} catch (Exception e) {
				System.out.println(" insertNews close Exception " + e.getMessage());
			}
		}
		return result;
	}
	
}