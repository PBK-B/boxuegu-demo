package com.tzmax.boxuegu.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.FrameLayout;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import com.google.android.material.tabs.TabLayout;
import com.tzmax.boxuegu.BaseApplication;
import com.tzmax.boxuegu.R;

import java.util.ArrayList;

public class MainActivity extends FragmentActivity {

    Context mContext;

    private TabLayout mTabLayout;
    private ArrayList<tabViewData> mTabs;

    // 页面启动方法
    public static void start() {
        Context context = BaseApplication.mContext;
        Intent intent = new Intent(context, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        setContentView(R.layout.activity_main);

        initView();
    }

    private void initView() {
        mTabLayout = findViewById(R.id.main_tab);

        setPages();
    }

    private void setPages() {

        mTabs = new ArrayList<>();
        Fragment fm = new HomeFragment();
        mTabs.add(new tabViewData(fm, "课程", R.drawable.main_course_icon, R.drawable.main_course_icon_selected));

        Fragment fm1 = new ExercisesFragment();
        mTabs.add(new tabViewData(fm1, "习题", R.drawable.main_exercises_icon, R.drawable.main_exercises_icon_selected));

        Fragment fm2 = new MyFragment();
        mTabs.add(new tabViewData(fm2, "个人", R.drawable.main_my_icon, R.drawable.main_my_icon_selected));

        // 注册 tab 底部导航事件监听
        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {

            @Override
            public void onTabSelected(TabLayout.Tab tab) {

                int index = tab.getPosition();
                Fragment fragment = mTabs.get(index).fragment;

                if (fragment != null) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.main_page, fragment).commit();
                }

                // 遍历底部 tab 设置图标
                for (int i = 0; i < mTabLayout.getTabCount(); i++) {
                    if (i == index) {
                        // 当前 tab 是选中的话设置为选中图标
                        mTabLayout.getTabAt(i).setIcon(mTabs.get(i).resPressed);
                    } else {
                        // 当前 tab 是未选中的话设置为未选中图标
                        mTabLayout.getTabAt(i).setIcon(mTabs.get(i).res);
                    }
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        for (tabViewData data : mTabs) {
            mTabLayout.addTab(mTabLayout.newTab().setText(data.title).setIcon(data.res));
        }
    }

    private class tabViewData {
        public String title;
        public Fragment fragment;
        public int res, resPressed;

        public tabViewData(Fragment fragment, String title, int res, int resPressed) {
            this.fragment = fragment;
            this.title = title;
            this.res = res;
            this.resPressed = resPressed;
        }
    }

}
