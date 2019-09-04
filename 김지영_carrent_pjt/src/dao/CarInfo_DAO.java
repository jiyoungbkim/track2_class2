package dao;

import java.rmi.RemoteException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;

import common.CommonUtil;
import common.DBConnectionOracle;
import dto.CarInfo_DTO;
import dto.Member_DTO;


public class CarInfo_DAO {
	DBConnectionOracle common = new DBConnectionOracle();
	Connection con = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
	
	public String getMaxId() {
		String query = " select max(car_id) from a20_track2_carinfo ";
		String result = "";
		try{
			con = common.getConnection();
			ps  = con.prepareStatement(query);
			rs  = ps.executeQuery();
			while(rs.next()){
				result = rs.getString(1);
			}
		} catch(RemoteException me) {
			System.out.println(" RemoteException getMaxId(): "+me.getMessage());
		} catch(SQLException se) {
			System.out.println(" SQLException getMaxId(): "+se.getMessage());
		} catch(Exception e) {
			System.out.println(" 오류 : D_2_DAO.getMaxId() ");
		} finally {
			try{
				common.close(con, ps, rs);
			} catch(Exception e) {
				System.out.println(" getMaxId close Exception "+e.getMessage());
			}
		}
		return result;
	}
	
	public String getMaxCarId() {
		String carMaxId = getMaxId(); // 같은 클래스 안에 있기 때문에 생성할 필요 없음
		//CommonUtil cu = new CommonUtil(); // static이라서 불러올 필요없음
		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);
		
		String nowYear = Integer.toString(year).substring(2,4);
		
