package edu.gatech.seclass.tourneymanager;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import edu.gatech.seclass.tourneymanager.dao.DatabaseHelper;

import java.util.ArrayList;
import java.util.List;

import edu.gatech.seclass.tourneymanager.dao.constants.Deck;
import edu.gatech.seclass.tourneymanager.dao.constants.MatchStatus;
import edu.gatech.seclass.tourneymanager.models.Match;
import edu.gatech.seclass.tourneymanager.models.Player;


public class playerViewMatches extends AppCompatActivity {
    private ListView mgrMatchList; //list within main to display the users
    private ArrayList<Match> myMatches;
    private mgrMatchListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_view_matches);

        mgrMatchList = (ListView)findViewById(R.id.plrViewMatchList);

        myMatches = (ArrayList)DatabaseHelper.getInstance().getTournamentDao().getActiveTournament().getMatches();


        adapter = new mgrMatchListAdapter(this, myMatches,false);

        mgrMatchList.setAdapter(adapter);

    }


}
