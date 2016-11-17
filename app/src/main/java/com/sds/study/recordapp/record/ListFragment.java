package com.sds.study.recordapp.record;

import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.sds.study.recordapp.R;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by seon on 2016-11-17.
 */

public class ListFragment extends Fragment implements AdapterView.OnItemClickListener {
    String TAG;
    ListView listView;
    ArrayAdapter<String> listAdapter;  //Item이 단일 위젯일경우 유용함
    ArrayList<String> list;
    String filename;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        TAG=this.getClass().getName();

        View view = inflater.inflate(R.layout.flag_list, null);
        listView = (ListView) view.findViewById(R.id.listView);

        list = (ArrayList) getFiles();

        listAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, list);
        listView.setAdapter(listAdapter); //ListView와 ArrayAdapter와 연결

        //리스트뷰 & 리스너 연결
        listView.setOnItemClickListener(this);

        return view;
    }

    //외부 저장소인 iot_record 디렉토리의 모든 파일 가져오기.
    public List getFiles() {

        File dir = new File(Environment.getExternalStorageDirectory(), "iot_record");
        File[] files = dir.listFiles();

        ArrayList list = new ArrayList();
        for (int i = 0; i < files.length; i++) {
            list.add(files[i].getName());
        }
        return list;
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int index, long id) {
        TextView txt = (TextView) view;
        filename = txt.getText().toString();
        Toast.makeText(getContext(), "파일명>>" + filename, Toast.LENGTH_SHORT).show();

        //2번째 ViewPage 호출 DetailFragment
        FileListActivity fileListActivity=(FileListActivity) getContext();
        Log.d(TAG,"FlieListActivity>>"+fileListActivity);
        fileListActivity.viewPager.setCurrentItem(1);
    }}
