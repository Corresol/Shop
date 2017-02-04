package com.npc.shop.ui.updateinfo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.npc.shop.R;
import com.npc.shop.base.BaseActivity;
import com.npc.shop.ui.main.MainActivity;
import com.npc.shop.ui.main.MainMenuActivity;

import butterknife.OnClick;

public class InfoActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
    }

    @OnClick(R.id.btnDone)
    public void onDone(){
        startActivity(MainMenuActivity.class, null, true);
    }

}
