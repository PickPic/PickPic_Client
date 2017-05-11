package com.pickpic.Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pickpic.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class DirectoryTabFragment extends Fragment {


    public DirectoryTabFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.directory_tab, container, false);
    }

}
