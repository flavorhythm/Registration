package com.example.zyuki.echoregistration;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import util.Constant;

public class ContentActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content);

        String cardTitle = getIntent().getExtras().getString(Constant.BUNDLE_TITLE);

        TextView text = (TextView)findViewById(R.id.test);
        if(text != null) {text.setText(cardTitle);}
    }
}
