package com.zhangshen147.android.hotelmanagersystem.fragment;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zhangshen147.android.hotelmanagersystem.R;
import com.zhangshen147.android.hotelmanagersystem.json.Room;

import java.util.ArrayList;
import java.util.List;

public class RoomFragment extends Fragment {

    public static final String TAG = "RoomFragment";
    private RecyclerView mRecyclerView;
    private RecyclerAdapter mRecyclerAdapter;

    private OnFragmentInteractionListener mListener;

    // recyclerView的数据集
    private List<Room> mRoomList = new ArrayList<Room>();

    /**
     * activity恢复状态时需要
     */
    public RoomFragment(){

    }

    // 推荐使用这种方式new一个fragment实例
    public static RoomFragment newInstance(){
        RoomFragment fragment = new RoomFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        Log.v(TAG,"newInstance()");
        return fragment;
    }

    // 继承自ViewHolder类
    private class RecyclerHolder extends RecyclerView.ViewHolder {
        private LinearLayout self;

        private ImageView image;
        private TextView name;
        private TextView content;
        private TextView price;
        private TextView status;

        public RecyclerHolder(View v){
            super(v);
            self = (LinearLayout) v;
            image = (ImageView) v.findViewById(R.id.room_item_image);
            name = (TextView) v.findViewById(R.id.room_item_name);
            content = (TextView) v.findViewById(R.id.room_item_content);
            price = (TextView) v.findViewById(R.id.room_item_price);
            status = (TextView) v.findViewById(R.id.room_item_status);
        }

    };

    // 继承自Adapter类
    private class RecyclerAdapter extends RecyclerView.Adapter<RecyclerHolder>{
        private List<Room> list;

        public RecyclerAdapter(List<Room> list){
            this.list = list;
        }

        @Override
        public RecyclerHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            LinearLayout view = (LinearLayout) layoutInflater.inflate(R.layout.room_item,parent,false);
            RecyclerHolder holder = new RecyclerHolder(view);
            return holder;
        }

        @Override
        public void onBindViewHolder(RecyclerHolder holder, int position) {
            holder.image.setImageResource(R.drawable.room_item);
            holder.name.setText(list.get(position).data.name);
            holder.content.setText(list.get(position).data.content);
            holder.price.setText(list.get(position).data.price);
            holder.status.setText(list.get(position).data.status);
        }

        @Override
        public int getItemCount() {
            return list.size();
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.v(TAG,"onCreate()");
    }

    /**
     * 创建视图
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        LinearLayout v = (LinearLayout) inflater.inflate(R.layout.fragment_room,container,false);

        // 现在getActivity不能获取绑定的activity，findView不能获取根视图，所以只能在onCreateView里面初始化mRecyclerView
        mRecyclerView = v.findViewById(R.id.recycler_view_in_roomfragment);

        // 设置垂直形的布局管理器
        LinearLayoutManager manager = new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false);
        mRecyclerView.setLayoutManager(manager);

        // 设置适配器
        List<Room> list = getList();
        mRoomList = list;
        mRecyclerAdapter = new RecyclerAdapter(mRoomList);
        mRecyclerView.setAdapter(mRecyclerAdapter);

        // 添加分割线
        mRecyclerView.addItemDecoration(new RecyclerView.ItemDecoration() {
            private Drawable mDivider = getActivity()
                    .obtainStyledAttributes(new int[]{android.R.attr.listDivider}).getDrawable(0);

            @Override
            public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
                final int left = parent.getPaddingLeft();
                final int right = parent.getWidth() - parent.getPaddingRight();
                int childCount = parent.getChildCount();

                for (int i = 0; i < childCount; i++) {
                    View child = parent.getChildAt(i);
                    final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams)child.getLayoutParams();
                    final int top = child.getBottom()+params.bottomMargin;
                    final int bottom = top + mDivider.getIntrinsicHeight();
                    mDivider.setBounds(left,top,right,bottom);
                    try{
                        mDivider.draw(c);
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                outRect.set(0,0,0,mDivider.getIntrinsicHeight());
            }
        });

        mRecyclerAdapter.notifyDataSetChanged();
        Log.v(TAG,"onCreateView()");
        return v;
    }

    private List<Room> getList() {
        //TODO 从数据库或者http服务器上获取list对象
        Room.RoomData roomData = new Room.RoomData("四人间，风景优美，服务多多","s05","4","A102","750","空闲");
        Room room = new Room(roomData);
        List<Room> list = new ArrayList<Room>();
        for (int i = 0; i < 20; i++) {
            list.add(room);
        }
        return list;
    }

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

    public interface OnFragmentInteractionListener {
        // 回调，与该fragment关联的activity实现该接口
        void onFragmentInteraction(Uri uri);
    }
}
