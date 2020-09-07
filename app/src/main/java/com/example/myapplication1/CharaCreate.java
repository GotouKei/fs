package com.example.myapplication1;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CharaCreate extends Activity implements      //キャラを作成するためのクラス
        RadioGroup.OnCheckedChangeListener{

    private EditText mEditText1Name;    //入力された名前を格納する
    private TextView Name1Kome1;    //米印を入れる変数
    private Button saveButton;     //登録ボタン
    private RadioGroup charaJudge;    //キャラのジョブを決めるラジオボタン

    private String strName;     //NAME
    private String strHp;      //HP
    private String strJob;    //JOB
    private String strMp;      //MP
    private String strStr;     //STR
    private String strDef;     //DEF
    private String strAgi;     //AGI
    private String strLuck;    //LUCK
    private String strCreate_At;    //CREATE_AT

    private int anIntJob;
    private int anIntHp;
    private int anIntMp;
    private int anIntStr;
    private int anIntDef;
    private int anIntAgi;
    private int anIntLuck;
    private long anIntCreate_At;

    static DateFormat tdate = new SimpleDateFormat("yyyy/MM/dd HH:mm");

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
        setContentView(R.layout.chara_create);
        getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.title_bar);

        TextView title = findViewById(R.id.title);
        title.setText("キャラ作成");

        findViews();
        init();

        charaJudge.setOnCheckedChangeListener(this);

        saveButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                if (mEditText1Name.length() <= 20) {

                    InputMethodManager inputMethodManager =
                            (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);

                    assert inputMethodManager != null;
                    inputMethodManager.hideSoftInputFromWindow(v.getWindowToken(), 0);

                    Intent intent = new Intent(CharaCreate.this, CharaConplete.class);

                    saveList();

                    strJob = Integer.toString(anIntJob);
                    strHp = Integer.toString(anIntHp);
                    strMp = Integer.toString(anIntMp);
                    strStr = Integer.toString(anIntStr);
                    strDef = Integer.toString(anIntDef);
                    strAgi = Integer.toString(anIntAgi);
                    strLuck = Integer.toString(anIntLuck);

                    intent.putExtra("KEY_NAME", strName);   //intentのそれぞれのキーと結びつける
                    intent.putExtra("KEY_JOB", strJob);
                    intent.putExtra("KEY_HP", strHp);
                    intent.putExtra("KEY_MP", strMp);
                    intent.putExtra("KEY_STR", strStr);
                    intent.putExtra("KEY_DEF", strDef);
                    intent.putExtra("KEY_AGI", strAgi);
                    intent.putExtra("KEY_LUCK", strLuck);
                    intent.putExtra("KEY_CREATE_AT", strCreate_At);

                    startActivity(intent);
                }
                else{
                    Toast.makeText(CharaCreate.this, "２０文字以下の名前を入力してください！", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void findViews(){   //xmlと結びつける
        mEditText1Name = findViewById(R.id.editText1Name);
        Name1Kome1 = findViewById(R.id.text1Kome1);
        saveButton = findViewById(R.id.button1Regist);
        charaJudge = findViewById(R.id.radioGroup1);
    }

    private void init(){    //入力欄を初期化
        mEditText1Name.setText("");
        Name1Kome1.setText("");
        mEditText1Name.requestFocus();
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId){

        switch(checkedId){
            case R.id.radioButton4_senshi:
                anIntJob = 0; anIntMp = 20; anIntDef = 30; anIntAgi = 30;
                break;
            case R.id.radioButton4_mahotsukai:
                anIntJob = 1; anIntMp = 55; anIntDef = 10; anIntAgi = 40;
                break;
            case R.id.radioButton4_soryo:
                anIntJob = 2; anIntMp = 50; anIntDef = 10; anIntAgi = 50;
                break;
        }
    }

    private void saveList(){    //データベースに登録する
        strName = mEditText1Name.getText().toString();

        //名前からハッシュ値を取得してHP,STR,LUCKを決める
        anIntHp = GetNumber(strName, 0) * (10);
        anIntStr = GetNumber(strName, 1);
        anIntLuck = GetNumber(strName, 1);

        anIntCreate_At = System.currentTimeMillis();
        strCreate_At = dateFormat(anIntCreate_At);

        if(strName.equals("")) {
            Name1Kome1.setText("※");

            Toast.makeText(CharaCreate.this, "※の箇所を入力してください", Toast.LENGTH_SHORT).show();

        } else {
            DBAdapter dbAdapter = new DBAdapter(this);

            dbAdapter.openDB();

            dbAdapter.saveDB(strName, anIntJob, anIntHp, anIntMp, anIntStr, anIntDef, anIntAgi, anIntLuck, anIntCreate_At);//日付がstr_Create_Atを保zんしたい

            dbAdapter.closeDB();
        }
    }

    public static Integer GetNumber(String name, Integer index) {	//ハッシュ値から値を出す
        try {
            byte[] result = MessageDigest.getInstance("SHA-1").digest(name.getBytes());
            String digest = String.format("%040x",new BigInteger(1,result));
            String hex = digest.substring(index*2,index*2+2);
            int hash = Integer.parseInt(hex,16);
            if(hash <= 50) {
                hash = 300;
            }
            else if(hash >= 500){
                hash = 300;
            }
            return hash/5;
        } catch(Exception ignored) {

        }
        return 0;
    }

    //TODO back先
    public void back(View view) {
        Intent intent = new Intent(getApplicationContext(), CharaList.class);
        startActivity(intent);
    }

    //何かわからん
    public static String dateFormat(Long date){
        return tdate.format(new Date(date));
    }
}
