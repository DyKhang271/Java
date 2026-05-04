package week6_dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import week6_connectDB.ConnectDB;
import week6_entity.NhanVien;
import week6_entity.PhongBan;

public class NhanVienDAO {
	public NhanVienDAO() {
		
	}
	
	public ArrayList<NhanVien> getListNhanVien(){
		ArrayList<NhanVien> list = new ArrayList<NhanVien>();
		
		try {
			Connection conn = ConnectDB.getConnect();
			String sql = "select * from NhanVien nv join PhongBan pb on nv.maPhong = pb.maPhong";
			
			Statement statement = conn.createStatement();
			ResultSet result = statement.executeQuery(sql);
			while(result.next()) {
				list.add(getNhanVienByResult(result));
			}
		}catch (Exception e) {
			System.err.print(e.getLocalizedMessage());		
		}
		return list;
	}

	private NhanVien getNhanVienByResult(ResultSet result) {
		NhanVien nv = null;
		try {
			String maNV = result.getString("maNV");
			String hoNV = result.getString("ho");
			String tenNV = result.getString("ten");
			int tuoi = result.getInt("tuoi");
			boolean phai = result.getBoolean("phai");
			PhongBan phongBan = new PhongBan(result.getString("maPhong"), result.getString("tenPhong"));
			double tienLuong = result.getDouble("tienLuong");
			
			nv = new NhanVien(maNV, hoNV, tenNV, tuoi, phai, phongBan, tienLuong);
			
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return nv;
	}
	
	public ArrayList<NhanVien> findListNhanVien(String ma){
		ArrayList<NhanVien> list = new ArrayList<NhanVien>();
		try {
			Connection conn = ConnectDB.getConnect();
			String sql = "select * from  NhanVien nv join PhongBan pb on nv.maPhong = pb.maPhong where maNV like '%' + ? + '%'";
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, ma);
			ResultSet result = statement.executeQuery();
			
			while(result.next()) {
				list.add(getNhanVienByResult(result));
			}
			
		}catch (Exception e) {
			 e.printStackTrace();
		}
		return list;
	}
	
	public boolean themNhanVien(NhanVien nv) {
		try {
			Connection conn = ConnectDB.getConnect();
			String sql = "insert NhanVien(maNV, ho, ten, tuoi, phai, maPhong, tienLuong) values(?,?,?,?,?,?,?)";
			PreparedStatement statement = conn.prepareStatement(sql);
			
			statement.setString(1, nv.getMaNV());
			statement.setString(2, nv.getHoNV());
			statement.setString(3, nv.getTenNV());
			statement.setInt(4, nv.getTuoi());
			statement.setInt(5, nv.isNu()?1:0);
			statement.setString(6, nv.getPhongBan().getMaPB());
			statement.setDouble(7, nv.getTienLuong());
			
			int n = statement.executeUpdate();   // phai co dong nay
			return n > 0;
			
			
		}catch (Exception e) {
			return false;
		}
	}
	
	public boolean xoaNhanVien(String x) {
		try {
			Connection conn = ConnectDB.getConnect();
			PreparedStatement statement = conn.prepareStatement("delete from NhanVien where maNV = ?");
			statement.setString(1, x);
			
			int n = statement.executeUpdate();
			return n > 0;
			
		}catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public  ArrayList<NhanVien> getNhanVienTheoPhongBan(String maPB){
		ArrayList<NhanVien> list = new ArrayList<NhanVien>();
		try {
			Connection conn = ConnectDB.getConnect();
			String sql= "select * from NhanVien nv join PhongBan pb on nv.maPhong = pb.maPhong where nv.maPhong = ?";
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, maPB);
			
			ResultSet result = statement.executeQuery();
			while(result.next()) {
				list.add(getNhanVienByResult(result));
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
}
