package com.example.myapplication1;

public class MagicPriority extends Strategy{

    @Override
    public void strategy() {
        GameManager.isMagicPriority = true;
        GameManager.isLifePriority = false;
        GameManager.isBalance = false;
        GameManager.isMagicSaving = false;
        GameManager.isRandomStrategy = false;
    }
}
