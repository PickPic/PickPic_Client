package com.pickpic.Fragment;

import android.app.ListFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

import com.pickpic.Adapter.RecommendTagListAdapter;
import com.pickpic.Backend.LocalImageManager;
import com.pickpic.Backend.TagDBManager;
import com.pickpic.Item.RecommendTagListItem;
import com.pickpic.R;

import java.util.ArrayList;
import java.util.List;

public class RecommendTagListFragment extends Fragment {

    RecommendTagListAdapter adapter;
    ListView recommendtagview;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_recommend_tag_list, container, false);
        recommendtagview = (ListView)view.findViewById(R.id.recommendtaglist);


        final EditText editText = (EditText) getActivity().findViewById(R.id.inputText) ;

        //클릭된 태그 처리 이벤트
        recommendtagview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView parent, View v, int position, long id) {

                RecommendTagListItem getTouchedTag = (RecommendTagListItem) parent.getItemAtPosition(position);
                String getTouched = getTouchedTag.getTag();
                editText.setText(getTouched);  //터치된 recommendtag로 input text변경

            }
        });

        ArrayList<RecommendTagListItem> tagListItems = new ArrayList<RecommendTagListItem>();

        TagDBManager tagDBManager = new TagDBManager(getActivity());
        //tagDBManager.getAllTags();

        List<String> list = tagDBManager.getAllTags();
        String[] stringArray = list.toArray(new String[0]);

        //tagall tagrecommend listview에 추가하기
        for(int i=0; i<stringArray.length; i++){
            tagListItems.add(new RecommendTagListItem(stringArray[i]));
        }

        //tagListItems.add(new RecommendTagListItem("tag1"));
        //tagListItems.add(new RecommendTagListItem("tag2"));

        adapter = new RecommendTagListAdapter(this.getContext(), tagListItems);

        recommendtagview.setAdapter(adapter); //recommendtagview가 문제!


        return view;
    }


}


