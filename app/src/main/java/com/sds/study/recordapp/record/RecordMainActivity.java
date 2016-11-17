package com.sds.study.recordapp.record;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.sds.study.recordapp.R;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by seon on 2016-11-17.
 */

public class RecordMainActivity extends AppCompatActivity {

    static final int REQUEST_RECORD_PERMISSION = 1;
    String TAG;
    boolean flag = true;

    MediaRecorder mediaRecorder;
    boolean isRun = false;
    ImageView img;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.record_layout);
        TAG = this.getClass().getName();
        img =(ImageView)findViewById(R.id.on_off);
        init();

    }

    public void init() {
        mediaRecorder = new MediaRecorder();
        mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);                  //미디어녹음시 자원 명시(마이크)
        mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4);       //음성과 영상 커버 가능(mp4)
        mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.DEFAULT);      //음성파일 인코더(Default=알아서)
    }

    //저장파일 구하기
    public String getSaveFile() {

        File dir = new File(Environment.getExternalStorageDirectory(), "iot_record");

        //현재 시간 구하기
        Date date = new Date(); //날짜를 구해오는 객체 (2016-11-17)
        String currentTime = new SimpleDateFormat("yyyy-MM-dd HHmm").format(date);
        Log.d(TAG, "현재시간" + currentTime);
        //long currentTime = System.currentTimeMillis();

        File saveFile = new File(dir, currentTime + ".mp4");

        return saveFile.getAbsolutePath();
    }

    public void showRecordList(){
        Intent intent = new Intent(this, FileListActivity.class); //명시적 Intent  == 정확히 어디로 가야하는지 알고있을때 사용하는 Intent
        startActivity(intent);
    }

    public void startRecord() {
        if (isRun) {
            mediaRecorder.stop();
            mediaRecorder.reset();// 재녹음을 위한 초기화
            img.setImageResource(R.drawable.record_start1);
            isRun=false;

            //녹음이 완료된 후 ListActivity 호출
            showRecordList();

        } else {
            try {
                mediaRecorder.setOutputFile(getSaveFile()); //파일저장(경로)
                mediaRecorder.prepare();  //실행전 준비
                mediaRecorder.start();      //시작
                isRun = true;
                img.setImageResource(R.drawable.record_stop);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }


    //유저의 접근권한 처리결과
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        Log.d(TAG, "requestCode>>" + requestCode);
        Log.d(TAG, "grantResult>>" + grantResults[0]);
        Log.d(TAG, "grantResult>>" + grantResults[1]);

        switch (requestCode) {
            case REQUEST_RECORD_PERMISSION:
                if (permissions.length > 0 && grantResults[0] == PackageManager.PERMISSION_DENIED) {
                    Toast.makeText(this, "쓰기 접근 권한이 없습니다.", Toast.LENGTH_SHORT).show();
                } else if (permissions.length > 0 && grantResults[1] == PackageManager.PERMISSION_DENIED) {
                    Toast.makeText(this, "녹음 접근 권한이 없습니다.", Toast.LENGTH_SHORT).show();
                }
                break;
        }


    }

    /*접근권한을 체크*/
    public void btnClick(View view) {
        int writePermission = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        int recordPermission = ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO);

        //접군 권한 여부 확인
        if (writePermission == PackageManager.PERMISSION_DENIED || recordPermission == PackageManager.PERMISSION_DENIED) {
            //쓰기 권한 & 오디오 녹음 권한 없을시 권한 접근 요청
            ActivityCompat.requestPermissions(this, new String[]{
                    //new String[] 배열인 이유는 여러가지 권한 부여 가능
                    Manifest.permission.WRITE_EXTERNAL_STORAGE,  //쓰기 권한
                    Manifest.permission.RECORD_AUDIO                        //오디오 녹음 권한
            }, REQUEST_RECORD_PERMISSION);
        } else {
            //접근권한 있을 경우 녹음시작
            startRecord();
        }

    }

}
