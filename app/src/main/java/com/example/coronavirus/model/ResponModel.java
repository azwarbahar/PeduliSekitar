package com.example.coronavirus.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResponModel {


    private Confirmed confirmed;

    private Recofered recovered;

    private Deaths deaths;

    @SerializedName("source")
    @Expose
    private String source;

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public Confirmed getConfirmed() {
        return confirmed;
    }

    public void setConfirmed(Confirmed confirmed) {
        this.confirmed = confirmed;
    }

    public Recofered getRecovered() {
        return recovered;
    }

    public void setRecovered(Recofered recovered) {
        this.recovered = recovered;
    }

    public Deaths getDeaths() {
        return deaths;
    }

    public void setDeaths(Deaths deaths) {
        this.deaths = deaths;
    }

    public class Confirmed {
        @SerializedName("value")
        @Expose
        private long value;

        @SerializedName("detail")
        @Expose
        private String detail;


        public Confirmed(long value, String detail) {
            this.value = value;
            this.detail = detail;
        }

        public Confirmed() {

        }

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
    }

    public class Recofered {
        @SerializedName("value")
        @Expose
        private long value;

        @SerializedName("detail")
        @Expose
        private String detail;

        public Recofered(long value, String detail) {
            this.value = value;
            this.detail = detail;
        }

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
    }

    public class Deaths {
        @SerializedName("value")
        @Expose
        private long value;

        @SerializedName("detail")
        @Expose
        private String detail;

        public Deaths(long value, String detail) {
            this.value = value;
            this.detail = detail;
        }

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
    }
}
