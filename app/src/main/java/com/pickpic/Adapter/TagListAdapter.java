package com.pickpic.Adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import com.pickpic.Backend.TagDBManager;
import com.pickpic.Item.TagListItem;
import com.pickpic.R;

import java.util.ArrayList;

/**
 * Created by yewon on 2017-05-14.
 */

public class TagListAdapter extends BaseAdapter {

    Context context;
    ArrayList<TagListItem> tagListItemArrayList;
    String imagefilepath;
    TextView tag_name;
    ImageButton deletetag;

    public TagListAdapter(Context context, ArrayList<TagListItem> tagListItemArrayList, String filepath) {
        this.context = context;
        this.tagListItemArrayList = tagListItemArrayList;
        this.imagefilepath = filepath;
    }

    @Override
    public int getCount() {
        return this.tagListItemArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return tagListItemArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if(convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.tag_list, null);
        }
        tag_name = (TextView)convertView.findViewById(R.id.name);
        tag_name.setText(tagListItemArrayList.get(position).getTag());

        deletetag = (ImageButton)convertView.findViewById(R.id.deleteTag);
        final int deletekey = position;
        final View finalConvertView = convertView;
        deletetag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String selectedtag = tagListItemArrayList.get(deletekey).getTag();

                AlertDialog.Builder tagdelete = new AlertDialog.Builder(finalConvertView.getContext());
                tagdelete.setTitle("Delete tag");
                tagdelete.setMessage(imagefilepath + "\n" + "Really want to delete " + selectedtag + "?");

                tagdelete.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        tagListItemArrayList.remove(deletekey);
                        (new TagDBManager(context)).removeTag(imagefilepath, selectedtag);
                        dialog.dismiss();
                        notifyDataSetChanged();
                    }
                });

                tagdelete.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

                tagdelete.show();

            }
        });

        return convertView;
    }
}
