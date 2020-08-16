package com.example.myapplication1;

public class MagicPriority implements Strategy{

    @Override
    public void strategy() {
        GameManager.isMagicPriority = true;
        GameManager.isLifePriority = false;
        GameManager.isBalance = false;
        GameManager.isMagicSaving = false;
        GameManager.isRandomStrategy = false;
    }
}
