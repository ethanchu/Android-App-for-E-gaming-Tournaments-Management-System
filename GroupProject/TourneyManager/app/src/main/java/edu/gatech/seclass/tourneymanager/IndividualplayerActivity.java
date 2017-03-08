package edu.gatech.seclass.tourneymanager;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

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
 * Created by Yichen Zhu on 2017/3/3.
 */

public class IndividualplayerActivity extends AppCompatActivity {

    private TextView Name;
    private TextView Username;
    private TextView Phonenumber;
    private TextView Deck;

    private ArrayList<PlayerResult> myPlayerresultList = new ArrayList();
    private ListView PlayerresultList;
    private Integer curplayerid;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deleteplayer);

        Name = (TextView) findViewById(R.id.name);
        Username = (TextView) findViewById(R.id.username);
        Phonenumber = (TextView) findViewById(R.id.phonenumber);
        Deck = (TextView) findViewById(R.id.deck);

        Intent i = getIntent();
        Bundle b = i.getBundleExtra("playerbd1");
        curplayerid = b.getInt("curplayerid");
        Player curplayer = DatabaseHelper.getInstance().getPlayerDao().getPlayer(curplayerid);
        String name = curplayer.getName();
        String username = curplayer.getUsername();
        String phone = curplayer.getPhoneNumber();
        String deck = curplayer.getDeck().toString();

        Name.setText(name);
        Username.setText(username);
        Phonenumber.setText(phone);
        Deck.setText(deck);

        //test purpose
        //DatabaseHelper.getInstance().getPlayerResultDao().createPlayerResult(1,curplayerid,1.0);
        //endtest

        myPlayerresultList = (ArrayList)DatabaseHelper.getInstance().getPlayerResultDao().getPlayerResults(curplayerid);
        PlayerresultList = (ListView)findViewById(R.id.datelistview);
        IndividualplayerAdapter a = new IndividualplayerAdapter(this,myPlayerresultList);
        PlayerresultList.setAdapter(a);

    }

    public void deleteplayer(View view){
        //TODO DB Implement
        DatabaseHelper.getInstance().getPlayerDao().deletePlayer(curplayerid);
        //end TODO
        Toast.makeText(IndividualplayerActivity.this, "The current player was deleted!", Toast.LENGTH_SHORT).show();
        finish(); //<<AGIFFT3, you want to "finish" this activity onot start a new activity otherwise hitting back will go back and forth
        //startActivity(new Intent(IndividualplayerActivity.this, UserlistActivity.class));

    }
}
