package com.zhangshen147.android.hotelmanagersystem.json;

/**
 * Created by 张申 on 2017/12/13 0013.
 */

public class Order {
    public String msg;
    public String code;
    public OrderData data;

    public Order(OrderData data) {
        this.data = data;
    }

    public static class OrderData {
        public String beginTime;
        public String deposit;
        public String endTime;
        public String id;
        public String roomId;
        public String status;
        public String userName;

        public OrderData(String beginTime, String deposit, String endTime, String id, String roomId, String status, String userName) {
            this.beginTime = beginTime;
            this.deposit = deposit;
            this.endTime = endTime;
            this.id = id;
            this.roomId = roomId;
            this.status = status;
            this.userName = userName;
        }
    }
}
