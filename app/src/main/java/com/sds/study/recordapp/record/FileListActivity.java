package com.sds.study.recordapp.record;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.sds.study.recordapp.R;

/**
 * Created by seon on 2016-11-17.
 * 녹음으로 인하여 생성된 파일을 목록으로 보여주고,
 * 해당 파일 선택시 재생.
 */

public class FileListActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener{

    String TAG;
    ViewPager viewPager ; //Fragment 관리 객체
    RecordPagerAdapter pagerAdapter; //Fragment Controller?

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TAG=this.getClass().getName();
        Log.d(TAG,"FileListActivity"+this);
        setContentView(R.layout.list_layout);

        viewPager = (ViewPager)findViewById(R.id.viewPager);                              //연결하기 위해 ViewPager 아이디 값 가져오기
        pagerAdapter = new RecordPagerAdapter(getSupportFragmentManager());     //getSupportFragmentManager는 AppCompatActivity일 경우에만 가능
        viewPager.setAdapter(pagerAdapter);                                                         //ViewPager와 pagerAdapter 연결

        //OnPageChangeListener 연결
        viewPager.addOnPageChangeListener(this);

    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        //Log.d(TAG,"onPageScrolled");
    }

    @Override
    public void onPageSelected(int position) { //페이지가 선택이 확정시 commit

        Log.d(TAG,"onPageSelected");
        DetailFragment detailFragment=(DetailFragment) pagerAdapter.fragments[1];
        ListFragment listFragment=(ListFragment) pagerAdapter.fragments[0];

        detailFragment.txt_filename.setText(listFragment.filename);
    }

    @Override
    public void onPageScrollStateChanged(int state) {
        //Log.d(TAG,"onPageScrollStateChanged");

    }
}
