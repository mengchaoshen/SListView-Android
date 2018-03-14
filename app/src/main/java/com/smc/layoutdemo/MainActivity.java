package com.smc.layoutdemo;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AbsListView;
import android.widget.TextView;


import com.smc.slistview.SListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private SListView mSListView;
    private TextView mTvBar;
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            mSListView.stopRefresh();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        mSListView = findViewById(R.id.sListView);
        mTvBar = findViewById(R.id.tv_bar);
        TestAdapter testAdapter = new TestAdapter();
        String[] array = {"这一栏需要固定在顶上", "2", "3", "4", "5", "1", "2", "3", "4", "5",
                "1", "2", "3", "4", "5", "1", "2", "3", "4", "5",
                "1", "2", "3", "4", "5", "1", "2", "3", "4", "5"};
        List<String> list = asList(array);
        testAdapter.setData(list);

        mSListView.setAdapter(testAdapter);
        TextView contentHeaderView = new TextView(this);
        contentHeaderView.setText("这是顶部，跟着一起滑动");
        contentHeaderView.setHeight(300);
        mSListView.addContentHeaderView(contentHeaderView);
        mSListView.setXListViewListener(new SListView.ISListViewListener() {
            @Override
            public void onRefresh() {
                new Thread() {
                    @Override
                    public void run() {
                        super.run();
                        mHandler.sendEmptyMessageDelayed(0, 2000);
                    }
                }.run();
            }

            @Override
            public void onLoadMore() {

            }
        });
        mSListView.setOnScrollListener(new SListView.OnSScrollListener() {
            @Override
            public void onSScrolling(View view) {

            }

            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                if(firstVisibleItem >= 1){
                    mTvBar.setVisibility(View.VISIBLE);
                }else{
                    mTvBar.setVisibility(View.GONE);
                }
            }
        });
    }

    public static <T> List<T> asList(T... t) {
        List<T> list = new ArrayList<>();
        for (T a : t) {
            list.add(a);
        }
        return list;
    }
}
