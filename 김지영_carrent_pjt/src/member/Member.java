package member;

import java.util.ArrayList;
import java.util.Scanner;

import common.CommonUtil;
import dao.MemberHistory_DAO;
import dao.Member_DAO;
import dto.Member_DTO;

public class Member {
	public void proc() {
		Scanner sc = new Scanner(System.in);
		Member_DAO daoM = new Member_DAO();
		MemberHistory_DAO daoMH = new MemberHistory_DAO();
		Member_DTO dtoM = null;
		ArrayList<Member_DTO> arrM = null;
		String qYN = "";
		CommonUtil util = new CommonUtil();
		int gubun = 0;
		do {
			System.out.println();
			System.out.print("회원검색:[1], 등록[2], 수정[3], 삭제[4], [메인으로:0] : ");
			gubun = sc.nextInt();
			// 검색
			if (gubun == 1) {
				int searchGubun = 0;
				do {
					System.out.print("이름검색:[1], 부서검색:[2], 전체출력[9], [이전메뉴:0] : ");
					searchGubun = sc.nextInt();
					if (searchGubun == 0)
						break;
					if (searchGubun == 1) {
						System.out.print("성명 : ");
						String name = sc.next();
						arrM = daoM.getMemberList("name", name);
					} else if (searchGubun == 2) {
						System.out.print("부서검색 :인사[10], 기획[20], 영업[30], 개발[40] : ");
						String dept = sc.next();
						arrM = daoM.getMemberList("dept", dept);
					} else if (searchGubun == 9) {
						arrM = daoM.getMemberList("all", "");
					}
					if (arrM.size() == 0) {
						System.out.println("검색 내용이 없습니다.");
					} else {
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
					}

				} while (searchGubun == 1 || searchGubun == 2 || searchGubun == 9);
				// 등록
			} else if (gubun == 2) {
				String name = "";
				do {
					System.out.print("성명 : ");
					name = sc.next();
					if (name.length() > 3)
						System.out.println("성명 3자 이내");
				} while (name.length() > 3);
				int age = 0;
				do {
					System.out.print("나이 : ");
					age = sc.nextInt();
					if (age > 100 || age < 0)
						System.out.println("나이 0~100이내");
				} while (age > 100 || age < 0);
				String dept = "";
				do {
					System.out.print("부서 -인사[10], 기획[20], 영업[30], 개발[40] : ");
					dept = sc.next();
					if (!dept.equals("10") && !dept.equals("20") && !dept.equals("30") && !dept.equals("40")) {
						System.out.println("존재하지 않는 코드. 재입력 : ");
					}
				} while (!dept.equals("10") && !dept.equals("20") && !dept.equals("30") && !dept.equals("40"));
				String rank = "";
				do {
					System.out.print("직급 -사장[10], 이사[20], 부장[30], 차장[40] : ");
					rank = sc.next();
					if (!rank.equals("10") && !rank.equals("20") && !rank.equals("30") && !rank.equals("40")) {
						System.out.println("존재하지 않는 코드. 재입력 : ");
					}
				} while (!rank.equals("10") && !rank.equals("20") && !rank.equals("30") && !rank.equals("40"));
				String address = "";
				do {
					System.out.print("주소 : ");
					address = sc.next();
					if (address.length() > 5)
						System.out.println("주소는 5자 이내");
				} while (address.length() > 5);
				String reg_date = "";
				boolean result = true;
				do {
					System.out.print("등록일(YYYY-MM-DD) : ");
					reg_date = sc.next();
					result = util.checkDate(reg_date);
					if (!result)
						System.out.println("유효하지 않은 날짜(YYYY-MM-DD)");
				} while (!result);
				String id = daoM.getMaxId();
				// 1
				// int res = daoM.insertMember(id,name,age,dept,rank,address,reg_date);
				// 2
				// Member_DTO dto = new Member_DTO(id,name,age,dept,rank,address,reg_date);
				// int res = daoM.insertMember_DTO(dto);
				// 3
				int res = daoM.insertMember_2(id, name, age, dept, rank, address, reg_date);

				if (res == 1) {
					System.out.println("등록 성공 : " + res + "행이 추가되었습니다.");
				} else {
					System.out.println("등록 실패 : " + res);
				}
				// 수정
			} else if (gubun == 3) {
				String id = "";
				do {
					System.out.print("수정 ID: [이전메뉴 : 0] : ");
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
						System.out.print("수정 하겠습니까? 예[y], 아니요 [n] : ");
						qYN = sc.next();
						if (qYN.equals("y") || qYN.equals("Y") || qYN.equals("ㅛ")) {
							String name = "";
							do {
								System.out.print("성명[" + dtoM.getName() + "] : ");
								name = sc.next();
								if (name.length() > 3)
									System.out.println("성명 3자 이내");
							} while (name.length() > 3);
							int age = 0;
							do {
								System.out.print("나이[" + dtoM.getAge() + "] : ");
								age = sc.nextInt();
								if (age > 100 || age < 0)
									System.out.println("나이 0~100이내");
							} while (age > 100 || age < 0);
							String dept = "";
							do {
								System.out.print("부서[" + dtoM.getDept() + "] -인사[10], 기획[20], 영업[30], 개발[40] : ");
								dept = sc.next();
								if (!dept.equals("10") && !dept.equals("20") && !dept.equals("30")
										&& !dept.equals("40")) {
									System.out.println("존재하지 않는 코드. 재입력 : ");
								}
							} while (!dept.equals("10") && !dept.equals("20") && !dept.equals("30")
									&& !dept.equals("40"));
							String rank = "";
							do {
								System.out.print("직급[" + dtoM.getRank() + "] -사장[10], 이사[20], 부장[30], 차장[40] : ");
								rank = sc.next();
								if (!rank.equals("10") && !rank.equals("20") && !rank.equals("30")
										&& !rank.equals("40")) {
									System.out.println("존재하지 않는 코드. 재입력 : ");
								}
							} while (!rank.equals("10") && !rank.equals("20") && !rank.equals("30")
									&& !rank.equals("40"));
							String address = "";
							do {
								System.out.print("주소[" + dtoM.getAddress() + "] : ");
								address = sc.next();
								if (address.length() > 5)
									System.out.println("주소는 5자 이내");
							} while (address.length() > 5);
							String reg_date = "";
							boolean result = true;
							do {
								System.out.print("등록일(YYYY-MM-DD)[" + dtoM.getReg_date() + "] : ");
								reg_date = sc.next();
								result = util.checkDate(reg_date);
								if (!result)
									System.out.println("유효하지 않은 날짜(YYYY-MM-DD)");
							} while (!result);
							int res = daoM.updateMember(id, name, age, dept, rank, address, reg_date);
							if (res == 1) {
								System.out.println("수정 성공 : " + res + "행이 업데이트되었습니다.");
							} else {
								System.out.println("수정 실패 : " + res);
							}
						}
					}
				} while (!id.equals("0") || dtoM == null);
				// 삭제
			} else if (gubun == 4) {
				String id = "";
				do {
					System.out.print("삭제 ID: [이전메뉴 : 0] : ");
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
						arrM = daoMH.memberRentList("id", id);
						if (arrM.isEmpty()) {
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
							System.out.print("삭제 하겠습니까? 예 [y], 아니요 [n] : ");
							qYN = sc.next();
							if (qYN.equals("y") || qYN.equals("Y") || qYN.equals("ㅛ")) {
								int res = daoM.deleteMember(id);
								if (res == 1) {
									System.out.println("삭제 성공 : " + res + "행이 삭제되었습니다.");
								} else {
									System.out.println("삭제 실패 : " + res);
								}
							}
						} else {
							System.out.println("다른 테이블에서 사용중인 회원입니다.");
						}
					}
				} while (!id.equals("0") || dtoM == null);
			}
		} while (gubun == 1 || gubun == 2 || gubun == 3 || gubun == 4);
	}
}
