package com.mycompany.myapplication.content;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.mycompany.myapplication.R;
import com.mycompany.myapplication.dto.Review;
import com.mycompany.myapplication.dto.Software;

import java.util.ArrayList;
import java.util.List;


public class SoftwareList extends LinearLayout {
    private static final String TAG = SoftwareList.class.getSimpleName();
    private ListView listView;
    private List<Software> list = new ArrayList<>();
    private ItemAdapter itemAdapter;

    public SoftwareList(Context context) {
        super(context);
        LayoutInflater inflater = LayoutInflater.from(context);
        listView = (ListView) inflater.inflate(R.layout.software_list, null);
        itemAdapter  = new ItemAdapter();
        listView.setAdapter(itemAdapter);

        addView(listView);
        listView.setOnItemClickListener(itemClickListener);
    }
    private AdapterView.OnItemClickListener itemClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Software software = (Software) itemAdapter.getItem(position);
            Log.i(TAG, software.getSname());
            Log.i(TAG, software.getSdesc());
        }
    };
    public void addItem(Software item) {
        list.add(item);
        itemAdapter.notifyDataSetChanged();
    }
    public void removeItem(Software item) {
        list.remove(item);
        itemAdapter.notifyDataSetChanged();
    }

    class ItemAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int position) {
            return list.get(position);
        }

        @Override
        public long getItemId(int position) {
            return list.get(position).getSno();
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if(convertView == null) {
                LayoutInflater inflater = LayoutInflater.from(getContext());
                convertView = inflater.inflate(R.layout.software_item, null);
            }

            ImageView sphoto = (ImageView) convertView.findViewById(R.id.sphoto);
            ImageView sstar = (ImageView) convertView.findViewById(R.id.sstar);
            TextView sname = (TextView) convertView.findViewById(R.id.sname);
            TextView sdesc = (TextView) convertView.findViewById(R.id.sdesc);

            Software software = list.get(position);
            sphoto.setImageResource(software.getSphoto());
            sstar.setImageResource(software.getSstar());
            sname.setText(software.getSname());
            sdesc.setText(software.getSdesc());

            return convertView;
        }
    }
}
