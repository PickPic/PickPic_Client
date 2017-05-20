package com.pickpic.Backend;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.provider.MediaStore;
import android.widget.ImageView;

/**
 * Created by sekyo on 2017-05-20.
 */

public class ThumbnailManager extends AsyncTask<Void, Void, Bitmap> {
    private ImageView imageView;
    private long id;
    private Context context;

    public ThumbnailManager(ImageView imageView, long id, Context context){
        this.imageView = imageView;
        this.id = id;
        this.context = context;
    }
    protected Bitmap doInBackground(Void... voids) {
        return MediaStore.Images.Thumbnails.getThumbnail(context.getContentResolver(), id, MediaStore.Images.Thumbnails.MICRO_KIND, null);
    }

    protected void onProgressUpdate() {
    }

    protected void onPostExecute(Bitmap bitmap) {
        imageView.setImageBitmap(bitmap);
    }
}
