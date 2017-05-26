package com.pickpic.Activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.PopupMenu;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.pickpic.Backend.TagDBManager;
import com.pickpic.Fragment.ImageFragment;
import com.pickpic.Fragment.TagFragment;
import com.pickpic.R;

import java.io.File;

import static android.support.v7.widget.PopupMenu.OnMenuItemClickListener;

public class GalleryActivity extends AppCompatActivity {

    int touch = 1;
    String filepath;
    ImageFragment imageFragment;
    TagFragment tagFragment;
    ImageButton back;
    ImageButton menu;
    ImageButton change;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);

        Intent intent = getIntent();
        filepath = intent.getStringExtra("filepath");

        imageFragment = new ImageFragment(filepath);
        tagFragment = new TagFragment(filepath);

        //button above
        back = (ImageButton)findViewById(R.id.backBtn);
        back.setOnClickListener(backActionListener);
        menu = (ImageButton)findViewById(R.id.menuBtn);
        change = (ImageButton)findViewById(R.id.changeBtn);
        change.setOnClickListener(changeClickListener);

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame, imageFragment);
        fragmentTransaction.commit();
    }

    public void ChangeFragment(View v) {

        Fragment fragment;

        touch = touch % 2;

        switch(touch) {
            default:
            case 1: {
                fragment = imageFragment;
                break;
            }
            case 0: {
                fragment = new TagFragment(filepath);
                break;
            }
        }

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame, fragment);
        fragmentTransaction.commit();

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(event.getAction() == KeyEvent.ACTION_DOWN) {
            if(keyCode == KeyEvent.KEYCODE_BACK) {
                back.callOnClick();
                return true;
            }
        }
        return super.onKeyDown(keyCode, event);
    }

    private View.OnClickListener backActionListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(touch == 0) {    //1일때 이미지 0일때 태그
                touch++;
                ChangeFragment(v);
            } else {
                finish();
            }
        }
    };

    private View.OnClickListener changeClickListener = new View.OnClickListener(){

        @Override
        public void onClick(View v) {
            touch++;
            ChangeFragment(v);
        }
    };


    public void menuClick(View v) {
        PopupMenu popupMenu = new PopupMenu(v.getContext(), v);
        popupMenu.inflate(R.menu.optionmenu);
        popupMenu.setOnMenuItemClickListener(menuitemListener);
        popupMenu.show();
    }


    private OnMenuItemClickListener menuitemListener = new OnMenuItemClickListener() {

        @Override
        public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()){

            case R.id.share:
                shareImage();
                break;

            case R.id.delete:

                Toast toast = Toast.makeText(getApplicationContext(), filepath + "\n" + "This image is deleted.", Toast.LENGTH_LONG);
                toast.show();
                (new TagDBManager(getApplicationContext())).removeImage(filepath);
                File file = new File(filepath);
                if(file.exists()) {
                    file.delete();
                }
                finish();
                break;
            }
            return false;
        }
    };

    public void shareImage() {
        File file = new File(filepath);
        Uri mSaveImageUri = Uri.fromFile(file);
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("image/jpg");
        intent.putExtra(Intent.EXTRA_STREAM, mSaveImageUri);
        startActivity(Intent.createChooser(intent, "Choose"));
    }

}
