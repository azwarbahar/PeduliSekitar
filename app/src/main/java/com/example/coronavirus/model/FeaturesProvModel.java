package com.example.coronavirus.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class FeaturesProvModel {

    @SerializedName("type")
    @Expose
    private String type;
    private Properties properties;
    private Geometry geometry;

    public FeaturesProvModel(String type, Properties properties, Geometry geometry) {
        this.type = type;
        this.properties = properties;
        this.geometry = geometry;
    }

    public FeaturesProvModel() {

    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Properties getProperties() {
        return properties;
    }

    public void setProperties(Properties properties) {
        this.properties = properties;
    }

    public Geometry getGeometry() {
        return geometry;
    }

    public void setGeometry(Geometry geometry) {
        this.geometry = geometry;
    }

    public class Properties {

        @SerializedName("Kode_Provi")
        @Expose
        private String Kode_Provinsi;

        @SerializedName("Provinsi")
        @Expose
        private String Provinsi;

        @SerializedName("Kasus_Posi")
        @Expose
        private String Kasus_Terkonfirmasi_Akumulatif;

        @SerializedName("Kasus_Semb")
        @Expose
        private String Kasus_Sembuh_Akumulatif;

        @SerializedName("Kasus_Meni")
        @Expose
        private String Kasus_Meninggal_Akumulatif;

        public Properties(String kode_Provinsi, String provinsi, String kasus_Terkonfirmasi_Akumulatif, String kasus_Sembuh_Akumulatif, String kasus_Meninggal_Akumulatif) {
            Kode_Provinsi = kode_Provinsi;
            Provinsi = provinsi;
            Kasus_Terkonfirmasi_Akumulatif = kasus_Terkonfirmasi_Akumulatif;
            Kasus_Sembuh_Akumulatif = kasus_Sembuh_Akumulatif;
            Kasus_Meninggal_Akumulatif = kasus_Meninggal_Akumulatif;
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
//        @SerializedName("Pembaruan")
//        @Expose
//        private String Pembaruan;
//
//        @SerializedName("Editor")
//        @Expose
//        private String Editor;


    }

    public class Geometry {
        @SerializedName("type")
        @Expose
        private String type;

        @SerializedName("coordinates")
        @Expose
        private List<String>coordinates;

        public Geometry(String type, List<String> coordinates) {
            this.type = type;
            this.coordinates = coordinates;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public List<String> getCoordinates() {
            return coordinates;
        }

        public void setCoordinates(List<String> coordinates) {
            this.coordinates = coordinates;
        }
    }
}
