/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Model;

/**
 *
 * @author Hi
 */
public class Sinhvien {
    private String maSV,hoTen,maLop,gioiTinh,sdt;
    public Sinhvien(){
        
    }
    public void sinhvien(String maSV,String hoTen, String maLop, String gioiTinh,String sdt){
        this.maSV = maSV;
        this.hoTen = hoTen;
        this.maLop = maLop;
        this.gioiTinh = gioiTinh;
        this.sdt = sdt;
    }
    public String getMaSV(){
        return maSV;
    }
    public void setMaSV(String maSV){
        this.maSV = maSV;
    }
    public String getHoTen(){
        return hoTen;
    }
    public void setHoTen(String hoTen){
        this.hoTen = hoTen;
    }
    public String getMaLop(){
        return maLop;
    }
    public void setMaLop(String maLop){
        this.maLop = maLop;
    }
    public String getGioiTinh(){
        return gioiTinh;
    }
    public void setGioiTinh(String gioiTinh){
        this.gioiTinh = gioiTinh;
    }
    public String getSDT(){
        return sdt;
    }
    public void setSDT(String sdt){
        this.sdt=sdt;
    }
}
