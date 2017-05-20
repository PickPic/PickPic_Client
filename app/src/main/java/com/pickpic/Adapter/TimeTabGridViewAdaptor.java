package com.pickpic.Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.media.Image;
import android.os.AsyncTask;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.pickpic.Backend.ThumbnailManager;
import com.pickpic.Item.GridViewItem;
import com.pickpic.R;

import java.net.URL;
import java.util.ArrayList;

public class TimeTabGridViewAdaptor extends BaseAdapter {
    private ArrayList<GridViewItem> gridViewItems = new ArrayList<GridViewItem>();

    @Override
    public int getCount() {
        return gridViewItems.size();
    }

    public TimeTabGridViewAdaptor() {
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        final int pos = position;
        final Context context = parent.getContext();

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.time_tab_gridview_item, parent, false);
        }

        ImageView iconImageView = (ImageView) convertView.findViewById(R.id.time_thumbnail);
        iconImageView.setScaleType(ImageView.ScaleType.FIT_XY);
        GridViewItem gridViewItem = gridViewItems.get(position);

        new ThumbnailManager(iconImageView, gridViewItem.getThumbnail(),parent.getContext()).executeOnExecutor(AsyncTask.SERIAL_EXECUTOR);

        return convertView;
    }

    public long getItemId(int position) {
        return position;
    }

    public Object getItem(int position) {
        return gridViewItems.get(position);
    }

    public void addItem(GridViewItem item) {
        gridViewItems.add(item);
    }

}
