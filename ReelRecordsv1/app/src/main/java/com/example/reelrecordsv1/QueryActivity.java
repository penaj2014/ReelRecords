package com.example.reelrecordsv1;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class QueryActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.query);
        //getting the intent that started this activity and extracting the string
        Intent intent = getIntent();
        String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
        //capture the layout's textview and ser the string as its text
        //TextView textView = findViewById(R.id.textView);
        //textView.setText(message);
    }
}
