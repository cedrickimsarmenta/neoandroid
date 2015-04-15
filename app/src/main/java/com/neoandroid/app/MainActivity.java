package com.neoandroid.app;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import retrofit.RestAdapter;
import retrofit.RetrofitError;

import java.io.InputStream;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText editText = (EditText)findViewById(R.id.editText);


//        RestAdapter restAdapter = new RestAdapter.Builder()
//                .setEndpoint("http://10.10.171.157:8080/securityx/criminalRest/index")
//                .build();

        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint("https://api.github.com")
                .build();
// create HttpClient
        InputStream inputStream = null;
        HttpClient httpclient = new DefaultHttpClient();

        // make GET request to the given URL

        try {

            HttpResponse httpResponse = httpclient.execute(new HttpGet("http://10.10.171.157:8080/securityx/criminalRest/index"));

            // receive response as inputStream
            inputStream = httpResponse.getEntity().getContent();

        } catch(Exception e) {

        }
//        try {
//            GitHubService service = restAdapter.create(GitHubService.class);
////            CriminalService service = restAdapter.create(CriminalService.class);
//
//            service.userList().toString();
//        } catch(RetrofitError e) {
//            Log.d("Got error type: {}", e.getKind().toString());
//        }
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
}
