package com.pickpic.Item;

/**
 * Created by Kangsoyeon on 2017. 5. 20..
 */

public class RecommendTagListItem {

    private String recommendtag;

    public RecommendTagListItem(String tag) {this.recommendtag = tag;}

    public void setTag(String tag) {
        recommendtag = tag ;
    }

    public String getTag(){
        return this.recommendtag;
    }
}
