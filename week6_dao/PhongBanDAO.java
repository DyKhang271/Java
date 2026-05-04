package week6_dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import week6_connectDB.ConnectDB;
import week6_entity.PhongBan;

public class PhongBanDAO {
	public PhongBanDAO() {
		
	}
	
	public ArrayList<PhongBan> getListPhongBan(){
		ArrayList<PhongBan> list = new ArrayList<PhongBan>();
		try {
			Connection conn = ConnectDB.getConnect();
			String sql = "SELECT * FROM PhongBan";
			Statement statement = conn.createStatement();
			ResultSet result = statement.executeQuery(sql);
			while(result.next()) {
				String maPB = result.getString("maPhong");
				String tenPB = result.getString("tenPhong");
				PhongBan phongBan = new PhongBan(maPB, tenPB);
				list.add(phongBan);
			}
		}catch (Exception e) {
			System.err.println(e.getLocalizedMessage());
		}
		return list;
	}
}
