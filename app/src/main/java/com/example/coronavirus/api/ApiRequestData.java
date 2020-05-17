package com.example.coronavirus.api;

import com.example.coronavirus.model.IndonesiaModel;
import com.example.coronavirus.model.JumlahLaporanModel;
import com.example.coronavirus.model.KasusModel;
import com.example.coronavirus.model.LaporanSendModel;
import com.example.coronavirus.model.LocationModel;
import com.example.coronavirus.model.NegaraResponseModel;
import com.example.coronavirus.model.ProvinsiDataModel;
import com.example.coronavirus.model.ResponLaporanModel;
import com.example.coronavirus.model.ResponModel;
import com.example.coronavirus.model.SinglePetugasModel;
import com.google.gson.JsonElement;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiRequestData {

    @GET("/api")
    Call<ResponModel> getResponData();

    @GET("/api/countries/ID")
    Call<NegaraResponseModel> getDataNegara();

    @GET("/api/confirmed")
    Call<List<LocationModel>> getLocationMarker();

    @GET("/api/countries/")
    Call<JsonElement> getAllNegara();


    @GET("0c0f4558f1e548b68a1c82112744bad3_0.geojson")
    Call<ProvinsiDataModel> getDataProvinsi();


    @GET("/api")
    Call<IndonesiaModel> getDataIndo();

    @GET("/api/kasus")
    Call<KasusModel> getDatakasus();

    @GET("get_count_data.php")
    Call<JumlahLaporanModel> getjumlahlapor();

    @GET("get_laporan.php")
    Call<ResponLaporanModel> getDataLaporan();

    @GET("get_single_laporan.php")
    Call<ResponLaporanModel> getDataLaporanSingle(@Query("id_laporan") String id);


    @FormUrlEncoded
    @POST("update_laporan.php")
    Call<ResponLaporanModel> updateDataLaporan(@Field("id_laporan") String id_laporan,
                                               @Field("status") String status);

    @GET("read_petugas_single.php")
    Call<List<SinglePetugasModel>> getSinglePetugas(@Query("username") String username, @Query("password") String password);


    @GET("get_all_petugas.php")
    Call<ResponLaporanModel> getPetugasAll();

//    @FormUrlEncoded
    @GET("add_petugas.php")
    Call<ResponLaporanModel> addPetugas(@Query("nama_petugas") String nama_petugas,
                                        @Query("profesi") String profesi,
                                        @Query("username") String username,
                                        @Query("password") String password);

    @FormUrlEncoded
    @POST("add_laporan.php")
    Call<LaporanSendModel> uploadLaporan(@Field("nama_lengkap") String nama_lengkap,
                                         @Field("telpon") String telpon,
                                         @Field("deskripsi") String deskripsi,
                                         @Field("tanggal") String tanggal,
                                         @Field("jam") String jam,
                                         @Field("alamat") String alamat,
                                         @Field("latitude") String latitude,
                                         @Field("longitude") String longitude,
                                         @Field("status") String status,
                                         @Field("image") String image);

}
