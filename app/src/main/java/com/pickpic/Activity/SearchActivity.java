package com.pickpic.Activity;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.pickpic.Fragment.RecommendTagListFragment;
import com.pickpic.Fragment.SearchResultFragment;
import com.pickpic.R;

public class SearchActivity extends AppCompatActivity {


    public SearchActivity(){

    }

    private boolean isRecommendTagList = true;
    RecommendTagListFragment recommendTagListFragment = new RecommendTagListFragment();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        FrameLayout frameLayout;
        frameLayout = (FrameLayout)findViewById(R.id.frame);

        //초기 fragment
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.add(R.id.frame, recommendTagListFragment);
        fragmentTransaction.commit();


        Button button1 = (Button) findViewById(R.id.button1);
        button1.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v){//버튼이 눌리면 searchresult로 fragment변경

                SearchResultFragment searchResultFragment = new SearchResultFragment();

                FragmentManager fm = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fm.beginTransaction();
                fragmentTransaction.replace(R.id.frame, searchResultFragment);
                fragmentTransaction.commit();

            }
        });


    }

}
