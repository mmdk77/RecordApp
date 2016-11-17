package com.sds.study.recordapp.page;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Created by seon on 2016-11-17.
    ViewPager는 View를 담당하므로, ViewPager에 Fragment를 관리해주는 어댑터
 */

public class MyAdapter extends FragmentStatePagerAdapter{ //앱에서 사용할 프래그먼트 준비

    Fragment[] fragments = new Fragment[3];

    public MyAdapter(FragmentManager fm) {
        super(fm);
        fragments[0] = new FragmentRed();
        fragments[1] = new FragmentGreen();
        fragments[2] = new FragmentBlue();

    }

    @Override
    public int getCount() {
        return fragments.length;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments[position];
    }
}
