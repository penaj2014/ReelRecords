/*  Reel Records
 *   Author: Jorge Pena
 */
package com.example.reelrecordsv1;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

/*
ResultsActivity class provides an inflater for the ArrayList containing cached data from LocalDB
 */
public class ResultsActivity extends AppCompatActivity {
    //variables storing LocalDB cache to be injected into the layout
    private ArrayList<String> data = new ArrayList<String>();
    private ArrayList<String> imageData = new ArrayList<String>();
    private int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.results);
        ListView lv = (ListView) findViewById(R.id.listview);
        generateListContent();
        lv.setAdapter(new MyListAdapter(this, R.layout.list_item, data));
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(ResultsActivity.this, "List item was clicked at " + position, Toast.LENGTH_SHORT).show();
            }
        });
    }

    /*
    generateListContent()
    Description: Adds cached data to be used in this instance.
     */
    private void generateListContent() {
        for (int i = 0; i < 10; i++) {
            data = LocalDB.results;
            imageData = LocalDB.images;
        }
    }

    /*
    MyListAdapter class
    Description: Serves to fill the XML file with LocalDB text and poster URL data.
     */
    private class MyListAdapter extends ArrayAdapter<String> {
        private int layout;
        private List<String> mObjects;
        private List<String> mImages;

        /*
       MyListAdapter()
       Description: Default constructor serves to fill the XML file with LocalDB text and poster URL data.
        */
        private MyListAdapter(Context context, int resource, List<String> objects) {
            super(context, resource, objects);
            mObjects = objects;
            layout = resource;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            ViewHolder mainViewHolder = null;
            if (convertView == null) {
                LayoutInflater inflater = LayoutInflater.from(getContext());
                convertView = inflater.inflate(layout, parent, false);
                ViewHolder viewHolder = new ViewHolder();
                viewHolder.thumbnail = (WebView) convertView.findViewById(R.id.list_item_thumbnail);
                viewHolder.title = (TextView) convertView.findViewById(R.id.list_item_text);
                viewHolder.button = (Button) convertView.findViewById(R.id.list_item_btn);
                convertView.setTag(viewHolder);
            }
            mainViewHolder = (ViewHolder) convertView.getTag();
            mainViewHolder.button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(getContext(), "Saved to Favorites", Toast.LENGTH_SHORT).show();
                    LocalDB.savedArray(getItem(position), imageData.get(position));
                }
            });
            mainViewHolder.title.setText(getItem(position));
                mainViewHolder.thumbnail.loadUrl(imageData.get(position));
            return convertView;
        }
    }

    /*
    ViewHolder class acts as a container to store the view referenced in the XML file.
     */
    public class ViewHolder {

        WebView thumbnail;
        TextView title;
        Button button;
    }
}