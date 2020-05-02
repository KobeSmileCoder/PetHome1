package com.example.sunhan.pethome1.listener;

import android.support.v4.view.ViewPager;

import com.example.sunhan.pethome1.ui.widget.Indicator;

public class ViewPagerListener implements ViewPager.OnPageChangeListener {
    private Indicator mIndicator;

    public ViewPagerListener(Indicator indicator) {
        mIndicator = indicator;
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        mIndicator.setOffset(position, positionOffset);
    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
