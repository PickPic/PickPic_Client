package com.pickpic.Fragment;

import android.app.ListFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.pickpic.Adapter.RecommendTagListAdapter;
import com.pickpic.Item.RecommendTagListItem;
import com.pickpic.R;

import java.util.ArrayList;

public class RecommendTagListFragment extends Fragment {

    RecommendTagListAdapter adapter;
    ListView recommendtagview;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState); }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_recommend_tag_list, container, false);



        recommendtagview = (ListView)view.findViewById(R.id.recommendtaglist);
        ArrayList<RecommendTagListItem> tagListItems = new ArrayList<RecommendTagListItem>();

        tagListItems.add(new RecommendTagListItem("tag1"));
        tagListItems.add(new RecommendTagListItem("tag2"));

        adapter = new RecommendTagListAdapter(this.getContext(), tagListItems);

        recommendtagview.setAdapter(adapter); //recommendtagview가 문제!


        return view;
    }


}


