package com.tzmax.boxuegu.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.tzmax.boxuegu.BaseApplication;
import com.tzmax.boxuegu.R;

public class VideoActivity extends Activity {

    public static void start(int VideoId) {
        Context context = BaseApplication.mContext;
        Intent intent = new Intent(context, VideoActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("videoID", VideoId);
        context.startActivity(intent);
    }

    public String TAG = "视频页面";
    private Context context;

    private TextView mTextInfo, mTextVideos, mTextDescribe;
    private ImageView mImgPoster;
    private ScrollView mSwitchInfo;
    private ListView mSwitchVideos;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = BaseApplication.mContext;
        setContentView(R.layout.activtiy_video);

        initView();
    }

    private void initView() {
        mTextInfo = findViewById(R.id.video_text_info);
        mTextVideos = findViewById(R.id.video_text_videos);
        mTextDescribe = findViewById(R.id.video_text_describe);
        mImgPoster = findViewById(R.id.video_img_poster);
        mSwitchInfo = findViewById(R.id.video_switch_info);
        mSwitchVideos = findViewById(R.id.video_switch_videos);

        // 设置简介选项点击事件
        mTextInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchInfo();
            }
        });

        // 设置视频选项点击事件
        mTextVideos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchVideos();
            }
        });

    }

    private void switchInfo() {
        // 清理样式
        mTextVideos.setBackgroundColor(getResources().getColor(R.color.white));
        mTextVideos.setTextColor(getResources().getColor(R.color.colorTextDefault));
        mSwitchVideos.setVisibility(View.GONE);

        // 设置选中样式
        mTextInfo.setBackgroundColor(getResources().getColor(R.color.colorSelectedBg));
        mTextInfo.setTextColor(getResources().getColor(R.color.white));
        mSwitchInfo.setVisibility(View.VISIBLE);

    }

    private void switchVideos() {
        // 清理样式
        mTextInfo.setBackgroundColor(getResources().getColor(R.color.white));
        mTextInfo.setTextColor(getResources().getColor(R.color.colorTextDefault));
        mSwitchInfo.setVisibility(View.GONE);

        // 设置选中样式
        mTextVideos.setBackgroundColor(getResources().getColor(R.color.colorSelectedBg));
        mTextVideos.setTextColor(getResources().getColor(R.color.white));
        mSwitchVideos.setVisibility(View.VISIBLE);

    }

}
