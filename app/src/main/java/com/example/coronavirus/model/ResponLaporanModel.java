package com.example.coronavirus.model;

import java.util.List;

public class ResponLaporanModel {
    String kode, pesan;
    List<LaporanModel> resultDataLaporan;
    List<SinglePetugasModel> result;

    public List<SinglePetugasModel> getResult() {
        return result;
    }

    public void setResult(List<SinglePetugasModel> result) {
        this.result = result;
    }

    public String getKode() {
        return kode;
    }

    public void setKode(String kode) {
        this.kode = kode;
    }

    public String getPesan() {
        return pesan;
    }

    public void setPesan(String pesan) {
        this.pesan = pesan;
    }

    public List<LaporanModel> getResultDataLaporan() {
        return resultDataLaporan;
    }

    public void setResultDataLaporan(List<LaporanModel> resultDataLaporan) {
        this.resultDataLaporan = resultDataLaporan;
    }
}
