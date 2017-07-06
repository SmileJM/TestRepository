package com.mycompany.myapplication.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class BackgroundService extends Service {
    private static final String TAG = "BackgroundService";
    private Thread thread;

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    // 최초로 startService() 호출하였을 때 실행
    @Override
    public void onCreate() {
        Log.i(TAG, "onCreate()");
        super.onCreate();
    }

    // startService() 를 호출할 때 마다 실행
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i(TAG, "onStartCommand()");
        if (thread == null || !thread.isAlive()) {
            thread = new Thread() {
                private int count;

                @Override
                public void run() {
                    while (true) {
                        Log.i(TAG, "count: " + count++);
                        try { Thread.sleep(1000); } catch (InterruptedException e) {
                            break;
                        }
                    }
                    stopSelf();
                }
            };
            thread.start();
        }

        // START_STICKY: 기본 리턴값, 서비스를 강제로 종료하지 않았는데 서비스가 종료가 된다면 드로이드가 다시 살림
        // START_STICKY: 서비스를 강제로 종료시키면 살아나지 않음
        return super.onStartCommand(intent, flags, startId);
    }

    // 작업 관리자에서 앱을 종료시켰을 때 실행
    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestroy()");
        if(thread != null || thread.isAlive()) {
            thread.interrupt();
            thread = null;
        }
    }
}
