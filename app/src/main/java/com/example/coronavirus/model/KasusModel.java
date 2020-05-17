package com.example.coronavirus.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class KasusModel {

    @SerializedName("data")
    @Expose
    private List<ListKasusModel> data;

    public List<ListKasusModel> getData() {
        return data;
    }

    public void setData(List<ListKasusModel> data) {
        this.data = data;
    }
}
