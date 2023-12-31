package com.example.appdoctruyen_v2.model;

public class DanhGia {
    private String IDdanhgia;
    private String NoiDungDanhGia;
    private String TenTK;
    private String tenTruyen;

    public DanhGia() {
    }

    public DanhGia(String tentaikhoan, String noiDungDanhGia) {
        TenTK = tentaikhoan;
        NoiDungDanhGia =noiDungDanhGia;

    }

    public DanhGia(String iDdanhgia, String tenTK, String noiDungDanhGia) {
        this.IDdanhgia = iDdanhgia;
        NoiDungDanhGia = noiDungDanhGia;
        TenTK = tenTK;

    }

    public DanhGia(String IDdanhgia, String noiDungDanhGia, String tenTK, String tenTruyen) {
        this.IDdanhgia = IDdanhgia;
        NoiDungDanhGia = noiDungDanhGia;
        TenTK = tenTK;
        this.tenTruyen = tenTruyen;
    }

    public void setIDdanhgia(String IDdanhgia) {
        this.IDdanhgia = IDdanhgia;
    }

    public String getNoiDungDanhGia() {
        return NoiDungDanhGia;
    }

    public void setNoiDungDanhGia(String noiDungDanhGia) {
        NoiDungDanhGia = noiDungDanhGia;
    }

    public String getIDdanhgia() {
        return IDdanhgia;
    }



    public String getTenTK() {
        return TenTK;
    }

    public void setTenTK(String tenTK) {
        TenTK = tenTK;
    }

    public String getTenTruyen() {
        return tenTruyen == null ? "" : tenTruyen;
    }

    public void setTenTruyen(String tenTruyen) {
        this.tenTruyen = tenTruyen;
    }

    @Override
    public String toString() {
        return "DanhGia{" +
                "IDdanhgia=" + IDdanhgia +
                ", NoiDungDanhGia='" + NoiDungDanhGia + '\'' +
                ", TenTK='" + TenTK + '\'' +
                ", tenTruyen='" + tenTruyen + '\'' +
                '}';
    }
}
