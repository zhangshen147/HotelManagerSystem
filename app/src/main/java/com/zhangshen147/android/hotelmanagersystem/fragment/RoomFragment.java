package com.zhangshen147.android.hotelmanagersystem.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
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

    private RecyclerView mRecyclerView;
    private RecyclerHolder mRecyclerHoder;
    private RecyclerAdapter mRecyclerAdapter;

    private LayoutInflater mLayoutInflater = LayoutInflater.from(getActivity());

    private OnFragmentInteractionListener mListener;

    // Holder类
    class RecyclerHolder extends RecyclerView.ViewHolder {
        private LinearLayout self;

        private ImageView image;
        private TextView title;
        private TextView type;
        private TextView location;
        private TextView star;
        private TextView price;

        public RecyclerHolder(View v){
            super(v);
            image = (ImageView) v.findViewById(R.id.room_item_image);
            title = (TextView) v.findViewById(R.id.room_item_title);
            type = (TextView) v.findViewById(R.id.room_item_type);
            location = (TextView) v.findViewById(R.id.room_item_location);
            star = (TextView) v.findViewById(R.id.room_item_star);
            price = (TextView) v.findViewById(R.id.room_item_price);
        }

    };

    // Adapter类
    class RecyclerAdapter extends RecyclerView.Adapter<RecyclerHolder>{
        private List<Room> list;

        public RecyclerAdapter(List<Room> list){
            this.list = list;
        }

        @Override
        public RecyclerHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LinearLayout view = (LinearLayout) mLayoutInflater.inflate(R.layout.room_item,null);
            RecyclerHolder holder = new RecyclerHolder(view);
            return holder;
        }

        @Override
        public void onBindViewHolder(RecyclerHolder holder, int position) {
            holder.image.setImageResource(R.drawable.test);
            holder.price.setText(list.get(position).getPrice());
            holder.star.setText(list.get(position).getStar());
            holder.location.setText(list.get(position).getLocation());
            holder.type.setText(list.get(position).getType());
        }

        @Override
        public int getItemCount() {
            return list.size();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        LinearLayout v = (LinearLayout) inflater.inflate(R.layout.fragment_room,null);
        return v;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mRecyclerView = (RecyclerView)getView().findViewById(R.id.recycler_view_in_roomfragment);
        mRecyclerView.setLayoutManager(RecyclerView.LayoutManager.);
        mRecyclerView.setHasFixedSize(true);
        // TODO 请求数据，生成List<Room>
        /**
         * 不过先自己写一个，用来测试
         */
        Room room = new Room("潘阿姨","双刃剑","堕落街","3.9分","150");
        List<Room> list = new ArrayList<Room>();
        for (int i = 0; i < 20; i++) {
        list.add(room);
        }
        mRecyclerAdapter = new RecyclerAdapter(list);
        mRecyclerView.setAdapter(mRecyclerAdapter);
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
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
