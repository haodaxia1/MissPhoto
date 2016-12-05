package com.example.administrator.missphoto;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by 15530 on 2016/11/30.
 */

public class RequestAdapter extends BaseAdapter {

    private Context context;
    private List<Requset> lRequest = new ArrayList<>();

    public RequestAdapter(Context context, List<Requset> lRequest) {
        this.context = context;
        this.lRequest = lRequest;
    }

    @Override
    public int getCount() {
        return lRequest.size();
    }

    @Override
    public Object getItem(int i) {
        return lRequest.get(i);
    }

    @Override
    public long getItemId(int i) {
        return lRequest.get(i).getId();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if(null == view){
            view = LayoutInflater.from(context).inflate(R.layout.layout_request_item,null);
        }
        ImageButton headPhoto = (ImageButton) view.findViewById(R.id.IbReqitemHeadphoto);
        headPhoto.setImageResource(lRequest.get(i).getHeadPhoto());
        TextView TvName = (TextView) view.findViewById(R.id.TvReqitemName);
        TvName.setText(lRequest.get(i).getName());
        TextView TvRequest = (TextView) view.findViewById(R.id.TvReqitemRequest);
        TvRequest.setText(lRequest.get(i).getRequest());
        TextView TvTime = (TextView) view.findViewById(R.id.TvReqitemTime);
        TvTime.setText(lRequest.get(i).getTime());
        return view;
    }
}
