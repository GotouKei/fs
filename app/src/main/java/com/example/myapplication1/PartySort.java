package com.example.myapplication1;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class PartySort extends Activity {

    private DBAdapter dbAdapter;    //データベースを操作するインスタンス
    private MyBaseAdapter myBaseAdapter;    //自身が持っているリストを変換
    private List<MyListItem> items;     //登録されたデータ
    private ListView onlyListView;   //一覧画面の登録されたデータ
    private MyListItem myListItem;  //登録されたデータの箱の箱

    private String[] columns = null;    //DBカラム
    Button battaleStart;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
        setContentView(R.layout.party_sort);
        getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.title_bar);

        TextView title = findViewById(R.id.title);
        title.setText("パーティー編成");

        dbAdapter = new DBAdapter(this);   //データベースに自身の情報を渡す
        items = new ArrayList<>();  //DBに登録されたリスト
        myBaseAdapter = new MyBaseAdapter(this, items);
        onlyListView = findViewById(R.id.listView1);

        loadMyList();
        battaleStart = findViewById(R.id.battaleStart);
        battaleStart.setText("このパーティーで開始("+myBaseAdapter.members2.size()+"/3)");
    }

    public void back(View view){
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
    }

    public void battale_start(View view){
        if(myBaseAdapter.members2.size() == 3) {
            Intent intent = new Intent(getApplicationContext(), BattaleStart.class);
            intent.putExtra("KEY_ENEMY", myBaseAdapter.members1);
            BattaleStart.items2 = myBaseAdapter.members2;
            startActivity(intent);
        }
        else{
            Context context = getApplicationContext();
            Toast.makeText(context, "パーティーを3人選んでください", Toast.LENGTH_LONG).show();
        }
    }

    private void loadMyList(){
        items.clear();
        dbAdapter.openDB();
        Cursor c = dbAdapter.getDB(columns);
        if(c.moveToFirst()){
            do{
                myListItem = new MyListItem(
                        c.getString(0),
                        c.getInt(1),
                        c.getInt(2),
                        c.getInt(3),
                        c.getInt(4),
                        c.getInt(5),
                        c.getInt(6),
                        c.getInt(7),
                        c.getInt(8));

                items.add(myListItem);

            } while(c.moveToNext());
        }
        c.close();
        dbAdapter.closeDB();
        onlyListView.setAdapter(myBaseAdapter);
        myBaseAdapter.notifyDataSetChanged();
    }

    public class MyBaseAdapter extends BaseAdapter {

        private Context context;
        private List<MyListItem> items2;

        ArrayList<MyListItem> members1 = new ArrayList<>();
        ArrayList<MyListItem> members2 = new ArrayList<>();

        private class ViewHolder{
            TextView text12Name;
            TextView text12Job;
            TextView text12Hp;
            TextView text12Mp;
            TextView text12Str;
            TextView text12Def;
            TextView text12Agi;
            CheckBox checkBox;
        }

        MyBaseAdapter(Context context,List<MyListItem> items){
            this.context = context;
            this.items2 = items;
        }

        @Override
        public int getCount(){
            return items2.size();
        }

        @Override
        public Object getItem(int position){
            return items2.get(position);
        }

        @Override
        public long getItemId(int position){
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            View view = convertView;
            final ViewHolder holder;

            myListItem = items2.get(position);

            if(view == null){
                LayoutInflater inflater =
                        (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                assert inflater != null;
                view = inflater.inflate(R.layout.check_list, parent, false);

                TextView text12Name = view.findViewById(R.id.text12Name);
                TextView text12Job = view.findViewById(R.id.text12Job);
                TextView text12Hp = view.findViewById(R.id.text12Hp);
                TextView text12Mp = view.findViewById(R.id.text12Mp);
                TextView text12Str = view.findViewById(R.id.text12Str);
                TextView text12Def = view.findViewById(R.id.text12Def);
                TextView text12Agi = view.findViewById(R.id.text12Agi);
                CheckBox checkBox = view.findViewById(R.id.checkbox);

                holder = new ViewHolder();
                holder.text12Name = text12Name;
                holder.text12Job = text12Job;
                holder.text12Hp = text12Hp;
                holder.text12Mp = text12Mp;
                holder.text12Str = text12Str;
                holder.text12Def = text12Def;
                holder.text12Agi = text12Agi;
                holder.checkBox = checkBox;
                view.setTag(holder);

            } else{
                holder = (ViewHolder) view.getTag();
            }

            String strJob = Integer.toString(myListItem.getJob());
            JobJudge judge = new JobJudge();
            strJob = judge.judge(strJob);
            String strHp = Integer.toString(myListItem.getHp());
            String strMp = Integer.toString(myListItem.getMp());
            String strStr = Integer.toString(myListItem.getStr());
            String strDef = Integer.toString(myListItem.getDef());
            String strAgi = Integer.toString(myListItem.getAgi());

            holder.text12Name.setText(myListItem.getName());
            holder.text12Job.setText(strJob);
            holder.text12Hp.setText(strHp);
            holder.text12Mp.setText(strMp);
            holder.text12Str.setText(strStr);
            holder.text12Def.setText(strDef);
            holder.text12Agi.setText(strAgi);

            holder.checkBox.setOnCheckedChangeListener(null);

            holder.checkBox.setChecked(myListItem.getChecked());

            holder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton cb, boolean isChecked) {
                    MyListItem listItem = (MyListItem) getItem(position);
                    listItem.setPosition(position);
                    if(members2.size() < 3 && isChecked) {
                        listItem.setChecked(isChecked);
                        holder.checkBox.setChecked(isChecked);
                        members2.add(listItem);

                    } else{
                        listItem.setChecked(false);
                        holder.checkBox.setChecked(false);

                        for(int i = 0; i < members2.size(); i++){
                            int po = members2.get(i).getPosition();

                            if(position == po){
                                members2.remove(i);
                            }
                        }
                    }

                    battaleStart = findViewById(R.id.battaleStart);
                    battaleStart.setText("このパーティーで開始("+myBaseAdapter.members2.size()+"/3)");

                }
            });
            return view;
        }
    }
}
