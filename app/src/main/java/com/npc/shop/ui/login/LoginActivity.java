package com.npc.shop.ui.login;

import android.os.Bundle;
import android.widget.Button;

import com.npc.shop.R;
import com.npc.shop.base.BaseActivity;
import com.npc.shop.ui.main.MainActivity;
import com.npc.shop.ui.main.MainMenuActivity;
import com.npc.shop.ui.register.RegisterActivity;

import butterknife.BindView;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    @OnClick(R.id.btnLogin)
    public void onLoginClick(){
        startActivity(MainMenuActivity.class, null, true);
    }

    @OnClick(R.id.btnRegister)
    public void onRegisterClick(){
        startActivity(RegisterActivity.class, null, true);
    }
}
