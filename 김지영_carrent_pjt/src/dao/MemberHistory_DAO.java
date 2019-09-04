package dao;

import common.DBConnectionOracle;
import dto.Member_DTO;
import java.rmi.RemoteException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MemberHistory_DAO {
	DBConnectionOracle common = new DBConnectionOracle();
	Connection con = null;
	PreparedStatement ps = null;
	ResultSet rs = null;

	public ArrayList<Member_DTO> memberRentList(String gubun, String search) {
		ArrayList<Member_DTO> arrM = new ArrayList<Member_DTO>();
		String query = " select distinct m.id, m.name, m.age, d.dept_name, r.rank_name, "
				+ "nvl(m.address,'-') address, to_char(m.reg_date, 'yyyy-MM-dd')\r\nfrom a20_track2_member m, a20_track2_carrent cr,\r\n    "
				+ "a20_track2_dept_desc d, a20_track2_rank_desc r\r\n"
				+ "where m.dept_no = d.dept_no\r\nand m.rank_no = r.rank_no\r\nand m.id = cr.member_id ";

		if (gubun.equals("id"))
			query = String.valueOf(query) + "and m.id = '" + search + "'";

		Member_DTO dto = null;
		try {
			this.con = this.common.getConnection();
			this.ps = this.con.prepareStatement(query);
			this.rs = this.ps.executeQuery();
			while (this.rs.next()) {
				String id = this.rs.getString(1);
				String name = this.rs.getString(2);
				int age = this.rs.getInt(3);
				String dept_name = this.rs.getString(4);
				String rank_name = this.rs.getString(5);
				String address = this.rs.getString(6);
				String reg_date = this.rs.getString(7);

				dto = new Member_DTO(id, name, age, dept_name, rank_name, address, reg_date);
				arrM.add(dto);
			}

		} catch (RemoteException re) {
			System.out.println("RemoteException getMemberInfo" + re.getMessage());
		} catch (SQLException se) {
			System.out.println("SQLException getMemberInfo" + se.getMessage());
		} catch (Exception e) {
			System.out.println("Exception getMemberInfo" + e.getMessage());
		} finally {
			try {
				this.common.close(this.con, this.ps, this.rs);
			} catch (Exception e) {
				System.out.println("getMemberInfo() close" + e.getMessage());
			}
		}
		return arrM;
	}
}