package ui;

import java.awt.Color;
import java.awt.Font;
import dao.DSNV;
import entity.NhanVien;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

public class GUI extends JFrame implements ActionListener {

	// ===================== HẰNG SỐ (CONSTANTS) =====================
	private static final long serialVersionUID = 1L;
	private static final String[] DS_PHONG_BAN = { "Kỹ Thuật", "Kế Toán", "Nhân Sự" };

	// ===================== CÁC THUỘC TÍNH (FIELDS) =====================
	private JTextField txtMa, txtHo, txtTen, txtTuoi, txtSoThang, txtTienLuong, txtThucNhan, txtTim;
	private JComboBox<String> cbophongBan;
	private JTable table;
	private DefaultTableModel model;
	private JButton btnTim, btnThem, btnXoaTrang, btnXoa, btnLuu;
	private DSNV dsnv;

	// ===================== HÀM KHỞI TẠO (CONSTRUCTOR) =====================
	public GUI() {
		super("Thông Tin Nhân Viên");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(800, 670);
		setLocationRelativeTo(null);
		setResizable(false);

		dsnv = new DSNV();
		createUI();
		napLenBang();
		dangKySuKien();
		setVisible(true);
	}

	// ===================== TẠO GIAO DIỆN (UI CREATION) =====================
	private void createUI() {
		setLayout(null);

		// --- Panel nhập liệu ---
		JPanel pninput = new JPanel(null);
		pninput.setBounds(5, 5, 770, 340);
		add(pninput);

		JLabel lblthongtin = new JLabel("Thông tin nhân viên");
		lblthongtin.setBounds(280, 10, 300, 25);
		lblthongtin.setFont(new Font("Arial", Font.BOLD, 25));
		lblthongtin.setForeground(Color.BLUE);
		pninput.add(lblthongtin);

		// Mã
		JLabel lblma = new JLabel("Mã nhân viên:");
		lblma.setBounds(10, 50, 100, 25);
		pninput.add(lblma);
		txtMa = new JTextField("NV07");
		txtMa.setBounds(120, 50, 650, 25);
		pninput.add(txtMa);

		// Họ
		JLabel lblho = new JLabel("Họ:");
		lblho.setBounds(10, 100, 100, 25);
		pninput.add(lblho);
		txtHo = new JTextField("Võ");
		txtHo.setBounds(120, 100, 250, 25);
		pninput.add(txtHo);

		// Tên
		JLabel lblten = new JLabel("Tên nhân viên:");
		lblten.setBounds(380, 100, 130, 25);
		pninput.add(lblten);
		txtTen = new JTextField("Linh");
		txtTen.setBounds(520, 100, 250, 25);
		pninput.add(txtTen);

		// Tuổi
		JLabel lbltuoi = new JLabel("Tuổi:");
		lbltuoi.setBounds(10, 150, 100, 25);
		pninput.add(lbltuoi);
		txtTuoi = new JTextField("21");
		txtTuoi.setBounds(120, 150, 400, 25);
		pninput.add(txtTuoi);

		// Phòng ban
		JLabel lblphongBan = new JLabel("Phòng ban:");
		lblphongBan.setBounds(530, 150, 100, 25);
		pninput.add(lblphongBan);
		cbophongBan = new JComboBox<>(DS_PHONG_BAN);
		cbophongBan.setBounds(620, 150, 150, 25);
		pninput.add(cbophongBan);

		// Số tháng
		JLabel lblthang = new JLabel("Tháng:");
		lblthang.setBounds(10, 200, 100, 25);
		pninput.add(lblthang);
		txtSoThang = new JTextField("3");
		txtSoThang.setBounds(120, 200, 650, 25);
		pninput.add(txtSoThang);

		// Lương
		JLabel lblluong = new JLabel("Lương:");
		lblluong.setBounds(10, 250, 100, 25);
		pninput.add(lblluong);
		txtTienLuong = new JTextField("70000000");
		txtTienLuong.setBounds(120, 250, 650, 25);
		pninput.add(txtTienLuong);

		// Thực nhận
		JLabel lblthucnhan = new JLabel("Thực Nhận:");
		lblthucnhan.setBounds(10, 300, 100, 25);
		pninput.add(lblthucnhan);
		txtThucNhan = new JTextField("210000000");
		txtThucNhan.setEditable(false);
		txtThucNhan.setBounds(120, 300, 650, 25);
		pninput.add(txtThucNhan);

		// --- Panel bảng ---
		JPanel pntable = new JPanel(null);
		pntable.setBounds(10, 360, 770, 200);
		add(pntable);

		String[] cols = { "Mã NV", "Họ", "Tên", "Tuổi", "Phòng ban", "Số tháng", "Lương", "Thực Nhận" };
		model = new DefaultTableModel(cols, 0) {
			@Override
			public boolean isCellEditable(int row, int col) {
				return col != 0;
			}
		};

		table = new JTable(model);
		table.getColumnModel().getColumn(4).setCellEditor(new DefaultCellEditor(new JComboBox<>(DS_PHONG_BAN)));

		JScrollPane scroll = new JScrollPane(table);
		scroll.setBounds(0, 0, 770, 200);
		pntable.add(scroll);

		// --- Panel trái (tìm kiếm) ---
		JPanel pntrai = new JPanel(null);
		pntrai.setBounds(10, 570, 380, 60);
		pntrai.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		add(pntrai);

		JLabel lbltim = new JLabel("Nhập mã cần tìm:");
		lbltim.setBounds(10, 15, 150, 25);
		pntrai.add(lbltim);
		txtTim = new JTextField();
		txtTim.setBounds(130, 15, 120, 25);
		pntrai.add(txtTim);
		btnTim = new JButton("Tìm");
		btnTim.setBounds(270, 15, 70, 25);
		pntrai.add(btnTim);

		// --- Panel phải (thao tác) ---
		JPanel pnPhai = new JPanel(null);
		pnPhai.setBounds(400, 570, 380, 60);
		pnPhai.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		add(pnPhai);

		btnThem = new JButton("Thêm");
		btnThem.setBounds(20, 15, 70, 25);
		pnPhai.add(btnThem);

		btnXoaTrang = new JButton("Xóa trắng");
		btnXoaTrang.setBounds(100, 15, 110, 25);
		pnPhai.add(btnXoaTrang);

		btnXoa = new JButton("Xóa");
		btnXoa.setBounds(220, 15, 70, 25);
		pnPhai.add(btnXoa);

		btnLuu = new JButton("Lưu");
		btnLuu.setBounds(300, 15, 70, 25);
		pnPhai.add(btnLuu);
	}

