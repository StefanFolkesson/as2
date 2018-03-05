package com.example.stefanfolkesson.myfirstproject;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

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

    }

    public void generallyPressed(View view){
        Context context = getApplicationContext();
        CharSequence text = "Hello toast!";
        int duration = Toast.LENGTH_SHORT;
        Toast.makeText(context, text, duration).show();
    }

    public void OMGIpushedTheButton(View view){
        Log.d(TAG, "OMGIpushedTheButton: ");
        Intent intent = new Intent(this, Game.class);
        startActivity(intent);

    }
}
