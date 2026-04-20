package util;

public class Regex {
	private Regex() {
	}

	// Mã nhân viên: NV + 2 hoặc 3 chữ số. Ví dụ: NV01, NV12, NV123
	public static final String MA_NV_REGEX = "^NV\\d{2,3}$";

	// Họ/tên: chỉ chứa chữ cái Unicode, có thể có khoảng trắng giữa các từ
	public static final String HO_TEN_REGEX = "^[\\p{L}]+(?:\\s[\\p{L}]+)*$";

	public static boolean isValidMaNhanVien(String ma) {
		return ma != null && ma.trim().toUpperCase().matches(MA_NV_REGEX);
	}

	public static boolean isValidHoTen(String text) {
		return text != null && text.trim().matches(HO_TEN_REGEX);
	}
}
