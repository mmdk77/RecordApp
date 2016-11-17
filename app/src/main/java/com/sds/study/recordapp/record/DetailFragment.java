package com.sds.study.recordapp.record;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.sds.study.recordapp.R;

import java.util.List;

/**
 * Created by seon on 2016-11-17.
 * <p>
 * 파일 재생
 */

public class DetailFragment extends Fragment implements View.OnClickListener{


    ListFragment listFragment;
    ImageView disc;
    TextView txt_filename;
    Button btnPlay;

    String filename;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.flag_detail, null);
        disc = (ImageView)view.findViewById(R.id.disc);
        txt_filename=(TextView)view.findViewById(R.id.txt_filename);
        btnPlay = (Button)view.findViewById(R.id.btnPlay);

        //페이지 구성하는 다른 페이지 Fragment를 접근
        FragmentManager faFragmentManager = this.getFragmentManager();
        List<Fragment> list = faFragmentManager.getFragments();

        listFragment = (ListFragment) list.get(0);


        //버튼과 OnClickListener의 연결
        btnPlay.setOnClickListener(this);

        return view;
    }
    public void play(){
        Animation animation=AnimationUtils.loadAnimation(getContext(),R.anim.disc);

        //안드로이드에서 애니메이션의 대상이 되는 주체는 모든 View
        disc.startAnimation(animation);
    }

    @Override
    public void onClick(View view) {

        if(view.getId()==R.id.btnPlay){
            play();
        }

    }

    //화면에 출력할 시 리스트 Fragment의 선택된 파일명 접근

    /*@Override
    public void onResume() {
        super.onResume();
        Toast.makeText(getContext(), "Resume의 선택한 파일명>>" + listFragment, Toast.LENGTH_SHORT).show();

    }*/
}
