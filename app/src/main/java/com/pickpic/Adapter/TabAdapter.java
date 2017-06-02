package com.pickpic.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.pickpic.Fragment.DirectoryTabFragment;
import com.pickpic.Fragment.TagTabFragment;
import com.pickpic.Fragment.TimeTabFragment;

public class TabAdapter extends FragmentPagerAdapter {

    int tabCount;
    DirectoryTabFragment directoryTabFragment;
    TimeTabFragment timeTabFragment;
    TagTabFragment tagTabFragment;
    public TabAdapter(FragmentManager fm, int numberOfTabs) {
        super(fm);
        this.tabCount = numberOfTabs;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                directoryTabFragment = new DirectoryTabFragment();
                return directoryTabFragment;
            case 1:
                timeTabFragment = new TimeTabFragment();
                return timeTabFragment;
            case 2:
                tagTabFragment = new TagTabFragment();
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

