package com.pickpic.Backend;

import android.content.ContentUris;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.util.Log;

import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import org.json.JSONObject;

import java.io.File;
import java.net.URISyntaxException;
import java.util.ArrayList;

/**
 * Created by 5p on 2017-05-03.
 */

class WaitSlot {
    public Context context;
    public String path;
    public WaitSlot(Context context, String path){
        this.context = context;
        this.path = path;
    }
}

public class AutoTagGenerator {
    public static int limit = 10;
    public static ArrayList<WaitSlot> que = new ArrayList<>();
    public static void autoTagGenerate(final Context context, String path) {
        if(AutoTagGenerator.limit <= 0){
            que.add(new WaitSlot(context, path));
            return;
        }
        AutoTagGenerator.limit--;
        File f = new File(path);
        Ion.with(context)
                    .load("http://165.194.104.17:8080/upload") //server ip
                    .setTimeout(1000 * 10)
                    .setMultipartFile(path, f.getName(), f)
                    .asString()
                    .setCallback(new FutureCallback<String>() {
                        @Override
                        public void onCompleted(Exception e, String result) {
                            if(e != null){
                                Log.e("ion-error", e.toString());
                                AutoTagGenerator.limit++;
                                if(que.size() > 0){
                                    WaitSlot tmp = que.get(0);
                                    que.remove(0);
                                    AutoTagGenerator.autoTagGenerate(tmp.context, tmp.path);
                                }
                                return;
                            }
                            try {
                                JSONObject jsonObject = new JSONObject(result);
                                int size = jsonObject.getInt("num");
                                TagDBManager tagDBManager = new TagDBManager(context);
                                for (int i = 0; i < size; i++) {
                                    Log.v("Json", jsonObject.get("tag" + i).toString());
                                    tagDBManager.insertTag(jsonObject.get("path").toString(), jsonObject.get("tag" + i).toString(), TagDBManager.NORMAL_TAG);
                                }
                                if(size > 0) {
                                    tagDBManager.makeTrueTagGenerated(jsonObject.get("path").toString());
                                }
                                ArrayList<String> test = tagDBManager.getAllImages();
                                Log.v("generater add image", "image num : " + test.size());
                                test = tagDBManager.getAllTags();
                                Log.v("generater add tag", "tag num : " + test.size());
                                if(que.size() > 0){
                                    WaitSlot tmp = que.get(0);
                                    que.remove(0);
                                    String path = tmp.path;
                                    File f = new File(path);
                                    Ion.with(tmp.context)
                                            .load("http://165.194.104.17:8080/upload") //server ip
                                            .setTimeout(1000 * 10)
                                            .setMultipartFile(path, f.getName(), f)
                                            .asString()
                                            .setCallback(this);
                                }
                                else {
                                    AutoTagGenerator.limit++;
                                }

                            } catch (Exception ex) {
                                Log.v("exception", ex.toString());
                                AutoTagGenerator.limit++;
                                if(que.size() > 0){
                                    WaitSlot tmp = que.get(0);
                                    que.remove(0);
                                    AutoTagGenerator.autoTagGenerate(tmp.context, tmp.path);
                                }
                            }
                        }

                    });
    }

    public static String getFilePath(Context context, Uri uri) throws URISyntaxException {
        String selection = null;
        String[] selectionArgs = null;
        // Uri is different in versions after KITKAT (Android 4.4), we need to
        if (Build.VERSION.SDK_INT >= 19 && DocumentsContract.isDocumentUri(context.getApplicationContext(), uri)) {
            if (isExternalStorageDocument(uri)) {
                final String docId = DocumentsContract.getDocumentId(uri);
                final String[] split = docId.split(":");
                return Environment.getExternalStorageDirectory() + "/" + split[1];
            } else if (isDownloadsDocument(uri)) {
                final String id = DocumentsContract.getDocumentId(uri);
                uri = ContentUris.withAppendedId(
                        Uri.parse("content://downloads/public_downloads"), Long.valueOf(id));
            } else if (isMediaDocument(uri)) {
                final String docId = DocumentsContract.getDocumentId(uri);
                final String[] split = docId.split(":");
                final String type = split[0];
                if ("image".equals(type)) {
                    uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                } else if ("video".equals(type)) {
                    uri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
                } else if ("audio".equals(type)) {
                    uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
                }
                selection = "_id=?";
                selectionArgs = new String[]{
                        split[1]
                };
            }
        }
        if ("content".equalsIgnoreCase(uri.getScheme())) {
            String[] projection = {
                    MediaStore.Images.Media.DATA
            };
            Cursor cursor = null;
            try {
                cursor = context.getContentResolver()
                        .query(uri, projection, selection, selectionArgs, null);
                int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
                if (cursor.moveToFirst()) {
                    return cursor.getString(column_index);
                }
            } catch (Exception e) {
            }
        } else if ("file".equalsIgnoreCase(uri.getScheme())) {
            return uri.getPath();
        }
        return null;
    }

    static boolean isExternalStorageDocument(Uri uri) {
        return "com.android.externalstorage.documents".equals(uri.getAuthority());
    }

    static boolean isDownloadsDocument(Uri uri) {
        return "com.android.providers.downloads.documents".equals(uri.getAuthority());
    }

    static boolean isMediaDocument(Uri uri) {
        return "com.android.providers.media.documents".equals(uri.getAuthority());
    }
}