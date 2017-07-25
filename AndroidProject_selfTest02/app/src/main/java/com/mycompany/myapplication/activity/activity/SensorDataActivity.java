package com.mycompany.myapplication.activity.activity;

import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.mycompany.myapplication.R;

public class SensorDataActivity extends AppCompatActivity implements SensorEventListener {

    private SensorManager manager;
    private Sensor sensor;
    private TextView txtSensorAccuracy;
    private TextView txtSensorName;
    private TextView[] txtAccel = new TextView[3];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensor_data);

        txtSensorName = (TextView) findViewById(R.id.txtSensorName);
        txtAccel[0] = (TextView) findViewById(R.id.txtAccelX);
        txtAccel[1] = (TextView) findViewById(R.id.txtAccelY);
        txtAccel[2] = (TextView) findViewById(R.id.txtAccelZ);

        manager = (SensorManager) getSystemService(SENSOR_SERVICE);
        sensor = manager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        Intent passedIntent = getIntent();
        if (passedIntent != null) {
            txtSensorName.setText(sensor.getName());
        }
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        String data = "Sensor Timestamp: " + event.timestamp + "\n\n";

        for (int i = 0; i < event.values.length; ++i) {
            txtAccel[i].setText(String.valueOf(event.values[i]));
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        txtSensorAccuracy.setText("Sensor Accuracy: " + getSensorAccuracyAsString(accuracy));
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
