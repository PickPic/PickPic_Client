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
import com.pickpic.Item.DirectoryTabListViewItem;
import com.pickpic.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class DirectoryTabFragment extends Fragment {


    private View view;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.directory_tab_fragment, container, false);
        DirectoryTabListViewAdaptor adaptor = new DirectoryTabListViewAdaptor();

        ListView listview = (ListView) view.findViewById(R.id.directory_tab_listview);

        inflater.inflate(R.layout.directory_tab_fragment, container, false);
        listview.setAdapter(adaptor);

        adaptor.addItem(ContextCompat.getDrawable(getActivity(), R.drawable.ic_folder_black_48dp), "Title", "200");
        adaptor.addItem(ContextCompat.getDrawable(getActivity(), R.drawable.ic_access_time_black_48dp), "title2", "5");
        adaptor.addItem(ContextCompat.getDrawable(getActivity(), R.drawable.ic_local_offer_black_48dp), "title3", "11");
        // Inflate the layout for this fragment

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView parent, View v, int position, long id){
                Intent intent = new Intent(getContext(), SearchActivity.class);
                startActivity(intent);
            }
        });
        return view;
    }

}
