package com.pickpic.Activity;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ListView;

import com.pickpic.Adapter.GridViewAdaptor;
import com.pickpic.Adapter.SearchRecyclerViewAdapter;
import com.pickpic.Backend.LocalImageManager;
import com.pickpic.Backend.TagDBManager;
import com.pickpic.Item.GridViewItem;
import com.pickpic.R;

import java.util.ArrayList;

public class SearchActivity extends AppCompatActivity {
    private EditText textView;
    private GridView gridView;
    private ListView listView;
    private ImageButton searchButton;
    private TagDBManager tagDBManager;
    private ArrayAdapter listViewAdapter;
    private RecyclerView recyclerView;
    private SearchRecyclerViewAdapter recyclerViewAdapter;
    private GridViewAdaptor gridViewAdaptor;
    private boolean isListView = true;
    private ImageButton backButton;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        tagDBManager = new TagDBManager(getApplicationContext());

        textView = (EditText) findViewById(R.id.inputText);
        gridView = (GridView) findViewById(R.id.search_activity_gridview);
        listView = (ListView) findViewById(R.id.search_activity_listview);
        searchButton = (ImageButton) findViewById(R.id.search_activity_search_btn);
        recyclerView = (RecyclerView) findViewById(R.id.search_activity_recycleview);
        backButton = (ImageButton) findViewById(R.id.search_activity_back_btn);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        listViewAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1);
        listView.setAdapter(listViewAdapter);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerViewAdapter = new SearchRecyclerViewAdapter(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(recyclerViewAdapter);

        gridViewAdaptor = new GridViewAdaptor(gridView, BitmapFactory.decodeResource(getResources(), R.drawable.blank));
        gridView.setAdapter(gridViewAdaptor);

        setListView();

        Intent intent = getIntent();

        if(intent.getStringExtra("bucket") != null){
            showDirectory(intent.getStringExtra("bucket"));
            recyclerViewAdapter.addItem(LocalImageManager.getBucketName(getApplicationContext(),intent.getStringExtra("bucket")));
        }

        if(intent.getStringExtra("tag") != null){
            showDirectory(intent.getStringExtra("tag"));
            recyclerViewAdapter.addItem( intent.getStringExtra("tag"));
            setGridViewFromTagTab();
        }




        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView parent, View view, int position, long id) {
                textView.setText((String) listViewAdapter.getItem(position));
                textView.setSelection(textView.length());
            }
        });

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setGridView();
            }
        });

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setListView();
            }
        });

        textView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                setListView();
            }
        });
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView parent, View v, int position, long id){
                String selectedimage = ((GridViewItem)gridViewAdaptor.getItem(position)).getPath();

                Intent intent = new Intent(getApplicationContext(), GalleryActivity.class);
                intent.putExtra("filepath", selectedimage);
                startActivity(intent);

            }
        });
    }
    public void onBackPressed() {
        if(isListView){
            gridView.setVisibility(GridView.VISIBLE);
            listView.setVisibility(ListView.INVISIBLE);
            isListView = false;
        } else {
            finish();
        }
    }
    public void setListView() {
        gridView.setVisibility(GridView.INVISIBLE);
        listView.setVisibility(ListView.VISIBLE);
        isListView = true;
        ArrayList<String> tagList = tagDBManager.getRecommendTagList(recyclerViewAdapter.getItems(), textView.getText().toString());
        listViewAdapter.clear();
        listViewAdapter.addAll(tagList);
    }
    private void showDirectory(String bucket){
        gridView.setVisibility(GridView.VISIBLE);
        listView.setVisibility(ListView.INVISIBLE);
        isListView = false;
        gridViewAdaptor.setItems(LocalImageManager.getImagesInDirectory(getApplicationContext(),bucket));
    }
    private void setGridViewFromTagTab(){
        gridView.setVisibility(GridView.VISIBLE);
        listView.setVisibility(ListView.INVISIBLE);
        isListView = false;
        ArrayList<String> inputs = tagDBManager.getPathsByTags(recyclerViewAdapter.getItems());

        ArrayList<GridViewItem> gridViewItems = LocalImageManager.getGridViewItemList(getApplicationContext(),inputs);
        gridViewAdaptor.setItems(gridViewItems);

    }
    private void setGridView() {
        if (!textView.getText().toString().equals("")) {
            recyclerViewAdapter.addItem(textView.getText().toString());
            textView.getText().clear();
            gridView.setVisibility(GridView.VISIBLE);
            listView.setVisibility(ListView.INVISIBLE);
            isListView = false;
            ArrayList<String> inputs = tagDBManager.getPathsByTags(recyclerViewAdapter.getItems());

            ArrayList<GridViewItem> gridViewItems = LocalImageManager.getGridViewItemList(getApplicationContext(),inputs);
            gridViewAdaptor.setItems(gridViewItems);
        }
    }
}
