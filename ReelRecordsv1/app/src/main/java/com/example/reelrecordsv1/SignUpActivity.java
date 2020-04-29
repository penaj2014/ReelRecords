package com.example.reelrecordsv1;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

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

    public void signUp(View view){
        String c, p;
        p = password.getText().toString();
        c = confirmPassword.getText().toString();
        if(c.equals(p)) {
            //add log in info to LocalDB
            LocalDB.createAccount(username.getText().toString(), password.getText().toString());
            //alert passwords are correct
            Context context = getApplicationContext();
            CharSequence text = "Success!";
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
            //allow entry into account
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        } else {
            //alert passwords are not correct
            Context context = getApplicationContext();
            CharSequence text = "Passwords do not match!";
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }
    }
}
