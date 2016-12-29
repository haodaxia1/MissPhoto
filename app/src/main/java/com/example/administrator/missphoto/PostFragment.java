package com.example.administrator.missphoto;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.apache.http.Header;

import java.io.File;
import java.io.FileNotFoundException;

import static com.example.administrator.missphoto.R.id.btn_post_draw;
import static com.tencent.open.utils.Global.getSharedPreferences;


public class PostFragment extends Fragment {
    private Context context;
    private View view;
    private ImageButton btn_post_request;
    private ImageButton btn_post_draw;
    private static final int IMAGE = 1;
    private int uid;

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

        view = inflater.inflate(R.layout.layout_post, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        btn_post_request = (ImageButton) view.findViewById(R.id.btn_post_request);
        btn_post_draw = (ImageButton)view.findViewById(R.id.btn_post_draw);
        SharedPreferences preferences = getActivity().getSharedPreferences("user", Context.MODE_PRIVATE);
        uid=preferences.getInt("id",0);

        btn_post_request.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(getActivity(), GettimeActivity.class);
                startActivity(intent);
            }
        });
        btn_post_draw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("****************************************");
                Intent intent = new Intent(Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, IMAGE);
            }
        });
    }
//    public void onClick(View v) {
//        //调用相册
//        System.out.println("****************************************");
//        Intent intent = new Intent(Intent.ACTION_PICK,
//                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
//        startActivityForResult(intent, IMAGE);
//    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //获取图片路径
        if (requestCode == IMAGE && resultCode == Activity.RESULT_OK && data != null) {
            Uri selectedImage = data.getData();
            String[] filePathColumns = {MediaStore.Images.Media.DATA};
            Cursor c = getActivity().getContentResolver().query(selectedImage, filePathColumns, null, null, null);
            c.moveToFirst();
            int columnIndex = c.getColumnIndex(filePathColumns[0]);
            String imagePath = c.getString(columnIndex);
            showImage(imagePath);
            c.close();
            uploadPic(imagePath);
        }
    }

    //加载图片
    private void showImage(String imaePath){

        Bitmap bm = BitmapFactory.decodeFile(imaePath);
        btn_post_draw.setImageBitmap(bm);
    }
    private void uploadPic(String cropIMagePath){
        if(cropIMagePath == null){
            Toast.makeText(getActivity(),"文件不存在",Toast.LENGTH_SHORT).show();
            return;
        }
        File file =new File(cropIMagePath);
        if(file.exists() && file.length()>0){
            AsyncHttpClient client = new AsyncHttpClient();

            RequestParams params=new RequestParams();

            params.put("uid",uid);
            try {
                params.put("img",file);

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            String urls=(new Utils().URL)+"draw/add";

            client.post(urls, params, new AsyncHttpResponseHandler() {
                @Override
                public void onSuccess(int i, Header[] headers, byte[] bytes) {
                    String result=new String(bytes);
                    System.out.print(result);

                    if(result.equals("1")){

                        Toast.makeText(getActivity(),"发布成功",Toast.LENGTH_SHORT).show();
                        Intent m=new Intent(getActivity(),MainActivity.class);
//                        Utils.utils=3;
                        startActivity(m);
//                        finish();
                    }
                    else{

                        Toast.makeText(getActivity(),"发布失败",Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(int i, Header[] headers, byte[] bytes, Throwable throwable) {
                    Toast.makeText(getActivity(),"发布失败",Toast.LENGTH_SHORT).show();
                }
            });

        }
    }
}

