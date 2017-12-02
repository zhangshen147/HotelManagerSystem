package com.zhangshen147.android.hotelmanagersystem;

import android.os.Build;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toolbar;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<View> mViewList = new ArrayList<View>();
    private ViewPager mViewPager;
    private LayoutInflater mInflater;

    private LinearLayout mRoomLayout;
    private LinearLayout mDetailLayout;
    private LinearLayout mOrderLayout;

    private TextView mTitleView;
    private LinearLayout mCurrentLayout;

    //实现ViewPager的适配器MyPagerAdapter类
    class MyPagerAdapter extends PagerAdapter{

        private List<View> list;

        public MyPagerAdapter(List<View> list){
            this.list = list;
        }

        @Override
        public int getCount() {
            if (list != null && list.size()>0){
                return list.size();
            }else {
                return 0;
            }
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            container.addView(mViewList.get(position),0);
            return mViewList.get(position);
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView(mViewList.get(position));
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //去掉标题栏,将ActionBar替换为ToolBar
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar_in_mainactivity);
        if (Build.VERSION.SDK_INT >= 21){
            setActionBar(toolbar);
        }
        setContentView(R.layout.activity_main);

        //引用组件
        mRoomLayout = (LinearLayout)findViewById(R.id.nav_item1_in_mainactivity);
        mRoomLayout = (LinearLayout)findViewById(R.id.nav_item2_in_mainactivity);
        mRoomLayout = (LinearLayout)findViewById(R.id.nav_item3_in_mainactivity);
        mTitleView = (TextView)findViewById(R.id.title_view_in_mainactivity);
        mViewPager = (ViewPager)findViewById(R.id.view_pager_in_mainactivity);
        mInflater = LayoutInflater.from(this);

        //设置默认布局为mRoomLayout
        mCurrentLayout = mRoomLayout;
        mCurrentLayout.setSelected(true);
        mTitleView.setText("明细");

        //mViewPager添加滑动监听
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener(){
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                changeTab(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }

        });

        //初始化mViewList
        View tab1 = mInflater.inflate(R.layout.fragment_room,null);
        View tab2 = mInflater.inflate(R.layout.fragment_detail,null);
        View tab3 = mInflater.inflate(R.layout.fragment_order,null);
        mViewList.add(tab1);
        mViewList.add(tab2);
        mViewList.add(tab3);

        //以mViewList为参数实现适配器，并添加给mViewPager
        PagerAdapter adapter = new MainActivity.MyPagerAdapter(mViewList);
        mViewPager.setAdapter(adapter);
    }

    //从底部导航栏切换view
    private void changeTab(int position){

    }
}
