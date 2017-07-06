package com.mycompany.myapplication.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.mycompany.myapplication.R;

public class HomeActivity extends AppCompatActivity {
    private static final String TAG = "HomeActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }

    public void handleBtnSendBroadcast(View v) {
        // 방송 발생시키기
        Intent intent = new Intent("com.mycompany.myapplication.receiver.SelfBroadcastReceiver");
        intent.putExtra("title","MQTT 알림");
        intent.putExtra("content","온도가 비정상적으로 높습니다.");
        sendBroadcast(intent);
    }
}
