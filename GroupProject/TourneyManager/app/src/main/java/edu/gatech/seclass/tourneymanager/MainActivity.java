package edu.gatech.seclass.tourneymanager;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import edu.gatech.seclass.tourneymanager.dao.DatabaseHelper;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DatabaseHelper.setContext(getApplicationContext());
        DatabaseHelper.init();
    }

    @Override
    protected void onStart() {
            super.onStart();}

    public void playermode(View view) {
        //TODO DB Implement

        //end TODO
        // TODO Playermode implement

    }


    public void managermode(View view){
        //TODO DB Implement

        //end TODO

        Toast.makeText(MainActivity.this, "Switch to Manager Mode!", Toast.LENGTH_LONG).show();
        if(DatabaseHelper.getInstance().getTournamentDao().getActiveTournament() == null) {

            startActivity(new Intent(MainActivity.this, InactivemanagermodeActivity.class));
        }else{
            startActivity(new Intent(MainActivity.this, ActiveManagerMode.class));
        }

    }
}

