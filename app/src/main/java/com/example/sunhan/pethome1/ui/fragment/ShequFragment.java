package com.example.sunhan.pethome1.ui.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.example.sunhan.pethome1.R;
import com.example.sunhan.pethome1.ui.base.BaseFragment;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

public class ShequFragment extends BaseFragment {
    View view;

    private TextView jingxuan;
    private TextView shiping;
    private TextView bendi;
    private TextView zuixin;

    private JingXuanFragment fragment1;
    private ShiPingFragment fragment2;
    private BenDiFragment fragment3;
    private ZuiXinFragment fragment4;

    public static final int HEADER_JINGXUAN = 1;
    public static final int HEADER_SHIPING = 2;
    public static final int HEADER_BENDI = 3;
    public static final int HEADER_ZUIXIN = 4;

    public int currentFragmentType = -1;

    private PullToRefreshListView mRefreshListView;
    private ListView mListView;

    private View headView;

    private static final String tag="JingXuan";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_shequ, container, false);
        initData(view);
        //initViews(view);
        return view;
    }

    private void initData(View view) {
        View rlheaderView = view.findViewById(R.id.home_rlheader);
        jingxuan = (TextView)rlheaderView.findViewById(R.id.jingxuan);
        shiping = (TextView)rlheaderView.findViewById(R.id.shiping);
        bendi = (TextView)rlheaderView.findViewById(R.id.bendi);
        zuixin = (TextView)rlheaderView.findViewById(R.id.zuixin);
        jingxuan.setOnClickListener(onClicker);
        shiping.setOnClickListener(onClicker);
        bendi.setOnClickListener(onClicker);
        zuixin.setOnClickListener(onClicker);
        loadFragment(HEADER_JINGXUAN);
    }

    /**
    private void initViews(View view) {

        //header头部
        headView = LayoutInflater.from(getActivity()).inflate(R.layout.shequ_head_page, null);

        mRefreshListView = (PullToRefreshListView)view.findViewById(R.id.home_pull_to_refresh_listView);
        mListView = mRefreshListView.getRefreshableView();
        mListView.addHeaderView(headView);
        mListView.setHeaderDividersEnabled(false);
    }
     */

    private View.OnClickListener onClicker = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.jingxuan:
                    switchFragment(HEADER_JINGXUAN);
                    break;
                case R.id.shiping:
                    switchFragment(HEADER_SHIPING);
                    break;
                case R.id.bendi:
                    switchFragment(HEADER_BENDI);
                    break;
                case R.id.zuixin:
                    switchFragment(HEADER_ZUIXIN);
                    break;
            }
        }
    };

    private void switchFragment(int type) {
        switch (type){
            case HEADER_JINGXUAN:
                loadFragment(HEADER_JINGXUAN);
                break;
            case HEADER_SHIPING:
                loadFragment(HEADER_SHIPING);
                break;
            case HEADER_BENDI:
                loadFragment(HEADER_BENDI);
                break;
            case HEADER_ZUIXIN:
                loadFragment(HEADER_ZUIXIN);
                break;
        }
    }

    private void loadFragment(int type) {
        FragmentManager fragmentManager = getChildFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        switch (type) {
            case HEADER_JINGXUAN:
                if (fragment1 == null) {
                    fragment1 = new JingXuanFragment();
                    transaction.add(R.id.rlheader, fragment1, "精选");
                    Log.i(tag, "执行了精选fragment");
                } else {
                    transaction.show(fragment1);
                    Log.i(tag, "执行了精选fragment");
                }
                if (fragment2 != null) {
                    transaction.hide(fragment2);
                }
                if (fragment3 != null) {
                    transaction.hide(fragment3);
                }
                if (fragment4 != null) {
                    transaction.hide(fragment4);
                }
                currentFragmentType = HEADER_JINGXUAN;
                transaction.commit();
                break;

            case HEADER_BENDI:
                if (fragment3 == null) {
                    fragment3 = new BenDiFragment();
                    transaction.add(R.id.rlheader, fragment3, "本地");
                    Log.i(tag, "执行了本地fragment");
                } else {
                    transaction.show(fragment3);
                    Log.i(tag, "执行了本地fragment");
                }
                if (fragment1 != null) {
                    transaction.hide(fragment1);
                }
                if (fragment2 != null) {
                    transaction.hide(fragment2);
                }
                if (fragment4 != null) {
                    transaction.hide(fragment4);
                }
                currentFragmentType = HEADER_BENDI;
                transaction.commit();
                break;

            case HEADER_SHIPING:
                if (fragment2 == null) {
                    fragment2 = new ShiPingFragment();
                    transaction.add(R.id.rlheader, fragment2, "视频");
                    Log.i(tag, "执行了视频fragment");
                } else {
                    transaction.show(fragment2);
                    Log.i(tag, "执行了视频fragment");
                }
                if (fragment1 != null) {
                    transaction.hide(fragment1);
                }
                if (fragment3 != null) {
                    transaction.hide(fragment3);
                }
                if (fragment4 != null) {
                    transaction.hide(fragment4);
                }
                currentFragmentType = HEADER_SHIPING;
                transaction.commit();
                break;

            case HEADER_ZUIXIN:
                if (fragment4 == null) {
                    fragment4 = new ZuiXinFragment();
                    transaction.add(R.id.rlheader, fragment4, "最新");
                } else {
                    transaction.show(fragment4);
                }
                if (fragment1 != null) {
                    transaction.hide(fragment1);
                }
                if (fragment2 != null) {
                    transaction.hide(fragment2);
                }
                if (fragment3 != null) {
                    transaction.hide(fragment3);
                }
                currentFragmentType = HEADER_ZUIXIN;
                transaction.commit();
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
