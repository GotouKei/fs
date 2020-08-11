package com.example.myapplication1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.RadioGroup;
import android.widget.TextView;

public class StrategyChange extends Activity implements RadioGroup.OnCheckedChangeListener{
    RadioGroup radioGroup;
    String strategy;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
        setContentView(R.layout.strategy_change);
        getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.title_enter);

        TextView title = findViewById(R.id.title);
        title.setText("作戦");

        radioGroup = findViewById(R.id.radioGroup);
        radioGroup.setOnCheckedChangeListener(this);
    }

    public void enter(View v){
        BattaleMain.strategy1 = strategy;
        finish();
    }

    public void back(View v){
        Intent intent = new Intent(this, BattaleMain.class);
        startActivity(intent);
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId){
        switch(checkedId){
            case R.id.magic_priority:
                strategy = "魔法優先";
                break;
            case R.id.magic_saving:
                strategy = "魔法節約";
                break;
            case R.id.balance:
                strategy = "バランスよく";
                break;
            case R.id.life_priority:
                strategy = "命優先";
                break;
            case R.id.random:
                strategy = "ランダム";
                break;
        }
    }
}
