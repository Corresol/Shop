package com.npc.shop.ui.main;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.npc.shop.R;
import com.npc.shop.base.BaseActivity;
import com.npc.shop.models.Product;
import com.npc.shop.ui.product.ProductActivity;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Lenovo on 1/21/2017.
 */

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductHolder>{

    List<Product> products;

    public ProductAdapter() {
        products = new ArrayList<>();
    }

    @Override
    public ProductHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ProductHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product, parent, false));
    }

    @Override
    public void onBindViewHolder(ProductHolder holder, int position) {
        holder.bind(products.get(position));
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public void setProducts(List<Product> products){
        this.products = products;
        notifyDataSetChanged();
    }

    class ProductHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        @BindView(R.id.ivImage) ImageView ivImage;
        @BindView(R.id.btnBuy) Button btnBuy;
        @BindView(R.id.tvName) TextView tvName;

        Product product;
        Context mContext;

        public ProductHolder(View itemView) {
            super(itemView);
            mContext = itemView.getContext();
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }

        public void bind(Product product){
            this.product = product;
            ivImage.setImageResource(product.resId);
            tvName.setText(product.name);
            btnBuy.setText(product.price + " F /tete");
        }

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(v.getContext(), ProductActivity.class);
            intent.putExtra("data", Parcels.wrap(product));
            v.getContext().startActivity(intent);
        }

        @OnClick(R.id.btnBuy)
        public void onBuy(){
            Intent intent = new Intent(mContext, ProductActivity.class);
            intent.putExtra("data", Parcels.wrap(product));
            mContext.startActivity(intent);
        }
    }
}
