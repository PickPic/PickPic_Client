package com.pickpic.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.pickpic.Fragment.DirectoryTabFragment;
import com.pickpic.Fragment.TimeTabFragment;
import com.pickpic.Fragment.TagTabFragment;

public class TabPagerAdapter extends FragmentPagerAdapter {

    int tabCount;

    public TabPagerAdapter(FragmentManager fm, int numberOfTabs) {
        super(fm);
        this.tabCount = numberOfTabs;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                DirectoryTabFragment tab1 = new DirectoryTabFragment();
                return tab1;
            case 1:
                TimeTabFragment tab2 = new TimeTabFragment();
                return tab2;
            case 2:
                TagTabFragment tab3 = new TagTabFragment();
                return tab3;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return tabCount;
    }
}

