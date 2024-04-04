/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;

import Model.diem;

import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ADMIN
 */
public class DaoDiem {

    private Connection conn;

    public DaoDiem() {
        String url = "jdbc:mysql://localhost:3306/quanlysinhvien";
        var user = "root";
        var password = "";

        try {
            conn = DriverManager.getConnection(url, user, password);
            System.out.println("Ket noi MySQL thanh cong");
            System.out.println(conn.getCatalog());
        } catch (SQLException ex) {
            Logger.getLogger(DaoDiem.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Object[][] loadData() {
        Object[][] data = null;

        try {
            Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = stmt.executeQuery("SELECT tbldiem.MaKQ, tbldiem.MaSV, tblsinhvien.HoTen, tbllop.TenLop, tbldiem.Mon, tbldiem.DiemRL, tbldiem.DiemGK, tbldiem.DiemCK, tbldiem.TongKet, tbldiem.TinhTrang  \n"
                    + "FROM tbldiem\n"
                    + "INNER JOIN tblsinhvien ON tbldiem.MaSV = tblsinhvien.MaSV\n" // Thêm khoảng trắng ở đây
                    + "INNER JOIN tbllop ON tblsinhvien.MaLop = tbllop.MaLop");

            // Xác định số lượng bản ghi trong ResultSet
            rs.last();
            int rowCount = rs.getRow();
            rs.beforeFirst();

            // Khởi tạo mảng dữ liệu với số lượng hàng và 5 cột
            data = new Object[rowCount][10];

            // Đọc dữ liệu từ ResultSet và đưa vào mảng
            int i = 0;
            while (rs.next()) {

                data[i][0] = rs.getString("MaKQ");
                data[i][1] = rs.getString("MaSV");
                data[i][2] = rs.getString("HoTen");
                data[i][3] = rs.getString("TenLop");
                data[i][4] = rs.getString("Mon");
                data[i][5] = rs.getDouble("DiemRL");
                data[i][6] = rs.getDouble("DiemGK");
                data[i][7] = rs.getDouble("DiemCK");
                data[i][8] = rs.getDouble("TongKet");
                data[i][9] = rs.getString("TinhTrang");
                i++;
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            if (conn != null) {
//                    conn.close(); // Đóng kết nối sau khi sử dụng xong
            }
        }
        return data;
    }

    public String[] queryStudentInfo(String studentID) {

        String[] result = new String[2];

        String sql = "SELECT tblsinhvien.HoTen, tbllop.TenLop \n"
                + "FROM tblsinhvien, tbllop \n"
                + "WHERE tblsinhvien.MaSV = ?\n"
                + "AND tblsinhvien.MaLop = tbllop.MaLop";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, studentID);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                result[0] = rs.getString("HoTen");
                result[1] = rs.getString("TenLop");

            } else {
                result[0] = "Không tìm thấy sinh viên có mã " + studentID;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;

    }
    
    public boolean kiemtratrungKQ(diem d){
        
        String sql = "SELECT * \n" 
                + "FROM tbldiem\n"
                + "WHERE tbldiem.MaSV = ? AND tbldiem.Mon = ?";
        
        try{
            PreparedStatement ps = conn.prepareStatement(sql);
            
            ps.setString(1, d.getMaSV());
            ps.setString(2, d.getMon());
            
            ResultSet rs = ps.executeQuery();
            
            if (rs.next()) {
            return true;
            }
        }catch (Exception e) {
            e.printStackTrace();
        }    
        
        return false;
    }
    
    public void addStudent(diem d){
        String sql = "INSERT INTO tbldiem(MaSV,Mon,DiemRL,DiemGK,DiemCK,TongKet,TinhTrang) VALUES (?,?,?,?,?,?,?)";
        
        try{
            PreparedStatement ps = conn.prepareStatement(sql);
                        
            ps.setString(1, d.getMaSV());
            ps.setString(2, d.getMon());
            ps.setFloat(3, d.getDiemRL());
            ps.setFloat(4, d.getDiemGK());
            ps.setFloat(5, d.getDiemCK());
            ps.setFloat(6, d.getTongKet());
            ps.setString(7, d.getTinhTrang());
            
            ps.executeUpdate();
        }catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    
    public void editStudent(diem d){
        String sql = "UPDATE tbldiem SET DiemRL = ?,DiemGK = ?,DiemCK = ?,TongKet = ?,TinhTrang = ? WHERE MaKQ = ?";
        
        try{
            PreparedStatement ps = conn.prepareStatement(sql);
            
            ps.setFloat(1, d.getDiemRL());
            ps.setFloat(2, d.getDiemGK());
            ps.setFloat(3, d.getDiemCK());
            ps.setFloat(4, d.getTongKet());
            ps.setString(5, d.getTinhTrang());
            ps.setInt(6, d.getMaKQ());
            
            ps.executeUpdate();
        }catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    
    public void deleteStudent(diem d){
        String sql = "DELETE FROM tbldiem WHERE MaKQ = ?";
        
        try{
            PreparedStatement ps = conn.prepareStatement(sql);
            
            ps.setInt(1, d.getMaKQ());
            
            ps.executeUpdate();
        }catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    
    public Object[][] searchStudent(String keyword){
        Object[][] data = null;
        try {
            Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            // Tiến hành truy vấn SQL với keyword
            ResultSet rs = stmt.executeQuery("SELECT tbldiem.MaKQ, tbldiem.MaSV, tblsinhvien.HoTen, tbllop.TenLop, tbldiem.Mon, tbldiem.DiemRL, tbldiem.DiemGK, tbldiem.DiemCK, tbldiem.TongKet, tbldiem.TinhTrang \n" 
                                            +"FROM tbldiem\n" 
                                            + "INNER JOIN tblsinhvien ON tbldiem.MaSV = tblsinhvien.MaSV\n" 
                                            +"INNER JOIN tbllop ON tblsinhvien.MaLop = tbllop.MaLop "
                                            + "WHERE tbldiem.MaSV LIKE '%" + keyword + "%'");
            
            // Xác định số lượng bản ghi trong ResultSet
            rs.last();
            int rowCount = rs.getRow();
            rs.beforeFirst();
            
            // Khởi tạo mảng dữ liệu với số lượng hàng và 5 cột
            data = new Object[rowCount][10];
            
            // Đọc dữ liệu từ ResultSet và đưa vào mảng
            int i = 0;
            while (rs.next()) {

                data[i][0] = rs.getString("MaKQ");
                data[i][1] = rs.getString("MaSV");
                data[i][2] = rs.getString("HoTen");
                data[i][3] = rs.getString("TenLop");
                data[i][4] = rs.getString("Mon");
                data[i][5] = rs.getDouble("DiemRL");
                data[i][6] = rs.getDouble("DiemGK");
                data[i][7] = rs.getDouble("DiemCK");
                data[i][8] = rs.getDouble("TongKet");
                data[i][9] = rs.getString("TinhTrang");
                i++;
            
            
        }
        }catch (SQLException ex) {
                ex.printStackTrace();
        }
        return data;
    }

    public static void main(String[] args) {
        DaoDiem c = new DaoDiem();
        
    }
}
