package com.example.myapplication1;

import android.app.Activity;
import android.content.Intent;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;

public class GameManager extends Activity {

    String strategy;
    static int End;

    private Random random = new Random();

    static ArrayList<Player> startPlayer; //攻撃する順番を入れるリスト

    Party party1 = new Party(1);	//パーティ種類(引数はパーティの種類ナンバー)
    Party party2 = new Party(2);

    int attackOrderNo;	//現在、攻撃する順番の何番目かを格納する変数

    Player attacker;	//これから攻撃するキャラ
    Player player;

    static boolean isMagicPriority;	//どの作戦を使うのかをboolean型で判定
    static boolean isBalance;
    static boolean isMagicSaving;
    static boolean isLifePriority;
    static boolean isRandomStrategy;

    Player enemy;	//攻撃対象

    //TODO
    int count1 = 0;		//パーティー1の生き残りキャラ数をカウントする
    int count2 = 0;

    int attackNumber = 0;	//どのキャラを攻撃するのか決める時に乱数を入れるための変数

    int count;	//何番目の人が攻撃しているのかを他のクラスからわかるようにする

    //TODO iteratorパターン partyもできれば汎用性持たせたい、パーティがたくさんになっても使えるように
    public void prepareGame(ArrayList<CharaStatus> items, int partyNumber) {
        for (int charaNumber = 0; charaNumber < items.size(); charaNumber++) {

            Player p = instanceFactory(items.get(charaNumber));
            p.setPartyNumber(partyNumber);
            if(partyNumber == 1) {
                party1.addPlayer(p);
            }
            else if(partyNumber == 2){
                party2.addPlayer(p);
            }
        }

        //TODO ここで下のprepare呼び出したい、分けるのはいいんだけど長いから改善、上の流れで値は問題ないがキーに柔軟性を持たせる
    }

    private Player instanceFactory(CharaStatus item){
        Player character;
        switch(item.getJob()){
            case 0:
                character = new Fighter(item);
                break;
            case 1:
                character = new Wizard(item);
                break;
            case 2:
                character = new Priest(item);
                break;
            default:
                character = new Fighter(item);
        }
        return character;
    }

    public Intent prepareGame() {

        Intent intent = new Intent();

        //FIXME
        intent.putExtra("KEY_NAME1",p1.getName());
        intent.putExtra("KEY_HP1", p1.getHp());
        intent.putExtra("KEY_MP1", p1.getMp());
        intent.putExtra("KEY_POISON1", p1.getPoison());
        intent.putExtra("KEY_PARALYSIS1", p1.getParalysis());
        intent.putExtra("KEY_NAME2",p2.getName());
        intent.putExtra("KEY_HP2", p2.getHp());
        intent.putExtra("KEY_MP2", p2.getMp());
        intent.putExtra("KEY_POISON2", p2.getPoison());
        intent.putExtra("KEY_PARALYSIS2", p2.getParalysis());
        intent.putExtra("KEY_NAME3",p3.getName());
        intent.putExtra("KEY_HP3", p3.getHp());
        intent.putExtra("KEY_MP3", p3.getMp());
        intent.putExtra("KEY_POISON3", p3.getPoison());
        intent.putExtra("KEY_PARALYSIS3", p3.getParalysis());
        intent.putExtra("KEY_NAME4",p4.getName());
        intent.putExtra("KEY_HP4", p4.getHp());
        intent.putExtra("KEY_MP4", p4.getMp());
        intent.putExtra("KEY_POISON4", p4.getPoison());
        intent.putExtra("KEY_PARALYSIS4", p4.getParalysis());
        intent.putExtra("KEY_NAME5",p5.getName());
        intent.putExtra("KEY_HP5", p5.getHp());
        intent.putExtra("KEY_MP5", p5.getMp());
        intent.putExtra("KEY_POISON5", p5.getPoison());
        intent.putExtra("KEY_PARALYSIS5", p5.getParalysis());
        intent.putExtra("KEY_NAME6",p6.getName());
        intent.putExtra("KEY_HP6", p6.getHp());
        intent.putExtra("KEY_MP6", p6.getMp());
        intent.putExtra("KEY_POISON6", p6.getPoison());
        intent.putExtra("KEY_PARALYSIS6", p6.getParalysis());
        return intent;
    }

    public void startGame(){

        startPlayer = new ArrayList<>();

        BattaleMain.stringBuilder.append("バトルを開始します！");
        BattaleMain.stringBuilder.append("\n");

        //party受け取る引数
        addMember(party1);		//攻撃する順番を決めるためのリストにパーティーメンバーを入れていく
        addMember(party2);

        //攻撃順決め
        Collections.sort(startPlayer, new java.util.Comparator<Player>(){
            @Override
            public int compare(Player c1, Player c2) {
                if(c1.getAgi() < c2.getAgi()) {
                    return 1;
                }
                else if(c1.getAgi() > c2.getAgi()) {
                    return -1;
                }
                else {
                    return c1.getName().compareTo(c2.getName());
                }
            }
        });
    }

