package com.zhangshen147.android.hotelmanagersystem;

import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Toast;

import com.zhangshen147.android.hotelmanagersystem.fragment.DetailFragment;
import com.zhangshen147.android.hotelmanagersystem.fragment.OrderFragment;
import com.zhangshen147.android.hotelmanagersystem.fragment.RoomFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
        implements RoomFragment.OnFragmentInteractionListener,
        DetailFragment.OnFragmentInteractionListener,
        OrderFragment.OnFragmentInteractionListener{

    public static final String TAG = "MainActivity";
    private List<Fragment> mFragmentList = new ArrayList<Fragment>();
    private ViewPager mViewPager;

    private Toolbar mToolBar;
    private int mCurrentFragmentIs;

    // 实现回调，实现通信
    @Override
    public void onFragmentInteraction(Uri uri) {
        Toast.makeText(this,"onFragmentInteraction",Toast.LENGTH_SHORT).show();
    }

    // 继承自FragmentPagerAdapter
    class MyFragmentPagerAdapter extends FragmentPagerAdapter {
        List<Fragment> list;

        public MyFragmentPagerAdapter(FragmentManager manager,List<Fragment> list){
            super(manager);
            this.list = list;
        }

        @Override
        public Fragment getItem(int position) {
            Log.v(TAG,"getItem"+position);
            return list.get(position);
            }

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            Log.v(TAG,"instantiateItem()"+container.toString()+position);
            return super.instantiateItem(container, position);
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        // 引用成员变量
        mToolBar = (Toolbar)findViewById(R.id.toolbar_in_mainactivity);
        mViewPager = (ViewPager)findViewById(R.id.view_pager_in_mainactivity);
        mCurrentFragmentIs = 0;

        // 如果运行在 5.0 版本以上，则设置UI全屏，状态栏透明
        if (Build.VERSION.SDK_INT >= 21){
            View vecor = getWindow().getDecorView();
            vecor.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }

        // TODO 设置mToolBar

        // mViewPager添加滑动监听
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener(){
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                mCurrentFragmentIs = position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }

        });

        // 以mFragmentList为参数实现适配器，并添加给mViewPager
        RoomFragment f1 = RoomFragment.newInstance();
        DetailFragment f2 = DetailFragment.newInstance();
        OrderFragment f3 = OrderFragment.newInstance();

        mFragmentList.add(f1);
        mFragmentList.add(f2);
        mFragmentList.add(f3);

        FragmentManager fm = getSupportFragmentManager();
        FragmentPagerAdapter adapter = new MyFragmentPagerAdapter(fm,mFragmentList);
        mViewPager.setAdapter(adapter);
        mViewPager.setCurrentItem(0);
        Log.v(TAG,"onCreate()");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.v(TAG,"onStart()");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.v(TAG,"onResume()");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.v(TAG,"onPause()");
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.v(TAG,"onSaveInstanceState()");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.v(TAG,"onStop()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.v(TAG,"onDestory()");
    }

}
