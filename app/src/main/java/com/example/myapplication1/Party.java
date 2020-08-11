package com.example.myapplication1;

import java.util.ArrayList;

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

    public int getPartyNumber() {
        return partyNumber;
    }

    public Player getMember(int ran) {
        player = members.get(ran);
        return player;
    }

    public String toString() {
        StringBuffer string = new StringBuffer();
        for(int i = 0; i < members.size(); i++) {
            string.append(members.get(i));
            string.append("");
        }
        return string.toString();
    }

    public int getSize() {
        int i  = members.size();
        return i;
    }
}
