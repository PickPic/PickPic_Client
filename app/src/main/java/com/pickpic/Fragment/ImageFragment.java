package com.pickpic.Fragment;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.pickpic.R;

import uk.co.senab.photoview.PhotoViewAttacher;

/**
 * Created by yewon on 2017-05-09.
 */

public class ImageFragment extends Fragment{

    ImageView imageView;
    String imageFilePath;
    int degree;
    PhotoViewAttacher photoViewAttacher;
    View view;

    //constructor
    public ImageFragment(String filepath) {
        this.imageFilePath = filepath;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    //connect xml file and java code, display image
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_image,container,false);

        degree = 0;

        imageView = (ImageView)view.findViewById(R.id.imageView);
        photoViewAttacher = new PhotoViewAttacher(imageView);
        imageView.setImageBitmap(BitmapFactory.decodeFile(imageFilePath));

        return view;
    }

    //activated when rotate button in menu is clicked
    public void rotateImage() {
        degree = (degree + 90) % 360;
        rotateImage(BitmapFactory.decodeFile(imageFilePath), degree);
    }

    //rotate image and display
    private void rotateImage(Bitmap src, float degree) {

        Bitmap temp;

        //rotate
        Matrix matrix = new Matrix();
        matrix.postRotate(degree);
        temp = Bitmap.createBitmap(src, 0, 0, src.getWidth(), src.getHeight(), matrix, true);

        imageView.setImageBitmap(temp);
        imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
        photoViewAttacher = new PhotoViewAttacher(imageView);

    }

}
