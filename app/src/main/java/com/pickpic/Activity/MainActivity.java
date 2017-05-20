package com.pickpic.Activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.Toolbar;
import android.support.design.widget.*;
import android.view.LayoutInflater;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import com.pickpic.Adapter.DirectoryTabListViewAdaptor;
import com.pickpic.Adapter.TabAdapter;
import com.pickpic.Backend.Synchronizer;
import com.pickpic.Backend.TagDBManager;
import com.pickpic.R;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // 툴바 선언
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // 탭 선언
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);

        tabLayout.addTab(tabLayout.newTab().setIcon(
                R.drawable.ic_folder_black_48dp));
        tabLayout.addTab(tabLayout.newTab().setIcon(
                R.drawable.ic_access_time_black_48dp));
        tabLayout.addTab(tabLayout.newTab().setIcon(
                R.drawable.ic_local_offer_black_48dp));
        tabLayout.getTabAt(0).getIcon().setColorFilter(Color.parseColor("#424242"), PorterDuff.Mode.SRC_IN);
        for(int i = 1; i< tabLayout.getTabCount(); i++){
            tabLayout.getTabAt(i).getIcon().setColorFilter(Color.parseColor("#a8a8a8"), PorterDuff.Mode.SRC_IN);
        }

        final ViewPager viewPager =
                (ViewPager) findViewById(R.id.pager);
        final PagerAdapter adapter = new TabAdapter
                (getSupportFragmentManager(),
                        tabLayout.getTabCount());
        viewPager.setAdapter(adapter);

        viewPager.addOnPageChangeListener(new
                TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
                tab.getIcon().setColorFilter(Color.parseColor("#424242"), PorterDuff.Mode.SRC_IN);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                tab.getIcon().setColorFilter(Color.parseColor("#a8a8a8"), PorterDuff.Mode.SRC_IN);
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        TagDBManager tagDBManager = new TagDBManager(this);

        ArrayList<String> sync =  tagDBManager.getAllTags();
        Log.v("sync before", "tag num : "+sync.size());
        sync = tagDBManager.getAllImages();
        Log.v("sync before", "image num : "+sync.size());

        Synchronizer.synchronize(this);

        sync =  tagDBManager.getAllTags();
        Log.v("sync after", "tag num : "+sync.size());
        sync = tagDBManager.getAllImages();
        Log.v("sync after", "image num : "+sync.size());
    }

    public  void search_btn(View view){
        Intent intent = new Intent(this, SearchActivity.class);
        startActivity(intent);
    }

    public void vert_btn(View v){
        PopupMenu popupMenu = new PopupMenu(this, v);
        popupMenu.getMenuInflater().inflate(R.menu.menu_main, popupMenu.getMenu());

        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener(){
            @Override
            public boolean onMenuItemClick(MenuItem menu){
                switch (menu.getItemId()){
                    case R.id.syncMenu:
                        /*
                        TagDBManager tagDBManager = new TagDBManager(MainActivity.this);

                        ArrayList<String> sync =  tagDBManager.getAllTags();
                        Log.v("sync before", "tag num : "+sync.size());
                        sync = tagDBManager.getAllImages();
                        Log.v("sync before", "image num : "+sync.size());

                        Syncronizer.synchronize(MainActivity.this);

                        sync =  tagDBManager.getAllTags();
                        Log.v("sync after", "tag num : "+sync.size());
                        sync = tagDBManager.getAllImages();
                        Log.v("sync after", "image num : "+sync.size()); */
                        Toast.makeText(getApplicationContext(),"synchronize is completed.",Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.howToUseMenu:
                        Intent intent2 = new Intent(MainActivity.this, SearchActivity.class);
                        startActivity(intent2);
                        break;
                    case R.id.serviceCenterMenu:
                        Intent intent3 = new Intent(MainActivity.this, ServiceCenterActivity.class);
                        startActivity(intent3);
                        break;
                }
                return true;
            }
        });
        popupMenu.show();
    }

}

