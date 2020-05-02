package com.example.sunhan.pethome1.ui.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.sunhan.pethome1.R;

public class ServiceHuliFragment extends Fragment{

    View view;

    private ListView listView_fujin;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_servicehuli, container,false);
        initData();
        initViews(view);
        return view;
    }

    private void initData() {

    }

    private void initViews(View view) {

    }
}
