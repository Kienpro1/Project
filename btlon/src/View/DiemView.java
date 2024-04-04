/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package View;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.io.FileOutputStream;
import java.io.IOException;
import static java.lang.Float.parseFloat;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.WindowConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import Dao.DaoDiem;
import Model.diem;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author ADMIN
 */
public class DiemView extends JFrame {

    private boolean isAdding = false;

    private DaoDiem controller;
    private JButton addStudentBtn;
    private JButton editStudentBtn;
    private JButton deleteStudentBtn;
    private JButton ExcelBtn;
    private JButton SaveBtn;
    private JButton BackBtn;
    private JButton SearchBtn;
    private JButton ExitBtn;
    private JScrollPane jScrollPaneStudentTable;
    private JTable studentTable;

    private JLabel MaKQLabel;
    private JLabel MaSVLabel;
    private JLabel HoTenLabel;
    private JLabel LopLabel;
    private JLabel MonLabel;
    private JLabel DiemRLLabel;
    private JLabel DiemGKLabel;
    private JLabel DiemCKLabel;
    private JLabel TongKetLabel;
    private JLabel TinhTrangLabel;

    private JTextField MaKQField;
    private JTextField MaSVField;
    private JTextField HoTenField;
    private JTextField LopField;
    private JComboBox MonCombobox;
    private JTextField DiemRLField;
    private JTextField DiemGKField;
    private JTextField DiemCKField;
    private JTextField TongKetField;
    private JTextField TinhTrangField;
    private JTextField SearchField;

    private DefaultTableModel tableModel;

    private final String[] columnNames = new String[]{
            "Mã KQ", "Mã SV", "Họ Tên", "Lớp", "Môn", "Điểm RL", "Điểm GK", "Điểm CK", "Tổng kết", "Tình Trạng"
    };

    private final Object data = new Object[][]{};

