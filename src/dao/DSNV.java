package dao;

import java.util.ArrayList;
import java.util.Arrays;
import entity.NhanVien;
import java.util.List;

public class DSNV {

	// ===================== HẰNG SỐ (CONSTANTS) =====================
	public static final List<NhanVien> DU_LIEU_MAC_DINH = Arrays.asList(
			new NhanVien("NV01", "Trần Duy", "Khang", 20, "Kỹ Thuật", 3, 5000),
			new NhanVien("NV02", "Trần", "Anh", 25, "Nhân Sự", 5, 6000),
			new NhanVien("NV03", "Lê", "Hương", 30, "Kế Toán", 10, 8000));

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
			throw new IllegalArgumentException("Danh sách không được null");

		List<NhanVien> banSao = new ArrayList<>();
		for (NhanVien nv : ds) {
			if (nv == null)
				throw new IllegalArgumentException("Danh sách không được chứa nhân viên null");
			if (banSao.contains(nv))
				throw new IllegalArgumentException("Trùng mã nhân viên: " + nv.getMa());
			banSao.add(nv);
		}

		this.ds = banSao;
		Luu.luuFile(this.ds);
	}

	// ===================== CÁC PHƯƠNG THỨC THÊM/XÓA (CRUD) =====================
	public void them(NhanVien nv) {
		if (nv == null)
			throw new IllegalArgumentException("Nhân viên không được null");
		if (ds.contains(nv))
			throw new IllegalArgumentException("Mã nhân viên đã tồn tại: " + nv.getMa());
		ds.add(nv);
		Luu.luuFile(ds);
	}

	public void xoa(int index) {
		if (index < 0 || index >= ds.size())
			throw new IndexOutOfBoundsException("Vị trí xóa không hợp lệ: " + index);
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
			throw new IndexOutOfBoundsException("Vị trí truy cập không hợp lệ: " + i);
		return ds.get(i);
	}
}
