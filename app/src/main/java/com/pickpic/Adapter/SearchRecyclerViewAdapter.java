package com.pickpic.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.pickpic.Item.SearchRecyclerViewItem;
import com.pickpic.R;

import java.util.ArrayList;

/**
 * Created by sekyo on 2017-05-25.
 */

public class SearchRecyclerViewAdapter extends RecyclerView.Adapter<SearchRecyclerViewAdapter.ViewHolder> {

    private ArrayList<String> items;

    public SearchRecyclerViewAdapter(){
        items = new ArrayList<String>();
    }
    public void addItem(String input){
        items.add(input);
        notifyDataSetChanged();
    }
  @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.search_recycleview_item,viewGroup,false);
        return new ViewHolder(view, items, this);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        String item = items.get(position);
        viewHolder.textView.setText(items.get(position));
        viewHolder.itemView.setTag(item);

    }

    @Override
    public int getItemCount() {
        return items.size();
    }
    public ArrayList<String> getItems(){
        return items;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        public ImageButton imageButton;
        public TextView textView;

        public ViewHolder(View itemView, final ArrayList<String> items, final SearchRecyclerViewAdapter searchRecyclerViewAdapter){
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.search_activity_recycleview_item_text);
            imageButton = (ImageButton) itemView.findViewById(R.id.search_activity_recycleview_item_remove_btn);

            imageButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    items.remove(textView.getText().toString());
                    searchRecyclerViewAdapter.notifyDataSetChanged();
                }
            });
        }

    }
}

