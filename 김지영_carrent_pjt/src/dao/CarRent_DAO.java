package dao;

import java.rmi.RemoteException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;

import common.CommonUtil;
import common.DBConnectionOracle;
import dto.CarRent_DTO;

public class CarRent_DAO {
	DBConnectionOracle common = new DBConnectionOracle();
	Connection con = null;
	PreparedStatement ps = null;
	ResultSet rs = null;

	public String getMaxId() {
		String query = " select max(rent_id) from a20_track2_carrent ";
		String result = "";
		try {
			con = common.getConnection();
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			while (rs.next()) {
				result = rs.getString(1);
			}
		} catch (RemoteException me) {
			System.out.println(" RemoteException getMaxId(): " + me.getMessage());
		} catch (SQLException se) {
			System.out.println(" SQLException getMaxId(): " + se.getMessage());
		} catch (Exception e) {
			System.out.println(" 오류 : D_2_DAO.getMaxId() ");
		} finally {
			try {
				common.close(con, ps, rs);
			} catch (Exception e) {
				System.out.println(" getMaxId close Exception " + e.getMessage());
			}
		}
		return result;
	}

	public String getMaxCarRentId() {

		String carRentMaxId = getMaxId();

		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH) + 1;

		String nowYear = Integer.toString(year).substring(2, 4);
		String nowMonth = CommonUtil.getLPad(Integer.toString(month), 2, "0");

