package edu.gatech.seclass.tourneymanager;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import edu.gatech.seclass.tourneymanager.dao.PlayerDao;
import edu.gatech.seclass.tourneymanager.dao.constants.TournamentStatus;
import edu.gatech.seclass.tourneymanager.models.Tournament;
import edu.gatech.seclass.tourneymanager.models.Player;
import edu.gatech.seclass.tourneymanager.models.Match;
import edu.gatech.seclass.tourneymanager.dao.constants.Deck;;
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
    private String[] playerUserNames;
    private Player[] myPlayers;

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
        playerUserNames = getIntent().getExtras().getStringArray("playerUserNames");

        //set known fields
        mgrEntreeFee.setText(Integer.toString(entryFee));

        //set player list based of playerIDs.  This is O(N^2) implementation
        ArrayList<Player> tournamentPlayers = new ArrayList<Player>();
        for(int i = 0; i<playerUserNames.length; i++){
            for(int j = 0; j<myPlayers.length; j++){
                if(myPlayers[j].getUsername().equals(playerUserNames[i])){ //player names are equal
                    tournamentPlayers.add(myPlayers[j]);
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

        int entrantsNumberValue = playerUserNames.length;
        int entranceFeeValue = entryFee;
        int housePercentageValue = houseCut;

        //calclualte purse
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
        Tournament t = new Tournament(0,
                0,
                TournamentStatus.NOTSTARTED,
                entryFee * 1.0,
                houseCut,
                new ArrayList<Player>(),
                new ArrayList<Match>(),
                new ArrayList<Match>());


        Toast.makeText(getApplicationContext(), "Tournament Started",
                Toast.LENGTH_SHORT).show();
        //Tournament activeT = new Tournament()

    }

    @NonNull
    private Player[] getPlayersFromdB(){
        List<Player> allPlayers = new ArrayList<Player>();


        //use this as temporary to fill player list
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
        //use this when PlayerDao is ready
        //allPlayers = PlayerDao.getPlayers();
        //i use more primitive, fixed length Arrays, conver this to array here.
        return allPlayers.toArray(new Player[allPlayers.size()]);
    }

}
