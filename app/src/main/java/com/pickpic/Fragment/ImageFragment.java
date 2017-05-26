package com.pickpic.Fragment;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.pickpic.Backend.TagDBManager;
import com.pickpic.R;

import java.io.File;

import uk.co.senab.photoview.PhotoViewAttacher;

/**
 * Created by yewon on 2017-05-09.
 */

public class ImageFragment extends Fragment{

    ImageView imageView;
    String imageFilePath;
    int degree;
    PhotoViewAttacher photoViewAttacher;

    public ImageFragment(String filepath) {
        this.imageFilePath = filepath;
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

        imageView = (ImageView)view.findViewById(R.id.imageView);
        photoViewAttacher = new PhotoViewAttacher(imageView);
        imageView.setImageBitmap(BitmapFactory.decodeFile(imageFilePath));

        return view;
    }

    public boolean deleteImage() {

        final boolean[] delete = {false};

        AlertDialog.Builder deleteimage = new AlertDialog.Builder(getContext());
        deleteimage.setTitle("Delete this image");
        deleteimage.setMessage("Are you sure?");

        deleteimage.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                delete[0] = true;
                (new TagDBManager(getContext())).removeImage(imageFilePath);
                File file = new File(imageFilePath);
                if(file.exists()) {
                    file.delete();
                }
                dialog.dismiss();
            }
        });

        deleteimage.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                delete[0] = false;
                dialog.dismiss();
            }
        });
        deleteimage.show();

        return delete[0];
    }


}
