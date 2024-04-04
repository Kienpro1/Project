/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Dao;

import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

import Model.Sinhvien;
import View.SinhVienView;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author Hi
 */
public class DaoSinhVien {
    private Connection conn;
    public DaoSinhVien() {
        dbconnect(); // Gọi phương thức kết nối trong constructor
    }
    public void dbconnect() {
        String DB_URL = "jdbc:mysql://localhost:3306/quanlysinhvien";
        String USER_NAME = "root";
        String PASSWORD = "";

        try {
            conn = DriverManager.getConnection(DB_URL, USER_NAME, PASSWORD);
            System.out.println("Kết nối thành công đến cơ sở dữ liệu.");
        } catch (SQLException e) {
            e.printStackTrace();
            // Xử lý ngoại lệ khi kết nối không thành công
            JOptionPane.showMessageDialog(null, "Không thể kết nối đến cơ sở dữ liệu.");
        }
    }
    public void add_Student(Sinhvien s){
        String sql = "INSERT INTO tblsinhvien(MaSV,HoTen,MaLop,GioiTinh,SDT) VALUES(?,?,?,?,?)";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, s.getMaSV());
            ps.setString(2, s.getHoTen());
            ps.setString(3, s.getMaLop());
            ps.setString(4, s.getGioiTinh());
            ps.setString(5, s.getSDT());
            
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Them sinh vien thanh cong!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Them sinh vien khong thanh cong!");
            e.printStackTrace();
        }
    }
    public void rsTableModel(JTable tbl, DefaultTableModel model){
        try {
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM tblsinhvien");
            ResultSetMetaData metaData = resultSet.getMetaData();
            
            int columnCount = metaData.getColumnCount();
            for(int columnIndex = 1; columnIndex <= columnCount; columnIndex++){
                model.addColumn(metaData.getColumnLabel(columnIndex));
            }
            
            // Them du lieu tu ket qua truy van vao model
            while(resultSet.next()){
                Object[] row = new Object[columnCount];
                for(int i = 0; i < columnCount; i++){
                    row[i] = resultSet.getObject(i+1);
                }
                model.addRow(row);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void delete_Student(Object j){
        String sql = "DELETE FROM tblsinhvien WHERE masv = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, j.toString());
            
            
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Xoa sinh vien thanh cong!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Xoa sinh vien khong thanh cong!");
            e.printStackTrace();
        }
    }
    public void edit_Student(Sinhvien s, Object IDold){
        String sql = "UPDATE tblsinhvien SET MaSV=?,HoTen=?,MaLop=?,GioiTinh=?,SDT=? WHERE MaSV = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, s.getMaSV());
            ps.setString(2, s.getHoTen());
            ps.setString(3, s.getMaLop());
            ps.setString(4, s.getGioiTinh());
            ps.setString(5, s.getSDT());
            ps.setString(6, IDold.toString());
            
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Sua sinh vien thanh cong!");
        } catch (Exception e) {
            
            JOptionPane.showMessageDialog(null, "Sua sinh vien khong thanh cong!");
        }
    }
    public void sortMa(JTable tbl, DefaultTableModel model){   
        try {
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM tblsinhvien ORDER BY MaSV ASC");
            ResultSetMetaData metaData = resultSet.getMetaData();
            
            int columnCount = metaData.getColumnCount();
            for(int columnIndex = 1; columnIndex <= columnCount; columnIndex++){
                model.addColumn(metaData.getColumnLabel(columnIndex));
            }
            
            // Them du lieu tu ket qua truy van vao model
            while(resultSet.next()){
                Object[] row = new Object[columnCount];
                for(int i = 0; i < columnCount; i++){
                    row[i] = resultSet.getObject(i+1);
                }
                model.addRow(row);
            }
            
            JOptionPane.showMessageDialog(null, "Sinh vien da duoc sap xep theo thu tu tang dan MaSV!");
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void sortName(JTable tbl, DefaultTableModel model){   
        try {
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM tblsinhvien ORDER BY HoTen ASC");
            ResultSetMetaData metaData = resultSet.getMetaData();
            
            int columnCount = metaData.getColumnCount();
            for(int columnIndex = 1; columnIndex <= columnCount; columnIndex++){
                model.addColumn(metaData.getColumnLabel(columnIndex));
            }
            
            // Them du lieu tu ket qua truy van vao model
            while(resultSet.next()){
                Object[] row = new Object[columnCount];
                for(int i = 0; i < columnCount; i++){
                    row[i] = resultSet.getObject(i+1);
                }
                model.addRow(row);
            }
            
            JOptionPane.showMessageDialog(null, "Sinh vien da duoc sap xep theo Ho Ten!");
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public  void searchValue(JTable table,DefaultTableModel model, SinhVienView sv) {
        String sql = "SELECT * FROM tblsinhvien WHERE MaSV LIKE ?";
        try {
            Statement statement = conn.createStatement();
            PreparedStatement ps = (PreparedStatement) conn.prepareStatement(sql);
            String msv = sv.searchTxt.getText().trim();
            ps.setString(1, "%" + msv + "%");
            ResultSet resultSet = ps.executeQuery();
            
            ResultSetMetaData metaData = resultSet.getMetaData();
            
            
            int columnCount = metaData.getColumnCount();
            for(int columnIndex = 1; columnIndex <= columnCount; columnIndex++){
                model.addColumn(metaData.getColumnLabel(columnIndex));
            }
            
            // Them du lieu tu ket qua truy van vao model
            while(resultSet.next()){
                Object[] row = new Object[columnCount];
                for(int i = 0; i < columnCount; i++){
                    row[i] = resultSet.getObject(i+1);
                }
                model.addRow(row);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
}
    public void excel_print(DefaultTableModel model){
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("Lop Data");

        // Tạo hàng header
        XSSFRow headerRow = sheet.createRow(0);
        for (int i = 0; i < model.getColumnCount(); i++) {
            XSSFCell cell = headerRow.createCell(i);
            cell.setCellValue(model.getColumnName(i));
        }

        // Dữ liệu
        FileOutputStream fileOut = null;
        try {
            // Hiển thị hộp thoại lưu tệp
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Save Excel File");
            FileNameExtensionFilter filter = new FileNameExtensionFilter("Excel Files (*.xlsx)", "xlsx");
            fileChooser.setFileFilter(filter);
            int userSelection = fileChooser.showSaveDialog(null);
            if (userSelection == JFileChooser.APPROVE_OPTION) {
                String filePath = fileChooser.getSelectedFile().getAbsolutePath();
                if (!filePath.endsWith(".xlsx")) {
                    filePath += ".xlsx"; // Đảm bảo rằng tên file có phần mở rộng .xlsx
                }
                fileOut = new FileOutputStream(filePath);

                // Viết dữ liệu vào file Excel
                for (int i = 0; i < model.getRowCount(); i++) {
                    XSSFRow row = sheet.createRow(i + 1);
                    for (int j = 0; j < model.getColumnCount(); j++) {
                        XSSFCell cell = row.createCell(j);
                        Object value = model.getValueAt(i, j);
                        if (value != null) {
                            cell.setCellValue(value.toString());
                        }
                    }
                }

                // Lưu file Excel
                workbook.write(fileOut);
                System.out.println("Excel file exported successfully.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fileOut != null) {
                try {
                    fileOut.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    public static void main(String[] args){
        DaoSinhVien sv = new DaoSinhVien();
    }
}
