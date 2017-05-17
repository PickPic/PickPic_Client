package com.pickpic.Item;

import android.graphics.drawable.Drawable;

/**
 * Created by user on 2017-05-11.
 */

public class DirectoryTabListViewItem {
    private Drawable iconDrawable;
    private String titleStr;
    private String descStr;

    public void setIcon(Drawable icon){
        iconDrawable = icon;
    }
    public void setTitle(String title){
        titleStr = title;
    }
    public  void setDesc(String desc){
        descStr = desc;
    }

    public Drawable getIcon(){
        return this.iconDrawable;
    }
    public String getTitle(){
        return this.titleStr;
    }
    public String getDesc(){
        return this.descStr;
    }

}
