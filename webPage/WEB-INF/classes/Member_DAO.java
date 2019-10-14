package dao;

import java.rmi.RemoteException;
import java.sql.*;
import java.util.ArrayList;
import common.DBConnectionOracle;
import dto.Member_DTO;

public class Member_DAO {
	DBConnectionOracle common = new DBConnectionOracle();
	Connection con = null;
	PreparedStatement ps = null;
	ResultSet rs = null;

	// 중복 검사
	public String getCheckcount(String id){
		String query=" select count(*) "+
					" from a20_track2_web_member "+
					" where id='"+id+"'";
		String result = "";
		try {
			con = common.getConnection();
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			while(rs.next()){
				result = rs.getString(1);
			}
		} catch (RemoteException me){
			System.out.println("RemoteException getCheckcount(): "+me.getMessage());
		} catch (SQLException se){
			System.out.println("SQLException getCheckcount(): "+se.getMessage());
		} catch (Exception e){
			System.out.println("Exception getCheckcount(): "+e.getMessage());
		} finally {
			try{
				common.close(con, ps, rs);
			}catch(Exception e){
				System.out.println("getCheckcount() close Exception: "+e.getMessage());
			}
		}
		return result;
	}
	//회원가입
	public int insertMember(String query) {
		int result = 0;
		try {
			con = common.getConnection();
			ps = con.prepareStatement(query);
			result = ps.executeUpdate();
		} catch (RemoteException me){
			System.out.println("RemoteException insertMember(): "+me.getMessage());
		} catch (SQLException se){
			System.out.println("SQLException insertMember(): "+se.getMessage());
		} catch (Exception e){
			System.out.println("Exception insertMember(): "+e.getMessage());
		} finally {
			try{
				common.close(con, ps);
			}catch(Exception e){
				System.out.println("insertMember() close Exception: "+e.getMessage());
			}
		}
		return result;
	}
	
	public int insertMember(String id, String pw, String name, String birth, String area, String address, 
			String telecom, String phone_1, String phone_2, String phone_3, String email_1, String email_2, 
			String att_desk, String att_note, String att_print, String att_beam, String reg_date, String update_date, String status) {
		String query = "insert into a20_track2_web_member(id, pw, name, birth, area, address, telecom, "+
					" phone_1, phone_2, phone_3, email_1, email_2, att_desk, att_note, att_print, att_beam, "+
					" reg_date, update_date, status) "+
					" values('"+id+"','"+pw+"','"+name+"','"+birth+"','"+area+"','"+address+"','"+telecom+"', "+
					" '"+phone_1+"','"+phone_2+"','"+phone_3+"','"+email_1+"','"+email_2+"', "+
					" '"+att_desk+"','"+att_note+"','"+att_print+"','"+att_beam+"','"+reg_date+"','"+update_date+"','y') ";
		System.out.print(query);
		int result = 0;
		try {
			con = common.getConnection();
			ps = con.prepareStatement(query);
			result = ps.executeUpdate();
		} catch (RemoteException me){
			System.out.println("RemoteException insertMember(): "+me.getMessage());
		} catch (SQLException se){
			System.out.println("SQLException insertMember(): "+se.getMessage());
		} catch (Exception e){
			System.out.println("Exception insertMember(): "+e.getMessage());
		} finally {
			try{
				common.close(con, ps);
			}catch(Exception e){
				System.out.println("insertMember() close Exception: "+e.getMessage());
			}
		}
		return result;
	}
	//로그인
	public String checkLogin(String id, String pw){
		String query="select name from a20_track2_web_member "+
					" where id = '"+id+"' "+
					" and pw = '"+pw+"' "+
					" and status = 'y'";
		String result = null;
		try {
			con = common.getConnection();
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			while(rs.next()){
				result = rs.getString(1);
			}
		} catch (RemoteException me){
			System.out.println("RemoteException checkLogin(): "+me.getMessage());
		} catch (SQLException se){
			System.out.println("SQLException checkLogin(): "+se.getMessage());
		} catch (Exception e){
			System.out.println("Exception checkLogin(): "+e.getMessage());
		} finally {
			try{
				common.close(con, ps, rs);
			}catch(Exception e){
				System.out.println("checkLogin() close Exception: "+e.getMessage());
			}
		}
		return result;
	}
}
