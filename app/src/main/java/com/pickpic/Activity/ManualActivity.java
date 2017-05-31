package com.pickpic.Activity;

import android.support.v4.view.ViewPager;
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
    }
    private void init() {
        for(int i=0;i<XMEN.length;i++)
            XMENArray.add(XMEN[i]);

        mPager = (ViewPager) findViewById(R.id.pager);
        //mPager.setOffscreenPageLimit(3);
        mPager.setAdapter(new ManualPagerAdapter(this,XMENArray));
        CircleIndicator indicator = (CircleIndicator) findViewById(R.id.indicator);
        indicator.setViewPager(mPager);
    }
}
