package com.example.myapplication1;

public class Balance implements Strategy{

    @Override
    public void strategy() {

        GameManager.isBalance = true;
        GameManager.isMagicPriority = false;
        GameManager.isMagicSaving = false;
        GameManager.isLifePriority = false;
        GameManager.isRandomStrategy = false;
    }
}

