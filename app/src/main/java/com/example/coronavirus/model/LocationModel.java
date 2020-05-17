package com.example.coronavirus.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LocationModel {

    @SerializedName("provinceState")
    @Expose
    private String provinceState;

    @SerializedName("countryRegion")
    @Expose
    private String countryRegion;

    @SerializedName("lat")
    @Expose
    private String latitude;

    @SerializedName("long")
    @Expose
    private String longitude;

    @SerializedName("confirmed")
    @Expose
    private String confirmed;

    @SerializedName("recovered")
    @Expose
    private String recovered;

    @SerializedName("deaths")
    @Expose
    private String deaths;

    @SerializedName("active")
    @Expose
    private String active;

    public LocationModel(String provinceState, String countryRegion, String latitude, String longitude, String confirmed, String recovered, String deaths, String active) {
        this.provinceState = provinceState;
        this.countryRegion = countryRegion;
        this.latitude = latitude;
        this.longitude = longitude;
        this.confirmed = confirmed;
        this.recovered = recovered;
        this.deaths = deaths;
        this.active = active;
    }

    public String getProvinceState() {
        return provinceState;
    }

    public void setProvinceState(String provinceState) {
        this.provinceState = provinceState;
    }

    public String getCountryRegion() {
        return countryRegion;
    }

    public void setCountryRegion(String countryRegion) {
        this.countryRegion = countryRegion;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getConfirmed() {
        return confirmed;
    }

    public void setConfirmed(String confirmed) {
        this.confirmed = confirmed;
    }

    public String getRecovered() {
        return recovered;
    }

    public void setRecovered(String recovered) {
        this.recovered = recovered;
    }

    public String getDeaths() {
        return deaths;
    }

    public void setDeaths(String deaths) {
        this.deaths = deaths;
    }

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }
}
