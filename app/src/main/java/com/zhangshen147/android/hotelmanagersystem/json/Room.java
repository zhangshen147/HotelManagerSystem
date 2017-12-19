package com.zhangshen147.android.hotelmanagersystem.json;

/**
 * Created by 张申 on 2017/12/1 0001.
 */

public class Room {
    public String msg;
    public String code;
    public RoomData data;

    public Room(RoomData data) {
        this.data = data;

    }

    public static class RoomData {
        public String content;
        public String id;
        public String liveCount;
        public String name;
        public String price;
        // "0" 空闲
        // "1" 已预定
        // "2" 入住
        public String status;

        public RoomData(String content, String id, String liveCount, String name, String price, String status) {
            this.content = content;
            this.id = id;
            this.liveCount = liveCount;
            this.name = name;
            this.price = price;
            this.status = status;
        }
    }
}
