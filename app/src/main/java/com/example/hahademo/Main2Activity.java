package com.example.hahademo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class Main2Activity extends AppCompatActivity {
    WebView wb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        wb = (WebView) findViewById(R.id.web);
        String result = getIntent().getStringExtra("html");
        wb.getSettings().setJavaScriptEnabled(true);//执行java脚本
        wb.loadDataWithBaseURL("about:blank", result, "text/html", "utf-8", null);
    }
}
