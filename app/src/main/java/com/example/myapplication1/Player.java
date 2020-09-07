package com.example.myapplication1;

abstract class Player {
    String name;
    int job;
    int hp;
    int mp;
    int str;
    int def;
    int luck;
    int agi;
    int partyNumber;
    boolean poison = false;
    boolean paralysis = false;
    int MaxHp;
    int MaxMp;

    public Player(CharaStatus item) {

        name = item.getName();
        job = item.getJob();
        hp = item.getHp();
        str = item.getStr();
        mp = item.getMp();
        def = item.getDef();
        luck = item.getLuck();
        agi = item.getAgi();
        this.MaxHp = hp;
        this.MaxMp = mp;
    }

    abstract void Attack(Player aite) ;

    public boolean judgeIsBigHit() {
        boolean isBigHit;

        int bigHitNumber = (int)Math.round(luck * Math.random());
        if(bigHitNumber <= 45) {
            isBigHit = false;
        }
        else {
            isBigHit = true;
        }
        return isBigHit;
    }

    public void setPartyNumber(int partyNumber){
        this.partyNumber = partyNumber;
    }

    public int getPartyNumber(){
        return partyNumber;
    }

    public int getJob(){
        return job;
    }

    public String getName() {
        return name;
    }

    public int getHp() {
        return hp;
    }

    public int getMp(){
        return mp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public String toString() {
        StringBuffer string = new StringBuffer();
        string.append(name+",");
        return string.toString();
    }

    public int getAgi() {
        return agi;
    }

    public int getDef() {
        return def;
    }

    public void setPoison(boolean poison) {
        this.poison = poison;
    }

    public boolean getPoison() {
        return poison;
    }

    public int poison() {
        int poisonDamage = 0;
        if(poison == true) {
            poisonDamage = 20;
            BattaleMain.stringBuilder.append(name + "が" + poisonDamage + "の毒ダメージを受けた！");
            BattaleMain.stringBuilder.append("\n");
        }
        return poisonDamage;
    }

    public void paralysis() {
        if(paralysis == true) {
            BattaleMain.stringBuilder.append("痺れて動けなかった！");
            BattaleMain.stringBuilder.append("\n");
        }
    }

    public void setParalysis(boolean paralysis) {
        this.paralysis = paralysis;
    }

    public boolean getParalysis() {
        return paralysis;
    }



}
