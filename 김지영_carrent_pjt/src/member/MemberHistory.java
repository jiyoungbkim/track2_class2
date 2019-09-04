package member;

import common.CommonUtil;
import dao.CarHistory_DAO;
import dao.MemberHistory_DAO;
import dto.CarRent_DTO;
import dto.Member_DTO;
import java.util.ArrayList;
import java.util.Scanner;


public class MemberHistory
{
  public void proc() {
    Scanner sc = new Scanner(System.in);
    CommonUtil util = new CommonUtil();
    ArrayList<Member_DTO> arrM = null;
    CarHistory_DAO daoH = new CarHistory_DAO();
    MemberHistory_DAO daoMH = new MemberHistory_DAO();
    ArrayList<CarRent_DTO> arrR = null;
    int gubun = 0;
    String member_id = "";
    do {
      System.out.print("렌트 회원 조회[1] [이전메뉴:0] : ");
      gubun = sc.nextInt();
      if (gubun == 0)
        break; 
      arrM = daoMH.memberRentList("", "");
      System.out.println(CommonUtil.getRPad("", 58, "="));
      System.out.println("회원ID\t회원명\t나이\t부서명\t직위\t주소\t날짜");
      System.out.println(CommonUtil.getRPad("", 58, "-"));
      for (int k = 0; k < arrM.size(); k++) {
        System.out.print(String.valueOf(((Member_DTO)arrM.get(k)).getId()) + "\t");
        System.out.print(String.valueOf(((Member_DTO)arrM.get(k)).getName()) + "\t");
        System.out.print(String.valueOf(((Member_DTO)arrM.get(k)).getAge()) + "\t");
        System.out.print(String.valueOf(((Member_DTO)arrM.get(k)).getDept()) + "\t");
        System.out.print(String.valueOf(((Member_DTO)arrM.get(k)).getRank()) + "\t");
        System.out.print(String.valueOf(((Member_DTO)arrM.get(k)).getAddress()) + "\t");
        System.out.print(String.valueOf(((Member_DTO)arrM.get(k)).getReg_date()) + "\n");
      } 
      System.out.println(CommonUtil.getRPad("", 58, "="));
      do {
        System.out.println();
        System.out.print("조회할 회원 ID [이전으로:0] : ");
        member_id = sc.next();
        if (member_id.equals("0"))
          break; 
        arrR = daoH.carRentHistory("id", member_id);
        if (arrR.size() == 0) {
          if (member_id.length() != 3) {
            System.out.println("ID는 3글자로 입력해주세요 [***]");
          } else {
            System.out.println("조회된 회원이 없습니다.");
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
      } while (!member_id.equals("0"));
    } while (gubun == 1);
  }
}
