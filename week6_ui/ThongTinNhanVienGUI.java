package week6_ui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DecimalFormat;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import week6_dao.NhanVienDAO;
import week6_dao.PhongBanDAO;
import week6_entity.NhanVien;
import week6_entity.PhongBan;

public class ThongTinNhanVienGUI extends JFrame implements ActionListener {
	
	JTextField txtMa, txtHo, txtTen, txtTuoi, txtLuong, txtTim;
	JCheckBox cbPhai;
	JComboBox< PhongBan> cboPhongBan, cboTablePhongBan;
	JComboBox<String> cboPhai;
	JTable table;
	DefaultTableModel model;
	JButton btnTim, btnThem, btnXoa, btnXoaTrang, btnLoc;
	NhanVienDAO nhanVienDao = new NhanVienDAO();
	PhongBanDAO phongBanDAO = new PhongBanDAO();
	
	public ThongTinNhanVienGUI() {
		setTitle("Trần Duy Khang - 23728961");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setBounds(100, 100, 1100, 600);
		createUI();
		
		setVisible(true);
	}

	private void createUI() {
		JPanel pnMain = new JPanel(new BorderLayout());
		pnMain.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		add(pnMain);
		
		Font fontLabel = new Font("Segoe UI", Font.PLAIN, 14);
		Font fontTitle = new Font("Segoe UI", Font.BOLD, 28);
	
		
		//North
		JPanel pnNorth = new JPanel(new FlowLayout(FlowLayout.CENTER));
		pnMain.add(pnNorth, BorderLayout.NORTH);
		JLabel lblTitle = new JLabel("THÔNG TIN NHÂN VIÊN");
		lblTitle.setFont(fontTitle);
		lblTitle.setForeground(new Color(0, 102, 204));
		pnNorth.add(lblTitle);
		
		//Center
		JPanel pnCenter = new JPanel(new BorderLayout());
		pnMain.add(pnCenter, BorderLayout.CENTER);
		
		//Nhap
		JPanel pnNhap = new JPanel(new GridLayout(4, 1, 10, 10));
		pnCenter.add(pnNhap, BorderLayout.NORTH);
		
		//Nhap ma
		JPanel pnMa = new JPanel(new BorderLayout());
		JLabel lblMa = new JLabel("Mã nhân viên:");
		lblMa.setFont(fontLabel);
		lblMa.setPreferredSize(new Dimension(120, 30));
		txtMa = new JTextField("NV0121");
		pnMa.add(lblMa, BorderLayout.WEST);
		pnMa.add(txtMa, BorderLayout.CENTER);
		pnNhap.add(pnMa);
		
		//Nhap ho ten
		JPanel pnHoTen = new JPanel(new GridLayout(1,2,10,10));
		pnNhap.add(pnHoTen);
		
		JPanel pnHo = new JPanel(new BorderLayout());
		JLabel lblHo = new JLabel("Họ:");
		lblHo.setFont(fontLabel);
		lblHo.setPreferredSize(new Dimension(120, 30));
		txtHo = new JTextField("Trần");
		pnHo.add(lblHo, BorderLayout.WEST);
		pnHo.add(txtHo, BorderLayout.CENTER);
		pnHoTen.add(pnHo);
		
		JPanel pnTen = new JPanel(new BorderLayout());
		JLabel lblTen = new JLabel("Tên:");
		lblTen.setFont(fontLabel);
		lblTen.setPreferredSize(new Dimension(120, 30));
		txtTen = new JTextField("Duy Khang");
		pnTen.add(lblTen, BorderLayout.WEST);
		pnTen.add(txtTen, BorderLayout.CENTER);
		pnHoTen.add(pnTen);
		
		//Nhap tuoi + phai
		JPanel pnTuoiPhai = new JPanel(new BorderLayout());
		pnNhap.add(pnTuoiPhai);
		
		JPanel pnTuoi = new JPanel(new BorderLayout());
		JLabel lblTuoi = new JLabel("Tuổi:");
		lblTuoi.setFont(fontLabel);
		lblTuoi.setPreferredSize(new Dimension(120, 30));
		txtTuoi = new JTextField("20");
		pnTuoi.add(lblTuoi, BorderLayout.WEST);
		pnTuoi.add(txtTuoi, BorderLayout.CENTER);
		pnTuoiPhai.add(pnTuoi, BorderLayout.CENTER);
		
		JPanel pnPhai = new JPanel(new BorderLayout());
		JLabel lblPhai = new JLabel("Phái:");
		lblPhai.setFont(fontLabel);
		lblPhai.setPreferredSize(new Dimension(120, 30));
		cbPhai = new JCheckBox("Nữ");		
		pnPhai.add(lblPhai, BorderLayout.WEST);
		pnPhai.add(cbPhai, BorderLayout.CENTER);
		pnTuoiPhai.add(pnPhai, BorderLayout.EAST);
		
		//Nhap luong  + Phong
		JPanel pnLuongPhong = new JPanel(new BorderLayout());
		pnNhap.add(pnLuongPhong);
		
		JPanel pnLuong = new JPanel(new BorderLayout());
		JLabel lblLuong = new JLabel("Lương:");
		lblLuong.setFont(fontLabel);
		lblLuong.setPreferredSize(new Dimension(120, 30));
		txtLuong = new JTextField("15000000");
		pnLuong.add(lblLuong, BorderLayout.WEST);
		pnLuong.add(txtLuong, BorderLayout.CENTER);
		pnLuongPhong.add(pnLuong, BorderLayout.CENTER);
		
		JPanel pnPhong = new JPanel(new BorderLayout());
		
		pnPhong.setPreferredSize(new Dimension(220, 30));
		JLabel lblPhong = new JLabel("Phòng Ban:");
		lblPhong.setFont(fontLabel);
		lblPhong.setPreferredSize(new Dimension(120, 30));
		cboPhongBan = new  JComboBox<PhongBan>();
		pnPhong.add(lblPhong, BorderLayout.WEST);
		pnPhong.add(cboPhongBan, BorderLayout.CENTER);
		pnLuongPhong.add(pnPhong, BorderLayout.EAST);
		
		//Table
		JPanel pnTable = new JPanel(new BorderLayout());
		pnCenter.add(pnTable, BorderLayout.CENTER);
		
		model = new DefaultTableModel(new String[] {
				"Mã NV", "Họ NV", "Tên NV", "Tuổi", "Phái", "Phòng Ban", "Lương"
		}, 0);
		table = new JTable(model);
		JScrollPane scroll = new JScrollPane(table);
		pnTable.add(scroll);
		
		cboTablePhongBan = new JComboBox<PhongBan>();
		loadComboBoxPhongBan(cboTablePhongBan);
		table.getColumnModel().getColumn(5).setCellEditor(new DefaultCellEditor(cboTablePhongBan));
		
		cboPhai = new JComboBox<String>();
		cboPhai.addItem("Nam");
		cboPhai.addItem("Nữ");
		
		table.getColumnModel().getColumn(4).setCellEditor(new DefaultCellEditor(cboPhai));
//		table.getColumnModel().getColumn(3).setCellEditor(new DefaultCellEditor(cboTablePhongBan));
		
		table.addMouseListener(new MouseAdapter() {
		    @Override
		    public void mouseClicked(MouseEvent e) {
		        int row = table.getSelectedRow();
		        if (row >= 0) {
		            showTableToForm(row);
		        }
		    }
		});
		
		//South
		JPanel pnSouth = new JPanel(new GridLayout(1, 2, 10, 10));
		pnMain.add(pnSouth, BorderLayout.SOUTH);
		
		JPanel pnTim = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		pnSouth.add(pnTim);
		JLabel lblTim = new JLabel("Nhập mã muốn tìm");
		lblTim.setFont(fontLabel);
		btnTim = new JButton("Tìm");
		txtTim = new JTextField();
		txtTim.setPreferredSize(new Dimension(120, 30));
		pnTim.add(lblTim);
		pnTim.add(txtTim);
		pnTim.add(btnTim);
		
		JPanel pnButton = new JPanel(new FlowLayout(FlowLayout.LEFT));
		pnSouth.add(pnButton);
		pnButton.add(btnThem = new JButton("Thêm")); 
		pnButton.add(btnXoaTrang = new JButton("Xóa trắng")); 
		pnButton.add(btnXoa = new JButton("Xóa")); 
		pnButton.add(btnLoc = new JButton("Lọc theo phòng ban")); 
		
		btnThem.addActionListener(this);
		btnXoa.addActionListener(this);
		btnXoaTrang.addActionListener(this);
		btnLoc.addActionListener(this);
		btnTim.addActionListener(this);
		
		
		loadDataToTable();
		loadComboBoxPhongBan(cboPhongBan);
		
	}
	
	

	

