package com.tzmax.boxuegu.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;

import com.tzmax.boxuegu.BaseApplication;
import com.tzmax.boxuegu.R;

public class SignInActivity extends Activity {

    public static void start() {
        Context context = BaseApplication.mContext;
        Intent intent = new Intent(context, SignInActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);
    }
}
