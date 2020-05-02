package com.example.sunhan.pethome1.ui.fragment;

import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridView;
import android.widget.LinearLayout;

import com.example.sunhan.pethome1.R;
import com.example.sunhan.pethome1.entity.ShequGridInfo;
import com.example.sunhan.pethome1.ui.activities.LoginActivity;
import com.example.sunhan.pethome1.ui.adapter.GridAdapter;
import com.example.sunhan.pethome1.ui.widget.MyToolbar;
import com.example.sunhan.pethome1.util.Constant;

import java.util.ArrayList;
import java.util.List;

public class InfoFragment extends Fragment implements View.OnClickListener {

    View view;

    public static MyToolbar toolbar;
    private List<ShequGridInfo> pageOneData = new ArrayList<>();
    private List<ShequGridInfo> pageTwoData = new ArrayList<>();
    private List<ShequGridInfo> pageThreeData = new ArrayList<>();

    private View liebiao_view;
    private View xiangce_view;

    private LieBiaoFragment fragment1;
    private XiangCeFragment fragment2;

    public static final int HEADER_LIEBIAO = 1;
    public static final int HEADER_XIANGCE = 2;

    public int currentFragmentType = -1;

    private Button loginButton;

    LinearLayout selfInfoItem;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (view == null) {
            view = inflater.inflate(R.layout.fragment_info, null);
            initData();
            initViews(view);
            autoScroll();
        }
        return view;
    }

    private void initData() {
        String[] gridTitles1 = getResources().getStringArray(R.array.info_bar1_labels);
        TypedArray typedArray1 = getResources().obtainTypedArray(R.array.info_bar1_icon);
        for (int i = 0; i<gridTitles1.length; i++) {
            pageOneData.add(new ShequGridInfo(typedArray1.getResourceId(i,0), gridTitles1[i]));
        }

        String[] gridTitles2 = getResources().getStringArray(R.array.info_bar2_labels);
        TypedArray typedArray2 = getResources().obtainTypedArray(R.array.info_bar2_icon);
        for (int i = 0; i<gridTitles2.length; i++) {
            pageTwoData.add(new ShequGridInfo(typedArray2.getResourceId(i,0), gridTitles2[i]));
        }

        String[] gridTitles3 = getResources().getStringArray(R.array.info_bar3_labels);
        TypedArray typedArray3 = getResources().obtainTypedArray(R.array.info_bar3_icon);
        for (int i = 0; i<gridTitles3.length; i++) {
            pageThreeData.add(new ShequGridInfo(typedArray3.getResourceId(i,0), gridTitles3[i]));
        }

        liebiao_view = view.findViewById(R.id.liebiao_view);
        xiangce_view = view.findViewById(R.id.xiangce_view);
        liebiao_view.setOnClickListener(onClicker);
        xiangce_view.setOnClickListener(onClicker);
        loadFragment(HEADER_LIEBIAO);
    }

    private void initViews(View view) {
        toolbar = (MyToolbar)view.findViewById(R.id.toolbar);
        toolbar.setBackgroundColor(getResources().getColor(R.color.orange));
        toolbar.setTitle("我的");
        toolbar.setTitleTextColor(getResources().getColor(R.color.white));

        GridView gridView1 = (GridView)view.findViewById(R.id.info_gridview1);
        gridView1.setAdapter(new GridAdapter(getActivity(), pageOneData));

        GridView gridView2 = (GridView)view.findViewById(R.id.info_gridview2);
        gridView2.setAdapter(new GridAdapter(getActivity(), pageTwoData));

        GridView gridView3 = (GridView)view.findViewById(R.id.info_gridview3);
        gridView3.setAdapter(new GridAdapter(getActivity(), pageTwoData));

        loginButton = (Button)view.findViewById(R.id.login);
        selfInfoItem = (LinearLayout)view.findViewById(R.id.self_info);

        loginButton.setOnClickListener(this);
        selfInfoItem.setOnClickListener(this);
    }

    private void autoScroll() {

    }

    private View.OnClickListener onClicker = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.liebiao_view:
                    switchFragment(HEADER_LIEBIAO);
                    break;
                case R.id.xiangce_view:
                    switchFragment(HEADER_XIANGCE);
                    break;
            }

        }
    };

    private void switchFragment(int type) {
        switch (type) {
            case HEADER_LIEBIAO:
                loadFragment(HEADER_LIEBIAO);
                break;
            case HEADER_XIANGCE:
                loadFragment(HEADER_XIANGCE);
                break;
        }
    }

    private void loadFragment(int type) {
        FragmentManager fragmentManager = getChildFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        switch (type) {
            case HEADER_LIEBIAO:
                if (fragment1 == null) {
                    fragment1 = new LieBiaoFragment();
                    transaction.add(R.id.info_header, fragment1, "列表");
                } else {
                    transaction.show(fragment1);
                }
                if (fragment2 != null) {
                    transaction.hide(fragment2);
                }
                currentFragmentType = HEADER_LIEBIAO;
                transaction.commit();
                break;
            case HEADER_XIANGCE:
                if (fragment2 == null) {
                    fragment2 = new XiangCeFragment();
                    transaction.add(R.id.info_header, fragment2, "相册");
                } else {
                    transaction.show(fragment2);
                }
                if (fragment1 != null) {
                    transaction.hide(fragment1);
                }
                currentFragmentType = HEADER_XIANGCE;
                transaction.commit();
                break;
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login:
                Intent loginIntent = new Intent(getActivity(), LoginActivity.class);
                startActivityForResult(loginIntent, 0);
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case 0:
                if (Constant.is_login) {
                    loginButton.setVisibility(View.GONE);
                    selfInfoItem.setVisibility(View.VISIBLE);
                } else {
                    loginButton.setVisibility(View.VISIBLE);
                    selfInfoItem.setVisibility(View.GONE);
                }
                break;
        }
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
    }
}