	public static void main(String[] args) {
		new ThongTinNhanVienGUI();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == btnThem) {
			themNhanVien();
		}
		if(e.getSource() == btnXoa) {
			xoaNhanVien();
		}
		if(e.getSource() == btnXoaTrang) {
			txtMa.setText("");
			txtHo.setText("");
			txtTen.setText("");
			txtTuoi.setText("");
			txtLuong.setText("");
			cbPhai.setSelected(false);
			if(cboPhongBan.getItemCount() > 0) {
				cboPhongBan.setSelectedIndex(0);
			}
			
		}
		
		if(e.getSource() == btnLoc) {
			PhongBan pb = (PhongBan)cboPhongBan.getSelectedItem();
			String maPB = pb.getMaPB();
			if(maPB.equalsIgnoreCase("Tat ca") || maPB.equalsIgnoreCase("Tất cả")) {
				loadDataToTable();
			}else {
				ArrayList<NhanVien> list = nhanVienDao.getNhanVienTheoPhongBan(maPB);
				loadDataToTable(list);
			    if(list.isEmpty()) {
			    	JOptionPane.showMessageDialog(
			    			this,
			    			"Không có nhân viên nào thuộc phòng ban này",
			    			"Lỗi",
			    			JOptionPane.ERROR_MESSAGE);
			    }else {
			    	table.setRowSelectionInterval(0, 0);
		            showTableToForm(0);
			    }
			}
		}
		if(e.getSource() == btnTim) {
			String tim = txtTim.getText().trim();
			if(tim.isEmpty()) {
				JOptionPane.showMessageDialog(
						this,
						"Vui lòng nhập thông tin muốn tìm",
						"Lỗi",
						JOptionPane.WARNING_MESSAGE);
				return;
			}
			ArrayList<NhanVien> list = nhanVienDao.findListNhanVien(tim);
			loadDataToTable(list);

		    if (list.isEmpty()) {
		        JOptionPane.showMessageDialog(this, "Không tìm thấy");
		    }else {
		    	table.setRowSelectionInterval(0, 0);
		    	showTableToForm(0);
		    }
			
		}
	}
	
	
	private void showTableToForm(int row) {
		txtMa.setText(model.getValueAt(row, 0).toString());
		txtHo.setText(model.getValueAt(row, 1).toString());
		txtTen.setText(model.getValueAt(row, 2).toString());
		txtTuoi.setText(model.getValueAt(row, 3).toString());
		String phai = model.getValueAt(row, 4).toString();
	    cbPhai.setSelected(phai.equalsIgnoreCase("Nữ") || phai.equalsIgnoreCase("Nu"));
	    String tenPB = model.getValueAt(row, 5).toString();
		for (int i = 0; i < cboPhongBan.getItemCount(); i++) {
	        PhongBan pb = cboPhongBan.getItemAt(i);
	        if (pb.getTenPB().equalsIgnoreCase(tenPB)) {
	            cboPhongBan.setSelectedIndex(i);
	            break;
	        }
	    }
		txtLuong.setText(model.getValueAt(row, 6).toString());
		
		
	}

	private void loadComboBoxPhongBan(JComboBox<PhongBan> cbo) {
		cbo.removeAllItems();
		if(cbo == cboPhongBan) {
			cbo.addItem(new PhongBan("Tat ca", "Tất cả"));
		}
		ArrayList<PhongBan> dsPB = phongBanDAO.getListPhongBan();
		for(PhongBan pb:dsPB) {
			cbo.addItem(pb);
		}
		
	}
	private void themNhanVien() {
		NhanVien nv = getNhanVien();
		if (nv == null)
			return;
		
		if(kiemTraTrungMa(nv.getMaNV())) {
			JOptionPane.showMessageDialog(
					this,
					"Mã nhân viên không được trùng",
					"Lỗi",
					JOptionPane.WARNING_MESSAGE);
			txtMa.requestFocus();
			txtMa.selectAll();
			return;
		}
		if(nhanVienDao.themNhanVien(nv)) {
			JOptionPane.showMessageDialog(
					this,
					"Thêm thành công",
					"Thông báo",
					JOptionPane.INFORMATION_MESSAGE);
			loadDataToTable();
		}
		
		
		
	}
	
	private void loadDataToTable() {
		loadDataToTable(nhanVienDao.getListNhanVien());
		
	}

	private void loadDataToTable(ArrayList<NhanVien> list) {
		DecimalFormat df = new DecimalFormat("#,###");
		
		
		model.setRowCount(0);
		
		for(NhanVien nv: list) {
			model.addRow(new Object[] {
					nv.getMaNV(),
					nv.getHoNV(),
					nv.getTenNV(),
					nv.getTuoi(),
					nv.isNu()?"Nữ":"Nam",
					nv.getPhongBan().getTenPB(),
					df.format(nv.getTienLuong())
							
			});
		}
		
	}

	private boolean kiemTraTrungMa(String maNV) {
		for (int i = 0; i < model.getRowCount(); i++) {
	        String ma = model.getValueAt(i, 0).toString().trim();
	        if (ma.equalsIgnoreCase(maNV.trim())) {
	            return true;
	        }
	    }
	    return false;
	}



	private NhanVien getNhanVien() {
		String ma = txtMa.getText().trim();
		String ho = txtHo.getText().trim();
		String ten = txtTen.getText().trim();
		String tuoi = txtTuoi.getText().trim();
		String luong = txtLuong.getText().trim();
		boolean nu = cbPhai.isSelected();
		PhongBan phongBan = (PhongBan) cboPhongBan.getSelectedItem();
		
		if(ma.isEmpty() || ho.isEmpty() || ten.isEmpty() || tuoi.isEmpty() || luong.isEmpty()  ) {
			JOptionPane.showMessageDialog(this, "Vui lòng nhập đầy đủ thông tin");
			return null;
		}
		int tuoiInt;
		try {
			tuoiInt = Integer.parseInt(tuoi);
			if(tuoiInt <= 0 ) {
				JOptionPane.showMessageDialog(this, "Tuổi phải lớn hơn 0");
				return null;
			}
		}catch (Exception e) {
			JOptionPane.showMessageDialog(this, "Tuổi phải là số");
			return null;
		}
		double luongDouble;
		try {
			luongDouble = Double.parseDouble(luong);
			if(luongDouble < 0 ) {
				JOptionPane.showMessageDialog(this, "Lương phải lớn hơn hoặc bằng 0");
				return null;
			}
		}catch (Exception e) {
			JOptionPane.showMessageDialog(this, "Lương phải là số");
			return null;
		}
		
		NhanVien nv = new NhanVien(ma, ho, ten, tuoiInt, nu,  phongBan, luongDouble);
		
		return nv;
	}

	private void xoaNhanVien() {
		int[] rows = table.getSelectedRows();
		if(rows.length == 0) {
			JOptionPane.showMessageDialog(
					this,
					"Vui lòng chọn dòng muốn xóa",
					"Thông báo",
					JOptionPane.INFORMATION_MESSAGE);
			return;
		}
		
		int choice = JOptionPane.showConfirmDialog(
				this,
				"Bạn chắc chắn muốn xóa?",
				"Xác nhận",
				JOptionPane.YES_NO_OPTION,
				JOptionPane.QUESTION_MESSAGE);
		if(choice == JOptionPane.YES_OPTION) {
			boolean xoa = true;
			for(int i = rows.length -1; i>=0; i--) {
					String maNV = model.getValueAt(rows[i], 0).toString();
					boolean ketQua = nhanVienDao.xoaNhanVien(maNV);
					
					if(!ketQua) {
						xoa = false;
					}
					
			}
			loadDataToTable();
			if(xoa) {
				JOptionPane.showMessageDialog(
						this,
						"Xóa thành công",
						"Thông báo",
						JOptionPane.INFORMATION_MESSAGE);
			}else {
				JOptionPane.showMessageDialog(
						this,
						"Xóa thất bại",
						"Thông báo",
						JOptionPane.INFORMATION_MESSAGE);
			}
		}
		
	}
}
