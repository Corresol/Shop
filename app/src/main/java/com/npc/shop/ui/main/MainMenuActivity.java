package com.npc.shop.ui.main;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
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
import com.npc.shop.ui.login.LoginActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainMenuActivity extends BaseActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.llCategoty)
    LinearLayout llCategory;
    @BindView(R.id.tvCart)
    TextView tvCart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        loadProduct();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        startActivity(CategoryActivity.class, null, false);
        if (item.getItemId() == R.id.nav_logout){
            AppSetting.getInstance().logout();
            startActivity(LoginActivity.class, null, true);
        }else {
            DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
            drawer.closeDrawer(GravityCompat.START);
        }
        return true;
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
                    MainMenuActivity.this.startActivity(CategoryActivity.class, null, false);
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
