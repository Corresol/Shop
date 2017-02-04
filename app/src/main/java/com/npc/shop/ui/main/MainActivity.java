package com.npc.shop.ui.main;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.npc.shop.R;
import com.npc.shop.app.AppSetting;
import com.npc.shop.app.Constant;
import com.npc.shop.base.BaseActivity;
import com.npc.shop.models.Product;
import com.npc.shop.ui.category.CategoryActivity;
import com.npc.shop.ui.checkout.CheckoutActivity;
import com.npc.shop.ui.custom.GridDividerDecoration;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {

    @BindView(R.id.llCategoty)
    LinearLayout llCategory;
    @BindView(R.id.tvCart)
    TextView tvCart;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loadProduct();
    }

    @Override
    protected void onResume() {
        super.onResume();
        tvCart.setText(AppSetting.getInstance().getCart() + " F CFA");

    }

    @OnClick(R.id.tvCart)
    public void onCart() {
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

    private void loadProduct(){
        for (int i=0; i<12; ++i) {
            View view = LayoutInflater.from(this).inflate(R.layout.layout_categoty, null);
            RecyclerView rvProduct = ButterKnife.findById(view, R.id.rvProduct);
            TextView tvCategory = ButterKnife.findById(view, R.id.tvCategory);
            Button btnMore = ButterKnife.findById(view, R.id.btnMore);
            rvProduct.setLayoutManager(new GridLayoutManager(this, 2, LinearLayoutManager.VERTICAL, false));
            ProductAdapter productAdapter = new ProductAdapter();
            rvProduct.setAdapter(productAdapter);
            rvProduct.addItemDecoration(new GridDividerDecoration(this));
            tvCategory.setText("Céréales");
            productAdapter.setProducts(getProducts());
            btnMore.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    MainActivity.this.startActivity(CategoryActivity.class, null, false);
                }
            });
            llCategory.addView(view);
        }

    }

    private List<Product> getProducts(){
        List<Product> products = new ArrayList<>();
        products.add(new Product("ananas", R.drawable.ananas, 15));
        products.add(new Product("legume", R.drawable.legume, 16));
        products.add(new Product("papaye", R.drawable.papaye, 17));
        products.add(new Product("watermelon", R.drawable.watermelon, 18));
        return products;
    }


}
