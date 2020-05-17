package com.example.coronavirus.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SinglePetugasModel {

    @SerializedName("id_petugas")
    @Expose
    private String  id_petugas;

    @SerializedName("nama_petugas")
    @Expose
    private String  nama_petugas;

    @SerializedName("profesi")
    @Expose
    private String  profesi;

    @SerializedName("username")
    @Expose
    private String  username;

    @SerializedName("password")
    @Expose
    private String  password;

    public String getId_petugas() {
        return id_petugas;
    }

    public void setId_petugas(String id_petugas) {
        this.id_petugas = id_petugas;
    }

    public String getNama_petugas() {
        return nama_petugas;
    }

    public void setNama_petugas(String nama_petugas) {
        this.nama_petugas = nama_petugas;
    }

    public String getProfesi() {
        return profesi;
    }

    public void setProfesi(String profesi) {
        this.profesi = profesi;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
