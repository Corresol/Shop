package com.npc.shop.ui.register;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.npc.shop.R;
import com.npc.shop.base.BaseActivity;
import com.npc.shop.ui.login.LoginActivity;
import com.npc.shop.ui.main.MainActivity;
import com.npc.shop.ui.updateinfo.InfoActivity;

import butterknife.BindView;
import butterknife.OnClick;

public class RegisterActivity extends BaseActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
    }

    @OnClick(R.id.btnLogin)
    public void onLoginClickl(){
        startActivity(LoginActivity.class, null, true);
    }

    @OnClick(R.id.btnRegister)
    public void onRegisterClick(){
        startActivity(InfoActivity.class, null, true);
    }
}
