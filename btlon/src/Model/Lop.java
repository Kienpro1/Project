/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Model;

/**
 *
 * @author Admin
 */
public class Lop {
        private String maLop;
    private String tenLop;
    private String tenNganh;
    private String heDT;
    private int namNhapHoc;
    
    public Lop(){
        
    }

    public Lop(String maLop, String tenLop, String tenNganh, String heDT, int namNhapHoc) {
        this.maLop = maLop;
        this.tenLop = tenLop;
        this.tenNganh = tenNganh;
        this.heDT = heDT;
        this.namNhapHoc = namNhapHoc;
    }

    public String getHeDT() {
        return heDT;
    }

    public void setHeDT(String heDT) {
        this.heDT = heDT;
    }

    public String getMaLop() {
        return maLop;
    }

    public void setMaLop(String maLop) {
        this.maLop = maLop;
    }

    public int getNamNhapHoc() {
        return namNhapHoc;
    }

    public void setNamNhapHoc(int namNhapHoc) {
        this.namNhapHoc = namNhapHoc;
    }

    public String getTenLop() {
        return tenLop;
    }

    public void setTenLop(String tenLop) {
        this.tenLop = tenLop;
    }

    public String getTenNganh() {
        return tenNganh;
    }

    public void setTenNganh(String tenNganh) {
        this.tenNganh = tenNganh;
    }
}
