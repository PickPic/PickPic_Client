package com.pickpic.Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.pickpic.Item.GridViewItem;
import com.pickpic.R;

import java.util.ArrayList;

public class TimeTabGridViewAdaptor extends BaseAdapter {
    private ArrayList<GridViewItem> gridViewItems = new ArrayList<GridViewItem>();
    private GridView gridView;
    private int gridViewScrollState = 0;
    private int beforeGridViewScrollState = 0;
    private Bitmap bitmap;

    @Override
    public int getCount() {
        return gridViewItems.size();
    }

    public TimeTabGridViewAdaptor(final GridView gridView, Bitmap bitmap) {
        this.gridView = gridView;
        this.bitmap = bitmap;
        gridView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                beforeGridViewScrollState = gridViewScrollState;
                gridViewScrollState = scrollState;
                if (AbsListView.OnScrollListener.SCROLL_STATE_IDLE == scrollState) {
                    if(AbsListView.OnScrollListener.SCROLL_STATE_FLING == beforeGridViewScrollState)
                        gridView.invalidateViews();
                }
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

            }
        });
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView = null;
        final int pos = position;
        final Context context = parent.getContext();

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.time_tab_gridview_item, parent, false);
            imageView = (ImageView) convertView.findViewById(R.id.time_thumbnail);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            convertView.setTag(imageView);
        } else {
            imageView = (ImageView) convertView.getTag();
        }

        GridViewItem gridViewItem = gridViewItems.get(position);

        if (gridViewScrollState == AbsListView.OnScrollListener.SCROLL_STATE_IDLE) {
            new TimeTabGridViewThumbnailManager(imageView, gridViewItem.getThumbnail(), parent.getContext(), gridView, position).executeOnExecutor(AsyncTask.SERIAL_EXECUTOR);
        } else if (gridViewScrollState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL) {
            imageView.setImageBitmap(bitmap);
            new TimeTabGridViewThumbnailManager(imageView, gridViewItem.getThumbnail(), parent.getContext(), gridView, position).executeOnExecutor(AsyncTask.SERIAL_EXECUTOR);
        } else {
            imageView.setImageBitmap(bitmap);

        }
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
        notifyDataSetChanged();
    }
    public void setItems(ArrayList<GridViewItem> items){
        gridViewItems = items;
        notifyDataSetChanged();
    }

}

class TimeTabGridViewThumbnailManager extends AsyncTask<Void, Void, Bitmap> {
    private ImageView imageView;
    private long id;
    private Context context;
    private GridView gridView;
    private int position = -1;

    public TimeTabGridViewThumbnailManager(ImageView imageView, long id, Context context, GridView gridView, int position) {
        this.imageView = imageView;
        this.id = id;
        this.context = context;
        this.gridView = gridView;
        this.position = position;
    }
    protected Bitmap doInBackground(Void... voids) {
        return MediaStore.Images.Thumbnails.getThumbnail(context.getContentResolver(), id, MediaStore.Images.Thumbnails.MICRO_KIND, null);
    }

    protected void onPostExecute(Bitmap bitmap) {
        if (gridView.getFirstVisiblePosition() <= position && gridView.getLastVisiblePosition() >= position)
            imageView.setImageBitmap(bitmap);
    }
}
