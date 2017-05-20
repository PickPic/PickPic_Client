package com.pickpic.Adapter;

import android.content.Context;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.pickpic.Backend.ThumbnailManager;
import com.pickpic.Item.DirectoryTabListViewItem;
import com.pickpic.R;


import java.util.ArrayList;


public class DirectoryTabListViewAdaptor extends BaseAdapter{
    private ArrayList<DirectoryTabListViewItem> directoryTabListViewItem = new ArrayList<DirectoryTabListViewItem>();

    public DirectoryTabListViewAdaptor(){

    }

    @Override
    public int getCount(){
        return directoryTabListViewItem.size();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        final int pos = position;
        final Context context = parent.getContext();

        if(convertView == null){
           LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.directory_tab_listview_item, parent, false);

        }

        ImageView iconImageView = (ImageView) convertView.findViewById(R.id.directory_thumbnail);
        TextView titleTextView = (TextView) convertView.findViewById(R.id.directory_title);
        TextView cntTextView = (TextView) convertView.findViewById(R.id.directory_cnt);

        DirectoryTabListViewItem listViewItem = directoryTabListViewItem.get(position);

        new ThumbnailManager(iconImageView, listViewItem.getThumbnail(),parent.getContext()).executeOnExecutor(AsyncTask.SERIAL_EXECUTOR);
        titleTextView.setText(listViewItem.getName());
        cntTextView.setText(listViewItem.getCount() + "");

        return  convertView;
    }
    @Override
    public long getItemId(int position){
        return position;
    }

    @Override
    public Object getItem(int position) {
        return directoryTabListViewItem.get(position) ;
    }

    public void addItem(DirectoryTabListViewItem item) {
        directoryTabListViewItem.add(item);
    }

}
