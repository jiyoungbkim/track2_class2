package car;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import common.CommonUtil;
import dao.CarHistory_DAO;
import dao.CarInfo_DAO;
import dto.CarInfo_DTO;
import dto.CarRent_DTO;

public class CarInfo {
	public void proc() {
		CommonUtil util = new CommonUtil();
		Scanner sc = new Scanner(System.in);
		ArrayList<CarInfo_DTO> arrC = new ArrayList<CarInfo_DTO>();
		ArrayList<CarRent_DTO> arrR = null;
		CarInfo_DTO dtoC = null;
		CarInfo_DAO daoC = new CarInfo_DAO();
		CarHistory_DAO daoH = new CarHistory_DAO();
		String car_name = "";
		String produce_name = "";
		String qYN = "";
		int produce_ym = 0;
		// System.out.println("car_id : " + daoC.getMaxCarId());

		int gubun = 0;
		do {
			System.out.println();
			System.out.print("검색[1], 등록[2], 수정[3], 삭제[4], [메인으로:0] : ");
			gubun = sc.nextInt();
			if (gubun == 1) {
				int searchGubun = 0;
				do {
					System.out.print("차량명 검색[1], 제조사 검색[2], 전체출력[9], [이전메뉴:0] : ");
					searchGubun = sc.nextInt();
					if (searchGubun == 0) {
						break;
					} else if (searchGubun == 1) {
						System.out.print("차량명 : ");
						car_name = sc.next();
						arrC = daoC.getCarList("car_name", car_name);
					} else if (searchGubun == 2) {
						int produce = 0;
						do {
							System.out.print("제조사- 기아[1], 현대[2], 삼성[3], 쌍용[4] : ");
							produce = sc.nextInt();

							if (produce == 1 || produce == 2 || produce == 3 || produce == 4) {
								switch (produce) {
								case 1:
									produce_name = "기아";
									break;
								case 2:
									produce_name = "현대";
									break;
								case 3:
									produce_name = "삼성";
									break;
								case 4:
									produce_name = "쌍용";
									break;
								}
								arrC = daoC.getCarList("produce", produce_name);
							} else {
								System.out.println("존재하지 않는 제조사. 재입력: ");
							}
						} while (produce != 1 && produce != 2 && produce != 3 && produce != 4);
					} else {
						arrC = daoC.getCarList("all", "");
					}

					if (arrC == null) {
						System.out.print("조회된 모델명이 없습니다.");
					} else {
						int count = 0;
						do {
							System.out.println(util.getRPad("", 58, "="));
							System.out.println("자동차ID\t모델명\t제조사\t년식\t주행거리\t렌트상태");
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
						} while (qYN.equals("y") || qYN.equals("Y") || qYN.equals("ㅛ"));
					}
				} while (searchGubun == 1 || searchGubun == 2 || searchGubun == 3);
			}
			if (gubun == 2) {
				do {
					System.out.print("모델명 : ");
					car_name = sc.next();
					if (car_name.length() > 10) {
						System.out.println("모델명은 10자 이내.");
					}
				} while (car_name.length() > 10);
				int produce = 0;
				do {
					System.out.print("제조사- 기아[1], 현대[2], 삼성[3], 쌍용[4] : ");
					produce = sc.nextInt();

					if (produce == 1 || produce == 2 || produce == 3 || produce == 4) {
						switch (produce) {
						case 1:
							produce_name = "기아";
							break;
						case 2:
							produce_name = "현대";
							break;
						case 3:
							produce_name = "삼성";
							break;
						case 4:
							produce_name = "쌍용";
							break;
						}
						arrC = daoC.getCarList("produce", produce_name);
					} else {
						System.out.println("존재하지 않는 제조사.");
					}
				} while (produce != 1 && produce != 2 && produce != 3 && produce != 4);
				String check = "";
				String check2 = "";
				do {
					System.out.print("년식[YYYYMM] : ");

					while (true) {
						try {
							produce_ym = sc.nextInt();
							check = Integer.toString(produce_ym);
							if (check.length() != 6) {
								System.out.println("6자로 입력하세요[YYYYMM]");
							} else {
								check2 = Integer.toString(produce_ym).substring(4, 6);

								if (Integer.parseInt(check2) > 12 || Integer.parseInt(check2) < 1) {
									System.out.println("월[MM]입력을 잘못하셨습니다.");
								}
							}
							break;
						} catch (InputMismatchException ie) {
							sc = new Scanner(System.in);
							System.out.print("숫자로 입력하세요 : ");
						}
					}
//					if(check.length() != 6 || Integer.parseInt(check2) > 12 || Integer.parseInt(check2) < 1) {
//						System.out.println("5");
//					} else {
//						System.out.println("8");
//					}
				} while (check.length() != 6 || Integer.parseInt(check2) > 12 || Integer.parseInt(check2) < 1);
				// System.out.println(daoC.getMaxCarId());

				dtoC = new CarInfo_DTO(daoC.getMaxCarId(), car_name, produce_name, Integer.toString(produce_ym), 0,
						"y");
				int res = daoC.insertCarInfo(dtoC);

				if (res == 1) {
					System.out.println("등록 성공 : " + res + "행이 추가되었습니다.");
				} else {
					System.out.println("등록 실패 : " + res);
				}
			}
			System.out.println();
			if (gubun == 3) {
				String car_id = "";
				do {
					System.out.print("수정 ID: [이전메뉴 : 0] : ");
					car_id = sc.next();
					if (car_id.equals("0"))
						break;
					dtoC = daoC.getCarInfo(car_id);
					if (dtoC == null) {
						if (car_id.length() != 6) {
							System.out.println("ID이 형식에 맞춰 입력해주세요 [**_***]");
						} else {
							System.out.println("조회된 모델명이 없습니다.");
						}
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
						System.out.print("수정 하겠습니까? 예 : y, 아니요 : n : ");

						qYN = sc.next();
						if (qYN.equals("y") || qYN.equals("Y") || qYN.equals("ㅛ")) {
							do {
								System.out.print("모델명[" + dtoC.getCar_name() + "] : ");
								car_name = sc.next();
								if (car_name.length() > 10) {
									System.out.println("모델명은 10자 이내.");
								}
							} while (car_name.length() > 10);
							int produce = 0;
							do {
								System.out.print("제조사[" + dtoC.getProduce() + "]" + "- 기아[1], 현대[2], 삼성[3], 쌍용[4] : ");
								produce = sc.nextInt();

								if (produce == 1 || produce == 2 || produce == 3 || produce == 4) {
									switch (produce) {
									case 1:
										produce_name = "기아";
										break;
									case 2:
										produce_name = "현대";
										break;
									case 3:
										produce_name = "삼성";
										break;
									case 4:
										produce_name = "쌍용";
										break;
									}
									arrC = daoC.getCarList("produce", produce_name);
								} else {
									System.out.println("존재하지 않는 제조사.");
								}
							} while (produce != 1 && produce != 2 && produce != 3 && produce != 4);

							String check = "";
							String check2 = "";
							do {
								System.out.print("년식(YYYYMM)[" + dtoC.getProduce_ym() + "] : ");
								while (true) {
									try {
										produce_ym = sc.nextInt();
										check = Integer.toString(produce_ym);
										if (check.length() != 6) {
											System.out.println("6자로 입력하세요[YYYYMM]");
										} else {
											check2 = Integer.toString(produce_ym).substring(4, 6);

											if (Integer.parseInt(check2) > 12 || Integer.parseInt(check2) < 1) {
												System.out.println("월[MM]입력을 잘못하셨습니다.");
											}
										}
										break;
									} catch (InputMismatchException ie) {
										sc = new Scanner(System.in);
										System.out.print("숫자로 입력하세요 : ");
									}
								}
							} while (check.length() != 6 || Integer.parseInt(check2) > 12
									|| Integer.parseInt(check2) < 1);
						} else
							break;
						int res = daoC.updateCarInfo(car_id, car_name, produce_name, Integer.toString(produce_ym));

						if (res == 1) {
							System.out.println("수정 성공 : " + res + "행이 업데이트되었습니다.");
							break;
						} else {
							System.out.println("수정 실패 : " + res);
						}

					}
				} while (dtoC == null);
				System.out.println();
			}
			if (gubun == 4) {
				String car_id = "";
				do {
					System.out.print("삭제 ID: [이전메뉴 : 0] : ");
					car_id = sc.next();
					if (car_id.equals("0"))
						break;
					dtoC = daoC.getCarInfo(car_id);
					if (dtoC == null) {
						if (car_id.length() != 6) {
							System.out.println("ID이 형식에 맞춰 입력해주세요 [**_***]");
						} else {
							System.out.println("조회된 모델명이 없습니다.");
						}
					} else {
						arrR = daoH.carRentHistory("car_id", car_id);
						if (arrR.isEmpty()) {
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
							System.out.print("삭제 하겠습니까? 예 : y, 아니요 : n : ");
							qYN = sc.next();
							if (qYN.equals("y") || qYN.equals("Y") || qYN.equals("ㅛ")) {
								int res = daoC.deleteCarInfo(car_id);
								if (res == 1) {
									System.out.println("삭제 성공 : " + res + "행이 삭제되었습니다.");
								} else {
									System.out.println("삭제 실패 : " + res);
								}
							}
						} else {
							System.out.println("다른 테이블에서 사용중인 차량입니다.");
						}

					}
				} while (!car_id.equals("0") ||dtoC == null);
				System.out.println();
			}
		} while (gubun == 1 || gubun == 2 || gubun == 3 || gubun == 4);

	}
}
