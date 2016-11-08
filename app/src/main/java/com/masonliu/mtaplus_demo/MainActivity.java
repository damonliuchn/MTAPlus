package com.masonliu.mtaplus_demo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.masonliu.mtaplus.MTAPlus;
import com.tencent.stat.StatService;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MTAPlus.init("AE9N8RCM74WP", "channel");
        StatService.trackCustomEvent(this, "onCreate", "");
    }

    @Override
    protected void onResume() {
        super.onResume();
        StatService.onResume(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        StatService.onPause(this);
    }
}
