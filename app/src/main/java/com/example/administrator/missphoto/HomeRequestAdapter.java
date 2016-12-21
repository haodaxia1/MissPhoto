package com.example.administrator.missphoto;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lenovo on 2016/12/21.
 */
public class HomeRequestAdapter extends BaseAdapter {

    private Context context;
    private List<HomeRequest> lRequest = new ArrayList<>();

    public HomeRequestAdapter(Context context, List<HomeRequest> lRequest) {
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
            view = LayoutInflater.from(context).inflate(R.layout.layout_homerequest_item,null);
        }

        TextView num  = (TextView) view.findViewById(R.id.num );
        num  .setText(lRequest.get(i).getNum());
        TextView title = (TextView) view.findViewById(R.id.title );
        title .setText(lRequest.get(i).getTitle());
        TextView request = (TextView) view.findViewById(R.id.request );
        request .setText(lRequest.get(i).getRequest());
        TextView phone = (TextView) view.findViewById(R.id.phone );
        phone .setText(lRequest.get(i).getPhone());
        TextView time = (TextView) view.findViewById(R.id.time );
        time .setText(lRequest.get(i).getTime());
        return view;
    }
}//
