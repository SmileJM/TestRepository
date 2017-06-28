package com.mycompany.myapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;

public class MainActivity extends AppCompatActivity {
    private ImageView imgTitle;
    private Button btnImg1;
    private Button btnImg2;
    private RadioButton rbImg1;
    private RadioButton rbImg2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // findViewById : 객체를 찾아오는 메소드
        // 안드로이드에서는 ID 도 하나의 리소스임
        imgTitle = (ImageView) findViewById(R.id.imgTitle);
        btnImg1 = (Button) findViewById(R.id.btnImg1);
        btnImg2 = (Button) findViewById(R.id.btnImg2);
        rbImg1 = (RadioButton) findViewById(R.id.rbImg1);
        rbImg2 = (RadioButton) findViewById(R.id.rbImg2);

        // 익명객체로 만들어도 되지만 코드가 길어보이기 때문에 필드로 정의함
        // 버튼 뿐만 아니라 모든 이벤트 처리가 이렇게 이루어짐
        btnImg1.setOnClickListener(handleBtnImg);
        btnImg2.setOnClickListener(handleBtnImg);
        rbImg1.setOnClickListener(handleBtnImg);
        rbImg2.setOnClickListener(handleBtnImg);
    }

    private View.OnClickListener handleBtnImg = new View.OnClickListener() {
        // View 는 모든 위젯의 상위 클래스
        // 눈에 보이는 것은 모두 View 를 상속해서 만들어짐
        @Override
        public void onClick(View v) {
            // 이미지를 바꾸기 위함
            if (v == btnImg1 || v == rbImg1) {
                imgTitle.setImageResource(R.drawable.photo1);
            } else if (v == btnImg2 || v == rbImg2) {
                imgTitle.setImageResource(R.drawable.photo2);
            }
        }
    };

    public void handleBtnImg3(View v) {
        imgTitle.setImageResource(R.drawable.photo3);
    }
    public void handleRbImg3(View v) {
        imgTitle.setImageResource(R.drawable.photo3);
    }
}
