package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.XMLEncoder;
import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.nio.file.Paths;
import java.util.ArrayList;

import javax.print.DocFlavor.URL;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

import model.Student;
import model.StudentManager;
import javax.swing.SwingConstants;
import java.awt.SystemColor;
import java.awt.Toolkit;

import javax.swing.UIManager;
import javax.swing.JSeparator;
import javax.swing.SwingWorker;

public class QLKTXview extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField Tkmasv;
	private JTextField masv;
	private JTextField ten;
	private JTextField phong;
	private JTextField lop;
	private JTextField khu;
	private StudentManager manager;
	private DefaultTableModel model;

	private JTable table_1;
	private int currentId = 1;
	private JTextField tkSoPhong;
    private ChatServerWindow serverWindow;

	public QLKTXview() {
		this.manager = new StudentManager();
		this.setTitle("PHẠM MINH NHẬT");
		java.net.URL urlIconNotepad = QLKTXview.class.getResource("PMN.jpg");
		Image img = Toolkit.getDefaultToolkit().createImage(urlIconNotepad);
        this.setIconImage(img);
        
        // Khởi tạo cửa sổ server
        serverWindow = new ChatServerWindow();
        serverWindow.setVisible(true);
    
     
        // Bố trí giao diện
		JPanel panel = new JPanel();
		panel.setForeground(new Color(255, 0, 0));
		panel.setBackground(SystemColor.controlHighlight);
		panel.setLayout(null);

		getContentPane().add(panel);
		

		JButton btnXoa = new JButton("Xóa");
		btnXoa.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().createImage(QLKTXview.class.getResource("xoa.png"))));
		btnXoa.setBackground(new Color(255, 255, 0));
		btnXoa.setForeground(new Color(0, 255, 0));
		btnXoa.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// Lấy chỉ số dòng được chọn
				int selectedRow = table_1.getSelectedRow();

				// Kiểm tra xem có dòng nào được chọn không
				if (selectedRow != -1) {
					// Lấy giá trị cột "Masv" từ dòng được chọn
					String masvToDelete = (String) table_1.getValueAt(selectedRow, 1);

					// Hiển thị hộp thoại xác nhận
					int option = JOptionPane.showConfirmDialog(QLKTXview.this,
							"Bạn có muốn xóa sinh viên có mã sinh viên là " + masvToDelete + " không? ", "Xác nhận xóa",
							JOptionPane.YES_NO_OPTION);

					// Nếu người dùng chọn YES, thực hiện xóa
					if (option == JOptionPane.YES_OPTION) {
						// Thực hiện xóa sinh viên từ cơ sở dữ liệu
						boolean success = manager.delete(masvToDelete);

						if (success) {
							// Xóa dòng từ DefaultTableModel
							DefaultTableModel model = (DefaultTableModel) table_1.getModel();
							model.removeRow(selectedRow);

							// Cập nhật JTable
							table_1.setModel(model);

							masv.setText("");
							ten.setText("");
							lop.setText("");
							phong.setText("");
							khu.setText("");
							

							// Hiển thị thông báo thành công
							JOptionPane.showMessageDialog(QLKTXview.this, "Sinh viên đã được xóa khỏi cơ sở dữ liệu.");
						} else {
							JOptionPane.showMessageDialog(QLKTXview.this, "Không thể xóa sinh viên.", "Lỗi",
									JOptionPane.ERROR_MESSAGE);
						}
					}
				} else {
					JOptionPane.showMessageDialog(QLKTXview.this, "Vui lòng chọn một sinh viên để xóa!", "Thông báo",
							JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});

			
		

		btnXoa.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnXoa.setBounds(205, 572, 120, 46);
		panel.add(btnXoa);

		JLabel lblMasv = new JLabel("Mã SV:");
		lblMasv.setForeground(new Color(0, 0, 255));
		lblMasv.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblMasv.setBounds(32, 391, 75, 28);
		panel.add(lblMasv);

		JLabel lblten = new JLabel("Tên:");
		lblten.setForeground(new Color(0, 0, 255));
		lblten.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblten.setBounds(47, 453, 60, 29);
		panel.add(lblten);

		JLabel lblphong = new JLabel("Phòng:");
		lblphong.setForeground(new Color(0, 0, 255));
		lblphong.setHorizontalAlignment(SwingConstants.LEFT);
		lblphong.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblphong.setBounds(376, 453, 67, 29);
		panel.add(lblphong);
		
		JLabel lblKhu = new JLabel("Khu:");
		lblKhu.setForeground(new Color(0, 0, 255));
		lblKhu.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblKhu.setBounds(241, 497, 75, 42);
		panel.add(lblKhu);

		masv = new JTextField();
		masv.setFont(new Font("Tahoma", Font.PLAIN, 16));
		masv.setColumns(10);
		masv.setBounds(117, 390, 197, 35);
		panel.add(masv);

		ten = new JTextField();
		ten.setFont(new Font("Tahoma", Font.PLAIN, 16));
		ten.setColumns(10);
		ten.setBounds(117, 452, 197, 35);
		panel.add(ten);

		lop = new JTextField();
		lop.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lop.setColumns(10);
		lop.setBounds(453, 390, 197, 34);
		panel.add(lop);

		phong = new JTextField();
		phong.setFont(new Font("Tahoma", Font.PLAIN, 16));
		phong.setColumns(10);
		phong.setBounds(453, 452, 197, 35);
		panel.add(phong);
		
		khu = new JTextField();
		khu.setFont(new Font("Tahoma", Font.PLAIN, 16));
		khu.setColumns(10);
		khu.setBounds(299, 504, 197, 35);
		panel.add(khu);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 178, 694, 182);
		panel.add(scrollPane);

		table_1 = new JTable();
		this.table_1.setRowHeight(25);
		table_1.setForeground(UIManager.getColor("InternalFrame.activeTitleGradient"));
		table_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int i = table_1.getSelectedRow();

				masv.setText(model.getValueAt(i, 1).toString());
				ten.setText(model.getValueAt(i, 2).toString());
				lop.setText(model.getValueAt(i, 3).toString());
				phong.setText(model.getValueAt(i, 4).toString());
				khu.setText(model.getValueAt(i, 5).toString());

			}
		});
		table_1.setBackground(UIManager.getColor("InternalFrame.activeTitleGradient"));
		model = new DefaultTableModel();
		Object[] column = { "ID", "Mã sinh viên", "Tên", "Lớp", "Phòng", "khu"};
		model.setColumnIdentifiers(column); // thay dổi tiêu đề cột
		table_1.setModel(model);
		scrollPane.setViewportView(table_1);

		JButton btnCapnhat = new JButton("Cập Nhật");
		btnCapnhat.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().createImage(QLKTXview.class.getResource("update.png"))));
		btnCapnhat.setBackground(new Color(255, 255, 0));
		btnCapnhat.setForeground(new Color(0, 255, 0));
		btnCapnhat.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int i = table_1.getSelectedRow();

				model.setValueAt(masv.getText(), i, 1);
				model.setValueAt(ten.getText(), i, 2);
				
			model.setValueAt(lop.getText(), i, 3);
			model.setValueAt(phong.getText(), i, 4);
			model.setValueAt(khu.getText(), i, 5);
			

			

			update();

		}
	});
	btnCapnhat.setFont(new Font("Tahoma", Font.PLAIN, 18));
	btnCapnhat.setBounds(376, 572, 141, 47);
	panel.add(btnCapnhat);

	JButton btnThem = new JButton("Thêm");
	btnThem.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().createImage(QLKTXview.class.getResource("add.png"))));
	btnThem.setBackground(new Color(255, 255, 0));
	btnThem.setForeground(new Color(0, 255, 0));
	btnThem.setBounds(32, 572, 120, 47);
	panel.add(btnThem);
	btnThem.addActionListener(new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			addStudent();
			String[] row = new String[5];
			row[0] = masv.getText();
			row[1] = ten.getText();
			row[2] = lop.getText();
			row[3] = phong.getText();
			row[4] = khu.getText();
			
            model.addRow(row);
			table_1.setModel(model);
		}
	});
	btnThem.setFont(new Font("Tahoma", Font.PLAIN, 18));

	JLabel lbllop = new JLabel("Lớp:");
	lbllop.setForeground(new Color(0, 0, 255));
	lbllop.setHorizontalAlignment(SwingConstants.LEFT);
	lbllop.setFont(new Font("Tahoma", Font.PLAIN, 20));
	lbllop.setBounds(388, 391, 67, 29);
	panel.add(lbllop);

	JButton btnhienthi = new JButton("Hiển thị");
	btnhienthi.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().createImage(QLKTXview.class.getResource("display.png"))));
	btnhienthi.setBackground(new Color(255, 255, 0));
	btnhienthi.setForeground(new Color(0, 255, 0));
	btnhienthi.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			hienthi();
		}
	});
	btnhienthi.setFont(new Font("Tahoma", Font.PLAIN, 18));
	btnhienthi.setBounds(545, 572, 141, 46);
	panel.add(btnhienthi);

	JPanel panel_1 = new JPanel();
	panel_1.setBackground(SystemColor.menu);
	panel_1.setBounds(32, 59, 638, 90);
	panel.add(panel_1);
	panel_1.setLayout(null);

	JButton btntimkiem = new JButton("Tìm Kiếm");
	btntimkiem.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().createImage(QLKTXview.class.getResource("search.png"))));
	btntimkiem.setBackground(new Color(255, 255, 0));
	btntimkiem.setBounds(94, 10, 132, 25);
	panel_1.add(btntimkiem);
	btntimkiem.setForeground(new Color(64, 0, 128));
	btntimkiem.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) { 
			TimKiemMasv();
			

		}

	});
	btntimkiem.setFont(new Font("Tahoma", Font.PLAIN, 18));

	Tkmasv = new JTextField();
	Tkmasv.setFont(new Font("Tahoma", Font.PLAIN, 16));
	Tkmasv.setBounds(163, 45, 111, 22);
	panel_1.add(Tkmasv);
	Tkmasv.setColumns(10);

	JLabel lbltkmasv = new JLabel("Nhập mã SV:");
	lbltkmasv.setForeground(new Color(0, 0, 255));
	lbltkmasv.setBounds(46, 45, 107, 22);
	panel_1.add(lbltkmasv);
	lbltkmasv.setFont(new Font("Tahoma", Font.PLAIN, 16));
	
	JLabel lbltk_Phong = new JLabel("Nhập số Phòng");
	lbltk_Phong.setForeground(Color.BLUE);
	lbltk_Phong.setFont(new Font("Tahoma", Font.PLAIN, 16));
	lbltk_Phong.setBounds(335, 45, 139, 22);
	panel_1.add(lbltk_Phong);
	
	tkSoPhong = new JTextField();
	tkSoPhong.setFont(new Font("Tahoma", Font.PLAIN, 16));
	tkSoPhong.setColumns(10);
	tkSoPhong.setBounds(466, 45, 111, 22);
	panel_1.add(tkSoPhong);
	
	JButton btntimkiem_1 = new JButton("Tìm Kiếm");
	btntimkiem_1.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().createImage(QLKTXview.class.getResource("search.png"))));
	btntimkiem_1.setForeground(new Color(64, 0, 128));
	btntimkiem_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
	btntimkiem_1.setBackground(Color.YELLOW);
	btntimkiem_1.setBounds(399, 10, 132, 25);
	panel_1.add(btntimkiem_1);
	btntimkiem_1.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			
			 TimKiemSoPhong();

		}

	});
	
	JLabel lblQuanLyKyTucXa = new JLabel("Quản Lý Ký Túc Xá VKU");
	lblQuanLyKyTucXa.setForeground(new Color(255, 0, 0));
	lblQuanLyKyTucXa.setHorizontalAlignment(SwingConstants.LEFT);
	lblQuanLyKyTucXa.setFont(new Font("Tahoma", Font.PLAIN, 21));
	lblQuanLyKyTucXa.setBounds(241, 10, 239, 29);
	panel.add(lblQuanLyKyTucXa);
	
	JSeparator separator = new JSeparator();
	separator.setBounds(10, 159, 694, 2);
	panel.add(separator);
	
	JSeparator separator_1 = new JSeparator();
	separator_1.setBounds(10, 370, 694, 11);
	panel.add(separator_1);
	
	JSeparator separator_2 = new JSeparator();
	separator_2.setBounds(10, 512, 694, -7);
	panel.add(separator_2);
	
	JSeparator separator_3 = new JSeparator();
	separator_3.setBounds(10, 549, 694, 2);
	panel.add(separator_3);
	
	JButton btnSaveToXML = new JButton("Lưu danh sách ");
	btnSaveToXML.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().createImage(QLKTXview.class.getResource("print.png"))));
	btnSaveToXML.setForeground(Color.GREEN);
	btnSaveToXML.setFont(new Font("Tahoma", Font.PLAIN, 18));
	btnSaveToXML.setBackground(Color.YELLOW);
	btnSaveToXML.setBounds(145, 652, 187, 47);
	panel.add(btnSaveToXML);

	btnSaveToXML.addActionListener(new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent e) {
	        saveToXML();
	    }
	});
	
	JButton btnChat = new JButton("Chat");
	btnChat.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().createImage(QLKTXview.class.getResource("chat.png"))));
	btnChat.setForeground(Color.GREEN);
	btnChat.setFont(new Font("Tahoma", Font.PLAIN, 18));
	btnChat.setBackground(Color.YELLOW);
	btnChat.setBounds(388, 652, 185, 46);
	panel.add(btnChat);
	btnChat.addActionListener(new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent e) {
	        openChatClientWindow();
	    }
	});
	
		

	

	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	setSize(728, 757);
	setLocationRelativeTo(null);
	setVisible(true);
	
}


 
// Mở cửa sổ client
private void openChatClientWindow() {
    String clientName = "Client " + (int) (Math.random() * 1000); // Tạo tên client ngẫu nhiên
    ChatClientWindow clientWindow = new ChatClientWindow(clientName);
    clientWindow.setVisible(true);

    // Kết nối client với server (việc này phụ thuộc vào cách bạn thiết lập kết nối)
    // Ví dụ: client.connectToServer(serverAddress);

    // Hiển thị tin nhắn từ client trên cửa sổ server
    serverWindow.receiveMessage(clientName, "Joined the chat.");
}


