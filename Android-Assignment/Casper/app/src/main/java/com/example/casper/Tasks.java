package com.example.casper;

public class Tasks {
    private int imageRes;
    private String text;

    public Tasks(int imageRes, String text){
        this.imageRes = imageRes;
        this.text = text;
    }

    public void select(){
        text = "Done";
    }


    public int getImageRes() {
        return imageRes;
    }

    public String getText() {
        return text;
    }
}


