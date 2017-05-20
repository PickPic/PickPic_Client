package com.pickpic.Fragment;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;

import com.pickpic.Adapter.TagListAdapter;
import com.pickpic.Item.TagListItem;
import com.pickpic.R;

import java.util.ArrayList;

/**
 * Created by yewon on 2017-05-09.
 */

public class TagFragment extends Fragment {

    ListView tagView;
    TagListAdapter tagListAdapter;
    ArrayList<TagListItem> tagListItems;
    ImageButton addtag;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_tag,container,false);

        addtag = (ImageButton)view.findViewById(R.id.addTagBtn);
        addtag.setOnClickListener(addTagListener);

        tagView = (ListView)view.findViewById(R.id.taglist);
        tagListItems = new ArrayList<TagListItem>();

        //임시, db에서 가져오는 부분, 삭제 제대로 되는지 확인하기
        tagListItems.add(new TagListItem("태그"));
        tagListItems.add(new TagListItem("태그"));

        tagListAdapter = new TagListAdapter(this.getContext(), tagListItems);
        tagView.setAdapter(tagListAdapter);

        return view;
    }

    private View.OnClickListener addTagListener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            tagInputDialog();
            tagListAdapter.notifyDataSetChanged();
        }
    };


    private void tagInputDialog() {
        AlertDialog.Builder taginput = new AlertDialog.Builder(getContext());
        taginput.setTitle("Add tag");

        final EditText tag = new EditText(getContext());
        taginput.setView(tag);

        taginput.setPositiveButton("Add", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String temp = tag.getText().toString();
                //db에 태그 보내기
                tagListItems.add(new TagListItem(temp));
                dialog.dismiss();
            }
        });

        taginput.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        taginput.show();

    }

}
