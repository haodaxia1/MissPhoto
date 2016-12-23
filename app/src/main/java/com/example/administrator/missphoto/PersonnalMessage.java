package com.example.administrator.missphoto;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.sharesdk.onekeyshare.OnekeyShare;

public class PersonnalMessage extends FirstActivity {
    private ImageButton btn_back;
    private String urlPath2;
    private URL url;
    private EditText passWord;
    private EditText userName;
    private TextView uaccount;
    private TextView uname;
    private String name,account;
    String responseData = "";
    private Button personnal_mine;
    private TextView fanhui;
    private TextView fenxiang;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.personnal_message);
        btn_back=(ImageButton)findViewById(R.id.btn_back1);
        uaccount=(TextView)findViewById(R.id.personal_uaccount);
        uname=(TextView)findViewById(R.id.personal_username);
        fanhui=(TextView)findViewById(R.id.personal_fanhui);
        fenxiang=(TextView)findViewById(R.id.personal_fenxiang);
        fanhui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent();
                i.setClass(PersonnalMessage.this, MainActivity.class);
                startActivity(i);
            }
        });
        fenxiang.setOnClickListener(new  View.OnClickListener() {

            @Override
            public void onClick(View view) {

                OnekeyShare oks = new OnekeyShare();
                //关闭sso授权
                oks.disableSSOWhenAuthorize();
                // title标题，印象笔记、邮箱、信息、微信、人人网、QQ和QQ空间使用
                oks.setTitle("标题");
                // titleUrl是标题的网络链接，仅在Linked-in,QQ和QQ空间使用
                oks.setTitleUrl("http://sharesdk.cn");
                // text是分享文本，所有平台都需要这个字段
                oks.setText("我是分享文本");
                //分享网络图片，新浪微博分享网络图片需要通过审核后申请高级写入接口，否则请注释掉测试新浪微博
                oks.setImageUrl("http://f1.sharesdk.cn/imgs/2014/02/26/owWpLZo_638x960.jpg");
                // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
                //oks.setImagePath("/sdcard/test.jpg");//确保SDcard下面存在此张图片
                // url仅在微信（包括好友和朋友圈）中使用
                oks.setUrl("http://sharesdk.cn");
                // comment是我对这条分享的评论，仅在人人网和QQ空间使用
                oks.setComment("我是测试评论文本");
                // site是分享此内容的网站名称，仅在QQ空间使用
                oks.setSite("ShareSDK");
                // siteUrl是分享此内容的网站地址，仅在QQ空间使用
                oks.setSiteUrl("http://sharesdk.cn");

// 启动分享GUI
                oks.show(getBaseContext());
            }
        });
        SharedPreferences preferences=getSharedPreferences("user", Context.MODE_PRIVATE);
        String name=preferences.getString("name", "name");
        String account=preferences.getString("account", "account");
        uaccount.setText(account);
        uname.setText(name);
//        personnal_mine=(Button)findViewById(R.id.personal_mine);
//        personnal_mine.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                SharedPreferences preferences=getSharedPreferences("user", Context.MODE_PRIVATE);
//                String name=preferences.getString("name", "name");
//                String account=preferences.getString("account", "account");
//                uaccount.setText(account);
//                uname.setText(name);
//            }
//        });


}
}
