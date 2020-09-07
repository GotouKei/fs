package com.example.myapplication1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

public class CharaContent extends Activity {

    //TODO
    String strName;
    TextView tName;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
        setContentView(R.layout.list_view);
        getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.title_bar);

        TextView title = findViewById(R.id.title);
        title.setText("キャラ詳細");

        Intent intent = getIntent();

        strName = intent.getStringExtra("KEY_NAME");
        String strJob = intent.getStringExtra("KEY_JOB");
        String strHp = intent.getStringExtra("KEY_HP");
        String strMp = intent.getStringExtra("KEY_MP");
        String strStr = intent.getStringExtra("KEY_STR");
        String strDef = intent.getStringExtra("KEY_DEF");
        String strAgi = intent.getStringExtra("KEY_AGI");
        String strLuck = intent.getStringExtra("KEY_LUCK");
        String Create_At = intent.getStringExtra("KEY_CREATE_AT");
        Long longCreate_At = Long.valueOf(Create_At);
        String strCreate_At = CharaCreate.dateFormat(longCreate_At);

        tName = findViewById(R.id.mmName);
        TextView tJob = findViewById(R.id.mmJob);
        TextView tHp = findViewById(R.id.mmHp);
        TextView tMp = findViewById(R.id.mmMp);
        TextView tStr = findViewById(R.id.mmStr);
        TextView tDef = findViewById(R.id.mmDef);
        TextView tAgi = findViewById(R.id.mmAgi);
        TextView tLuck = findViewById(R.id.mmLuck);
        TextView tCreate_At = findViewById(R.id.create_at);

        tName.setText(strName);
        JobJudge judge = new JobJudge();
        strJob = judge.judge(strJob);
        tJob.setText(strJob);
        tHp.setText(strHp);
        tMp.setText(strMp);
        tStr.setText(strStr);
        tDef.setText(strDef);
        tAgi.setText(strAgi);
        tLuck.setText(strLuck);
        tCreate_At.setText(strCreate_At);
    }

    //TODO backと戻り先のアクティビティ名入れるか
    public void back(View v){
        Intent intent = new Intent(this, CharaList.class);
        startActivity(intent);
    }

    //TODO 何をデリートするか,select,All火加えた方が後から付け加えた機能と差別化できる
    public void delete(View v){    //リストセレクト消去
        String listName = (String)tName.getText();
        DBAdapter dbAdapter = new DBAdapter(this);
        dbAdapter.openDB();
        dbAdapter.selectDelete(listName);
        dbAdapter.closeDB();
        Intent intent = new Intent(this, CharaList.class);
        startActivity(intent);
    }
}

