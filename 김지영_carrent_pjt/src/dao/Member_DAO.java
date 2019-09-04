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

	public ArrayList<Member_DTO> getMemberList(String gubun, String search) {
		ArrayList<Member_DTO> arrM = new ArrayList<Member_DTO>();
		String query = " select m.id, m.name, m.age, d.dept_name, r.rank_name, "+
				        " nvl(m.address,'-') address, to_char(m.reg_date, 'yyyy-MM-dd') "+
				        " from a20_track2_member m, "+
				        " a20_track2_dept_desc d, a20_track2_rank_desc r "+
				        " where m.dept_no = d.dept_no "+
				        " and m.rank_no = r.rank_no ";
		if(gubun.equals("name")) query += " and m.name like '%"+search+"%'";
		else if(gubun.equals("dept")) query += " and m.dept_no = '"+search+"'";
		query += " order by m.id desc ";
		Member_DTO dto = null;
		try {
			con = common.getConnection();
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			while(rs.next()) {
				dto = new Member_DTO();
				dto.setId(rs.getString(1));
				dto.setName(rs.getString(2));
				dto.setAge(rs.getInt(3));
				dto.setDept(rs.getString(4));
				dto.setRank(rs.getString(5));
				dto.setAddress(rs.getString(6));
				dto.setReg_date(rs.getString(7));
//				String id = rs.getString(1);
//				String name = rs.getString(2);
//				int age = rs.getInt(3);
//				String dept_name = rs.getString(4);
//				String rank_name = rs.getString(5);
//				String address = rs.getString(6);
//				String reg_date = rs.getString(7);
				
				//dto = new Member_DTO(id,name,age,dept_name,rank_name,address,reg_date);
				arrM.add(dto);
			}
		} catch(RemoteException re) {
			System.out.println("RemoteException getMemberList"+re.getMessage());
		} catch(SQLException se) {
			System.out.println("SQLException getMemberList"+se.getMessage());
		} catch(Exception e) {
			System.out.println("Exception getMemberList"+e.getMessage());
		} finally {
			try {
				common.close(con, ps, rs);
			} catch(Exception e) {
				System.out.println("getMemberList() close"+e.getMessage());
			}
		}
		return arrM;
	}
	public String getMaxId(){
		int maxId = 0;
		String query = " select max(id) from A20_TRACK2_MEMBER ";
		
		try{
			con = common.getConnection();
			ps  = con.prepareStatement(query);
			rs  = ps.executeQuery();
			while(rs.next()){
				maxId = rs.getInt(1);
			}
		} catch(RemoteException me) {
			System.out.println(" RemoteException getMaxId: "+me.getMessage());
		} catch(SQLException se) {
			System.out.println(" SQLException getMaxId: "+se.getMessage());
		} catch(Exception e) {
			System.out.println(" 오류 : D_2_DAO.getMaxId() ");
		} finally {
			try{
				common.close(con, ps, rs);
			} catch(Exception e) {
				System.out.println(" getMaxId close Exception "+e.getMessage());
			}
		}
		if(maxId == 0) maxId = 101;
		else maxId += 1;
		return Integer.toString(maxId);
	}
	public int insertMember(String id, String name, int age, String dept, String rank, String address,
			String reg_date) {
		String query = " insert into a20_track2_member(id,name,age,dept_no,rank_no,address,reg_date) " + 
				" values ('"+id+"','"+name+"',"+age+",'"+dept+"','"+rank+"','"+address+"','"+reg_date+"') ";
		int result = 0;
		try{
			con = common.getConnection();
			ps  = con.prepareStatement(query);
			result  = ps.executeUpdate();

		} catch(RemoteException me) {
			System.out.println(" RemoteException insertMember: "+me.getMessage());
		} catch(SQLException se) {
			System.out.println(" SQLException insertMember: "+se.getMessage());
		} catch(Exception e) {
			System.out.println(" 오류 : D_2_DAO.insertMember() ");
		} finally {
			try{
				common.close(con, ps);
			} catch(Exception e) {
				System.out.println(" insertMember close Exception "+e.getMessage());
			}
		}
		return result;
	}
	public int insertMember_DTO(Member_DTO dto) {
		String query = " insert into a20_track2_member(id,name,age,dept_no,rank_no,address,reg_date) " + 
				" values ('"+dto.getId()+"','"+dto.getName()+"',"+dto.getAge()+",'"+dto.getDept()+"', "+
				" '"+dto.getRank()+"','"+dto.getAddress()+"','"+dto.getReg_date()+"') ";
		int result = 0;
		try{
			con = common.getConnection();
			ps  = con.prepareStatement(query);
			result  = ps.executeUpdate();

		} catch(RemoteException me) {
			System.out.println(" RemoteException insertMember_DTO: "+me.getMessage());
		} catch(SQLException se) {
			System.out.println(" SQLException insertMember_DTO: "+se.getMessage());
		} catch(Exception e) {
			System.out.println(" 오류 : D_2_DAO.insertMember_DTO() ");
		} finally {
			try{
				common.close(con, ps);
			} catch(Exception e) {
				System.out.println(" insertMember_DTO close Exception "+e.getMessage());
			}
		}
		return result;
	}
	public int insertMember_2(String id, String name, int age, String dept, String rank, String address,
			String reg_date) {
		String query = " insert into a20_track2_member(id,name,age,dept_no,rank_no,address,reg_date) " + 
				" values (?,?,?,?,?,?,?) ";	
		int result = 0;
		try{
			con = common.getConnection();
			ps  = con.prepareStatement(query);
			ps.setString(1, id);
			ps.setString(2, name);
			ps.setInt(3, age);
			ps.setString(4, dept);
			ps.setString(5, rank);
			ps.setString(6, address);
			ps.setString(7, reg_date);
			
			result  = ps.executeUpdate();

		} catch(RemoteException me) {
			System.out.println(" RemoteException insertMember_2: "+me.getMessage());
		} catch(SQLException se) {
			System.out.println(" SQLException insertMember_2: "+se.getMessage());
		} catch(Exception e) {
			System.out.println(" 오류 : D_2_DAO.insertMember_2() ");
		} finally {
			try{
				common.close(con, ps);
			} catch(Exception e) {
				System.out.println(" insertMember_2 close Exception "+e.getMessage());
			}
		}
		return result;
	}
	public Member_DTO getMemberInfo(String id) {
		String query = " select m.id, m.name, m.age, d.dept_name, r.rank_name, "+
				        " nvl(m.address,'-') address, to_char(m.reg_date, 'yyyy-MM-dd') "+
				        " from a20_track2_member m, "+
				        " a20_track2_dept_desc d, a20_track2_rank_desc r "+
				        " where m.dept_no = d.dept_no "+
				        " and m.rank_no = r.rank_no "+
				        " and m.id ="+id+" "+
				        " order by m.id ";

		Member_DTO dto = null;
		try {
			con = common.getConnection();
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			while(rs.next()) {
				dto = new Member_DTO();
				dto.setId(rs.getString(1));
				dto.setName(rs.getString(2));
				dto.setAge(rs.getInt(3));
				dto.setDept(rs.getString(4));
				dto.setRank(rs.getString(5));
				dto.setAddress(rs.getString(6));
				dto.setReg_date(rs.getString(7));
//				String id = rs.getString(1);
//				String name = rs.getString(2);
//				int age = rs.getInt(3);
//				String dept_name = rs.getString(4);
//				String rank_name = rs.getString(5);
//				String address = rs.getString(6);
//				String reg_date = rs.getString(7);
				
				//dto = new Member_DTO(id,name,age,dept_name,rank_name,address,reg_date);
				
			}
		} catch(RemoteException re) {
			System.out.println("RemoteException getMemberInfo"+re.getMessage());
		} catch(SQLException se) {
			System.out.println("SQLException getMemberInfo"+se.getMessage());
		} catch(Exception e) {
			System.out.println("Exception getMemberInfo"+e.getMessage());
		} finally {
			try {
				common.close(con, ps, rs);
			} catch(Exception e) {
				System.out.println("getMemberInfo() close"+e.getMessage());
			}
		}
		return dto;
	}
	public int updateMember(String id, String name, int age, String dept, String rank, String address,
			String reg_date) {
		String query = "update a20_track2_member "+ 
				"set name = '"+name+"', age = "+age+", dept_no = '"+dept+"', rank_no = '"+rank+"', "+ 
				"address = '"+address+"', reg_date ='"+reg_date+"' " + 
				"where id = '"+id+"' ";
		int result = 0;
		try{
			con = common.getConnection();
			ps  = con.prepareStatement(query);
			result  = ps.executeUpdate();

		} catch(RemoteException me) {
			System.out.println(" RemoteException updateMember: "+me.getMessage());
		} catch(SQLException se) {
			System.out.println(" SQLException updateMember: "+se.getMessage());
		} catch(Exception e) {
			System.out.println(" 오류 : D_2_DAO.updateMember() ");
		} finally {
			try{
				common.close(con, ps);
			} catch(Exception e) {
				System.out.println(" updateMember close Exception "+e.getMessage());
			}
		}
		return result;
	}
	public int deleteMember(String id) {
		String query = " delete from a20_track2_member " + 
				"where id = '"+id+"' ";
		int result = 0;
		try{
			con = common.getConnection();
			ps  = con.prepareStatement(query);
			result  = ps.executeUpdate();

		} catch(RemoteException me) {
			System.out.println(" RemoteException deleteMember: "+me.getMessage());
		} catch(SQLException se) {
			System.out.println(" SQLException deleteMember: "+se.getMessage());
		} catch(Exception e) {
			System.out.println(" 오류 : D_2_DAO.deleteMember() ");
		} finally {
			try{
				common.close(con, ps);
			} catch(Exception e) {
				System.out.println(" deleteMember close Exception "+e.getMessage());
			}
		}
		return result;
	}
}