		if (carRentMaxId == null) {
			carRentMaxId = nowYear + nowMonth + "_" + "0001";
		} else {
			String checkYear = carRentMaxId.substring(0, 2);
			String checkMonth = carRentMaxId.substring(2, 4);

			int y = 0;
			if (nowYear.equals(checkYear) && nowMonth.equals(checkMonth)) {
				y = Integer.parseInt(carRentMaxId.substring(5));
				y++;

				String res1 = CommonUtil.getLPad(Integer.toString(y), 4, "0");
				carRentMaxId = nowYear + nowMonth + "_" + res1;
			} else {
				carRentMaxId = nowYear + nowMonth + "_0001";
			}
		}
		return carRentMaxId;
	}

	public ArrayList<CarRent_DTO> getCarRentList(String gubun, String search) {
		ArrayList<CarRent_DTO> arrR = new ArrayList<CarRent_DTO>();
		String query = " select r.rent_id,i.car_name,m.name,nvl(r.driving_km,'0'),to_char(r.rent_start_date,'yyyy-MM-dd'),\r\n"
				+ "decode(r.rent_return_date,'','[미반납]','[반납]') as rent_return_date,i.status\r\n"
				+ "from a20_track2_carrent r, a20_track2_carinfo i, a20_track2_member m\r\n"
				+ "where r.car_id = i.car_id\r\n" + "and r.member_id = m.id ";
		if (gubun.equals("rent_id"))
			query += "and r.rent_id = '" + search + "'";
		else if (gubun.equals("status"))
			query += "and i.status = '" + search + "'";
		else if (gubun.equals("return_date")) query = String.valueOf(query) + "and rent_return_date is null ";
		query += " order by rent_id desc";
		//System.out.println(query);
		CarRent_DTO dto = null;
		try {
			con = common.getConnection();
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			while (rs.next()) {
				String rent_id = rs.getString(1);
				String car_name = rs.getString(2);
				String member_id = rs.getString(3);
				String driving_km = rs.getString(4);
				String rent_start_date = rs.getString(5);
				String rent_return_date = rs.getString(6);

				dto = new CarRent_DTO(rent_id, car_name, member_id, driving_km, rent_start_date, rent_return_date);
				arrR.add(dto);
			}
		} catch (RemoteException re) {
			System.out.println("RemoteException getCarRentList" + re.getMessage());
		} catch (SQLException se) {
			System.out.println("SQLException getCarRentList" + se.getMessage());
		} catch (Exception e) {
			System.out.println("Exception getCarRentList" + e.getMessage());
		} finally {
			try {
				common.close(con, ps, rs);
			} catch (Exception e) {
				System.out.println("getCarRentList() close" + e.getMessage());
			}
		}
		return arrR;
	}

	public CarRent_DTO getCarRentInfo(String id) {
		String query = "select r.rent_id,i.car_name,m.name,nvl(r.driving_km,'0'),to_char(r.rent_start_date,'yyyy-MM-dd'),\r\n" + 
				"decode(r.rent_return_date,'','[미반납]','[반납]') as rent_return_date,i.status\r\n" + 
				"from a20_track2_carrent r, a20_track2_carinfo i, a20_track2_member m\r\n" + 
				"where r.car_id = i.car_id\r\n" + 
				"and r.member_id = m.id "
				+ "and rent_id = '"+id+"' ";
		// System.out.println(query);
		CarRent_DTO dto = null;
		try {
			con = common.getConnection();
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			while (rs.next()) {
				String rent_id = rs.getString(1);
				String car_name = rs.getString(2);
				String member_id = rs.getString(3);
				String driving_km = rs.getString(4);
				String rent_start_date = rs.getString(5);
				String rent_return_date = rs.getString(6);

				dto = new CarRent_DTO(rent_id, car_name, member_id, driving_km, rent_start_date, rent_return_date);
			}
		} catch (RemoteException re) {
			System.out.println("RemoteException getCarList" + re.getMessage());
		} catch (SQLException se) {
			System.out.println("SQLException getCarList" + se.getMessage());
		} catch (Exception e) {
			System.out.println("Exception getCarList" + e.getMessage());
		} finally {
			try {
				common.close(con, ps, rs);
			} catch (Exception e) {
				System.out.println("getCarList() close" + e.getMessage());
			}
		}
		return dto;
	}

	public int insertCarRent(CarRent_DTO dtoR) {
		String query = "insert into a20_track2_carrent(rent_id,car_id,member_id,driving_km,rent_start_date,rent_return_date)\r\n"
				+ "values ('" + dtoR.getRent_id() + "','" + dtoR.getCar_id() + "','" + dtoR.getMember_id() + "','','"
				+ dtoR.getRent_start_date() + "','')";
		int result = 0;
		try {
			con = common.getConnection();
			ps = con.prepareStatement(query);
			result = ps.executeUpdate();

		} catch (RemoteException me) {
			System.out.println(" RemoteException insertCarRent(): " + me.getMessage());
		} catch (SQLException se) {
			System.out.println(" SQLException insertCarRent: " + se.getMessage());
		} catch (Exception e) {
			System.out.println(" 오류 : D_2_DAO.insertCarRent() ");
		} finally {
			try {
				common.close(con, ps);
			} catch (Exception e) {
				System.out.println(" insertCarRent close Exception " + e.getMessage());
			}
		}
		return result;
	}
	public int insertCarRent_commit(CarRent_DTO dtoR) {
	    int result = 0;
	    String query = "insert into a20_track2_carrent(rent_id,car_id,member_id,driving_km,rent_start_date,rent_return_date)\r\nvalues ('" + 
	      dtoR.getRent_id() + "','" + dtoR.getCar_id() + "','" + dtoR.getMember_id() + "','','" + 
	      dtoR.getRent_start_date() + "','')";
	    
	    String query2 = "update a20_track2_carinfo\r\nset status = 'n' where car_id = '" + 
	      dtoR.getCar_id() + "'";
	    try {
	      this.con = this.common.getConnection();

	      
	      this.con.setAutoCommit(false);
	      Statement stmt = this.con.createStatement();
	      stmt.executeUpdate(query);
	      stmt.executeUpdate(query2);
	      this.con.commit();
	    }
	    catch (RemoteException me) {
	      System.out.println(" RemoteException insertCarRent(): " + me.getMessage());
	    } catch (SQLException se) {
	      System.out.println(" SQLException insertCarRent: " + se.getMessage());
	      try {
	        this.con.rollback();
	      } catch (SQLException e) {
	        e.printStackTrace();
	      } 
	    } catch (Exception e) {
	      System.out.println(" ���� : D_2_DAO.insertCarRent() ");
	    } finally {
	      try {
	        this.con.setAutoCommit(true);
	        this.common.close(this.con, this.ps);
	      } catch (Exception e) {
	        System.out.println(" insertCarRent close Exception " + e.getMessage());
	      } 
	    } 
	    return result;
	  }

	public int updateCarRent(String car_id, String status) {

		String query = "update a20_track2_carinfo\r\n" + "set status = '" + status + "'\r\n" + "where car_id = '"
				+ car_id + "'";
		int result = 0;
		try {
			con = common.getConnection();
			ps = con.prepareStatement(query);
			result = ps.executeUpdate();

		} catch (RemoteException me) {
			System.out.println(" RemoteException updateCarRent(): " + me.getMessage());
		} catch (SQLException se) {
			System.out.println(" SQLException updateCarRent: " + se.getMessage());
		} catch (Exception e) {
			System.out.println(" 오류 : D_2_DAO.updateCarRent() ");
		} finally {
			try {
				common.close(con, ps);
			} catch (Exception e) {
				System.out.println(" updateCarRent close Exception " + e.getMessage());
			}
		}
		return result;
	}

	public int deleteCarRent(String rent_id) {
		String query = "delete from A20_TRACK2_CARRENT\r\n" + "where rent_id = '" + rent_id + "'";
		int result = 0;
		try {
			con = common.getConnection();
			ps = con.prepareStatement(query);
			result = ps.executeUpdate();

		} catch (RemoteException me) {
			System.out.println(" RemoteException deleteCarRent(): " + me.getMessage());
		} catch (SQLException se) {
			System.out.println(" SQLException deleteCarRent: " + se.getMessage());
		} catch (Exception e) {
			System.out.println(" 오류 : D_2_DAO.deleteCarRent() ");
		} finally {
			try {
				common.close(con, ps);
			} catch (Exception e) {
				System.out.println(" deleteCarRent close Exception " + e.getMessage());
			}
		}
		return result;
	}

}
