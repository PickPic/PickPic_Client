package com.pickpic.Fragment;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.pickpic.Adapter.SearchResultAdapter;
import com.pickpic.Adapter.TimeTabGridViewAdaptor;
import com.pickpic.Backend.LocalImageManager;
import com.pickpic.R;

public class SearchResultFragment extends Fragment {

    private View view;

    public SearchResultFragment(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_search_result, container, false);
        SearchResultAdapter adapter = new SearchResultAdapter();
        GridView gridView = (GridView) view.findViewById(R.id.search_result_gridview);

        inflater.inflate(R.layout.fragment_search_result, container, false);
        gridView.setAdapter(adapter);

       //이미지 가져오기


        return inflater.inflate(R.layout.search_result_item, container, false);
    }

}
