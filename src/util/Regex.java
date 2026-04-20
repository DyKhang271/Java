package util;

public class Regex {
	private Regex() {
	}

	// MÃ£ nhÃ¢n viÃªn: NV + 2 hoáº·c 3 chá»¯ sá»‘. VÃ­ dá»¥: NV01, NV12, NV123
	public static final String MA_NV_REGEX = "^NV\\d{2,3}$";

	// Há»/tÃªn: chá»‰ chá»©a chá»¯ cÃ¡i Unicode, cÃ³ thá»ƒ cÃ³ khoáº£ng tráº¯ng giá»¯a cÃ¡c tá»«
	public static final String HO_TEN_REGEX = "^[\\p{L}]+(?:\\s[\\p{L}]+)*$";

	public static boolean isValidMaNhanVien(String ma) {
		return ma != null && ma.trim().toUpperCase().matches(MA_NV_REGEX);
	}

	public static boolean isValidHoTen(String text) {
		return text != null && text.trim().matches(HO_TEN_REGEX);
	}
}

