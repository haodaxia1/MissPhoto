package com.example.administrator.missphoto;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.OnekeyShare;
import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;
import cn.smssdk.gui.CommonDialog;
import cn.smssdk.gui.ContactsPage;
import cn.smssdk.gui.RegisterPage;

public class ZhuceActivity extends Activity {
    private String urlPath,urlPath2;
    private URL url = null;
    private ImageButton btn_back;
    private Button btn_ok;
    private Button btn_fx;
    private EditText userName;
    private EditText passWord;
    private EditText uaccount;
    private Button btn_zhuce_entry;
    //

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ShareSDK.initSDK(this);
        SMSSDK.initSDK(this, "191991a25bce4", "93d28288195be0f357eaf4f9e7ad87e4");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zhuce);
        userName=(EditText)findViewById(R.id.zhuce_user);
        passWord=(EditText)findViewById(R.id.zhuce_password);
        uaccount=(EditText)findViewById(R.id.zhuce_uaccount);
        btn_back=(ImageButton)findViewById(R.id.btn_back);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent();
                i.setClass(ZhuceActivity.this, MainActivity.class);
                startActivity(i);
            }
        });
        btn_ok=(Button)findViewById(R.id.btn_ok);

        btn_ok.setOnClickListener(new  View.OnClickListener(){

            @Override
            public void onClick(View view) {
                RegisterPage registerPage = new RegisterPage();
                //回调函数
                registerPage.setRegisterCallback(new EventHandler()
                {
                    public void afterEvent(int event, int result, Object data)
                    {
                        // 解析结果
                        if (result == SMSSDK.RESULT_COMPLETE)
                        {
                            //提交验证码成功，此时已经验证成功了
                            if (event == SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE)
                            {
                                Toast.makeText(ZhuceActivity.this,"success",Toast.LENGTH_SHORT).show();
                                new Thread()
                                {
                                    public void run()
                                    {
                                        try
                                        {

                                            urlPath = "http://10.7.88.111:8080/user/?obj=0&upwd="+passWord.getText().toString()
                                                    +"&uname="+URLEncoder.encode(userName.getText().toString(),"UTF-8")
                                                    +"&uaccount="+URLEncoder.encode(uaccount.getText().toString(),"UTF-8");

                                            url = new URL(urlPath);


                                            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

                                            if (conn.getResponseCode() == 200) {
                                                // 获得服务器响应的数据
                                                BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
                                                // 数据
                                                String retData = null;
                                                String responseData = "";
                                                while ((retData = in.readLine()) != null) {
                                                    responseData += retData;
                                                }
                                                in.close();
                                                Intent i=new Intent();
                                                i.setClass(ZhuceActivity.this, FirstActivity.class);
                                                startActivity(i);
                                            }
                                        } catch (Exception e) {
                                            e.printStackTrace();
                                        }
                                    }
                                }.start();
                            }
                            //已发送验证码
                            else if (event == SMSSDK.EVENT_GET_VERIFICATION_CODE)
                            {
                            }
                        }
                    }
                });
                registerPage.show(getBaseContext());
            }
        });
        btn_fx=(Button)findViewById(R.id.btn_fx);
        btn_fx.setOnClickListener(new  View.OnClickListener() {

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


    }


}
