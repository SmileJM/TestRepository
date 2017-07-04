package com.mycompany.myapplication;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    private static String TAG = "MainActivity.class";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void handleBtnMessageDialog(View v) {
        // Builder: 다이얼로그 안의 내용을 어떻게 만들 것인지를 하는 역할
        // 정적 클래스
        // 정형화된 다이얼로그
        AlertDialog dialog = new AlertDialog.Builder(this)
                .setIcon(R.mipmap.ic_launcher)
                .setTitle("제목")
                .setMessage("알려줄 메시지...")
                // 위치의 의미라고 생각하기
                .setPositiveButton("Yes", listener)
                .setNegativeButton("No", null)
                .setNeutralButton("Cancle", null)
                // create: 위와 같은 내용으로 dialog 만들기
                .create();
        dialog.show();
    }

    DialogInterface.OnClickListener listener = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            Log.i(TAG, "Yes 버튼 터치");
        }
    };

    public void handleBtnListDialog(View v) {
        final String[] menus = {"메뉴1", "메뉴2", "메뉴3"};
        AlertDialog dialog = new AlertDialog.Builder(this)
                .setIcon(R.mipmap.ic_launcher)
                .setTitle("선택하세요")
                .setItems(menus, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String selectedMenu = menus[which];
                        Log.i(TAG, selectedMenu);
                    }
                })
                .create();
        dialog.show();
    }

    public void handleBtnSingleChoiceDialog(View v) {
        final String[] menus = {"메뉴1", "메뉴2", "메뉴3"};
        AlertDialog dialog = new AlertDialog.Builder(this)
                .setIcon(R.mipmap.ic_launcher)
                .setTitle("선택하세요")
                .setSingleChoiceItems(menus, 1, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String selectedMenu = menus[which];
                        Log.i(TAG, selectedMenu);
                        // 다이얼로그 창 닫기 (다이얼로그 자원 해제)
                        dialog.dismiss();
                        // hide 의 개념
//                        dialog.cancel();
                    }
                })
                .create();
        dialog.show();
    }

    public void handleBtnMultiChoiceDialog(View v) {
        final String[] menus = {"메뉴1", "메뉴2", "메뉴3", "메뉴4", "메뉴5"};
        final boolean[] selected = {false, true, false, false, true, false};
        AlertDialog dialog = new AlertDialog.Builder(this)
                .setIcon(R.mipmap.ic_launcher)
                .setTitle("선택하세요")
                .setMultiChoiceItems(menus, selected, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                        selected[which] = isChecked;
                    }
                })
                .setPositiveButton("취소", null)
                .setNegativeButton("확인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        for (int i = 0; i < selected.length; i++) {
                            if (selected[i]) {
                                String menu = menus[i];
                                Log.i(TAG, " " + menu);
                            }
                        }
                    }
                })
                .create();
        dialog.show();
    }

    public void handleBtnStickProgressDialog(final View v) {
        final ProgressDialog dialog = new ProgressDialog(this);
        dialog.setIcon(R.mipmap.ic_launcher);
        dialog.setTitle("통신상태");
        dialog.setMessage("다운로드 중입니다.");
        dialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        dialog.setMax(1024);    // 실제 파일 사이즈

        dialog.show();
        Thread thread = new Thread() {
            @Override
            public void run() {
                for (int i = 0; i <= 1024; i++) {
                    final int value = i;
                    Runnable runable = new Runnable() {
                        @Override
                        public void run() {
                            // 값이 바뀌는 부분, 스레드 처리를 함
                            dialog.setProgress(value);
                            dialog.setSecondaryProgress(value * 2);

                        }
                    };

                    v.post(runable);

                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        thread.start();
    }

    public void handleBtnCircleProgressDialog(final View v) {
        final ProgressDialog dialog = new ProgressDialog(this);
        dialog.setIcon(R.mipmap.ic_launcher);
        dialog.setTitle("통신상태");
        dialog.setMessage("다운로드 중입니다.");
        dialog.show();
        Thread thread = new Thread() {
            @Override
            public void run() {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Runnable runnable = new Runnable() {
                    @Override
                    public void run() {
                        dialog.dismiss();
                    }
                };
                v.post(runnable);
            }
        };
        thread.start();
    }

    public void handleBtnCustomDialog(View v) {
        CustomDialog dialog = new CustomDialog();
        dialog.show(getSupportFragmentManager(), null);
    }
}
