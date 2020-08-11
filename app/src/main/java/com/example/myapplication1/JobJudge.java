package com.example.myapplication1;

public class JobJudge {

    public String judge(String job){
        String strJob;
        switch(job){
            case "0":
                strJob = "戦士";
                break;
            case "1":
                strJob = "魔法使い";
                break;
            case "2":
                strJob = "僧侶";
                break;
            default:
                strJob = "エラー";
                break;
        }
        return strJob;
    }
}
