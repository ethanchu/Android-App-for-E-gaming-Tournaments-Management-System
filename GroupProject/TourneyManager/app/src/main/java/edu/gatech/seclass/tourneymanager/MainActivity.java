package edu.gatech.seclass.tourneymanager;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.util.List;

import edu.gatech.seclass.tourneymanager.dao.PlayerDao;
import edu.gatech.seclass.tourneymanager.dao.constants.Deck;
import edu.gatech.seclass.tourneymanager.models.Player;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }



    public void playermode(View view) {
        //TODO DB Implement

        //end TODO
        // TODO Playermode implement

    }


    public void managermode(View view){
        //TODO DB Implement

        //end TODO

        Toast.makeText(MainActivity.this, "Switch to Manager Mode!", Toast.LENGTH_LONG).show();
        startActivity(new Intent(MainActivity.this, InactivemanagermodeActivity.class));

    }
}
