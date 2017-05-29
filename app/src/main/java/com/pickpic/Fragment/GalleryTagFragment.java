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

import com.pickpic.Adapter.GalleryTagListAdapter;
import com.pickpic.Backend.TagDBManager;
import com.pickpic.Item.GalleryTagListItem;
import com.pickpic.R;

import java.util.ArrayList;

/**
 * Created by yewon on 2017-05-09.
 */

public class GalleryTagFragment extends Fragment {

    ListView tagView;
    GalleryTagListAdapter galleryTagListAdapter;
    ArrayList<GalleryTagListItem> galleryTagListItems;
    ImageButton addTag;
    String imageFilePath;

    public GalleryTagFragment(String filepath) {
        this.imageFilePath = filepath;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_tag,container,false);

        addTag = (ImageButton)view.findViewById(R.id.addTagBtn);
        addTag.setOnClickListener(addTagListener);

        tagView = (ListView)view.findViewById(R.id.tagList);
        galleryTagListItems = new ArrayList<GalleryTagListItem>();

        ArrayList<String> getTags = (new TagDBManager(getContext())).getTagsByPath(imageFilePath);
        if(getTags.size() == 0) {
            galleryTagListItems.add(new GalleryTagListItem("no tags"));
        } else {
            for (int i = 0; i < getTags.size(); i++) {
                galleryTagListItems.add(new GalleryTagListItem(getTags.get(i)));
            }
        }

        galleryTagListAdapter = new GalleryTagListAdapter(this.getContext(), galleryTagListItems, imageFilePath);
        tagView.setAdapter(galleryTagListAdapter);

        return view;
    }

    private View.OnClickListener addTagListener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            tagInputDialog();
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
                (new TagDBManager(getContext())).insertTag(imageFilePath, temp, TagDBManager.NORMAL_TAG);
                galleryTagListItems.add(new GalleryTagListItem(temp));
                galleryTagListAdapter.notifyDataSetChanged();
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
