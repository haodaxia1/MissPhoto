package com.example.administrator.missphoto;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.BinaryHttpResponseHandler;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLDecoder;
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
    private String urlRequestfragmentPath;
    private URL url;
    String urls=(new Utils().URL)+"upload/";

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
        cameraAdapter = new CameraAdapter(getActivity(), listCamera);
        LvCameraList.setAdapter(cameraAdapter);
        return view;
    }

    private void getData() {

        new  Thread(){
            @Override
            public void run() {
                try {
                    urlRequestfragmentPath = (new Utils().URL)+"draw/?obj=9";

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



                        in.close();


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
                        int did = item.getInt("did");     //图片id
                        String durl = item.getString("durl");   //图片地址
                        int dlike=item.getInt("dlike");     //图片点赞数
                        int udid=item.getInt("udid");   //图片发布人id


                        //通知主线程更新UI
                        Message message = new Message();
                        message.what = 1;
                        message.obj = urls + durl;
                        handler1.sendMessage(message);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                } catch (Exception e) {
                    e.printStackTrace();
                }


            }
        }
    };
    private Handler handler1 = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 1){

                /**
                 * @param url 要下载的文件URL
                 * @throws Exception
                 */
//                public  void downloadFile(String url) throws Exception {


                    AsyncHttpClient client = new AsyncHttpClient();
                    // 指定文件类型
                    String[] allowedContentTypes = new String[]{"image/png", "image/jpeg"};
                    String url = msg.obj.toString();
                    // 获取二进制数据如图片和其他文件
                    client.get(url, new BinaryHttpResponseHandler(allowedContentTypes) {

                        @Override
                        public void onSuccess(int statusCode, Header[] headers, byte[] binaryData) {


                            //
                            Bitmap bmp = BitmapFactory.decodeByteArray(binaryData, 0, binaryData.length);

                            listCamera.add(new Camera(0L, R.drawable.touxiang3, "喵星狗宠物店老版", bmp));



                        }

                        @Override
                        public void onFailure(int statusCode, Header[] headers,
                                              byte[] binaryData, Throwable error) {
                            // TODO Auto-generated method stub
                            System.out.println("下载失败*********************************");

                        }


                        @Override
                        public void onProgress(long bytesWritten, long totalSize) {
                            super.onProgress(bytesWritten, totalSize);
                            int count = (int) ((bytesWritten * 1.0 / totalSize) * 100);
                            // 下载进度显示

                            Log.e("下载 Progress>>>>>", bytesWritten + " / " + totalSize);
                        }

                        @Override
                        public void onRetry(int retryNo) {
                            // TODO Auto-generated method stub
                            super.onRetry(retryNo);
                            // 返回重试次数
                        }

                    });
            }





        }
    };

    private void findView() {
        LvCameraList = (ListView) view.findViewById(R.id.LvCameraList);
        IvHeadportrait = (ImageView) view.findViewById(R.id.IvCameraitemHeadportrait);
        TvName = (TextView) view.findViewById(R.id.TvCameraitemName);
        IvImage = (ImageView) view.findViewById(R.id.IvCameraitemImage);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }


}
