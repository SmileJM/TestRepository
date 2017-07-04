package com.mycompany.myapplication.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.mycompany.myapplication.R;
import com.mycompany.myapplication.dto.Review;
import com.mycompany.myapplication.util.RealPathUtil;

public class HomeActivity extends AppCompatActivity {
    private static final String TAG = "HomeActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        permission();
    }

    public void permission() {
        int permissionCall = ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE);
        int permissionRES = ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE);
        int permissionWES = ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);

        if (permissionCall != PackageManager.PERMISSION_GRANTED ||
                permissionRES != PackageManager.PERMISSION_GRANTED ||
                permissionWES != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{
                            Manifest.permission.CALL_PHONE,
                            Manifest.permission.READ_EXTERNAL_STORAGE,
                            Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    1);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == 1) {
            if (grantResults.length > 0 &&
                    grantResults[0] == PackageManager.PERMISSION_GRANTED &&
                    grantResults[1] == PackageManager.PERMISSION_GRANTED &&
                    grantResults[2] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "권한을 얻음", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "권한을 얻지 못함", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void handleBtnUIActivity(View v) {
        Intent intent = new Intent(this, UIActivity.class);
        startActivity(intent);
    }

    public void handleBtnWebBrowser(View v) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.naver.com"));
        startActivity(intent);
    }

    public void handleBtnDialActivity(View v) {
        int permissionCall = ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE);
        if (permissionCall == PackageManager.PERMISSION_GRANTED) {
            Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:000-1234-5678"));
            startActivity(intent);
        }
    }

    public void handleBtnMapActivity(View v) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("geo:37.515889, 127.07275?z=16"));
        startActivity(intent);
    }

    public void handleBtnDataSend(View v) {
        Intent intent = new Intent(this, DataReceiveActivity.class);
        intent.putExtra("key1", 10);
        intent.putExtra("key2", "Android");
        Review review = new Review();
        review.setTitle("오늘은 화요일");
        // review 객체를 전송하려면 Review 클래스는 Serializable 구현하고 있어야함
        intent.putExtra("key3", review);
        startActivity(intent);
    }

    public void handleBtnReturnValue(View v) {
        Intent intent = new Intent(this, ReturnValueActivity.class);
        intent.putExtra("x", 10);
        intent.putExtra("y", 20);
        // 결과를 받기 위해 사용하는 메소드 startActivity()는 이동만
        startActivityForResult(intent, 1);
    }

    public void handleBtnFileSelect(View v) {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        startActivityForResult(intent.createChooser(intent, "이미지선택"), 2);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                int result = data.getIntExtra("result", 0);
                Log.i(TAG, "전달된 결과값: " + result);
            } else {
                Log.i(TAG, "전달된 결과값이 없음");
            }
        } else if (requestCode == 2) {
            if (resultCode == RESULT_OK) {
                // 이미지는 getData() 로 얻음
                Uri uri = data.getData();
                String path = RealPathUtil.getRealPath(this, uri);
                Log.i(TAG, path);
            }
        }
    }
}
