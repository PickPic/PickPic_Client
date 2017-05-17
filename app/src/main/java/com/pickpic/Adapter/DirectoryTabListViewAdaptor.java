package com.pickpic.Adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.pickpic.Item.DirectoryTabListViewItem;
import com.pickpic.R;

import org.w3c.dom.Text;

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

        iconImageView.setImageDrawable(listViewItem.getIcon());
        titleTextView.setText(listViewItem.getTitle());
        cntTextView.setText(listViewItem.getDesc());

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

    public void addItem(Drawable icon, String title, String desc) {
        DirectoryTabListViewItem item = new DirectoryTabListViewItem();

        item.setIcon(icon);
        item.setTitle(title);
        item.setDesc(desc);

        directoryTabListViewItem.add(item);
    }

}
