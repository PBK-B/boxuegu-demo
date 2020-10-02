package com.tzmax.boxuegu.activity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.tzmax.boxuegu.R;

public class MyFragment extends Fragment {

    private View rootView;
    private LinearLayout mBtnMyInfo, mBtnPlayRecord, mBtnAppSetting;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_my, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rootView = getView();

        initView();
    }

    private void initView() {
        mBtnMyInfo = rootView.findViewById(R.id.my_myinfo);
        mBtnPlayRecord = rootView.findViewById(R.id.my_play_record);
        mBtnAppSetting = rootView.findViewById(R.id.my_app_setting);

        // 设置个人页面点击事件
        mBtnMyInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 判断是否登陆，登陆后跳转个人资料页面。未登陆跳转登陆页面
                SignInActivity.start();
            }
        });

        mBtnPlayRecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "您还未登陆，请先登陆", Toast.LENGTH_LONG).show();
            }
        });

        mBtnAppSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "您还未登陆，请先登陆", Toast.LENGTH_LONG).show();
            }
        });
    }

}
