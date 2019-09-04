package dao;

import common.DBConnectionOracle;
import dto.CarRent_DTO;
import java.rmi.RemoteException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CarReturn_DAO
{
  DBConnectionOracle common = new DBConnectionOracle();
  Connection con = null;
  PreparedStatement ps = null;
  ResultSet rs = null;
  
  public ArrayList<CarRent_DTO> getCarReturnList(String query) {
    ArrayList<CarRent_DTO> arrR = new ArrayList<CarRent_DTO>();
    
    CarRent_DTO dto = null;
    try {
      this.con = this.common.getConnection();
      this.ps = this.con.prepareStatement(query);
      this.rs = this.ps.executeQuery();
      while (this.rs.next()) {
        String rent_id = this.rs.getString(1);
        String car_id = this.rs.getString(2);
        String member_id = this.rs.getString(3);
        String driving_km = this.rs.getString(4);
        String rent_start_date = this.rs.getString(5);
        String rent_return_date = this.rs.getString(6);
        
        dto = new CarRent_DTO(rent_id, car_id, member_id, driving_km, rent_start_date, rent_return_date);
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
        this.common.close(this.con, this.ps, this.rs);
      } catch (Exception e) {
        System.out.println("getCarRentList() close" + e.getMessage());
      } 
    } 
    return arrR;
  }
  public String getDrivingTotalKm(String car_id) {
    String query = " select driving_total_km\r\nfrom a20_track2_carinfo\r\nwhere car_id = '" + 
      
      car_id + "' ";
    String result = "";
    try {
      this.con = this.common.getConnection();
      this.ps = this.con.prepareStatement(query);
      this.rs = this.ps.executeQuery();
      while (this.rs.next()) {
        result = this.rs.getString(1);
      }
    } catch (RemoteException me) {
      System.out.println(" RemoteException getMaxId(): " + me.getMessage());
    } catch (SQLException se) {
      System.out.println(" SQLException getMaxId(): " + se.getMessage());
    } catch (Exception e) {
      System.out.println(" \uC624\uB958 : D_2_DAO.getMaxId() ");
    } finally {
      try {
        this.common.close(this.con, this.ps, this.rs);
      } catch (Exception e) {
        System.out.println(" getMaxId close Exception " + e.getMessage());
      } 
    } 
    return result;
  }
  
  public int updateCarReturn(String car_id, String status, String km) {
    String query = "update a20_track2_carinfo set status = '" + 
      status + "', " + 
      "DRIVING_total_KM = '" + km + "' " + 
      "where car_id = '" + car_id + "'";
    int result = 0;
    try {
      this.con = this.common.getConnection();
      this.ps = this.con.prepareStatement(query);
      result = this.ps.executeUpdate();
    }
    catch (RemoteException me) {
      System.out.println(" RemoteException updateCarReturn(): " + me.getMessage());
    } catch (SQLException se) {
      System.out.println(" SQLException updateCarReturn: " + se.getMessage());
    } catch (Exception e) {
      System.out.println(" \uC624\uB958 : D_2_DAO.updateCarReturn() ");
    } finally {
      try {
        this.common.close(this.con, this.ps);
      } catch (Exception e) {
        System.out.println(" updateCarReturn close Exception " + e.getMessage());
      } 
    } 
    return result;
  }
  
  public int updateCarReturnDate(String rent_id, String return_date, String km) {
    String query = "update a20_track2_carrent\r\nset rent_return_date ='" + 
      return_date + "'\r\n" + 
      ",driving_km = '" + km + "' " + 
      "where rent_id = '" + rent_id + "'";
    int result = 0;
    try {
      this.con = this.common.getConnection();
      this.ps = this.con.prepareStatement(query);
      result = this.ps.executeUpdate();
    }
    catch (RemoteException me) {
      System.out.println(" RemoteException updateCarReturnDate(): " + me.getMessage());
    } catch (SQLException se) {
      System.out.println(" SQLException updateCarReturnDate: " + se.getMessage());
    } catch (Exception e) {
      System.out.println(" \uC624\uB958 : D_2_DAO.updateCarReturnDate() ");
    } finally {
      try {
        this.common.close(this.con, this.ps);
      } catch (Exception e) {
        System.out.println(" updateCarReturnDate close Exception " + e.getMessage());
      } 
    } 
    return result;
  }
}
