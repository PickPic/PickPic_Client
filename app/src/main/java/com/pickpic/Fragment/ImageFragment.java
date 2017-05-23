package com.pickpic.Fragment;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.pickpic.R;

import uk.co.senab.photoview.PhotoViewAttacher;

/**
 * Created by yewon on 2017-05-09.
 */

public class ImageFragment extends Fragment{

    ImageView imageView;
    String imagefilepath;
    int degree;
    PhotoViewAttacher photoViewAttacher;

    public ImageFragment(String filepath) {
        this.imagefilepath = filepath;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_image,container,false);

        degree = 0;

        imageView = (ImageView)view.findViewById(R.id.imageview);
        photoViewAttacher = new PhotoViewAttacher(imageView);
        imageView.setImageBitmap(BitmapFactory.decodeFile(imagefilepath));

        return view;
    }

    public void rotateImage() {
        degree = (degree + 90) % 360;

        rotateImage(BitmapFactory.decodeFile(imagefilepath), degree);
    }

    private void rotateImage(Bitmap src, float degree) {
        Bitmap temp;

        //rotate
        Matrix matrix = new Matrix();
        matrix.postRotate(degree);
        temp = Bitmap.createBitmap(src, 0, 0, src.getWidth(), src.getHeight(), matrix, true);

        imageView.setImageBitmap(temp);

    }


}
