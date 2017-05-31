package com.pickpic.Activity;

import android.content.DialogInterface;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.pickpic.Adapter.ManualPagerAdapter;
import com.pickpic.R;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Handler;

import me.relex.circleindicator.CircleIndicator;

public class ManualActivity extends AppCompatActivity {
    private ViewPager mPager;
    private int currentPage = 0;
    private static final Integer[] XMEN= {R.drawable.manual1, R.drawable.manual2, R.drawable.manual3, R.drawable.manual4, R.drawable.manual5, R.drawable.manual6, R.drawable.manual7, R.drawable.manual8};
    private ArrayList<Integer> XMENArray = new ArrayList<Integer>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manual);
        init();
        AlertDialog.Builder manualChecker = new AlertDialog.Builder(this);
        manualChecker.setMessage("Do you want to see a manual?");
        manualChecker.setPositiveButton("Yes", null);
        manualChecker.setNegativeButton("No", new DialogInterface.OnClickListener(){
           @Override
            public void onClick(DialogInterface dialog, int which){
               finish();
           }
        });
        AlertDialog alert = manualChecker.create();
        alert.show();
    }
    private void init() {
        for(int i=0;i<XMEN.length;i++)
            XMENArray.add(XMEN[i]);

        mPager = (ViewPager) findViewById(R.id.pager);
        mPager.setOffscreenPageLimit(7);
        mPager.setAdapter(new ManualPagerAdapter(this,XMENArray));
        CircleIndicator indicator = (CircleIndicator) findViewById(R.id.indicator);
        indicator.setViewPager(mPager);
    }
}
