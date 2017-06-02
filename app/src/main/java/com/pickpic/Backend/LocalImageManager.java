package com.pickpic.Backend;


import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;

import com.pickpic.Item.DirectoryTabListViewItem;
import com.pickpic.Item.GridViewItem;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

/**
 * Created by sekyo on 2017-05-11.
 */


public class LocalImageManager {
    public static ArrayList<GridViewItem> getGridViewItemList(Context context, ArrayList<String> paths) {

        ArrayList<GridViewItem> list = new ArrayList<GridViewItem>();
        for (int i = 0; i < paths.size(); i++) {
            list.add(LocalImageManager.getGridViewItem(context, paths.get(i)));
        }
        return list;

    }
    public static String getBucketName(Context context, String bucketId){
        String bucketName = null;

        Uri uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
        String[] projection = {MediaStore.Images.Media.BUCKET_DISPLAY_NAME, MediaStore.Images.Media.BUCKET_ID};
        String[] selection = {bucketId};
        Cursor cursor = context.getContentResolver().query(uri, projection, MediaStore.Images.Media.BUCKET_ID + "=?", selection, null);
        if (cursor.moveToNext()) {
            int columnIndex = cursor.getColumnIndex(MediaStore.Images.Media.BUCKET_DISPLAY_NAME);
            bucketName = cursor.getString(columnIndex);
        }
        cursor.close();
        return bucketName;
    }
    public static ArrayList<GridViewItem> getTimeTabGridViewItemList(Context context){
        ArrayList<GridViewItem> gridViewItems = new ArrayList<GridViewItem>();
        Uri uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
        String[] projection = {MediaStore.Images.Media.DATA, MediaStore.Images.Media._ID, MediaStore.Images.Media.DATE_ADDED};
        Cursor cursor = context.getContentResolver().query(uri, projection, null, null, "DATE_ADDED DESC");

        int columnIndex;

        if (cursor != null) {

            while (cursor.moveToNext()) {
                GridViewItem gridViewItem = new GridViewItem();

                columnIndex = cursor.getColumnIndex(MediaStore.Images.Media.DATA);
                gridViewItem.setPath(cursor.getString(columnIndex));

                columnIndex = cursor.getColumnIndex(MediaStore.Images.Media._ID);
                gridViewItem.setThumbnail(cursor.getLong(columnIndex));
                gridViewItems.add(gridViewItem);
            }
            cursor.close();
        }
        return gridViewItems;
    }

    public static String getDateByPath(Context context, String path) {
        Uri uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
        String[] projection = {MediaStore.Images.Media.DATA, MediaStore.Images.Media.DATE_ADDED};
        String[] selection = {path};
        String date = null;

        Cursor cursor = context.getContentResolver().query(uri, projection, MediaStore.Images.Media.DATA + "=?", selection, "DATE_ADDED DESC");
        if (cursor.moveToNext()) {
            int columnIndex = cursor.getColumnIndex(MediaStore.Images.Media.DATE_ADDED);
            date = cursor.getString(columnIndex);
            date += "000";
            String format = "MM/dd/yyyy";
            SimpleDateFormat formatter = new SimpleDateFormat(format, Locale.ENGLISH);

            date = formatter.format(new Date(Long.parseLong(date)));
        }
        cursor.close();
        return date;
    }

    public static GridViewItem getGridViewItem(Context context, String path) {

        GridViewItem gridViewItem = new GridViewItem();

        Uri uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
        String[] projection = {MediaStore.Images.Media.DATA, MediaStore.Images.Media._ID, MediaStore.Images.Media.DATE_ADDED};
        String[] selection = {path};

        Cursor cursor = context.getContentResolver().query(uri, projection, MediaStore.Images.Media.DATA + "=?", selection, "DATE_ADDED DESC");

        if (cursor.moveToNext()) {

            int columnIndex = cursor.getColumnIndex(MediaStore.Images.Media.DATA);
            gridViewItem.setPath(cursor.getString(columnIndex));

            columnIndex = cursor.getColumnIndex(MediaStore.Images.Media._ID);
            gridViewItem.setThumbnail(cursor.getLong(columnIndex));

        }

        cursor.close();
        return gridViewItem;

    }

    public static ArrayList<String> getAllImagePath(Context context, String order) {

        ArrayList<String> list = new ArrayList<String>();
        Uri uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
        String[] projection = {MediaStore.Images.Media.DATA, MediaStore.Images.Media.DATE_ADDED};
        Cursor cursor = context.getContentResolver().query(uri, projection, null, null, "DATE_ADDED " + order);

        int columnIndex = cursor.getColumnIndex(MediaStore.Images.Media.DATA);

        if (cursor != null) {
            while (cursor.moveToNext()) {
                list.add(cursor.getString(columnIndex));
            }
            cursor.close();
        }
        return list;
    }

    public static ArrayList<GridViewItem> getImagesInDirectory(Context context, String bucketId) {

        ArrayList<GridViewItem> list = new ArrayList<GridViewItem>();
        Uri uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
        String[] projection = {MediaStore.Images.Media.DATA, MediaStore.Images.Media.DATE_ADDED, MediaStore.Images.Media._ID, MediaStore.Images.Media.BUCKET_ID};
        String[] selection = {bucketId};

        Cursor cursor = context.getContentResolver().query(uri, projection, MediaStore.Images.Media.BUCKET_ID + "=?", selection, "DATE_ADDED DESC");

        if (cursor != null) {

            while (cursor.moveToNext()) {

                GridViewItem gridViewItem = new GridViewItem();

                int columnIndex = cursor.getColumnIndex(MediaStore.Images.Media.DATA);
                gridViewItem.setPath(cursor.getString(columnIndex));

                columnIndex = cursor.getColumnIndex(MediaStore.Images.Media._ID);
                gridViewItem.setThumbnail(cursor.getLong(columnIndex));

                list.add(gridViewItem);
            }
            cursor.close();
        }
        return list;
    }

    public static ArrayList<DirectoryTabListViewItem> getDirectoryTabListViewItem(Context context) {

        Uri uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
        String[] projection = {MediaStore.Images.Media._ID, MediaStore.Images.Media.DATE_ADDED, MediaStore.Images.Media.BUCKET_ID,
                MediaStore.Images.Media.BUCKET_DISPLAY_NAME};

        Cursor cursor = context.getContentResolver().query(uri, projection, null, null, "DATE_ADDED DESC");

        ArrayList<String> ids = new ArrayList<String>();
        ArrayList<DirectoryTabListViewItem> itemList = new ArrayList<DirectoryTabListViewItem>();

        if (cursor != null) {
            while (cursor.moveToNext()) {
                DirectoryTabListViewItem directoryTabListViewItem = new DirectoryTabListViewItem();
                int columnIndex = cursor.getColumnIndex(MediaStore.Images.Media.BUCKET_ID);

                directoryTabListViewItem.setId(cursor.getString(columnIndex));
                if (!ids.contains(directoryTabListViewItem.getId())) {
                    columnIndex = cursor.getColumnIndex(MediaStore.Images.Media.BUCKET_DISPLAY_NAME);
                    directoryTabListViewItem.setName(cursor.getString(columnIndex));

                    columnIndex = cursor.getColumnIndex(MediaStore.Images.Media._ID);
                    directoryTabListViewItem.setThumbnail(cursor.getLong(columnIndex));

                    itemList.add(directoryTabListViewItem);
                    ids.add(directoryTabListViewItem.getId());

                } else {
                    itemList.get(ids.indexOf(directoryTabListViewItem.getId())).upCount();
                }
            }
            cursor.close();
        }
        return itemList;
    }
}

