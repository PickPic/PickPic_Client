package com.pickpic.Adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.pickpic.Item.DirectoryTabListViewItem;
import com.pickpic.Item.TagTabListViewItem;
import com.pickpic.R;

import java.util.ArrayList;

public class TagTabListViewAdapter extends BaseAdapter{
    private ArrayList<TagTabListViewItem> tagTabListViewItem = new ArrayList<TagTabListViewItem>();

    public TagTabListViewAdapter(){

    }

    @Override
    public int getCount(){
        return tagTabListViewItem.size();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        final int pos = position;
        final Context context = parent.getContext();

        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.tag_tab_listview_item, parent, false);
        }

        TextView titleTextView = (TextView) convertView.findViewById(R.id.tag_title);

        TagTabListViewItem listViewItem = tagTabListViewItem.get(position);

        titleTextView.setText(listViewItem.getTagValue());

        return  convertView;
    }

    @Override
    public long getItemId(int position){
        return position;
    }

    @Override
    public Object getItem(int position) {
        return tagTabListViewItem.get(position) ;
    }

    public void addItem(TagTabListViewItem item) {
        tagTabListViewItem.add(item);
    }
}
