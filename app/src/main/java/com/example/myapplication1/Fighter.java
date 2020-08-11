package com.example.myapplication1;

public class Fighter extends Player {

    public Fighter(MyListItem item) {
        super(item);
    }

    @Override
    protected void Attack(Player enemy) {
        boolean isBigHit= judgeIsBigHit();
        int damage = agi;
        int hp = enemy.getHp();

        if(!isBigHit) {
            damage = enemy.getDef() - damage;
        }
        else {
            damage = damage * (-1);
        }
        if(paralysis) {
            int parseParalysis = (int)Math.round(Math.random() * 100);
            if( parseParalysis>= 75) {
                paralysis();
                return;
            }
        }

        if(poison) {	//毒状態かどうか
            int poison = poison();
            this.hp -= poison;
        }

        if(damage > 0) {
            damage = -1;
        }

        damage *= -1;
        int restLife = hp - damage;
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
}
