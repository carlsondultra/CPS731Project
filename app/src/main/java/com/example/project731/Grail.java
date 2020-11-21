package com.example.project731;

import android.content.Context;

public class Grail {
    private String userId;
    private String shoePicDrawable;
    private String realName;

    public Grail(String userId, String realName, String shoePicDrawable){
        this.userId = userId;
        this.realName = realName;
        this.shoePicDrawable = shoePicDrawable;

    }
    public Grail(){
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getShoePicDrawable() {
        return shoePicDrawable;
    }

    public void setShoePicDrawable(String shoePicDrawable) {
        this.shoePicDrawable = shoePicDrawable;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }
}
