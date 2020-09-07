package com.example.myapplication1;

import java.io.Serializable;

public class CharaStatus implements Serializable {
    private String name;
    private int job;
    private int hp;
    private int mp;
    private int str;
    private int def;
    private int agi;
    private int luck;
    private long create_at;
    private boolean isChecked;
    private int position;

    CharaStatus(String name, int job, int hp, int mp, int str, int def, int agi, int luck, long create_at) {
        this.name = name;
        this.job = job;
        this.hp = hp;
        this.mp = mp;
        this.str = str;
        this.def = def;
        this.agi = agi;
        this.luck = luck;
        this.create_at = create_at;
    }

    public String getName() {
        return this.name;
    }

    int getJob() {
        return this.job;
    }

    public int getHp() {
        return this.hp;
    }

    public int getMp() {
        return this.mp;
    }

    public int getStr() {
        return this.str;
    }

    public int getDef() {
        return this.def;
    }

    public int getAgi() {
        return this.agi;
    }

    public int getLuck() {
        return this.luck;
    }

    long getCreate_At() {
        return this.create_at;
    }

    public void setChecked(boolean isChecked){
        this.isChecked = isChecked;
    }

    public boolean getChecked(){
        return isChecked;
    }

    public void setPosition(int position){
        this.position = position;
    }

    public int getPosition(){
        return position;
    }
}
