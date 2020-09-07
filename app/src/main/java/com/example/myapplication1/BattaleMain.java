package com.example.myapplication1;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.view.Window;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import static android.graphics.Color.RED;

public class BattaleMain extends Activity { //バトルメイン、ゲームマネージャー、バトルエンドの受渡し改善

    TextView strategy;

    static StringBuilder stringBuilder = new StringBuilder();   //ログに写す文字列

    static boolean isTurn = false;
    static boolean isEnter = false;

    //TODO
    int count = 0;

    GameManager gameManager;
    TextView chara1Name;
    TextView chara1Hp;
    TextView chara1maxHp;
    TextView chara1Mp;
    TextView chara1maxMp;
    TextView chara1paralysis;
    TextView chara1poison;
    TextView chara2Name;
    TextView chara2Hp;
    TextView chara2maxHp;
    TextView chara2Mp;
    TextView chara2maxMp;
    TextView chara2paralysis;
    TextView chara2poison;
    TextView chara3Name;
    TextView chara3Hp;
    TextView chara3maxHp;
    TextView chara3Mp;
    TextView chara3maxMp;
    TextView chara3paralysis;
    TextView chara3poison;
    TextView chara4Name;
    TextView chara4Hp;
    TextView chara4maxHp;
    TextView chara4Mp;
    TextView chara4maxMp;
    TextView chara4paralysis;
    TextView chara4poison;
    TextView chara5Name;
    TextView chara5Hp;
    TextView chara5maxHp;
    TextView chara5Mp;
    TextView chara5maxMp;
    TextView chara5paralysis;
    TextView chara5poison;
    TextView chara6Name;
    TextView chara6Hp;
    TextView chara6maxHp;
    TextView chara6Mp;
    TextView chara6maxMp;
    TextView chara6paralysis;
    TextView chara6poison;

    //TODO
    static String strategy1;
    TextView tlog;  //ログ表示テキスト

    boolean isNextTurn = true;

    //TODO
    Intent intent;

    ArrayList<CharaStatus> items1;   //相手のキャラの属性
    ArrayList<CharaStatus> items2;   //見方のキャラの属性

    @Override
    protected void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
        setContentView(R.layout.battale_main);

        stringBuilder = new StringBuilder();

        getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.title);
        TextView title = findViewById(R.id.title);
        title.setText("バトル");

        Intent intent = getIntent();
        strategy = findViewById(R.id.strategy_main);

        strategy.setText(intent.getStringExtra("KEY_STRATEGY"));

        items1 = (ArrayList<CharaStatus>) intent.getSerializableExtra("KEY_ENEMY");
        items2 = (ArrayList<CharaStatus>) intent.getSerializableExtra("KEY_MEMBERS");

        //TODO パーティーナンバーだとわかりづらい
        gameManager = new GameManager();
        gameManager.prepareGame(items1, 1);
        gameManager.prepareGame(items2, 2);

        //TODO
        Intent inten = gameManager.prepareGame();
        prepareView(inten);

        //TODO 数字が謎
        gameManager.End = 4;
    }

    public void strategy_change(View v){    //作戦変更ボタン
        Intent intent = new Intent(this, StrategyChange.class);
        startActivity(intent);
    }

    public void battaleTurn(View v) {//ここから１ターンが終わる間のバグ
        if(strategy.getText() == ""){
            Context context = getApplicationContext();
            Toast.makeText(context , "作戦を選択してください", Toast.LENGTH_LONG).show();
        } else {
            isTurn = true;
            isEnter = true;

            if (count == 0) {
                gameManager.startGame();
                count = 1;
            }
            gameManager.game();

            Intent inten = gameManager.prepareGame();
            prepareView(inten);

            //TODO textLogの方がいいかも
            tlog = findViewById(R.id.battale_log);
            tlog.setText(stringBuilder.toString());

            if (gameManager.End == 1) {
                Intent intent = new Intent(this, BattaleOver.class);
                intent = prepareIntent(intent );
                intent.putExtra("KEY_ENEMY", items1);
                intent.putExtra("KEY_MEMBERS", items2);
                startActivity(intent);
            } else if (gameManager.End == 2) {
                Intent intent = new Intent(this, BattaleOver.class);
                intent = prepareIntent(intent);
                intent.putExtra("KEY_ENEMY", items1);
                intent.putExtra("KEY_MEMBERS", items2);
                startActivity(intent);
            } else if (gameManager.End == 3) {
                Intent intent = new Intent(this, BattaleOver.class);
                intent = prepareIntent(intent);
                intent.putExtra("KEY_ENEMY", items1);
                intent.putExtra("KEY_MEMBERS", items2);
                startActivity(intent);
            }
        }
    }

    public Intent prepareIntent(Intent intent){
        intent.putExtra("KEY_NAME1",chara1Name.getText());
        intent.putExtra("KEY_HP1", this.intent.getIntExtra("KEY_HP1", 0));
        intent.putExtra("KEY_MP1", this.intent.getIntExtra("KEY_MP1", 0));
        intent.putExtra("KEY_MHP1", chara1maxHp.getText());
        intent.putExtra("KEY_MMP1", chara1maxHp.getText());
        intent.putExtra("KEY_NAME2",chara2Name.getText());
        intent.putExtra("KEY_HP2", this.intent.getIntExtra("KEY_HP2", 0));
        intent.putExtra("KEY_MP2", this.intent.getIntExtra("KEY_MP2", 0));
        intent.putExtra("KEY_MHP2",chara2maxHp.getText());
        intent.putExtra("KEY_MMP2", chara2maxMp.getText());
        intent.putExtra("KEY_NAME3",chara3Name.getText());
        intent.putExtra("KEY_HP3", this.intent.getIntExtra("KEY_HP3", 0));
        intent.putExtra("KEY_MP3", this.intent.getIntExtra("KEY_MP3", 0));
        intent.putExtra("KEY_MHP3", chara3maxHp.getText());
        intent.putExtra("KEY_MMP3", chara3maxHp.getText());
        intent.putExtra("KEY_NAME4",chara4Name.getText());
        intent.putExtra("KEY_HP4", this.intent.getIntExtra("KEY_HP4", 0));
        intent.putExtra("KEY_MP4", this.intent.getIntExtra("KEY_MP4", 0));
        intent.putExtra("KEY_MHP4", chara4maxHp.getText());
        intent.putExtra("KEY_MMP4", chara4maxHp.getText());
        intent.putExtra("KEY_NAME5",chara5Name.getText());
        intent.putExtra("KEY_HP5", this.intent.getIntExtra("KEY_HP5", 0));
        intent.putExtra("KEY_MP5", this.intent.getIntExtra("KEY_MP5", 0));
        intent.putExtra("KEY_MHP5", chara5maxHp.getText());
        intent.putExtra("KEY_MMP5", chara5maxHp.getText());
        intent.putExtra("KEY_NAME6",chara6Name.getText());
        intent.putExtra("KEY_HP6", this.intent.getIntExtra("KEY_HP6", 0));
        intent.putExtra("KEY_MP6", this.intent.getIntExtra("KEY_MP6", 0));
        intent.putExtra("KEY_MHP6", chara6maxHp.getText());
        intent.putExtra("KEY_MMP6", chara6maxHp.getText());

        return intent;
    }

    //TODO ここでセットしてる作戦
    @Override
    public void onResume() {
        super.onResume();
        strategy.setText(strategy1);
        TextView textView = findViewById(R.id.battale_log);
        textView.setMovementMethod(ScrollingMovementMethod.getInstance());
    }

    private void prepareView(Intent intent){
        chara1Name = findViewById(R.id.chara1name);
        chara1Hp = findViewById(R.id.chara1hp);
        chara1Mp = findViewById(R.id.chara1mp);
        chara1maxHp = findViewById(R.id.chara1mhp);
        chara1maxMp = findViewById(R.id.chara1mmp);
        chara1paralysis = findViewById(R.id.chara1paralysis);
        chara1poison = findViewById(R.id.chara1poison);
        chara2Name = findViewById(R.id.chara2name);
        chara2Hp = findViewById(R.id.chara2hp);
        chara2Mp = findViewById(R.id.chara2mp);
        chara2maxHp = findViewById(R.id.chara2mhp);
        chara2maxMp = findViewById(R.id.chara2mmp);
        chara2paralysis = findViewById(R.id.chara2paralysis);
        chara2poison = findViewById(R.id.chara2poison);
        chara3Name = findViewById(R.id.chara3name);
        chara3Hp = findViewById(R.id.chara3hp);
        chara3Mp = findViewById(R.id.chara3mp);
        chara3maxHp = findViewById(R.id.chara3mhp);
        chara3maxMp = findViewById(R.id.chara3mmp);
        chara3paralysis = findViewById(R.id.chara3paralysis);
        chara3poison = findViewById(R.id.chara3poison);
        chara4Name = findViewById(R.id.chara4name);
        chara4Hp = findViewById(R.id.chara4hp);
        chara4Mp = findViewById(R.id.chara4mp);
        chara4maxHp = findViewById(R.id.chara4mhp);
        chara4maxMp = findViewById(R.id.chara4mmp);
        chara4paralysis = findViewById(R.id.chara4paralysis);
        chara4poison = findViewById(R.id.chara4poison);
        chara5Name = findViewById(R.id.chara5name);
        chara5Hp = findViewById(R.id.chara5hp);
        chara5Mp = findViewById(R.id.chara5mp);
        chara5maxHp = findViewById(R.id.chara5mhp);
        chara5maxMp = findViewById(R.id.chara5mmp);
        chara5paralysis = findViewById(R.id.chara5paralysis);
        chara5poison = findViewById(R.id.chara5poison);
        chara6Name = findViewById(R.id.chara6name);
        chara6Hp = findViewById(R.id.chara6hp);
        chara6Mp = findViewById(R.id.chara6mp);
        chara6maxHp = findViewById(R.id.chara6mhp);
        chara6maxMp = findViewById(R.id.chara6mmp);
        chara6paralysis = findViewById(R.id.chara6paralysis);
        chara6poison = findViewById(R.id.chara6poison);

        if(isNextTurn){
            chara1Name.setText(intent.getStringExtra("KEY_NAME1"));
            chara1maxHp.setText(Integer.toString(intent.getIntExtra("KEY_HP1", 0)));
            chara1maxMp.setText(Integer.toString(intent.getIntExtra("KEY_MP1", 0)));

            chara2Name.setText(intent.getStringExtra("KEY_NAME2"));
            chara2maxHp.setText(Integer.toString(intent.getIntExtra("KEY_HP2", 0)));
            chara2maxMp.setText(Integer.toString(intent.getIntExtra("KEY_MP2", 0)));

            chara3Name.setText(intent.getStringExtra("KEY_NAME3"));
            chara3maxHp.setText(Integer.toString(intent.getIntExtra("KEY_HP3", 0)));
            chara3maxMp.setText(Integer.toString(intent.getIntExtra("KEY_MP3", 0)));

            chara4Name.setText(intent.getStringExtra("KEY_NAME4"));
            chara4maxHp.setText(Integer.toString(intent.getIntExtra("KEY_HP4", 0)));
            chara4maxMp.setText(Integer.toString(intent.getIntExtra("KEY_MP4", 0)));

            chara5Name.setText(intent.getStringExtra("KEY_NAME5"));
            chara5maxHp.setText(Integer.toString(intent.getIntExtra("KEY_HP5", 0)));
            chara5maxMp.setText(Integer.toString(intent.getIntExtra("KEY_MP5", 0)));

            chara6Name.setText(intent.getStringExtra("KEY_NAME6"));
            chara6maxHp.setText(Integer.toString(intent.getIntExtra("KEY_HP6", 0)));
            chara6maxMp.setText(Integer.toString(intent.getIntExtra("KEY_MP6", 0)));
        }
        dedJudge(chara1Name, intent.getIntExtra("KEY_HP1",0));
        chara1Hp.setText(Integer.toString(intent.getIntExtra("KEY_HP1", 0)));
        chara1Mp.setText(Integer.toString(intent.getIntExtra("KEY_MP1", 0)));
        condition(chara1paralysis, "麻痺", intent.getBooleanExtra("KEY_PARALYSIS1", true));
        condition(chara1poison, "毒", intent.getBooleanExtra("KEY_POISON1", true));

        dedJudge(chara2Name, intent.getIntExtra("KEY_HP2",0));
        chara2Hp.setText(Integer.toString(intent.getIntExtra("KEY_HP2", 0)));
        chara2Mp.setText(Integer.toString(intent.getIntExtra("KEY_MP2", 0)));
        condition(chara2paralysis, "麻痺", intent.getBooleanExtra("KEY_PARALYSIS2", true));
        condition(chara2poison, "毒", intent.getBooleanExtra("KEY_POISON2", true));

        dedJudge(chara3Name, intent.getIntExtra("KEY_HP3",0));
        chara3Hp.setText(Integer.toString(intent.getIntExtra("KEY_HP3", 0)));
        chara3Mp.setText(Integer.toString(intent.getIntExtra("KEY_MP3", 0)));
        condition(chara3paralysis, "麻痺", intent.getBooleanExtra("KEY_PARALYSIS3", true));
        condition(chara3poison, "毒", intent.getBooleanExtra("KEY_POISON3", true));
        this.intent = intent;

        condition();

        isNextTurn = false;
    }

    public void condition(){

        dedJudge(chara4Name, intent.getIntExtra("KEY_HP4", 0));
        chara4Hp.setText(Integer.toString(intent.getIntExtra("KEY_HP4", 0)));
        chara4Mp.setText(Integer.toString(intent.getIntExtra("KEY_MP4", 0)));
        condition(chara4paralysis, "麻痺", intent.getBooleanExtra("KEY_PARALYSIS4", true));
        condition(chara4poison, "毒", intent.getBooleanExtra("KEY_POISON4", true));

        dedJudge(chara5Name, intent.getIntExtra("KEY_HP5", 0));
        chara5Hp.setText(Integer.toString(intent.getIntExtra("KEY_HP5", 0)));
        chara5Mp.setText(Integer.toString(intent.getIntExtra("KEY_MP5", 0)));
        condition(chara5paralysis, "麻痺", intent.getBooleanExtra("KEY_PARALYSIS5", true));
        condition(chara5poison, "毒", intent.getBooleanExtra("KEY_POISON5", true));

        dedJudge(chara6Name, intent.getIntExtra("KEY_HP6", 0));
        chara6Hp.setText(Integer.toString(intent.getIntExtra("KEY_HP6", 0)));
        chara6Mp.setText(Integer.toString(intent.getIntExtra("KEY_MP6", 0)));
        condition(chara6paralysis, "麻痺", intent.getBooleanExtra("KEY_PARALYSIS6", true));
        condition(chara6poison, "毒", intent.getBooleanExtra("KEY_POISON6", true));
    };

    public static void dedJudge(TextView die, int hp){    //死んだかチェック
        if(hp <= 0){
            die.setText(die.getText());
            die.setTextColor(RED);
        } else{
            die.setText(die.getText());
        }
    }

    public void condition(TextView condition, String strCondition, boolean isCondition){   //状態異常になっているか
        if(isCondition){
            condition.setText(strCondition);
        }
    }
}
