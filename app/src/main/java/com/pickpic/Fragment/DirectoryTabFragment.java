package com.pickpic.Fragment;


import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.pickpic.Activity.MainActivity;
import com.pickpic.Activity.SearchActivity;
import com.pickpic.Adapter.DirectoryTabListViewAdaptor;
import com.pickpic.Backend.LocalImageManager;
import com.pickpic.Item.DirectoryTabListViewItem;
import com.pickpic.R;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class DirectoryTabFragment extends Fragment {


    private View view;



    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.directory_tab_fragment, container, false);
        final DirectoryTabListViewAdaptor adaptor = new DirectoryTabListViewAdaptor();

        final ListView listview = (ListView) view.findViewById(R.id.directory_tab_listview);

        inflater.inflate(R.layout.directory_tab_fragment, container, false);
        listview.setAdapter(adaptor);

        ArrayList<DirectoryTabListViewItem> list = LocalImageManager.getDirectoryTabListViewItem(getContext());
        for(int i = 0;i < list.size(); i++){
            adaptor.addItem(list.get(i));
        }
        // Inflate the layout for this fragment

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView parent, View v, int position, long id){
                Intent intent = new Intent(getContext(), SearchActivity.class);
                intent.putExtra("bucket", ((DirectoryTabListViewItem)adaptor.getItem(position)).getId() );
                startActivity(intent);
            }
        });
        return view;
    }

}
