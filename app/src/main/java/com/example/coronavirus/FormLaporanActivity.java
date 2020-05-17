package com.example.coronavirus;

import android.Manifest;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.location.Address;
import android.location.Geocoder;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.coronavirus.api.ApiRequestData;
import com.example.coronavirus.api.LaporanRetrosAPI;
import com.example.coronavirus.model.LaporanSendModel;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FormLaporanActivity extends AppCompatActivity implements OnMapReadyCallback {

    GoogleMap map;

    String tanggal, jam;
    private TextView tv_alamat;
    String latitud, longitud;

    private Button btn_batal, btn_ok;

    private EditText input_nama;
    private EditText input_telpon;
    private EditText input_deskripsi;

    private Toolbar toolbar;
    private static final int CAMERA_REQUEST = 1888;
    private ImageView imageView;
    private static final int MY_CAMERA_PERMISSION_CODE = 100;
    private Handler handler = new Handler();
    private String alamat_latlig;
    private ImageView img_foto;
    private Bitmap bitmap;
    private CheckBox checkbox1;
    private View dialogView;

    private TextView ini;
    private ProgressBar progesBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_laporan);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.maps_titik);
        mapFragment.getMapAsync(this);

        handler.postDelayed(runnable, 1000);

        ini = findViewById(R.id.ini);
        ini.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialogIni();
            }
        });

        input_nama = findViewById(R.id.input_nama);
        tv_alamat = findViewById(R.id.tv_alamat);
        progesBar = findViewById(R.id.progesBar);
        progesBar.setVisibility(View.GONE);
        checkbox1 = findViewById(R.id.checkbox1);
        btn_ok = findViewById(R.id.btn_ok);
        input_telpon = findViewById(R.id.input_telpon);
        input_deskripsi = findViewById(R.id.input_deskripsi);
        imageView = findViewById(R.id.img_foto);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        btn_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (imageView.getDrawable() == null) {
                    Toast.makeText(FormLaporanActivity.this, "Lengkapi Bukti Foto!", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(input_nama.getText())) {
                    input_nama.setError("Lengkapi");
                } else if (TextUtils.isEmpty(input_telpon.getText())) {
                    input_telpon.setError("Lengkapi");
                } else if (TextUtils.isEmpty(input_deskripsi.getText())) {
                    Toast.makeText(FormLaporanActivity.this, "Masukkan penjelasan kejadian", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(tv_alamat.getText())) {
                    Toast.makeText(FormLaporanActivity.this, "Tentukan Lokasi Kejadian", Toast.LENGTH_SHORT).show();
                } else if (!checkbox1.isChecked()) {
                    Toast.makeText(FormLaporanActivity.this, "Pastikan diatas sudah dibaca ", Toast.LENGTH_SHORT).show();
                } else {
                    sendLaporan();
                }


            }
        });

    }

    private void showDialogIni() {
        android.app.AlertDialog.Builder dialog = new android.app.AlertDialog.Builder(FormLaporanActivity.this);
        LayoutInflater inflater = getLayoutInflater();
        dialogView = inflater.inflate(R.layout.dialog_ini, null);
        dialog.setView(dialogView);
        dialog.setIcon(R.drawable.ic_info_blue);
        dialog.setCancelable(true);
        dialog.setTitle("Informasi Data anda :");

        dialog.show();
    }

    private Runnable runnable = new Runnable() {
        @Override
        public void run() {

            DateFormat timeFormat = new SimpleDateFormat("HH:mm");
            Date datejam = new Date();
            jam = timeFormat.format(datejam);

            DateFormat dateFormat = new SimpleDateFormat("EEEE, dd MMMM yyyy");
            Date date = new Date();
            tanggal = dateFormat.format(date);

//            Toast.makeText(FormLaporanActivity.this, tanggal+"-"+jam, Toast.LENGTH_SHORT).show();
            handler.postDelayed(this, 5000);
        }
    };


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == MY_CAMERA_PERMISSION_CODE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "camera permission granted", Toast.LENGTH_LONG).show();
                Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent, CAMERA_REQUEST);
            } else {
                Toast.makeText(this, "camera permission denied", Toast.LENGTH_LONG).show();
            }
        }
    }

    private void sendLaporan() {
        progesBar.setVisibility(View.VISIBLE);
        String nama_send = input_nama.getText().toString();
        String telpon_send = input_telpon.getText().toString();
        String deskripsi_send = input_deskripsi.getText().toString();
        String tanggal_send = tanggal;
        String jam_send = jam;
        String alamat_send = alamat_latlig;
        String latitude_send = latitud;
        String longitude_send = longitud;
        String status_send = "0";
        String image_send = imgToString();

        ApiRequestData apiRequestData = LaporanRetrosAPI.getClient().create(ApiRequestData.class);
        Call<LaporanSendModel> sendLapor = apiRequestData.uploadLaporan(nama_send, telpon_send, deskripsi_send,
                tanggal_send, jam_send, alamat_send, latitude_send, longitude_send, status_send, image_send);
        sendLapor.enqueue(new Callback<LaporanSendModel>() {
            @Override
            public void onResponse(Call<LaporanSendModel> call, Response<LaporanSendModel> response) {
                if (response.isSuccessful()) {
                    progesBar.setVisibility(View.GONE);
                    LaporanSendModel laporanSendModel = response.body();
                    String value = response.body().getValue();
                    String message = response.body().getMassage();
                    Log.d("LAPORAN ", "value : " + value + " / msg : " + message);

                    showDialogSucces();

                } else {
                    progesBar.setVisibility(View.GONE);
                    Toast.makeText(FormLaporanActivity.this, "GAGAL", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<LaporanSendModel> call, Throwable t) {

                progesBar.setVisibility(View.GONE);
                Log.d("LAPORAN ERROR", "Respon : " + t);
                showDialogSucces();

            }
        });
    }

    private void showDialogSucces() {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(FormLaporanActivity.this);
        alertDialog.setTitle("Berhasil");
        alertDialog.setCancelable(false);
        alertDialog.setMessage("Laporan Berhasil Terkirim!");
        alertDialog.setIcon(R.drawable.ic_check_orange);

        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(FormLaporanActivity.this, MenuUtamaActivity.class);
                startActivity(intent);
            }
        });

        alertDialog.show();
    }

    private String imgToString() {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
        byte[] imgByte = byteArrayOutputStream.toByteArray();
        return Base64.encodeToString(imgByte, Base64.DEFAULT);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CAMERA_REQUEST && resultCode == Activity.RESULT_OK) {
            bitmap = (Bitmap) data.getExtras().get("data");
            imageView.setImageBitmap(bitmap);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_form_laporan, menu);
        return true;
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item_foto:
                if (checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                    requestPermissions(new String[]{Manifest.permission.CAMERA}, MY_CAMERA_PERMISSION_CODE);
                } else {
                    Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(cameraIntent, CAMERA_REQUEST);
                }
                return true;

//            case R.id.item_kirim:
//                Toast.makeText(this, "Kirim", Toast.LENGTH_SHORT).show();
//                return true;

            case R.id.home:
                finish();

            default:
                return super.onContextItemSelected(item);
        }
    }

    private void getAddress(double latitud, double longitud) {

        Geocoder geocoder;
        List<Address> addressList;
        geocoder = new Geocoder(this, Locale.getDefault());

        try {
            addressList = geocoder.getFromLocation(latitud, longitud, 1);
            String address = addressList.get(0).getAddressLine(0);
            alamat_latlig = address;
            tv_alamat.setText(address);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;
        LatLng latLngzoom = new LatLng(-5.157265, 119.436625);
        map.animateCamera(CameraUpdateFactory.newLatLngZoom(latLngzoom, 12));
        map.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                MarkerOptions markerOptions = new MarkerOptions();
                markerOptions.position(latLng);
                markerOptions.title("Titik Lokasi Kejadian");
                getAddress(latLng.latitude, latLng.longitude);
                latitud = String.valueOf(latLng.latitude);
                longitud = String.valueOf(latLng.longitude);
                map.clear();
                map.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15));
                map.addMarker(markerOptions);
            }
        });
    }


}
