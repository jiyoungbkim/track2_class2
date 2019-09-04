package rent;

import common.CommonUtil;
import dao.CarHistory_DAO;
import dto.CarInfo_DTO;
import dto.CarRent_DTO;
import java.util.ArrayList;
import java.util.Scanner;


public class CarHistory
{
  public void proc() {
    Scanner sc = new Scanner(System.in);
    CarHistory_DAO daoH = new CarHistory_DAO();
    ArrayList<CarInfo_DTO> arrC = new ArrayList<CarInfo_DTO>();
    ArrayList<CarRent_DTO> arrR = null;
    CommonUtil util = new CommonUtil();
    int gubun = 0;
    String car_id = "";
    arrC = daoH.carRentList();
    
    do {
      System.out.print("렌트 기록 조회[1] [이전메뉴:0] : ");
      gubun = sc.nextInt();
      if (gubun == 0)
        break;  System.out.println(CommonUtil.getRPad("", 58, "="));
      System.out.println("자동차ID\t모델명\t제조사\t년식\t주행거리\t렌트상태");
      System.out.println(CommonUtil.getRPad("", 58, "-"));
      for (int k = 0; k < arrC.size(); k++) {
        System.out.print(String.valueOf(((CarInfo_DTO)arrC.get(k)).getCar_id()) + "\t");
        System.out.print(String.valueOf(((CarInfo_DTO)arrC.get(k)).getCar_name()) + "\t");
        System.out.print(String.valueOf(((CarInfo_DTO)arrC.get(k)).getProduce()) + "\t");
        System.out.print(String.valueOf(((CarInfo_DTO)arrC.get(k)).getProduce_ym()) + "\t");
        System.out
          .print(String.valueOf(CommonUtil.getLPad(String.valueOf(Integer.toString(((CarInfo_DTO)arrC.get(k)).getDriving_total_km())) + "km", 5, " ")) + "\t");
        System.out.print(String.valueOf(((CarInfo_DTO)arrC.get(k)).getStatus()) + "\n");
      } 
      System.out.println(CommonUtil.getRPad("", 58, "="));
      do {
        System.out.println();
        System.out.print("조회할 자동차ID [이전으로:0] : ");
        car_id = sc.next();
        if (car_id.equals("0"))
          break; 
        arrR = daoH.carRentHistory("car_id", car_id);
        if (arrR.size() == 0) {
          if (car_id.length() != 6) {
            System.out.println("ID이 형식에 맞춰 입력해주세요 [**_***]");
          } else {
            System.out.println("조회된 모델명이 없습니다.");
          } 
        } else {
          System.out.println(CommonUtil.getRPad("", 75, "="));
          System.out.println("자동차ID\t렌트ID\t\t모델명\t회원명\t주행거리\t대여일\t\t반납일");
          System.out.println(CommonUtil.getRPad("", 75, "-"));
          for (int k = 0; k < arrR.size(); k++) {
            
            System.out.print(String.valueOf(((CarRent_DTO)arrR.get(k)).getCar_id()) + "\t");
            System.out.print(String.valueOf(((CarRent_DTO)arrR.get(k)).getRent_id()) + "\t");
            System.out.print(String.valueOf(((CarRent_DTO)arrR.get(k)).getCar_name()) + "\t");
            System.out.print(String.valueOf(((CarRent_DTO)arrR.get(k)).getMember_id()) + "\t");
            System.out.print(String.valueOf(CommonUtil.getLPad(String.valueOf(((CarRent_DTO)arrR.get(k)).getDriving_km()) + "km", 5, " ")) + "\t");
            System.out.print(String.valueOf(((CarRent_DTO)arrR.get(k)).getRent_start_date()) + "\t");
            System.out.print(String.valueOf(((CarRent_DTO)arrR.get(k)).getRent_return_date()) + "\n");
          } 
          System.out.println(CommonUtil.getRPad("", 75, "="));
        } 
      } while (!car_id.equals("0"));
    } while (gubun == 1);
  }
}








