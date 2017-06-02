package com.pickpic.Adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.pickpic.R;


/**
 * Created by sekyo on 2017-05-30.
 */

public class ManualPagerAdapter extends PagerAdapter {
    Button skipButton;
    Context mContext;
    LayoutInflater mLayoutInflater;

    private static final Integer[] mResources= {R.drawable.manual1, R.drawable.manual2, R.drawable.manual3, R.drawable.manual4, R.drawable.manual5, R.drawable.manual6, R.drawable.manual7, R.drawable.manual8};

    public ManualPagerAdapter(Context context, Button skipButton) {
        mContext = context;
        mLayoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.skipButton = skipButton;
    }

    @Override
    public int getCount() {
        return mResources.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == ((LinearLayout) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View itemView = mLayoutInflater.inflate(R.layout.pager_item, container, false);

        ImageView imageView = (ImageView) itemView.findViewById(R.id.pagerItemImageView);
        imageView.setImageResource(mResources[position]);
        container.addView(itemView);
        if(position == mResources.length - 1)
            skipButton.setText("OK");
        return itemView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((LinearLayout) object);
    }
}
