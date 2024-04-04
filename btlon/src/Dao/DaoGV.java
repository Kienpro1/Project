package Dao;
import Model.GiangVien;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DaoGV {
    Connection con;
    private PreparedStatement ps;
    public static List<GiangVien> findAll() {
        List<GiangVien> GVList = new ArrayList<>();

        Connection connection = null;
        Statement statement = null;

        try {
            //lay tat ca danh sach sinh vien
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/quanlysinhvien", "root", "");

            //query
            String sql = "SELECT tblgiangvien.MaGV, tblgiangvien.HoTenGV, tblgiangvien.Tuoi, tblgiangvien.BoMon, tblgiangvien.ChuNhiem, tblgiangvien.SDT FROM tblgiangvien";
            statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                GiangVien GV = new GiangVien(resultSet.getString("MaGV"),
                                            resultSet.getString("HoTenGV"),
                                            resultSet.getInt("Tuoi"),
                                            resultSet.getString("BoMon"),
                                            resultSet.getString("ChuNhiem"),
                                            resultSet.getString("SDT"));

                GVList.add(GV);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DaoGV.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if(statement != null) {
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DaoGV.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DaoGV.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        //ket thuc.

        return GVList;
    }

    public static void insert(GiangVien GV) {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            //lay tat ca danh sach sinh vien
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/quanlysinhvien", "root", "");

            //query
            String sql = "insert into tblgiangvien(MaGV, HoTenGV, Tuoi, BoMon, ChuNhiem,SDT) values(?, ?, ?, ?, ?,?)";
            statement = connection.prepareCall(sql);

            statement.setString(1, GV.getMaGV());
            statement.setString(2, GV.getHoTenGV());
            statement.setInt(3, GV.getTuoi());
            statement.setString(4, GV.getBoMon());
            statement.setString(5, GV.getChuNhiem());
            statement.setString(6, GV.getSDT());


            statement.execute();
        } catch (SQLException ex) {
            Logger.getLogger(DaoGV.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if(statement != null) {
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DaoGV.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DaoGV.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        //ket thuc.
    }

    public void update(GiangVien GV)
    {

        String sql = "UPDATE tblgiangvien SET HoTenGV = ?,Tuoi = ?,BoMon = ?,ChuNhiem = ?,SDT = ? WHERE MaGV = ?";

        try{
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/quanlysinhvien", "root", "");
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, GV.getHoTenGV());
            ps.setInt(2, GV.getTuoi());
            ps.setString(3, GV.getBoMon());
            ps.setString(4, GV.getChuNhiem());
            ps.setString(5, GV.getSDT());
            ps.setString(6, GV.getMaGV());

            ps.executeUpdate();
        }catch(Exception e)
        {
            e.printStackTrace();
        }
    }


    public static void delete(String MaGV) {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            //lay tat ca danh sach sinh vien
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/quanlysinhvien", "root", "");

            //query
            String sql = "delete from tblgiangvien where MaGV = ?";
            statement = connection.prepareCall(sql);

            statement.setString(1, MaGV);

            statement.execute();
        } catch (SQLException ex) {
            Logger.getLogger(DaoGV.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if(statement != null) {
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DaoGV.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DaoGV.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        //ket thuc.
    }

    public static List<GiangVien> findByFullname(String input) {
        List<GiangVien> GVList = new ArrayList<>();

        Connection connection = null;
        PreparedStatement statement = null;

        try {
            //lay tat ca danh sach sinh vien
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/quanlysinhvien", "root", "");

            //query
            String sql = "select * from tblgiangvien where  MaGV like ? OR HoTenGV like ? OR Tuoi like ? OR BoMon like ? OR ChuNhiem like ? OR SDT like ?";
            statement = connection.prepareCall(sql);
            statement.setString(1, "%"+input+"%");
            statement.setString(2, "%"+input+"%");
            statement.setString(3, "%"+input+"%");
            statement.setString(4, "%"+input+"%");
            statement.setString(5, "%"+input+"%");
            statement.setString(6, "%"+input+"%");

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                GiangVien GV = new GiangVien(resultSet.getString("MaGV"),
                        resultSet.getString("HoTenGV"), resultSet.getInt("Tuoi"),
                        resultSet.getString("BoMon"), resultSet.getString("ChuNhiem"),
                        resultSet.getString("SDT"));
                GVList.add(GV);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DaoGV.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if(statement != null) {
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DaoGV.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DaoGV.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        //ket thuc.



        return GVList;
    }
    public static Boolean findByMaGV(String MaGV) {
        List<GiangVien> GVList = new ArrayList<>();

        Connection connection = null;
        PreparedStatement statement = null;

        try {
            //lay tat ca danh sach sinh vien
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/quanlysinhvien", "root", "");

            //query
            String sql = "select * from tblgiangvien where MaGV like ?";
            statement = connection.prepareCall(sql);
            statement.setString(1, "%" + MaGV + "%");

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException ex) {
            Logger.getLogger(DaoGV.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DaoGV.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DaoGV.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        //ket thuc.

        return true;
    }




}