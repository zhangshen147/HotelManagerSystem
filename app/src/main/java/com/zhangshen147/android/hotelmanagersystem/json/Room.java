package com.zhangshen147.android.hotelmanagersystem.json;

/**
 * Created by 张申 on 2017/12/1 0001.
 */

public class Room {
    private String title;
    private String type;
    private String location;
    private String star;
    private String price;

    public Room(String title, String type, String location, String star, String price) {
        this.title = title;
        this.type = type;
        this.location = location;
        this.star = star;
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getStar() {
        return star;
    }

    public void setStar(String star) {
        this.star = star;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
