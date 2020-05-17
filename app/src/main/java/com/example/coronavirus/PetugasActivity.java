package com.example.coronavirus;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.coronavirus.api.ApiRequestData;
import com.example.coronavirus.api.LaporanRetrosAPI;
import com.example.coronavirus.model.LaporanModel;
import com.example.coronavirus.model.ResponLaporanModel;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PetugasActivity extends AppCompatActivity implements GoogleMap.OnInfoWindowClickListener, OnMapReadyCallback {

    private Toolbar toolbar;
    GoogleMap map;
    private ProgressBar progesBar;
    private List<LaporanModel> laporanModels = new ArrayList<>();


    HashMap<String, String> markerMap = new HashMap<String, String>();

    private List<String> listId         = new ArrayList<String>();
    private List<String> listNama       = new ArrayList<String>();
    private List<String> listTelpon     = new ArrayList<String>();
    private List<String> listDeskripsi  = new ArrayList<String>();
    private List<String> listTanggal    = new ArrayList<String>();
    private List<String> listJam        = new ArrayList<String>();
    private List<String> listAlamat     = new ArrayList<String>();
    private List<String> listLatitude   = new ArrayList<String>();
    private List<String> listLongitude  = new ArrayList<String>();
    private List<String> listPicture    = new ArrayList<String>();
    private List<String> listStatus     = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_petugas);

        progesBar = findViewById(R.id.progesBar);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        loadData();
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    private void initLaporan(List<LaporanModel> models){
        for (int a = 0; a<laporanModels.size(); a++){

            String id = laporanModels.get(a).getId();
            String nama = laporanModels.get(a).getName();
            String telpon = laporanModels.get(a).getTelpon();
            String deskripsi = laporanModels.get(a).getDeskripsi();
            String tanggal = laporanModels.get(a).getTanggal();
            String jam = laporanModels.get(a).getJam();
            String alamat = laporanModels.get(a).getAlamat();
            String latitud = laporanModels.get(a).getLatitude();
            String longitud = laporanModels.get(a).getLongitude();
            String picture = laporanModels.get(a).getPicture();
            String status = laporanModels.get(a).getStatus();

            listId.add(id);
            listNama.add(nama);
            listTelpon.add(telpon);
            listDeskripsi.add(deskripsi);
            listTanggal.add(tanggal);
            listJam.add(jam);
            listAlamat.add(alamat);
            listLatitude.add(latitud);
            listLongitude.add(longitud);
            listPicture.add(picture);
            listStatus.add(status);

            Log.d("LatLing", "Latitude : "+latitud+ " , "+longitud);

            Double latit = Double.parseDouble(latitud);
            Double longit = Double.parseDouble(longitud);

            if (status.equals("0")){

                Marker marker = map.addMarker(new MarkerOptions().title("Keramaian")
                        .snippet("Nama : "+nama+"\nTanggal : "+tanggal+"\nJam : "+jam+"\nStatus : Belum diproses")
                        .icon(bitmapDescriptor(getApplicationContext(),
                                R.drawable.ic_place_merah))
                        .position(new LatLng(latit,longit)));
                String idmark= marker.getId();
                markerMap.put(idmark, id);
            } else if (status.equals("1")){

                Marker marker = map.addMarker(new MarkerOptions().title("Keramaian")
                        .snippet("Nama : "+nama+"\nTanggal : "+tanggal+"\nJam : "+jam+"\nStatus : Sudah diproses")
                        .icon(bitmapDescriptor(getApplicationContext(),
                                R.drawable.ic_place_hijau))
                        .position(new LatLng(latit,longit)));
                String idmark= marker.getId();
                markerMap.put(idmark, id);
            }

        }
    }

    private BitmapDescriptor bitmapDescriptor(Context context, int vactorResid) {

        int height = 80;
        int width = 80;
        Drawable vectorDrawble = ContextCompat.getDrawable(context, vactorResid);
        vectorDrawble.setBounds(0, 0, height, width);
        Bitmap bitmap = Bitmap.createBitmap(height, width, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        vectorDrawble.draw(canvas);
        return BitmapDescriptorFactory.fromBitmap(bitmap);
    }


    private void loadData(){
        ApiRequestData apiRequestData = LaporanRetrosAPI.getClient().create(ApiRequestData.class);
        Call<ResponLaporanModel> dataLaporan = apiRequestData.getDataLaporan();
        dataLaporan.enqueue(new Callback<ResponLaporanModel>() {
            @Override
            public void onResponse(Call<ResponLaporanModel> call, Response<ResponLaporanModel> response) {
                if (response.isSuccessful()){
                    progesBar.setVisibility(View.GONE);
                    if (response.body().getKode().equals("1")){
                        laporanModels = response.body().getResultDataLaporan();
                        Log.d("MAP","Respon : "+response.body().getKode());
                        initLaporan(laporanModels);

                    }


                }
            }

            @Override
            public void onFailure(Call<ResponLaporanModel> call, Throwable t) {

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_petugas, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.item_refresh:
                finish();
                overridePendingTransition(0, 0);
                startActivity(getIntent());
                overridePendingTransition(0, 0);
                Toast.makeText(this, "Refresh Halaman", Toast.LENGTH_SHORT).show();
                return true;

            case R.id.item_profil:
                getBundle();
//                Toast.makeText(this, "Item Profile", Toast.LENGTH_SHORT).show();
                return true;

            case R.id.item_petugas:
                Intent intent1 = new Intent(PetugasActivity.this, DataPetugasActivity.class);
                startActivity(intent1);
                return true;

            case R.id.item_logout:
                Intent intent = new Intent(PetugasActivity.this, LoginActivity.class);
                startActivity(intent);
                return true;

            default:
                return super.onContextItemSelected(item);
        }
    }

    private void dataToProfile(String id, String nama, String profesi, String user, String pass){

        Bundle bundle = new Bundle();
        bundle.putString("ID", id);
        bundle.putString("NAMA", nama);
        bundle.putString("PROFESI", profesi);
        bundle.putString("USER", user);
        bundle.putString("PASS", pass);

        Intent intent = new Intent(PetugasActivity.this, ProfilePrtugasActivity.class);
        intent.putExtras(bundle);
        startActivity(intent);

    }

    private void getBundle() {

        Bundle bundle = getIntent().getExtras();
        assert bundle != null;

        String id = bundle.getString("ID");
        String nama = bundle.getString("NAMA");
        String profesi = bundle.getString("PROFESI");
        String username = bundle.getString("USER");
        String password = bundle.getString("PASS");

        dataToProfile(id,nama,profesi,username,password);

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;
        LatLng latLngzoom = new LatLng(-5.157265, 119.436625);
        map.animateCamera(CameraUpdateFactory.newLatLngZoom(latLngzoom, 12));
        map.setOnInfoWindowClickListener(this);
        map.setInfoWindowAdapter(new GoogleMap.InfoWindowAdapter() {
            @Override
            public View getInfoWindow(Marker marker) {
                return null;
            }

            @Override
            public View getInfoContents(Marker marker) {
                Context context = getApplicationContext(); //or getActivity(), YourActivity.this, etc.

                LinearLayout info = new LinearLayout(context);
                info.setOrientation(LinearLayout.VERTICAL);

                TextView title = new TextView(context);
                title.setTextColor(Color.BLACK);
                title.setGravity(Gravity.CENTER);
                title.setTypeface(null, Typeface.BOLD);
                title.setText(marker.getTitle());

                TextView snippet = new TextView(context);
                snippet.setTextColor(Color.GRAY);
                snippet.setText(marker.getSnippet());

                Button button = new Button(context);
                button.setText("Detail");
                button.setTextColor(getResources().getColor(R.color.putih));
                button.setBackgroundResource(R.drawable.bg_btn_lapor);
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(PetugasActivity.this, "Detail", Toast.LENGTH_SHORT).show();
                    }
                });

                info.addView(title);
                info.addView(snippet);
                info.addView(button);

                return info;
            }
        });
    }

    @Override
    public void onInfoWindowClick(Marker marker) {
        String id = markerMap.get(marker.getId());
        Bundle bundle = new Bundle();
        bundle.putString("sendID",id);
        Intent intent = new Intent(PetugasActivity.this, DetailLaporanActivity.class);
        intent.putExtras(bundle);
        startActivity(intent);
    }
}
