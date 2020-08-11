package com.example.myapplication1;

public class LifePriority extends Strategy{

    @Override
    public void strategy() {
        GameManager.isLifePriority = true;
        GameManager.isMagicPriority = false;
        GameManager.isMagicSaving = false;
        GameManager.isBalance = false;
        GameManager.isRandomStrategy = false;
    }
}
