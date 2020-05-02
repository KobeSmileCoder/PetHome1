package com.example.sunhan.pethome1.ui.adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sunhan.pethome1.R;
import com.example.sunhan.pethome1.entity.ShequGridInfo;

import java.util.List;

public class GridAdapter extends BaseAdapter {

    private Context mContext;
    private List<ShequGridInfo> mData;

    public GridAdapter(Context mContext, List<ShequGridInfo> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }


    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public Object getItem(int position) {
        return mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View mView = LayoutInflater.from(mContext).inflate(R.layout.item_gridview,null);
        ImageView gridIcon = (ImageView) mView.findViewById(R.id.grid_icon);
        TextView gridTitle = (TextView)mView.findViewById(R.id.grid_title);

        gridIcon.setImageResource(mData.get(position).getGridIcon());
        gridTitle.setText(mData.get(position).getGridTitle());
        return mView;
    }


}
