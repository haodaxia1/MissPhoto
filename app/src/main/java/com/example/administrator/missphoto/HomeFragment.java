package com.example.administrator.missphoto;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.ViewFlipper;

import java.util.ArrayList;
import java.util.List;


public class HomeFragment extends Fragment {
    private ViewFlipper flipper;
    private int[] resId = {R.drawable.p1, R.drawable.p5, R.drawable.p3,R.drawable.p4,R.drawable.p2};//轮播图图片
    //最新需求
    private TextView num;
    private TextView title;
    private TextView request;
    private TextView phone;
    private TextView time;
    private List<HomeRequest> lRequest = new ArrayList<>();
    private HomeRequestAdapter requestAdapter;
    private ListView mRequestList;
    //点赞榜
    private ImageView pic;
    private ImageView number;
    private TextView name;
    private List<HomeLike> lLike = new ArrayList<>();
    private HomeLikeAdapter likeAdapter;
    private GridView mLikeList;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_home, container, false);

        flipper = (ViewFlipper)view.findViewById(R.id.flipper);//轮播图
        //最新需求
        mRequestList = (ListView)view.findViewById(R.id.LvReqAdpview);
        num = title = (TextView) view.findViewById(R.id.num );
        title = (TextView) view.findViewById(R.id.title );
        request = (TextView) view.findViewById(R.id.request );
        phone = (TextView) view.findViewById(R.id.phone );
        time = (TextView) view.findViewById(R.id.time );
        getData();//获取最新需求的数据
        requestAdapter = new HomeRequestAdapter(getActivity(),lRequest);
        mRequestList.setAdapter(requestAdapter);
        //点赞榜
        mLikeList = (GridView)view.findViewById(R.id.grid);
        pic = (ImageView)view.findViewById(R.id.pic );
        number = (ImageView) view.findViewById(R.id.number );
        name = (TextView) view.findViewById(R.id.name );
        getLikeDate();//获取点赞榜数据
        likeAdapter = new HomeLikeAdapter(getActivity(),lLike);
        mLikeList.setAdapter(likeAdapter);


        //首页轮播图代码
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
        //首页轮播图代码



    }
    private ImageView getImageView(int resId) {
        ImageView image = new ImageView(getActivity());
        /*    ImageView image = new HomeFragment().getImageView(resId);*/
        image.setBackgroundResource(resId);
        return image;
    }
    private void getData() {
        lRequest.add(new HomeRequest(0,"100","zhaozhao","zhaozhaozhoa","1234","2016-1-1"));
        lRequest.add(new HomeRequest(1,"99","zhaozhao","zhaozhaozhoa","1234","2016-1-1"));
        lRequest.add(new HomeRequest(2,"98","zhaozhao","zhaozhaozhoa","1234","2016-1-1"));
    }
    private void getLikeDate() {
        lLike .add(new HomeLike(0,R.drawable.yuepai,R.drawable.num1,"2016-1-1"));
        lLike .add(new HomeLike(1,R.drawable.yuepai,R.drawable.num1,"2016-1-1"));
        lLike .add(new HomeLike(2,R.drawable.yuepai,R.drawable.num1,"2016-1-1"));
        lLike .add(new HomeLike(3,R.drawable.yuepai,R.drawable.num1,"2016-1-1"));
        lLike .add(new HomeLike(4,R.drawable.yuepai,R.drawable.num1,"2016-1-1"));
        lLike .add(new HomeLike(5,R.drawable.yuepai,R.drawable.num1,"2016-1-1"));
    }
}