    public class ExcelExporter {
        public static void exportToExcel(JTable table, String filePath) {
            DefaultTableModel model = (DefaultTableModel) table.getModel();
            Workbook workbook = new XSSFWorkbook();
            Sheet sheet = workbook.createSheet("Data");

            // Đầu tiên, tạo dòng tiêu đề trong file Excel
            Row headerRow = sheet.createRow(0);
            for (int i = 0; i < model.getColumnCount(); i++) {
                headerRow.createCell(i).setCellValue(model.getColumnName(i));
            }

            // Sau đó, thêm dữ liệu từ JTable vào file Excel
            for (int row = 0; row < model.getRowCount(); row++) {
                Row excelRow = sheet.createRow(row + 1);
                for (int col = 0; col < model.getColumnCount(); col++) {
                    excelRow.createCell(col).setCellValue(model.getValueAt(row, col).toString());
                }
            }

            // Lưu file Excel
            try (FileOutputStream fileOut = new FileOutputStream(filePath)) {
                workbook.write(fileOut);
                JOptionPane.showMessageDialog(null, "Xuất dữ liệu thành công!");
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, "Xuất dữ liệu thất bại: " + e.getMessage());
            }
        }
    }


    public DiemView() {
        initComponents();
        setSize(1350, 700);
        MaKQField.setEnabled(false);
        MaSVField.setEnabled(false);
        HoTenField.setEnabled(false);
        LopField.setEnabled(false);
        MonCombobox.setEnabled(false);
        DiemRLField.setEnabled(false);
        DiemGKField.setEnabled(false);
        DiemCKField.setEnabled(false);
        TongKetField.setEnabled(false);
        TinhTrangField.setEnabled(false);
        SaveBtn.setEnabled(false);
        BackBtn.setEnabled(false);
        Menu menu = new Menu();

        this.setJMenuBar(menu);

        controller = new DaoDiem(); // Khởi tạo Controller
        loadDataToTableModel(); // Load dữ liệu từ Controller vào tableModel

        studentTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
                                                                      public void valueChanged(ListSelectionEvent event) {
                                                                          if (!event.getValueIsAdjusting()) {
                                                                              int selectedRow = studentTable.getSelectedRow();
                                                                              if (selectedRow >= 0) {
                                                                                  // Lấy dữ liệu từ bảng và hiển thị vào các JTextField tương ứng
                                                                                  MaKQField.setText(studentTable.getValueAt(selectedRow, 0).toString());
                                                                                  MaSVField.setText(studentTable.getValueAt(selectedRow, 1).toString());
                                                                                  HoTenField.setText(studentTable.getValueAt(selectedRow, 2).toString());
                                                                                  LopField.setText(studentTable.getValueAt(selectedRow, 3).toString());
                                                                                  MonCombobox.setSelectedItem(studentTable.getValueAt(selectedRow, 4).toString());
                                                                                  DiemRLField.setText(studentTable.getValueAt(selectedRow, 5).toString());
                                                                                  DiemGKField.setText(studentTable.getValueAt(selectedRow, 6).toString());
                                                                                  DiemCKField.setText(studentTable.getValueAt(selectedRow, 7).toString());
                                                                                  TongKetField.setText(studentTable.getValueAt(selectedRow, 8).toString());
                                                                                  TinhTrangField.setText(studentTable.getValueAt(selectedRow, 9).toString());

                                                                              }
                                                                          }
                                                                      }
                                                                  }
        );

        MaSVField.addActionListener((ActionEvent e) -> {
            String studentID = MaSVField.getText();
            String[] studentInfo = controller.queryStudentInfo(studentID);
            if (studentInfo != null) {
                HoTenField.setText(studentInfo[0]);
                LopField.setText(studentInfo[1]);
                MonCombobox.requestFocus();
            } else {
                // Xử lý trường hợp không tìm thấy thông tin sinh viên
                // Ví dụ: Hiển thị thông báo hoặc xóa các trường dữ liệu
                HoTenField.setText("không tìm thấy");
                LopField.setText("");
            }

        });

        DiemRLField.addActionListener((ActionEvent e) -> {
            String input = DiemRLField.getText();
            try {
                double value = Double.parseDouble(input);

                if (value > 10) {
                    JOptionPane.showMessageDialog(null, "Vui lòng nhập số nhỏ hơn 10", "Lỗi", JOptionPane.ERROR_MESSAGE);
                    DiemRLField.setText("");
                } else {
                    // Chuyển giá trị từ String sang float
                    if (DiemRLField.getText().isEmpty() || DiemGKField.getText().isEmpty() || DiemCKField.getText().isEmpty()) {
                        TinhTrangField.setText("");
                    } else {
                        float DiemRL = Float.parseFloat(DiemRLField.getText());
                        float DiemGK = Float.parseFloat(DiemGKField.getText());
                        float DiemCK = Float.parseFloat(DiemCKField.getText());

                        // Tính tổng kết theo công thức
                        float TongKet = (float) (DiemRL * 0.1 + DiemGK * 0.2 + DiemCK * 0.7);

                        TongKetField.setText(String.format("%.2f", TongKet).replace(",", "."));
                        // Hiển thị tổng kết trên trường TinhTrangField
                        if (TongKet >= 4) {
                            TinhTrangField.setText("Qua Môn");
                        } else {
                            TinhTrangField.setText("Thi Lại");
                        }

                    }

                }
                DiemGKField.requestFocus();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Vui lòng nhập số.", "Lỗi", JOptionPane.ERROR_MESSAGE);
                DiemRLField.setText("");
            }

        });

        DiemGKField.addActionListener((ActionEvent e) -> {
            String input = DiemGKField.getText();
            try {
                double value = Double.parseDouble(input);
                if (value > 10) {
                    JOptionPane.showMessageDialog(null, "Vui lòng nhập số nhỏ hơn 10", "Lỗi", JOptionPane.ERROR_MESSAGE);
                    DiemGKField.setText("");
                } else {
                    if (DiemRLField.getText().isEmpty() || DiemGKField.getText().isEmpty() || DiemCKField.getText().isEmpty()) {
                        TinhTrangField.setText("");
                    } else {
                        float DiemRL = Float.parseFloat(DiemRLField.getText());
                        float DiemGK = Float.parseFloat(DiemGKField.getText());
                        float DiemCK = Float.parseFloat(DiemCKField.getText());

                        // Tính tổng kết theo công thức
                        float TongKet = (float) (DiemRL * 0.1 + DiemGK * 0.2 + DiemCK * 0.7);

                        TongKetField.setText(String.format("%.2f", TongKet).replace(",", "."));
                        // Hiển thị tổng kết trên trường TinhTrangField
                        if (TongKet >= 4) {
                            TinhTrangField.setText("Qua Môn");
                        } else {
                            TinhTrangField.setText("Thi Lại");
                        }

                    }

                }
                DiemCKField.requestFocus();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Vui lòng nhập số.", "Lỗi", JOptionPane.ERROR_MESSAGE);
                DiemGKField.setText("");
            }

        });

        DiemCKField.addActionListener((ActionEvent e) -> {
            String input = DiemCKField.getText();
            try {
                double value = Double.parseDouble(input);
                if (value > 10) {
                    JOptionPane.showMessageDialog(null, "Vui lòng nhập số nhỏ hơn 10", "Lỗi", JOptionPane.ERROR_MESSAGE);
                    DiemCKField.setText("");
                } else {
                    if (DiemRLField.getText().isEmpty() || DiemGKField.getText().isEmpty() || DiemCKField.getText().isEmpty()) {
                        TinhTrangField.setText("");
                    } else {
                        float DiemRL = Float.parseFloat(DiemRLField.getText());
                        float DiemGK = Float.parseFloat(DiemGKField.getText());
                        float DiemCK = Float.parseFloat(DiemCKField.getText());

                        // Tính tổng kết theo công thức
                        float TongKet = (float) (DiemRL * 0.1 + DiemGK * 0.2 + DiemCK * 0.7);

                        TongKetField.setText(String.format("%.2f", TongKet).replace(",", "."));
                        // Hiển thị tổng kết trên trường TinhTrangField
                        if (TongKet >= 4) {
                            TinhTrangField.setText("Qua Môn");
                        } else {
                            TinhTrangField.setText("Thi Lại");
                        }

                    }

                }

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Vui lòng nhập số.", "Lỗi", JOptionPane.ERROR_MESSAGE);
                DiemCKField.setText("");
            }

        });

        addStudentBtn.addActionListener((ActionEvent e) -> {

                    MaSVField.setEnabled(true);
                    MonCombobox.setEnabled(true);
                    DiemRLField.setEnabled(true);
                    DiemGKField.setEnabled(true);
                    DiemCKField.setEnabled(true);
                    SearchField.setEnabled(false);

                    MaKQField.setText("");
                    MaSVField.setText("");
                    HoTenField.setText("");
                    LopField.setText("");
                    MonCombobox.setSelectedItem("");
                    DiemRLField.setText("");
                    DiemGKField.setText("");
                    DiemCKField.setText("");
                    TongKetField.setText("");
                    TinhTrangField.setText("");
                    SearchField.setText("Nhập mã sinh viên");

                    SaveBtn.setEnabled(true);
                    BackBtn.setEnabled(true);
                    addStudentBtn.setEnabled(false);
                    editStudentBtn.setEnabled(false);
                    deleteStudentBtn.setEnabled(false);
                    ExcelBtn.setEnabled(false);
                    SearchBtn.setEnabled(false);
                    studentTable.setEnabled(false);

                    isAdding = true;
                }
        );

        editStudentBtn.addActionListener((ActionEvent e) -> {
            DiemRLField.setEnabled(true);
            DiemGKField.setEnabled(true);
            DiemCKField.setEnabled(true);
            SearchField.setEnabled(false);

            SaveBtn.setEnabled(true);
            BackBtn.setEnabled(true);
            addStudentBtn.setEnabled(false);
            editStudentBtn.setEnabled(false);
            deleteStudentBtn.setEnabled(false);
            ExcelBtn.setEnabled(false);
            SearchBtn.setEnabled(false);
        });

        deleteStudentBtn.addActionListener((ActionEvent e) -> {
            if (MaKQField.getText().isBlank()) {
                JOptionPane.showMessageDialog(DiemView.this, "yêu cầu chọn đối tượng để xóa");
            } else {
                int choice = JOptionPane.showConfirmDialog(DiemView.this, "Bạn có muốn xóa dữ liệu này không?", "Xác nhận xóa", JOptionPane.YES_NO_OPTION);
                if (choice == JOptionPane.YES_OPTION) {
                    diem d = new diem();
                    d.setMaKQ(Integer.parseInt(MaKQField.getText()));

                    DaoDiem a = new DaoDiem();
                    a.deleteStudent(d);
                    JOptionPane.showMessageDialog(DiemView.this, "Xóa dữ liệu thành công");
                    loadDataToTableModel();

                    MaKQField.setText("");
                    MaSVField.setText("");
                    HoTenField.setText("");
                    LopField.setText("");
                    MonCombobox.setSelectedItem("");
                    DiemRLField.setText("");
                    DiemGKField.setText("");
                    DiemCKField.setText("");
                    TongKetField.setText("");
                    TinhTrangField.setText("");

                }
            }
        });

        SaveBtn.addActionListener((ActionEvent e) -> {
            if (isAdding) {
                if (MaSVField.getText().isEmpty() || MonCombobox.getSelectedItem() == "" || DiemRLField.getText().isEmpty() || DiemGKField.getText().isEmpty() || DiemCKField.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Vui lòng nhập đủ thông tin yêu cầu.", "Lỗi", JOptionPane.ERROR_MESSAGE);
                } else {
                    diem d = new diem();
                    d.setMaSV(MaSVField.getText());
                    d.setMon((String) MonCombobox.getSelectedItem());
                    d.setDiemRL(parseFloat(DiemRLField.getText()));
                    d.setDiemGK(parseFloat(DiemGKField.getText()));
                    d.setDiemCK(parseFloat(DiemCKField.getText()));
                    d.setTongKet(parseFloat(TongKetField.getText()));
                    d.setTinhTrang(TinhTrangField.getText());

                    DaoDiem a = new DaoDiem();
                    boolean kiemtra = a.kiemtratrungKQ(d);
                    if (kiemtra) {
                        JOptionPane.showMessageDialog(null, "Kết quả đã tồn tại", "Lỗi", JOptionPane.ERROR_MESSAGE);
                    } else {
                        a.addStudent(d);
                        JOptionPane.showMessageDialog(DiemView.this, "Lưu dữ liệu thành công");
                        loadDataToTableModel();

                        MaSVField.setText("");
                        HoTenField.setText("");
                        LopField.setText("");
                        DiemRLField.setText("");
                        DiemGKField.setText("");
                        DiemCKField.setText("");
                        TongKetField.setText("");
                        TinhTrangField.setText("");

                        isAdding = true;
                    }
                    isAdding = true;
                }
            } else {
                if (MaSVField.getText().isEmpty() || MonCombobox.getSelectedItem() == "" || DiemRLField.getText().isEmpty() || DiemGKField.getText().isEmpty() || DiemCKField.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(DiemView.this, "Mời chọn đối tượng bạn muốn sửa thông tin và điền đầy đủ thông tin yêu cầu");
                } else {
                    int choice = JOptionPane.showConfirmDialog(DiemView.this, "Bạn có muốn sửa dữ liệu này không?", "Xác nhận xóa", JOptionPane.YES_NO_OPTION);
                    if (choice == JOptionPane.YES_OPTION) {
                        diem d = new diem();
                        d.setMaKQ(Integer.parseInt(MaKQField.getText()));
                        d.setDiemRL(parseFloat(DiemRLField.getText()));
                        d.setDiemGK(parseFloat(DiemGKField.getText()));
                        d.setDiemCK(parseFloat(DiemCKField.getText()));
                        d.setTongKet(parseFloat(TongKetField.getText()));
                        d.setTinhTrang(TinhTrangField.getText());

                        DaoDiem a = new DaoDiem();
                        a.editStudent(d);
                        JOptionPane.showMessageDialog(DiemView.this, "Sửa dữ liệu thành công");
                        loadDataToTableModel();

                        MaKQField.setText("");
                        MaSVField.setText("");
                        HoTenField.setText("");
                        LopField.setText("");
                        MonCombobox.setSelectedItem("");
                        DiemRLField.setText("");
                        DiemGKField.setText("");
                        DiemCKField.setText("");
                        TongKetField.setText("");
                        TinhTrangField.setText("");
                    }
                }

            }
        });

        BackBtn.addActionListener((ActionEvent e) -> {
                    MaSVField.setEnabled(false);
                    MonCombobox.setEnabled(false);
                    DiemRLField.setEnabled(false);
                    DiemGKField.setEnabled(false);
                    DiemCKField.setEnabled(false);
                    studentTable.setEnabled(true);

                    MaKQField.setText("");
                    MaSVField.setText("");
                    HoTenField.setText("");
                    LopField.setText("");
                    MonCombobox.setSelectedItem("");
                    DiemRLField.setText("");
                    DiemGKField.setText("");
                    DiemCKField.setText("");
                    TongKetField.setText("");
                    TinhTrangField.setText("");


                    SaveBtn.setEnabled(false);
                    BackBtn.setEnabled(false);
                    addStudentBtn.setEnabled(true);
                    editStudentBtn.setEnabled(true);
                    deleteStudentBtn.setEnabled(true);
                    SearchBtn.setEnabled(true);
                    ExcelBtn.setEnabled(true);

                    isAdding = false;
                }
        );

        SearchBtn.addActionListener((ActionEvent e) -> {
            String keyword = SearchField.getText();
            if (!keyword.isEmpty()) {
                // Gọi phương thức tìm kiếm từ lớp Controller
                Object[][] searchData = controller.searchStudent(keyword);
                // Cập nhật dữ liệu tìm kiếm lên bảng
                tableModel.setDataVector(searchData, columnNames);
                SearchField.setText("nhập mã sinh viên");
            } else {
                // Nếu không có từ khóa, hiển thị toàn bộ dữ liệu
                loadDataToTableModel();
            }
        });

        ExitBtn.addActionListener((ActionEvent e) -> {
            LoginView frame = new LoginView();
            dispose();
        });

        ExcelBtn.addActionListener((ActionEvent e) -> {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Chọn nơi lưu file Excel");
            int userSelection = fileChooser.showSaveDialog(DiemView.this);
            if (userSelection == JFileChooser.APPROVE_OPTION) {
                String filePath = fileChooser.getSelectedFile().getPath();
                ExcelExporter.exportToExcel(studentTable, filePath + ".xlsx");
            }
        });
    }

    private void loadDataToTableModel() {
        Object[][] data = controller.loadData(); // Lấy dữ liệu từ Controller
        tableModel.setDataVector(data, columnNames); // Cập nhật dữ liệu cho tableModel
    }

    private void initComponents() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        addStudentBtn = new JButton("Thêm");
        editStudentBtn = new JButton("Sửa");
        deleteStudentBtn = new JButton("Xóa");
        ExcelBtn = new JButton("Excel");
        SaveBtn = new JButton("Lưu");
        BackBtn = new JButton("Hủy");
        SearchBtn = new JButton("Tìm Kiếm");
        ExitBtn = new JButton("Thoát");

        jScrollPaneStudentTable = new JScrollPane();
        studentTable = new JTable();

        MaKQLabel = new JLabel("Mã kết quả");
        MaSVLabel = new JLabel("Mã Sinh viên");
        HoTenLabel = new JLabel("Họ Tên");
        LopLabel = new JLabel("Lớp");
        MonLabel = new JLabel("Môn");
        DiemRLLabel = new JLabel("Điểm rèn luyện");
        DiemGKLabel = new JLabel("Điểm giữa kỳ");
        DiemCKLabel = new JLabel("Điểm cuói kỳ");
        TongKetLabel = new JLabel("Tổng Kết");
        TinhTrangLabel = new JLabel("Tình trạng");

        MaKQField = new JTextField(6);
        MaSVField = new JTextField(15);
        HoTenField = new JTextField(15);
        LopField = new JTextField(15);
        String[] monHoc = {"", "Toán cao cấp", "Toán rời rạc", "Lập trình web", "Lập trình di động", "Java nâng cao", "BigData", "cơ sở dữ liệu phân tán"};
        MonCombobox = new JComboBox<>(monHoc);
        MonCombobox.setBounds(50, 50, 180, 20);
        DiemRLField = new JTextField(6);
        DiemGKField = new JTextField(6);
        DiemCKField = new JTextField(6);
        TongKetField = new JTextField(6);
        TinhTrangField = new JTextField(15);
        SearchField = new JTextField(15);

        tableModel = new DefaultTableModel((Object[][]) data, columnNames);
        studentTable.setModel(tableModel);
        jScrollPaneStudentTable.setViewportView(studentTable);
        jScrollPaneStudentTable.setPreferredSize(new Dimension(1000, 400));

        SpringLayout layout = new SpringLayout();
        JPanel panel = new JPanel();
        panel.setSize(800, 420);
        panel.setLayout(layout);
        panel.add(jScrollPaneStudentTable);

        panel.add(addStudentBtn);
        panel.add(editStudentBtn);
        panel.add(deleteStudentBtn);
        panel.add(ExcelBtn);
        panel.add(SaveBtn);
        panel.add(BackBtn);
        panel.add(ExitBtn);
        panel.add(SearchBtn);

        panel.add(MaKQLabel);
        panel.add(MaSVLabel);
        panel.add(HoTenLabel);
        panel.add(LopLabel);
        panel.add(MonLabel);
        panel.add(DiemRLLabel);
        panel.add(DiemGKLabel);
        panel.add(DiemCKLabel);
        panel.add(TongKetLabel);
        panel.add(TinhTrangLabel);

        panel.add(MaKQField);
        panel.add(MaSVField);
        panel.add(HoTenField);
        panel.add(LopField);
        panel.add(MonCombobox);
        panel.add(DiemRLField);
        panel.add(DiemGKField);
        panel.add(DiemCKField);
        panel.add(TongKetField);
        panel.add(TinhTrangField);
        panel.add(SearchField);

        layout.putConstraint(SpringLayout.WEST, MaKQLabel, 10, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, MaKQLabel, 10, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, MaSVLabel, 10, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, MaSVLabel, 40, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, HoTenLabel, 10, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, HoTenLabel, 70, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, LopLabel, 10, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, LopLabel, 100, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, MonLabel, 10, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, MonLabel, 130, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, DiemRLLabel, 10, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, DiemRLLabel, 160, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, DiemGKLabel, 10, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, DiemGKLabel, 190, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, DiemCKLabel, 10, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, DiemCKLabel, 220, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, TongKetLabel, 10, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, TongKetLabel, 250, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, TinhTrangLabel, 10, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, TinhTrangLabel, 280, SpringLayout.NORTH, panel);

        layout.putConstraint(SpringLayout.WEST, MaKQField, 100, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, MaKQField, 10, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, MaSVField, 100, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, MaSVField, 40, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, HoTenField, 100, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, HoTenField, 70, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, LopField, 100, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, LopField, 100, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, MonCombobox, 100, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, MonCombobox, 130, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, DiemRLField, 100, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, DiemRLField, 160, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, DiemGKField, 100, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, DiemGKField, 190, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, DiemCKField, 100, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, DiemCKField, 220, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, TongKetField, 100, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, TongKetField, 250, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, TinhTrangField, 100, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, TinhTrangField, 280, SpringLayout.NORTH, panel);

        layout.putConstraint(SpringLayout.WEST, jScrollPaneStudentTable, 300, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, jScrollPaneStudentTable, 10, SpringLayout.NORTH, panel);

        layout.putConstraint(SpringLayout.WEST, addStudentBtn, 10, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, addStudentBtn, 320, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, editStudentBtn, 80, SpringLayout.WEST, addStudentBtn);
        layout.putConstraint(SpringLayout.NORTH, editStudentBtn, 320, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, deleteStudentBtn, 70, SpringLayout.WEST, editStudentBtn);
        layout.putConstraint(SpringLayout.NORTH, deleteStudentBtn, 320, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, ExcelBtn, 70, SpringLayout.WEST, deleteStudentBtn);
        layout.putConstraint(SpringLayout.NORTH, ExcelBtn, 320, SpringLayout.NORTH, panel);

        layout.putConstraint(SpringLayout.WEST, SaveBtn, 400, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, SaveBtn, 450, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, BackBtn, 115, SpringLayout.WEST, SaveBtn);
        layout.putConstraint(SpringLayout.NORTH, BackBtn, 450, SpringLayout.NORTH, panel);

        layout.putConstraint(SpringLayout.WEST, SearchField, 10, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, SearchField, 10, SpringLayout.SOUTH, jScrollPaneStudentTable);

        layout.putConstraint(SpringLayout.WEST, SearchBtn, 5, SpringLayout.EAST, SearchField);
        layout.putConstraint(SpringLayout.NORTH, SearchBtn, 10, SpringLayout.SOUTH, jScrollPaneStudentTable);

        layout.putConstraint(SpringLayout.WEST, ExitBtn, 10, SpringLayout.WEST, panel); // Cách lề trái của container 10 pixel
        layout.putConstraint(SpringLayout.SOUTH, ExitBtn, -10, SpringLayout.SOUTH, panel); // Cách lề dưới của container 10 pixel

        add(panel);
        setTitle("Quan lý điểm sinh viên");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }


//    public static void main(String[] args) {
//        SwingUtilities.invokeLater(from::new);
//    }

}