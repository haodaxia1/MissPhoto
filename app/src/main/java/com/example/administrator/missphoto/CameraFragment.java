package com.example.administrator.missphoto;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class CameraFragment extends Fragment {
    private View view;
    private Context context;
    private ImageView IvHeadportrait;
    private TextView TvName;
    private ImageView IvImage;
    private TextView TvUserName;
    private TextView TvUserComment;
    private List<Camera> listCamera = new ArrayList<>();
    private CameraAdapter cameraAdapter;
    private ListView LvCameraList;

    @Nullable

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

        view = inflater.inflate(R.layout.layout_camera, container, false);

        findView();

        getData();
        cameraAdapter = new CameraAdapter(getActivity(),listCamera);
        LvCameraList.setAdapter(cameraAdapter);
        return view;
    }

    private void getData() {
        listCamera.add(new Camera(0L,R.drawable.touxiang3,"喵星狗宠物店老版",R.drawable.shili4,1,"我是天才","构图较完美。整个作品看起来均衡、稳定、有规律。有明显的视觉美。"));
        listCamera.add(new Camera(1L,R.drawable.touxiang1,"神经病摄影店",R.drawable.shili5,1,"我是天才","轮廓清晰，主体突出，线条分明"));
        listCamera.add(new Camera(2L,R.drawable.touxiang2,"(⊙o⊙)哦",R.drawable.shili4,1,"我是天才","色彩鲜艳、饱和、丰满，层次分明，有较强的感染力。"));
        listCamera.add(new Camera(3L,R.drawable.touxiang3,"#%@#……#！……",R.drawable.shili5,1,"我是天才","对焦清晰，曝光正确。主题突出，细节明了。"));
    }

    private void findView() {
        LvCameraList = (ListView)view.findViewById(R.id.LvCameraList);
        IvHeadportrait = (ImageView) view.findViewById(R.id.IvCameraitemHeadportrait);
        TvName = (TextView) view.findViewById(R.id.TvCameraitemName);
        IvImage = (ImageView) view.findViewById(R.id.IvCameraitemImage);
        TvUserComment = (TextView) view.findViewById(R.id.tv_user_comment);
        TvUserName = (TextView) view.findViewById(R.id.tv_user_name);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }
}
