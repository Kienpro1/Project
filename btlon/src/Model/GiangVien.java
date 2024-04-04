package Model;
public class GiangVien {
    String MaGV;
    String HoTenGV;
    int Tuoi;
    String BoMon;
    String ChuNhiem;
    String SDT;

    public GiangVien() {
    }

    public GiangVien(String maGV, String hoTenGV, int tuoi, String boMon, String chuNhiem, String SDT) {
        MaGV = maGV;
        HoTenGV = hoTenGV;
        Tuoi = tuoi;
        BoMon = boMon;
        ChuNhiem = chuNhiem;
        this.SDT = SDT;
    }

    public String getMaGV() {
        return MaGV;
    }

    public void setMaGV(String maGV) {
        MaGV = maGV;
    }

    public String getHoTenGV() {
        return HoTenGV;
    }

    public void setHoTenGV(String hoTenGV) {
        HoTenGV = hoTenGV;
    }

    public int getTuoi() {
        return Tuoi;
    }

    public void setTuoi(int tuoi) {
        Tuoi = tuoi;
    }

    public String getBoMon() {
        return BoMon;
    }

    public void setBoMon(String boMon) {
        BoMon = boMon;
    }

    public String getChuNhiem() {
        return ChuNhiem;
    }

    public void setChuNhiem(String chuNhiem) {
        ChuNhiem = chuNhiem;
    }

    public String getSDT() {
        return SDT;
    }

    public void setSDT(String SDT) {
        this.SDT = SDT;
    }
}