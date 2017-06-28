package com.mycompany.myapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {
    // 앱을 실행하면 실행되는 메소드
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // d : debug 로그 레벨로 실행 / i : info 레벨
        // MainActivity : 로그 태그 이름
        // 실행 : 로그 내용
        Log.i(Utils.getTag(), "실행");
        super.onCreate(savedInstanceState);
        // R : resource
        // layout : res>layout 폴더
        // setContentView : xml을 해석해서 현재 내용의 뷰를 세팅
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onRestart() {
        Log.i(Utils.getTag(), "실행");
        super.onRestart();
    }

    @Override
    protected void onStart() {
        Log.i(Utils.getTag(), "실행");
        super.onStart();
    }

    @Override
    protected void onResume() {
        Log.i(Utils.getTag(), "실행");
        super.onResume();
    }

    // xml 파일로부터 메뉴를 생성하는 역할
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        Log.i(Utils.getTag(), "실행");
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    // 어떤 xml 이 선택되었는지에 따라서 실행 (이벤트 핸드러 역할)
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Log.i(Utils.getTag(), "실행");
        if (item.getItemId() == R.id.action_full_activity) {
            Intent intent = new Intent(this, FullActivity.class);
            startActivity(intent);
        } else if (item.getItemId() == R.id.action_dialog_activity) {
            Intent intent = new Intent(this, DialogActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    // ------------------------------------------------------
    @Override
    protected void onPause() {
        Log.i(Utils.getTag(), "실행");
        super.onPause();
    }

    @Override
    protected void onStop() {
        Log.i(Utils.getTag(), "실행");
        super.onStop();
    }

    // 앱을 종료하면 실행되는 메소드
    @Override
    protected void onDestroy() {
        Log.i(Utils.getTag(), "실행");
        super.onDestroy();
    }
}
