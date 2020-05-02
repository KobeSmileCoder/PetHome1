package com.example.sunhan.pethome1.ui.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.sunhan.pethome1.R;
import com.example.sunhan.pethome1.ui.base.BaseFragment;

public class LieBiaoFragment extends BaseFragment {

    View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_liebiao, container, false);
        return view;
    }
}
