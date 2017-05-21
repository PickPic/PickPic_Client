package com.pickpic.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.pickpic.Item.GridViewItem;
import com.pickpic.R;

import java.util.ArrayList;

/**
 * Created by Kangsoyeon on 2017. 5. 11..
 */

public class SearchResultAdapter extends BaseAdapter{
    private ArrayList<GridViewItem> gridViewItem = new ArrayList<GridViewItem>();

    @Override
    public int getCount() { return gridViewItem.size(); }

    public SearchResultAdapter(){

    }
    public View getView(int position, View convertView, ViewGroup parent){
        final int pos = position;
        final Context context = parent.getContext();

        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.search_result_item, parent, false);
        }
        ImageView iconImageView = (ImageView) convertView.findViewById(R.id.time_thumbnail);
        iconImageView.setScaleType(ImageView.ScaleType.FIT_XY);
        GridViewItem searchResultItem = gridViewItem.get(position);

       // iconImageView.setImageBitmap(searchResultItem.getThumbnail());

        return convertView;
    }

    public long getItemId(int position) { return position; }
    public Object getItem(int position) { return gridViewItem.get(position);}
    public void addItem(GridViewItem item) { gridViewItem.add(item);}


}
