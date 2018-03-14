/**
 * @file XListViewHeader.java
 * @create Apr 18, 2012 5:22:27 PM
 * @author Maxwin
 * @description XListView's header
 */
package com.smc.slistview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

public class HeaderView extends LinearLayout {

    private LinearLayout mLlRoot;
    private FrameLayout mContentView;
    private RefreshHeaderView mRefresh;

    public HeaderView(Context context) {
        super(context);
        initView(context);
    }

    /**
     * @param context
     * @param attrs
     */
    public HeaderView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public FrameLayout getContentView(){
        return mContentView;
    }

    public RefreshHeaderView getRefreshHeaderView(){
        return mRefresh;
    }

    private void initView(Context context) {
        // 初始情况，设置下拉刷新view高度为0
        LayoutParams lp = new LayoutParams(
                LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        mLlRoot = (LinearLayout) LayoutInflater.from(context).inflate(
                R.layout.slistview_view_header, null);
        addView(mLlRoot, lp);
        setGravity(Gravity.BOTTOM);

        mContentView = mLlRoot.findViewById(R.id.fl_content);
        mRefresh = mLlRoot.findViewById(R.id.refresh_header_view);
    }

}
