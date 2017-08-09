package com.kidusmt.android.json_parsing;

import android.app.ProgressDialog;
import android.net.http.HttpResponseCache;
import android.os.AsyncTask;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
import android.support.v7.widget.Toolbar;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<Trainer> trainersList;

    TrainerAdapter adapter;

    SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = new Toolbar(this);
        toolbar.setTitle("Gebeya Trainers");
        toolbar.setLogo(R.drawable.gebeya_logo_white);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        searchView = (SearchView)findViewById(R.id.searchView);
        searchView.setLayoutParams(new ActionBar.LayoutParams(Gravity.RIGHT));

        trainersList = new ArrayList<Trainer>();
        new JSONAsyncTask().execute("https://jsonpractise.000webhostapp.com/gebeya-trainers.json");

        ListView listview = (ListView)findViewById(R.id.list);
        adapter = new TrainerAdapter(getApplicationContext(), R.layout.row, trainersList);

        listview.setAdapter(adapter);

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position,
                                    long id) {
                // TODO Auto-generated method stub
                Toast.makeText(getApplicationContext(), trainersList.get(position).getName(), Toast.LENGTH_LONG).show();
            }
        });
    }

    class JSONAsyncTask extends AsyncTask<String, Void, Boolean> {

        ProgressDialog dialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog = new ProgressDialog(MainActivity.this);
            dialog.setMessage("Loading, please wait");
            dialog.setTitle("Connecting server");
            dialog.show();
            dialog.setCancelable(false);
        }

        @Override
        protected Boolean doInBackground(String... urls) {
            try {

                URL urlObj = new URL(urls[0]);

                Log.e("the link",urls[0].toString());
                HttpURLConnection connection = (HttpURLConnection)urlObj.openConnection();
                connection.setRequestMethod("GET");
                connection.connect();

                // StatusLine stat = response.getStatusLine();
                int code = connection.getResponseCode();
                Log.e("status code",""+code);
                //------------------>>
                InputStream stream = connection.getInputStream();

                //Log.e("stream ==>",stream.toString());
                if (code == 200) {


                    BufferedReader br = new BufferedReader(new InputStreamReader(stream));
                    StringBuilder sb = new StringBuilder();
                    String line;
                    while ((line = br.readLine()) != null) {
                        sb.append(line+"\n");
                    }
                    br.close();
                    sb.toString();

                    JSONObject jsono = new JSONObject(sb.toString());
                    JSONArray jarray = jsono.getJSONArray("gebeya-trainers");

                    //Log.e("stream",""+jarray.length());
                    for (int i = 0; i < jarray.length()-1; i++) {
                        JSONObject object = jarray.getJSONObject(i);

                        Trainer actor = new Trainer();
                        //Log.e("professional-course",object.toString());

                        actor.setName(object.getString("name"));
                        actor.setDescription(object.getString("description"));
                        actor.setProfessional_course(object.getString("professional-course"));
                        actor.setCountry(object.getString("country"));
                        actor.setImage(object.getString("image"));

                        trainersList.add(actor);
                    }
                    return true;
                }

                //------------------>>

            } catch (Exception e) {
                e.printStackTrace();
            }
            return false;
        }

        protected void onPostExecute(Boolean result) {
            dialog.cancel();
            adapter.notifyDataSetChanged();
            if(result == false)
                Toast.makeText(getApplicationContext(), "Unable to fetch data from server", Toast.LENGTH_LONG).show();

        }
    }
}
