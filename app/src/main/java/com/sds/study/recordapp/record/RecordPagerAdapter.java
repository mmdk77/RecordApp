package com.sds.study.recordapp.record;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;

/**
 * Created by seon on 2016-11-17.
 */

public class RecordPagerAdapter extends FragmentStatePagerAdapter {

    Fragment[] fragments = new Fragment[2];



    public RecordPagerAdapter(FragmentManager fm) {
        super(fm);
        fragments[0]=new Fragment();
        fragments[1]=new Fragment();
    }

    @Override
    public Fragment getItem(int position) {
        return fragments[position];
    }

    @Override
    public int getCount() {
        return fragments.length;
    }
}