    public void game() {
        mainStrategy();		//作戦選択
        for(int reakAttaker = 0; reakAttaker < startPlayer.size(); reakAttaker++) {
            attacker = startPlayer.get(reakAttaker);	//素早さが早い人から攻撃者になる

            count = reakAttaker;		//何番目の人が攻撃しているか取得
            boolean isEnterAttacker = true;

            while(isEnterAttacker) {
                attackNumber = random.nextInt(startPlayer.size());	//攻撃対象を選択
                enemy = startPlayer.get(attackNumber);

                if(attackNumber != reakAttaker) {		//相手のパーティーのキャラになるまでループ
                    if(enemy.getPartyNumber() != attacker.getPartyNumber()) {	//自分のパーティーナンバーと違ったら攻撃可
                        attackOrderNo = attackNumber;	//現在、攻撃する順番の何番目かを格納する
                        isEnterAttacker = false;
                    }
                }
            }

            enemy = startPlayer.get(attackNumber);

            attacker.Attack(enemy);	//攻撃者の攻撃メソッド呼び出し

            if(enemy.getHp() <= 0) { //相手のHPが0以下なら
                Player p = startPlayer.remove(attackNumber);	//死んだキャラは攻撃者リストから取り除く
                p.setHp(0);
                BattaleMain.stringBuilder.append(p.getName() + "は死んだ！");
                BattaleMain.stringBuilder.append("\n");
            }
            if(attacker.getHp() <= 0) {		//攻撃者のHPが0以下なら
                Player p = startPlayer.remove(count);	//死んだキャラは攻撃者リストから取り除く
                p.setHp(0);
                BattaleMain.stringBuilder.append(p.getName() + "は死んだ！");
                BattaleMain.stringBuilder.append("\n");
            }

            count1 = 0;		//パーティー1の生き残りキャラ数をリセット
            count2 = 0;

            for(int g = 0; g < startPlayer.size(); g++) {	//パーティーの生き残りメンバー数を確かめ、勝ち負け判定
                player = startPlayer.get(g);
                int disPartyNum = player.getPartyNumber();
                if( disPartyNum == 1) {
                    count1++;
                }
                else if( disPartyNum == 2) {
                    count2++;
                }
            }

            if(count1 == 0 && count2 > 0) {
                BattaleMain.stringBuilder.append("パーティ１の負け！");
                BattaleMain.stringBuilder.append("\n");
                End = 1;
                break;
            }
            else if(count2 == 0 && count1 > 0) {
                BattaleMain.stringBuilder.append("パーティ2の負け！");
                BattaleMain.stringBuilder.append("\n");
                End = 2;
                break;
            }
            else if(count1 == 0 && count2 == 0){
                BattaleMain.stringBuilder.append("引き分け");
                BattaleMain.stringBuilder.append("\n");
                End = 3;
            }
        }
    }

    public void addMember(Party party) {
        for(int i = 0; i < party.getSize(); i++) {
            startPlayer.add(party.getMember(i)); //攻撃順番を決めるためにリストにパーティのメンバーを入れる
        }
    }

    public void mainStrategy() {

        //TODO 別の止める方法
        Scanner scanner = new Scanner(System.in);
        strategy = BattaleMain.strategy1;

        while (true) {
            if (strategy == null || BattaleMain.isEnter == false) {
                strategy = scanner.nextLine();
            } else {
                strategy = BattaleMain.strategy1;
                BattaleMain.isEnter = true;
                break;
            }
        }
        boolean isStrategy = true;

        //TODO 分岐なし　なんか判断する場所かなんかで行けそう
        while (isStrategy) {
            switch (strategy) {
                case "ランダム":
                    isStrategy = false;
                    new Strategy(){
                        @Override
                        public void strategy(){
                            isMagicPriority = false;
                            isLifePriority = false;
                            isBalance = false;
                            isMagicSaving = false;
                            isRandomStrategy = true;
                        }
                    };
                    break;
                case "命優先":
                    isStrategy = false;
                    new Strategy(){
                        @Override
                        public void strategy(){
                            isLifePriority = true;
                            isMagicPriority = false;
                            isMagicSaving = false;
                            isBalance = false;
                            isRandomStrategy = false;
                        }
                    };
                    break;
                case "魔法節約":
                    isStrategy = false;
                    new Strategy(){
                        @Override
                        public void strategy(){
                            GameManager.isMagicPriority = false;
                            GameManager.isBalance = false;
                            GameManager.isLifePriority = false;
                            GameManager.isMagicSaving = true;
                            GameManager.isRandomStrategy = false;
                        }
                    };
                    break;
                case "魔法優先":
                    isStrategy = false;
                    new Strategy(){
                        @Override
                        public void strategy(){
                            GameManager.isMagicPriority = true;
                            GameManager.isLifePriority = false;
                            GameManager.isBalance = false;
                            GameManager.isMagicSaving = false;
                            GameManager.isRandomStrategy = false;
                        }
                    };
                    break;
                case "バランスよく":
                    isStrategy = false;
                    new Strategy(){
                        @Override
                        public void strategy(){
                            GameManager.isBalance = true;
                            GameManager.isMagicPriority = false;
                            GameManager.isMagicSaving = false;
                            GameManager.isLifePriority = false;
                            GameManager.isRandomStrategy = false;
                        }
                    };
                    break;
                default:
                    BattaleMain.stringBuilder.append("選択肢から選んでください！");
                    break;
            }
        }
    }
}
