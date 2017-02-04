package com.npc.shop.ui.checkout;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.npc.shop.R;
import com.npc.shop.app.AppSetting;
import com.npc.shop.models.Product;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Lenovo on 1/22/2017.
 */

public class VerifyFragment extends Fragment {

    @BindView(R.id.llProducts) LinearLayout llProducts;
    @BindView(R.id.tvWarn) TextView tvWarn;
    @BindView(R.id.btnContinue) Button btnContinue;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_verify, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        final List<Product> products = AppSetting.getInstance().getProducts();
        if (products.size() == 0){
            btnContinue.setVisibility(View.GONE);
            tvWarn.setVisibility(View.VISIBLE);
        }else {
            for (final Product product : products) {
                final View mView = LayoutInflater.from(getActivity()).inflate(R.layout.layout_verify, null);
                ImageView ivDelete = ButterKnife.findById(mView, R.id.ivDelete);
                TextView tvName = ButterKnife.findById(mView, R.id.tvName);
                TextView tvQuan = ButterKnife.findById(mView, R.id.tvQuan);
                TextView tvPrice = ButterKnife.findById(mView, R.id.tvPrice);
                TextView tvTotal = ButterKnife.findById(mView, R.id.tvTotal);
                ivDelete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        products.remove(product);
                        int cart = AppSetting.getInstance().getCart();
                        AppSetting.getInstance().setCart(cart - product.quan * product.price);
                        AppSetting.getInstance().setProducts(products);
                        Intent intent = new Intent("com.job.update");
                        intent.putExtra("cmd", 1);
                        VerifyFragment.this.getContext().getApplicationContext().sendBroadcast(intent);
                        llProducts.removeView(mView);
                    }
                });
                tvName.setText(product.name);
                tvQuan.setText(product.quan + "");
                tvPrice.setText(product.price + "");
                tvTotal.setText((product.price * product.quan) + " F CFA");
                llProducts.addView(mView);
            }
        }
    }

    @OnClick(R.id.btnContinue)
    public void onContinue(){
        List<Product> products = AppSetting.getInstance().getProducts();
        if (products.size() > 0) {
            Intent intent = new Intent("com.job.update");
            intent.putExtra("cmd", 2);
            getContext().getApplicationContext().sendBroadcast(intent);
        }
    }
}
