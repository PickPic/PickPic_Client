package com.pickpic.Fragment;


import android.content.Intent;
import android.graphics.BitmapFactory;
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

import com.pickpic.Activity.GalleryActivity;
import com.pickpic.Activity.SearchActivity;
import com.pickpic.Adapter.TimeTabGridViewAdaptor;
import com.pickpic.Backend.LocalImageManager;
import com.pickpic.Item.DirectoryTabListViewItem;
import com.pickpic.Item.GridViewItem;
import com.pickpic.R;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class TimeTabFragment extends Fragment {

    private View view;

    public TimeTabFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.time_tab_fragment, container, false);
        final GridView gridView = (GridView) view.findViewById(R.id.time_tab_gridview);
        TimeTabGridViewAdaptor adaptor = new TimeTabGridViewAdaptor(gridView, BitmapFactory.decodeResource(getResources(), R.drawable.blank));
        inflater.inflate(R.layout.time_tab_fragment, container, false);
        gridView.setAdapter(adaptor);

        ArrayList<GridViewItem>  gridViewItems = LocalImageManager.getTimeTabGridViewItemList(getContext());
        for(int i = 0;i < gridViewItems.size(); i++)
            adaptor.addItem(gridViewItems.get(i));

        final ArrayList<String> imagepath = LocalImageManager.getAllImagePath(getContext(), "DESC");

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView parent, View v, int position, long id){
                String selectedimage = imagepath.get(position);

                Intent intent = new Intent(getActivity(), GalleryActivity.class);
                intent.putExtra("filepath", selectedimage);
                startActivity(intent);

            }
        });
        return view;
    }

}
