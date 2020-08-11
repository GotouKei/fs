package com.example.myapplication1;

public class MagicSaving extends Strategy{

    @Override
    public void strategy() {
        GameManager.isMagicPriority = true;
        GameManager.isBalance = false;
        GameManager.isLifePriority = false;
        GameManager.isMagicSaving = false;
        GameManager.isRandomStrategy = false;

    }
}
