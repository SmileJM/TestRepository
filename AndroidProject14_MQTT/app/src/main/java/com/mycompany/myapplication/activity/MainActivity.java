package com.mycompany.myapplication.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.mycompany.myapplication.R;
import com.mycompany.myapplication.service.MqttSubscribeService;

import org.eclipse.paho.android.service.MqttAndroidClient;
import org.eclipse.paho.client.mqttv3.IMqttActionListener;
import org.eclipse.paho.client.mqttv3.IMqttToken;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private EditText txtMessage;
    private Button btnPublish;

    private Intent intent;
    private String serverURI = "tcp://192.168.3.113:1883";
    private String topic = "/devices/device1/temperature";
    private int qos = 0;
    private MqttAndroidClient mqttAndroidClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtMessage = (EditText) findViewById(R.id.txtMessage);
        btnPublish = (Button) findViewById(R.id.btnPublish);

        mqttAndroidClient = new MqttAndroidClient(this, serverURI, MqttClient.generateClientId());
        try {
            // 연결 실행 후 자동으로 실행할 콜백 메소드가 있음
            mqttAndroidClient.connect().setActionCallback(new IMqttActionListener() {
                @Override
                public void onSuccess(IMqttToken asyncActionToken) {
                    Log.i(TAG, "MQTT 서버 연결 성공");
                    btnPublish.setEnabled(true);
                }

                @Override
                public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
                    Log.i(TAG, "MQTT 서버 연결 실패: " + exception.toString());
                    btnPublish.setEnabled(false);
                }
            });
        } catch (MqttException e) {
            Log.i(TAG, "MQTT 서버 연결 실패: " + e.toString());
        }

    }


    public void handleBtnStartService(View v) {
        intent = new Intent(this, MqttSubscribeService.class);
        startService(intent);
    }

    public void handleBtnStopService(View v) {
        if (intent != null) {
            stopService(intent);
            intent = null;
        }
    }

    public void handleBtnPublish(View v) {
        String message = txtMessage.getText().toString();
        try {
            byte[] encodedPayload = message.getBytes("UTF-8");
            MqttMessage mqttMessage = new MqttMessage(encodedPayload);
            mqttAndroidClient.publish(topic, mqttMessage)
                    .setActionCallback(new IMqttActionListener() {
                        @Override
                        public void onSuccess(IMqttToken asyncActionToken) {
                            Log.i(TAG, "MQTT 메시지 전송 성공");
                        }

                        @Override
                        public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
                            Log.i(TAG, "MQTT 메시지 전송 실패: " + exception.toString());
                        }
                    });
        } catch (Exception e) {
            Log.i(TAG, "MQTT 메시지 전송 실패: " + e.toString());
        }
    }
}
