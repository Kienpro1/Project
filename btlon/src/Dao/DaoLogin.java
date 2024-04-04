package Dao;

import Model.Account;

import java.sql.*;

public class DaoLogin {
    public static boolean check(String username) {
        // Thực hiện kết nối đến cơ sở dữ liệu
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/quanlysinhvien", "root", "")) {
            // Tạo truy vấn
            String sql = "SELECT username FROM account WHERE username = ?";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, username);
                // Thực hiện truy vấn
                try (ResultSet rs = stmt.executeQuery()) {
                    // Kiểm tra xem có dòng nào trả về không
                    return rs.next();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    public static boolean login(String username, String password) {
        // Thực hiện kết nối đến cơ sở dữ liệu
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/quanlysinhvien", "root", "")) {
            // Tạo truy vấn
            String sql = "SELECT * FROM account WHERE username = ? AND password = ?";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, username);
                stmt.setString(2, password);
                // Thực hiện truy vấn
                try (ResultSet rs = stmt.executeQuery()) {
                    // Kiểm tra xem có dòng nào trả về không
                    return rs.next();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    public static void addAccount(Account account){
        PreparedStatement statement = null;

        try{

            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/quanlysinhvien", "root", "");
           String sql= "insert into account(username,password) values(?, ?) ";
           statement=con.prepareStatement(sql);
           statement.setString(1,account.getUsername());
           statement.setString(2,account.getPassword());
           statement.execute();
        }catch (SQLException ex) {
            System.out.println(ex);
        }
//        }finally {
//            if(statement != null) {
//                try {
//                    statement.close();
//                } catch (SQLException ex) {
//                    Logger.getLogger(controller.class.getName()).log(Level.SEVERE, null, ex);
//                }
//            }
//
//            if (con != null) {
//                try {
//                    con.close();
//                } catch (SQLException ex) {
//                    Logger.getLogger(controller.class.getName()).log(Level.SEVERE, null, ex);
//                }
//            }
//        }
    }
}
