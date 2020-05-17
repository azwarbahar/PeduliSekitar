package com.example.coronavirus;

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
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.coronavirus.adapter.NegaraAdapter;
import com.example.coronavirus.api.ApiRequestData;
import com.example.coronavirus.api.RetroServerAPI;
import com.example.coronavirus.model.LocationModel;
import com.example.coronavirus.model.RecyclerViewNegaraModel;
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
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback {

    ImageView btn_back;
    ImageView btn_info;
    ProgressBar progesBar;
    String mulai = "tahan";
    private ArrayList<String> allmodelsnegara = new ArrayList<>();
    private List<LocationModel> locationModels = new ArrayList<>();

    GoogleMap map;
    ArrayList<RecyclerViewNegaraModel> viewNegaraModels;
    private List<String> list = new ArrayList<String>();
    private RecyclerView.Adapter adapter;
    private RecyclerView rv_negara;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        btn_back = findViewById(R.id.btn_back);
        btn_info = findViewById(R.id.btn_info);
        rv_negara = findViewById(R.id.rv_negara);
        progesBar = findViewById(R.id.progesBar);
        progesBar.setVisibility(View.VISIBLE);

        btn_info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(MapsActivity.this);
                alertDialog.setTitle("Catatan");
                alertDialog.setCancelable(true);
                alertDialog.setMessage("Titik lokasi yang ditunjukkan pada peta didasarkan pada centroid geografis Negara masing-masing, dan tidak mewakili alamat tertentu, bangunan, atau lokasi apapun!");
                alertDialog.setIcon(R.drawable.ic_warning_black_24dp);

                alertDialog.show();
            }
        });

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onPause();
                Intent intent = new Intent(MapsActivity.this, MenuUtamaActivity.class);
                startActivity(intent);
            }
        });

//        Loadnegara();
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    private void panggildata(){
            ArrayList<String> files = new ArrayList<String>(list.size());
            files.addAll(list);
            allmodelsnegara = files;

            ArrayList<RecyclerViewNegaraModel> models = new ArrayList<>();
            for (int a = 0; a < allmodelsnegara.size(); a++) {
                RecyclerViewNegaraModel negaraModel = new RecyclerViewNegaraModel();
                negaraModel.setTitle(allmodelsnegara.get(a));
                models.add(negaraModel);
            }

            viewNegaraModels = models;
//            recVieDataNegaraModels = recVieDataNegaraModel;


    }

    private void Loadnegara() {
        ApiRequestData requestData = RetroServerAPI.getClient().create(ApiRequestData.class);
        Call<List<LocationModel>> listCall = requestData.getLocationMarker();
        listCall.enqueue(new Callback<List<LocationModel>>() {
            @Override
            public void onResponse(Call<List<LocationModel>> call, Response<List<LocationModel>> response) {
                if (response.isSuccessful()){
                    progesBar.setVisibility(View.GONE);
                    locationModels = response.body();
                    Log.d("Respon", "Value : "+locationModels.get(1).getLatitude());
                    initMarker(locationModels);
                    rv_negara.setLayoutManager(new LinearLayoutManager(MapsActivity.this));
                    adapter = new NegaraAdapter(MapsActivity.this, locationModels);
                    rv_negara.setAdapter(adapter);

                }
            }

            @Override
            public void onFailure(Call<List<LocationModel>> call, Throwable t) {
                Log.d("RESPON", "Respon : "+t);
                progesBar.setVisibility(View.GONE);
            }
        });
    }
    /**
     * Method ini digunakan untuk menampilkan semua marker di maps dari data yang didapat dari database
     * @param list
     */
    private void initMarker(List<LocationModel> list){
        for (int i = 0; i <locationModels.size(); i++){
            Log.d("InitMarker", "Respon : " + locationModels.get(i).getLatitude());
            MarkerOptions markerOptions = new MarkerOptions();
            String latitud = locationModels.get(i).getLatitude();
            String longitud = locationModels.get(i).getLongitude();
            if (latitud == null){
                latitud = "0";
            }
            if (longitud == null){
                longitud = "0";
            }
            markerOptions.title(locationModels.get(i).getProvinceState()+" - " +locationModels.get(i).getCountryRegion())
                    .snippet("Confirmed : "+locationModels.get(i).getConfirmed()+
                            "\nRecovered : "+locationModels.get(i).getRecovered()+
                            "\nDeaths : "+locationModels.get(i).getDeaths()+
                            "\nActiv : "+locationModels.get(i).getActive())
                    .icon(bitmapDescriptor(getApplicationContext(),
                            R.drawable.icon_markers))
                    .position(new LatLng(Double.parseDouble(latitud),
                            Double.parseDouble(longitud)));
            map.addMarker(markerOptions);
        }
    }


    private BitmapDescriptor bitmapDescriptor(Context context, int vactorResid) {

        int height = 40;
        int width = 40;
        Drawable vectorDrawble = ContextCompat.getDrawable(context, vactorResid);
        vectorDrawble.setBounds(0, 0, height, width);
        Bitmap bitmap = Bitmap.createBitmap(height, width, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        vectorDrawble.draw(canvas);
        return BitmapDescriptorFactory.fromBitmap(bitmap);
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;
        map.clear();
//        try {
//            // Customise the styling of the base map using a JSON object defined
//            // in a raw resource file.
//            boolean success = googleMap.setMapStyle(
//                    MapStyleOptions.loadRawResourceStyle(
//                            this, R.raw.mapstyle));
//
//            if (!success) {
//                Log.e("MapsActivity", "Style parsing failed.");
//            }
//        } catch (Resources.NotFoundException e) {
//            Log.e("MapsActivity", "Can't find style. Error: ", e);
//        }

        Loadnegara();
        LatLng latLngzoom = new LatLng(-0.7893, 113.9213);
        map.animateCamera(CameraUpdateFactory.newLatLngZoom(latLngzoom, 0), 3000, null);

        map.setInfoWindowAdapter(new GoogleMap.InfoWindowAdapter() {

            @Override
            public View getInfoWindow(Marker arg0) {
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

                info.addView(title);
                info.addView(snippet);

                return info;
            }
        });

    }
}
