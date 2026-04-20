package dao;

import java.util.ArrayList;
import java.util.Arrays;
import entity.NhanVien;
import java.util.List;

public class DSNV {

	// ===================== HẰNG SỐ (CONSTANTS) =====================
	public static final List<NhanVien> DU_LIEU_MAC_DINH = Arrays.asList(
			new NhanVien("NV01", "Huá»³nh", "Khang", 20, "Ká»¹ Thuáº­t", 3, 5000),
			new NhanVien("NV02", "Tráº§n", "Anh", 25, "NhÃ¢n Sá»±", 5, 6000),
			new NhanVien("NV03", "LÃª", "HÆ°Æ¡ng", 30, "Káº¿ ToÃ¡n", 10, 8000));

	// ===================== CÁC THUỘC TÍNH (FIELDS) =====================
	private List<NhanVien> ds;

	// ===================== HÀM KHỞI TẠO (CONSTRUCTOR) =====================
	public DSNV() {
		try {
			ds = Luu.docFile();
		} catch (RuntimeException e) {
			ds = new ArrayList<>();
		}
		if (ds == null)
			ds = new ArrayList<>();
		if (ds.isEmpty()) {
			themDuLieuMacDinh();
		}
	}

	// ===================== HÀM HỖ TRỢ NỘI BỘ =====================
	private void themDuLieuMacDinh() {
		ds.addAll(DU_LIEU_MAC_DINH);
		Luu.luuFile(ds);
	}

	// ===================== PHƯƠNG THỨC GET/SET =====================
	public List<NhanVien> getDs() {
		return this.ds;
	}

	public void setDs(List<NhanVien> ds) {
		if (ds == null)
			throw new IllegalArgumentException("Danh sÃ¡ch khÃ´ng Ä‘Æ°á»£c null");

		List<NhanVien> banSao = new ArrayList<>();
		for (NhanVien nv : ds) {
			if (nv == null)
				throw new IllegalArgumentException("Danh sÃ¡ch khÃ´ng Ä‘Æ°á»£c chá»©a nhÃ¢n viÃªn null");
			if (banSao.contains(nv))
				throw new IllegalArgumentException("TrÃ¹ng mÃ£ nhÃ¢n viÃªn: " + nv.getMa());
			banSao.add(nv);
		}

		this.ds = banSao;
		Luu.luuFile(this.ds);
	}

	// ===================== CÁC PHƯƠNG THỨC THÊM/XÓA (CRUD) =====================
	public void them(NhanVien nv) {
		if (nv == null)
			throw new IllegalArgumentException("NhÃ¢n viÃªn khÃ´ng Ä‘Æ°á»£c null");
		if (ds.contains(nv))
			throw new IllegalArgumentException("MÃ£ nhÃ¢n viÃªn Ä‘Ã£ tá»“n táº¡i: " + nv.getMa());
		ds.add(nv);
		Luu.luuFile(ds);
	}

	public void xoa(int index) {
		if (index < 0 || index >= ds.size())
			throw new IndexOutOfBoundsException("Vá»‹ trÃ­ xÃ³a khÃ´ng há»£p lá»‡: " + index);
		ds.remove(index);
		Luu.luuFile(ds);
	}

	// ===================== TÌM KIẾM & TIỆN ÍCH =====================
	public int timTheoMa(String ma) {
		if (ma == null || ma.isBlank())
			return -1;
		return ds.indexOf(new NhanVien(ma.trim()));
	}

	public int size() {
		return ds.size();
	}

	public NhanVien get(int i) {
		if (i < 0 || i >= ds.size())
			throw new IndexOutOfBoundsException("Vá»‹ trÃ­ truy cáº­p khÃ´ng há»£p lá»‡: " + i);
		return ds.get(i);
	}
}
