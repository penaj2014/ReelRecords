package com.example.reelrecordsv1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    //view declarations
    Button signIn, signUp;
    TextView username, password;
    public static final String EXTRA_MESSAGE = "null";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //view/button definitions
        signIn = (Button)findViewById(R.id.signIn);
        signUp = (Button)findViewById(R.id.signUp);
        username = (TextView)findViewById(R.id.username);
        password = (TextView)findViewById(R.id.password);
        LocalDB.createAccount("admin", "admin");
    }

    public void signIn(View view){
        Boolean found = false;
        for(int i = 0; i < 100; i++) {
            if ((username.getText().toString().equals(LocalDB.usernameArray[i])) && (password.getText().toString().equals(LocalDB.passwordArray[i]))) {
                //allow entry into account
                Intent intent = new Intent(this, QueryActivity.class);
                String message = username.getText().toString();
                intent.putExtra(EXTRA_MESSAGE, message);
                startActivity(intent);
                found = true;
            }
        }
        if(found == false) {
                //alert no such account exists
                Context context = getApplicationContext();
                CharSequence text = "Account not found!";
                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
            }
    }
    public void signUp(View view){
                //alert no such account exists
                Intent intent = new Intent(this, SignUpActivity.class);
                startActivity(intent);
    }
}
