package com.example.appdoctruyen_v2.model;

import java.io.Serializable;

public class Truyen implements Serializable {

    private String TenTruyen;
    private String NoiDung;
    private String Anh;
    private int soluongyt;
    private long time;


    public Truyen() {
    }

    public Truyen(String tenTruyen, String noiDung, String anh, int soluongyt, long time) {
        TenTruyen = tenTruyen;
        NoiDung = noiDung;
        Anh = anh;
        this.soluongyt = soluongyt;
        this.time = time;
    }

    public String getTenTruyen() {
        return TenTruyen;
    }

    public void setTenTruyen(String tenTruyen) {
        TenTruyen = tenTruyen;
    }

    public String getNoiDung() {
        return NoiDung;
    }

    public void setNoiDung(String noiDung) {
        NoiDung = noiDung;
    }

    public String getAnh() {
        return Anh;
    }

    public void setAnh(String anh) {
        Anh = anh;
    }

    public int getSoluongyt() {
        return soluongyt;
    }

    public void setSoluongyt(int soluongyt) {
        this.soluongyt = soluongyt;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public  void xuattruyen(){
        System.out.println(getTenTruyen()+"+++++++++++++++++++");
    }

    @Override
    public String toString() {
        return "Truyen{" +
                "TenTruyen='" + TenTruyen + '\'' +
                ", NoiDung='" + NoiDung + '\'' +
                ", Anh='" + Anh + '\'' +
                ", soluongyt=" + soluongyt +
                ", time=" + time +
                '}';
    }
}
