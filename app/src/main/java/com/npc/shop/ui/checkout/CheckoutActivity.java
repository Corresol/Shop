package com.npc.shop.ui.checkout;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.npc.shop.R;
import com.npc.shop.app.AppSetting;
import com.npc.shop.base.BaseActivity;
import com.npc.shop.ui.custom.CustomViewPager;

import butterknife.BindView;
import butterknife.OnClick;

public class CheckoutActivity extends BaseActivity {

    @BindView(R.id.tabLayout) TabLayout tabLayout;
    @BindView(R.id.viewPager) CustomViewPager viewPager;
    @BindView(R.id.tvTran) TextView tvTrans;
    @BindView(R.id.tvPrice) TextView tvPrice;
    @BindView(R.id.tvTotal) TextView tvTotal;

    ProductPagerAdapter adapter;

    int currentPage = 0;

    BroadcastReceiver updateJob = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            int cmd = intent.getIntExtra("cmd", -1);
            switch (cmd){
                case 1:
                    updateValue();
                    break;
                case 2:
                    viewPager.setCurrentItem(++currentPage);
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);
        viewPager.setPagingEnabled(false);
        adapter = new ProductPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);
        tabLayout.setTabGravity(TabLayout.GRAVITY_CENTER);
        tabLayout.setupWithViewPager(viewPager);
        adapter.addFragment(new VerifyFragment(), "Vérification");
        adapter.addFragment(new InfoFragment(), "Info Livraison");
        adapter.addFragment(new ValidateFragment(), "Validation");
        LinearLayout tabStrip = ((LinearLayout)tabLayout.getChildAt(0));
        for(int i = 0; i < tabStrip.getChildCount(); i++) {
            tabStrip.getChildAt(i).setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    return true;
                }
            });
        }
        updateValue();
    }

    @Override
    protected void onResume() {
        super.onResume();
        IntentFilter filter = new IntentFilter("com.job.update");
        registerReceiver(updateJob, filter);
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(updateJob);
    }

    @OnClick(R.id.tvBack)
    public void onBack(){
        this.finish();
    }

    private void updateValue(){
        int value = AppSetting.getInstance().getCart();
        tvPrice.setText(value + " F");
        tvTrans.setText("500 F");
        tvTotal.setText((500 + value) + " F");
    }
}
