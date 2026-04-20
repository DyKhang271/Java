package entity;

import java.io.Serializable;
import java.util.Objects;

import util.Regex;

/**
 * Lá»›p thá»±c thá»ƒ Ä‘áº¡i diá»‡n cho má»™t NhÃ¢n ViÃªn trong há»‡ thá»‘ng.
 * Chá»©a cÃ¡c thÃ´ng tin cÃ¡ nhÃ¢n vÃ  phÆ°Æ¡ng thá»©c tÃ­nh lÆ°Æ¡ng.
 */
public class NhanVien implements Serializable {
	private static final long serialVersionUID = 1L;

	// ===================== CÃC THUá»˜C TÃNH (FIELDS) =====================
	private String ma, ho, ten;
	private int tuoi;
	private String phongBan;
	private int soThang;
	private double tienLuong;

	// ===================== HÃ€M KHá»žI Táº O (CONSTRUCTORS) =====================
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

	// ===================== PHÆ¯Æ NG THá»¨C GET/SET =====================
	public String getMa() {
		return ma;
	}

	public void setMa(String ma) {
		if (ma == null || ma.isBlank())
			throw new IllegalArgumentException("MÃ£ khÃ´ng Ä‘Æ°á»£c Ä‘á»ƒ trá»‘ng");
		if (!Regex.isValidMaNhanVien(ma))
			throw new IllegalArgumentException("MÃ£ pháº£i theo máº«u NV + 2 hoáº·c 3 chá»¯ sá»‘, vÃ­ dá»¥: NV01 hoáº·c NV123");
		this.ma = ma.trim().toUpperCase();
	}

	public String getHo() {
		return ho;
	}

	public void setHo(String ho) {
		if (ho == null || ho.isBlank())
			throw new IllegalArgumentException("Há» khÃ´ng Ä‘Æ°á»£c Ä‘á»ƒ trá»‘ng");
		if (!Regex.isValidHoTen(ho))
			throw new IllegalArgumentException("Há» chá»‰ Ä‘Æ°á»£c chá»©a chá»¯ cÃ¡i vÃ  khoáº£ng tráº¯ng");
		this.ho = ho.trim();
	}

	public String getTen() {
		return ten;
	}

	public void setTen(String ten) {
		if (ten == null || ten.isBlank())
			throw new IllegalArgumentException("TÃªn khÃ´ng Ä‘Æ°á»£c Ä‘á»ƒ trá»‘ng");
		if (!Regex.isValidHoTen(ten))
			throw new IllegalArgumentException("TÃªn chá»‰ Ä‘Æ°á»£c chá»©a chá»¯ cÃ¡i vÃ  khoáº£ng tráº¯ng");
		this.ten = ten.trim();
	}

	public int getTuoi() {
		return tuoi;
	}

	public void setTuoi(int tuoi) {
		if (tuoi < 18 || tuoi > 60)
			throw new IllegalArgumentException("Tuá»•i pháº£i tá»« 18 Ä‘áº¿n 60");
		this.tuoi = tuoi;
	}

	public String getPhongBan() {
		return phongBan;
	}

	public void setPhongBan(String phongBan) {
		if (phongBan == null || phongBan.isBlank())
			throw new IllegalArgumentException("PhÃ²ng ban khÃ´ng Ä‘Æ°á»£c Ä‘á»ƒ trá»‘ng");
		this.phongBan = phongBan.trim();
	}

	public int getSoThang() {
		return soThang;
	}

	public void setSoThang(int soThang) {
		if (soThang < 0 || soThang > 12)
			throw new IllegalArgumentException("Sá»‘ thÃ¡ng pháº£i tá»« 0 Ä‘áº¿n 12");
		this.soThang = soThang;
	}

	public double getTienLuong() {
		return tienLuong;
	}

	public void setTienLuong(double tienLuong) {
		if (tienLuong < 0)
			throw new IllegalArgumentException("Tiá»n lÆ°Æ¡ng khÃ´ng Ä‘Æ°á»£c Ã¢m");
		this.tienLuong = tienLuong;
	}

	// ===================== NGHIá»†P Vá»¤ & TIá»†N ÃCH =====================
	/**
	 * TÃ­nh lÆ°Æ¡ng thá»±c nháº­n cá»§a nhÃ¢n viÃªn = Sá»‘ thÃ¡ng * Tiá»n LÆ°Æ¡ng.
	 */
	public double getThucNhan() {
		return this.soThang * this.tienLuong;
	}

	/**
	 * Render thÃ´ng tin thÃ nh má»™t hÃ ng máº£ng Object Ä‘á»ƒ dá»… dÃ ng Ä‘á»• vÃ o JTable.
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
		String format = "MÃ£: %s, Há»: %s, TÃªn: %s, Tuá»•i: %d, PhÃ²ng Ban: %s, Sá»‘ ThÃ¡ng: %d, Tiá»n LÆ°Æ¡ng: %.2f, Thá»±c Nháº­n: %.2f";
		return String.format(format, ma, ho, ten, tuoi, phongBan, soThang, tienLuong, getThucNhan());
	}
}

