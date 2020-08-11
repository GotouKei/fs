package com.example.myapplication1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class BattaleStart extends Activity {

    EnemyChara enemyChara = new EnemyChara();   //相手キャラ作成
    MyBaseAdapter myBaseAdapter1;
    MyBaseAdapter myBaseAdapter2;
    static ArrayList<MyListItem> items1;    //相手キャラ
    static ArrayList<MyListItem> items2;    //見方キャラ
    ListView listView1;
    Intent intent = new Intent();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
        setContentView(R.layout.battale_start);
        getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.title_bar);

        TextView title = findViewById(R.id.title);
        title.setText("バトル開始");

        items1 = enemyChara.createChara();  //相手キャラ作成

        myBaseAdapter1 = new MyBaseAdapter(this, items1);   //相手キャラをむすぎつける
        myBaseAdapter2 = new MyBaseAdapter(this, items2);   //見方キャラを結びつける

        listView1 = findViewById(R.id.listView1);
        listView1.setAdapter(myBaseAdapter1);

        ListView listView2 = findViewById(R.id.listView2);
        listView2.setAdapter(myBaseAdapter2);

        intent.putExtra("KEY_ENEMY", items1);
        intent.putExtra("KEY_MEMBERS", items2);
    }

    public void enemyChoice(View v){    //相手キャラ作成メソッド
        items1.clear(); //相手キャラをクリア
        myBaseAdapter1 = (MyBaseAdapter)listView1.getAdapter();
        items1 = enemyChara.createChara();  //相手キャラ作成
        myBaseAdapter1 = new MyBaseAdapter(this, items1);   //結びつけ
        listView1 = findViewById(R.id.listView1);
        listView1.setAdapter(myBaseAdapter1);
        myBaseAdapter1.notifyDataSetChanged();
        intent.putExtra("KEY_ENEMY", items1);
        intent.putExtra("KEY_MEMBERS", items2);
    }

    public void battale_main(View v){   //バトル
        Intent intent = new Intent(getApplicationContext(), BattaleMain.class);
        intent.putExtra("KEY_ENEMY", this.intent.getSerializableExtra("KEY_ENEMY"));
        intent.putExtra("KEY_MEMBERS", this.intent.getSerializableExtra("KEY_MEMBERS"));
        startActivity(intent);
    }

    public void back(View v){   //パーティ選択画面に戻る
        Intent intent = new Intent(this, PartySort.class);
        startActivity(intent);
    }

    private class EnemyChara{   //相手キャラクラス
        ArrayList<MyListItem> createChara(){
            ArrayList<MyListItem> items = new ArrayList<>();
            for(int i = 0; i < 3; i++) {
                String name = randomName();
                int hp = CharaCreate.GetNumber(name, 0)*(10);
                int str = CharaCreate.GetNumber(name, 1);
                int luck = CharaCreate.GetNumber(name, 1);
                int create_at = 10;

                int job;
                int mp = 0;
                int def = 0;
                int agi = 0;

                Random random = new Random();
                job = random.nextInt(3);

                switch (job) {
                    case 0:
                        mp = 20;
                        def = 30;
                        agi = 30;
                        break;
                    case 1:
                        mp = 55;
                        def = 10;
                        agi = 40;
                        break;
                    case 2:
                        mp = 50;
                        def = 10;
                        agi = 50;
                }
                MyListItem item = new MyListItem(name, job, hp, mp, str, def, agi, luck, create_at);
                items.add(item);
            }
            return items;
        }

        private String randomName(){    //名前決め
            ArrayList<String> names = new ArrayList<>();
            Collections.addAll(names, "ドリアール", "アーミュー", "ジャスカー", "ドバイモン");
            Random random = new Random();
            int nameNumber = random.nextInt(names.size()+(-1));
            return names.get(nameNumber);
        }
    }
}

