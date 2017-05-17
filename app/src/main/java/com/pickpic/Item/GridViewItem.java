package com.pickpic.Item;

import android.graphics.Bitmap;

/**
 * Created by sekyo on 2017-05-13.
 */

public class GridViewItem {
    private Bitmap thumbnail;
    private String path;
    private String date;

    public void setDate(String date) {
        this.date = date;
    }

    public String getDate() {

        return date;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;

    }

    public void setThumbnail(Bitmap thumbnail) {
        this.thumbnail = thumbnail;
    }

    public Bitmap getThumbnail() {
        return thumbnail;
    }

}
