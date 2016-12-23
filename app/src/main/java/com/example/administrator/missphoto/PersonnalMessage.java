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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.personnal_message);
        btn_back=(ImageButton)findViewById(R.id.btn_back1);
        uaccount=(TextView)findViewById(R.id.personal_uaccount);
        uname=(TextView)findViewById(R.id.personal_username);

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent();
                i.setClass(PersonnalMessage.this, MainActivity.class);
                startActivity(i);
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
