package com.example.administrator.missphoto;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.annotation.StringDef;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import java.util.logging.LogRecord;

import static android.R.id.list;


public class RequestFragment extends Fragment {
    private View view;
    private ImageButton mHeadphoto;
    private TextView mName;
    private TextView mRequest;
    private TextView mTime;
    private List<Requset> lRequest = new ArrayList<>();
    private RequestAdapter requestAdapter;
    private ListView mRequestList;
    private String urlRequestfragmentPath;
    private URL url;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.layout_request, container, false);

        findView();
        getData();
//        addData();
        requestAdapter = new RequestAdapter(getActivity(),lRequest);


        return view;
    }


//    @Override
//    public void onActivityCreated(Bundle savedInstanceState) {
//        super.onActivityCreated(savedInstanceState);
//
//        mRequestList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                getData();
////                addData();
//            }
//        });
//    }

//    private void addData() {
//        lRequest.add(new Requset(0L,R.drawable.touxiang3,"喵星狗宠物店老版","构图较完美。整个作品看起来均衡、稳定、有规律。有明显的视觉美。","2016-1-1"));
//        lRequest.add(new Requset(1L,R.drawable.touxiang1,"神经病摄影店","轮廓清晰，主体突出，线条分明","2016-12-19"));
//        lRequest.add(new Requset(2L,R.drawable.touxiang2,"(⊙o⊙)哦","色彩鲜艳、饱和、丰满，层次分明，有较强的感染力。","2016-8-18"));
//        lRequest.add(new Requset(3L,R.drawable.touxiang3,"#%@#……#！……","对焦清晰，曝光正确。主题突出，细节明了。","2016-9-1"));
//    }

    private void findView() {
        mRequestList = (ListView)view.findViewById(R.id.LvReqAdpview);
        mHeadphoto = (ImageButton)view.findViewById(R.id.IbReqitemHeadphoto);
        mName = (TextView)view.findViewById(R.id.TvReqitemName);
        mRequest = (TextView)view.findViewById(R.id.TvReqitemRequest);
        mTime = (TextView)view.findViewById(R.id.TvReqitemTime);
    }
    private void getData(){
        new  Thread(){
            @Override
            public void run() {
                try {
                    urlRequestfragmentPath = (new Utils().URL)+"request/?obj=5";

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
                        System.out.println("#######################################################");
                        System.out.println(responseData);
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
//                            String rdetail = item.getString("rdetail");//URLEncoder.encode(item.getString("rdetail"),"utf-8");//item.getString("rdetail"); //URLEncoder.encode(userName.getText().toString(),"UTF-8")
                        String redetail = URLDecoder.decode(item.getString("rdetail"),"utf-8");

//                            byte[] b_rdetail = rdetail.getBytes("utf-8");
//                            String r_rdetail = new String(b_rdetail,"UTF-8");

                        int ruid = item.getInt("ruid");
                        String name = item.getString("rname");
                        lRequest.add(new Requset((long)rid,R.drawable.touxiang3,name,redetail,rdate));
                        System.out.println(rid + rdate + redetail + ruid );

                    }
                    mRequestList.setAdapter(requestAdapter);
                } catch (JSONException e) {
                    e.printStackTrace();
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }


            }
        }
    };
}
