package com.example.administrator.missphoto;

/**
 * Created by 隔窗望海 on 2016/12/8.
 */
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Calendar;

public class GettimeActivity extends AppCompatActivity {
    private Button button;
    private int year;
    private int month;
    private int minute;
    private int day;
    private int hour;
    private int second;
    private EditText editText;
    private EditText requestContent;
    private Button publish;
    private String urlRequestPath;
    private URL url;
    private EditText ruId;
    private EditText EtPutrequestRuid;
    private int uid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_putrequest);
        final Calendar c = Calendar.getInstance();
        year = c.get(Calendar.YEAR);
        month = c.get(Calendar.MONTH);
        day = c.get(Calendar.DAY_OF_MONTH);
        hour = c.get(Calendar.HOUR_OF_DAY);
        minute = c.get(Calendar.MINUTE);
        second=c.get(Calendar.SECOND);
        final int Month = month+1;
      findView();
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editText.setText(year+"-"+Month+"-"+day+"\t"+hour+":"+minute+":"+second);
            }
        });


    }
    private void findView() {
        ruId = (EditText)findViewById(R.id.EtPutrequestRuid);
        requestContent = (EditText)findViewById(R.id.EtPutrequestContent);
        editText=(EditText)findViewById(R.id.edtext) ;
        button=(Button)findViewById(R.id.btn);
        publish = (Button)findViewById(R.id.btn_publish);
    }
}