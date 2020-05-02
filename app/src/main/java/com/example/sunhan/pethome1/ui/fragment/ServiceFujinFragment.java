package com.example.sunhan.pethome1.ui.fragment;

import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.sunhan.pethome1.R;
import com.example.sunhan.pethome1.beans.ServiceFujinInfo;
import com.example.sunhan.pethome1.ui.activities.MainActivity;
import com.example.sunhan.pethome1.ui.adapter.ServiceFujinAdapter;

import java.util.ArrayList;

public class ServiceFujinFragment extends Fragment{

    public static ArrayList<ServiceFujinInfo> serviceFujinInfos = new ArrayList<>();

    View view;

    private ListView listView_fujin;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_servicefujin, container, false);
        initData();
        initViews(view);
        return view;
    }

    private void initData() {
        initialFigures();
        listView_fujin = (ListView)view.findViewById(R.id.listView_fujin);
    }

    private void initialFigures() {
        serviceFujinInfos.add(new ServiceFujinInfo("A",10, 20, 20, "123", 20, "123", BitmapFactory.decodeResource(getContext().getResources(), R.mipmap.park1)));
        serviceFujinInfos.add(new ServiceFujinInfo("B",20, 20, 20, "123", 20, "123", BitmapFactory.decodeResource(getContext().getResources(), R.mipmap.park2)));
        serviceFujinInfos.add(new ServiceFujinInfo("C",20, 20, 20, "123", 20, "123", BitmapFactory.decodeResource(getContext().getResources(), R.mipmap.park3)));
        serviceFujinInfos.add(new ServiceFujinInfo("D",20, 20, 20, "123", 20, "123", BitmapFactory.decodeResource(getContext().getResources(), R.mipmap.park4)));
    }

    private void initViews(View view) {

    }

    @Override
    public void onResume() {
        //重置车位数量
        ServiceFujinAdapter serviceFujinAdapter = new ServiceFujinAdapter(getContext(), serviceFujinInfos);
        listView_fujin.setAdapter(serviceFujinAdapter);
        super.onResume();
    }
}
