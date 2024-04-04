/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package View;


import Dao.DaoSinhVien;
import Model.Sinhvien;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Hi
 */
public class SinhVienView implements ActionListener {

    String searchValue;
    static int row = 0;
    public Object o;
    public JLabel maSV, hoTen, maLop, gioiTinh, sdt;
    public JTextField maSVTxt, hoTenTxt, maLopTxt, gioiTinhTxt, sdtTxt, searchTxt;
    public JButton add, edit, delete, clear, sortByMa, sortByName, search, excel, exit;
    JTable sinhvienTable;
    String[] columnNames = {"MaSV", "HoTen", "MaLop", "GioiTinh", "SDT"};
    DefaultTableModel tableModel;

    public SinhVienView() {
        JFrame j = new JFrame();
        j.setTitle("Sinh Vien Information");
        j.setSize(1920, 1080);
        j.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        j.setVisible(true);

        maSV = new JLabel("Ma SV");
        hoTen = new JLabel("Ho Ten");
        maLop = new JLabel("Ma Lop");
        gioiTinh = new JLabel("Gioi Tinh");
        sdt = new JLabel("SDT");

        maSVTxt = new JTextField(40);
        hoTenTxt = new JTextField(40);
        maLopTxt = new JTextField(40);
        gioiTinhTxt = new JTextField(40);
        sdtTxt = new JTextField(40);
        searchTxt = new JTextField(8);

        add = new JButton("ADD");
        edit = new JButton("EDIT");
        delete = new JButton("DELETE");
        clear = new JButton("CLEAR");
        sortByMa = new JButton("SORT BY MA");
        sortByName = new JButton("SORT BY NAME");
        search = new JButton("SEARCH");
        excel = new JButton("EXCEL");
        exit = new JButton("Exit");

        j.setLayout(new BorderLayout());

        JPanel p1 = new JPanel(new GridBagLayout());
        JPanel pp = new JPanel(new FlowLayout(10, 30, 50));
        JPanel pp1 = new JPanel(new FlowLayout(10, 50, 50));

        JPanel p2 = new JPanel(new GridBagLayout());
       

        JLabel j1 = new JLabel("QUẢN LÝ SINH VIÊN");
        j1.setHorizontalAlignment(SwingConstants.CENTER);
        j.add(j1, BorderLayout.NORTH);
        j.add(p1, BorderLayout.WEST);
        j.add(p2, BorderLayout.EAST);
        Menu menu=new Menu();

        j.setJMenuBar(menu);
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(0, 50, 20, 10);
        gbc.gridx = 0;
        gbc.gridy = 0;
        p1.add(maSV, gbc);

        gbc.fill = GridBagConstraints.HORIZONTAL;
//        gbc.insets = new Insets(0, 50, 20, 200);
        gbc.gridx = 1;
        gbc.gridy = 0;
        p1.add(maSVTxt, gbc);

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 1;
        p1.add(hoTen, gbc);

        gbc.fill = GridBagConstraints.HORIZONTAL;
//        gbc.insets = new Insets(0, 50, 20, 200);
        gbc.gridx = 1;
        gbc.gridy = 1;
        p1.add(hoTenTxt, gbc);

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 2;
        p1.add(maLop, gbc);

        gbc.fill = GridBagConstraints.HORIZONTAL;
//        gbc.insets = new Insets(0, 50, 20, 200);
        gbc.gridx = 1;
        gbc.gridy = 2;
        p1.add(maLopTxt, gbc);

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 3;
        p1.add(gioiTinh, gbc);

        gbc.fill = GridBagConstraints.HORIZONTAL;
//        gbc.insets = new Insets(0, 50, 20, 200);
        gbc.gridx = 1;
        gbc.gridy = 3;
        p1.add(gioiTinhTxt, gbc);

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 4;
        p1.add(sdt, gbc);

        gbc.fill = GridBagConstraints.HORIZONTAL;
//        gbc.insets = new Insets(0, 50, 20, 200);
        gbc.gridx = 1;
        gbc.gridy = 4;
        p1.add(sdtTxt, gbc);

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 1;
        gbc.gridy = 5;

        pp.add(add);
        pp.add(edit);
        pp.add(delete);
        pp.add(clear);
        pp.add(excel);
        p1.add(pp, gbc);

        tableModel = new DefaultTableModel();
        sinhvienTable = new JTable(tableModel);
        sinhvienTable.setAutoCreateRowSorter(true);

        // adding it to JScrollPane
        gbc.gridx = 0;
        gbc.gridy = 0;
        JScrollPane sp = new JScrollPane(sinhvienTable);
        p2.add(sp, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.insets = new Insets(0, 50, 20, 80);
        pp1.add(sortByMa);
        pp1.add(sortByName);
        pp1.add(search);
        pp1.add(searchTxt);
        p2.add(pp1, gbc);

        JPanel exitPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        exitPanel.add(exit); // Thêm nút "Exit" vào JPanel

        j.add(exitPanel, BorderLayout.SOUTH);
        
        exit.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            btnExit_actionperformed(); // Gọi phương thức khi nhấn nút Exit
            j.dispose();
        }
        });

        add.addActionListener(this);
        delete.addActionListener(this);
        edit.addActionListener(this);
        clear.addActionListener(this);
        sortByMa.addActionListener(this);
        sortByName.addActionListener(this);
        search.addActionListener(this);
        excel.addActionListener(this);

        DaoSinhVien std = new DaoSinhVien();
        hienthi_tbl(std);
        sinhvienTable.addMouseListener(new MouseListener() {

            public void mouseClicked(MouseEvent e) {
                row = sinhvienTable.getSelectedRow();
                // lay du lieu tu cot 0
                o = tableModel.getValueAt(row, 0);

                // hien thi du lieu len JTextFiled
                Object dataMaSV = tableModel.getValueAt(row, 0); // Lấy dữ liệu từ cột 0
                Object dataHoTen = tableModel.getValueAt(row, 1); // Lấy dữ liệu từ cột 1
                Object dataMaLop = tableModel.getValueAt(row, 2); // Lấy dữ liệu từ cột 2
                Object dataGioiTinh = tableModel.getValueAt(row, 3); // Lấy dữ liệu từ cột 3
                Object dataSDT = tableModel.getValueAt(row, 4); // Lấy dữ liệu từ cột 4

                maSVTxt.setText(dataMaSV.toString());
                hoTenTxt.setText(dataHoTen.toString());
                maLopTxt.setText(dataMaLop.toString());
                gioiTinhTxt.setText(dataGioiTinh.toString());
                sdtTxt.setText(dataSDT.toString());
            }

            public void mousePressed(MouseEvent e) {

            }

            public void mouseReleased(MouseEvent e) {

            }

            public void mouseEntered(MouseEvent e) {

            }

            public void mouseExited(MouseEvent e) {

            }
        });

    }

    public void actionPerformed(ActionEvent e) {
        JButton btn = (JButton) e.getSource();
        if (btn.equals(add)) {
            btnAdd_actionperformed();
            // Xóa tất cả các hàng và cột
            tableModel.setRowCount(0);
            tableModel.setColumnCount(0);
            DaoSinhVien std = new DaoSinhVien();
            hienthi_tbl(std);
        } else if (btn.equals(delete)) {
            btnDelete_actionperformed();
        } else if (btn.equals(edit)) {
            btnEdit_actionperformed();
        } else if (btn.equals(clear)) {
            btnClear_actionperformed();
        } else if (btn.equals(sortByMa)) {
            tableModel.setRowCount(0);
            tableModel.setColumnCount(0);
            btnSortMa_actionperformed();
        } else if (btn.equals(sortByName)) {
            tableModel.setRowCount(0);
            tableModel.setColumnCount(0);
            btnSortName_actionperformed();
        } else if (btn.equals(search)) {
            tableModel.setRowCount(0);
            tableModel.setColumnCount(0);
            btnSearch_actionperformed();
        } else if (btn.equals(excel)) {
            btnExcel_actionperformed();
        }
    }
    
    private void btnExit_actionperformed() {
        // Quay lại trang HomePage
        LoginView homepage = new LoginView();
        homepage.setVisible(true); // Hiển thị trang HomePage
         // Đóng frame hiện tại (sinhvienfrm)
    }
    
    private void btnAdd_actionperformed() {
        Sinhvien s = new Sinhvien();
        s.setMaSV(maSVTxt.getText());
        s.setHoTen(hoTenTxt.getText());
        s.setMaLop(maLopTxt.getText());
        s.setGioiTinh(gioiTinhTxt.getText());
        s.setSDT(sdtTxt.getText());

        DaoSinhVien std = new DaoSinhVien();
        std.add_Student(s);
    }

    private void btnDelete_actionperformed() {
        DaoSinhVien std = new DaoSinhVien();
        std.delete_Student(o);
        tableModel.removeRow(row);
        tableModel.fireTableDataChanged();

        maSVTxt.setText("");
        hoTenTxt.setText("");
        maLopTxt.setText("");
        gioiTinhTxt.setText("");
        sdtTxt.setText("");

    }

    private void btnClear_actionperformed() {
        maSVTxt.setText("");
        hoTenTxt.setText("");
        maLopTxt.setText("");
        gioiTinhTxt.setText("");
        sdtTxt.setText("");

    }

    private void btnEdit_actionperformed() {
        Sinhvien s = new Sinhvien();
        s.setMaSV(maSVTxt.getText());
        s.setHoTen(hoTenTxt.getText());
        s.setMaLop(maLopTxt.getText());
        s.setGioiTinh(gioiTinhTxt.getText());
        s.setSDT(sdtTxt.getText());

        DaoSinhVien std = new DaoSinhVien();
        std.edit_Student(s, o);

        // Cập nhật dữ liệu trong bảng
        tableModel.setValueAt(maSVTxt.getText(), row, 0);
        tableModel.setValueAt(hoTenTxt.getText(), row, 1);
        tableModel.setValueAt(maLopTxt.getText(), row, 2);
        tableModel.setValueAt(gioiTinhTxt.getText(), row, 3);
        tableModel.setValueAt(sdtTxt.getText(), row, 4);

    }

    private void btnSortMa_actionperformed() {
        DaoSinhVien std = new DaoSinhVien();
        std.sortMa(sinhvienTable, tableModel);
    }

    private void btnSortName_actionperformed() {
        DaoSinhVien std = new DaoSinhVien();
        std.sortName(sinhvienTable, tableModel);
    }

    private void btnSearch_actionperformed() {
        DaoSinhVien std = new DaoSinhVien();
        std.searchValue(sinhvienTable, tableModel, SinhVienView.this);
    }

    public void btnExcel_actionperformed() {
        DaoSinhVien std = new DaoSinhVien();
        std.excel_print(tableModel);
    }

    public void hienthi_tbl(DaoSinhVien std) {
        std.rsTableModel(sinhvienTable, tableModel);
    }

//    public static void main(String[] args) {
//        SwingUtilities.invokeLater(sinhvienfrm::new);
//    }
}
