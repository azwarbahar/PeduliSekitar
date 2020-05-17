package com.example.coronavirus.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class NegaraResponseModel {

    private NegaraConfermed confirmed;
    private NegaraRecofered recovered;
    private NegaraDeaths deaths;
    private String lastUpdate;

    public NegaraResponseModel(NegaraConfermed confirmed, NegaraRecofered recovered, NegaraDeaths deaths, String lastUpdate) {
        this.confirmed = confirmed;
        this.recovered = recovered;
        this.deaths = deaths;
        this.lastUpdate = lastUpdate;
    }

    public NegaraConfermed getConfirmed() {
        return confirmed;
    }

    public void setConfirmed(NegaraConfermed confirmed) {
        this.confirmed = confirmed;
    }

    public NegaraRecofered getRecovered() {
        return recovered;
    }

    public void setRecovered(NegaraRecofered recovered) {
        this.recovered = recovered;
    }

    public NegaraDeaths getDeaths() {
        return deaths;
    }

    public void setDeaths(NegaraDeaths deaths) {
        this.deaths = deaths;
    }

    public String getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(String lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public class NegaraDeaths{
        @SerializedName("value")
        @Expose
        private long  value;

        @SerializedName("detail")
        @Expose
        private String  detail;

        public long getValue() {
            return value;
        }

        public void setValue(long value) {
            this.value = value;
        }

        public String getDetail() {
            return detail;
        }

        public void setDetail(String detail) {
            this.detail = detail;
        }

        public NegaraDeaths(long value, String detail) {
            this.value = value;
            this.detail = detail;
        }
    }

    public class NegaraRecofered{
        @SerializedName("value")
        @Expose
        private long  value;

        @SerializedName("detail")
        @Expose
        private String  detail;

        public long getValue() {
            return value;
        }

        public void setValue(long value) {
            this.value = value;
        }

        public String getDetail() {
            return detail;
        }

        public void setDetail(String detail) {
            this.detail = detail;
        }

        public NegaraRecofered(long value, String detail) {
            this.value = value;
            this.detail = detail;
        }
    }

    public class NegaraConfermed{
        @SerializedName("value")
        @Expose
        private long  value;

        @SerializedName("detail")
        @Expose
        private String  detail;

        public long getValue() {
            return value;
        }

        public void setValue(long value) {
            this.value = value;
        }

        public String getDetail() {
            return detail;
        }

        public void setDetail(String detail) {
            this.detail = detail;
        }

        public NegaraConfermed(long value, String detail) {
            this.value = value;
            this.detail = detail;
        }
    }

}
