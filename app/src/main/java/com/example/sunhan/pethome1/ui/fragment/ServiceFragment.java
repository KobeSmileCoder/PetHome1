package com.example.sunhan.pethome1.ui.fragment;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.location.BDAbstractLocationListener;
import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.example.sunhan.pethome1.R;
import com.example.sunhan.pethome1.entity.GoodsInfo;
import com.example.sunhan.pethome1.ui.activities.AllSeller;
import com.example.sunhan.pethome1.ui.activities.ChooseLocationPlace;
import com.example.sunhan.pethome1.ui.activities.DetailActivity;
import com.example.sunhan.pethome1.ui.activities.MainActivity;
import com.example.sunhan.pethome1.ui.adapter.GoodsListAdapter;
import com.example.sunhan.pethome1.ui.base.BaseFragment;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.util.ArrayList;
import java.util.List;

import static cn.bmob.v3.Bmob.getApplicationContext;

public class ServiceFragment extends BaseFragment implements View.OnClickListener{

    public static final String GOODS_ID = "goodsId";
    public static final String GOODS_SEVEN_REFUND = "sevenRefund";
    public static final String GOODS_TIME_REFUND = "timeRefund";
    public static final String GOODS_BOUGHT = "bought";

    View view;

    private View service_fujin;
    private View service_huli;

    private ServiceFujinFragment fragment1;
    private ServiceHuliFragment fragment2;

    public static final int HEADER_FUJIN = 1;
    public static final int HEADER_HULI = 2;

    public int currentListViewType = -1;

    private PullToRefreshListView mRefreshListView;
    private ListView mListView;
    private List<String> datas_fujin;
    private List<String> datas_huli;
    private ArrayAdapter<String> adapter_fujin;
    private ArrayAdapter<String> adapter_huli;
    private GoodsListAdapter mGoodsListAdapter;
    private List<GoodsInfo.ResultBean.GoodlistBean> mGoodlist = new ArrayList<>();

    View headView;
    private View service_place;
    private View service_all_seller;

    private TextView service_place_name;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (view == null) {
            view = inflater.inflate(R.layout.fragment_service, null);
            initData();
            initViews(view);
        }
        return view;
    }

    private void initData() {
        headView = LayoutInflater.from(getActivity()).inflate(R.layout.service_head_page, null);
        service_place = (LinearLayout)view.findViewById(R.id.service_place);
        service_all_seller = (LinearLayout)headView.findViewById(R.id.service_all_seller);
        service_place.setOnClickListener(this);
        service_all_seller.setOnClickListener(this);

        service_place_name = view.findViewById(R.id.service_place_name);
        service_place_name.setText(MainActivity.location_name);
    }

    private void initViews(View view) {
        //该fragment的头部
        service_fujin = headView.findViewById(R.id.service_fujin);
        service_huli = headView.findViewById(R.id.service_huli);
        service_fujin.setOnClickListener(this);
        service_huli.setOnClickListener(this);

        mRefreshListView = (PullToRefreshListView)view.findViewById(R.id.service_pull_to_refresh_listView);
        mListView = mRefreshListView.getRefreshableView();
        mListView.addHeaderView(headView);
        mListView.setHeaderDividersEnabled(false);
        int headerViewCount = mListView.getHeaderViewsCount();
        //最下面的列表功能
        datas_fujin=new ArrayList<>();
        for(int i=1;i<=50;i++){
            datas_fujin.add("item---------"+i);
        }
        adapter_fujin = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, datas_fujin);
        datas_huli = new ArrayList<>();
        for (int i=50; i<=100; i++) {
            datas_huli.add("item----------"+i);
        }
        adapter_huli = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, datas_huli);
        for (int i=0; i<10; i++) {
            GoodsInfo.ResultBean.GoodlistBean goodlistBean1 = new GoodsInfo.ResultBean.GoodlistBean();
            mGoodlist.add(goodlistBean1);
        }
        mGoodsListAdapter = new GoodsListAdapter(getActivity(), mGoodlist, headerViewCount);
        loadFragment(HEADER_FUJIN);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long id) {
                /**
                Bundle bundle = new Bundle();
                bundle.putString(GOODS_ID,mGoodlist.get(i-2).getGoods_id());
                bundle.putString(GOODS_SEVEN_REFUND,mGoodlist.get(i-2).getSeven_refund());
                bundle.putInt(GOODS_TIME_REFUND,mGoodlist.get(i-2).getTime_refund());
                bundle.putInt(GOODS_BOUGHT,mGoodlist.get(i-2).getBought());
                openActivity(DetailActivity.class,bundle);
                 */
                Intent intent = new Intent(getActivity(), DetailActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.service_fujin:
                loadFragment(HEADER_FUJIN);
                break;
            case R.id.service_huli:
                loadFragment(HEADER_HULI);
                break;
            case R.id.service_place:
                Intent intent = new Intent(getActivity(), ChooseLocationPlace.class);
                startActivity(intent);
                break;
            case R.id.service_all_seller:
                Intent intent1 = new Intent(getActivity(), AllSeller.class);
                startActivity(intent1);
        }
    }

    private void loadFragment(int type) {
        switch (type) {
            case HEADER_FUJIN:
                mListView.setAdapter(mGoodsListAdapter);
                currentListViewType = HEADER_FUJIN;
                break;
            case HEADER_HULI:
                mListView.setAdapter(adapter_huli);
                currentListViewType = HEADER_HULI;
                break;
        }
    }

}
