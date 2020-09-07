package com.example.myapplication1;

import java.util.ArrayList;
import java.util.Comparator;

//TODO 色がおかしいところを色の意味を調べて直す
public class Party {
    private ArrayList<Player> members;
    private int partyNumber;
    private Player player;
    Party(int partyNumber){
        this.partyNumber = partyNumber;
        members = new ArrayList();
    }

    public void addPlayer(Player player) {
        members.add(player);
    }

    public Player getMember(int number) {
        player = members.get(number);
        return player;
    }

    //TODO
    public int getSize() {
        int count  = members.size();
        return count;
    }
}
