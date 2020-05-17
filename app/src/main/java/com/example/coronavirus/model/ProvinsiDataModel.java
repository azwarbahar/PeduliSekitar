package com.example.coronavirus.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ProvinsiDataModel {
    @SerializedName("type")
    @Expose
    private String type;

    @SerializedName("features")
    @Expose
    private List<FeaturesProvModel> features;

    public ProvinsiDataModel(String type, List<FeaturesProvModel> features) {
        this.type = type;
        this.features = features;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<FeaturesProvModel> getFeatures() {
        return features;
    }

    public void setFeatures(List<FeaturesProvModel> features) {
        this.features = features;
    }
}
