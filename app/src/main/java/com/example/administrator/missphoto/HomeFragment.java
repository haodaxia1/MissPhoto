package com.example.administrator.missphoto;

import android.app.Fragment;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLDecoder;
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
    private String urlRequestfragmentPath;
    private URL url;
    //点赞榜
    private ImageView pic;
    private ImageView number;
    private TextView name;
    private List<HomeLike> lLike = new ArrayList<>();
    private HomeLikeAdapter likeAdapter;
    private ListView mLikeList;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_home, container, false);

        flipper = (ViewFlipper)view.findViewById(R.id.flipper);//轮播图
        //最新需求
        mRequestList = (ListView)view.findViewById(R.id.LvReqAdpview);
        title = (TextView) view.findViewById(R.id.title );
        request = (TextView) view.findViewById(R.id.request );
        phone = (TextView) view.findViewById(R.id.phone );
        time = (TextView) view.findViewById(R.id.time );
        getData();//获取最新需求的数据
        requestAdapter = new HomeRequestAdapter(getActivity(),lRequest);
        mRequestList.setAdapter(requestAdapter);
        //点赞榜
        mLikeList = (ListView)view.findViewById(R.id.grid);
        pic = (ImageView)view.findViewById(R.id.pic );
/*        number = (ImageView) view.findViewById(R.id.number );*/
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
    private void getData(){
        new  Thread(){
            @Override
            public void run() {
                try {
                    urlRequestfragmentPath = "http://10.7.86.108/request/?obj=5";

                    url = new URL(urlRequestfragmentPath);

                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();

                    if (conn.getResponseCode() == 200){
                        //获得服务器响应数据

                        BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream(),"UTF-8"));

                        //数据
                        String retData = null;
                        String responseData = "";
                        while ((retData = in.readLine()) != null){
                            responseData += retData;
                        }

                        //通知主线程更新UI
                        Message message = new Message();
                        message.what = 1;
                        message.obj = responseData;
                        handler.sendMessage(message);


//                        JSONObject jsonObject = new JSONObject(responseData);
//                        JSONObject jsonObject = new JSONObject(responseData);
//                        JSONArray jsonArray = jsonObject.getJSONArray("list");
//                        for (int i = 0; i < jsonArray.length(); i++) {
//                            JSONObject item = jsonArray.getJSONObject(i); // 得到每个对象
//                            int id = item.getInt("rid");
//                            String weburl = item.getString("rdate");
//                            String username = item.getString("rdetail");
//                            map = new HashMap<String, String>();
//                            map.put("rid", id + "");
//                            map.put("rdate", weburl);
//                            map.put("rdetail",username);
//                            list.add(map);
//                        }
//                        System.out.println(list.get(0));
//                        String json = responseData;
//                        JSONArray j = new JSONArray(json);
//                        for (int i = 0 ; i < j.length();i++){
//                            JSONObject item = j.getJSONObject(i);
//                            int rid=item.getInt("rid");
//                            String rdate = item.getString("rdate");
////                            String rdetail = item.getString("rdetail");//URLEncoder.encode(item.getString("rdetail"),"utf-8");//item.getString("rdetail"); //URLEncoder.encode(userName.getText().toString(),"UTF-8")
//                            String redetail = URLDecoder.decode(item.getString("rdetail"),"utf-8");
//
////                            byte[] b_rdetail = rdetail.getBytes("utf-8");
////                            String r_rdetail = new String(b_rdetail,"UTF-8");
//
//                            int ruid = item.getInt("ruid");
//
//                            lRequest.add(new Requset((long)rid,R.drawable.touxiang3,"作者",redetail,rdate));
//                            System.out.println(rid + rdate + redetail + ruid );
//
//                        }


                        //System.out.println(rid + rdate + rdetail + ruid + "&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&");
//                        if (responseData != null && responseData.length() > 0) {
//                            JSONObject jsonObject = new JSONObject(responseData);
//                            JSONArray jsonArray = (JSONArray)jsonObject.get("ruid");
//                            for (int i = 0;i < 3; i++){
//                                JSONObject jsonObject1 = jsonArray.getJSONObject(i);
//                                String rdate = jsonObject1.getString("rdate");
//                                System.out.println(rdate);
//                            }
//                        }
                        //Log.e("***********************",responseData);
                        //System.out.println(responseData);
                        in.close();
                        /*Intent i = new Intent();
                        i.setClass(GettimeActivity.this,TakephotoActivity.class);
                        startActivity(i);*/

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 1){
                JSONArray j = null;
                try {
                    j = new JSONArray((String)msg.obj);
                    for (int i = 0 ; i < j.length();i++){
                        JSONObject item = j.getJSONObject(i);
                        int rid=item.getInt("rid");
                        String rdate = item.getString("rdate");
//                        String rtitle = item.getString("rtitle");//URLEncoder.encode(item.getString("rdetail"),"utf-8");//item.getString("rdetail"); //URLEncoder.encode(userName.getText().toString(),"UTF-8")
                        String redetail = URLDecoder.decode(item.getString("rdetail"),"utf-8");

//                            byte[] b_rdetail = rdetail.getBytes("utf-8");
//                            String r_rdetail = new String(b_rdetail,"UTF-8");
                        String rname = item.getString("rname");
                        int ruid = item.getInt("ruid");
                        String account = item.getString("account");
                        lRequest.add(new HomeRequest(rid,rname,redetail,account,rdate));
                        System.out.println(rid + rname+rdate + redetail + ruid+account );
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }

//
            }
        }
    };
    /*private void getData() {
        lRequest.add(new HomeRequest(0,"100","标题","需求详情","联系方式","2016-1-1"));
        lRequest.add(new HomeRequest(1,"99","zhaozhao","zhaozhaozhoa","1234","2016-1-1"));
        lRequest.add(new HomeRequest(2,"98","zhaozhao","zhaozhaozhoa","1234","2016-1-1"));
    }*/
    private void getLikeDate() {
        lLike .add(new HomeLike(0,R.drawable.draw1,"有人说，一下雪，北京便成了北平。对故宫，也如是。一夜飞雪，紫禁城便穿越回了六百年前。"));
        lLike .add(new HomeLike(1,R.drawable.draw2,"合适的曝光时间对拍摄效果尤为重要，时间过长雾流会显得模糊，过短则看起来粗糙。"));
        lLike .add(new HomeLike(2,R.drawable.draw3,"场名为《尘与雪》的展览，能使你忘却一切尘世的喧嚣，眼前只有生命的空灵和洁净。"));
    }
}


