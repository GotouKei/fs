package com.example.myapplication1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

public class CharaConplete extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
        setContentView(R.layout.chara_conplete);
        getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.title_bar);

        Intent intent = getIntent();

        String strName = intent.getStringExtra("KEY_NAME");
        String strJob = intent.getStringExtra("KEY_JOB");
        JobJudge judge = new JobJudge();
        strJob = judge.judge(strJob);
        String strHp = intent.getStringExtra("KEY_HP");
        String strMp = intent.getStringExtra("KEY_MP");
        String strStr = intent.getStringExtra("KEY_STR");
        String strDef = intent.getStringExtra("KEY_DEF");
        String strAgi = intent.getStringExtra("KEY_AGI");
        String strLuck = intent.getStringExtra("KEY_LUCK");

        //TODO textNameにする
        TextView tName = findViewById(R.id.mName);
        TextView tJob = findViewById(R.id.mJob);
        TextView tHp = findViewById(R.id.mHp);
        TextView tMp = findViewById(R.id.mMp);
        TextView tStr = findViewById(R.id.mStr);
        TextView tDef = findViewById(R.id.mDef);
        TextView tAgi = findViewById(R.id.mAgi);
        TextView tLuck = findViewById(R.id.mLuck);

        tName.setText(strName);
        tJob.setText(strJob);
        tHp.setText(strHp);
        tMp.setText(strMp);
        tStr.setText(strStr);
        tDef.setText(strDef);
        tAgi.setText(strAgi);
        tLuck.setText(strLuck);
    }

    //TODO アンダーバーをメソッド名にしてよかったかな？newCharaにできるっけ?
    public void newchara_create(View v){
        Intent intent = new Intent(this,CharaCreate.class);
        startActivity(intent);
    }

    public void chara_all(View v){
        Intent intent = new Intent(this, CharaList.class);
        startActivity(intent);
    }

}
