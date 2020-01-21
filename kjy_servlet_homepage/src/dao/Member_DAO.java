package dao;

import java.rmi.RemoteException;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import common.CommonUtil;
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
					" from a20_track2_sv_member "+
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
	public int insertMember(String id, String pw, String name, String birth, String area, String address, 
			String telecom, String phone_1, String phone_2, String phone_3, String email_1, String email_2, 
			String reg_date, String update_date, String status) {
		String query = "insert into a20_track2_sv_member(id, pw, name, birth, area, address, telecom, "+
					" phone_1, phone_2, phone_3, email_1, email_2, "+
					" reg_date, update_date, status) "+
					" values('"+id+"','"+pw+"','"+name+"','"+birth+"','"+area+"','"+address+"','"+telecom+"', "+
					" '"+phone_1+"','"+phone_2+"','"+phone_3+"','"+email_1+"','"+email_2+"', "+
					" '"+reg_date+"','"+update_date+"','y') ";
		//System.out.print(query);
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
		String query= "select name from a20_track2_sv_member "+
					  " where id = '"+id+"' "+
					  " and pw = '"+pw+"' "+
					  " and status = 'y' ";

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
	// 전체 회원
	public int getMemberCount(){
		String query = " select count(*) from a20_track2_web_member ";
		int result = 0;
		try{
			con = common.getConnection();
			ps  = con.prepareStatement(query);
			rs  = ps.executeQuery();
			while(rs.next()){
				result = rs.getInt(1);
			}
		} catch(RemoteException me) {
			System.out.println(" RemoteException getMemberCount(): "+me.getMessage());
		} catch(SQLException se) {
			System.out.println(" SQLException getMemberCount(): "+se.getMessage());
		} catch(Exception e) {
			System.out.println(" 오류 : D_2_DAO.getMemberCount() ");
		} finally {
			try{
				common.close(con, ps, rs);
			} catch(Exception e) {
				System.out.println(" getMemberCount close Exception "+e.getMessage());
			}
		}				
		return result;
	}
	// 회원 목록 조회
	public ArrayList<Member_DTO> getMemberList(String selValue, String txtValue, String areaValue, String checkValue){
		ArrayList<Member_DTO> arrM = new ArrayList<Member_DTO>();
		String query ="";
		query = " select id, name, birth, area, address, telecom, phone_1, " + 
				" phone_2, phone_3, email_1, email_2, "+
				" to_char(reg_date, 'yyyy-mm-dd'), decode(status,'n','탈퇴','회원') "+
				" from a20_track2_sv_member ";
					
		if(selValue.equals("id")||selValue.equals("name")) {
			query += " where "+selValue+" like '%"+txtValue+"%' ";
						
			
			if(!areaValue.equals("all")) {
				query += " and area like '%"+areaValue+"%' "+
						" order by "+checkValue+" ";
			}
		} else {
			query += " where area like '%"+areaValue+"%' "+
					" order by "+checkValue+" ";	
					
		}
		try{
			con = common.getConnection();
			ps  = con.prepareStatement(query);
			rs  = ps.executeQuery();
			while(rs.next()){
				String id 		= rs.getString(1);
				String name 	= rs.getString(2);
				String birth    = rs.getString(3);
				String area 	= rs.getString(4);
				String address  = rs.getString(5);
				String telecom  = rs.getString(6);
				String phone_1  = rs.getString(7);
				String phone_2  = rs.getString(8);
				String phone_3  = rs.getString(9);
				String email_1  = rs.getString(10);
				String email_2  = rs.getString(11);				
				String reg_date = rs.getString(12);
				String status   = rs.getString(13);
				
				Member_DTO dto = new Member_DTO(id, name, birth, area, address, telecom, phone_1,
						phone_2, phone_3, email_1, email_2, reg_date, status);
				arrM.add(dto);
				
			}
		} catch(RemoteException me) {
			System.out.println(" RemoteException getMemberList(): "+me.getMessage());
		} catch(SQLException se) {
			System.out.println(" SQLException getMemberList(): "+se.getMessage());
		} catch(Exception e) {
			System.out.println(" 오류 : D_2_DAO.getMemberList() ");
		} finally {
			try{
				common.close(con, ps, rs);
			} catch(Exception e) {
				System.out.println(" getMemberList close Exception "+e.getMessage());
			}
		}				
		
		return arrM;
	}
	//회원정보조회
	public Member_DTO getMemberinfo(String id){
		String query =  " select id, name, birth, area, address, telecom, phone_1, phone_2, phone_3, email_1, email_2, "+
						" to_char(reg_date, 'yyyy-mm-dd'), status "+
						" from a20_track2_sv_member "+
						" where id = '"+id+"' ";		
		Member_DTO dtoM = null;				
		try{
			con = common.getConnection();
			ps  = con.prepareStatement(query);
			rs  = ps.executeQuery();
			while(rs.next()){
				String t_id 	= rs.getString(1);
				String name 	= rs.getString(2);
				String birth    = rs.getString(3);
				String area 	= rs.getString(4);
				String address  = rs.getString(5);
				String telecom  = rs.getString(6);
				String phone_1  = rs.getString(7);
				String phone_2  = rs.getString(8);
				String phone_3  = rs.getString(9);
				String email_1  = rs.getString(10);
				String email_2  = rs.getString(11);				
				String reg_date = rs.getString(12);
				String status   = rs.getString(13);			
				dtoM = new Member_DTO(t_id, name, birth, area, address, telecom, phone_1,
						phone_2, phone_3, email_1, email_2, reg_date, status);
			}
		} catch(RemoteException me) {
			System.out.println(" RemoteException getMemberinfo(): "+me.getMessage());
		} catch(SQLException se) {
			System.out.println(" SQLException getMemberinfo(): "+se.getMessage());
		} catch(Exception e) {
			System.out.println(" 오류 : D_2_DAO.getMemberinfo() ");
		} finally {
			try{
				common.close(con, ps, rs);
			} catch(Exception e) {
				System.out.println(" getMemberinfo close Exception "+e.getMessage());
			}
		}				
		
		return dtoM;
	}
	public int getIdPwCheck(String id, String pw) {
		int result = 0;
		String pw_enc = "";
		try {
			pw_enc = CommonUtil.encryptSHA256(pw);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		String query =" select count(*) "+
				" from a20_track2_sv_member "+
				" where id = '"+id+"' "+
				" and pw = '"+pw_enc+"' ";

		try{
			con = common.getConnection();
			ps  = con.prepareStatement(query);
			rs  = ps.executeQuery();
			while(rs.next()){
				result = rs.getInt(1);
			}
		} catch(RemoteException me) {
			System.out.println(" RemoteException getLikeCount(): "+me.getMessage());
		} catch(SQLException se) {
			System.out.println(" SQLException getLikeCount(): "+se.getMessage());
		} catch(Exception e) {
			System.out.println(" 오류 : D_2_DAO.getLikeCount() ");
		} finally {
			try{
				common.close(con, ps, rs);
			} catch(Exception e) {
				System.out.println(" getLikeCount close Exception "+e.getMessage());
			}
		}						
		return result;
	}
}