private void addStudent() {

	String m = masv.getText();
	String t = ten.getText();
	String l = lop.getText();
	String p = phong.getText();
	String k = khu.getText();

	if (!m.isEmpty() && !t.isEmpty() && !l.isEmpty() && !p.isEmpty()) {
		Student newStudent = new Student(m, t, l, p, k);
		boolean success = manager.add(newStudent);

		if (success) {
			// Thêm sinh viên mới vào DefaultTableModel
			Object[] rowData = { currentId++, m, t, l, p, k };
			DefaultTableModel model = (DefaultTableModel) table_1.getModel();
			model.addRow(rowData);

			// Cập nhật JTable
			table_1.setModel(model);

			masv.setText("");
			ten.setText("");
			lop.setText("");
			phong.setText("");
			khu.setText("");

			JOptionPane.showMessageDialog(this, "Sinh viên đã được thêm vào cơ sở dữ liệu.");
		} else {
			JOptionPane.showMessageDialog(this, "Không thể thêm sinh viên.");
		}
	} else {
		JOptionPane.showMessageDialog(this, "Vui lòng điền đầy đủ thông tin!");
	}
}

private void update() {
	String m = masv.getText();
	String t = ten.getText();
	String l = lop.getText();
	String p = phong.getText();
	String k = khu.getText();

	if (m.isEmpty() || t.isEmpty() || l.isEmpty() || p.isEmpty() || k.isEmpty()) {
		JOptionPane.showMessageDialog(this, "Vui lòng điền đầy đủ thông tin!", "Lỗi",
				JOptionPane.ERROR_MESSAGE);
		return; 
	}

	Student updatedStudent = new Student(m, t, l, p, k);

	boolean success = manager.edit(m, updatedStudent);
	
	if (success) {
		JOptionPane.showMessageDialog(this, "Thông tin sinh viên đã được cập nhật.", "Thành công",
				JOptionPane.INFORMATION_MESSAGE);
	} else {
		JOptionPane.showMessageDialog(this, "Không thể cập nhật thông tin sinh viên. Vui lòng kiểm tra lại.", "Lỗi",
				JOptionPane.ERROR_MESSAGE);
	}
}

