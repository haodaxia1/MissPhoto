package com.example.administrator.missphoto;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 15530 on 2016/12/6.
 */

public class CameraAdapter extends BaseAdapter {
    private Context context;
    private List<Camera> cameraList = new ArrayList<>();

    public CameraAdapter(Context context, List<Camera> cameraList) {
        this.context = context;
        this.cameraList = cameraList;
    }

    @Override
    public int getCount() {
        return cameraList.size();
    }

    @Override
    public Object getItem(int i) {
        return cameraList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return cameraList.get(i).getId();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if(null == view){
            view = LayoutInflater.from(context).inflate(R.layout.layout_camera_item,null);
        }
        ImageView IvHeadportrait = (ImageView) view.findViewById(R.id.IvCameraitemHeadportrait);
        IvHeadportrait.setImageResource(cameraList.get(i).getHeadPortrait());
        TextView TvName = (TextView) view.findViewById(R.id.TvCameraitemName);
        TvName.setText(cameraList.get(i).getName());
        ImageView IvImage = (ImageView) view.findViewById(R.id.IvCameraitemImage);
        IvImage.setImageResource(cameraList.get(i).getImage());
        EditText EtComment = (EditText) view.findViewById(R.id.EtCameraitemComment);
        EtComment.setText(cameraList.get(i).getComment());
        return view;
    }
}
