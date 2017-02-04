package com.npc.shop.ui.checkout;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.npc.shop.R;
import com.npc.shop.app.AppSetting;
import com.npc.shop.ui.term.TermActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Lenovo on 1/22/2017.
 */

public class ValidateFragment extends Fragment {

    @BindView(R.id.tvPrice) TextView tvPrice;
    @BindView(R.id.tvTotal) TextView tvTotal;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_validate, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        int price = AppSetting.getInstance().getCart();
        tvPrice.setText(price + " F CFA");
        tvTotal.setText((price + 500) + "F CFA");
    }

    @OnClick(R.id.tvterm)
    public void onTerm(){
        getContext().startActivity(new Intent(getContext(), TermActivity.class));
    }

}
