package entity;

import java.io.Serializable;
import java.util.Objects;
import util.Regex;

/**
 * Lớp thực thể đại diện cho một Nhân Viên trong hệ thống.
 * Chứa các thông tin cá nhân và phương thức tính lương.
 */
public class NhanVien implements Serializable {
	private static final long serialVersionUID = 1L;

	// ===================== CÁC THUỘC TÍNH (FIELDS) =====================
	private String ma, ho, ten;
	private int tuoi;
	private String phongBan;
	private int soThang;
	private double tienLuong;

	// ===================== HÀM KHỞI TẠO (CONSTRUCTORS) =====================
	public NhanVien(String ma) {
		setMa(ma);
	}

	public NhanVien(String ma, String ho, String ten, int tuoi, String phongBan, int soThang, double tienLuong) {
		setMa(ma);
		setHo(ho);
		setTen(ten);
		setTuoi(tuoi);
		setPhongBan(phongBan);
		setSoThang(soThang);
		setTienLuong(tienLuong);
	}

	// ===================== PHƯƠNG THỨC GET/SET =====================
	public String getMa() {
		return ma;
	}

	public void setMa(String ma) {
		if (ma == null || ma.isBlank())
			throw new IllegalArgumentException("Mã không được để trống");
		if (!Regex.isValidMaNhanVien(ma))
			throw new IllegalArgumentException("Mã phải theo mẫu NV + 2 hoặc 3 chữ số, ví dụ: NV01 hoặc NV123");
		this.ma = ma.trim().toUpperCase();
	}

	public String getHo() {
		return ho;
	}

	public void setHo(String ho) {
		if (ho == null || ho.isBlank())
			throw new IllegalArgumentException("Họ không được để trống");
		if (!Regex.isValidHoTen(ho))
			throw new IllegalArgumentException("Họ chỉ được chứa chữ cái và khoảng trắng");
		this.ho = ho.trim();
	}

	public String getTen() {
		return ten;
	}

	public void setTen(String ten) {
		if (ten == null || ten.isBlank())
			throw new IllegalArgumentException("Tên không được để trống");
		if (!Regex.isValidHoTen(ten))
			throw new IllegalArgumentException("Tên chỉ được chứa chữ cái và khoảng trắng");
		this.ten = ten.trim();
	}

	public int getTuoi() {
		return tuoi;
	}

	public void setTuoi(int tuoi) {
		if (tuoi < 18 || tuoi > 60)
			throw new IllegalArgumentException("Tuổi phải từ 18 đến 60");
		this.tuoi = tuoi;
	}

	public String getPhongBan() {
		return phongBan;
	}

	public void setPhongBan(String phongBan) {
		if (phongBan == null || phongBan.isBlank())
			throw new IllegalArgumentException("Phòng ban không được để trống");
		this.phongBan = phongBan.trim();
	}

	public int getSoThang() {
		return soThang;
	}

	public void setSoThang(int soThang) {
		if (soThang < 0 || soThang > 12)
			throw new IllegalArgumentException("Số tháng phải từ 0 đến 12");
		this.soThang = soThang;
	}

	public double getTienLuong() {
		return tienLuong;
	}

	public void setTienLuong(double tienLuong) {
		if (tienLuong < 0)
			throw new IllegalArgumentException("Tiền lương không được âm");
		this.tienLuong = tienLuong;
	}

	// ===================== NGHIỆP VỤ & TIỆN ÍCH =====================
	/**
	 * Tính lương thực nhận của nhân viên = Số tháng * Tiền Lương.
	 */
	public double getThucNhan() {
		return this.soThang * this.tienLuong;
	}

	/**
	 * Render thông tin thành một hàng mảng Object để dễ dàng đổ vào JTable.
	 */
	public Object[] toRow() {
		return new Object[] { ma, ho, ten, tuoi, phongBan, soThang, tienLuong, getThucNhan() };
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof NhanVien))
			return false;
		NhanVien other = (NhanVien) obj;
		return ma != null && other.ma != null && ma.equalsIgnoreCase(other.ma);
	}

	@Override
	public int hashCode() {
		return Objects.hash(ma == null ? null : ma.toLowerCase());
	}

	@Override
	public String toString() {
		String format = "Mã: %s, Họ: %s, Tên: %s, Tuổi: %d, Phòng Ban: %s, Số Tháng: %d, Tiền Lương: %.2f, Thực Nhận: %.2f";
		return String.format(format, ma, ho, ten, tuoi, phongBan, soThang, tienLuong, getThucNhan());
	}
}
