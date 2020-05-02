/*  Reel Records
 *   Author: Jorge Pena
 */
package com.example.reelrecordsv1;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

/*
SignUpActivity() contains the methods to create a new account
and it validates correct information has been entered.
 */
public class SignUpActivity extends AppCompatActivity {
    TextView username, password, confirmPassword;
    public static final String EXTRA_MESSAGE = "null";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up);
        //Links XML buttons to .java
        username = (TextView)findViewById(R.id.username);
        password = (TextView)findViewById(R.id.password);
        confirmPassword = (TextView)findViewById(R.id.confirmPassword);
    }

    /*
    signUp()
    Description: Verifies the user's entered password is correct and inserts new user into LocalDB.
    @param view                         receives button's onClick response
     */
    public void signUp(View view){
        String c, p;
        p = password.getText().toString();
        c = confirmPassword.getText().toString();
        if(c.equals(p)) {
            LocalDB.createAccount(username.getText().toString(), password.getText().toString());
            //alert passwords are correct
            Context context = getApplicationContext();
            CharSequence text = "Success!";
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
            //allow entry into account
            //returns the user to the main sign in page
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
