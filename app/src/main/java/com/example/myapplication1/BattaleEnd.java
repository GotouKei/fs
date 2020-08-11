package com.example.myapplication1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

public class BattaleEnd extends Activity {

    TextView chara1Name;    //キャラの名前１
    TextView chara1Hp;  //キャラのHP
    TextView chara1maxHp;   //キャラの最大HP
    TextView chara1Mp;  //キャラのMP
    TextView chara1maxMp;   //キャラの最大MP
    TextView chara1Paralysis;   //キャラの麻痺状態
    TextView chara1Poison;  //キャラの毒状態
    TextView chara2Name;
    TextView chara2Hp;
    TextView chara2maxHp;
    TextView chara2Mp;
    TextView chara2maxMp;
    TextView chara2Paralysis;
    TextView chara2Poison;
    TextView chara3Name;
    TextView chara3Hp;
    TextView chara3maxHp;
    TextView chara3Mp;
    TextView chara3maxMp;
    TextView chara3Paralysis;
    TextView chara3Poison;
    TextView chara4Name;
    TextView chara4Hp;
    TextView chara4maxHp;
    TextView chara4Mp;
    TextView chara4maxMp;
    TextView chara4Paralysis;
    TextView chara4Poison;
    TextView chara5Name;
    TextView chara5Hp;
    TextView chara5maxHp;
    TextView chara5Mp;
    TextView chara5maxMp;
    TextView chara5Paralysis;
    TextView chara5Poison;
    TextView chara6Name;
    TextView chara6Hp;
    TextView chara6maxHp;
    TextView chara6Mp;
    TextView chara6maxMp;
    TextView chara6Paralysis;
    TextView chara6Poison;