private void TimKiemMasv() {

	String timkiem = Tkmasv.getText();

	if (timkiem.isEmpty()) {
		JOptionPane.showMessageDialog(this, "Vui lòng nhập mã sinh viên để tìm kiếm!");
		return;
	}

	ArrayList<Student> result = manager.findByMasv(timkiem);

	if (result.isEmpty()) {
		JOptionPane.showMessageDialog(this, "Sinh viên không tồn tại!");
		return;
	}

	model.setRowCount(0);

	for (int i = 0; i < result.size(); i++) {
		Student student = result.get(i);
		Object[] row = { i + 1, student.getMasv(), student.getTen(), student.getLop(), student.getPhong(), student.getKhu() };
		model.addRow(row);

	}
}
private void TimKiemSoPhong() {
    String soPhongTimKiem = tkSoPhong.getText();

    if (soPhongTimKiem.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Vui lòng nhập số phòng để tìm kiếm!");
        return;
    }

    ArrayList<Student> result = manager.findByPhong(soPhongTimKiem);

    if (result.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Không có sinh viên nào ở phòng này!");
        return;
    }

    // Xóa dữ liệu cũ trong model
    model.setRowCount(0);

    // Thêm dữ liệu từ danh sách sinh viên vào model
    for (Student student : result) {
        Object[] row = { student.getId(), student.getMasv(), student.getTen(), student.getLop(), student.getPhong(), student.getKhu() };
        model.addRow(row);
    }
}



