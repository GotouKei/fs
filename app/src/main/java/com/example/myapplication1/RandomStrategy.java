package com.example.myapplication1;

public class RandomStrategy implements Strategy{

    @Override
    public void strategy() {
        GameManager.isMagicPriority = false;
        GameManager.isLifePriority = false;
        GameManager.isBalance = false;
        GameManager.isMagicSaving = false;
        GameManager.isRandomStrategy = true;
    }
}
