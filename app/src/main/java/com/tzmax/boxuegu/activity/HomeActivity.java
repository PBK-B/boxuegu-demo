package com.tzmax.boxuegu.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;

import com.tzmax.boxuegu.BaseApplication;
import com.tzmax.boxuegu.R;

public class HomeActivity extends Activity {

    Context mContext;

    // 页面启动方法
    public static void start() {
        Context context = BaseApplication.mContext;
        Intent intent = new Intent(context, HomeActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        setContentView(R.layout.activity_home);
    }
}
