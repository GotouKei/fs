package com.example.myapplication1;

import java.util.Random;

public class Wizard extends Player {

    private Random random = new Random();
    public Wizard(MyListItem item) {	//パーティーのナンバー
        super(item);		//mp , def , agi , パーティーナンバー
    }

    @Override
    protected void Attack(Player enemy) {	//攻撃メソッド　　引数は攻撃対象
        int attackNumber;	//どんな攻撃をするのかを乱数で決めるための変数
        int damage = 0;		//相手に与えるダメージ
        int restLife;		//相手の残りHP
        int hp;
        attackNumber = random.nextInt(3);

        if(paralysis) {		//麻痺状態か
            int parseParalysis = (int)Math.round(Math.random() * 100);	//麻痺状態の時に痺れる確率
            if(parseParalysis >= 75) {
                paralysis();		//痺れたら麻痺メソッドを呼び出す(Player)
                return;
            }
        }

        if(poison) {		//毒状態ならダメージを喰らう
            int poison = poison();
            this.hp -= poison;
        }

        //以下、作戦実行時のそれぞれの処理

        if(GameManager.isMagicPriority) {	//魔法を優先して使用する作戦
            if(mp >= 20) {
                attackNumber = random.nextInt(2);
            }
            else {
                attackNumber = 2;
            }
        }

        else if(GameManager.isMagicSaving) {	//魔法を節約する作戦
            if(MaxMp == mp) {
                attackNumber = random.nextInt(3);
            }
            else {
                attackNumber = 2;
            }
        }

        else if(GameManager.isBalance) {//バランス使用する作戦(前回の攻撃方法とかぶらないようにする)
            boolean isAttack = true;
            switch(attackNumber) {
                case 0:
                    attackNumber = random.nextInt(2)+1;
                    if(mp < 20) {
                        attackNumber = 2;
                    }
                    break;
                case 1:
                    while(isAttack) {
                        attackNumber = random.nextInt(3);
                        if(attackNumber == 1) {	//1でケースと被ったら1以外が出るまでやる
                            isAttack = false;
                        }
                        else {
                            if(mp < 10) {	//MPが10よりしたなら魔法が使えないため通常攻撃
                                attackNumber = 2;
                                isAttack = false;
                            }
                            break;
                        }
                    }
                case 2:
                    while(isAttack) {
                        attackNumber = random.nextInt(3);
                        if(attackNumber == 2) {	//2でケースと被ったら2以外が出るまでやる
                            isAttack = false;
                        }
                        else {
                            if(mp < 20) {	//mpが20より下なら1にする
                                attackNumber = 0;
                                break;
                            }
                        }
                    }
            }

            if(mp < 10) {	//MPが10よりしたなら魔法が使えないため通常攻撃
                attackNumber = 2;
            }
        }

        else if(GameManager.isLifePriority) {
            attackNumber = random.nextInt(3);	//どの攻撃方法かを決める乱数
            if(mp < 20) {
                while(true) {
                    if(attackNumber == 1) {
                        attackNumber = random.nextInt(3);
                    }
                    else {
                        if(attackNumber == 0) {
                            attackNumber = 2;
                        }
                        break;
                    }
                }
            }
            else if(mp < 10) {
                attackNumber = 2;
            }
        }

        else if(GameManager.isRandomStrategy) {	//ランダムに攻撃を選ぶ
            attackNumber = random.nextInt(3);	//どの攻撃方法かを決める乱数
            if(mp < 20) {
                while(true) {
                    if(attackNumber == 1) {
                        random.nextInt(3);
                    }
                    else {
                        break;
                    }
                }
            }
            else if(mp < 10) {
                attackNumber = 2;
            }
        }

        //以下、攻撃リスト
        if(attackNumber == 0) {
            BattaleMain.stringBuilder.append(name + "は呪文を唱えた！");        //ファイヤー
            BattaleMain.stringBuilder.append("\n");
            BattaleMain.stringBuilder.append("ファイヤー");
            damage = fire();
            hp = enemy.getHp();
            restLife = hp - damage;
            enemy.setHp(restLife);

        }else if(attackNumber == 1){
            BattaleMain.stringBuilder.append(name + "は呪文を唱えた！");		//サンダー
            BattaleMain.stringBuilder.append("\n");
            BattaleMain.stringBuilder.append("サンダー");
            BattaleMain.stringBuilder.append("\n");
            damage = thunder();
            hp = enemy.getHp();
            restLife = hp - damage;
            enemy.setHp(restLife);
        }

        else if(attackNumber == 2){		//通常攻撃
            boolean isBigHit = judgeIsBigHit();	//会心の一撃が発動するかどうか
            damage = str;
            hp = enemy.getHp();

            if(!isBigHit) {
                damage = enemy.getDef() - damage;
            }
            else {
                damage = damage * (-1);
            }

            if(damage > 0) {	//ダメージが0より上ということは防御の方が大きいという事になるので1ダメージ
                damage = -1;
            }
            damage *=  -1;
            restLife = hp - damage;
            BattaleMain.stringBuilder.append(name + "の攻撃！");
            BattaleMain.stringBuilder.append("\n");
            if(isBigHit) {
                BattaleMain.stringBuilder.append("会心の一撃！");
                BattaleMain.stringBuilder.append("\n");
            }
            enemy.setHp(restLife);
        }

        BattaleMain.stringBuilder.append(enemy.getName() + "に" + damage + "のダメージ！");
        BattaleMain.stringBuilder.append("\n");
    }

    protected int fire() {		//ファイヤー攻撃
        int damage = random.nextInt(20) + 10;
        mp -= 10;
        return damage;
    }

    protected int thunder() {		//サンダー攻撃
        int damage = random.nextInt(20) + 10 ;
        mp -= 20;
        return damage;
    }
}
