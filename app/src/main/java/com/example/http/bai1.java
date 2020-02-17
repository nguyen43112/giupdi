package com.example.http;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class bai1 extends Activity implements View.OnClickListener {
    public static final String SREVER_NAME ="http://192.168.9.172/android/Sutdent_get.php";
    private EditText ed1,ed2;
    private TextView tvresult;
    private Button b1;
    String strname,strscore;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.b1);
        tvresult = findViewById(R.id.Tvresult);
        ed1= findViewById(R.id.editname);
        ed2= findViewById(R.id.editscore);
        b1= findViewById(R.id.btnsend);
        b1.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
switch (view.getId()){
    case R.id.btnsend:
    strname= ed1.getText().toString();
    strscore=ed2.getText().toString();
    BackgroundTask_GET backgroundTask = new BackgroundTask_GET(tvresult, strname, strscore, this);
    backgroundTask.execute();
    break;
}
    }
}
