package com.littledog.activity;

import com.littledog.bitsandpizzsa.R;

/**
 * Created by qzw on 2017/3/17.
 */

public class Pizza {
    private  String name;
    private int imageResourceId;
    public static final Pizza[] pizzas={
        new Pizza("Diavolo", R.drawable.pizza1),
        new Pizza("Funghi",R.drawable.pizza2)
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImageResourceId() {
        return imageResourceId;
    }

    public void setImageResourceId(int imageResourceId) {
        this.imageResourceId = imageResourceId;
    }

    private Pizza(String name , int imageResourceId){
        this.name=name;
        this.imageResourceId=imageResourceId;
    }
}
