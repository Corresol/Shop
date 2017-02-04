package com.npc.shop.ui.term;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.npc.shop.R;
import com.npc.shop.base.BaseActivity;

import butterknife.OnClick;

public class TermActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_term);
    }

    @OnClick(R.id.tvBack)
    public void onBack(){
        this.finish();
    }

}
