package com.example.appdoctruyen_v2.model;

public class yeuthich {
    public String tentaikhoan;
    public String tieude;

    public yeuthich() {
    }

    public yeuthich(String tentaikhoan, String tieude) {
        this.tentaikhoan = tentaikhoan;
        this.tieude = tieude;
    }

    public String getTentaikhoan() {
        return tentaikhoan;
    }

    public void setTentaikhoan(String tentaikhoan) {
        this.tentaikhoan = tentaikhoan;
    }

    public String getTieude() {
        return tieude;
    }

    public void setTieude(String tieude) {
        this.tieude = tieude;
    }

    @Override
    public String toString() {
        return "yeuthich{" +
                "tentaikhoan='" + tentaikhoan + '\'' +
                ", tieude='" + tieude + '\'' +
                '}';
    }
}
