package com.littledog.activity;


import android.app.ListFragment;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.littledog.bitsandpizzsa.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class PastaFragment extends ListFragment {


    public PastaFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(
                inflater.getContext(),
                android.R.layout.simple_list_item_1,
                getResources().getStringArray(R.array.pasta)
        );
        setListAdapter(adapter);
        return super.onCreateView(inflater,container,savedInstanceState);
    }

}
