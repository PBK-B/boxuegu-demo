package com.tzmax.boxuegu.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.tzmax.boxuegu.BaseApplication;
import com.tzmax.boxuegu.R;
import com.tzmax.boxuegu.data.userContract;

public class SignUpActivity extends Activity {

    public static void start() {
        Context context = BaseApplication.mContext;
        Intent intent = new Intent(context, SignUpActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    public String TAG = "注册页面";
    public Context context;

    private ImageButton mImgBtnGoBack;
    private EditText mEditAccount, mEditPassword, mEditConfirm;
    private Button mBtnSignUp;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        context = BaseApplication.mContext;

        initView();
    }

    private void initView() {
        mImgBtnGoBack = findViewById(R.id.signup_page_goback);
        mEditAccount = findViewById(R.id.signup_edit_account);
        mEditPassword = findViewById(R.id.signup_edit_password);
        mEditConfirm = findViewById(R.id.signup_edit_confirm);
        mBtnSignUp = findViewById(R.id.signup_btn_signup);

        // 退出页面按钮点击事件
        mImgBtnGoBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        // 设置登陆按钮点击事件
        mBtnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 获取用户输入账号密码，判断账号密码是否输入
                String account = mEditAccount.getText().toString();
                String password = mEditPassword.getText().toString();
                String confirm = mEditConfirm.getText().toString();

                Log.d(TAG, "onClick: " + account + " " + password + " " + confirm );

                if (account.equals("") || password.equals("") || confirm.equals("")) {
                    Toast.makeText(context, "账号密码不得为空！", Toast.LENGTH_LONG).show();
                    return;
                }

                // 判断两次输入密码是否一致
                if(!password.equals(confirm)) {
                    Toast.makeText(context, "两次输入密码不一致！", Toast.LENGTH_LONG).show();
                    return;
                }

                requestSignUp(account,password);

            }
        });

    }

    private void requestSignUp(String account, String password) {
        boolean isSignUp = userContract.getInstance(context).apiSignUp(account, password);
        if(isSignUp) {
            Toast.makeText(context, "注册成功！", Toast.LENGTH_LONG).show();
            finish();
        } else {
            Toast.makeText(context, "注册失败！", Toast.LENGTH_LONG).show();
        }
    }

}
