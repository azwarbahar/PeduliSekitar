package com.example.coronavirus;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
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

import com.example.coronavirus.adapter.ProvinsiAdapter;
import com.example.coronavirus.api.ApiRequestData;
import com.example.coronavirus.api.ProvinsiRetroServAPI;
import com.example.coronavirus.model.FeaturesProvModel;
import com.example.coronavirus.model.PropertisModel;
import com.example.coronavirus.model.ProvinsiDataModel;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.sothree.slidinguppanel.SlidingUpPanelLayout;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MapsIndoActivity extends AppCompatActivity implements OnMapReadyCallback {

    private static final String TAG = "ContentSlider";
    private SlidingUpPanelLayout mLayout;

    GoogleMap map;

    ImageView btn_info;
    ImageView btn_back;
    ProgressBar progesBar;

    private List<String> listprovinsi = new ArrayList<String>();
    private List<String> listconfirmed = new ArrayList<String>();
    private List<String> listrecovered = new ArrayList<String>();
    private List<String> listdeaths = new ArrayList<String>();
    private List<String> listupdate = new ArrayList<String>();
    //    private ArrayList<String> allmodelsnegara = new ArrayList<>();
//    private ArrayList<String> allmodelsnegara = new ArrayList<>();
//    private ArrayList<String> allmodelsnegara = new ArrayList<>();
//    private ArrayList<String> allmodelsnegara = new ArrayList<>();
//    private ArrayList<String> allmodelsnegara = new ArrayList<>();
    private ArrayList<ProvinsiDataModel> propertisData;
    private RecyclerView rv_negara;
    private RecyclerView.Adapter adapter;
    private List<FeaturesProvModel> featuresProvModels = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps_indo);

        if (mLayout != null) {
            if (mLayout.getPanelState() != SlidingUpPanelLayout.PanelState.HIDDEN) {
                mLayout.setPanelState(SlidingUpPanelLayout.PanelState.HIDDEN);

            } else {
                mLayout.setPanelState(SlidingUpPanelLayout.PanelState.COLLAPSED);

            }
        }

        mLayout = (SlidingUpPanelLayout) findViewById(R.id.sliding_layout);
        mLayout.addPanelSlideListener(new SlidingUpPanelLayout.PanelSlideListener() {
            @Override
            public void onPanelSlide(View panel, float slideOffset) {

            }

            @Override
            public void onPanelStateChanged(View panel, SlidingUpPanelLayout.PanelState previousState, SlidingUpPanelLayout.PanelState newState) {

            }
        });

        btn_back = findViewById(R.id.btn_back);
        btn_info = findViewById(R.id.btn_info);
        btn_info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(MapsIndoActivity.this);
                alertDialog.setTitle("Catatan");
                alertDialog.setCancelable(true);
                alertDialog.setMessage("Titik lokasi yang ditunjukkan pada peta didasarkan pada centroid geografis Provinsi masing-masing, dan tidak mewakili alamat tertentu, bangunan, atau lokasi apapun!");
                alertDialog.setIcon(R.drawable.ic_warning_black_24dp);

                alertDialog.show();
            }
        });

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onPause();
                Intent intent = new Intent(MapsIndoActivity.this, MenuUtamaActivity.class);
                startActivity(intent);
            }
        });

        progesBar = findViewById(R.id.progesBar);
        rv_negara = findViewById(R.id.rv_negara);
        progesBar.setVisibility(View.VISIBLE);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        LoadDataProv();

    }

    private void initFature(List<FeaturesProvModel> models) {

//        Log.d("Location ", "Latitude : "+featuresProvModels.get(0).getGeometry().getCoordinates().get(1));
        for (int a = 0; a < featuresProvModels.size(); a++) {

            String kode = featuresProvModels.get(a).getProperties().getKode_Provinsi();
            String prov = featuresProvModels.get(a).getProperties().getProvinsi();
            String posit = featuresProvModels.get(a).getProperties().getKasus_Terkonfirmasi_Akumulatif();
            String sembuh = featuresProvModels.get(a).getProperties().getKasus_Sembuh_Akumulatif();
            String mati = featuresProvModels.get(a).getProperties().getKasus_Meninggal_Akumulatif();
            listprovinsi.add(prov);
            listconfirmed.add(posit);
            listrecovered.add(sembuh);
            listdeaths.add(mati);
//            listupdate.add(featuresProvModels.get(a).getProperties().getPembaruan());

            MarkerOptions markerOptions = new MarkerOptions();
            markerOptions.title(prov)
                    .snippet("Kode Provinsi : "+kode+"\nPositif : "+posit+"\nSembuh : "+sembuh+"\nMeninggal : "+mati)
                    .icon(bitmapDescriptor(getApplicationContext(),
                            R.drawable.icon_markers))
                    .position(new LatLng(Double.parseDouble(featuresProvModels.get(a).getGeometry().getCoordinates().get(1)),
                            Double.parseDouble(featuresProvModels.get(a).getGeometry().getCoordinates().get(0))));
            map.addMarker(markerOptions);
//            Log.d("URUTAN", "Nomor : " + a);
//            Log.d("INIT FEATUR" , "RESPON : "+featuresProvModels.get(a).getType());
//            Log.d("Propertis", "Provinsi : "+ featuresProvModels.get(a).getProperties().getProvinsi());


        }

        ArrayList<PropertisModel> arrayList = new ArrayList<>();
        for (int x = 0; x < listprovinsi.size(); x++) {
            PropertisModel propertisModel = new PropertisModel();
            propertisModel.setProvinsi(listprovinsi.get(x));
            propertisModel.setKasus_Terkonfirmasi_Akumulatif(listconfirmed.get(x));
            propertisModel.setKasus_Sembuh_Akumulatif(listrecovered.get(x));
            propertisModel.setKasus_Meninggal_Akumulatif(listdeaths.get(x));
//            propertisModel.setPembaruan(listupdate.get(x));
            arrayList.add(propertisModel);
        }
        rv_negara.setLayoutManager(new LinearLayoutManager(MapsIndoActivity.this));
        adapter = new ProvinsiAdapter(MapsIndoActivity.this, arrayList);
        rv_negara.setAdapter(adapter);

    }


    private void LoadDataProv() {

        ApiRequestData apiRequestData = ProvinsiRetroServAPI.getClient().create(ApiRequestData.class);
        Call<ProvinsiDataModel> dataModelCall = apiRequestData.getDataProvinsi();
        dataModelCall.enqueue(new Callback<ProvinsiDataModel>() {
            @Override
            public void onResponse(Call<ProvinsiDataModel> call, Response<ProvinsiDataModel> response) {
                if (response.isSuccessful()) {
                    progesBar.setVisibility(View.GONE);
//                    Toast.makeText(MapsIndoActivity.this, "SUKSES", Toast.LENGTH_SHORT).show();
//                    Log.d("Respon", "TYPE : "+response.body().getType());
                    featuresProvModels = response.body().getFeatures();
                    initFature(featuresProvModels);
//

                } else {
                    progesBar.setVisibility(View.GONE);
//                    Toast.makeText(MapsIndoActivity.this, "Gagal", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ProvinsiDataModel> call, Throwable t) {
                progesBar.setVisibility(View.GONE);
//                Toast.makeText(MapsIndoActivity.this, "ERROR", Toast.LENGTH_SHORT).show();
//                Log.d("ERROR" , "PESAN : "+t.getMessage());
            }
        });
    }

    private BitmapDescriptor bitmapDescriptor(Context context, int vactorResid) {

        int height = 50;
        int width = 50;
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
        LatLng latLngzoom = new LatLng(-0.7893, 113.9213);
        map.animateCamera(CameraUpdateFactory.newLatLngZoom(latLngzoom, 4), 3000, null);

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
