package com.pickpic.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.pickpic.Fragment.DirectoryTabFragment;
import com.pickpic.Fragment.TimeTabFragment;
import com.pickpic.Fragment.TagTabFragment;

public class TabAdapter extends FragmentPagerAdapter {

    int tabCount;

    public TabAdapter(FragmentManager fm, int numberOfTabs) {
        super(fm);
        this.tabCount = numberOfTabs;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                DirectoryTabFragment directoryTabFragment = new DirectoryTabFragment();
                return directoryTabFragment;
            case 1:
                TimeTabFragment timeTabFragment = new TimeTabFragment();
                return timeTabFragment;
            case 2:
                TagTabFragment tagTabFragment = new TagTabFragment();
                return tagTabFragment;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return tabCount;
    }
}

