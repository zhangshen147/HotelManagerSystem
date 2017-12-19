package com.zhangshen147.android.hotelmanagersystem.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;

import com.zhangshen147.android.hotelmanagersystem.R;
import com.zhangshen147.android.hotelmanagersystem.json.Order;

import java.util.ArrayList;
import java.util.List;

public class OrderFragment extends Fragment {
    public static final String TAG = "OrderFragment";

    private View mRootView;
    private RecyclerView mRecyclerView;
    private RecyclerAdapter mRecyclerAdapter;
    private OnFragmentInteractionListener mListener;

    // mRecyclerView 的数据集
    private List<Order> mDetailList = new ArrayList<Order>();


    // activity恢复状态时需要
    public OrderFragment() {

    }

    // 推荐用这种方式 new fragment 实例
    public static OrderFragment newInstance() {
        OrderFragment fragment = new OrderFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        Log.v(TAG,"newInstance()");
        return fragment;
    }




    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.v(TAG,"onCreate()");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mRootView = inflater.inflate(R.layout.fragment_order,container,false);

        // 初始化组件
        mRecyclerView = mRootView.findViewById(R.id.recycler_view_in_detailfragment);

        // 设置垂直的布局管理器
        LinearLayoutManager manager = new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false);
        mRecyclerView.setLayoutManager(manager);

        // 设置适配器
        initList();
        mRecyclerAdapter = new RecyclerAdapter(mDetailList);
        mRecyclerView.setAdapter(mRecyclerAdapter);

        Log.v(TAG,"onCreate()");
        return mRootView;
    }

    /********************内部类*************************/

    // 继承自ViewHolder类
    private class RecyclerHolder extends RecyclerView.ViewHolder{
        private CardView self;

        private ImageView image;
        private TextView time;
        private TextView room_id;
        private TextView customer;
        private ImageView handle;

        public RecyclerHolder(View v){
            super(v);
            self = (CardView) v;
            image = (ImageView) v.findViewById(R.id.order_item_image);
            time = (TextView) v.findViewById(R.id.order_item_time);
            room_id = (TextView) v.findViewById(R.id.order_item_room_id);
            customer = (TextView) v.findViewById(R.id.order_item_customer);
            handle = (ImageView) v.findViewById(R.id.order_item_handle);
        }
    }


    // 继承自Adapter类
    private class RecyclerAdapter extends RecyclerView.Adapter<RecyclerHolder>{
        private List<Order> list;

        public RecyclerAdapter(List<Order> list){
            this.list = list;
        }

        @Override
        public RecyclerHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            View v = layoutInflater.inflate(R.layout.order_item,parent,false);
            RecyclerHolder h = new RecyclerHolder(v);
            return h;
        }

        @Override
        public void onBindViewHolder(final RecyclerHolder holder, int position) {
            Order current = list.get(position);

            holder.image.setImageResource(R.drawable.head_photo);
            holder.time.setText("发起时间：" + current.data.beginTime);
            holder.room_id.setText("房间号：   " + current.data.roomId);
            holder.customer.setText("来自于：   " + current.data.userName);
            holder.handle.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    PopupMenu menu = new PopupMenu(getActivity(),holder.handle);
                    // 添加 popMenu 点击事件
                    menu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                        @Override
                        public boolean onMenuItemClick(MenuItem item) {
                            switch (item.getItemId()){
                                case R.id.menu_item_accept:
                                    Snackbar.make(mRootView,"恭喜，接受了一条订单！",Snackbar.LENGTH_SHORT)
                                            .setAction("撤销", new View.OnClickListener() {
                                                @Override
                                                public void onClick(View v) {
                                                    System.out.println("撤销");
                                                }
                                            })
                                            .show();
                                    return true;
                                case R.id.menu_item_refuse:
                                    Snackbar.make(mRootView,"残忍地拒绝了这一条订单！",Snackbar.LENGTH_SHORT)
                                            .setAction("撤销", new View.OnClickListener() {
                                                @Override
                                                public void onClick(View v) {
                                                    System.out.println("撤销");
                                                }
                                            })
                                            .show();
                                    return true;


                                case R.id.menu_item_ignore:
                                    // TODO 把小红点去掉
                                    return true;


                            }
                            return false;
                        }
                    });
                    menu.inflate(R.menu.menu_order_handle);
                    menu.show();
                }
            });
        }

        @Override
        public int getItemCount() {
            return list.size();
        }
    }

    /*****************私有方法*******************************/

    private void initList() {
        Order.OrderData orderData = new Order.OrderData("2017-08-14","80","08-20","05","A502","空闲","张申");
        Order order = new Order(orderData);

        for (int i = 0; i < 10; i++) {
            mDetailList.add(order);
        }
    }

    /********************生命周期*****************************/

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
        Log.v(TAG,"onAttach()");
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.v(TAG,"onStart()");
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.v(TAG,"onActivityCreated()");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.v(TAG,"onResume()");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.v(TAG,"onPause()");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.v(TAG,"onStop()");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.v(TAG,"onDestory()");
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.v(TAG,"onSaveInstanceState()");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
        Log.v(TAG,"onDetach()");
    }


    // 回调方法，与fragment关联的activity实现
    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }
}
