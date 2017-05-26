package com.pickpic.Item;

public class GridViewItem {

    private long thumbnail;
    private String path;

    public void setPath(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }

    public void setThumbnail(long thumbnail) {
        this.thumbnail = thumbnail;
    }

    public long getThumbnail() {
        return thumbnail;
    }
  
}

