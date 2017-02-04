package com.npc.shop.ui.splash;

import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

import com.npc.shop.R;
import com.npc.shop.base.BaseActivity;
import com.npc.shop.ui.login.LoginActivity;
import com.npc.shop.ui.main.MainActivity;

import butterknife.BindView;

public class SplashActivity extends BaseActivity {

    @BindView(R.id.ivBanner)
    ImageView ivBanner;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivityWithShareElement(LoginActivity.class, null, "robot", ivBanner, true);
            }
        }, 1000);
    }
}
