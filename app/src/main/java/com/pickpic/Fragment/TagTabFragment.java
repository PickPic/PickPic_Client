package com.pickpic.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.Toast;

import com.pickpic.Activity.MainActivity;
import com.pickpic.Adapter.TagTabListViewAdapter;
import com.pickpic.Adapter.TimeTabGridViewAdaptor;
import com.pickpic.Backend.LocalImageManager;
import com.pickpic.Backend.TagDBManager;
import com.pickpic.Item.DirectoryTabListViewItem;
import com.pickpic.Item.TagTabListViewItem;
import com.pickpic.R;

import java.util.ArrayList;
import java.util.List;


public class TagTabFragment extends Fragment {

    private View view;
    public TagTabFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.tag_tab_fragment, container, false);
        TagTabListViewAdapter adaptor = new TagTabListViewAdapter();
        ListView listView = (ListView) view.findViewById(R.id.tag_tab_listview);

        inflater.inflate(R.layout.tag_tab_fragment, container, false);
        listView.setAdapter(adaptor);

        //TagDBManager;
        TagDBManager tagDBManager = new TagDBManager(getActivity());
        ArrayList<TagTabListViewItem> list = tagDBManager.getTagTabListViewItem();
        for(int i = 0;i < list.size(); i++){
            adaptor.addItem(list.get(i));
        }

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView parent, View v, int position, long id){
                Toast.makeText(getContext(), "listView clicked", Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }

}
