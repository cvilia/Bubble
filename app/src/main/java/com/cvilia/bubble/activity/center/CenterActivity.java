package com.cvilia.bubble.activity.center;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.cvilia.bubble.R;
import com.cvilia.bubble.route.PageUrlConfig;

@Route(path = PageUrlConfig.CENTER_PAGE)
public class CenterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_center);
    }
}