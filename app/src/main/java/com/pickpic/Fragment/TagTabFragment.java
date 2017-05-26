package com.pickpic.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.pickpic.Backend.TagDBManager;
import com.pickpic.R;

import java.util.ArrayList;


public class TagTabFragment extends Fragment {

    private View view;
    public TagTabFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.tag_tab_fragment, container, false);
        ArrayAdapter listViewAdapter = new ArrayAdapter(getContext(), android.R.layout.simple_list_item_1);
        ListView listView = (ListView) view.findViewById(R.id.tag_tab_listview);

        inflater.inflate(R.layout.tag_tab_fragment, container, false);
        listView.setAdapter(listViewAdapter);

        TagDBManager tagDBManager = new TagDBManager(getActivity());
        ArrayList<String> list = tagDBManager.getAllTags();
        listViewAdapter.addAll(list);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView parent, View v, int position, long id){
                Toast.makeText(getContext(), "listView clicked", Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }

}
