package com.cvilia.bubble.activity.share;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.cvilia.bubble.R;
import com.cvilia.bubble.route.PageUrlConfig;

@Route(path = PageUrlConfig.SHARE_PAGE)
public class ShareActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share);
        Log.d("BaseActivity", "新的实例");
    }
}