package com.mycompany.myapplication.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.mycompany.myapplication.R;
import com.mycompany.myapplication.content.FoodList;
import com.mycompany.myapplication.content.ReviewList;
import com.mycompany.myapplication.content.SoftwareList;
import com.mycompany.myapplication.dto.Food;
import com.mycompany.myapplication.dto.Review;
import com.mycompany.myapplication.dto.Software;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private LinearLayout linearLayoutTop;
    private FrameLayout frameLayout;
    private List<String> snames = new ArrayList<>();
    private List<String> sdescs = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        linearLayoutTop = (LinearLayout) findViewById(R.id.linearLayoutTop);
        frameLayout = (FrameLayout) findViewById(R.id.frameLayout);
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

    public void handleRadioButton1(View v) {
        linearLayoutTop.setBackgroundResource(R.drawable.photo1);
    }

    public void handleRadioButton2(View v) {
        linearLayoutTop.setBackgroundResource(R.drawable.photo2);
    }

    public void handleRadioButton3(View v) {
        linearLayoutTop.setBackgroundResource(R.drawable.photo3);
    }

    public void handleButton1(View v) {
        frameLayout.removeAllViews();
        ReviewList reviewList = new ReviewList(this);
        frameLayout.addView(reviewList);
        for (int i = 1; i <= 10; i++) {
            Review item = new Review();
            item.setPhoto(getResources().getIdentifier("member"+i,"drawable",getPackageName()));
            item.setTitle("ListView와 Adapter");
            item.setStar(R.drawable.star10);
            item.setContent("Adapter는 item XML을 Inflation 해서 데이터를 세팅한 후 ListView에 제공하는 역할을 합니다.");

            reviewList.addItem(item);
        }
    }

    public void handleButton2(View v) {
        frameLayout.removeAllViews();
        FoodList foodList = new FoodList(this);
        frameLayout.addView(foodList);
        for (int i = 1; i <= 6; i++) {
            Food food = new Food();
            food.setFno(i);
            food.setFphoto(getResources().getIdentifier("food" + i, "drawable", getPackageName()));
            food.setFname("food" + i);
            food.setFstar(getResources().getIdentifier("star" + i, "drawable", getPackageName()));
            food.setFdesc("맛있는 음식입니다.");
            foodList.addItem(food);
        }
    }

    public void handleButton3(View v) {
        frameLayout.removeAllViews();
        SoftwareList softwareList = new SoftwareList(this);
        frameLayout.addView(softwareList);
        setSnames();
        setSdesc();
        for (int i = 1; i <= 16; i++) {
            Random random = new Random();
            Software software = new Software();
            software.setSno(i);
            software.setSphoto(getResources().getIdentifier("software" + i, "drawable", getPackageName()));
            software.setSname(snames.get(i - 1));
            software.setSstar(getResources().getIdentifier("star" + (1 + random.nextInt(10)), "drawable", getPackageName()));
            software.setSdesc(sdescs.get(i - 1));
            softwareList.addItem(software);
        }
    }
}
