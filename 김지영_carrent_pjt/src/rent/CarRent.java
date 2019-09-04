package rent;

import java.util.ArrayList;
import java.util.Scanner;

import common.CommonUtil;
import dao.CarInfo_DAO;
import dao.CarRent_DAO;
import dao.Member_DAO;
import dto.CarInfo_DTO;
import dto.CarRent_DTO;
import dto.Member_DTO;

public class CarRent {
	public void proc() {
		Scanner sc = new Scanner(System.in);
		CommonUtil util = new CommonUtil();
		CarInfo_DAO daoC = new CarInfo_DAO();
		CarRent_DAO daoR = new CarRent_DAO();
		Member_DAO daoM = new Member_DAO();
		Member_DTO dtoM = null;
		CarInfo_DTO dtoC = null;
		CarRent_DTO dtoR = null;
		ArrayList<Member_DTO> arrM = null;
		ArrayList<CarRent_DTO> arrR = null;
		ArrayList<CarInfo_DTO> arrC = new ArrayList<CarInfo_DTO>();
		int gubun = 0;
		String rentgubun = "";
		String id = "";
		String car_id = "";
		String eq = "";
		String status = "";
		String rent_id = "";

		do {
			System.out.println();
			System.out.print("차량렌트[1], 렌트취소[2], [메인으로:0] : ");
			gubun = sc.nextInt();

			if (gubun == 0) break;
			if (gubun == 1) {

				arrM = daoM.getMemberList("all", "");
				System.out.println(util.getRPad("", 58, "="));
				System.out.println("회원ID\t회원명\t나이\t부서명\t직위\t주소\t날짜");
				System.out.println(util.getRPad("", 58, "-"));
				for (int k = 0; k < arrM.size(); k++) {
					System.out.print(arrM.get(k).getId() + "\t");
					System.out.print(arrM.get(k).getName() + "\t");
					System.out.print(arrM.get(k).getAge() + "\t");
					System.out.print(arrM.get(k).getDept() + "\t");
					System.out.print(arrM.get(k).getRank() + "\t");
					System.out.print(arrM.get(k).getAddress() + "\t");
					System.out.print(arrM.get(k).getReg_date() + "\n");
				}
				System.out.println(util.getRPad("", 58, "="));
				do {
					System.out.print("회원 ID: [이전메뉴 : 0] : ");
					id = sc.next();
					if (id.equals("0"))
						break;
					dtoM = daoM.getMemberInfo(id);
					if (dtoM == null) {
						if (id.length() != 3) {
							System.out.println("ID는 3자리입니다.재입력");
						} else {
							System.out.println("존재하지 않는 ID.재입력");
						}
					} else {
						System.out.println(util.getRPad("", 58, "="));
						System.out.println("회원ID\t회원명\t나이\t부서명\t직위\t주소\t날짜");
						System.out.println(util.getRPad("", 58, "-"));
						System.out.print(dtoM.getId() + "\t");
						System.out.print(dtoM.getName() + "\t");
						System.out.print(dtoM.getAge() + "\t");
						System.out.print(dtoM.getDept() + "\t");
						System.out.print(dtoM.getRank() + "\t");
						System.out.print(dtoM.getAddress() + "\t");
						System.out.print(dtoM.getReg_date() + "\n");
						System.out.println(util.getRPad("", 58, "="));
						System.out.println();
						System.out.print("위의 회원으로 차량을 렌트하시겠습니까? 예[y], 아니요[n] : ");
						rentgubun = sc.next();
						if (rentgubun.equals("n") || rentgubun.equals("N") || rentgubun.equals("ㅜ")) continue;
						if (rentgubun.equals("y") || rentgubun.equals("Y") || rentgubun.equals("ㅛ")) {
							arrC = daoC.getCarList("status", "y");
							System.out.println(util.getRPad("", 58, "="));
							System.out.println("자동차ID\t모델명\t제조사\t년식\t주행거리\t렌트여부");
							System.out.println(util.getRPad("", 58, "-"));
							for (int k = 0; k < arrC.size(); k++) {
								System.out.print(arrC.get(k).getCar_id() + "\t");
								System.out.print(arrC.get(k).getCar_name() + "\t");
								System.out.print(arrC.get(k).getProduce() + "\t");
								System.out.print(arrC.get(k).getProduce_ym() + "\t");
								System.out.print(
									util.getLPad(Integer.toString(arrC.get(k).getDriving_total_km()) + "km", 5, " ")
												+ "\t");
								System.out.print(arrC.get(k).getStatus() + "\n");
							}
							System.out.println(util.getRPad("", 58, "="));
							do {
								System.out.print("자동차 ID [이전메뉴:0] : ");
								car_id = sc.next();
								if (car_id.equals("0")) break;
								dtoC = daoC.getCarInfo(car_id);
								if (dtoC == null) {
									if (car_id.length() != 6) {
										System.out.println("ID이 형식에 맞춰 입력해주세요 [**_***]");
									} else {
										System.out.println("조회된 모델명이 없습니다.");
									}
								} else {
									eq = dtoC.getStatus();
									if (eq.equals("[렌트불가]")) {
										System.out.println("렌트 불가 차량.");
									} else {
										System.out.println(util.getRPad("", 58, "="));
										System.out.println("자동차ID\t모델명\t제조사\t년식\t주행거리\t렌트상태");
										System.out.println(util.getRPad("", 58, "-"));
										System.out.print(dtoC.getCar_id() + "\t");
										System.out.print(dtoC.getCar_name() + "\t");
										System.out.print(dtoC.getProduce() + "\t");
										System.out.print(dtoC.getProduce_ym() + "\t");
										System.out.print(dtoC.getDriving_total_km() + "\t");
										System.out.print(dtoC.getStatus() + "\n");
										System.out.println(util.getRPad("", 58, "-"));
										System.out.print("위의 차량을 렌트하시겠습니까? 예[y], 아니요[n] : ");
										rentgubun = sc.next();
										if (rentgubun.equals("y") || rentgubun.equals("Y") || rentgubun.equals("ㅛ")) {
											String rentDate = "";
											boolean result = true;
											boolean result2 = true;
											do {
												System.out.print("렌트할 날짜[YYYY-MM-DD] : ");
												rentDate = sc.next();
												result = util.checkDate(rentDate);
												if (!result) {
													System.out.println("유효하지 않은 날짜 형식(YYYY-MM-DD)");
												} else {
													result2 = util.checkToday(rentDate);
													if (!result2) {
														System.out.println("오늘날짜 이후로 입력하세요.");
													}
												}
											} while (!result || !result2);
											// 렌트 등록
											rent_id = daoR.getMaxCarRentId();
											dtoR = new CarRent_DTO(rent_id, car_id, id, "", rentDate, "");
											//int res = daoR.insertCarRent(dtoR);
											
//											if (res == 1) {
//												if (dtoC.getStatus().equals("[렌트가능]")) {
//													status = "n";
//												}
//												int res1 = daoR.updateCarRent(car_id, status);
//												System.out.println("등록 성공 : " + res + "행이 추가되었습니다.");
//											} else {
//												System.out.println("등록 실패 : " + res);
//											}
//											break;
											int res = daoR.insertCarRent_commit(dtoR);
						                      if (res == 0) {
						                        System.out.println("등록 성공 "); break;
						                      } 
						                      System.out.println("등록 실패 ");
						                      
						                      break;
										}
									}
								}
							} while (dtoC == null || eq.equals("[렌트불가]") || !rentgubun.equals("y") || !rentgubun.equals("Y")
									|| !rentgubun.equals("ㅗ"));
						}
					}
				} while (id.length() != 3 || dtoM == null );
			}
			if (gubun == 2) {
				arrR = daoR.getCarRentList("status", "n");
				if(arrR.size()==0) {
					System.out.println("취소할 목록이 없습니다.");
				} else {
					System.out.println(util.getRPad("", 62, "="));
					System.out.println("렌트ID\t\t자동차ID\t회원명\t주행거리\t대여일\t\t반납일");
					System.out.println(util.getRPad("", 62, "-"));
					for (int k = 0; k < arrR.size(); k++) {
						System.out.print(arrR.get(k).getRent_id() + "\t");
						System.out.print(arrR.get(k).getCar_id() + "\t");
						System.out.print(arrR.get(k).getMember_id() + "\t");
						System.out.print(util.getLPad(arrR.get(k).getDriving_km() + "km", 5, " ")
								+ "\t");
						System.out.print(arrR.get(k).getRent_start_date() + "\t");
						System.out.print(arrR.get(k).getRent_return_date() + "\n");
					}
					System.out.println(util.getRPad("", 62, "="));
					System.out.println();
					do {
						System.out.print("렌트 ID [이전으로:0] : ");
						rent_id = sc.next();
						if (rent_id.equals("0")) break;
						dtoR = daoR.getCarRentInfo(rent_id);
						if (dtoR == null) {
							if (rent_id.length() != 9) {
								System.out.println("ID이 형식에 맞춰 입력해주세요 [****_****]");
							} else {
								System.out.println("조회된 모델명이 없습니다.");
							}
						} else {
							eq = dtoR.getRent_return_date();
							if (eq.equals("[반납]")) {
								System.out.println("렌트 목록에 없습니다.");
							} else {
								System.out.println(util.getRPad("", 62, "="));
								System.out.println("렌트ID\t\t자동차ID\t회원명\t주행거리\t대여일\t\t반납일");
								System.out.println(util.getRPad("", 62, "-"));
								System.out.print(dtoR.getRent_id() + "\t");
								System.out.print(dtoR.getCar_id() + "\t");
								System.out.print(dtoR.getMember_id() + "\t");
								System.out.print(util.getLPad(dtoR.getDriving_km() + "km", 5, " ")
										+ "\t");
								System.out.print(dtoR.getRent_start_date() + "\t");
								System.out.print(dtoR.getRent_return_date() + "\n");
								System.out.println(util.getRPad("", 62, "-"));
								System.out.print("위의 차량 렌트를 취소 하시겠습니까? 예[y], 아니요[n] : ");
								rentgubun = sc.next();
								if (rentgubun.equals("y") || rentgubun.equals("Y") || rentgubun.equals("ㅛ")) {
									if (dtoR.getRent_return_date().equals("[미반납]")) {
										status = "y";
									}

									int res = daoR.updateCarRent(dtoR.getCar_id(), status);
									int res1 = daoR.deleteCarRent(rent_id);
									if (res == 1) {
										System.out.println("취소 성공 : " + res + "행이 삭제되었습니다.");
									} else {
										System.out.println("취소 실패 : " + res);
									}
									break;
								}
							}
						}
					} while (dtoR == null || eq.equals("[반납]")|| !rentgubun.equals("y") || !rentgubun.equals("Y")
							|| !rentgubun.equals("ㅗ"));
				}
			}
		} while (gubun == 1 || gubun == 2);
	}
}
