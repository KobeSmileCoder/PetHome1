package com.example.sunhan.pethome1.ui.adapter;

import android.content.ComponentName;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sunhan.pethome1.R;
import com.example.sunhan.pethome1.beans.ServiceFujinInfo;

import java.util.ArrayList;

/**
 * Created by 孙汉 on 2019/01/14
 */
public class ServiceFujinAdapter extends BaseAdapter{

    private LayoutInflater mInflater;

    private Context context;

    private ArrayList<ServiceFujinInfo> serviceFujinInfomations;

    public ServiceFujinAdapter(Context context, ArrayList<ServiceFujinInfo> serviceFujinInfomations) {
        this.context = context;
        this.mInflater = LayoutInflater.from(context);
        this.serviceFujinInfomations = serviceFujinInfomations;
    }

    @Override
    public int getCount() {
        return serviceFujinInfomations.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.servicefujin_item, null);
        }
        ImageView fujinImage = (ImageView)convertView.findViewById(R.id.fujinImage);
        TextView fujin_name = (TextView)convertView.findViewById(R.id.fujin_name);
        TextView fujin_score = (TextView)convertView.findViewById(R.id.fujin_score);
        TextView fujin_price = (TextView) convertView.findViewById(R.id.fujin_price);
        TextView fujin_place = (TextView)convertView.findViewById(R.id.fujin_place);
        TextView fujin_distance = (TextView)convertView.findViewById(R.id.fujin_distance);
        TextView fujin_salenumber = (TextView)convertView.findViewById(R.id.fujin_salenumber);

        fujinImage.setImageBitmap(serviceFujinInfomations.get(position).getBitmap());
        fujin_name.setText(serviceFujinInfomations.get(position).getShopname());
        fujin_price.setText(""+serviceFujinInfomations.get(position).getPrice());
        fujin_score.setText(""+serviceFujinInfomations.get(position).getScore());
        fujin_place.setText(serviceFujinInfomations.get(position).getPlace());
        fujin_distance.setText(""+serviceFujinInfomations.get(position).getDistance());
        fujin_salenumber.setText(""+serviceFujinInfomations.get(position).getSalesnumber());
        return convertView;
    }
}
