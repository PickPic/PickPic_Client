package com.pickpic.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.pickpic.Activity.SearchActivity;
import com.pickpic.Backend.TagDBManager;
import com.pickpic.R;

import java.util.ArrayList;


public class TagTabFragment extends Fragment {

    private View view;
    ArrayAdapter listViewAdapter;

    public TagTabFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.tag_tab_fragment, container, false);
        listViewAdapter = new ArrayAdapter(getContext(), android.R.layout.simple_list_item_1);
        ListView listView = (ListView) view.findViewById(R.id.tag_tab_listview);

        inflater.inflate(R.layout.tag_tab_fragment, container, false);
        listView.setAdapter(listViewAdapter);
        setList();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView parent, View v, int position, long id) {

                Intent intent = new Intent(getContext(), SearchActivity.class);
                intent.putExtra("tag", listViewAdapter.getItem(position).toString());
                startActivity(intent);
            }
        });
        return view;
    }

    public void setList() {
        TagDBManager tagDBManager = new TagDBManager(getActivity());
        ArrayList<String> list = tagDBManager.getAllTags();
        listViewAdapter.addAll(list);
        listViewAdapter.notifyDataSetChanged();
    }

}
