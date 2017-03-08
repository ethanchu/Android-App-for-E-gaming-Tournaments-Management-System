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


public class mgrControlMatches extends AppCompatActivity {
    private ListView mgrMatchList; //list within main to display the users
    private ArrayList<Match> myMatches;
    private mgrMatchListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mgr_control_matches);

        mgrMatchList = (ListView)findViewById(R.id.mgrControlMatchList);

        myMatches = (ArrayList)DatabaseHelper.getInstance().getTournamentDao().getActiveTournament().getMatches();


        adapter = new mgrMatchListAdapter(this, myMatches,true);

        mgrMatchList.setAdapter(adapter);

        //set on click listener
        mgrMatchList.setOnItemClickListener((AdapterView.OnItemClickListener)adapter);
    }

    public void StartMatchClick(View v){
        int position = adapter.getSelectedrow();
        if(position>=0){
            Match myMatch = myMatches.get(position);
            if(myMatch.getMatchStatus() == MatchStatus.NOTSTARTED){
                //start match
                DatabaseHelper.getInstance().getMatchDao().updateMatchStatus(myMatch.getMatchId(),MatchStatus.STARTED,null);
                myMatches = (ArrayList)DatabaseHelper.getInstance().getTournamentDao().getActiveTournament().getMatches();
                adapter.refresh(myMatches);
                //tells data to refresh
            }else{
                Toast.makeText(getApplicationContext(), "Match is not not in Notstarted state",
                        Toast.LENGTH_SHORT).show();
            }
            //expensive, re-loads all matches from dB, but ensures its up to date

        }
    }

    public void EndMatchClick(View v){
        int position = adapter.getSelectedrow();
        if(position>=0){
            Match myMatch = myMatches.get(position);
            if(myMatch.getMatchStatus() == MatchStatus.STARTED){
                //end match
                Bundle matchParams = new Bundle();

                matchParams.putInt("matchID",myMatch.getMatchId());
                //pass match ID to below.
                startActivityForResult(new Intent(mgrControlMatches.this,mgrSelectMatchWinner.class).putExtras(matchParams),0);

            }else{
                Toast.makeText(getApplicationContext(), "Match is not in Started state",
                        Toast.LENGTH_SHORT).show();
            }
        }
    }

    //this is used to determine if the reviewActivity started the Tournament or not
    //if it did start the Tournament this will return as well.
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //update matches from database, something may have changed
        myMatches = (ArrayList)DatabaseHelper.getInstance().getTournamentDao().getActiveTournament().getMatches();
        //notify adapter that status has changed
        adapter.refresh(myMatches);

    }


}
