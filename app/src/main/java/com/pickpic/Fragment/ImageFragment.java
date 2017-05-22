package com.pickpic.Fragment;

import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.pickpic.R;

/**
 * Created by yewon on 2017-05-09.
 */

public class ImageFragment extends Fragment{

    ImageView imageView;
    String imagefilepath;

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

        imageView = (ImageView)view.findViewById(R.id.imageview);
        imageView.setImageBitmap(BitmapFactory.decodeFile(imagefilepath));

        return view;
    }

}
