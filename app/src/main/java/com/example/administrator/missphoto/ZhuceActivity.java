package com.example.administrator.missphoto;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;
import cn.smssdk.gui.CommonDialog;
import cn.smssdk.gui.ContactsPage;
import cn.smssdk.gui.RegisterPage;

public class ZhuceActivity extends Activity {
    private ImageButton btn_back;
    private Button btn_ok;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        SMSSDK.initSDK(this, "191991a25bce4", "93d28288195be0f357eaf4f9e7ad87e4");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zhuce);
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
