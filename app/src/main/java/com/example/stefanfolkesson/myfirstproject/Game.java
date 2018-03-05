package com.example.stefanfolkesson.myfirstproject;

import android.arch.persistence.room.Room;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class Game extends AppCompatActivity {
    public String TAG ="MinLog";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        AppDatabase db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "database-name").allowMainThreadQueries().build();
        Log.d("datainfo", "onCreate2: "+db.userDao().getAll().get(0).getFirstName());

    }

    public void rotate(View view){
        Log.d(TAG, "Rotate: ");
        view.setRotation((float)90+view.getRotation());

    }

}
