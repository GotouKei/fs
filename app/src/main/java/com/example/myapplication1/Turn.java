package com.example.myapplication1;

import java.util.Comparator;

public class Turn implements Comparator<Player>{
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

}
