package com.pickpic.Activity;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.pickpic.Adapter.ManualPagerAdapter;
import com.pickpic.R;


public class ManualActivity extends AppCompatActivity {
    ManualPagerAdapter manualPagerAdapter;
    ViewPager viewPager;
    Button skipButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manual);

        skipButton = (Button) findViewById(R.id.manual_skip_button);

        manualPagerAdapter = new ManualPagerAdapter(getApplicationContext(), skipButton);
        viewPager = (ViewPager)findViewById(R.id.manual_pager);
        viewPager.setAdapter(manualPagerAdapter);

        skipButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
