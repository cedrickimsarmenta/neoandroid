package com.neoandroid.app;

import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.internal.bind.DateTypeAdapter;
import org.apache.http.HttpEntity;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.converter.GsonConverter;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class MainActivity extends ActionBarActivity {

    private final String REST_URL_END_POINT = "http://192.168.0.104:8080"; // change this for your local ip

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new LongRunningGetIO().execute();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    //as suggested for calling api's from http://www.techrepublic.com/blog/software-engineer/calling-restful-services-from-your-android-app/
    private class LongRunningGetIO extends AsyncTask<Void, Void, String> {

        protected String getASCIIContentFromEntity(HttpEntity entity) throws IllegalStateException, IOException {
            InputStream in = entity.getContent();
            StringBuffer out = new StringBuffer();
            int n = 1;
            while (n>0) {
                byte[] b = new byte[4096];
                n =  in.read(b);
                if (n>0) out.append(new String(b, 0, n));
            }
            return out.toString();
        }

        @Override
        protected String doInBackground(Void... params) {
            Gson gson = new GsonBuilder()
                    .setFieldNamingPolicy(FieldNamingPolicy.IDENTITY)
                    .registerTypeAdapter(Date.class, new DateTypeAdapter())
                    .create();


            RestAdapter restAdapter = new RestAdapter.Builder()
            .setEndpoint(REST_URL_END_POINT)
                    .setConverter(new GsonConverter(gson))
                    .build();
            List<Criminal> criminals = new ArrayList<Criminal>();

            try {
                CriminalService criminalService = restAdapter.create(CriminalService.class);
                criminals = criminalService.listCriminals();

            } catch (RetrofitError e) {
                Log.d("Got error type: {}", e.getKind().toString());
            }
            EditText editText = (EditText)findViewById(R.id.editText);
            editText.setText(criminals.get(0).alias);
            return criminals.get(0).alias;

        }

        protected void onPostExecute(String results) {
            if (results!=null) {
                EditText editText = (EditText)findViewById(R.id.editText);
                editText.setText(results);
            }
        }
    }
}
