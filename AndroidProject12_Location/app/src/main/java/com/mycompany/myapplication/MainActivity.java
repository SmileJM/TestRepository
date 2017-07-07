package com.mycompany.myapplication;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import org.apache.commons.lang.StringEscapeUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private LocationManager locationManager;
    private SupportMapFragment mapFragment;
    private GoogleMap googleMap;
    private ArrayList<LatLng> listLocation;
    private Polyline polyline;
    private LatLng currLatLng;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        checkPermission();
    }

    public void checkPermission() {
        int permissionGps = ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION);
        int permissionNetwork = ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION);

        if (permissionGps != PackageManager.PERMISSION_GRANTED || permissionNetwork != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(
                    this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION},
                    1
            );
        }
    }

    public void handleBtnCurrentLocation(View v) {
        checkPermission();
        // 현재 위치 정보 얻기
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        // 메소드 안에 권한 체크 코드가 없으면 컴파일 에러가 발생되기 때문에 위에서 checkPermission(); 를 호출함
        locationManager.requestLocationUpdates(
                // 누가 제공하는지(기지국)
                LocationManager.NETWORK_PROVIDER,
                // 갱신된 위치 정보를 얼마의 시간마다 받을 지
                10000,
                0, // 거리(m) 10m가는데 10초가 안걸려도 다시 받음, 0은 거리와 상관없이 시간으로만 하겠다는 것
                locationListener
        );
    }

    private LocationListener locationListener = new LocationListener() {
        @Override
        public void onLocationChanged(Location location) {
            double latitude = location.getLatitude();
            double longitude = location.getLongitude();
            Log.i(TAG, "위도: " + latitude);
            Log.i(TAG, "경도: " + longitude);
            //
            locationManager.removeUpdates(locationListener);
            showMap(latitude, longitude);
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {}

        @Override
        public void onProviderEnabled(String provider) {}

        @Override
        public void onProviderDisabled(String provider) {}
    };

    public void handleBtnFromAddressToLocation(View v) {
        String url = "https://apis.daum.net/local/geo/addr2coord";
        HttpUrl httpUrl = HttpUrl.parse(url).newBuilder()
                .addQueryParameter("apikey", "ed39f1a2d5d0ef6900f484030523afe2")
                .addQueryParameter("q", "제주특별자치도 제주시 한림읍 한림로 300")
                .addQueryParameter("output", "json")
                .build();
        Request request = new Request.Builder()
                .url(httpUrl)
                .build();
        OkHttpClient client = new OkHttpClient();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                // \u00aa 등을 한글로 변환하기 위해 StringEscapeUtils 사용
                // compile 'commons-lang:commons-lang:2.6' 추가해야 함
                String json = StringEscapeUtils.unescapeJava(response.body().string());
                Log.i(TAG, json);
                try {
                    JSONObject jsonObject = new JSONObject(json);
                    jsonObject = jsonObject.getJSONObject("channel");
                    JSONArray jsonArray = jsonObject.getJSONArray("item");
                    jsonObject = jsonArray.getJSONObject(0);
                    double latitude = jsonObject.getDouble("lat");
                    double longitude = jsonObject.getDouble("lng");
                    Log.i(TAG, "위도: " + latitude);
                    Log.i(TAG, "경도: " + longitude);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
            @Override
            public void onFailure(Call call, IOException e) {
                Log.i(TAG, e.toString());
            }
        });
    }
    private void showMap(double latitude, double longitude){

        currLatLng = new LatLng(latitude, longitude);
        // animateCamera() - 현재위치에서 애니매이션 되면서 위치를 보여줌
        // moveCamera() - 어떻게 갔는지 보여주지 않고 바로보여줌
        // 지도를 드로잉하기 위한 코드
        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(currLatLng, 16));
        googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        // 마커 보이기
        checkPermission(); // 개인정보로 인해 권한 필요
        googleMap.setMyLocationEnabled(true);

        // 경로를 드로잉하기 위한 코드
        listLocation.clear();
        listLocation.add(currLatLng);
        googleMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                listLocation.add(latLng);

                drawingPath();
            }
        });

    }

    private void drawingPath() {
        if(polyline != null) {
            polyline.remove();
        }
        LatLng[] paths = new LatLng[listLocation.size()];
        polyline = googleMap.addPolyline(
                new PolylineOptions()
                        .add(listLocation.toArray(paths))
                        .width(10)
                        .color(Color.RED)
        );
    }

    public void handleBtnMap(View v) {
        mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.mapFragment);
        // 지도를 그리기 위한 객체를 초기화하기 위한 시간이 필요함(네트워크 연결..)
        // 비동기 방식으로 지도를 얻음
        // 동기방식으로 얻으면 메인 스레드가 멈추어버림
        // 준비가 다 되면 콜백 메소드 실행
        // 콜백 메소드에 하고싶은 내용을 작성
        // 콜백: 비동기 작업이 끝난 후 통보를 해주는 방식
        mapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                MainActivity.this.googleMap = googleMap;
                handleBtnCurrentLocation(null);
            }
        });
    }
    public void handleBtnCurrLocationReturn(View v) {
        listLocation.add(currLatLng);
        drawingPath();
    }
    public void handleBtnPathRemove(View v) {
        listLocation.clear();
        polyline.remove();
    }
}
