package com.npc.shop.ui.checkout;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.npc.shop.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Lenovo on 1/22/2017.
 */

public class InfoFragment extends Fragment {

    @BindView(R.id.sp1) Spinner sp1;
    @BindView(R.id.sp2) Spinner sp2;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_info, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        List<String> categories = new ArrayList<String>();
        categories.add("...");
        categories.add("Matinée");
        categories.add("Après midi");
        categories.add("Soirée");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, categories);
        sp1.setAdapter(dataAdapter);
        List<String> categories1 = new ArrayList<String>();
        categories1.add("...");
        categories1.add("Makepe");
        categories1.add("Bonamoussadi");
        categories1.add("Akwa");
        categories1.add("Deido");
        categories1.add("Kotto");
        categories1.add("Logpom");
        categories1.add("Bepanda");
        categories1.add("Cité des palmiers");
        categories1.add("Autre");
        ArrayAdapter<String> dataAdapter1 = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, categories1);
        sp2.setAdapter(dataAdapter1);

    }

    @OnClick(R.id.btnValidate)
    public void onValidate(){
        Intent intent = new Intent("com.job.update");
        intent.putExtra("cmd", 2);
        getContext().getApplicationContext().sendBroadcast(intent);
    }
}
