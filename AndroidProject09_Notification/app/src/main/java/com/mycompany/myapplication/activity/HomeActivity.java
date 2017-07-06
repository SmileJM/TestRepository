package com.mycompany.myapplication.activity;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.os.Bundle;
import android.support.v4.app.TaskStackBuilder;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.NotificationCompat;
import android.view.View;

import com.mycompany.myapplication.R;

public class HomeActivity extends AppCompatActivity {
    private static final String TAG = "HomeActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }

    public void handleBtnNotification(View v) {
        Intent intent = new Intent(this, UIActivity.class);
        // 뒤로 버튼을 누르면 바로 이전 화면이 나옴
        // PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        // 뒤로 버튼을 누르면 UIActivity의 이전 화면(HomeActivity)이 나옴
        PendingIntent pendingIntent = TaskStackBuilder.create(this)
                // manifest 파일에서 이전 화면의 정보를 얻어 스택에 넣음
                // UIActivity의 이전은?(부모는?)
                .addParentStack(UIActivity.class)
                .addNextIntent(intent) // 부모에서 그 다음 화면은 어디? UIActivity 이지만 intent 를 넣게 되어 있음
                .getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);


        Notification notification = new NotificationCompat.Builder(this)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("MQTT 알림")
                .setContentText("온도가 비정상적으로 높습니다.")
                .setContentIntent(pendingIntent)
                .setAutoCancel(true)
                .setVibrate(new long[] {0, 500, 1000, 500})
                .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))
                .build();

        NotificationManager nm = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        // 똑같은 통지라면 1번만 나오게 하기
        nm.notify(1, notification);
        nm.notify(1, notification);
    }
}
