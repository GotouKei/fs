package com.example.myapplication1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void chara_all(View v){
        Intent intent = new Intent(getApplicationContext(), CharaAll.class);
        startActivity(intent);
    }

    public void party_sort(View v){
        Intent intent = new Intent(getApplicationContext(), PartySort.class);
        startActivity(intent);
    }
}

