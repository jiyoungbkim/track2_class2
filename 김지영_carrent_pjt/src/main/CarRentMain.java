package main;

import java.util.Scanner;

import car.CarInfo;
import member.Member;
import member.MemberHistory;
import rent.CarHistory;
import rent.CarRent;
import rent.CarReturn;

public class CarRentMain {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int gubun = 0;
		do {
			System.out.println("* 회원관리:[1] ");
			System.out.println("* 차량관리:[2] ");
			System.out.println("* 렌트관리:[3] ");
			System.out.println("* 차량반납:[4] ");
			System.out.println("* 차량별 렌트조회:[5] ");
			System.out.println("* 회원별 렌트조회:[6] ");
			System.out.println("* 시스템 종료:[0] ");
			System.out.print("* 작업 선택 ? ==> ");
			gubun = sc.nextInt();
			if (gubun == 1) {
				Member member = new Member();
				member.proc();
			} else if (gubun == 2) {
				CarInfo car_info = new CarInfo();
				car_info.proc();
			} else if (gubun == 3) {
				CarRent rent = new CarRent();
				rent.proc();
			} else if (gubun == 4) {
				CarReturn ret = new CarReturn();
				ret.proc();
			} else if (gubun == 5) {
				CarHistory his = new CarHistory();
				his.proc();
			} else if (gubun == 6) {
				MemberHistory mhis = new MemberHistory();
				mhis.proc();
			} else if (gubun == 0) {
				System.exit(0);
			}
		} while (gubun != 0);
	}

}
