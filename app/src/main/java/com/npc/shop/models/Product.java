package com.npc.shop.models;

import org.parceler.Parcel;

import java.io.Serializable;

/**
 * Created by Lenovo on 1/21/2017.
 */
@Parcel
public class Product implements Serializable{
    public String name;
    public int resId;
    public int price;
    public int quan = 0;

    public Product() {
    }

    public Product(String name, int resId, int price) {
        this.name = name;
        this.resId = resId;
        this.price = price;
    }
}
