package com.example.coronavirus.model;

public class PropertisModel {

    private String Kode_Provinsi;
    private String Provinsi;
    private String Kasus_Terkonfirmasi_Akumulatif;
    private String Kasus_Sembuh_Akumulatif;
    private String Kasus_Meninggal_Akumulatif;

    public PropertisModel() {

    }

    public String getKode_Provinsi() {
        return Kode_Provinsi;
    }

    public void setKode_Provinsi(String kode_Provinsi) {
        Kode_Provinsi = kode_Provinsi;
    }

    public String getProvinsi() {
        return Provinsi;
    }

    public void setProvinsi(String provinsi) {
        Provinsi = provinsi;
    }

    public String getKasus_Terkonfirmasi_Akumulatif() {
        return Kasus_Terkonfirmasi_Akumulatif;
    }

    public void setKasus_Terkonfirmasi_Akumulatif(String kasus_Terkonfirmasi_Akumulatif) {
        Kasus_Terkonfirmasi_Akumulatif = kasus_Terkonfirmasi_Akumulatif;
    }

    public String getKasus_Sembuh_Akumulatif() {
        return Kasus_Sembuh_Akumulatif;
    }

    public void setKasus_Sembuh_Akumulatif(String kasus_Sembuh_Akumulatif) {
        Kasus_Sembuh_Akumulatif = kasus_Sembuh_Akumulatif;
    }

    public String getKasus_Meninggal_Akumulatif() {
        return Kasus_Meninggal_Akumulatif;
    }

    public void setKasus_Meninggal_Akumulatif(String kasus_Meninggal_Akumulatif) {
        Kasus_Meninggal_Akumulatif = kasus_Meninggal_Akumulatif;
    }

    public PropertisModel(String kode_Provinsi, String provinsi, String kasus_Terkonfirmasi_Akumulatif, String kasus_Sembuh_Akumulatif, String kasus_Meninggal_Akumulatif) {
        Kode_Provinsi = kode_Provinsi;
        Provinsi = provinsi;
        Kasus_Terkonfirmasi_Akumulatif = kasus_Terkonfirmasi_Akumulatif;
        Kasus_Sembuh_Akumulatif = kasus_Sembuh_Akumulatif;
        Kasus_Meninggal_Akumulatif = kasus_Meninggal_Akumulatif;
    }
//    private String Pembaruan;
//    private String Editor;


}
