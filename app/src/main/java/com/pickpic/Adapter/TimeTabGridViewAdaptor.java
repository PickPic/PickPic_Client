package com.pickpic.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import com.pickpic.Item.GridViewItem;
import com.pickpic.R;

import java.util.ArrayList;

public class TimeTabGridViewAdaptor extends BaseAdapter{
    private ArrayList<GridViewItem> gridViewItem = new ArrayList<GridViewItem>();

    @Override
    public int getCount(){
        return gridViewItem.size();
    }

    public TimeTabGridViewAdaptor(){

    }

    public View getView(int position, View convertView, ViewGroup parent){

        final int pos = position;
        final Context context = parent.getContext();

        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.time_tab_gridview_item, parent, false);
        }

        ImageView iconImageView = (ImageView) convertView.findViewById(R.id.time_thumbnail);
        iconImageView.setScaleType(ImageView.ScaleType.FIT_XY);
        GridViewItem timeTabGridViewItem = gridViewItem.get(position);

        iconImageView.setImageBitmap(timeTabGridViewItem.getThumbnail());

        return  convertView;
    }
    public long getItemId(int position){
        return position;
    }

    public Object getItem(int position) {
        return gridViewItem.get(position) ;
    }

    public void addItem(GridViewItem item) {
        gridViewItem.add(item);
    }

}