	// ===================== NẠP DỮ LIỆU LÊN BẢNG =====================
	private void napLenBang() {
		model.setRowCount(0);
		for (NhanVien nv : dsnv.getDs()) {
			model.addRow(nv.toRow());
		}
	}

	// ===================== ĐĂNG KÝ SỰ KIỆN =====================
	private void dangKySuKien() {
		// Buttons
		btnThem.addActionListener(this);
		btnLuu.addActionListener(this);
		btnXoa.addActionListener(this);
		btnXoaTrang.addActionListener(this);
		btnTim.addActionListener(this);

		// Tự động tính Thực Nhận khi gõ vào form
		KeyAdapter calc = new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				tinhThucNhanForm();
			}
		};
		txtTienLuong.addKeyListener(calc);
		txtSoThang.addKeyListener(calc);

		// Chọn dòng trên bảng → điền vào form
		table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				if (!e.getValueIsAdjusting()) {
					int row = table.getSelectedRow();
					if (row >= 0)
						dienVaoForm(row);
				}
			}
		});
	}

	// ===================== HÀM HỖ TRỢ FORM =====================
	private void dienVaoForm(int row) {
		NhanVien nv = dsnv.get(row);
		if (nv == null)
			return;
		txtMa.setText(nv.getMa());
		txtHo.setText(nv.getHo());
		txtTen.setText(nv.getTen());
		txtTuoi.setText(String.valueOf(nv.getTuoi()));
		cbophongBan.setSelectedItem(nv.getPhongBan());
		txtSoThang.setText(String.valueOf(nv.getSoThang()));
		txtTienLuong.setText(String.valueOf(nv.getTienLuong()));
		txtThucNhan.setText(String.valueOf(nv.getThucNhan()));
	}

	private void tinhThucNhanForm() {
		try {
			double luong = Double.parseDouble(txtTienLuong.getText().trim());
			int thang = Integer.parseInt(txtSoThang.getText().trim());
			txtThucNhan.setText(String.valueOf(luong * thang));
		} catch (Exception e) {
			txtThucNhan.setText("");
		}
	}

	private NhanVien docForm() {
		String ma = txtMa.getText().trim();
		if (ma.isBlank()) {
			txtMa.requestFocusInWindow();
			throw new IllegalArgumentException("Mã không được để trống");
		}

		String ho = txtHo.getText().trim();
		if (ho.isBlank()) {
			txtHo.requestFocusInWindow();
			throw new IllegalArgumentException("Họ không được để trống");
		}

		String ten = txtTen.getText().trim();
		if (ten.isBlank()) {
			txtTen.requestFocusInWindow();
			throw new IllegalArgumentException("Tên không được để trống");
		}

		int tuoi;
		try {
			tuoi = Integer.parseInt(txtTuoi.getText().trim());
		} catch (Exception e) {
			txtTuoi.requestFocusInWindow();
			throw new IllegalArgumentException("Tuổi phải là số nguyên");
		}

		String pb = (String) cbophongBan.getSelectedItem();
		if (pb == null || pb.isBlank()) {
			cbophongBan.requestFocusInWindow();
			throw new IllegalArgumentException("Phòng ban không được để trống");
		}

		int thang;
		try {
			thang = Integer.parseInt(txtSoThang.getText().trim());
		} catch (Exception e) {
			txtSoThang.requestFocusInWindow();
			throw new IllegalArgumentException("Tháng phải là số nguyên");
		}

		double luong;
		try {
			luong = Double.parseDouble(txtTienLuong.getText().trim());
		} catch (Exception e) {
			txtTienLuong.requestFocusInWindow();
			throw new IllegalArgumentException("Lương phải là số");
		}

		return new NhanVien(ma, ho, ten, tuoi, pb, thang, luong);
	}

	private List<NhanVien> docTable() {
		List<NhanVien> list = new ArrayList<>();
		for (int i = 0; i < table.getRowCount(); i++) {
			String ma = model.getValueAt(i, 0).toString();
			String ho = model.getValueAt(i, 1).toString();
			String ten = model.getValueAt(i, 2).toString();
			int tuoi = Integer.parseInt(model.getValueAt(i, 3).toString());
			String phongBan = model.getValueAt(i, 4).toString();
			int thang = Integer.parseInt(model.getValueAt(i, 5).toString());
			double luong = Double.parseDouble(model.getValueAt(i, 6).toString());
			list.add(new NhanVien(ma, ho, ten, tuoi, phongBan, thang, luong));
		}
		return list;
	}

	// ===================== BỘ LẮNG NGHE SỰ KIỆN =====================
	@Override
	public void actionPerformed(ActionEvent e) {
		Object src = e.getSource();
		if (src == btnThem) {
			xuLyThem();
		} else if (src == btnLuu) {
			xuLyLuu();
		} else if (src == btnXoa) {
			xuLyXoa();
		} else if (src == btnXoaTrang) {
			xoaTrang();
		} else if (src == btnTim) {
			xuLyTim();
		}
	}

	// ===================== XỬ LÝ NGHIỆP VỤ TỪ NÚT BẤM =====================
	private void xuLyThem() {
		try {
			NhanVien nv = docForm();
			dsnv.them(nv);
			model.addRow(nv.toRow());
			showInfo("Thêm nhân viên " + nv.getMa() + " thành công!");
		} catch (Exception ex) {
			showError(ex.getMessage());
		}
	}

	private void xuLyLuu() {
		try {
			if (table.isEditing())
				table.getCellEditor().stopCellEditing();
			List<NhanVien> list = docTable();
			dsnv.setDs(list);
			napLenBang();
			showInfo("Lưu thành công!");
		} catch (Exception ex) {
			showError(ex.getMessage());
		}
	}

	private void xuLyXoa() {
		int[] rows = table.getSelectedRows();
		if (rows.length == 0) {
			showError("Vui lòng chọn nhân viên cần xóa!");
			return;
		}

		String message = "Bạn có chắc muốn xóa nhân viên đã chọn?";
		int confirm = JOptionPane.showConfirmDialog(this, message, "Xác nhận xóa", JOptionPane.YES_NO_OPTION,
				JOptionPane.WARNING_MESSAGE);
		if (confirm != JOptionPane.YES_OPTION) {
			return;
		}

		try {
			for (int i = rows.length - 1; i >= 0; i--) {
				dsnv.xoa(rows[i]);
			}
			napLenBang();
			xoaTrang();
			showInfo("Xóa thành công!");
		} catch (Exception ex) {
			showError(ex.getMessage());
		}
	}

	private void xuLyTim() {
		String ma = txtTim.getText().trim();
		if (ma.isBlank()) {
			showError("Vui lòng nhập mã cần tìm");
			return;
		}
		xoaTrang();
		table.clearSelection();
		int idx = dsnv.timTheoMa(ma);
		if (idx < 0) {
			showError("Không tìm thấy mã: " + ma);
		} else {
			table.setRowSelectionInterval(idx, idx);
			table.scrollRectToVisible(table.getCellRect(idx, 0, true));
			dienVaoForm(idx);
		}
	}

	private void xoaTrang() {
		txtMa.setText("");
		txtHo.setText("");
		txtTen.setText("");
		txtTuoi.setText("");
		cbophongBan.setSelectedIndex(0);
		txtSoThang.setText("");
		txtTienLuong.setText("");
		txtThucNhan.setText("");
		table.clearSelection();
		txtTim.setText("");
	}

	// ===================== CỬA SỔ THÔNG BÁO =====================
	private void showError(String msg) {
		JOptionPane.showMessageDialog(this, msg, "Lỗi", JOptionPane.ERROR_MESSAGE);
	}

	private void showInfo(String msg) {
		JOptionPane.showMessageDialog(this, msg, "Thông báo", JOptionPane.INFORMATION_MESSAGE);
	}

	// ===================== HÀM CHÍNH (MAIN) =====================
	public static void main(String[] args) {
		new GUI();
	}
}
