/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Dao;

import Model.Lop;
import View.LopView;

import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author Admin
 */
public class DaoLop {
   LopView v;
   Lop m;
   private Connection conn; 
    public DaoLop() {
        String DB_URL = "jdbc:mysql://localhost:3306/quanlysinhvien";
        String USER_NAME = "root";
        String PASSWORD = "";

        try {
            conn = DriverManager.getConnection(DB_URL, USER_NAME, PASSWORD);
            System.out.println("seccessfully connect");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public DaoLop(LopView v){
        this.v = v;
    }
    public void loadData(DefaultTableModel model){
        
        
        String sql = "SELECT tbllop.MaLop, tbllop.TenLop, tbllop.TenNganh, " +
                 "CASE WHEN tblgiangvien.ChuNhiem = tbllop.MaLop THEN tblgiangvien.MaGV ELSE '' END AS MaGV, " +
                 "tbllop.HeDaoTao, tbllop.NamNhapHoc " +
                 "FROM tbllop " +
                 "LEFT JOIN tblgiangvien ON tblgiangvien.ChuNhiem = tbllop.MaLop";
        try {
            PreparedStatement ps = (PreparedStatement) conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                String maLop = rs.getString("MaLop");
                String tenLop = rs.getString("TenLop");
                String tenNganh = rs.getString("TenNganh");
                String maGV = rs.getString("MaGV");
                String heDT = rs.getString("HeDaoTao");
                int namNH = rs.getInt("NamNhapHoc");
                model.addRow(new Object[]{maLop,tenLop,tenNganh,maGV,heDT,namNH});
                
            }
        } catch (Exception e) {
            e.printStackTrace();
        }    
    }
    
    public void addLop(DefaultTableModel model,String malop,String tenlop,String tennganh,String hedt,String namnh){
        
        if(malop.equals("")||tenlop.equals("")||tennganh.equals("")||
                          hedt.equals("")||namnh.equals("")  ){
            JOptionPane.showMessageDialog(null, "Nhap day du thong tin.", "Error", JOptionPane.ERROR_MESSAGE);
        }else{            
            int nam = Integer.parseInt(namnh);            
            m = new Lop(malop, tenlop,tennganh,hedt,nam);
            // Kiểm tra trung lặp
            boolean duplicate = false;
            for(int i = 0; i < model.getRowCount(); i++){
                if(malop.equals(model.getValueAt(i, 0))){
                    duplicate = true;
                    break;
                }
            }

            if(duplicate!=true){


            if (m != null) {
                String maGV = ""; // Giá trị mặc định cho cột MaGV là rỗng
                model.addRow(new Object[]{m.getMaLop(), m.getTenLop(), m.getTenNganh(), maGV, m.getHeDT(), m.getNamNhapHoc()});
            }
            String sql = "INSERT INTO tbllop(MaLop, TenLop, TenNganh, HeDaoTao,NamNhapHoc) VALUES(?,?,?,?,?)";
                try{
                PreparedStatement ps = (PreparedStatement) conn.prepareStatement(sql);  
                ps.setString(1,m.getMaLop() );
                ps.setString(2,m.getTenLop());
                ps.setString(3, m.getTenNganh());  
                ps.setString(4, m.getHeDT());
                ps.setInt(5, m.getNamNhapHoc());

                ps.executeUpdate();
                }catch(Exception e){  
                    e.printStackTrace();
                }
            }else{
                JOptionPane.showMessageDialog(null, "Ma Lop da ton tai.","Canh Bao", JOptionPane.ERROR_MESSAGE);
            }
        }
      }
    
    public void updateLop(DefaultTableModel model,JTable table,String malop,String tenlop,String tennganh,String hedt,String namnh){
                    int selectRowIndex = table.getSelectedRow();
                    // Xác nhận với người dùng đồng ý sửa thông tin 
                    
                    int nam = Integer.parseInt(namnh);
                    Lop m = new Lop(malop, tenlop,tennganh,hedt,nam);
                     if(malop.equals("")||tenlop.equals("")||tennganh.equals("")||
                          hedt.equals("")||namnh.equals("")  ){
                        JOptionPane.showMessageDialog(null, "Nhap day du thong tin.", "Error", JOptionPane.ERROR_MESSAGE);
                    }else{
                    int option = JOptionPane.showConfirmDialog(null, "Do you want to edit this student's information?", "Confirmation", JOptionPane.YES_NO_OPTION);
                    if (option == JOptionPane.YES_OPTION){
                        
//                        lopModel l = new LopModel(maLop, tenLop, tenNganh, heDT, nam);
                        
                        // cập nhập lại bảng 
                        model.setValueAt(m.getMaLop(), selectRowIndex, 0);
                        model.setValueAt(m.getTenLop(), selectRowIndex, 1);
                        model.setValueAt(m.getTenNganh(), selectRowIndex, 2);
                        model.setValueAt(m.getHeDT(), selectRowIndex, 4);
                        model.setValueAt(m.getNamNhapHoc(), selectRowIndex, 5);                        
                        // update len csdl
                        String sql = "UPDATE tbllop SET TenLop=?, TenNganh=?, HeDaoTao=?, NamNhapHoc=? WHERE MaLop=?";
                        try {
                            PreparedStatement ps = (PreparedStatement) conn.prepareStatement(sql);
                            ps.setString(1, m.getTenLop());
                            ps.setString(2, m.getTenNganh());
                            ps.setString(3,m.getHeDT());
                            ps.setInt(4,m.getNamNhapHoc());
                            ps.setString(5, m.getMaLop()); 
                            ps.executeUpdate();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }                     
                    }else{
                        JOptionPane.showMessageDialog(null, "Hay chon Lop muon sua.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                    }
    }
    
    public void deleteLop(DefaultTableModel model,JTable table){
     int selecteIndexRow = table.getSelectedRow();
        if (selecteIndexRow != -1) {
        String idToDelete = model.getValueAt(selecteIndexRow, 0).toString();
        String sql = "DELETE FROM tbllop WHERE MaLop=?";
        try {
            PreparedStatement ps = (PreparedStatement) conn.prepareStatement(sql);
            ps.setString(1, idToDelete);
            int deletedRows = ps.executeUpdate();
            if (deletedRows > 0) {
                JOptionPane.showMessageDialog(null, "Deleted successfully.");
            } else {
                JOptionPane.showMessageDialog(null, "Failed to delete.", "Error", JOptionPane.ERROR_MESSAGE);
            }
            model.removeRow(selecteIndexRow);
        } catch (Exception e) {
            e.printStackTrace();
        }
    } else {
        JOptionPane.showMessageDialog(null, "Hay chon Lop muon xoa.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void searchByMaLop(DefaultTableModel model,String malop) {
            // Xóa dữ liệu cũ trong bảng
            model.setRowCount(0);  
            // Truy vấn CSDL để lấy dữ liệu dựa trên mã lớp
            System.out.println(malop); 
            String sql = "SELECT tbllop.MaLop, tbllop.TenLop, tbllop.TenNganh, " +
                         "CASE WHEN tblgiangvien.ChuNhiem = tbllop.MaLop THEN tblgiangvien.MaGV ELSE '' END AS MaGV, " +
                         "tbllop.HeDaoTao, tbllop.NamNhapHoc " +
                         "FROM tbllop " +
                         "LEFT JOIN tblgiangvien ON tblgiangvien.ChuNhiem = tbllop.MaLop " +
                         "WHERE tbllop.MaLop LIKE ?";
            try {
                PreparedStatement ps = (PreparedStatement) conn.prepareStatement(sql);
                ps.setString(1, "%" + malop + "%"); // Sử dụng toán tử LIKE để tìm kiếm phù hợp
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    String maLopResult = rs.getString("MaLop");
                    String tenLop = rs.getString("TenLop");
                    String tenNganh = rs.getString("TenNganh");
                    String maGV = rs.getString("MaGV");
                    String heDT = rs.getString("HeDaoTao");
                    int namNH = rs.getInt("NamNhapHoc");
                    model.addRow(new Object[]{maLopResult, tenLop, tenNganh, maGV, heDT, namNH});
                    System.out.println("tim kiem");
                }       
            } catch (SQLException e) {
                e.printStackTrace();
            }       
}
    
    public void exportToExcel(DefaultTableModel model) {
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
}