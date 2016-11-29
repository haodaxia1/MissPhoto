package com.example.administrator.missphoto;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ViewFlipper;


public class HomeFragment extends Fragment {
    private ViewFlipper flipper;
   /* private int[] resId = {R.drawable.home_scr1,R.drawable.home_scr2,R.drawable.home_scr3};*/
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.layout_home, container, false);
    }
      /*  flipper = (ViewFlipper)view.findViewById(R.id.flipper);

        *//*
        * 动态导入的方式为ViewFlipper加入子View
        * *//*
        for (int i = 0; i < resId.length; i++) {
            flipper.addView(getImageView(resId[i]));
        }
        *//*
        * 为ViewFlipper去添加动画效果
        * *//*
        flipper.setInAnimation(this.getContext(), R.anim.left_in);
        flipper.setOutAnimation(this.getContext(), R.anim.left_out);
        flipper.setFlipInterval(2000);
        flipper.startFlipping();
    }
    private ImageView getImageView(int resId){
        *//*ImageView image = new ImageView(this);*//*
        ImageView image = new HomeFragment().getImageView(resId);
        image.setBackgroundResource(resId);
        return image;
    }*/
}

