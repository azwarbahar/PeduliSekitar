package com.example.coronavirus.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class IndonesiaModel {

    @SerializedName("meninggal")
    @Expose
    private String meninggal;

    @SerializedName("sembuh")
    @Expose
    private String sembuh;

    @SerializedName("perawatan")
    @Expose
    private String perawatan;

    @SerializedName("jumlahKasus")
    @Expose
    private String jumlahKasus;

    public IndonesiaModel(String meninggal, String sembuh, String perawatan, String jumlahKasus) {
        this.meninggal = meninggal;
        this.sembuh = sembuh;
        this.perawatan = perawatan;
        this.jumlahKasus = jumlahKasus;
    }

    public String getMeninggal() {
        return meninggal;
    }

    public void setMeninggal(String meninggal) {
        this.meninggal = meninggal;
    }

    public String getSembuh() {
        return sembuh;
    }

    public void setSembuh(String sembuh) {
        this.sembuh = sembuh;
    }

    public String getPerawatan() {
        return perawatan;
    }

    public void setPerawatan(String perawatan) {
        this.perawatan = perawatan;
    }

    public String getJumlahKasus() {
        return jumlahKasus;
    }

    public void setJumlahKasus(String jumlahKasus) {
        this.jumlahKasus = jumlahKasus;
    }
}
