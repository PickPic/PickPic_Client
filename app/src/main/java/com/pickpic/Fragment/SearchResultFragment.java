package com.pickpic.Fragment;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.pickpic.Activity.SearchActivity;
import com.pickpic.Adapter.SearchResultAdapter;
import com.pickpic.Adapter.TimeTabGridViewAdaptor;
import com.pickpic.Backend.LocalImageManager;
import com.pickpic.Backend.TagDBManager;
import com.pickpic.Item.RecommendTagListItem;
import com.pickpic.R;

import java.util.ArrayList;

public class SearchResultFragment extends Fragment {

    private View view;
    private String getDataFromSearch;

    public SearchResultFragment(){

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if(getActivity() != null && getActivity() instanceof SearchActivity){
            getDataFromSearch = ((SearchActivity)getActivity()).getData();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_search_result, container, false);
        SearchResultAdapter adapter = new SearchResultAdapter();
        GridView gridView = (GridView) view.findViewById(R.id.search_result_gridview);

        inflater.inflate(R.layout.fragment_search_result, container, false);
        gridView.setAdapter(adapter);

        //검색된 태그의 스트링을 어레이리스트로 받아서 넣기
        TagDBManager tagDBManager = new TagDBManager(getActivity());


        ArrayList<String> searchedtag = new ArrayList<String>();
        searchedtag.add(getDataFromSearch);

        //이미지 가져오기
        LocalImageManager.getGridViewItemList(getContext(), tagDBManager.getPathsByTags(searchedtag));

        return inflater.inflate(R.layout.search_result_item, container, false);
    }

}
