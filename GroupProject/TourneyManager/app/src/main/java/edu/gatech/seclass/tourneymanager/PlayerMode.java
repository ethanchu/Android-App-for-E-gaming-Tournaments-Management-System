package edu.gatech.seclass.tourneymanager;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import edu.gatech.seclass.tourneymanager.dao.DatabaseHelper;

/**
 * Created by Summer on 3/4/17.
 */

public class PlayerMode extends AppCompatActivity {

    private Button mybutton;
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            mybutton = (Button)findViewById(R.id.plr_sel_button);
            setContentView(R.layout.activity_player_mode);
            if(DatabaseHelper.getInstance().getTournamentDao().getActiveTournament() == null) {


            }else if (DatabaseHelper.getInstance().getTournamentDao().getActiveTournament() != null) {



            }
        }
    public void plrShowNextActivity(View v){
        if(DatabaseHelper.getInstance().getTournamentDao().getActiveTournament() == null) {

            startActivity(new Intent(PlayerMode.this, PlayerModeUserlistActivity.class));
        }else if (DatabaseHelper.getInstance().getTournamentDao().getActiveTournament() != null) {


            //startActivity(new Intent(PlayerMode.this, InactivemanagermodeActivity.class));
        }
    }
}