		if(carMaxId == null) {
			carMaxId = nowYear+"_"+"001";
		} else {
			String checkYear = carMaxId.substring(0,2);
//			nowYear = "21";
//			int num = 0;
//			if(Integer.parseInt(nowYear) > Integer.parseInt(checkYear)) {
//				checkYear = nowYear;
//				num = 1;
//			} else {
//				String id = carMaxId.substring(3,6);
//				//System.out.println(checkYear);
//				num = Integer.parseInt(id);
//				num += 1;
//			}
//			String res = Integer.toString(num);
//			
//			String r = cu.getLPad(res, 3, "0");
//			
//			//System.out.println(r);
//			carMaxId = checkYear+"_"+r;
//			//carMaxId = nowYear+"_001";
//			System.out.println(carMaxId);
			
			int y = 0;
			if(nowYear.equals(checkYear)) {
				y = Integer.parseInt(carMaxId.substring(3));
				y++;
				String r = CommonUtil.getLPad(Integer.toString(y), 3, "0");
				carMaxId = checkYear+"_"+r;
			} else {
				carMaxId = nowYear + "_001";
			}
			//System.out.println(carMaxId);
			
		}
		return carMaxId;
	}

	public ArrayList<CarInfo_DTO> getCarList(String gubun, String search) {
		ArrayList<CarInfo_DTO> arrC = new ArrayList<CarInfo_DTO>();
		String query = "select car_id, car_name, produce, produce_ym, driving_total_km, decode(status,'y','[렌트가능]','[렌트불가]')  "+ 
					"from a20_track2_carinfo " ; 
		if(gubun.equals("car_name")) query += "where car_name like '%"+search+"%'";
		else if(gubun.equals("produce")) query += "where produce = '"+search+"'";
		else if(gubun.equals("car_id")) query +="where car_id = '"+search+"'";
		else if(gubun.equals("status")) query +="where status = '"+search+"'";
		query += " order by car_id desc";
		//System.out.println(query);		
		CarInfo_DTO dto = null;
		try {
			con = common.getConnection();
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			while(rs.next()) {
				String car_id = rs.getString(1);
				String car_name = rs.getString(2);
				String produce = rs.getString(3);
				String produce_ym = rs.getString(4);
				
				int driving_total_km = rs.getInt(5);
				String status = rs.getString(6);
				
				dto = new CarInfo_DTO(car_id,car_name,produce,produce_ym,driving_total_km,status);
				
				arrC.add(dto);
			}
		} catch(RemoteException re) {
			System.out.println("RemoteException getCarList"+re.getMessage());
		} catch(SQLException se) {
			System.out.println("SQLException getCarList"+se.getMessage());
		} catch(Exception e) {
			System.out.println("Exception getCarList"+e.getMessage());
		} finally {
			try {
				common.close(con, ps, rs);
			} catch(Exception e) {
				System.out.println("getCarList() close"+e.getMessage());
			}
		}
		return arrC;
	}
	
	
	public CarInfo_DTO getCarInfo(String id) {
		String query = "select car_id, car_name, produce, produce_ym, driving_total_km, decode(status,'y','[렌트가능]','[렌트불가]')  "+ 
				" from a20_track2_carinfo "+
				" where car_id = '"+id+"' "+
				" order by car_id desc";
		CarInfo_DTO dto = null;
		try {
			con = common.getConnection();
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			while(rs.next()) {
				String car_id = rs.getString(1);
				String car_name = rs.getString(2);
				String produce = rs.getString(3);
				String produce_ym = rs.getString(4);
				int driving_total_km = rs.getInt(5);
				String status = rs.getString(6);
				
				dto = new CarInfo_DTO(car_id,car_name,produce,produce_ym,driving_total_km,status);
				
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

	public int insertCarInfo(CarInfo_DTO dtoC) {
		String query = " insert into A20_TRACK2_CARINFO(car_id,car_name,produce,produce_ym,status,driving_total_km)\r\n" + 
				" values ('"+dtoC.getCar_id()+"','"+dtoC.getCar_name()+"','"+dtoC.getProduce()+"', "+
				" '"+dtoC.getProduce_ym()+"','"+dtoC.getStatus()+"',"+dtoC.getDriving_total_km()+") ";
		int result = 0;
		//System.out.println("==========1111111111111=====result "+query);
		try{
			con = common.getConnection();
			ps  = con.prepareStatement(query);
			result  = ps.executeUpdate();
			//System.out.println("===============result"+result);
		
		} catch(SQLException se) {
			System.out.println(" SQLException insertCarInfo: "+se.getMessage());
		} catch(Exception e) {
			System.out.println(" 오류 : D_2_DAO.insertCarInfo() ");
		} finally {
			try{
				common.close(con, ps);
			} catch(Exception e) {
				System.out.println(" insertCarInfo close Exception "+e.getMessage());
			}
		}
		return result;
	}

	public int updateCarInfo(String car_id, String car_name, String produce_name, String produce_ym) {
		String query = "update a20_track2_carinfo\r\n" + 
				"set car_name = '"+car_name+"', produce = '"+produce_name+"',\r\n" + 
				"produce_ym = '"+produce_ym+"' "+ 
				"where car_id = '"+car_id+"'";
		int result = 0;
		try{
			con = common.getConnection();
			ps  = con.prepareStatement(query);
			result  = ps.executeUpdate();

		} catch(RemoteException me) {
			System.out.println(" RemoteException updateCarInfo: "+me.getMessage());
		} catch(SQLException se) {
			System.out.println(" SQLException updateCarInfo: "+se.getMessage());
		} catch(Exception e) {
			System.out.println(" 오류 : D_2_DAO.updateCarInfo() ");
		} finally {
			try{
				common.close(con, ps);
			} catch(Exception e) {
				System.out.println(" updateCarInfo close Exception "+e.getMessage());
			}
		}
		return result;
	}

	public int deleteCarInfo(String car_id) {
		String query = " delete from a20_track2_carinfo " + 
				"where car_id = '"+car_id+"' ";
		int result = 0;
		try{
			con = common.getConnection();
			ps  = con.prepareStatement(query);
			result  = ps.executeUpdate();

		} catch(RemoteException me) {
			System.out.println(" RemoteException deleteCarInfo: "+me.getMessage());
		} catch(SQLException se) {
			System.out.println(" SQLException deleteCarInfo: "+se.getMessage());
		} catch(Exception e) {
			System.out.println(" 오류 : D_2_DAO.deleteCarInfo() ");
		} finally {
			try{
				common.close(con, ps);
			} catch(Exception e) {
				System.out.println(" deleteCarInfo close Exception "+e.getMessage());
			}
		}
		return result;
	}
	
}
