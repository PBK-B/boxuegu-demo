package com.tzmax.boxuegu.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.tzmax.boxuegu.R;

import java.util.ArrayList;


public class HomeFragment extends Fragment {

    private Activity activity;
    private View rootView;
    private ViewPager mBanner;
    private RecyclerView mVideoList;
    private BannerAdapter bannerAdapter;
    private ArrayList<View> banners;
    private LinearLayout mBannerIndicator;
    public Thread bannerThread;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        activity = this.getActivity();
        rootView = view;
        initView();
    }

    private void initView() {
        mBanner = rootView.findViewById(R.id.home_banner);
        mVideoList = rootView.findViewById(R.id.home_list);
        mBannerIndicator = rootView.findViewById(R.id.home_banner_indicator);

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

        initBannerIndicator();

        int firstPage = Integer.MAX_VALUE / 2;
        mBanner.setCurrentItem(firstPage, false);

        autoBanner(3500);

    }

    private void autoBanner(final int autoTime) {

        if(bannerThread != null) return;

        // 开启一个线程，用于循环
        bannerThread = new Thread(new Runnable() {
            @Override
            public void run() {
                boolean isAuto = true;
                while (isAuto) {
                    try {
                        Thread.sleep(autoTime);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    activity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            int index = mBanner.getCurrentItem() + 1;

                            mBanner.setCurrentItem(index, false);
                        }
                    });
                }
            }
        });
        bannerThread.start();
    }

    private void initBannerIndicator() {

        final ArrayList<View> indicators = new ArrayList<>();

        for (View view : banners) {
            View mView = new View(activity);
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(20, 20);
            lp.setMargins(0, 0, 10, 0);
            mView.setLayoutParams(lp);
            mView.setBackgroundResource(R.drawable.indicator_off);
            indicators.add(mView);
            mBannerIndicator.addView(mView);
        }

        mBanner.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            int currentPosition;

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                int index = position % indicators.size();
                int i = 0;
                for (View view : indicators) {
                    if( i++ == index) {
                        view.setBackgroundResource(R.drawable.indicator_on);
                    } else {
                        view.setBackgroundResource(R.drawable.indicator_off);
                    }
                }
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    private static class BannerAdapter extends PagerAdapter {

        String TAG = "测试";
        public ArrayList<View> mViewList;

        public BannerAdapter(ArrayList<View> views) {
            this.mViewList = views;
        }

        @Override
        public int getCount() {
            if (mViewList == null) {
                return 0;
            }
            if (mViewList.size() == 1) {
                return 1;
            } else {
                return Integer.MAX_VALUE;
            }
        }

        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
            return view == object;
        }

        @NonNull
        @Override
        public Object instantiateItem(@NonNull ViewGroup container, int position) {
            int index = innerPosition(position);
            if (container.getChildCount() < mViewList.size()) {
                container.addView(mViewList.get(index));
            }
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
