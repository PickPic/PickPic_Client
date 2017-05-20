package com.pickpic.Adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.pickpic.Item.RecommendTagListItem;
import com.pickpic.R;

import java.util.ArrayList;

/**
 * Created by Kangsoyeon on 2017. 5. 20..
 */

public class RecommendTagListAdapter extends BaseAdapter{

    Context context;
    private ArrayList<RecommendTagListItem> recommendTagItemList = new ArrayList<RecommendTagListItem>();

    public RecommendTagListAdapter(Context context, ArrayList<RecommendTagListItem> recommendTagItemList){
        this.context = context;
        this.recommendTagItemList = recommendTagItemList;

    }

    @Override
    public int getCount(){
        return recommendTagItemList.size();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final int pos = position;
        final Context context = parent.getContext();

        if(convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.recommend_tag_list_item, parent, false);
        }

        TextView tagTextView = (TextView) convertView.findViewById(R.id.singletag);

        RecommendTagListItem recommendItem = recommendTagItemList.get(position);

        tagTextView.setText(recommendItem.getTag());

        return convertView;

    }

    @Override
    public long getItemId(int position){
        return position;
    }

    @Override
    public Object getItem(int position) {
        return recommendTagItemList.get(position);
    }



}
