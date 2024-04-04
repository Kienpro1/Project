/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author ADMIN
 */
public class diem {
    private int MaKQ;
    private String MaSV;
    private String Mon;
    private float DiemRL;
    private float DiemGK;
    private float DiemCK;
    private float TongKet;
    private String TinhTrang;
    
    public diem(){
        super();
    }
    
    public diem(int MaKQ,String MaSV,String Mon,float DiemRL,float DiemGK,float DiemCK,float TongKet,String TinhTrang)
    {
        super();
        this.MaKQ = MaKQ;
        this.MaSV = MaSV;
        this.Mon = Mon;
        this.DiemRL = DiemRL;
        this.DiemGK = DiemGK;
        this.DiemCK = DiemCK;
        this.TongKet = TongKet;
        this.TinhTrang = TinhTrang;
    }
    
    public int getMaKQ(){
        return MaKQ;
    }
    public void setMaKQ(int MaKQ){
        this.MaKQ = MaKQ;
    }
    
    public String getMaSV(){
        return MaSV;
    }
    public void setMaSV(String MaSV){
        this.MaSV = MaSV;
    }
    
    public String getMon(){
        return Mon;
    }
    public void setMon(String Mon){
        this.Mon = Mon;
    }
    
    public float getDiemRL(){
        return DiemRL;
    }
    public void setDiemRL(float DiemRL){
        this.DiemRL = DiemRL;
    }
    
    public float getDiemGK(){
        return DiemGK;
    }
    public void setDiemGK(float DiemGK){
        this.DiemGK = DiemGK;
    }
    
    public float getDiemCK(){
        return DiemCK;
    }
    public void setDiemCK(float DiemCK){
        this.DiemCK = DiemCK;
    }
    
    public float getTongKet(){
        return TongKet;
    }
    public void setTongKet(float TongKet){
        this.TongKet = TongKet;
    }
    
    public String getTinhTrang(){
        return TinhTrang;
    }
    public void setTinhTrang(String TinhTrang){
        this.TinhTrang = TinhTrang;
    }
}
