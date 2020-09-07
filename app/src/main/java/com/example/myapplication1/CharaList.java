package com.example.myapplication1;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class CharaList extends Activity {

    private DBAdapter dbAdapter;    //データベースを操作するインスタンス
    private MyBaseAdapter myBaseAdapter;    //自身が持っているリストを変換

    //TODO
    private List<CharaStatus> items;     //登録されたデータ
    private ListView onlyListView;   //一覧画面の登録されたデータ
    private CharaStatus myListItem;  //登録されたデータの箱の箱

    private String[] columns = null;    //DBカラム

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
        setContentView(R.layout.chara_all);
        getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE,R.layout.title_bar);

        dbAdapter = new DBAdapter(this);   //データベースに自身の情報を渡す
        items = new ArrayList<>();  //DBに登録されたリスト
        myBaseAdapter = new MyBaseAdapter(this, items);
        onlyListView = findViewById(R.id.listView);

        loadMyList();

        onlyListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {

                Intent intent = new Intent(getApplicationContext(), CharaContent.class);

                loadMyList();

                CharaStatus selectedText = items.get(position);
                String strName = selectedText.getName();
                String strJob = Integer.toString(selectedText.getJob());
                String strHp = Integer.toString(selectedText.getHp());
                String strMp = Integer.toString(selectedText.getMp());
                String strStr = Integer.toString(selectedText.getStr());
                String strDef = Integer.toString(selectedText.getDef());
                String strAgi = Integer.toString(selectedText.getAgi());
                String strLuck = Integer.toString(selectedText.getLuck());
                String strCreate_At = Long.toString(selectedText.getCreate_At());

                intent.putExtra("KEY_NAME", strName);
                intent.putExtra("KEY_JOB", strJob);
                intent.putExtra("KEY_HP", strHp);
                intent.putExtra("KEY_MP", strMp);
                intent.putExtra("KEY_STR", strStr);
                intent.putExtra("KEY_DEF", strDef);
                intent.putExtra("KEY_AGI", strAgi);
                intent.putExtra("KEY_LUCK", strLuck);
                intent.putExtra("KEY_CREATE_AT", strCreate_At);

                loadMyList();

                startActivity(intent);
            }
        });
    }

    public void chara_create(View view){  //キャラ作成
        Intent intent = new Intent(this, CharaCreate.class);
        startActivity(intent);
    }

    //TODO back先
    public void back(View view){    //戻るボタン
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    private void loadMyList(){
        items.clear();
        dbAdapter.openDB();
        Cursor c = dbAdapter.getDB(columns);

        if(c.moveToFirst()){
            do{
                myListItem = new CharaStatus(
                        c.getString(0),
                        c.getInt(1),
                        c.getInt(2),
                        c.getInt(3),
                        c.getInt(4),
                        c.getInt(5),
                        c.getInt(6),
                        c.getInt(7),
                        c.getLong(8));
                items.add(myListItem);
            } while(c.moveToNext());
        }
        c.close();
        dbAdapter.closeDB();
        onlyListView.setAdapter(myBaseAdapter);
        myBaseAdapter.notifyDataSetChanged();
    }
}
