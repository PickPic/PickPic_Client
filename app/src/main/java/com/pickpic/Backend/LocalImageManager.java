package com.pickpic.Backend;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;

import com.pickpic.Item.DirectoryTabListViewItem;
import com.pickpic.Item.GridViewItem;

import java.util.ArrayList;

/**
 * Created by sekyo on 2017-05-11.
 */

public class LocalImageManager {

    public static ArrayList<GridViewItem> getGridViewItemList(Context context, ArrayList<String> paths){

        ArrayList<GridViewItem> list = new ArrayList<GridViewItem>();

        for(int i = 0;i < paths.size();i++){
            list.add(LocalImageManager.getGridViewItem(context,paths.get(i)));
        }

        return  list;
    }
    public static String getDateByPath(Context context, String path){
        Uri uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
        String[] projection = {MediaStore.Images.Media.DATA, MediaStore.Images.Media.DATE_ADDED};
        String[] selection = {path};
        String date = null;

        Cursor cursor = context.getContentResolver().query(uri, projection, MediaStore.Images.Media.DATA + "=?", selection, "DATE_ADDED DESC");

        if(cursor.moveToNext()){
            int columnIndex = cursor.getColumnIndex(MediaStore.Images.Media.DATE_ADDED);
            date = cursor.getString(columnIndex);
        }
        return date;
    }
    public static GridViewItem getGridViewItem(Context context, String path){

        GridViewItem gridViewItem  = new GridViewItem();

        Uri uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
        String[] projection = {MediaStore.Images.Media.DATA, MediaStore.Images.Media._ID, MediaStore.Images.Media.DATE_ADDED};
        String[] selection = {path};

        Cursor cursor = context.getContentResolver().query(uri, projection, MediaStore.Images.Media.DATA + "=?", selection, "DATE_ADDED DESC");

        int columnIndex = cursor.getColumnIndex(MediaStore.Images.Media.DATA);

        if (cursor.moveToNext()){
            columnIndex = cursor.getColumnIndex(MediaStore.Images.Media.DATA);
            gridViewItem.setPath(cursor.getString(columnIndex));

            columnIndex = cursor.getColumnIndex(MediaStore.Images.Media.DATE_ADDED);
            gridViewItem.setDate(cursor.getString(columnIndex));

            columnIndex = cursor.getColumnIndex(MediaStore.Images.Media._ID);
            long thumbnail = cursor.getLong(columnIndex);
            gridViewItem.setThumbnail(MediaStore.Images.Thumbnails.getThumbnail(context.getContentResolver(),thumbnail,MediaStore.Images.Thumbnails.MICRO_KIND, null));
        }
        cursor.close();

        return  gridViewItem;
    }

    public static ArrayList<String> getAllImagePath(Context context){

        ArrayList<String> list = new ArrayList<String>();
        Uri uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
        String[] projection = {MediaStore.Images.Media.DATA, MediaStore.Images.Media.DATE_ADDED};

        Cursor cursor = context.getContentResolver().query(uri, projection, null, null, "DATE_ADDED DESC");
        int columnIndex = cursor.getColumnIndex(MediaStore.Images.Media.DATA);
        if (cursor != null) {
            while (cursor.moveToNext()) {
                list.add(cursor.getString(columnIndex));
            }
            cursor.close();
        }
        return list;
    }

    public static ArrayList<GridViewItem> getImagesInDirectory(Context context,String bucketId){

        ArrayList<GridViewItem> list = new ArrayList<GridViewItem>();
        Uri uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
        String[] projection = {MediaStore.Images.Media.DATA, MediaStore.Images.Media.DATE_ADDED, MediaStore.Images.Media._ID, MediaStore.Images.Media.BUCKET_ID};
        String[] selection = {bucketId};

        Cursor cursor = context.getContentResolver().query(uri, projection, MediaStore.Images.Media.BUCKET_ID + "=?" , selection , "DATE_ADDED DESC");

        if (cursor != null) {
            while (cursor.moveToNext()) {
                GridViewItem gridViewItem = new GridViewItem();

                int columnIndex = cursor.getColumnIndex(MediaStore.Images.Media.DATA);
                gridViewItem.setPath(cursor.getString(columnIndex));

                columnIndex = cursor.getColumnIndex(MediaStore.Images.Media._ID);
                Long thumbnail = cursor.getLong(columnIndex);
                gridViewItem.setThumbnail(MediaStore.Images.Thumbnails.getThumbnail(context.getContentResolver(),thumbnail,MediaStore.Images.Thumbnails.MICRO_KIND, null));

                list.add(gridViewItem);
            }
        }
        return list;
    }

    public static ArrayList<DirectoryTabListViewItem> getDirectoryTabListViewItem(Context context) {

        Uri uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
        String[] projection = {MediaStore.Images.Media._ID, MediaStore.Images.Media.DATE_ADDED,MediaStore.Images.Media.BUCKET_ID,
                MediaStore.Images.Media.BUCKET_DISPLAY_NAME};

        Cursor cursor = context.getContentResolver().query(uri, projection, null, null, "DATE_ADDED DESC");

        ArrayList<String> ids = new ArrayList<String>();
        ArrayList<DirectoryTabListViewItem> itemList = new ArrayList<DirectoryTabListViewItem>();

        if (cursor != null) {
            while (cursor.moveToNext()) {
                DirectoryTabListViewItem directoryTabListViewItem = new DirectoryTabListViewItem();

                int columnIndex = cursor.getColumnIndex(MediaStore.Images.Media.BUCKET_ID);
                directoryTabListViewItem.id = cursor.getString(columnIndex);

                if (!ids.contains(directoryTabListViewItem.id)) {
                    columnIndex = cursor.getColumnIndex(MediaStore.Images.Media.BUCKET_DISPLAY_NAME);
                    directoryTabListViewItem.name = cursor.getString(columnIndex);

                    columnIndex = cursor.getColumnIndex(MediaStore.Images.Media._ID);
                    Long thumbnail = cursor.getLong(columnIndex);

                    itemList.add(directoryTabListViewItem);
                    ids.add(directoryTabListViewItem.id);

                    directoryTabListViewItem.thumbnail = MediaStore.Images.Thumbnails.getThumbnail(context.getContentResolver(),thumbnail,MediaStore.Images.Thumbnails.MICRO_KIND, null);
                } else {
                    itemList.get(ids.indexOf(directoryTabListViewItem.id)).count++;
                }
            }
            cursor.close();
        }
        return itemList;
    }
}
