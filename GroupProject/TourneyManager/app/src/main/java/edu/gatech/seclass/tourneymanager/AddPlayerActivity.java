package edu.gatech.seclass.tourneymanager;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import edu.gatech.seclass.tourneymanager.dao.DatabaseHelper;
import edu.gatech.seclass.tourneymanager.models.Match;
import edu.gatech.seclass.tourneymanager.models.Tournament;
import edu.gatech.seclass.tourneymanager.models.Player;
import edu.gatech.seclass.tourneymanager.models.PlayerResult;
import edu.gatech.seclass.tourneymanager.models.TournamentResult;
import edu.gatech.seclass.tourneymanager.dao.constants.MatchStatus;
import edu.gatech.seclass.tourneymanager.dao.constants.Deck;
import edu.gatech.seclass.tourneymanager.dao.constants.TournamentStatus;


/**
 * Created by Yichen Zhu on 2017/3/2.
 */

public class AddPlayerActivity extends AppCompatActivity {

    private EditText Name;
    private EditText Username;
    private EditText Phone;
    private Spinner Deck;

     protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_createplayer);

         Name = (EditText) findViewById(R.id.name);
         Username = (EditText) findViewById(R.id.username);
         Phone = (EditText) findViewById(R.id.phonenumber);
         Deck = (Spinner) findViewById(R.id.deck);
    }


    public void createplayer(View view){
        //TODO DB Implement
        //int playerID = DatabaseHelper.getInstance().getPlayerDao().createPlayer(Name.getText().toString(),Username.getText().toString(),Phone.getText().toString(), edu.gatech.seclass.tourneymanager.dao.constants.Deck.ENGINEER );
        //end TODO
        Toast.makeText(AddPlayerActivity.this, "Player has been added", Toast.LENGTH_LONG).show();
        finish();

    }
}
