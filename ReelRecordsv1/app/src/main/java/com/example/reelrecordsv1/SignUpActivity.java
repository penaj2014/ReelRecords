package com.example.reelrecordsv1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class SignUpActivity extends AppCompatActivity {
    TextView username, password, confirmPassword;
    public static final String EXTRA_MESSAGE = "null";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up);
        username = (TextView)findViewById(R.id.username);
        password = (TextView)findViewById(R.id.password);
        confirmPassword = (TextView)findViewById(R.id.confirmPassword);
    }

    public void signUpPage(View view){
        if(password.getText().toString() == confirmPassword.getText().toString()) {
            //add log in info to LocalDB
            LocalDB.createAccount(username.getText().toString(), password.getText().toString());
            //allow entry into account
            Intent intent = new Intent(this, QueryActivity.class);
            String message = username.getText().toString();
            intent.putExtra(EXTRA_MESSAGE, message);
            startActivity(intent);
        }
    }
}
