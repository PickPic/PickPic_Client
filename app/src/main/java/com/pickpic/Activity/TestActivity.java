package com.pickpic.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.pickpic.Backend.Syncronizer;
import com.pickpic.Backend.TagDBManager;
import com.pickpic.R;


public class TestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        Button btn = (Button)findViewById(R.id.drop_btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TagDBManager tagDBManager = new TagDBManager(getApplicationContext());
                tagDBManager.initTable();
            }
        });
        Syncronizer.synchronize(this);
    }
}
