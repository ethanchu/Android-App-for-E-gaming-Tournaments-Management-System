package edu.gatech.seclass.tourneymanager;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import edu.gatech.seclass.tourneymanager.dao.PlayerDao;
import edu.gatech.seclass.tourneymanager.dao.constants.MatchStatus;
import edu.gatech.seclass.tourneymanager.dao.constants.TournamentStatus;
import edu.gatech.seclass.tourneymanager.models.Tournament;
import edu.gatech.seclass.tourneymanager.models.Player;
import edu.gatech.seclass.tourneymanager.models.Match;
import edu.gatech.seclass.tourneymanager.dao.constants.Deck;
import edu.gatech.seclass.tourneymanager.dao.DatabaseHelper;
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class mgrReviewTournSetup extends AppCompatActivity {
    private TextView mgrHouseProfit;
    private TextView mgrEntreeFee;
    private TextView mgrFirstPrize;
    private TextView mgrSecondPrize;
    private TextView mgrThirdPrize;
    private ListView mgrTournPlayerList;
    private int houseCut;
    private int entryFee;
    private ArrayList<Integer> playerUserID;
    private List<Player> myPlayers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mgr_review_tourn_setup);

        mgrHouseProfit = (TextView)findViewById(R.id.mgrHouseProfit);
        mgrEntreeFee = (TextView)findViewById(R.id.mgrEntreeFee);
        mgrFirstPrize = (TextView)findViewById(R.id.mgrFirstPrize);
        mgrSecondPrize = (TextView)findViewById(R.id.mgrSecondPrize);
        mgrThirdPrize = (TextView)findViewById(R.id.mgrThirdPrize);
        mgrTournPlayerList = (ListView)findViewById(R.id.mgrTournPlayerList);
        myPlayers = getPlayersFromdB();

        houseCut = getIntent().getExtras().getInt("HouseCut");
        entryFee = getIntent().getExtras().getInt("EntranceFee");
        playerUserID = getIntent().getExtras().getIntegerArrayList("playerUserID");

        //set known fields
        mgrEntreeFee.setText(Integer.toString(entryFee));

        //set player list based of playerIDs.  This is O(N^2) implementation
        ArrayList<Player> tournamentPlayers = new ArrayList<Player>();
        for(int i = 0; i<playerUserID.size(); i++){
            for(int j = 0; j<myPlayers.size(); j++){
                if(myPlayers.get(j).getPlayerId().equals(playerUserID.get(i))){ //player names are equal
                    tournamentPlayers.add(myPlayers.get(j));
                }
            }
        }
        ArrayList<Player> myPlayerList = new ArrayList<Player>(tournamentPlayers);
        //ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,R.layout.user_view_row, R.id.userName, tournamentPlayerNames);

        PlayerNameListAdapter a = new PlayerNameListAdapter(this,myPlayerList);

        //mgrTournPlayerList.setAdapter(adapter);
        mgrTournPlayerList.setAdapter(a);
        //calculate prizes and set values
        runCalculate();
    }
    private void runCalculate(){

        mgrFirstPrize.setText("");
        mgrSecondPrize.setText("");
        mgrThirdPrize.setText("");
        mgrHouseProfit.setText("");

        int entrantsNumberValue = playerUserID.size();
        int entranceFeeValue = entryFee;
        int housePercentageValue = houseCut;

        //calculate purse
        double purse = (double)(entrantsNumberValue * entranceFeeValue);
        //set house cut
        mgrHouseProfit.setText(Integer.toString((int)(purse / 100.0 * housePercentageValue + 0.5)));
        //calculate remaining value in purse
        purse = purse * (100 - housePercentageValue) / 100.0;

        //set prize values
        mgrFirstPrize.setText(Integer.toString((int)(purse * 0.5 + 0.5)));
        mgrSecondPrize.setText(Integer.toString((int)(purse * 0.3 + 0.5)));
        mgrThirdPrize.setText(Integer.toString((int)(purse * 0.2 + 0.5)));

    }

    public void startTournament(View v) {
        ArrayList<Integer> playerIDs = getIntent().getExtras().getIntegerArrayList("playerUserID");
        //create the tournament
        int tournamentID = DatabaseHelper.getInstance().getTournamentDao().createTournament(
                getIntent().getExtras().getInt("EntranceFee") * 1.0,
                Integer.parseInt(mgrHouseProfit.getText().toString()),
                playerIDs
        );
        if(tournamentID == 0){
            Toast.makeText(getApplicationContext(), "ERROR Tournament ID is '0'",
                    Toast.LENGTH_SHORT).show();
        }
        //create dummy matches
        List<Integer> Matches = new ArrayList<Integer>();
        //i can trust the divide by 2 as only 8 or 16 players are allowed

        ArrayList<Integer> my_ints = new ArrayList<Integer>();
        String x = "";
        //creates first round of matches.
        for(int i = 0; i<playerIDs.size()/2;i++){
            int matchID = DatabaseHelper.getInstance().getMatchDao().createMatch(tournamentID,playerIDs.get(i),playerIDs.get(playerIDs.size()-i-1));
            my_ints.add(matchID);
            x = x + "Tid:" + Integer.toString(tournamentID) + ", Mid:" + Integer.toString(matchID) +
                    ", P1id:" + Integer.toString(playerIDs.get(i)) + ", P2id:" + Integer.toString(playerIDs.get(playerIDs.size()-i-1)) + "\n";
        }
        Toast.makeText(getApplicationContext(), x,
                Toast.LENGTH_SHORT).show();
        setResult(Activity.RESULT_OK,new Intent()); //return with result OK meaning we started a tournament
        //if a result is not provided (becauase the user hits back)
        finish(); //done with this activity



        Toast.makeText(getApplicationContext(), "Tournament Started",
                Toast.LENGTH_SHORT).show();
        //Tournament activeT = new Tournament()

    }

    @NonNull
    private List<Player> getPlayersFromdB(){
        List<Player> allPlayers = new ArrayList<Player>();
        //this is for the actual database
        allPlayers = DatabaseHelper.getInstance().getPlayerDao().getPlayers();

        ///====================
        /// This is dummy to fill players without interfacing with dB
        ///====================
        //use this as temporary to fill player list
        /*
        for(int i = 0; i<20; i++){
            allPlayers.add(new Player(i,
                            "player name " + Integer.toString(i),
                            "username" + Integer.toString(i),
                            Integer.toString(i) + "00-000-0000",
                            i * 3.0,
                            Deck.forValue(0)
                    )
            );
        }
        */
        return allPlayers;
    }

}
