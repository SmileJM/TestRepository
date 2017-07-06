package com.mycompany.myapplication.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.mycompany.myapplication.R;
import com.mycompany.myapplication.dto.Software;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SoftwareListFragment extends Fragment {
    private static final String TAG = SoftwareListFragment.class.getSimpleName();
    private ListView listView;
    private List<Software> list = new ArrayList<>();
    private ItemAdapter itemAdapter;

    private List<String> snames = new ArrayList<>();
    private List<String> sdescs = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        listView = (ListView) inflater.inflate(R.layout.fragment_food_list, container, false);
        itemAdapter = new ItemAdapter();
        listView.setAdapter(itemAdapter);
        listView.setOnItemClickListener(itemClickListener);
        return listView;
    }

    @Override
    public void onStart() {
        super.onStart();
        setSnames();
        setSdesc();
        for (int i = 1; i <= 16; i++) {
            Random random = new Random();
            Software software = new Software();
            software.setSno(i);
            software.setSphoto(getResources().getIdentifier("software" + i, "drawable", getContext().getPackageName()));
            software.setSname(snames.get(i - 1));
            software.setSstar(getResources().getIdentifier("star" + (1 + random.nextInt(10)), "drawable", getContext().getPackageName()));
            software.setSdesc(sdescs.get(i - 1));
            addItem(software);
        }
    }

    private AdapterView.OnItemClickListener itemClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Software software = (Software) itemAdapter.getItem(position);
            Log.i(TAG, software.getSname());
            Log.i(TAG, software.getSdesc());
        }
    };

    public void addItem(Software software) {
        list.add(software);
        itemAdapter.notifyDataSetChanged();
    }

    public void removeItem(Software software) {
        list.remove(software);
        itemAdapter.notifyDataSetChanged();
    }

    class ItemAdapter extends BaseAdapter {

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
            if (convertView == null) {
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

    public void setSnames() {
        snames.add("카카오톡");
        snames.add("멜론플레이어");
        snames.add("알집");
        snames.add("고클린");
        snames.add("알약");
        snames.add("Steam");
        snames.add("안랩 V3 Lite");
        snames.add("Apple iTunes");
        snames.add("곰플레이어");
        snames.add("토크온");
        snames.add("KM플레이어");
        snames.add("오캠");
        snames.add("Google Chrome");
        snames.add("알씨");
        snames.add("TeamViewer");
        snames.add("Skype");
    }

    public void setSdesc() {
        sdescs.add("어디서나 무료로 즐기는 1:1 및 그룹채닝 메신저");
        sdescs.add("음악 플레이어 프로그램");
        sdescs.add("여러 포맷을 지원하는 다기능 압축 프로그램");
        sdescs.add("PC 최적화 프로그램");
        sdescs.add("이스트소프트의 가볍고 강력한 무료 백신");
        sdescs.add("게임 접속 플랫폼 프로그램");
        sdescs.add("AhnLab의 가볍고 빠른 무료백신");
        sdescs.add("Apple사의 다기능 디지털 미디어 플레이어");
        sdescs.add("여러 포맷을 지원하는 다기능 동영상 재생 프로그램");
        sdescs.add("마이크와 헤드셋으로 음성 채팅을 즐길 수 있는 프로그램");
        sdescs.add("전 세계 3억명이 사용하는 동영상 플레이어");
        sdescs.add("스크린 레코더 및 화면 캡처 프로그램");
        sdescs.add("구글 크롬 브라우저 프로그램");
        sdescs.add("국내 대표적인 이미지 편집 및 뷰어 프로그램");
        sdescs.add("원격을 통한 PC 및 서버 제어 관리 프로그램");
        sdescs.add("인터넷에서 즐기는 국제 전화 메신저");
    }
}
