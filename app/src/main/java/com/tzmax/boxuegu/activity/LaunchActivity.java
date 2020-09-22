package com.tzmax.boxuegu.activity;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;

import androidx.annotation.Nullable;

import com.tzmax.boxuegu.R;

public class LaunchActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch);

        startHome();
    }

    // 启动主页
    private void startHome() {

        // 启动页展示 3000 毫秒后，打开主页并关闭启动页使主页置于顶栈
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                HomeActivity.start();
                finish();
            }
        }, 3000);
    }

}
