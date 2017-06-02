package com.pickpic.Activity;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.pickpic.Adapter.TabAdapter;
import com.pickpic.Backend.Synchronizer;
import com.pickpic.Backend.TagDBManager;
import com.pickpic.R;

// This is mainactivity : provide base skeleton. 
public class MainActivity extends AppCompatActivity {
    ViewPager viewPager;
    TabAdapter adapter;
    PopupMenu popupMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // Toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Tab
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);

        tabLayout.addTab(tabLayout.newTab().setIcon(
                R.drawable.ic_folder_white_24dp));
        tabLayout.addTab(tabLayout.newTab().setIcon(
                R.drawable.ic_access_time_white_24dp));
        tabLayout.addTab(tabLayout.newTab().setIcon(
                R.drawable.ic_local_offer_white_24dp));


        tabLayout.getTabAt(0).getIcon().setColorFilter(getResources().getColor(R.color.colorPrimary), PorterDuff.Mode.SRC_IN);
        for(int i = 1; i< tabLayout.getTabCount(); i++){
            tabLayout.getTabAt(i).getIcon().setColorFilter(getResources().getColor(R.color.grey), PorterDuff.Mode.SRC_IN);
        }

        viewPager =
                (ViewPager) findViewById(R.id.pager);
        adapter = new TabAdapter
                (getSupportFragmentManager(),
                        tabLayout.getTabCount());
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new
                TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        // tab select listener is declared as below
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
                tab.getIcon().setColorFilter(getResources().getColor(R.color.colorPrimary), PorterDuff.Mode.SRC_IN);

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                tab.getIcon().setColorFilter(getResources().getColor(R.color.grey), PorterDuff.Mode.SRC_IN);
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        
        new Synchronizer(this).execute();

    }
    // this is search button method
    public  void search_btn(View view){
        Intent intent = new Intent(this, SearchActivity.class);
        startActivity(intent);
    }
    // this is for menu button
    public Context getContext(){
        return this;
    }
    // this is menu button method
    public void vert_btn(View v){
        popupMenu = new PopupMenu(this, v);
        popupMenu.getMenuInflater().inflate(R.menu.menu_main, popupMenu.getMenu());
        // on click listener
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener(){
            @Override
            public boolean onMenuItemClick(MenuItem menu){
                switch (menu.getItemId()){
                    case R.id.syncMenu:
                        new Synchronizer(getContext()).execute();
                        break;
                    case R.id.dropMenu:
                        new TagDBManager(getContext()).initTable();
                        break;
                    case R.id.howToUseMenu:
                        Intent intent2 = new Intent(MainActivity.this, ManualActivity.class);
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
