package edu.gatech.seclass.tourneymanager;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

/**
 * Created by Owner on 2017/3/2.
 */

public class AddPlayerActivity extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_createplayer);
    }


    public void createplayer(View view){
        //TODO DB Implement
        // Add constraint to call SQLite to implement
        //end TODO
        Toast.makeText(AddPlayerActivity.this, "Player has been added", Toast.LENGTH_LONG).show();
        finish();

    }
}
