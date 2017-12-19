package com.zhangshen147.android.hotelmanagersystem;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.widget.ImageView;

public class StartActivity extends AppCompatActivity {

    private ImageView mImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        // 引用成员变量
        mImageView = findViewById(R.id.iv_start_in_startactivity);

        // 添加开场的缩放动画
        Animation animation;
    }


}
