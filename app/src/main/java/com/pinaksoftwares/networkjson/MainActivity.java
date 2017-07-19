package com.pinaksoftwares.networkjson;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.pinaksoftwares.networkjson.retrofit.RetrofitMain;
import com.pinaksoftwares.networkjson.volley.VolleyMain;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void volley(View view) {
        Intent intent = new Intent(this,VolleyMain.class);
        startActivity(intent);

    }

    public void retrofit(View view) {
        Intent intent = new Intent(this, RetrofitMain.class);
        startActivity(intent);
    }
}
