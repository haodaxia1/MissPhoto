package com.example.administrator.missphoto;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lenovo on 2016/12/21.
 */
public class HomeLikeAdapter extends BaseAdapter {

    private Context context;
    private List<HomeLike> lLike = new ArrayList<>();

    public HomeLikeAdapter(Context context, List<HomeLike> lLike) {
        this.context = context;
        this.lLike = lLike;
    }

    @Override
    public int getCount() {
        return lLike.size();
    }

    @Override
    public Object getItem(int i) {
        return lLike.get(i);
    }

    @Override
    public long getItemId(int i) {
        return lLike.get(i).getId();
    }
//
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if(null == view){
            view = LayoutInflater.from(context).inflate(R.layout.layout_homelike_item,null);
        }

        ImageView pic = (ImageView) view.findViewById(R.id.pic );
        pic .setImageResource(lLike.get(i).getPic());
        ImageView number = (ImageView) view.findViewById(R.id.number );
        number .setImageResource(lLike.get(i).getNumber());
        TextView name = (TextView) view.findViewById(R.id.name );
        name .setText(lLike.get(i).getName());

        return view;
    }
}

