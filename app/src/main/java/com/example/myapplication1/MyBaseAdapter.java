package com.example.myapplication1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class MyBaseAdapter extends BaseAdapter {

    private Context context;
    private List<MyListItem> items;

    private class ViewHolder{
        TextView text12Name;
        TextView text12Job;
        TextView text12Hp;
        TextView text12Mp;
        TextView text12Str;
        TextView text12Def;
        TextView text12Agi;
    }

    MyBaseAdapter(Context context, List<MyListItem> items){
        this.context = context;
        this.items = items;
    }

    @Override
    public int getCount(){
        return items.size();
    }

    @Override
    public Object getItem(int position){
        return items.get(position);
    }

    @Override
    public long getItemId(int position){
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        ViewHolder holder;

        MyListItem myListItem = items.get(position);

        if(view == null){
            LayoutInflater inflater =
                    (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            assert inflater != null;
            view = inflater.inflate(R.layout.row_sheet_list_view, parent, false);

            TextView text12Name = view.findViewById(R.id.text12Name);
            TextView text12Job = view.findViewById(R.id.text12Job);
            TextView text12Hp = view.findViewById(R.id.text12Hp);
            TextView text12Mp = view.findViewById(R.id.text12Mp);
            TextView text12Str = view.findViewById(R.id.text12Str);
            TextView text12Def = view.findViewById(R.id.text12Def);
            TextView text12Agi = view.findViewById(R.id.text12Agi);

            holder = new ViewHolder();
            holder.text12Name = text12Name;
            holder.text12Job = text12Job;
            holder.text12Hp = text12Hp;
            holder.text12Mp = text12Mp;
            holder.text12Str = text12Str;
            holder.text12Def = text12Def;
            holder.text12Agi = text12Agi;
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

        return view;
    }
}
