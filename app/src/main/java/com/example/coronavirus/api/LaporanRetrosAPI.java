package com.example.coronavirus.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LaporanRetrosAPI {

    private final static String LINK_API ="http://192.168.43.188/pedulisekitar/";

    private static Retrofit retrofit;

    public static Retrofit getClient(){
        if (retrofit == null){
//            Gson gson = new GsonBuilder()
//                    .setLenient()
//                    .create();
            retrofit = new Retrofit.Builder()
                    .baseUrl(LINK_API)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();


        }
        return retrofit;
    }

}
