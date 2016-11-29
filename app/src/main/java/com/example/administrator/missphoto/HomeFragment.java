package com.example.administrator.missphoto;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.ViewFlipper;


public class HomeFragment extends Fragment {
    private ViewFlipper flipper;
    private int[] resId = {R.drawable.yuepai, R.drawable.yuehua, R.drawable.shili1};
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_home, container, false);
        flipper = (ViewFlipper)view.findViewById(R.id.flipper);
        /*
        * 动态导入的方式为ViewFlipper加入子View
        * */
        for (int i = 0; i < resId.length; i++) {
            flipper.addView(getImageView(resId[i]));
        }
        /*
        * 为ViewFlipper去添加动画效果
        * */
        TranslateAnimation rightInAnim = new TranslateAnimation(Animation.RELATIVE_TO_PARENT, 1.0f,
                Animation.RELATIVE_TO_PARENT, 0.0f,
                Animation.RELATIVE_TO_PARENT, 0.0f,
                Animation.RELATIVE_TO_PARENT, 0.0f);
        rightInAnim.setDuration(1000);
        TranslateAnimation leftOutAnim = new TranslateAnimation(Animation.RELATIVE_TO_PARENT, 0.0f,
                Animation.RELATIVE_TO_PARENT, -1.0f,
                Animation.RELATIVE_TO_PARENT, 0.0f,
                Animation.RELATIVE_TO_PARENT, 0.0f);
        leftOutAnim.setDuration(1000);
        flipper.setInAnimation(rightInAnim);
        flipper.setOutAnimation(leftOutAnim);
        flipper.setFlipInterval(2000);
        flipper.startFlipping();
        return view;
    }
        private ImageView getImageView(int resId) {
            ImageView image = new ImageView(getActivity());
        /*    ImageView image = new HomeFragment().getImageView(resId);*/
            image.setBackgroundResource(resId);
            return image;
        }
}


