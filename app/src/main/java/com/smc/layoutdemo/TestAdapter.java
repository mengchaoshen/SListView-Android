package com.smc.layoutdemo;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * @author shenmengchao
 * @version 1.0.0
 * @date 2018/3/13
 * @description
 */

public class TestAdapter extends BaseAdapter {

    private List<String> mList = new ArrayList<>();

    public void setData(List<String> data) {
        this.mList = data;
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_main, parent, false);
        TextView textView = convertView.findViewById(R.id.tv_main);
        String text = mList.get(position);
        textView.setText(text);
        return convertView;
    }
}
