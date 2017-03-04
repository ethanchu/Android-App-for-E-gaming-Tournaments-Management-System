package edu.gatech.seclass.tourneymanager;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

/**
 * Created by Summer on 3/4/17.
 */

public class PlayerMode extends AppCompatActivity {

    public void onClick(View view) {
        if(DatabaseHelper.getInstance().getTournamentDao().getActiveTournament() != null) {
            Intent intent = new Intent();
            intent.setClass(MainActivity.this, PlaerModeMatchlist.class);
            MainActivity.this.startActivity(intent);
        }
        else if(onTournament == flase){
            Intent intent = new Intent();
            intent.setClass(MainActivity.this, PlayerModeUserlistActivity.class);
            MainActivity.this.startActivity(intent);
        }
    }

}
