# SListView-Android
仿@Maxwin-z的XListView库，主要功能是下拉刷新和上拉加载更多功能，并且添加了一部分Header可以默认显示，并通过demo给出了itme悬停的方案。实现效果跟支付宝首页比较类似，下拉刷新的Header上面还有一个布局，往上滑会跟着一起滚动。

本来想在@Maxwin-z的XListView库Fork一个来写，后来发现，大神还是使用Eclipse编写的，改动实在太大，所以自己开了一个。希望大神谅解。

使用简介(基本用法和ListView一致)：

1.在布局文件中添加如下：
```
<com.smc.slistview.SListView
        android:id="@+id/sListView"
        android:visibility="visible"
        android:layout_width="match_parent"
        android:layout_height="match_parent"/>
```
2.设置下拉刷新和加载跟多的回调接口
```
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
```
```
private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            mSListView.stopRefresh();//刷新结束后，调用stopRefresh()来停止刷新
        };
```
3.添加在RefreshHeader上部，永久显示的Header(可选)
```
TextView contentHeaderView = new TextView(this);
        contentHeaderView.setText("这是顶部，跟着一起滑动");
        contentHeaderView.setHeight(300);
        mSListView.addContentHeaderView(contentHeaderView);
```
4.悬停解决方法（可选）
```
在布局文件中添加与你想悬停一样的布局，这里需要注意的是，需要把SListView与悬停布局叠加放置
<FrameLayout
    xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical"
    tools:context="com.smc.layoutdemo.MainActivity">

    <com.smc.slistview.SListView
        android:id="@+id/sListView"
        android:visibility="visible"
        android:layout_width="match_parent"
        android:layout_height="match_parent"/>

    <TextView
        android:id="@+id/tv_bar"
        android:layout_width="match_parent"
        android:layout_height="50dp"
        android:visibility="gone"
        android:background="#ffffff"
        android:text="这一栏需要固定在顶上"/>

</FrameLayout>
```
```
mSListView.setOnScrollListener(new SListView.OnSScrollListener() {
            @Override
            public void onSScrolling(View view) {

            }

            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                if(firstVisibleItem >= 1){//滚动到上面时，就把悬停布局显示出来
                    mTvBar.setVisibility(View.VISIBLE);
                }else{//否则把悬停布局隐藏起来
                    mTvBar.setVisibility(View.GONE);
                }
            }
        });
```
