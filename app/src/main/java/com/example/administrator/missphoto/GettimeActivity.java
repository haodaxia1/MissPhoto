package com.example.administrator.missphoto;

/**
 * Created by 隔窗望海 on 2016/12/8.
 */
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Calendar c = Calendar.getInstance();
        year = c.get(Calendar.YEAR);
        month = c.get(Calendar.MONTH);
        day = c.get(Calendar.DAY_OF_MONTH);
        hour = c.get(Calendar.HOUR_OF_DAY);
        minute = c.get(Calendar.MINUTE);
        second=c.get(Calendar.SECOND);
        editText=(EditText)findViewById(R.id.edtext) ;
        button=(Button)findViewById(R.id.btn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editText.setText(year+"-"+month+"-"+day+"\t"+hour+":"+minute+":"+second);
            }
        });
    }
}