package com.example.administrator.missphoto;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;


public class MineFragment extends Fragment {
    @Nullable
    private Context context;
    private View view;
    private ImageButton btn_mine_qq;
    private ImageButton btn_mine_weibo;
    private ImageButton btn_mine_phone;

    public void setContext(Context context) {
        this.context = context;
    }

    @Override
    public Context getContext() {
        return context;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.layout_mine, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        btn_mine_qq=(ImageButton)view.findViewById(R.id.btn_mine_qq);
        btn_mine_weibo=(ImageButton)view.findViewById(R.id.btn_mine_weibo);
        btn_mine_phone=(ImageButton)view.findViewById(R.id.btn_mine_phone);
        btn_mine_phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(getActivity(), ZhuceActivity.class);
                startActivity(intent);
            }
        });
        btn_mine_qq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(getActivity(), ZhuceActivity.class);
                startActivity(intent);
            }
        });
        btn_mine_weibo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(getActivity(), ZhuceActivity.class);
                startActivity(intent);
            }
        });

    }
}
