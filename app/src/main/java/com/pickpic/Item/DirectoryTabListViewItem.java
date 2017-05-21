package com.pickpic.Item;

import android.graphics.Bitmap;

public class DirectoryTabListViewItem {
  
    private long thumbnail;
    private String name = null;
    private String id = null;
    private int count = 1;

    public void upCount(){
        count++;
    }
    public void setThumbnail(long thumbnail) {
        this.thumbnail = thumbnail;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public long getThumbnail() {
        return thumbnail;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public int getCount() {
        return count;
    }
}
