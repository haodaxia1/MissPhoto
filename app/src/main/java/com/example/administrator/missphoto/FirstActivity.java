package com.example.administrator.missphoto;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;
import cn.smssdk.gui.RegisterPage;

public class FirstActivity extends Activity {
    private String urlPath2;
    private URL url = null;
    private EditText userName;
    private EditText passWord;
    private Button btn_zhuce_entry;
    private TextView btn_first_ok;
    public String name,account;

    Handler handler2 = new Handler()
    {
        public void handleMessage(Message msg)
        {
            if (msg.what == 0x123) {
                Toast.makeText(FirstActivity.this, "登陆成功，Welcom MissPhoto", Toast.LENGTH_LONG).show();
                Intent intent = new Intent();
                intent.setClass(FirstActivity.this,MainActivity.class);
                startActivity(intent);
            } else {
                Toast.makeText(FirstActivity.this, "用户名或者密码错误", Toast.LENGTH_SHORT).show();
            }
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        //判断是否为下载后第一次打开程序
        SharedPreferences googleActivitySP = getSharedPreferences("Tutorial", Context.MODE_PRIVATE);
        boolean firstStart = googleActivitySP.getBoolean("first_start", true);
        if(firstStart == true){

            Intent intent = new Intent(this, TutorialIntroPageActivity.class);
            startActivity(intent);

            SharedPreferences.Editor edit = googleActivitySP.edit();
            edit.putBoolean("first_start", false);
            edit.commit();}
        userName=(EditText)findViewById(R.id.first_username);
        passWord=(EditText)findViewById(R.id.first_password);
        //设置密码为密文显示
        passWord.setTransformationMethod(PasswordTransformationMethod.getInstance());
        btn_zhuce_entry=(Button)findViewById(R.id.btn_first_entry);
        btn_first_ok=(TextView)findViewById(R.id.btn_first_ok);
        btn_first_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(FirstActivity.this,ZhuceActivity.class);
                startActivity(intent);
            }
        });

        btn_zhuce_entry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    urlPath2 = (new Utils().URL)+"user/?obj=1&upwd="+passWord.getText().toString()
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
                                if(responseData.equals("false")){
                                    handler2.sendEmptyMessage(0x122);
                                }
                                String json = responseData;
                                JSONArray j=new JSONArray(json);
                                JSONObject item=j.getJSONObject(0);
                                int id=item.getInt("uid");
                                account=item.getString("uaccount");
                                name=item.getString("uname");
                                handler2.sendEmptyMessage(0x123);
                                SharedPreferences preferences=getSharedPreferences("user",Context.MODE_PRIVATE);
                                SharedPreferences.Editor editor=preferences.edit();
                                editor.putInt("id", id);
                                editor.putString("name", name);
                                editor.putString("account", account);
                                editor.commit();
                                in.close();
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                    }
                }.start();
            }
        });


    }


}
