package com.example.hahademo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.github.mr5.icarus.Callback;
import com.github.mr5.icarus.Icarus;
import com.github.mr5.icarus.TextViewToolbar;
import com.github.mr5.icarus.button.TextViewButton;
import com.github.mr5.icarus.entity.Options;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class MainActivity extends AppCompatActivity {

    WebView webView;
    Button button;
    Icarus icarus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        webView = (WebView) findViewById(R.id.wb);
        button = (Button) findViewById(R.id.btn);
        final TextViewToolbar toolbar = new TextViewToolbar();

        Options options = new Options();
        options.setPlaceholder("请输入歌词");
        icarus = new Icarus(toolbar, options, webView);

        TextView tv = (TextView) findViewById(R.id.tv);
        TextViewButton tb = new TextViewButton(tv, icarus);
        tb.setName(com.github.mr5.icarus.button.Button.NAME_ALIGN_CENTER);
        toolbar.addButton(tb);

        TextView tv1 = (TextView) findViewById(R.id.tv2);
        TextViewButton tb1 = new TextViewButton(tv1, icarus);
        tb1.setName(com.github.mr5.icarus.button.Button.NAME_BOLD);
        toolbar.addButton(tb1);

        icarus.loadCSS("file:///android_asset/editor.css");
        icarus.loadJs("file:///android_asset/test.js");
        icarus.render();
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                icarus.getContent(new Callback() {
                    public void run(String params) {
                        Intent intent = new Intent(MainActivity.this,Main2Activity.class);
                        Gson gson = new Gson();
                        LrcHtmlBean lrcHtml = gson.fromJson(params, new TypeToken<LrcHtmlBean>() {
                        }.getType());
                        intent.putExtra("html",lrcHtml.getContent());
                        startActivity(intent);
                        Log.i("MainActivity",params);
                        Toast.makeText(MainActivity.this, "获取的" + params, Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}
