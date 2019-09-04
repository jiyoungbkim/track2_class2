package rent;

import common.CommonUtil;
import dao.CarInfo_DAO;
import dao.CarRent_DAO;
import dao.CarReturn_DAO;
import dao.Member_DAO;
import dto.CarInfo_DTO;
import dto.CarRent_DTO;
import dto.Member_DTO;
import java.util.ArrayList;
import java.util.Scanner;

public class CarReturn {
	public void proc() {
		Scanner sc = new Scanner(System.in);
		CommonUtil util = new CommonUtil();
		CarInfo_DAO daoC = new CarInfo_DAO();
		CarRent_DAO daoR = new CarRent_DAO();
		CarReturn_DAO daoR2 = new CarReturn_DAO();
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
		String sKm = "";
		do {
			System.out.println();
			System.out.print("차량반납[1], 반납취소[2], [메인으로:0] : ");
			gubun = sc.nextInt();

			if (gubun == 0)
				break;
			if (gubun == 1) {
				arrR = daoR.getCarRentList("return_date", null);
				if (arrR.size() == 0) {
					System.out.println("반납할 목록이 없습니다.");
				} else {
					System.out.println(CommonUtil.getRPad("", 62, "="));
					System.out.println("렌트ID\t\t자동차ID\t회원명\t주행거리\t대여일\t\t반납일");
					System.out.println(CommonUtil.getRPad("", 62, "-"));
					for (int k = 0; k < arrR.size(); k++) {
						System.out.print(String.valueOf(((CarRent_DTO) arrR.get(k)).getRent_id()) + "\t");
						System.out.print(String.valueOf(((CarRent_DTO) arrR.get(k)).getCar_id()) + "\t");
						System.out.print(String.valueOf(((CarRent_DTO) arrR.get(k)).getMember_id()) + "\t");
						System.out.print(String
								.valueOf(CommonUtil.getLPad(
										String.valueOf(((CarRent_DTO) arrR.get(k)).getDriving_km()) + "km", 5, " "))
								+ "\t");
						System.out.print(String.valueOf(((CarRent_DTO) arrR.get(k)).getRent_start_date()) + "\t");
						System.out.print(String.valueOf(((CarRent_DTO) arrR.get(k)).getRent_return_date()) + "\n");
					}
					System.out.println(CommonUtil.getRPad("", 62, "="));
					System.out.println();
					do {
						System.out.print("렌트 ID [이전으로:0] : ");
						rent_id = sc.next();
						if (rent_id.equals("0"))
							break;
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
								System.out.println(CommonUtil.getRPad("", 62, "="));
								System.out.println("렌트ID\t\t자동차ID\t회원명\t주행거리\t대여일\t\t반납일");
								System.out.println(CommonUtil.getRPad("", 62, "-"));
								System.out.print(String.valueOf(dtoR.getRent_id()) + "\t");
								System.out.print(String.valueOf(dtoR.getCar_id()) + "\t");
								System.out.print(String.valueOf(dtoR.getMember_id()) + "\t");
								System.out.print(String.valueOf(
										CommonUtil.getLPad(String.valueOf(dtoR.getDriving_km()) + "km", 5, " "))
										+ "\t");
								System.out.print(String.valueOf(dtoR.getRent_start_date()) + "\t");
								System.out.print(String.valueOf(dtoR.getRent_return_date()) + "\n");
								System.out.println(CommonUtil.getRPad("", 62, "-"));
								System.out.print("위의 차량 반납 하시겠습니까? 예[y], 아니요[n] : ");
								rentgubun = sc.next();
								if (rentgubun.equals("y") || rentgubun.equals("Y") || rentgubun.equals("ㅛ")) {
									System.out.print("주행 거리[km]: ");
									int km = sc.nextInt();
									int resKm = Integer.parseInt(daoR2.getDrivingTotalKm(dtoR.getCar_id())) + km;
									sKm = Integer.toString(resKm);
									String rentDate = "";
									boolean result = true;
									boolean result2 = true;

									System.out.print("직접입력[1], 오늘날짜[2] : ");
									int daygubun = sc.nextInt();
									do {
										
										if (daygubun == 1) {
											System.out.print("반납 날짜[YYYY-MM-DD] : ");
											rentDate = sc.next();
											result = util.checkDate(rentDate);
											if (!result) {
												System.out.println("유효하지 않은 날짜 형식(YYYY-MM-DD)");
											} else {
												result2 = util.checkSRdate(dtoR.getRent_start_date(), rentDate);
												if (!result2) {
													System.out.println("대여일자 이후로 입력하세요.");
												}
											}
										} else if (daygubun == 2) {
											rentDate = util.getToday();
											result2 = util.checkSRdate(dtoR.getRent_start_date(), rentDate);
											if (!result2) {
												System.out.println("오늘이 대여일보다 이전입니다.직접입력하세요.");
												daygubun = 1;
											}
										}
									} while (!result || !result2);

									int res = daoR2.updateCarReturn(dtoR.getCar_id(), "y", sKm);

									if (res == 1) {
										System.out.println("반납 성공 : " + res + "행이 업데이트되었습니다.");
										int i = daoR2.updateCarReturnDate(dtoR.getRent_id(), rentDate,
												Integer.toString(km));
										break;
									}
									System.out.println("반납 실패 : " + res);

									break;
								}
							}
						}
					} while (dtoR == null || eq.equals("[반납]") || !rentgubun.equals("y") || !rentgubun.equals("Y")
							|| !rentgubun.equals("ㅗ"));
				}
			}
			if (gubun != 2) {
				continue;
			}
			String query = " select r.rent_id,r.car_id,m.name,nvl(r.driving_km,'0'),to_char(r.rent_start_date,'yyyy-MM-dd'),  "
					+ "decode(r.rent_return_date,'','[미반납]','[반납]') as rent_return_date,i.status  from a20_track2_carrent r, "
					+ "a20_track2_carinfo i, a20_track2_member m  where r.rent_id in(select max(rent_id) as rent_id  "
					+ "from a20_track2_carrent  group by car_id)  and r.car_id = i.car_id  and r.member_id = m.id  "
					+ "and i.status = 'y'  order by rent_id desc ";

