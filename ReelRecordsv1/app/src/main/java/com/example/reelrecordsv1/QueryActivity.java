/*  Reel Records
 *   Author: Jorge Pena
 */
package com.example.reelrecordsv1;
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
import java.util.ArrayList;

/*
QueryActivity class links the XML buttons to methods which utilize HTTPRequests that pull JSON Payloads
for films which were queried.
 */
public class QueryActivity extends AppCompatActivity {
    TextView query;
    public static Boolean done = false;
    public static final String EXTRA_MESSAGE = "null";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.query);
        query = (TextView)findViewById(R.id.query);
        //getting the intent that started this activity and extracting the string
        Intent intent = getIntent();
        String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
        LocalDB.currentUser(message);
    }

    /*
    searchTitle()
    Description: Concatenates a the main URL link for searching for films by movie title with the entered
    movie title and then creates an intent.
    @param View v               onClick button linked to call method
     */
    public void searchTitle(View v)
    {
        //allow entry into account
        Intent intent = new Intent(this, ResultsActivity.class);
        String message = query.getText().toString();
        message = emptySpace(message);
        message = "https://api.themoviedb.org/3/search/movie?api_key=e21ab52dd4c9714064881126c6db3943&language=en-US" +
                "&query=" + message + "&page=1";
        Log.d("test", message.substring(0,41));
        intent.putExtra(EXTRA_MESSAGE, message);
        HTTPRequest(message);
        for(int i = 0 ; i < 5000; i++){}
        startActivity(intent);
    }

    /*
    searchAuthor()
    Description: Concatenates a the main URL link for searching for films by person with the entered
   name and then creates an intent.
    @param View v               onClick button linked to call method
     */
    public void searchAuthor(View v) {
        //allow entry into account
        Intent intent = new Intent(this, ResultsActivity.class);
        String message = query.getText().toString();
        message = emptySpace(message);
        message = "https://api.themoviedb.org/3/search/person?api_key=e21ab52dd4c9714064881126c6db3943&language=en-US" +
                "&query=" + message + "&page=1&include_adult=false";
        Log.d("test", message);
        intent.putExtra(EXTRA_MESSAGE, message);
        HTTPRequest(message);
        for(int i = 0 ; i < 5000; i++){}
        startActivity(intent);
    }

    /*
   searchGenre()
   Description: Concatenates a the main URL link for searching for films by movie title with the entered
   movie title and then creates an intent.
   @param View v               onClick button linked to call method
    */
    public void searchGenre(View v)
    {
        //allow entry into account
        Intent intent = new Intent(this, ResultsActivity.class);
        String message = query.getText().toString();
        message = emptySpace(message);
        message = "https://api.themoviedb.org/3/search/movie?api_key=e21ab52dd4c9714064881126c6db3943&language=en-US" +
                "&query=" + message + "&page=1";
        Log.d("test", message);
        intent.putExtra(EXTRA_MESSAGE, message);
        HTTPRequest(message);
        for(int i = 0 ; i < 5000; i++){}
        startActivity(intent);
    }

    /*
   Saved()
   Description: Chen creates an intent to see saved queries.
   @param View v               onClick button linked to call method
    */
    public void Saved(View v)
    {
        //allow entry into account
        Intent intent = new Intent(this, SavedActivity.class);
        startActivity(intent);
    }

    /*
   HTTPRequest()
   Description: Handles the async payload request and reception which is then stored in LocalDB.java
   @param String url               concatenated url to perform REST API call.
    */
    public void HTTPRequest(final String url)
    {
        //Method declaration
        RequestQueue requestQueue = MySingleton.getInstance(this.getApplicationContext()).getRequestQueue();
        DiskBasedCache cache = new DiskBasedCache(getCacheDir(), 1024 * 1024); // 1MB cap
        BasicNetwork network = new BasicNetwork(new HurlStack());
        requestQueue = new RequestQueue(cache, network);
        Log.d("URL", url);
        requestQueue.start();
        // Formulate the request and handle the response.
        final JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            Log.d("test", url.substring(0,41));
                            ArrayList<String> s = new ArrayList<>();
                            ArrayList<String> t = new ArrayList<>();
                            JSONArray data = response.getJSONArray("results");
                            int length = response.length();
                            for(int i = 0; i < length; i++)
                            {
                                JSONObject tempObject = data.getJSONObject(i);
                                //confirms the type of payload to receive by using an if else statement
                                if(url.substring(0,41).equals("https://api.themoviedb.org/3/search/movie"))
                                {
                                    String payload = tempObject.getString("title") +"\n" + tempObject.getString("overview");
                                    s.add(payload);
                                    payload = "https://image.tmdb.org/t/p/w185/" + tempObject.getString("poster_path");
                                    t.add(payload);
                                }
                                else
                                {
                                    String payload = tempObject.getString("name") +"\nKnown for: " + tempObject.getString("known_for_department");
                                    s.add(payload);
                                    payload = "https://image.tmdb.org/t/p/w185/" + tempObject.getString("profile_path");
                                    t.add(payload);
                                }

                            }
                            LocalDB.resultsArray(s, t);
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

    /*
   searchTitle
   Description: Removes the space in between entered queries.
   @param String s             the string intended to be appended to the main url
    */
    public String emptySpace(String s)
    {
        if(s.contains(" "))
        {
            int i = s.indexOf(" ");
             s = s.substring(0,i)+"%20"+s.substring(i+1);
        }
        return s;
    }
}



