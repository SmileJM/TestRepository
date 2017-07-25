package com.mycompany.myapplication.activity.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.mycompany.myapplication.R;
import com.mycompany.myapplication.activity.dto.Sensor;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class SensorDataFragment extends Fragment {
    private static final String TAG = "SensorDataFragment";
    private ListView listView;
    private List<Sensor> list = new ArrayList<>();
    private ItemAdapter itemAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        listView = (ListView) inflater.inflate(R.layout.fragment_sensor_data, container, false);
        itemAdapter = new ItemAdapter();
        listView.setAdapter(itemAdapter);
        listView.setOnItemClickListener(itemClickListener);
        return listView;
    }

    private AdapterView.OnItemClickListener itemClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Sensor sensor = (Sensor) itemAdapter.getItem(position);
            Log.i(TAG, sensor.getSensorName());
        }
    };

}