			arrR = daoR2.getCarReturnList(query);
			if (arrR.size() == 0) {
				System.out.println("취소할 목록이 없습니다.");
			} else {
				System.out.println(CommonUtil.getRPad("", 62, "="));
				System.out.println("렌트ID\t\t자동차ID\t회원명\t주행거리\t대여일\t\t반납일");
				System.out.println(CommonUtil.getRPad("", 62, "-"));
				for (int k = 0; k < arrR.size(); k++) {
					System.out.print(String.valueOf(((CarRent_DTO) arrR.get(k)).getRent_id()) + "\t");
					System.out.print(String.valueOf(((CarRent_DTO) arrR.get(k)).getCar_id()) + "\t");
					System.out.print(String.valueOf(((CarRent_DTO) arrR.get(k)).getMember_id()) + "\t");
					System.out.print(String
							.valueOf(CommonUtil.getLPad(
									String.valueOf(((CarRent_DTO) arrR.get(k)).getDriving_km()) + "km", 5, " "))
							+ "\t");
					System.out.print(String.valueOf(((CarRent_DTO) arrR.get(k)).getRent_start_date()) + "\t");
					System.out.print(String.valueOf(((CarRent_DTO) arrR.get(k)).getRent_return_date()) + "\n");
				}
				System.out.println(CommonUtil.getRPad("", 62, "="));
				System.out.println();
				do {
					System.out.print("렌트 ID [이전으로:0] : ");
					rent_id = sc.next();
					if (rent_id.equals("0"))
						break;
					dtoR = daoR.getCarRentInfo(rent_id);
					if (dtoR == null) {
						if (rent_id.length() != 9) {
							System.out.println("ID이 형식에 맞춰 입력해주세요 [****_****]");
						} else {
							System.out.println("조회된 모델명이 없습니다.");
						}
					} else {
						eq = dtoR.getRent_return_date();
						if (eq.equals("[미반납]")) {
							System.out.println("반납 목록에 없습니다.");
						} else {
							System.out.println(CommonUtil.getRPad("", 62, "="));
							System.out.println("렌트ID\t\t자동차ID\t회원명\t주행거리\t대여일\t\t반납일");
							System.out.println(CommonUtil.getRPad("", 62, "-"));
							System.out.print(String.valueOf(dtoR.getRent_id()) + "\t");
							System.out.print(String.valueOf(dtoR.getCar_id()) + "\t");
							System.out.print(String.valueOf(dtoR.getMember_id()) + "\t");
							System.out.print(String.valueOf(
									CommonUtil.getLPad(String.valueOf(dtoR.getDriving_km()) + "km", 5, " ")) + "\t");
							System.out.print(String.valueOf(dtoR.getRent_start_date()) + "\t");
							System.out.print(String.valueOf(dtoR.getRent_return_date()) + "\n");
							System.out.println(CommonUtil.getRPad("", 62, "-"));
							System.out.print("반납 취소 하시겠습니까? 예[y], 아니요[n] : ");
							rentgubun = sc.next();
							if (rentgubun.equals("y") || rentgubun.equals("Y") || rentgubun.equals("ㅛ")) {

								int Km = Integer.parseInt(daoR2.getDrivingTotalKm(dtoR.getCar_id()))
										- Integer.parseInt(dtoR.getDriving_km());

								int res = daoR2.updateCarReturn(dtoR.getCar_id(), "n", Integer.toString(Km));

								if (res == 1) {
									System.out.println("취소 성공 : " + res + "행이 업데이트되었습니다.");
									int i = daoR2.updateCarReturnDate(dtoR.getRent_id(), "", "");
									break;
								}
								System.out.println("취소 실패 : " + res);

								break;
							}
						}
					}
				} while (dtoR == null || eq.equals("[반납]") || !rentgubun.equals("y") || !rentgubun.equals("Y")
						|| !rentgubun.equals("ㅗ"));
			}

		} while (gubun == 1 || gubun == 2);
	}
}
