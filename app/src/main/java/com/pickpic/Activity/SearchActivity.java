package com.pickpic.Activity;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.pickpic.Backend.TagDBManager;
import com.pickpic.Fragment.RecommendTagListFragment;
import com.pickpic.Fragment.SearchResultFragment;
import com.pickpic.Item.RecommendTagListItem;
import com.pickpic.R;

import java.util.ArrayList;

public class SearchActivity extends AppCompatActivity {


    public SearchActivity(){

    }

    private boolean isRecommendTagList = true;
    RecommendTagListFragment recommendTagListFragment = new RecommendTagListFragment();
    @Override


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        final TagDBManager tagDBManager = new TagDBManager(this);

        FrameLayout frameLayout;
        frameLayout = (FrameLayout)findViewById(R.id.frame);

        //초기 fragment
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.add(R.id.frame, recommendTagListFragment);
        fragmentTransaction.commit();


        ImageButton button1 = (ImageButton) findViewById(R.id.button1);
        button1.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v){//버튼이 눌리면 searchresult로 fragment변경

                //입력받은 스트링 가져오기
                EditText inputString = (EditText) findViewById(R.id.inputText) ;
                String inputTag = inputString.getText().toString() ;


                Log.v("searched Tag:", inputTag);    //검색된 검색어 출력해보기


                if(!(inputTag.isEmpty())){ //아무 스트링도 입력하지 않으면 실행 안함

                    SearchResultFragment searchResultFragment = new SearchResultFragment();
                    FragmentManager fm = getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fm.beginTransaction();
                    fragmentTransaction.replace(R.id.frame, searchResultFragment);
                    fragmentTransaction.commit();

                }
            }
        });


    }
}
