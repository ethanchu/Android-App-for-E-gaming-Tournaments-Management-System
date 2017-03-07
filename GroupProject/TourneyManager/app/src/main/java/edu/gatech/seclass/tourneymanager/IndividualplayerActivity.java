package edu.gatech.seclass.tourneymanager;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.TextView;

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

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deleteplayer);

        Name = (TextView) findViewById(R.id.name);
        Username = (TextView) findViewById(R.id.username);
        Phonenumber = (TextView) findViewById(R.id.phonenumber);
        Deck = (TextView) findViewById(R.id.deck);

        Intent i = getIntent();
        Bundle b = i.getBundleExtra("playerbd1");
        Integer curplayerid = b.getInt("curplayerid");
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
        DatabaseHelper.getInstance().getPlayerResultDao().createPlayerResult(1,curplayerid,1.0);
        DatabaseHelper.getInstance().getPlayerResultDao().createPlayerResult(2,curplayerid,2.0);
        DatabaseHelper.getInstance().getPlayerResultDao().createPlayerResult(3,curplayerid,3.0);
        DatabaseHelper.getInstance().getPlayerResultDao().createPlayerResult(4,curplayerid,4.0);
        DatabaseHelper.getInstance().getPlayerResultDao().createPlayerResult(5,curplayerid,5.0);
        DatabaseHelper.getInstance().getPlayerResultDao().createPlayerResult(6,curplayerid,6.0);
        DatabaseHelper.getInstance().getPlayerResultDao().createPlayerResult(7,curplayerid,7.0);
        DatabaseHelper.getInstance().getPlayerResultDao().createPlayerResult(8,curplayerid,8.0);
        DatabaseHelper.getInstance().getPlayerResultDao().createPlayerResult(9,curplayerid,9.0);
        DatabaseHelper.getInstance().getPlayerResultDao().createPlayerResult(10,curplayerid,10.0);
        DatabaseHelper.getInstance().getPlayerResultDao().createPlayerResult(20,curplayerid,20.0);
        DatabaseHelper.getInstance().getPlayerResultDao().createPlayerResult(10,curplayerid,1.0);
        //endtest

        myPlayerresultList = (ArrayList)DatabaseHelper.getInstance().getPlayerResultDao().getPlayerResults(curplayerid);
        PlayerresultList = (ListView)findViewById(R.id.datelistview);
        IndividualplayerAdapter a = new IndividualplayerAdapter(this,myPlayerresultList);
        PlayerresultList.setAdapter(a);

    }
}
