package com.example.sunhan.pethome1.ui.fragment;

import android.content.res.TypedArray;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.print.PrinterId;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.sunhan.pethome1.R;
import com.example.sunhan.pethome1.common.AppConstant;
import com.example.sunhan.pethome1.entity.GoodsInfo;
import com.example.sunhan.pethome1.entity.ShequGridInfo;
import com.example.sunhan.pethome1.listener.ViewPagerListener;
import com.example.sunhan.pethome1.network.CallServer;
import com.example.sunhan.pethome1.network.HttpListener;
import com.example.sunhan.pethome1.ui.View.AutoVerticalScrollTextView;
import com.example.sunhan.pethome1.ui.adapter.BannerPagerAdapter;
import com.example.sunhan.pethome1.ui.adapter.GoodsListAdapter;
import com.example.sunhan.pethome1.ui.adapter.GridAdapter;
import com.example.sunhan.pethome1.ui.base.BaseFragment;
import com.example.sunhan.pethome1.ui.widget.Indicator;
import com.example.sunhan.pethome1.util.ToastUtil;
import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.yolanda.nohttp.NoHttp;
import com.yolanda.nohttp.RequestMethod;
import com.yolanda.nohttp.rest.Request;
import com.yolanda.nohttp.rest.Response;

import java.util.ArrayList;
import java.util.List;

public class JingXuanFragment extends BaseFragment implements HttpListener<String> {

    private static final int GOOD_REQUEST = 0X01;

    private View view;

    private PullToRefreshListView mRefreshListView;
    private ListView mListView;

    private List<String> datas;
    private ArrayAdapter<String> adapter;
    private List<ShequGridInfo> pageData = new ArrayList<>();

    private int[] imgRes = new int[]{R.drawable.banner01, R.drawable.banner02, R.drawable.banner03};
    private  Handler mHandler = new Handler();
    //广告轮播
    private ViewPager bannerPager;
    private Indicator bannerIndicator;
    //新闻竖直轮播
    private String[] strings={"我的剑，就是你的剑!","俺也是从石头里蹦出来得!","我用双手成就你的梦想!","人在塔在!","犯我德邦者，虽远必诛!","我会让你看看什么叫残忍!","我的大刀早已饥渴难耐了!"};
    private AutoVerticalScrollTextView verticalScrollTV;
    private boolean isRunning = true;
    private int number = 0;
    private static final String tag = "ChongDie";
    //最下面的列表
    private GoodsListAdapter mGoodsListAdapter;
    private List<GoodsInfo.ResultBean.GoodlistBean> mGoodlist = new ArrayList<>();
    //是否正在刷新
    private boolean isRefreshing = false;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if(view == null) {
            view = inflater.inflate(R.layout.fragment_jingxuan, container, false);
            initData();
            initViews(view);
            autoScroll();
        }
        return view;
    }

    private void initData() {
        String[] gridTitles = getResources().getStringArray(R.array.shequ_bar_labels);
        TypedArray typedArray = getResources().obtainTypedArray(R.array.shequ_bar_icon);
        for (int i = 0; i<gridTitles.length; i++) {
            pageData.add(new ShequGridInfo(typedArray.getResourceId(i,0), gridTitles[i]));
        }

        Request<String> goodRequest = NoHttp.createStringRequest(AppConstant.RECOMMEND_URL, RequestMethod.GET);
        CallServer.getInstance().add(getActivity(), GOOD_REQUEST, goodRequest, this, true, true);
    }

    private void autoScroll() {
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                int currentItem = bannerPager.getCurrentItem();
                bannerPager.setCurrentItem(currentItem+1, true);
                mHandler.postDelayed(this, 2000);
            }
        }, 2000);
    }

    private void initViews(View view) {

        //header头部
        View headView = LayoutInflater.from(getActivity()).inflate(R.layout.shequ_head_page, null);
        //banner
        View bannerView = headView.findViewById(R.id.shequ_head_include_banner);
        bannerPager = bannerView.findViewById(R.id.shequ_banner_pager);
        bannerIndicator = bannerView.findViewById(R.id.shequ_banner_indicator);
        bannerPager.setAdapter(new BannerPagerAdapter(getChildFragmentManager(), imgRes));
        bannerPager.addOnPageChangeListener(new ViewPagerListener(bannerIndicator));

        //最下面的列表功能
        datas=new ArrayList<>();
        for(int i=1;i<=50;i++){
        datas.add("item---------"+i);
        }
        adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, datas);
        mRefreshListView = (PullToRefreshListView)view.findViewById(R.id.home_pull_to_refresh_listView);
        mListView = mRefreshListView.getRefreshableView();
        mListView.setAdapter(adapter);

        //gridView实现功能方阵
        //View page = LayoutInflater.from(getActivity()).inflate(R.layout.shequ_gridview, null);
        View page = headView.findViewById(R.id.shequ_gridView);
        GridView gridView = (GridView)page.findViewById(R.id.shequ_gridView);
        gridView.setAdapter(new GridAdapter(getActivity(), pageData));
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            }
        });

        //竖直滚动
        View verticalRoll = headView.findViewById(R.id.shequ_head_include_verticalroll);
        verticalScrollTV = (AutoVerticalScrollTextView) verticalRoll.findViewById(R.id.textview_auto_roll);
        verticalScrollTV.setText(strings[0]);
        new Thread(){
            @Override
            public void run() {
                while (isRunning){
                    SystemClock.sleep(3000);
                    handler.sendEmptyMessage(199);
                }
            }
        }.start();

        //添加头部功能
        mListView.addHeaderView(headView);
        Log.i("ChongDie", "initViews");
        //mListView.addHeaderView(page);
        mListView.setHeaderDividersEnabled(false);

        int headerViewsCount = mListView.getHeaderViewsCount();
        mGoodsListAdapter = new GoodsListAdapter(getActivity(),mGoodlist,headerViewsCount);
        //mRefreshListView.setAdapter(mGoodsListAdapter);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            }
        });

        //下拉刷新
        mRefreshListView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ListView>() {
            @Override
            public void onRefresh(PullToRefreshBase<ListView> refreshView) {
                isRefreshing = true;
                Request<String> goodRequest = NoHttp.createStringRequest(AppConstant.RECOMMEND_URL, RequestMethod.GET);
                CallServer.getInstance().add(getActivity(), GOOD_REQUEST, goodRequest,JingXuanFragment.this, true, true);
            }
        });
    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {

            if (msg.what == 199) {
                verticalScrollTV.next();
                number++;
                verticalScrollTV.setText(strings[number%strings.length]);
            }

        }
    };

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onSucceed(int what, Response<String> response) {
        if (isRefreshing) {
            mRefreshListView.onRefreshComplete();
            isRefreshing = false;
        }
        switch (what) {
            case GOOD_REQUEST:
                /**
                Gson gson = new Gson();
                GoodsInfo goodsInfo = gson.fromJson(response.get(),GoodsInfo.class);
                List<GoodsInfo.ResultBean.GoodlistBean> goodlistBeen = goodsInfo.getResult().getGoodlist();
                mGoodlist.clear();
                mGoodlist.addAll(goodlistBeen);
                mGoodsListAdapter.notifyDataSetChanged();
                 */
        }
    }

    @Override
    public void onFailed(int what, Response<String> response) {
        if (isRefreshing) {
            mRefreshListView.onRefreshComplete();
            isRefreshing = false;
            ToastUtil.show(getActivity(), "刷新失败");
        }
    }
}
