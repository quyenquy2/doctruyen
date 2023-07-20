package com.example.appdoctruyen_v2.model;

import java.io.Serializable;

public class TaiKhoan implements Serializable {
    private String mTenTaiKhoan;
    private String mMatKhau;
    private String mEmail;
    private int mPhanQuyen;

    public TaiKhoan(String mTenTaiKhoan, String mMatKhau, String mEmail, int mPhanQuyen) {
        this.mTenTaiKhoan = mTenTaiKhoan;
        this.mMatKhau = mMatKhau;
        this.mEmail = mEmail;
        this.mPhanQuyen = mPhanQuyen;
    }

    public TaiKhoan() {
    }

    public TaiKhoan(String mTenTaiKhoan, String mEmail) {
        this.mTenTaiKhoan = mTenTaiKhoan;
        this.mEmail = mEmail;
    }



    public String getmTenTaiKhoan() {
        return mTenTaiKhoan;
    }

    public void setmTenTaiKhoan(String mTenTaiKhoan) {
        this.mTenTaiKhoan = mTenTaiKhoan;
    }

    public String getmMatKhau() {
        return mMatKhau;
    }

    public void setmMatKhau(String mMatKhau) {
        this.mMatKhau = mMatKhau;
    }

    public String getmEmail() {
        return mEmail;
    }

    public void setmEmail(String mEmail) {
        this.mEmail = mEmail;
    }

    public int getmPhanQuyen() {
        return mPhanQuyen;
    }

    public void setmPhanQuyen(int mPhanQuyen) {
        this.mPhanQuyen = mPhanQuyen;
    }

    public  void xuattk()
    {
        System.out.println(mTenTaiKhoan+""+mMatKhau+""+mEmail+""+mPhanQuyen);
    }

    @Override
    public String toString() {
        return "TaiKhoan{" +
                ", mTenTaiKhoan='" + mTenTaiKhoan + '\'' +
                ", mMatKhau='" + mMatKhau + '\'' +
                ", mEmail='" + mEmail + '\'' +
                ", mPhanQuyen=" + mPhanQuyen +
                '}';
    }
}
