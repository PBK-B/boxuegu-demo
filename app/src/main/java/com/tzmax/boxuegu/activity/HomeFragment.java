package com.tzmax.boxuegu.activity;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.tzmax.boxuegu.R;

import java.util.ArrayList;


public class HomeFragment extends Fragment {

    private View rootView;
    private ViewPager mBanner;
    private RecyclerView mVideoList;
    private BannerAdapter bannerAdapter;
    private ArrayList<View> banners;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rootView = view;
        initView();
    }

    private void initView() {
        mBanner = rootView.findViewById(R.id.home_banner);
        mVideoList = rootView.findViewById(R.id.home_list);

        setBanners();
    }

    private void setBanners() {
        if (mBanner == null) return; // 组件初始化判空，避免闪退问题
        banners = new ArrayList<>();

        ImageView imageView1 = new ImageView(this.getContext());
        imageView1.setImageResource(R.drawable.banner_1);
        imageView1.setScaleType(ImageView.ScaleType.FIT_XY);
        banners.add(imageView1);

        ImageView imageView2 = new ImageView(this.getContext());
        imageView2.setImageResource(R.drawable.banner_2);
        imageView2.setScaleType(ImageView.ScaleType.FIT_XY);
        banners.add(imageView2);

        ImageView imageView3 = new ImageView(this.getContext());
        imageView3.setImageResource(R.drawable.banner_3);
        imageView3.setScaleType(ImageView.ScaleType.FIT_XY);
        banners.add(imageView3);

        bannerAdapter = new BannerAdapter(banners);
        mBanner.setAdapter(bannerAdapter);

        int firstPage = Integer.MAX_VALUE / 2;
        mBanner.setCurrentItem(firstPage, false);

    }

    private static class BannerAdapter extends PagerAdapter {

        String TAG = "测试";
        private ArrayList<View> mViewList;

        public BannerAdapter(ArrayList<View> views) {
            this.mViewList = views;
        }

        @Override
        public int getCount() {
            return Integer.MAX_VALUE;
        }

        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
            return view == object;
        }

        @NonNull
        @Override
        public Object instantiateItem(@NonNull ViewGroup container, int position) {
            int index = innerPosition(position);
            container.addView(mViewList.get(index));
            return mViewList.get(index);
        }

        @Override
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
            int index = innerPosition(position);
            container.removeView(mViewList.get(index));
        }

        private int innerPosition(int position) {
            return position % mViewList.size();
        }

    }


}
