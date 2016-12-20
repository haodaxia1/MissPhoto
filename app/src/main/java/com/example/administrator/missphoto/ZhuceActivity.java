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
    private EditText userName;
    private EditText passWord;
    private EditText uaccount;
    private Button btn_zhuce_entry;

    Handler handler2 = new Handler()
    {
        public void handleMessage(Message msg)
        {
            if (msg.what == 0x123) {
                Toast.makeText(ZhuceActivity.this, "登陆成功", Toast.LENGTH_LONG).show();
                Intent intent = new Intent();
                intent.setClass(ZhuceActivity.this,GettimeActivity.class);
                startActivity(intent);
            } else {
                Toast.makeText(ZhuceActivity.this, "登陆失败", Toast.LENGTH_LONG).show();
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
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
        btn_zhuce_entry=(Button)findViewById(R.id.btn_zhuce_entry);

        btn_zhuce_entry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    urlPath2 = "http://172.16.17.133:8080/user/?obj=1&upwd="+passWord.getText().toString()
                            +"&uname="+URLEncoder.encode(userName.getText().toString(),"UTF-8");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                new Thread() {
                    public void run ()
                    {
                        try {
                            url = new URL(urlPath2);
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

                                if (responseData.equals("ture")) {
                                    handler2.sendEmptyMessage(0x123);
                                }else {
                                    handler2.sendEmptyMessage(0x122);
                                }
                                in.close();
                            }

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }.start();
            }
        });
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

                                            urlPath = "http://172.16.17.133:8080/user/?obj=0&upwd="+passWord.getText().toString()
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


    }


}
