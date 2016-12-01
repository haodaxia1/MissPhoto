package com.example.administrator.missphoto;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class RequestFragment extends Fragment {
    private View view;
    private ImageButton mHeadphoto;
    private TextView mName;
    private TextView mRequest;
    private TextView mTime;
    private List<Requset> lRequest = new ArrayList<>();
    private RequestAdapter requestAdapter;
    private ListView mRequestList;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.layout_request, container, false);

        findView();

        getData();
        requestAdapter = new RequestAdapter(getActivity(),lRequest);
        mRequestList.setAdapter(requestAdapter);

        return view;
    }

/*    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);



    }*/

    private void getData() {
        lRequest.add(new Requset(0L,R.drawable.touxiang3,"喵星狗宠物店老版","构图较完美。整个作品看起来均衡、稳定、有规律。有明显的视觉美。","2016-1-1"));
        lRequest.add(new Requset(1L,R.drawable.touxiang1,"神经病摄影店","轮廓清晰，主体突出，线条分明","2016-12-19"));
        lRequest.add(new Requset(2L,R.drawable.touxiang2,"(⊙o⊙)哦","色彩鲜艳、饱和、丰满，层次分明，有较强的感染力。","2016-8-18"));
        lRequest.add(new Requset(3L,R.drawable.touxiang3,"#%@#……#！……","对焦清晰，曝光正确。主题突出，细节明了。","2016-9-1"));
    }

    private void findView() {
        mRequestList = (ListView)view.findViewById(R.id.LvReqAdpview);
        mHeadphoto = (ImageButton)view.findViewById(R.id.IbReqitemHeadphoto);
        mName = (TextView)view.findViewById(R.id.TvReqitemName);
        mRequest = (TextView)view.findViewById(R.id.TvReqitemRequest);
        mTime = (TextView)view.findViewById(R.id.TvReqitemTime);
    }
}