    Intent inten;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
        setContentView(R.layout.battale_end);
        getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.title);
        TextView title = findViewById(R.id.title);
        title.setText("バトル結果");

        inten = getIntent();

        if(GameManager.End == 1) {
            ((ImageView)findViewById(R.id.imageView)).setImageResource(R.drawable.you_win); //勝った時の画像
        }
        else if(GameManager.End == 2){
            ((ImageView)findViewById(R.id.imageView)).setImageResource(R.drawable.you_lose);    //負けた時の画像
        }
        else if(GameManager.End == 3){

        }

        chara1Name = findViewById(R.id.chara1name);
        chara1Hp = findViewById(R.id.chara1hp);
        chara1Mp = findViewById(R.id.chara1mp);
        chara1maxHp = findViewById(R.id.chara1mhp);
        chara1maxMp = findViewById(R.id.chara1mmp);
        chara1Paralysis = findViewById(R.id.chara1paralysis);
        chara1Poison = findViewById(R.id.chara1poison);
        chara2Name = findViewById(R.id.chara2name);
        chara2Hp = findViewById(R.id.chara2hp);
        chara2Mp = findViewById(R.id.chara2mp);
        chara2maxHp = findViewById(R.id.chara2mhp);
        chara2maxMp = findViewById(R.id.chara2mmp);
        chara2Paralysis = findViewById(R.id.chara2paralysis);
        chara2Poison = findViewById(R.id.chara2poison);
        chara3Name = findViewById(R.id.chara3name);
        chara3Hp = findViewById(R.id.chara3hp);
        chara3Mp = findViewById(R.id.chara3mp);
        chara3maxHp = findViewById(R.id.chara3mhp);
        chara3maxMp = findViewById(R.id.chara3mmp);
        chara3Paralysis = findViewById(R.id.chara3paralysis);
        chara3Poison = findViewById(R.id.chara3poison);
        chara4Name = findViewById(R.id.chara4name);
        chara4Hp = findViewById(R.id.chara4hp);
        chara4Mp = findViewById(R.id.chara4mp);
        chara4maxHp = findViewById(R.id.chara4mhp);
        chara4maxMp = findViewById(R.id.chara4mmp);
        chara4Paralysis = findViewById(R.id.chara4paralysis);
        chara4Poison = findViewById(R.id.chara4poison);
        chara5Name = findViewById(R.id.chara5name);
        chara5Hp = findViewById(R.id.chara5hp);
        chara5Mp = findViewById(R.id.chara5mp);
        chara5maxHp = findViewById(R.id.chara5mhp);
        chara5maxMp = findViewById(R.id.chara5mmp);
        chara5Paralysis = findViewById(R.id.chara5paralysis);
        chara5Poison = findViewById(R.id.chara5poison);
        chara6Name = findViewById(R.id.chara6name);
        chara6Hp = findViewById(R.id.chara6hp);
        chara6Mp = findViewById(R.id.chara6mp);
        chara6maxHp = findViewById(R.id.chara6mhp);
        chara6maxMp = findViewById(R.id.chara6mmp);
        chara6Paralysis = findViewById(R.id.chara6paralysis);
        chara6Poison = findViewById(R.id.chara6poison);

        //
        chara1Name.setText(inten.getStringExtra("KEY_NAME1"));
        chara1Hp.setText(Integer.toString(inten.getIntExtra("KEY_HP1", 0)));
        BattaleMain.dedJudge(chara1Name, Integer.parseInt((String)chara1Hp.getText()));
        chara1Mp.setText(Integer.toString(inten.getIntExtra("KEY_MP1", 0)));
        chara1maxHp.setText(inten.getStringExtra("KEY_MHP1"));
        chara1maxMp.setText(inten.getStringExtra("KEY_MMP1"));
        chara1Paralysis.setText(inten.getStringExtra("KEY_PARALYSIS1"));
        chara1Poison.setText(inten.getStringExtra("KEY_POISON1"));
        chara2Name.setText(inten.getStringExtra("KEY_NAME2"));
        chara2Hp.setText(Integer.toString(inten.getIntExtra("KEY_HP2", 0)));
        BattaleMain.dedJudge(chara2Name, Integer.parseInt((String)chara2Hp.getText()));
        chara2Mp.setText(Integer.toString(inten.getIntExtra("KEY_MP2", 0)));
        chara2maxHp.setText(inten.getStringExtra("KEY_MHP2"));
        chara2maxMp.setText(inten.getStringExtra("KEY_MMP2"));
        chara2Paralysis.setText(inten.getStringExtra("KEY_PARALYSIS2"));
        chara2Poison.setText(inten.getStringExtra("KEY_POISON2"));
        chara3Name.setText(inten.getStringExtra("KEY_NAME3"));
        chara3Hp.setText(Integer.toString(inten.getIntExtra("KEY_HP3", 0)));
        BattaleMain.dedJudge(chara3Name, Integer.parseInt((String)chara3Hp.getText()));
        chara3Mp.setText(Integer.toString(inten.getIntExtra("KEY_MP3", 0)));
        chara3maxHp.setText(inten.getStringExtra("KEY_MHP3"));
        chara3maxMp.setText(inten.getStringExtra("KEY_MMP3"));
        chara3Paralysis.setText(inten.getStringExtra("KEY_PARALYSIS3"));
        chara3Poison.setText(inten.getStringExtra("KEY_POISON3"));
        chara4Name.setText(inten.getStringExtra("KEY_NAME4"));
        chara4Hp.setText(Integer.toString(inten.getIntExtra("KEY_HP4", 0)));
        BattaleMain.dedJudge(chara4Name, Integer.parseInt((String)chara4Hp.getText()));
        chara4Mp.setText(Integer.toString(inten.getIntExtra("KEY_MP4", 0)));
        chara4maxHp.setText(inten.getStringExtra("KEY_MHP4"));
        chara4maxMp.setText(inten.getStringExtra("KEY_MMP4"));
        chara4Paralysis.setText(inten.getStringExtra("KEY_PARALYSIS4"));
        chara4Poison.setText(inten.getStringExtra("KEY_POISON4"));
        chara5Name.setText(inten.getStringExtra("KEY_NAME5"));
        chara5Hp.setText(Integer.toString(inten.getIntExtra("KEY_HP5", 0)));
        BattaleMain.dedJudge(chara5Name, Integer.parseInt((String)chara5Hp.getText()));
        chara5Mp.setText(Integer.toString(inten.getIntExtra("KEY_MP5", 0)));
        chara5maxHp.setText(inten.getStringExtra("KEY_MHP5"));
        chara5maxMp.setText(inten.getStringExtra("KEY_MMP5"));
        chara5Paralysis.setText(inten.getStringExtra("KEY_PARALYSIS5"));
        chara5Poison.setText(inten.getStringExtra("KEY_POISON5"));
        chara6Name.setText(inten.getStringExtra("KEY_NAME6"));
        chara6Hp.setText(Integer.toString(inten.getIntExtra("KEY_HP6", 0)));
        BattaleMain.dedJudge(chara6Name, Integer.parseInt((String)chara6Hp.getText()));
        chara6Mp.setText(Integer.toString(inten.getIntExtra("KEY_MP6", 0)));
        chara6maxHp.setText(inten.getStringExtra("KEY_MHP6"));
        chara6maxMp.setText(inten.getStringExtra("KEY_MMP6"));
        chara6Paralysis.setText(inten.getStringExtra("KEY_PARALYSIS6"));
        chara6Poison.setText(inten.getStringExtra("KEY_POISON6"));
    }

    public void revenge(View v){    //再挑戦
        Intent intent = new Intent(this, BattaleMain.class);
        intent.putExtra("KEY_ENEMY", inten.getSerializableExtra("KEY_ENEMY"));
        intent.putExtra("KEY_MEMBERS", inten.getSerializableExtra("KEY_MEMBERS"));
        startActivity(intent);
    }

    public void nextBattale(View v){    //次のバトル
        Intent intent = new Intent(this, BattaleStart.class);
        startActivity(intent);
    }

    public void battaleEnd(View v){ //バトル終了
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
