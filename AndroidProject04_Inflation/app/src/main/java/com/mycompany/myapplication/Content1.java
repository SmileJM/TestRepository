package com.mycompany.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Content1 extends LinearLayout {
    private LinearLayout itemContainer;

    public Content1(Context context) {
        super(context);
        // content1 의 루트태그를 LinearLayout에서 ScrollView 로 바꾸고 방향을 정해줌
        this.setOrientation(LinearLayout.VERTICAL);
        // 2번째 방법을 사용
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.content1, this);

        itemContainer = (LinearLayout) findViewById(R.id.itemContainer);
    }

    // item 이 4개 필요함
    public void addItem(Item1 item) {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View view = inflater.inflate(R.layout.content1_item, null);
        // content1_item.xml 에서 데이터가 들어가는 곳에 id 부여
        // 반복되게 생성되기 때문에 이름이 같을 수 있으므로 아래와 같이 해서는 안됨
        // 그래서 위에서(inflater.inflate(R.layout.content1_item, null)) 도
        // 부모를 넣으면 안되고 null을 넣어야 함
        // ImageView photo = (ImageView) findViewById(R.id.photo);
        // 따라서 현재 xml 이 있는 view 에서 찾아와야함
        ImageView photo = (ImageView) view.findViewById(R.id.photo);
        photo.setImageResource(item.getPhoto());
        TextView title = (TextView) view.findViewById(R.id.title);
        title.setText(item.getTitle());
        ImageView star = (ImageView) view.findViewById(R.id.star);
        star.setImageResource(item.getStar());
        TextView content = (TextView) view.findViewById(R.id.content);
        content.setText(item.getContent());

        itemContainer.addView(view);
    }
}