private void hienthi() {
	 LoadDataWorker worker = new LoadDataWorker(model, manager);
	    worker.execute(); // Bắt đầu thực hiện tác vụ ở nền
}
// Phương thức để lưu dữ liệu bảng theo thứ tự hiển thị trong giao diện vào tập tin XML trong thư mục Documents
private void saveToXML() {
    String documentsPath = Paths.get(System.getProperty("user.home"), "Documents").toString();
    String filePath = Paths.get(documentsPath, "students.xml").toString();

    DefaultTableModel tableModel = (DefaultTableModel) table_1.getModel();
    int rowCount = tableModel.getRowCount();
    int columnCount = tableModel.getColumnCount();
    Object[][] data = new Object[rowCount][columnCount];
    String[] columnNames = new String[columnCount];

    // Lấy dữ liệu từ mô hình bảng
    for (int i = 0; i < columnCount; i++) {
        columnNames[i] = table_1.getColumnName(i); // Lấy tên cột
        for (int j = 0; j < rowCount; j++) {
            data[j][i] = table_1.getValueAt(j, i); // Lấy giá trị từng ô và gán vào mảng theo cột
        }
    }

    // Lưu dữ liệu vào tập tin XML
    try (XMLEncoder encoder = new XMLEncoder(new BufferedOutputStream(new FileOutputStream(filePath)))) {
        encoder.writeObject(columnNames); // Ghi tên cột
        encoder.writeObject(data); // Ghi dữ liệu theo thứ tự hiển thị trong giao diện
        JOptionPane.showMessageDialog(this, "Dữ liệu đã được lưu vào tập tin Documents/students.xml.", "Thành công",
                JOptionPane.INFORMATION_MESSAGE);
    } catch (FileNotFoundException e) {
        JOptionPane.showMessageDialog(this, "Lỗi: Không thể tạo hoặc ghi vào tập tin Documents/students.xml.", "Lỗi",
                JOptionPane.ERROR_MESSAGE);
    }
}
public class LoadDataWorker extends SwingWorker<Void, Void> {

    private DefaultTableModel model;
    private StudentManager manager;

    public LoadDataWorker(DefaultTableModel model, StudentManager manager) {
        this.model = model;
        this.manager = manager;
    }

    @Override
    protected Void doInBackground() throws Exception {
        // Gọi phương thức getAll() để lấy danh sách sinh viên từ cơ sở dữ liệu
        ArrayList<Student> students = manager.getAll();

        // Xóa dữ liệu cũ trong model
        model.setRowCount(0);

        // Thêm dữ liệu từ danh sách sinh viên vào model
        for (int i = 0; i < students.size(); i++) {
            Student student = students.get(i);
            Object[] row = { student.getId(), student.getMasv(), student.getTen(), student.getLop(), student.getPhong(), student.getKhu() };
            model.addRow(row);
        }
        return null;
    }

    @Override
    protected void done() {
       
    }
}
}