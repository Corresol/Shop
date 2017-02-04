package com.npc.shop.ui.category;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.npc.shop.R;
import com.npc.shop.app.AppSetting;
import com.npc.shop.app.Constant;
import com.npc.shop.base.BaseActivity;
import com.npc.shop.models.Product;
import com.npc.shop.ui.checkout.CheckoutActivity;
import com.npc.shop.ui.custom.GridDividerDecoration;
import com.npc.shop.ui.main.ProductAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class CategoryActivity extends BaseActivity {

    @BindView(R.id.tvBack) TextView tvBack;
    @BindView(R.id.rvProduct) RecyclerView rvProduct;
    @BindView(R.id.tvCart) TextView tvCart;

    ProductAdapter productAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        rvProduct.setLayoutManager(new GridLayoutManager(this, 2, LinearLayoutManager.VERTICAL, false));
        productAdapter = new ProductAdapter();
        rvProduct.setAdapter(productAdapter);
        rvProduct.addItemDecoration(new GridDividerDecoration(this));
        productAdapter.setProducts(getProducts());
    }

    @Override
    protected void onResume() {
        super.onResume();
        tvCart.setText(AppSetting.getInstance().getCart() + " F CFA");
    }

    @OnClick(R.id.tvCart)
    public void onCart(){
        startActivity(CheckoutActivity.class, null, false);
    }

    @OnClick(R.id.tvCall)
    public void onCall() {
        Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + Constant.PHONE_NUMBER));
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        startActivity(intent);
    }

    @OnClick(R.id.tvBack)
    public void onBack(){
        this.finish();
    }

    private List<Product> getProducts(){
        List<Product> products = new ArrayList<>();
        for (int i=0; i<5; ++i) {
            products.add(new Product("ananas", R.drawable.ananas, 15));
            products.add(new Product("legume", R.drawable.legume, 16));
            products.add(new Product("papaye", R.drawable.papaye, 17));
            products.add(new Product("watermelon", R.drawable.watermelon, 18));
        }
        return products;
    }

}
