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
    private Spinner Deck_spinner;
    private Deck deck_db;

     protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_createplayer);

         Name = (EditText) findViewById(R.id.name);
         Username = (EditText) findViewById(R.id.username);
         Phone = (EditText) findViewById(R.id.phonenumber);
         Deck_spinner = (Spinner) findViewById(R.id.deck);
    }


    public void createplayer(View view){
        //TODO DB Implement
        String deckvalue = Deck_spinner.getSelectedItem().toString();
        switch (deckvalue) {
            case "ENGINEER": deck_db = Deck.ENGINEER; break;
            case "BUZZ": deck_db = Deck.BUZZ; break;
            case "SIDEWAYS": deck_db = Deck.SIDEWAYS; break;
            case "WRECK": deck_db = Deck.WRECK; break;
            case "T": deck_db = Deck.T; break;
            case "RAT": deck_db = Deck.RAT; break;
        }

        if (Phone.getText().toString().matches("^[0-9]{10}$")) { // Check the phonenumber format
            Integer playerID = DatabaseHelper.getInstance().getPlayerDao().createPlayer(Name.getText().toString(), Username.getText().toString(), Phone.getText().toString(), deck_db);
            if (playerID == -1) {
                Toast.makeText(AddPlayerActivity.this, "Player has exist, was not created!", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(AddPlayerActivity.this, "Player was created successfully!", Toast.LENGTH_SHORT).show();
                finish();
            }
        }
        else {
            Toast.makeText(AddPlayerActivity.this, "Phone number format is not correct(should be 10 digits)!", Toast.LENGTH_SHORT).show();
        }

    }
}
