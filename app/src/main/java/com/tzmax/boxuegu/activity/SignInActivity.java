package com.tzmax.boxuegu.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.tzmax.boxuegu.BaseApplication;
import com.tzmax.boxuegu.R;
import com.tzmax.boxuegu.data.userContract;

public class SignInActivity extends Activity {

    public static void start() {
        Context context = BaseApplication.mContext;
        Intent intent = new Intent(context, SignInActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    public String TAG = "登陆页面";
    private Context context;

    private ImageButton mGoBack;
    private EditText mEditAccount,mEditPassword;
    private Button mBtnSignIn,mBtnSignUp,mBtnRePassword;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = BaseApplication.mContext;
        setContentView(R.layout.activity_signin);

        initView();
    }

    private void initView() {
        mGoBack = findViewById(R.id.signin_page_goback);
        mEditAccount = findViewById(R.id.signin_edit_account);
        mEditPassword = findViewById(R.id.signin_edit_password);
        mBtnSignIn = findViewById(R.id.signin_btn_signin);
        mBtnSignUp = findViewById(R.id.signin_btn_signup);
        mBtnRePassword = findViewById(R.id.signin_btn_repassword);

        // 返回按钮点击事件
        mGoBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        // 登陆按钮点击事件
        mBtnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String account = mEditAccount.getText().toString();
                String password = mEditPassword.getText().toString();

                if(account.equals("") || password.equals("")) {
                    Toast.makeText(context,"账号密码不得为空！",Toast.LENGTH_LONG).show();
                    return;
                }

                requestSignIn(account,password);

            }
        });

        // 注册按钮点击事件
        mBtnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SignUpActivity.start();
            }
        });

        // 找回密码按钮点击事件
        mBtnRePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RePasswordActivity.start();
            }
        });

    }

    private void requestSignIn(String account, String password) {
        boolean isSignIn =  userContract.getInstance(context).apiSignIn(account,password);
        if(isSignIn) {
            Toast.makeText(context, "登陆成功！",Toast.LENGTH_LONG).show();
            finish();
        } else {
            Toast.makeText(context, "登陆失败，账号不存在或密码错误！", Toast.LENGTH_LONG).show();
            mEditPassword.setText("");
        }
    }


}
