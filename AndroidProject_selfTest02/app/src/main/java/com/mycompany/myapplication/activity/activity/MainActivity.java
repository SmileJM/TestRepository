package com.mycompany.myapplication.activity.activity;

import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.mycompany.myapplication.R;

public class MainActivity extends AppCompatActivity implements SensorEventListener {
    private static final String TAG = "MainActivity";
    private SensorManager manager;
    private Sensor sensor;
    private TextView txtSensorAccuracy;
    private TextView txtSensorName;
    private TextView txtAccelX;
    private TextView txtAccelY;
    private TextView txtAccelZ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensor_data);

        txtSensorName = (TextView) findViewById(R.id.txtSensorName);
        txtAccelX = (TextView) findViewById(R.id.txtAccelX);
        txtAccelY = (TextView) findViewById(R.id.txtAccelY);
        txtAccelZ = (TextView) findViewById(R.id.txtAccelZ);

        manager = (SensorManager) getSystemService(SENSOR_SERVICE);
        sensor = manager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);


        Intent passedIntent = getIntent();
        if (passedIntent != null) {
            txtSensorName.setText(sensor.getName());
        }
    }

    protected void onResume() {
        super.onResume();
//        manager.registerListener(accL, sensor, SensorManager.SENSOR_DELAY_NORMAL);
        manager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

    protected void onStop() {
        manager.unregisterListener(this);

        super.onStop();
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        String data = "Sensor Timestamp: " + event.timestamp + "\n\n";

        for (int i = 0; i < event.values.length; ++i) {
            txtAccelX.setText(String.valueOf(event.values[i]));

        }
        Log.i(TAG, "!!!!!!!!!!!!!");
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        txtSensorAccuracy.setText("Sensor Accuracy : " + getSensorAccuracyAsString(accuracy));
    }

    private String getSensorAccuracyAsString(int accuracy) {
        String accuracyString = "";

        switch (accuracy) {
            case SensorManager.SENSOR_STATUS_ACCURACY_HIGH:
                accuracyString = "High";
                break;
            case SensorManager.SENSOR_STATUS_ACCURACY_LOW:
                accuracyString = "Low";
                break;
            case SensorManager.SENSOR_STATUS_ACCURACY_MEDIUM:
                accuracyString = "Medium";
                break;
            case SensorManager.SENSOR_STATUS_UNRELIABLE:
                accuracyString = "Unreliable";
                break;
            default:
                accuracyString = "Unknown";
                break;
        }
        return accuracyString;
    }

}
