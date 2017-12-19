package com.zhangshen147.android.hotelmanagersystem;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.util.ArrayMap;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.zhangshen147.android.hotelmanagersystem.fragment.MyFragment;
import com.zhangshen147.android.hotelmanagersystem.fragment.OrderFragment;
import com.zhangshen147.android.hotelmanagersystem.fragment.RoomFragment;
import com.zhangshen147.android.hotelmanagersystem.util.TintHandle;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity
        implements RoomFragment.OnFragmentInteractionListener,
        OrderFragment.OnFragmentInteractionListener,
        MyFragment.OnFragmentInteractionListener{

    public static final String TAG = "MainActivity";

    private Toolbar mToolBar;
    private ImageView mMyImageView;
    private ImageView mOrderImageView;
    private ImageView mRoomImageView;

    private ViewPager mViewPager;

    private ImageButton mDrawerOpener;
    private DrawerLayout mDrawerLayout;

    private List<Fragment> mFragmentList = new ArrayList<Fragment>();

    // Fragment 和导航栏中间三个 ImageView 之间的映射
    private Map<Fragment,ImageView> mFragmentImageViewMap = new ArrayMap<Fragment, ImageView>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        findView();

        // 如果运行在 5.0 及以上版本，设置状态栏颜色与 colorPrimary 一致
        if (Build.VERSION.SDK_INT >= 21){
            getWindow().setStatusBarColor(Color.parseColor("#BC1717"));
        }

//        // 这段代码可实现透明全屏效果
//        // 设置UI全屏，状态栏透明
//        if (Build.VERSION.SDK_INT >= 21){
//            View vecor = getWindow().getDecorView();
//            vecor.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
//            getWindow().setStatusBarColor(Color.parseColor("#BC1717"));
//        }

        // mDrawerOpener 点击事件
        mDrawerOpener.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDrawerLayout.openDrawer(Gravity.LEFT);
            }
        });

        // 给 mDrawerLayout 添加内容
        String[] list = new String[]{"检查新版本","设置","关于"};
        ListView sideView = findViewById(R.id.list_view_in_drawer);
        sideView.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,list));

        // mDrawerLayout 状态改变事件
        mDrawerLayout.addDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(@NonNull View drawerView, float slideOffset) {

            }

            @Override
            public void onDrawerOpened(@NonNull View drawerView) {

            }

            @Override
            public void onDrawerClosed(@NonNull View drawerView) {

            }

            @Override
            public void onDrawerStateChanged(int newState) {

            }
        });

        // 给 mViewPager 添加自定义的 fragmentPagerAdapter
        MyFragment f1 = MyFragment.newInstance();
        OrderFragment f2 = OrderFragment.newInstance();
        RoomFragment f3 = RoomFragment.newInstance();

        mFragmentList.add(f1);
        mFragmentList.add(f2);
        mFragmentList.add(f3);
        mFragmentImageViewMap.put(f1,mMyImageView);
        mFragmentImageViewMap.put(f2,mOrderImageView);
        mFragmentImageViewMap.put(f3,mRoomImageView);

        FragmentManager fm = getSupportFragmentManager();
        FragmentPagerAdapter adapter = new MyFragmentPagerAdapter(fm,mFragmentList);
        mViewPager.setOffscreenPageLimit(2);
        mViewPager.setAdapter(adapter);
        mViewPager.setCurrentItem(0);
        changeImageViewColorToWhite(mMyImageView);

        // 三个导航图标点击事件
        mMyImageView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                mViewPager.setCurrentItem(0);
                setHighLight((ImageView) v);

            }
        });

        mOrderImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mViewPager.setCurrentItem(1);
                setHighLight((ImageView) v);

            }
        });

        mRoomImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mViewPager.setCurrentItem(2);
                setHighLight((ImageView) v);
            }
        });

        // mViewPager 状态改变事件
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener(){
            @Override
            public void onPageScrollStateChanged(int state) {

            }
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                ImageView view = mFragmentImageViewMap.get(mFragmentList.get(position));
                setHighLight(view);
            }

        });


        Log.v(TAG,"onCreate()");
    }

    private void findView() {
        mToolBar = (Toolbar) findViewById(R.id.toolbar_in_mainactivity);
        mMyImageView = (ImageView)findViewById(R.id.iv_my_in_mainactivity);
        mOrderImageView = (ImageView)findViewById(R.id.iv_order_in_mainactivity);
        mRoomImageView = (ImageView)findViewById(R.id.iv_room_in_mainactivity);

        mViewPager = (ViewPager)findViewById(R.id.view_pager_in_mainactivity);

        mDrawerLayout = (DrawerLayout)findViewById(R.id.drawer_in_mainactivity);
        mDrawerOpener = (ImageButton)findViewById(R.id.drawer_button_in_toolbar);
    }

    private void changeImageViewColorToWhite(ImageView v){
        // 透明度为DD的白色
        int color = 0xDDffffff;
        Drawable drawable = v.getDrawable();
        drawable = TintHandle.tintDrawable(drawable,color);
        v.setImageDrawable(drawable);
    }

    private void changeImageViewColorToBlack(ImageView v){
        // 透明度为FF的黑色
        int color = 0xFF000000;
        Drawable drawable = v.getDrawable();
        drawable = TintHandle.tintDrawable(drawable,color);
        v.setImageDrawable(drawable);
    }

    private void setHighLight(ImageView view){
        changeImageViewColorToBlack(mRoomImageView);
        changeImageViewColorToBlack(mMyImageView);
        changeImageViewColorToBlack(mOrderImageView);
        changeImageViewColorToWhite(view);

    }

    // 自定义 FragmentPagerAdapter
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


    // 实现 fragment 与 activity 的通信接口
    @Override
    public void onFragmentInteraction(Uri uri) {
        Toast.makeText(this,"onFragmentInteraction",Toast.LENGTH_SHORT).show();
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
