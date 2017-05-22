package com.pickpic.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.PopupMenu;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.Toast;

import com.pickpic.Fragment.ImageFragment;
import com.pickpic.Fragment.TagFragment;
import com.pickpic.R;

import static android.support.v7.widget.PopupMenu.OnMenuItemClickListener;

public class GalleryActivity extends AppCompatActivity {

    int touch = 1;
    String filepath;
    ImageFragment imageFragment;
    TagFragment tagFragment;
    ImageButton back;
    ImageButton menu;

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

        //image screen, tag screen change
        FrameLayout frameLayout;
        frameLayout = (FrameLayout)findViewById(R.id.frame);
        frameLayout.setOnTouchListener(screentouchListener);

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
                fragment = new TagFragment(filepath);   //태그 추가한 것에 대해서 갱신이 안되면 수정하기
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

    private View.OnTouchListener screentouchListener = new View.OnTouchListener() {

        @Override
        public boolean onTouch(View v, MotionEvent event) {

            switch(event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    touch++;
                    ChangeFragment(v);
                    break;
                case MotionEvent.ACTION_CANCEL:
                    break;
                case MotionEvent.ACTION_UP: {
                    break;
                }
                default:
                    break;
            }

            return false;
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
                    Toast toast1 = Toast.makeText(getApplicationContext(), "Sharing", Toast.LENGTH_SHORT);
                    toast1.show();
                    break;
                case R.id.info:
                    Toast toast2 = Toast.makeText(getApplicationContext(), "Show more information", Toast.LENGTH_SHORT);
                    toast2.show();
                    break;
                case R.id.delete:
                    Toast toast3 = Toast.makeText(getApplicationContext(), "Delete this picture", Toast.LENGTH_SHORT);
                    toast3.show();
                    break;
            }
            return false;
        }
    };


}
