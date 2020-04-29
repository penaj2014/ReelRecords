package com.example.reelrecordsv1;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.RequestQueue;

public class QueryActivity extends AppCompatActivity {
    RequestQueue requestQueue;
    TextView query;
    public static final String EXTRA_MESSAGE = "null";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.query);
        query = (TextView)findViewById(R.id.query);
        //getting the intent that started this activity and extracting the string
        Intent intent = getIntent();
        String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
        Context context = getApplicationContext();
        CharSequence text = "Welcome " + message + "!";
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }
    public void searchTitle(View v)
    {
        //allow entry into account
        Intent intent = new Intent(this, ResultsActivity.class);
        String message = query.getText().toString();
        message = "https://api.themoviedb.org/3/search/movie?api_key=e21ab52dd4c9714064881126c6db3943&language=en-US" +
                "&query=" + message + "&page=1";
        Log.d("test", message);
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    }
    public void searchAuthor(View v)
    {
        //allow entry into account
        Intent intent = new Intent(this, ResultsActivity.class);
        String message = query.getText().toString();
        message = "https://api.themoviedb.org/3/search/movie?api_key=e21ab52dd4c9714064881126c6db3943&language=en-US" +
                "&query=" + message + "&page=1";
        Log.d("test", message);
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    }
    public void searchGenre(View v)
    {
        //allow entry into account
        Intent intent = new Intent(this, QueryActivity.class);
        String message = query.getText().toString();
        message = "https://api.themoviedb.org/3/search/movie?api_key=e21ab52dd4c9714064881126c6db3943&language=en-US" +
                "&query=" + message + "&page=1";
        Log.d("test", message);
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    }
}

//    final TextView textView = findViewById(R.id.username);
//        requestQueue = MySingleton.getInstance(this.getApplicationContext()).getRequestQueue();
//                DiskBasedCache cache = new DiskBasedCache(getCacheDir(), 1024 * 1024); // 1MB cap
//                BasicNetwork network = new BasicNetwork(new HurlStack());
//                requestQueue = new RequestQueue(cache, network);
//                // Start the queue
//                requestQueue.start();
//                String url ="https://api.openweathermap.org/data/2.5/weather?zip=33414&APPID=272fb986fe46ba90cb194bc1aee0beed";
//                // Formulate the request and handle the response.
//                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
//                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
//
//@Override
//public void onResponse(JSONObject response) {
//        //textView.setText("Response: " + response.toString());
//        try {
//        JSONObject data = response.getJSONObject("coord");
//        String lon = data.get("lon").toString();
//        String lat = data.get("lat").toString();
//        String cityName = response.getString("name");
//        //textView.setText(cityName + " Weather:\n");
//        //textView.setText("The Longitude is:\n" + lon);
//        textView.setText("The Latitude is:\n" + lat);
//        String finalResult = cityName + " Weather:\n" + "The Longitude is: " + lon + "\nThe Latitude is: " + lat;
//        //textView.setText(finalResult);
//        } catch (JSONException e) {
//        textView.setText("Exception");
//        e.printStackTrace();
//        }
//        }
//        }, new Response.ErrorListener() {
//
//@Override
//public void onErrorResponse(VolleyError error) {
//        // TODO: Handle error
//        }
//        });
//        MySingleton.getInstance(this).addToRequestQueue(jsonObjectRequest);
