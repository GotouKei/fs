package com.example.myapplication1;

import java.util.Random;

//TODO 長いしわかりづらい
public class Priest extends Player {

    public Priest(CharaStatus item) {
        super(item);
    }

    public void Attack(Player enemy) {	//攻撃メソッド　　　引数は攻撃対象

        Random random = new Random();	//どの攻撃にするか乱数で決めるための準備
        int attackNumber = random.nextInt(4);	//どの攻撃にするか決めるための乱数
        int damage;		//攻撃ダメージ
        int hp = MaxHp;
        int restLife;   //相手のHP

        if(paralysis) {		//自身が麻痺状態なら読み込む
            int parseParalysis = (int)Math.round(Math.random() * 100);	//動けない確率
            if(parseParalysis >= 75) {
                paralysis();
                return;
            }
        }

        if(poison) {		//自身が毒状態なら読み込む
            int poison = poison();
            this.hp -= poison;
        }

        //以下、作戦実行時のそれぞれの処理

        if(GameManager.isMagicPriority) {	//魔法を優先して使用する作戦
            if(mp >= 20) {
                attackNumber = random.nextInt(3)+1;
            }
            else if(mp >= 10 && mp < 20) {
                attackNumber = random.nextInt(2)+2;
            }
            else {
                attackNumber = 0;
            }
        }

        else if(GameManager.isMagicSaving) {	//魔法を節約する作戦
            if(MaxMp == mp) {
                attackNumber= random.nextInt(4);
            }
            else {
                attackNumber = 0;
            }
        }

        else if(GameManager.isBalance) {    //バランス使用する作戦(前回の攻撃方法とかぶらないようにする)
            boolean isAttack = true;

            switch(attackNumber) {
                case 0:
                    attackNumber = random.nextInt(3)+1;
                    if(mp < 20) {
                        attackNumber = random.nextInt(2)+2;
                    }
                    break;
                case 1:
                    while(isAttack) {
                        attackNumber = random.nextInt(4);
                        if(attackNumber == 1) {	//1でケースと被ったら1以外が出るまでやる
                            isAttack = false;
                        }
                        else {
                            if(mp < 10) {	//MPが10よりしたなら魔法が使えないため通常攻撃
                                attackNumber = 0;
                                isAttack = false;
                            }
                            else{
                                isAttack =false;
                            }
                            break;
                        }
                    }
                case 2:
                    while(isAttack) {
                        attackNumber = random.nextInt(4);
                        if(attackNumber == 2) {	//2でケースと被ったら2以外が出るまでやる
                            break;
                        }
                        else {
                            if(mp < 20) {	//mpが20より下なら1にする
                                if(mp < 10) {
                                    attackNumber = 0;
                                    break;
                                }
                                else {
                                    attackNumber = random.nextInt(2);
                                    if(attackNumber == 1) {
                                        attackNumber = 3;
                                        break;
                                    }
                                    else{
                                        isAttack = false;
                                    }
                                }
                            }
                        }
                    }
            }

            if(mp < 10) {	//MPが10よりしたなら魔法が使えないため通常攻撃
                attackNumber = 0;
            }
        }

        else if(GameManager.isLifePriority) {	//命優先にする作戦
            if(mp >= 20 && hp != MaxHp) {
                attackNumber = 1;
            }
            else {
                while(true) {
                    attackNumber = random.nextInt(4);
                    if(attackNumber != 1) {
                        break;
                    }
                }
                if(mp < 10) {
                    attackNumber = 0;
                }
            }
        }

        else if(GameManager.isRandomStrategy) {	//ランダムに攻撃を選ぶ
            attackNumber = random.nextInt(3);	//どの攻撃方法かを決める乱数
        }


        if(attackNumber == 0) {			//通常攻撃
            boolean isBigHit = judgeIsBigHit();	//会心の一撃が発動するかどうか
            damage = str;
            hp = enemy.getHp();
            if(!isBigHit) {
                damage = enemy.getDef()- damage;
            }
            else {
                damage = damage * (-1);
            }

            if(damage > 0) {
                damage = -1;
            }
            damage *= -1;
            restLife = hp - damage;
            enemy.setHp(restLife);
            BattaleMain.stringBuilder.append(name + "の攻撃！");
            BattaleMain.stringBuilder.append("\n");
            if(isBigHit) {
                BattaleMain.stringBuilder.append("会心の一撃！");
                BattaleMain.stringBuilder.append("\n");
            }
            BattaleMain.stringBuilder.append(enemy.getName() + "に" + damage + "のダメージ！");
            BattaleMain.stringBuilder.append("\n");
        }

        else if(attackNumber == 1){		//回復メソッド呼び出し
            BattaleMain.stringBuilder.append(name + "は呪文を唱えた！");		//サンダー
            BattaleMain.stringBuilder.append("\n");
            this.hp += 50;
            mp -= 20;
            if(MaxHp <= this.hp) {	//HPが上限を超えてしまったら上限の数値に設定する
                this.hp = MaxHp;
            }
            BattaleMain.stringBuilder.append(name + "は回復した");
            BattaleMain.stringBuilder.append("\n");
            BattaleMain.stringBuilder.append(name + "のHPは" + this.hp);
            BattaleMain.stringBuilder.append("\n");
        }

        else if(attackNumber == 2){		//ポイズンメソッド
            mp -= 10;
            enemy.setPoison(true);
            BattaleMain.stringBuilder.append(name + "は呪文を唱えた！");		//サンダー
            BattaleMain.stringBuilder.append("\n");
            BattaleMain.stringBuilder.append(enemy.getName() + "を毒にした！");
            BattaleMain.stringBuilder.append("\n");
        }

        else if(attackNumber == 3) {		//麻痺にするメソッド
            mp -= 10;
            enemy.setParalysis(true);
            BattaleMain.stringBuilder.append(name + "は呪文を唱えた！");		//サンダー
            BattaleMain.stringBuilder.append("\n");
            BattaleMain.stringBuilder.append(enemy.getName() + "を麻痺にした！");
            BattaleMain.stringBuilder.append("\n");
        }
    }
}
