package com.example.coronavirus.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ListKasusModel {

    @SerializedName("kode_pasien")
    @Expose
    private String kode_pasien;

    @SerializedName("hasil_lab")
    @Expose
    private String hasil_lab;

    @SerializedName("provinsi")
    @Expose
    private String provinsi;

    @SerializedName("lat")
    @Expose
    private String latit;

    @SerializedName("long")
    @Expose
    private String longit;

    @SerializedName("jenis_kelamin")
    @Expose
    private String jenis_kelamin;

    @SerializedName("umur")
    @Expose
    private String umur;

    @SerializedName("keterangan")
    @Expose
    private String keterangan;

    @SerializedName("id_status")
    @Expose
    private String status_pasien;

    @SerializedName("keterangan_status")
    @Expose
    private String keterangan_status;

    @SerializedName("detail_wn")
    @Expose
    private String detail_wn;

    public String getKode_pasien() {
        return kode_pasien;
    }

    public void setKode_pasien(String kode_pasien) {
        this.kode_pasien = kode_pasien;
    }

    public String getHasil_lab() {
        return hasil_lab;
    }

    public void setHasil_lab(String hasil_lab) {
        this.hasil_lab = hasil_lab;
    }

    public String getProvinsi() {
        return provinsi;
    }

    public void setProvinsi(String provinsi) {
        this.provinsi = provinsi;
    }

    public String getLatit() {
        return latit;
    }

    public void setLatit(String latit) {
        this.latit = latit;
    }

    public String getLongit() {
        return longit;
    }

    public void setLongit(String longit) {
        this.longit = longit;
    }

    public String getJenis_kelamin() {
        return jenis_kelamin;
    }

    public void setJenis_kelamin(String jenis_kelamin) {
        this.jenis_kelamin = jenis_kelamin;
    }

    public String getUmur() {
        return umur;
    }

    public void setUmur(String umur) {
        this.umur = umur;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public String getStatus_pasien() {
        return status_pasien;
    }

    public void setStatus_pasien(String status_pasien) {
        this.status_pasien = status_pasien;
    }

    public String getKeterangan_status() {
        return keterangan_status;
    }

    public void setKeterangan_status(String keterangan_status) {
        this.keterangan_status = keterangan_status;
    }

    public String getDetail_wn() {
        return detail_wn;
    }

    public void setDetail_wn(String detail_wn) {
        this.detail_wn = detail_wn;
    }

    public ListKasusModel(String kode_pasien, String hasil_lab, String provinsi, String latit, String longit, String jenis_kelamin, String umur, String keterangan, String status_pasien, String keterangan_status, String detail_wn) {
        this.kode_pasien = kode_pasien;
        this.hasil_lab = hasil_lab;
        this.provinsi = provinsi;
        this.latit = latit;
        this.longit = longit;
        this.jenis_kelamin = jenis_kelamin;
        this.umur = umur;
        this.keterangan = keterangan;
        this.status_pasien = status_pasien;
        this.keterangan_status = keterangan_status;
        this.detail_wn = detail_wn;
    }
}
