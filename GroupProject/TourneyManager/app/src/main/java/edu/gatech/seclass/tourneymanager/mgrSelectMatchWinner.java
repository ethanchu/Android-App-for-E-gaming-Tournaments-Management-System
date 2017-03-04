package edu.gatech.seclass.tourneymanager;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Toast;

import edu.gatech.seclass.tourneymanager.dao.constants.MatchStatus;
import edu.gatech.seclass.tourneymanager.dao.constants.TournamentStatus;
import edu.gatech.seclass.tourneymanager.models.Tournament;
import edu.gatech.seclass.tourneymanager.models.Player;
import edu.gatech.seclass.tourneymanager.models.Match;
import edu.gatech.seclass.tourneymanager.dao.constants.Deck;
import edu.gatech.seclass.tourneymanager.dao.DatabaseHelper;

public class mgrSelectMatchWinner extends AppCompatActivity {
    private RadioButton MatchResultP1Wins;
    private RadioButton MatchResultP2Wins;
    private Button MatchResultSubmit;
    private int matchID;
    Match myMatch;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mgr_select_match_winner);

        matchID = getIntent().getExtras().getInt("matchID"); //get match ID from previous activity
        myMatch = DatabaseHelper.getInstance().getMatchDao().getMatch();
        MatchResultP1Wins = (RadioButton)findViewById(R.id.MatchResultP1Wins);
        MatchResultP2Wins = (RadioButton)findViewById(R.id.MatchResultP2Wins);
        MatchResultSubmit = (Button)findViewById(R.id.MatchResultSubmit);

        MatchResultP1Wins.setText(myMatch.getPlayer1().getName());
        MatchResultP1Wins.setText(myMatch.getPlayer2().getName());

    }
    public void setMatchWinner(View v){
        if(MatchResultP1Wins.isChecked()){
            //P1 wins
            DatabaseHelper.getInstance().getMatchDao().updateMatchStatus(
                    myMatch.getMatchId(),
                    MatchStatus.COMPLETED,
                    myMatch.getPlayer1().getPlayerId()
            );
            finish();
        }else if(MatchResultP2Wins.isChecked()){
            //P2 wins
            DatabaseHelper.getInstance().getMatchDao().updateMatchStatus(
                    myMatch.getMatchId(),
                    MatchStatus.COMPLETED,
                    myMatch.getPlayer2().getPlayerId()
            );

            finish();
        }else{
            //winner not selected
            Toast.makeText(getApplicationContext(), "you must select either player1 or player2 to win",
                    Toast.LENGTH_SHORT).show();
        }
    }
}
