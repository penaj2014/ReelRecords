package com.example.reelrecordsv1;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.BasicNetwork;
import com.android.volley.toolbox.DiskBasedCache;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class QueryActivity extends AppCompatActivity {
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
        HTTPRequest(message);
        for(int i = 0 ; i < 900; i++){}
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

    public void HTTPRequest(String url)
    {
        RequestQueue requestQueue = MySingleton.getInstance(this.getApplicationContext()).getRequestQueue();
        DiskBasedCache cache = new DiskBasedCache(getCacheDir(), 1024 * 1024); // 1MB cap
        BasicNetwork network = new BasicNetwork(new HurlStack());
        requestQueue = new RequestQueue(cache, network);
        // Start the queue
        requestQueue.start();
        // Formulate the request and handle the response.
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            String s = null;
                            JSONArray data = response.getJSONArray("results");
                            int length = response.length();
                            for(int i = 0; i < length; i++)
                            {
                                JSONObject tempObject = data.getJSONObject(i);
                                s = tempObject.getString("original_title");
                                LocalDB.temp = s;
                            }
                            Log.d("test1", s);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO: Handle error
                    }
                });
        MySingleton.getInstance(this).addToRequestQueue(jsonObjectRequest);
    }
}


//    JSONObject jsonObj = new JSONObject(jsonStr);
//    JSONArray ja_data = jsonObj.getJSONArray("data");
//    int length = jsonObj.length();
//for(int i=0; i<length; i++) {
//        JSONObject jsonObj = ja_data.getJSONObject(i);
//        Toast.makeText(this, jsonObj.getString("Name"), Toast.LENGTH_LONG).show();
//
//        // getting inner array Ingredients
//        JSONArray ja = jsonObj.getJSONArray("Ingredients");
//        int len = ja.length();
//
//        ArrayList<String> Ingredients_names = new ArrayList<>();
//        for(int j=0; j<len; j++) {
//        JSONObject json = ja.getJSONObject(j);
//        Ingredients_names.add(json.getString("name"));
//        }
//        }





