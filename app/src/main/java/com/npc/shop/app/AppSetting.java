package com.npc.shop.app;

import com.npc.shop.models.Product;
import com.orhanobut.hawk.Hawk;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by USER on 12/01/2017.
 */

public class AppSetting {
    private static final AppSetting instance = new AppSetting();

    private AppSetting(){

    }

    public static AppSetting getInstance(){
        return instance;
    }

    int cart;
    List<Product> products;

    public List<Product> getProducts() {
        if (products == null){
            products = Hawk.get("products", new ArrayList<Product>());
        }
        return products;
    }

    public void setProducts(List<Product> products) {
        Hawk.put("products", products);
        this.products = products;
    }

    public void addProduct(Product product){
        products = getProducts();
        products.add(product);
        setProducts(products);
    }

    public int getCart() {
        if (cart == 0){
            cart = Hawk.get("cart", 0);
        }
        return cart;
    }

    public void setCart(int cart) {
        Hawk.put("cart", cart);
        this.cart = cart;
    }

    public void logout(){
        Hawk.deleteAll();
    }
}
