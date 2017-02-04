package com.npc.shop.ui.product;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.npc.shop.R;
import com.npc.shop.app.AppSetting;
import com.npc.shop.app.Constant;
import com.npc.shop.base.BaseActivity;
import com.npc.shop.models.Product;
import com.npc.shop.ui.checkout.CheckoutActivity;
import com.orhanobut.hawk.Hawk;

import org.parceler.Parcels;

import butterknife.BindView;
import butterknife.OnClick;

public class ProductActivity extends BaseActivity{

    @BindView(R.id.ivProduct) ImageView ivProduct;
    @BindView(R.id.edtQuan) EditText edtQuan;
    @BindView(R.id.edtPrice) EditText edtPrice;
    @BindView(R.id.edtTotal) EditText edtTotal;
    @BindView(R.id.tvName) TextView tvName;
    @BindView(R.id.tvCart) TextView tvCart;

    Product product;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);
        product = Parcels.unwrap(getIntent().getParcelableExtra("data"));
        product.quan = 1;
        tvName.setText(product.name);
        ivProduct.setImageResource(product.resId);
        edtQuan.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!edtQuan.getText().toString().isEmpty()) {
                    int quan = Integer.parseInt(edtQuan.getText().toString());
                    product.quan = quan;
                    int total = quan * product.price;
                    edtTotal.setText(total + "");
                }
            }
        });
        edtPrice.setText(product.price + "");
        edtTotal.setText(product.price + "");

    }

    @Override
    protected void onResume() {
        super.onResume();
        tvCart.setText(AppSetting.getInstance().getCart() + " F CFA");

    }

    @OnClick(R.id.tvCall)
    public void onCall() {
        Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + Constant.PHONE_NUMBER));
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        startActivity(intent);
    }

    @OnClick(R.id.tvCart)
    public void onCart(){
        startActivity(CheckoutActivity.class, null, false);
    }

    @OnClick(R.id.tvBack)
    public void onBack(){
        this.finish();
    }

    @OnClick(R.id.tvSub)
    public void onSub(){
        int price = Integer.parseInt(edtQuan.getText().toString());
        if (price > 1) {
            edtQuan.setText(price - 1 + "");
        }
    }

    @OnClick(R.id.tvAdd)
    public void onAdd(){
        int price = Integer.parseInt(edtQuan.getText().toString());
        edtQuan.setText(price + 1 + "");
    }

    @OnClick(R.id.btnBuy)
    public void onBuy(){
        int cart = AppSetting.getInstance().getCart();
        AppSetting.getInstance().setCart(cart + Integer.parseInt(edtTotal.getText().toString()));
        AppSetting.getInstance().addProduct(product);
        this.finish();
    }


}
