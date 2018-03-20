package com.example.stefanfolkesson.myfirstproject;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class SplashScreen extends AppCompatActivity {
    public String TAG ="MinLog";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppDatabase db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "database-name").allowMainThreadQueries().build();
        User namn = new User();
//        namn.setFirstName("Stefan");
  //      namn.setLastName("Folkesson");
    //    namn.setUid(1);
      //  db.userDao().insertAll(namn);
        Log.d("datainfo", "onCreate: "+db.userDao().getAll().get(0).getFirstName());
        setContentView(R.layout.activity_splash_screen);
        new LongOperation().execute("");

    }

    public void generallyPressed(View view){
        Context context = getApplicationContext();
        CharSequence text = "Hello toast!";
        int duration = Toast.LENGTH_SHORT;
        Toast.makeText(context, text, duration).show();
/*        finish();
        overridePendingTransition(0, 0);
        startActivity(getIntent());
        overridePendingTransition(0, 0);*/
        recreate();
    }

    public void OMGIpushedTheButton(View view){
        Log.d(TAG, "OMGIpushedTheButton: ");
        Intent intent = new Intent(this, Game.class);
        startActivity(intent);

    }


    public void startMeny(View view){
        Log.d(TAG, "OMGIpushedTheButton: ");
        Intent intent = new Intent(this, Meny.class);
        startActivity(intent);

    }

    private class LongOperation extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... params) {
            URL url;
            HttpURLConnection urlConnection = null;
            String data = null;
            try {
                url = new URL("http://192.168.56.1:8080/test.php");

                urlConnection = (HttpURLConnection) url
                        .openConnection();
                InputStream in = urlConnection.getInputStream();

                InputStreamReader isw = new InputStreamReader(in);
                data = readStream(isw);

            } catch (Exception e) {
                Log.d("datainfo", "doInBackground:  Error ");
                e.printStackTrace();
            } finally {
                if (urlConnection != null) {
                  //  urlConnection.disconnect();
                }
            }

            Log.d("datainfo", "doInBackground data: "+data);
            return data;
        }
        private String readStream(InputStreamReader is) throws IOException {
            StringBuilder sb = new StringBuilder();
            BufferedReader r = new BufferedReader(is,1000);
            String line = r.readLine();
                Log.d("datainfo", "readStream: "+line);
            is.close();
            return line;
        }

        @Override
        protected void onPostExecute(String result) {
            TextView txt = (TextView) findViewById(R.id.textView);
            txt.setText("res"+result);
        }

        @Override
        protected void onPreExecute() {
        }

        @Override
        protected void onProgressUpdate(Void... values) {
        }
    }
}
