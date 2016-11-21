package com.example.administrator.missphoto;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;

public class EntryActivity extends Activity {

    private Button btn_pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entry);
        btn_pass=(Button)findViewById(R.id.btn_pass);
    }
}
