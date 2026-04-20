package dao;

import java.io.File;
import java.io.FileInputStream;
import entity.NhanVien;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class Luu {
	// ===================== HẰNG SỐ (CONSTANTS) =====================
	private static final String BIN_FILE = "data/dsNhanVien.bin";

	// ===================== CÁC PHƯƠNG THỨC =====================
	public static void luuFile(List<NhanVien> ds) {
		File file = new File(BIN_FILE);
		file.getParentFile().mkdirs();
		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
			oos.writeObject(ds);
		} catch (Exception e) {
			throw new RuntimeException("Lá»—i khi lÆ°u file: " + e.getMessage());
		}
	}

	@SuppressWarnings("unchecked")
	public static List<NhanVien> docFile() {
		File file = new File(BIN_FILE);
		if (!file.exists())
			return new ArrayList<>();
		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
			return (List<NhanVien>) ois.readObject();
		} catch (Exception e) {
			throw new RuntimeException("Lá»—i khi Ä‘á»c file: " + e.getMessage());
		}
	}
}

