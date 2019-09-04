package dao;

import common.DBConnectionOracle;
import dto.CarInfo_DTO;
import dto.CarRent_DTO;
import java.rmi.RemoteException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CarHistory_DAO
{
  DBConnectionOracle common = new DBConnectionOracle();
  Connection con = null;
  PreparedStatement ps = null;
  ResultSet rs = null;
  
  public ArrayList<CarInfo_DTO> carRentList() {
    ArrayList<CarInfo_DTO> arrC = new ArrayList<CarInfo_DTO>();
    String query = "select distinct i.car_id, i.car_name, i.produce, i.produce_ym, i.driving_total_km, "+
    		" decode(i.status,'y','[렌트가능]','[렌트불가]') "+
    		" from a20_track2_carinfo i, a20_track2_carrent cr "+
    		" where i.car_id = cr.car_id order by i.car_id desc ";
    
    CarInfo_DTO dto = null;
    try {
      this.con = this.common.getConnection();
      this.ps = this.con.prepareStatement(query);
      this.rs = this.ps.executeQuery();
      while (this.rs.next()) {
        String car_id = this.rs.getString(1);
        String car_name = this.rs.getString(2);
        String produce = this.rs.getString(3);
        String produce_ym = this.rs.getString(4);
        
        int driving_total_km = this.rs.getInt(5);
        String status = this.rs.getString(6);
        
        dto = new CarInfo_DTO(car_id, car_name, produce, produce_ym, driving_total_km, status);
        
        arrC.add(dto);
      } 
    } catch (RemoteException re) {
      System.out.println("RemoteException getCarList" + re.getMessage());
    } catch (SQLException se) {
      System.out.println("SQLException getCarList" + se.getMessage());
    } catch (Exception e) {
      System.out.println("Exception getCarList" + e.getMessage());
    } finally {
      try {
        this.common.close(this.con, this.ps, this.rs);
      } catch (Exception e) {
        System.out.println("getCarList() close" + e.getMessage());
      } 
    } 
    return arrC;
  }
  public ArrayList<CarRent_DTO> carRentHistory(String gubun, String search) {
    ArrayList<CarRent_DTO> arrR = new ArrayList<CarRent_DTO>();
    String query = "select cr.rent_id, i.car_id, i.car_name, m.name, nvl(cr.driving_km,'0'), "+
    		" to_char(cr.rent_start_date,'yyyy-MM-dd'), "+
    		" decode(cr.rent_return_date, null ,'[미반납]',to_char(cr.rent_return_date, 'yyyy-mm-dd')) "+
    		" from A20_TRACK2_CARRENT cr, a20_track2_carinfo i, A20_TRACK2_MEMBER m "+
    		" where cr.car_id = i.car_id "+
    		" and cr.member_id = m.id ";
    
    if (gubun.equals("car_id")) query = String.valueOf(query) + "and i.car_id = '" + search + "' "; 
    if (gubun.equals("id")) query = String.valueOf(query) + "and m.id = '" + search + "' "; 
    query = String.valueOf(query) + "order by cr.rent_id desc";
    
    CarRent_DTO dto = null;
    try {
      this.con = this.common.getConnection();
      this.ps = this.con.prepareStatement(query);
      this.rs = this.ps.executeQuery();
      while (this.rs.next()) {
        String rent_id = this.rs.getString(1);
        String car_id = this.rs.getString(2);
        String car_name = this.rs.getString(3);
        String member_id = this.rs.getString(4);
        String driving_km = this.rs.getString(5);
        String rent_start_date = this.rs.getString(6);
        String rent_return_date = this.rs.getString(7);
        
        dto = new CarRent_DTO(rent_id, car_id, car_name, member_id, driving_km, rent_start_date, rent_return_date);
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
}
